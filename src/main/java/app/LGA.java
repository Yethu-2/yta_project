package app;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2016
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 */
public class LGA {
   // LGA Code
   protected String code;

   // LGA Name
   protected String name;

   // LGA Year
   protected String year;

   /**
    * Create an LGA and set the fields
    */
   public LGA(String code, String name, String year) {
      this.code = code;
      this.name = name;
      this.year = year;
   }
   public LGA(){}
   public String getCode() {
      return code;
   }

   public String getName() {
      return name;
   }

   public String getYear() {
      return year;
   }
}
