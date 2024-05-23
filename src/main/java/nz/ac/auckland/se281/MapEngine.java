package nz.ac.auckland.se281;

import java.util.List;

/** This class is the main entry point. */
public class MapEngine {

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    
    // create countries and add it to the graph
    for(String c : countries) {
      // parse the country data
      String[] countryData = c.split(",");
      String name = countryData[0];
      String continent = countryData[1];
      int taxFees = Integer.parseInt(countryData[2]);

      // create the country object
      Country country = new Country(name, continent, taxFees);

    }

  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
