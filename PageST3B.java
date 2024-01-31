package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PageST3B implements Handler {

    public static final String URL = "/page3B.html";

    @Override
    public void handle(Context context) throws Exception {
        String findCitiesButton = context.formParam("findCitiesButton");

    if (findCitiesButton != null && findCitiesButton.equals("Find Cities")) {
        // If the "Find Cities" button is clicked, update the city dropdown
        String selectedCountry = context.formParam("country_dropdown");
        ArrayList<String> cityNames = getCitiesByCountry(selectedCountry);

        StringBuilder updatedHtml = new StringBuilder(buildHtmlPage(context));
        int startIndex = updatedHtml.indexOf("<select class=\"w3-input w3-border w3-sand\" id='city_dropdown'");
        int endIndex = updatedHtml.indexOf("</select>", startIndex) + 9;

        updatedHtml.replace(startIndex, endIndex, ""); // Remove the existing city dropdown

        updatedHtml.insert(startIndex, "<select class=\"w3-input w3-border w3-sand\" id='city_dropdown' name='city_dropdown' value ='city_dropdown'>");
        for (String city : cityNames) {
            updatedHtml.insert(endIndex, "<option>" + city + "</option>");
        }

        context.html(updatedHtml.toString());
    } else {
        // If the button was not clicked, render the page as usual
        String html = buildHtmlPage(context);
        context.html(html);
    }
 }

    private String buildHtmlPage(Context context) {
        StringBuilder html = new StringBuilder("<html>");
        
        // Add some Head information
        html.append("<head>")
                .append("<title>Subtask 3.2</title>")
                //.append("<link rel='stylesheet' type='text/css' href='common.css' />")
                //.append("<link rel='stylesheet' type='text/css' href='custom.css' />")
                .append("<link rel='stylesheet' type='text/css' href='style.css' />")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1%\">")
                .append("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">")
                .append("<link rel='stylesheet' type='text/css' href='https://www.w3schools.com/w3css/4/w3.css' />")
                .append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">")
                .append("</head>");

        // Add the body
        html.append("<body>");

        // Add the topnav
        html.append("""
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
        """);

        // Add header content block
        html.append("""
            
                <section class="headline">
                    <h1>Time Series Analysis for and Temperature and Population</h1>
                    <h3>Explore the Past, Present, and Future with Our Time Series Analysis Tool.</h3>
               
            
        """);

        html.append("""
           
            <div class="w3-card w3-hover-shadow">
            <form action='/page3B.html' method='post'>
                <div id="checker" class="w3-container w3-blue">
                    <h2>Similarity Checker</h2>
                </br></div>
                        
                        """);
        // Add Div for page Content
        html.append("""
            <div class='w3-input w3-border w3-sand w3-round-large'>
                <label class="w3-text-brown"><b>Select Country</b></label>
                <select class="w3-input w3-border w3-sand" id='country_dropdown' name='country_dropdown'>
    """);
    
    String selectedCountry = context.formParam("country_dropdown");
    
    ArrayList<String> countryNames = getCountryNames();
    for (String country : countryNames) {
        if (country.equals(selectedCountry)) {
            html.append("<option selected>").append(country).append("</option>");
        } else {
            html.append("<option>").append(country).append("</option>");
        }//<button class="w3-btn w3-brown w3-round-large" onclick="findCities()">Find Sub Regions</button></p>
    }
    
    html.append("""
                </select>
            </div>
            <div>
                <p></p>
            <div><p></p>
                
                <button class="w3-btn w3-blue w3-round-large" id="btn-find" onclick="findCities()">Find Sub Regions</button></br>
            </div>
            <div class='w3-input w3-border w3-sand w3-round-large'>
                <label class="w3-text-brown w3-round-large"><b>Select Region</b></label>
                <select class="w3-input w3-border w3-sand" id='city_dropdown' name='city_dropdown' value ='city_dropdown'>
    """);
    
    String selectedValue = context.formParam("country_dropdown");
    ArrayList<String> cityNames = getCitiesAndStatesByCountry(selectedValue);
    for (String city : cityNames) {
        html.append("<option>").append(city).append("</option>");
    }

    // html.append("""
    //     <script>
    //         function findCities() {
    //             var selectedCountry = document.getElementById('country_dropdown').value;
    
    //             // Make an AJAX request to the server to fetch cities based on the selected country
    //             var xhr = new XMLHttpRequest();
    //             xhr.open('GET', '/getCities?country=' + selectedCountry, true);
    
    //             xhr.onreadystatechange = function () {
    //                 if (xhr.readyState == 4 && xhr.status == 200) {
    //                     // Update the city dropdown with the response from the server
    //                     document.getElementById('city_dropdown').innerHTML = xhr.responseText;
    //                 }
    //             };
    
    //             xhr.send();
    //         }
    //     </script>
    // """);
    
    html.append("""
                        </select>
                    </div>
                    
    
                    <p>
                        <label class="w3-text-brown w3-round-large"><b>Starting Year</b></label>
                        <input  type="number" id="start_year" name="start_year" class="w3-input w3-round-large w3-border w3-sand" min="1960" max="2015" placeholder="1960" value="1970"><br>
                    </p>
                    <p>
                        <label class="w3-text-brown w3-round-large"><b>Ending Year</b></label>
                        <input  type="number" id="end_year" name="end_year" class="w3-input w3-border w3-round-large w3-sand" min="1960" max="2015" placeholder="1960" value="2015"><br>
                    </p>
                    <p>
                        <label class="w3-text-brown w3-round-large"><b>Filtered By</b></label>
                        <select class="w3-input w3-border w3-sand" id='Temp_filter' name='Temp_filter' value ='Temp_filter'>
                        <option selected>Temperature</option>
                        <option selected>Population</option>
                        <option selected>Both</option>
                        </select>

                    </p>
                   
                        <input class="w3-radio" type="radio" id='filter_2' name="filter_2" value="absolute" checked>
                    <label class="w3-text-brown">Absolute Value</label>
                    <p>
                    <input class="w3-radio" type="radio" id='filter_2' name="filter_2" value="Relative">
                    <label class="w3-text-brown">Relative Value</label></br>
                
                    <p>
                        <button id='submit' class="w3-btn w3-blue w3-round-large">Submit</button><br>
                    </p>
                </form>
                <div class="w3-card-3"><br>
                    <p></p>
                </div>
            </div>
            </div>
        """);

        // Close Content div
        html.append("</div>").append("</section>");

        // String countryDropdown = context.formParam("country_dropdown");
        // if (countryDropdown == null) {
        //     html.append("<h2><i>No Results to show for dropdown</i></h2>")
        //         .append("<style>.h2{ text-aligned: center; }</style>");
        // } else {
        //     html.append(outputCountry(countryDropdown));
        // }

        // Add Div for page Content
        html.append(" </div>");
        html.append("<div class=\"w3-card-3 w3-round w3-ios-deep-blue \" id='pannel'>");


/*Displaying Data Table */

        String countryDropdown = context.formParam("country_dropdown");
        String startyear = context.formParam("start_year");
        String endyear = context.formParam("end_year");
        if (countryDropdown != null) {
            String queryResult = getSimilarityData2(countryDropdown,startyear,endyear);
            html.append(queryResult);
         }
         String cityDropdown = context.formParam("city_dropdown");
        //  if (cityDropdown != null) {
        //     String queryResult = getSimilarCityData(cityDropdown,startyear,endyear);
        //     html.append(queryResult);
        // }
        String filterdata = context.formParam("Temp_filter");

        if ("Temperature".equals(filterdata)) {  // Use equals method for string comparison
        String queryResult = getSimilarCityData(cityDropdown, startyear, endyear);
        html.append(queryResult);
        }
        if ("Both".equals(filterdata)) {  // Use equals method for string comparison
            String queryResult = getSimilarCityData(cityDropdown, startyear, endyear);
            html.append(queryResult);
        }
        String filter2 = context.formParam("filter_2");
        if ("Relative".equals(filter2)) {  // Use equals method for string comparison
            String queryResult = getSimilarityData(countryDropdown, startyear, endyear);
            html.append(queryResult);
        }
        

    

        html.append("</div>");
        // Footer
        html.append("""
            <br>
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
                </div>
            </footer>
        """);

        // Finish the HTML webpage
        html.append("</body>").append("</html>");
        return html.toString();
    }

    public ArrayList<String> getCountryNames() {
        ArrayList<String> countries = new ArrayList<>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(JDBCConnection.DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT * FROM PopulationData";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String countryname = results.getString("CountryName");
                countries.add(countryname);
            }

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return countries;
    }

    // public String outputCountry(String country) {
    //     StringBuilder html = new StringBuilder();
    //     html.append("<h2>").append(country).append(" Population from 1971 to 1975</h2>");

    //     ArrayList<String> countryName = getPopulationbyCountry(country);

    //     html.append("<ul>");
    //     for (String name : countryName) {
    //         html.append("<li>").append(name).append(" ").append("</li>");
    //     }
    //     html.append("</ul>");

    //     JDBCConnection jdbc = new JDBCConnection();
    //     ArrayList<Population> populations = jdbc.getPopulationbyCountry(country);

    //     html.append("<h2>")
    //             .append(country)
    //             .append(" Population with Years (from JDBCConnection)</h2>")
    //             .append("<ul>");
    //     for (Population population : populations) {
    //         html.append("<li>")
    //                 .append("Population of ").append(population.Countryname)
    //                 .append(" was ").append(population.populationbyYear)
    //                 .append(" in ").append(population.CountryCode).append("</li>");
    //     }
    //     html.append("</ul>");

    //     return html.toString();
    // }
    

    public ArrayList<String> getPopulationbyCountry(String countrynames) {
        ArrayList<String> countries = new ArrayList<>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(JDBCConnection.DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT * FROM populationdata where countryname ='" + countrynames + "' and year between 1971 and 1975";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String name = results.getString("population");
                String year = results.getString("year");
                countries.add(year);
                countries.add(name);
            }

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return countries;
    }
    //without percentage
    public String getSimilarityData2(String selectedCountry, String startyear,String endyear) {
        StringBuilder resultHtml = new StringBuilder();
    try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE2)) {
        String query = "SELECT DISTINCT" +
               "  p1.countryname AS selected_country, " +
               "  p1.year AS selected_year, " +
               "  p1.population AS selected_population, " +
               "  p2.countryname AS similar_country, " +
               "  p2.year AS similar_year, " +
               "  p2.population AS similar_population, " +
               "  ABS(p1.population - p2.population) AS population_difference " +
               "FROM " +
               "  populationdata p1 " +
               "JOIN " +
               "  populationdata p2 ON p1.countryname <> p2.countryname " +
               "                    AND p1.year = p2.year " +
               "                    AND p1.countryname = ? " +
               "                    AND p1.year BETWEEN ? AND ?" +
               "                    AND p1.countryname < p2.countryname " +
               "ORDER BY " +
               "  population_difference ASC " +
               "LIMIT 10";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, selectedCountry);
            preparedStatement.setString(2, startyear);
            preparedStatement.setString(3, endyear);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultHtml.append("<div class=\"w3-container\">")
                        .append("<h2 class='datadisplay'>Similar Countries from " + startyear + " to " +endyear+" </h2>")
                        .append("<table class=\"w3-table-all\">")
                        .append("<thead>")
                        .append("<tr class=\"w3-green\">")
                        .append("<th>Selected Country</th>")
                        .append("<th>Selected Year</th>")
                        .append("<th>Selected Population</th>")
                        .append("<th>Similar Country</th>")
                        .append("<th>Similar Year</th>")
                        .append("<th>Similar Population</th>")
                        .append("<th>Population Difference</th>")
                        .append("</tr>")
                        .append("</thead>");
        

                        while (resultSet.next()) {
                            resultHtml.append("<tr>")
                                    .append("<td>").append(resultSet.getString("selected_country")).append("</td>")
                                    .append("<td>").append(resultSet.getInt("selected_year")).append("</td>")
                                    .append("<td>").append(resultSet.getLong("selected_population")).append("</td>")
                                    .append("<td>").append(resultSet.getString("similar_country")).append("</td>")
                                    .append("<td>").append(resultSet.getInt("similar_year")).append("</td>")
                                    .append("<td>").append(resultSet.getLong("similar_population")).append("</td>")
                                    .append("<td>").append(resultSet.getLong("population_difference")).append("</td>")
                                    .append("</tr>");
                        }
                
                        resultHtml.append("</table>")
                                .append("</div>");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        resultHtml.append("<p>Error retrieving data</p>");
    }

    return resultHtml.toString();
}

