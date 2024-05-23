package nz.ac.auckland.se281;

import java.util.List;

/** This class is the main entry point. */
public class MapEngine {
  private RiskGraph riskGraph;

  public MapEngine() {
    // add other code here if you want
    riskGraph = new RiskGraph();
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    // parse the countries list
    for (String c : countries) {
      // parse the country data
      String[] countryData = c.split(",");
      String name = countryData[0];
      String continent = countryData[1];
      int taxFees = Integer.parseInt(countryData[2]);
      // create the country object and add it to the list of countries
      Country country = new Country(name, continent, taxFees);
      riskGraph.addCountry(country);
    }

    // parse the adjacencies list
    for (String a : adjacencies) {
      // parse the adjacency data
      String[] adjacencyData = a.split(",");
      String country = adjacencyData[0];
      for (int i = 1; i < adjacencyData.length; i++) {
        String neighbour = adjacencyData[i];
        // add the edge between the two countries
        riskGraph.addEdge(country, neighbour);
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    MessageCli.INSERT_COUNTRY.printMessage();
    String countryName = Utils.scanner.nextLine();
    while (true) {
      try {
        Country country = riskGraph.getCountry(countryName);
        MessageCli.COUNTRY_INFO.printMessage(
            country.getName(), country.getContinent(), Integer.toString(country.getTaxFees()));
        break;
      } catch (CountryNotFoundException e) {
        MessageCli.INVALID_COUNTRY.printMessage(countryName);
        countryName = Utils.scanner.nextLine();
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
