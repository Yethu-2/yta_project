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
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + """
                     <link rel='stylesheet' type='text/css' href='https://www.w3schools.com/w3css/4/w3.css' />
                     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                      """;
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
            <a href="/" class="logo-container">
            <button class="btn"><i class="fa fa-maxcdn fa-2x"></i></button>
            <span class="logo-text">etaStat</span>
            </a>
                
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='page2A.html'>Sub Task 2.A</a>
                <a href='page2B.html'>Sub Task 2.B</a>
                <a href='page3A.html'>Sub Task 3.A</a>
                <a href='page3B.html'>Sub Task 3.B</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <section class="headline">
                
                <h1>Monitoring Temperatures Across Different Time Spans And Locations</h1>
                <h3>Our database offers comprehensive temperature and world population information, encompassing data from numerous trusted statics article and research. Explore the temperature of yesterday or discover into weather patterns from centuries past.<h3>
                <br>
                <a href="page2A.html">Explore Now</a>
                <br>
            </section>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """
            <h1>Designed for those who</h1>
            <br>
            <div class="container">
         <div class="boxc">
          <img src="female-student.jpg" alt="Jessica Jones" class="profile-image">
          <br>
         <h2>Jessica Jones</h2>
         <br>
         <p>17/Female</p>
          <p><strong>Background</strong></p>
         <p>A 17 years old female student studying & residing at Melbourne</p>
         <p><strong>Skills</strong></p>
         <p>Owns a smartphone & laptop and very familiar with web browsing and technology</p>
         <p><strong>Habits</strong></p>
         <p>Relatively uninterested to environmental matters Prefers short, concise paragraphs that are easy to read</p>
          <p><strong>Pain Points</strong></p>
          <p>Having to look at multiple websites to make a deep down research for the matter. Having to copy the content of the site by hand</p>
          <p><strong>Goals</strong></p>
         <p>Searching for concise and simple website detailing climate changes in Australia with brief explanations for statistics and terminology.</p>
         </div>
          <div class="boxc">
          <img src="male-student.png" alt="Micheal Smith" class="profile-image">
          <br>
         <h2>Micheal Smith</h2>
          <br>
         <p>17/Male</p>
         <p><strong>Background</strong></p>
         <p>A 17-year-old male high school student Currently living in Portland, Oregon</p>
         <p><strong>Skills</strong></p>
         <p>Good at using platforms to talk about climate change. Enjoys sharing ideas and stories online</p>
         <p><strong>Habits</strong></p>
         <p>Enjoys learning about global issues and is curious about the impact of climate change on different regions</p>
         <p><strong>Pain Points</strong></p>
         <p>Frustrated with the lack of easily accessible resources for school projects related to climate change</p>
          <p><strong>Goals</strong></p>
          <p>Looking for a user-friendly web app for easy exploration of global and regional climate change data. Also, needs educational resources for school projects and presentations on climate science.</p>
          </div>
          <div class="boxc">
          <img src="professor.png" alt="Allison Green" class="profile-image">
          <br>
          <h2>Allison Green</h2>
         <br>
         <p>40/Female</p>
          <p><strong>Background</strong></p>
         <p>Born in 1985, Ph.D in Geology and a Master's degree in Environmental Science</p>
         <p><strong>Skills</strong></p>
          <p>Have a decent knowledge about web browsing, expert in every environmental terms</p>
         <p><strong>Habits</strong></p>
          <p>Geology and weather are her jam, and she specifically look deep into how hotter temps have impact on mountains, rocks, and everything in between.</p>
          <p><strong>Pain Points</strong></p>
          <p>Has problems because of missing data, different methods used, or not enough data. This can affect how correct and detailed her research is.</p>
          <p><strong>Goals</strong></p>
          <p>Looking for a platform to share findings and access extensive global temperature data for analysis, focusing on reputable scientific journals in geology, climate science, and environmental studies.</p>
          </div>
          </div>
          <br>
            """;

         // Get the ArrayList of Strings of all LGAs//ArrayList<String> lgaNames = getLGAs2016();

          // Add HTML for the LGA list//html = html + "<h1>All 2016 LGAs in the Voice to Parliament database</h1>" + "<ul>";
         // Finally we can print out all of the LGAs//for (String name : lgaNames) {//    html = html + "<li>" + name + "</li>";//}

          // Finish the List HTML
           //html = html + "</ul>";// Close Content div//html = html + "</div>";// Footer
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
     * Get the names of the LGAs in the database.
     */
    public ArrayList<String> getLGAs2016() {
        // Create the ArrayList of LGA objects to return
        ArrayList<String> lgas = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Population";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String name16  = results.getString("CountryName");

                // Add the lga object to the array
                lgas.add(name16);
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

        // Finally we return all of the lga
        return lgas;
    }
}