public String getSimilarityData(String selectedCountry, String startyear, String endyear) {
    StringBuilder resultHtml = new StringBuilder();
    try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE2)) {
        String query = "SELECT DISTINCT" +
                "  p1.countryname AS selected_country, " +
                "  p1.year AS selected_year, " +
                "  p1.population AS selected_population, " +
                "  p2.countryname AS similar_country, " +
                "  p2.year AS similar_year, " +
                "  p2.population AS similar_population, " +
                "  ABS(p1.population - p2.population) AS population_difference, " +
                "  ROUND(((p1.population - p2.population) * 100.0 / p1.population), 2) AS population_percentage " +
                "FROM " +
                "  populationdata p1 " +
                "JOIN " +
                "  populationdata p2 ON p1.countryname <> p2.countryname " +
                "                    AND p1.year = p2.year " +
                "                    AND p1.countryname = ? " +
                "                    AND p1.year BETWEEN ? AND ?" +
                "                    AND p1.countryname < p2.countryname " +
                "ORDER BY " +
                "  population_difference ASC " +
                "LIMIT 10";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, selectedCountry);
            preparedStatement.setString(2, startyear);
            preparedStatement.setString(3, endyear);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultHtml.append("<div class=\"w3-container\">")
                        .append("<h2 class='datadisplay'>Relative Data Similar Countries from " + startyear + " to " + endyear + " </h2>")
                        .append("<table class=\"w3-table-all\">")
                        .append("<thead>")
                        .append("<tr class=\"w3-green\">")
                        .append("<th>Selected Country</th>")
                        .append("<th>Selected Year</th>")
                        .append("<th>Selected Population</th>")
                        .append("<th>Similar Country</th>")
                        .append("<th>Similar Year</th>")
                        .append("<th>Similar Population</th>")
                        .append("<th>Population Difference</th>")
                        .append("<th>Population Percentage Difference</th>")
                        .append("</tr>")
                        .append("</thead>");

                while (resultSet.next()) {
                    resultHtml.append("<tr>")
                            .append("<td>").append(resultSet.getString("selected_country")).append("</td>")
                            .append("<td>").append(resultSet.getInt("selected_year")).append("</td>")
                            .append("<td>").append(resultSet.getLong("selected_population")).append("</td>")
                            .append("<td>").append(resultSet.getString("similar_country")).append("</td>")
                            .append("<td>").append(resultSet.getInt("similar_year")).append("</td>")
                            .append("<td>").append(resultSet.getLong("similar_population")).append("</td>")
                            .append("<td>").append(resultSet.getLong("population_difference")).append("</td>")
                            .append("<td>").append(resultSet.getDouble("population_percentage")).append("%</td>")
                            .append("</tr>");
                }

                resultHtml.append("</table>")
                        .append("</div>");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        resultHtml.append("<p>Error retrieving data</p>");
    }

    return resultHtml.toString();
}

    
public String getSimilarCityData(String selectedCity,String startyear,String endyear ) {
    StringBuilder resultHtml = new StringBuilder();
    try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE2)) {
        String query = "SELECT " +
               "  t1.city AS selected_location, " +
               "  t1.year AS selected_year, " +
               "  t1.AverageTemperature AS selected_temperature, " +
               "  t2.city AS similar_location, " +
               "  t2.year AS similar_year, " +
               "  t2.AverageTemperature AS similar_temperature, " +
               "  ABS(t1.AverageTemperature - t2.AverageTemperature) AS temperature_difference " +
               "FROM " +
               "  GlobalYearlyLandTempByCity t1 " +
               "JOIN " +
               "  GlobalYearlyLandTempByCity t2 ON t1.city <> t2.city " +
               "                                AND t1.year = t2.year " +
               "                                AND t1.city = ? " +
               "                                AND t1.year BETWEEN ? AND ? " +
               "ORDER BY " +
               "  selected_location, " +
               "  selected_year, " +
               "  temperature_difference " +
               "LIMIT 5";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, selectedCity);
            preparedStatement.setString(2, startyear);
            preparedStatement.setString(3, endyear);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultHtml.append("<div class=\"w3-container\">")
                        .append("<h2 class=\"datadisplay\" >Similar Cities from " + startyear + " to " +endyear+ "</h2>")
                        .append("<table class=\"w3-table-all\">")
                        .append("<thead>")
                        .append("<tr class=\"w3-green\">")
                        .append("<th>Selected Location</th>")
                        .append("<th>Selected Year</th>")
                        .append("<th>Selected Temperature</th>")
                        .append("<th>Similar Location</th>")
                        .append("<th>Similar Year</th>")
                        .append("<th>Similar Temperature</th>")
                        .append("<th>Temperature Difference</th>")
                        .append("</tr>")
                        .append("</thead>");

                while (resultSet.next()) {
                    resultHtml.append("<tr>")
                            .append("<td>").append(resultSet.getString("selected_location")).append("</td>")
                            .append("<td>").append(resultSet.getInt("selected_year")).append("</td>")
                            .append("<td>").append(resultSet.getDouble("selected_temperature")).append("</td>")
                            .append("<td>").append(resultSet.getString("similar_location")).append("</td>")
                            .append("<td>").append(resultSet.getInt("similar_year")).append("</td>")
                            .append("<td>").append(resultSet.getDouble("similar_temperature")).append("</td>")
                            .append("<td>").append(resultSet.getDouble("temperature_difference")).append("</td>")
                            .append("</tr>");
                }

                resultHtml.append("</table>")
                        .append("</div>");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        resultHtml.append("<p>Error retrieving data</p>");
    }

    return resultHtml.toString();
}


