package app;

/**
 * Class represeting a Movie from the Movies database
 * For simplicity this uses public fields
 * You could use private fields with getters to be safer
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 */
public class Population {
   // Movie id
   public String Countryname;

   // Movie name
   public String CountryCode;

   // Year the movie was made
   public String populationbyYear;

   // String representing the movie genre
   /**
    * Create a movie withou setting any of the fields,
    * Fields will have default "empty" values
    */
   public Population() {
   }

   /**
    * Create a movie setting all of the fields
    */
   public Population(String Countryname, String CountryCode, String populationbyYear) {
      
      this.Countryname = Countryname;
      this.CountryCode = CountryCode;
      this.populationbyYear = populationbyYear;
   }
}
