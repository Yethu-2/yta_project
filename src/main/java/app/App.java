package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;


/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the 
 * Javalin HTTP Server and our web application.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class App {

    public static final int         JAVALIN_PORT    = 7001;
    public static final String      CSS_DIR         = "css/";
    public static final String      IMAGES_DIR      = "images/";

    public static void main(String[] args) {
        // Create our HTTP server and listen in port 7001
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            
            // Uncomment this if you have files in the CSS Directory
            config.addStaticFiles(CSS_DIR);

            // Uncomment this if you have files in the Images Directory
            config.addStaticFiles(IMAGES_DIR);
        }).start(JAVALIN_PORT);


        // Configure Web Routes
        configureRoutes(app);
    }

    public static void configureRoutes(Javalin app) {
        // Note in this example we must add Movies Type as a GET and a POST!
        
        // ADD ALL OF YOUR WEBPAGES HERE
        app.get(PageIndex.URL, new PageIndex());
        app.get(PageMoviesList.URL, new PageMoviesList());      
        app.get(PageMoviesType.URL, new PageMoviesType());
        app.get(PageMission.URL, new PageMission());
        app.get(PageST2A.URL, new PageST2A());
        app.get(PageST2B.URL, new PageST2B());
        app.get(PageST3A.URL, new PageST3A());
        app.get(PageST3B.URL, new PageST3B());
        app.get(CountryName.URL, new CountryName());

        // POST pages can accept form data
        app.post(PageMoviesType.URL, new PageMoviesType());
        app.post(CountryName.URL, new CountryName());
        app.post(PageST3B.URL, new PageST3B());

    }

}
