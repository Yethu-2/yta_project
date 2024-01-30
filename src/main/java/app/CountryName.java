package app;

import java.util.ArrayList;
import java.util.List;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class CountryName implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/countryname.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>List All Country</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + """
            <meta name="viewport" content="width=device-width, initial-scale=1%">
            <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
                """;
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='movies.html'>List All Movies</a>
                <a href='moviestype.html'>Get Movies by Type</a>
                <a href='countryname.html'>Get Country Name</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>
                    List all Country
                </h1>
            </div>
        """;

        html = html + "<form action='/countryname.html' method='post'>";
        html = html + "   <div class='w3-input w3-border w3-sand'>";
        html = html + "      <label for='country_dropdown'>Select the Country (Dropdown):</label>";
        html = html + "      <select id='country_dropdown' name='country_dropdown'>";
        
        List<String> countryNames = getCountryNames();
        for (String country : countryNames) {
            html = html + "         <option>" + country + "</option>";
        }

        html = html + "      </select>";
        html = html + "   </div>";
        html = html + "   <button type='submit' class='w3-btn w3-blue w3-round-large'>Get Population</button>";
        html = html + "</form>";
        String country_dropdown = context.formParam("country_dropdown");
        // String movietype_drop = context.queryParam("movietype_drop");
        if (country_dropdown == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            html = html + outputCountry(country_dropdown);
        }


        // Footer
        html = html + """
            <div class='footer'>
                <p>COSC3056 Module 0 - Week 06</p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }


    /**
     * Get all of the Movie Titles in the database
     * @return
     *    Returns an ArrayList of String with ONLY the movie titles
     */
    public ArrayList<String> getCountryNames() {
        // Create the ArrayList to return - of Strings for the movie titles
        ArrayList<String> countries = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE2);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM PopulationData";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String countryname     = results.getString("CountryName");

                // Store the movieName in the ArrayList to return
                countries.add(countryname);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movie titles
        return countries;
    }
    public String outputCountry(String country) {
        String html = "";
        html = html + "<h2>" + country + " Population from 1971 to 1975</h2>";

        // Look up movies from JDBC
        ArrayList<String> countryName = getPopulationbyCountry(country);
        
        // Add HTML for the movies list
        html = html + "<ul>";
        for (String name : countryName) {
            html = html + "<li>" + name + " "+"</li>";
        }
        html = html + "</ul>";

        // Altneratively we can use JDBCConnection to add HTML for the movies list
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Population> populations = jdbc.getPopulationbyCountry(country);
        html = html + "<h2>" + 
                      country +
                      " Population with Years (from JDBCConnection)</h2>" +
                      "<ul>";
        for (Population population : populations) {
            html = html + "<li>" +"Population of "+ population.Countryname + " was " + population.populationbyYear + " in " + population.CountryCode + "</li>";
        }
        html = html + "</ul>";

        return html;
    }

    /**
     * Get all the movies in the database by a given type.
     * Note this takes a string of the type as an argument!
     * This has been implemented for you as an example.
     * HINT: you can use this to find all of the horror movies!
     */
    public ArrayList<String> getPopulationbyCountry(String countrynames) {
        ArrayList<String> countries = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE2);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM populationdata where countryname ='" + countrynames + "' and year between 1971 and 1975";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String name = results.getString("population");
                String year = results.getString("year");
                countries.add(year);
                countries.add(name);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countries;
    }

}