public ArrayList<String> getCitiesByCountry(String selectCountry) {
    ArrayList<String> cities = new ArrayList<>();

    Connection connection = null;

    try {
        connection = DriverManager.getConnection(JDBCConnection.DATABASE2);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        String query = "SELECT distinct city FROM GlobalYearlyLandTempByCity WHERE country ='" + selectCountry +"'";

        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String cityName = results.getString("city");
            cities.add(cityName);
        }

        statement.close();
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    return cities;
}
public ArrayList<String> getCitiesAndStatesByCountry(String selectCountry) {
    ArrayList<String> locations = new ArrayList<>();

    Connection connection = null;

    try {
        connection = DriverManager.getConnection(JDBCConnection.DATABASE2);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // Query for distinct cities
        String cityQuery = "SELECT DISTINCT city AS location FROM GlobalYearlyLandTempByCity WHERE country = '" + selectCountry + "'";
        ResultSet cityResults = statement.executeQuery(cityQuery);

        while (cityResults.next()) {
            String cityName = cityResults.getString("location");
            locations.add(cityName);
        }

        // Query for distinct states
        String stateQuery = "SELECT DISTINCT state AS location FROM GlobalYearlyLandTempByState WHERE country = '" + selectCountry + "'";
        ResultSet stateResults = statement.executeQuery(stateQuery);

        while (stateResults.next()) {
            String stateName = stateResults.getString("location");
            locations.add(stateName);
        }

        statement.close();
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    return locations;
}

}
