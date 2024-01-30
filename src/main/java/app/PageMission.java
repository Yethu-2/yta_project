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
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Our Mission</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + """

                     <link rel='stylesheet' type='text/css' href='https://www.w3schools.com/w3css/4/w3.css' />
                     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                     <script src="https://kit.fontawesome.com/1165876da6.js" crossorigin="anonymous"></script>
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
                      <body>
                          <div class="about-us">
                             <div class="about-section">
                                 <h2 class="about-title">About Us</h2>
                                 <p class="about-text">Our mission is to assist governmental bodies, scientific communities, researchers, and the public in scrutinizing the evolution of oceanic and terrestrial temperatures over a span of 260 years. We strive to accommodate a broad spectrum of users, ranging from those seeking a high-level understanding to those desiring a comprehensive analysis of the data. Our primary emphasis is on discerning trends across diverse temporal scales and geographical locations. By juxtaposing all available statistics, we aim to enlighten individuals about the geological facets of environmental issues and underscore the significance of climate change.</p>
                             </div>
                             <br>
                          <div class="info-section">
                             <div class="info-box">
                                 <div class="number">707,973</div>
                                     <div class="label">results by cities</div>
                                     <i class="fa-solid fa-city fa-2x" aria-hidden="true"></i>
                                 </div>
                             <div class="info-box">
                                 <div class="number">260</div>
                                     <div class="label">   within years</div>
                                     <i class="fa fa-calendar fa-2x" aria-hidden="true"></i>
                                 </div>
                             <div class="info-box">
                                  <div class="number">40,845</div>
                                  <div class="label">results by countries</div>
                                  <i class="fa-solid fa-map-location-dot fa-2x" aria-hidden="true"></i>
                             </div>
                             <div class="info-box">
                                 <div class="number">11,024</div>
                                 <div class="label">results by population</div>
                                 <i class="fa-solid fa-people-line fa-2x" aria-hidden="true"></i>
                             </div>
                           </div>
                         </div>
                        </body>
                        
                         """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """
            <section class="team">
		<div class="center">
			<h1>Our Team</h1>
            <h3 style="text-align: center; font-style:'Roboto';padding:20px ;color: black ; ">Meet the dynamic and innovative IT student team at RMIT University, a group of aspiring technologists dedicated to pushing the boundaries of information technology</h3>
		</div>

		<div class="team-content">
			<div class="box">
				<img src="teammimg1.jpg">
				<h3>Linn Htin Nyo</h3>
				<h5>s4037889@rmit.edu.vn</h5>
				<div class="icons">
					<a href="#"><i class="fa fa-twitter"></i></a>
					<a href="#"><i class="fa fa-facebook"></i></a>
					<a href="#"><i class="fa fa-instagram"></i></a>
				</div>
			</div>

			<div class="box">
				<img src="teammimg2.jpg">
				<h3>Truong Gia Loi</h3>
				<h5>s3924514@rmit.edu.vn</h5>
				<div class="icons">
					<a href="#"><i class="fa fa-twitter"></i></a>
					<a href="#"><i class="fa fa-facebook"></i></a>
					<a href="#"><i class="fa fa-instagram"></i></a>
				</div>
			</div>
            <div class="box">
				<img src="teammimg4.jpg">
				<h3>Ye Thu Aung</h3>
				<h5>s4066945@rmit.edu.vn</h5>
				<div class="icons">
					<a href="#"><i class="fa fa-twitter"></i></a>
					<a href="#"><i class="fa fa-facebook"></i></a>
					<a href="#"><i class="fa fa-instagram"></i></a>
				</div>
			</div>

			<div class="box">
				<img src="teammimg3.jpg">
				<h3>Kim JongOh</h3>
				<h5>s3924514@rmit.edu.vn</h5>
				<div class="icons">
					<a href="#"><i class="fa fa-twitter"></i></a>
					<a href="#"><i class="fa fa-facebook"></i></a>
					<a href="#"><i class="fa fa-instagram"></i></a>
				</div>
			</div>
            <div class="box">
				<img src="teammimg5.jpg">
				<h3>Ngo Dang Khoi</h3>
				<h5>s3924514@rmit.edu.vn</h5>
				<div class="icons">
					<a href="#"><i class="fa fa-twitter"></i></a>
					<a href="#"><i class="fa fa-facebook"></i></a>
					<a href="#"><i class="fa fa-instagram"></i></a>
				</div>
			</div>

		</div>
	</section>
    <br>
    <br>
    </div>
            """;
            html = html + """
                <br>
                <h1 style="text-align: center; font-style:'Roboto';color: black; font-weight:600;font-size:4rem;">Our Work Includes</h1>
                <div class="lower-container container">
                <section class="stats">
                  <div class="flex">
                    <div class="stat">
                      <i class="fa fa-calendar-check-o fa-2x" aria-hidden="true"></i>
                      <h3 class="title">Year Range</h3>
                      <p class="text"> We offer an extensive dataset spanning from <strong>1750</strong> to<strong> 2013</strong>, with the flexibility to present information tailored to your preferred year of interest.</p>
                    </div>
                    <div class="stat">
                      <i class="fa fa-users fa-2x" aria-hidden="true"></i>
                      <h3 class="title">Population</h3>
                      <p class="text">The population data, which spans from <strong>1960</strong> to <strong>2013</strong>, is available at both a global scale and at the granularity of individual countries.</p>
                    </div>
                    <div class="stat">
                      <i class="fa fa-thermometer-three-quarters fa-2x" aria-hidden="true"></i>
                      <h3 class="title">Global Temperature </h3>
                      <p class="text">We will provide a detailed analysis of temperature statistics, derived from various countries, presented in a manner that is both concise and informative.</p>
                    </div>
                  </div>
                </section>
                </div>
               
                """;

        // This example uses JDBC to lookup the LGAs
        JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this class for the LGAs
        ArrayList<LGA> lgas = jdbc.getPopulation();

        // Add HTML for the LGA list
        //html = html + "<h1>Population of all the countries during 1960 (Using JDBC Connecton)</h1>"+ "<ul>";

       // html = html + "<table class=\"w3-table w3-striped w3-bordered\">";
       // html = html + "<tr>";
       // html = html + "<th>Country Name</th>";
       // html = html + "<th>Country Code</th>";
        //html = html + "<th>Population</th>";
       // html = html + "</tr>";
       // html = html + "<tr>";
       // for (LGA lga : lgas) {
       //     html = html + "<tr>";
       //     html = html + "<td>" + lga.getCode() + "</td>"
       //                + "<td>" + lga.getName() + "</td>"
       //                + "<td>" + lga.getYear() + "</td>";
        //    html = html + "</tr>";
       // }
        //html = html+ "</tr>" ;
        // Finally we can print out all of the LGAs
        /*for (LGA lga : lgas) {
            html = html + "<li>" + lga.getCode()
                        + " - " + lga.getName() + "</li>";
        }*/

        // Finish the List HTML
       // html = html + "</ul>";

       // html = html +"</table>";
        // Close Content div
       // html = html + "</div>";

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