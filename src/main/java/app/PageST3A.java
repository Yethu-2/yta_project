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
public class PageST3A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 3.1</title>";

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
            <div class='header1'>
            <section class="headline1">
                <h1>Time Series Analysis for and Temperature</h1>
            <h3>Explore the Past, Present, and Future with Our Time Series Analysis Tool. Uncover Trends and Correlations in Temperature Data. Gain Insights<h3>
            </div>

            """;
        // Add Div for page Content
        html = html + "<div class='content'>";
        

        // Add HTML for the page content
        html = html + """
        <div class="initiate">Initiate the process by setting up crucial initial parameters</div>
        <div style="width: 100%; height:5%; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 18px; display: inline-flex">
          <div style="color: #282938; font-size: 24px; font-family: Roboto; font-weight: 400;  word-wrap: break-word">Starting Years</div>
          </div>
        <form class="example" action="action_page.php">
        <input type="text" placeholder="Search.." name="search">
        </form>
        <div style="color: #282938; font-size: 24px; font-family: Roboto; font-weight: 400;  word-wrap: break-word">View By</div>
        
        
        
        <div class="dropdown">
        <button class="dropbtn">Year Range</button>
        <div class="dropdown-content">
                <a href="#">5 years</a>
                <a href="#">10 years</a>
                <a href="#">15 years</a>
        </div>
        </div>
    
    
 
    
    <div class="radio-group">
            <input class="radio-input" name="radio-group" id="radio1" type="radio">
            <label class="radio-label" for="radio1">
              <span class="radio-inner-circle"></span>
              World
            </label>
            
            <input class="radio-input" name="radio-group" id="radio2" type="radio">
            <label class="radio-label" for="radio2">
              <span class="radio-inner-circle"></span>
              Country
            </label>

            <input class="radio-input" name="radio-group" id="radio3" type="radio">
            <label class="radio-label" for="radio3">
              <span class="radio-inner-circle"></span>
              State
            </label>

            <input class="radio-input" name="radio-group" id="radio4" type="radio">
            <label class="radio-label" for="radio4">
              <span class="radio-inner-circle"></span>
              City
            </label>
          </div>

          <label for="countryname" class="country-label" style="color: #282938; font-size: 24px; font-family: Roboto; font-weight: 400;  word-wrap: break-word">Country Name:</label>
        <input type="text" id="countryname" name="countryname" class="country-input">
        </section>

    """;

        // Close Content div
        html = html + "</div>";

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

}
