package app;

import java.util.ArrayList;

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
public class PageST3B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3B.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 3.2</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='custom.css' />";
        html = html + """
            <meta name="viewport" content="width=device-width, initial-scale=1%">
            <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
                """;
        html = html + """
                     <link rel='stylesheet' type='text/css' href='https://www.w3schools.com/w3css/4/w3.css' />
                     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                      """;
        html = html + "</head>";
        html = html +"""
            <style>
            .w3-card-4 {
              padding:20px;
             width: 40%;
              text-align: left;
              margin: 0 auto; 
            }
            .w3-card-4 h2{
              text-align: center;
            }
            body{
                background: rgb(174,233,238);
                background: radial-gradient(circle, rgba(174,233,238,1) 0%, rgba(148,187,233,1) 100%);
                 
            }
            .topnav{
                background: linear-gradient(90deg, #00d2ff 0%, #3a47d5 100%);
                
                
                order-radius: 10px;
                position:-webkit-sticky;
                position: sticky;
                top: 0%;
                width: 100%;
                height: 80px;
                color: #fff;
                display: flex;
                justify-content: space-around;
                align-items: center;
            }
            
          </style>
                """;
        // Add the body
        html = html + "<body>";
        html = html + "<div class='content'>";
        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='page2A.html'>Sub Task 2.A</a>
                <a href='page2B.html'>Sub Task 2.B</a>
                <a href='page3A.html'>Sub Task 3.A</a>
                <a href='page3B.html'>Similarity Checker</a>
            </div>
        """;

        // Add header content block
         html = html + """
            <div class='header1'>
            <section class="headline1">
                <h1>Time Series Analysis for and Temperature and Population</h1>
            <h3>Explore the Past, Present, and Future with Our Time Series Analysis Tool.<h3>
            </div>

            """;
           
        // Add Div for page Content
       
        

        // Add HTML for the page content

// Form
html = html+ """
    <div class="w3-card-4">
<div class="w3-container w3-brown">
<h2>Similarity Checker</h2>
</div>
""";
html = html + "<form action='/page3B.html' method='post'>";

        html = html + "   <div class='w3-input w3-border w3-sand'>";
        html = html + "      <label class=\"w3-text-brown\"><b>Search By</b></label>";
        html = html + "      <select class=\"w3-input w3-border w3-sand\" id='country_dropdown' name='country_dropdown'>";
        
        ArrayList<String> countryNames = getCountryNames();
        for (String country : countryNames) {
            html = html + "         <option>" + country + "</option>";
        }

        html = html + "      </select>";
        html = html + "   </div>";
        //html = html + "   <button type='submit' class='w3-btn w3-blue w3-round-large'>Get Population</button>";
       // html = html + "</form>";

// <form class="w3-container" action="/action_page.php">
// <p>      
// <label class="w3-text-brown"><b>Search By</b></label>
// <div class="custom-select">
// <select class="w3-input w3-border w3-sand" id="region" name="region">
// <option value="saab">Country</option>
// <option value="fiat">State</option>
// <option value="audi">City</option>
// </select>
// <div class="select-arrow"></div>
// </div>

        

html = html + """
<p> 
<label class="w3-text-brown"><b>Type Region Name</b></label>
<input class="w3-input w3-border w3-sand" name="last" placeholder="Type Region" type="text"></p>
<p>
<label class="w3-text-brown"><b>Starting Year</b></label>
<input class="w3-input w3-border w3-sand" name="last" placeholder="1960" type="number"></p>
<p>

<label class="w3-text-brown"><b>Year Range</b></label>
<input type="number" class="w3-input w3-border w3-sand" min="5" max="20" value="5"><br>
<p>
<label class="w3-text-brown"><b>Filtered By</b></label>
<select class="w3-input w3-border w3-sand" id="region" name="region">
<option value="saab">Temperature</option>
<option value="fiat">Population</option>
<option value="audi">Both</option>
</select>
</p>

<input class="w3-radio" type="radio" name="gender" value="absolute" checked>
<label>Absolute Value</label>
<p>
<input class="w3-radio" type="radio" name="gender" value="female">
<label>Relative Value</label>
</p>
<p>
<button class="w3-btn w3-blue w3-round-large">Submit</button><br>
</p>
<div class="w3-card-3"><br>
<p></p>
</div>

</form>

<div>
</div>

       """;

           // Close Content div
      html = html + "</div>";
      html = html + "<div class= 'output'>";

// html = html + "<form action='/countryname.html' method='post'>";
//         html = html + "   <div class='w3-input w3-border w3-sand'>";
//         html = html + "      <label for='country_dropdown'>Select the Country (Dropdown):</label>";
//         html = html + "      <select id='country_dropdown' name='country_dropdown'>";
        
//         ArrayList<String> countryNames = getCountryNames();
//         for (String country : countryNames) {
//             html = html + "         <option>" + country + "</option>";
//         }

//         html = html + "      </select>";
//         html = html + "   </div>";
//         html = html + "   <button type='submit' class='w3-btn w3-blue w3-round-large'>Get Population</button>";
//         html = html + "</form>";
        String country_dropdown = context.formParam("country_dropdown");
        // String movietype_drop = context.queryParam("movietype_drop");
        if (country_dropdown == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h2><i>No Results to show for dropbox</i></h2>";
            html = html +"""
                <style> .h2{
                text-aligned: center;
            } </style>
                    
                    """; 
        } else {
            // If NOT NULL, then lookup the movie by type!
            html = html + outputCountry(country_dropdown);
        }

// Add Div for page Content
        html = html +" </div>";
        // Footer
        html = html + """
                <br>
                <body>
                   <footer>
                       <div class="footer">
                           <div class="row">
                               <a href="#"><i class="fa fa-facebook"></i></a>
                               <a href="#"><i class="fa fa-instagram"></i></a>
                               <a href="#"><i class="fa fa-youtube"></i></a>
                               <a href="#"><i class="fa fa-twitter"></i></a>
                           </div>
                           <div class="row">
                               <ul>
                                   <li><a href="/">Home</a></li>
                                   <li><a href="mission.html">Our Mission</a></li>
                                   <li><a href="page2A.html">Explore Data</a></li>
                                   <li><a href="page3A.html">Time Series Analysis</a></li>
                                   <li><a href="#">Privacy Policy</a></li>
                                   </ul>
                           </div>

                           <div class="row">
                                Copyright © Metastat | Designed by Dean-S4037889(SG03-G05) - All rights reserved || Powered by SG03-G05 
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
