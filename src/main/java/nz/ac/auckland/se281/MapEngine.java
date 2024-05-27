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
    Country country = getCountryInput();
    MessageCli.COUNTRY_INFO.printMessage(
        country.getName(), country.getContinent(), Integer.toString(country.getTaxFees()));
  }

  public Country getCountryInput() {
    while (true) {
      String countryName = Utils.capitalizeFirstLetterOfEachWord(Utils.scanner.nextLine());

      try {
        Country country = riskGraph.getCountry(countryName);
        return country;
      } catch (CountryNotFoundException e) {
        MessageCli.INVALID_COUNTRY.printMessage(countryName);
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    MessageCli.INSERT_SOURCE.printMessage();
    Country source = getCountryInput();
    MessageCli.INSERT_DESTINATION.printMessage();
    Country destination = getCountryInput();

    // if the source and destination are the same
    if (source.getName().equals(destination.getName())) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    }

    List<String> path = riskGraph.bfsShortestPath(source.getName(), destination.getName());

    int totalFees = riskGraph.getTaxFees(path);

    StringBuilder continents = new StringBuilder();
    continents.append("[");
    String lastContinent = "";
    for (String countryName : path) {
      try {
        Country country = riskGraph.getCountry(countryName);
        if (!country.getContinent().equals(lastContinent)) {
          if (continents.length() > 1) {
            continents.append(", ");
          }
          continents.append(country.getContinent());
          lastContinent = country.getContinent();
        }
      } catch (CountryNotFoundException e) {
        System.out.println("Error: Country not found in path calculation.");
        return;
      }
    }
    continents.append("]");

    MessageCli.ROUTE_INFO.printMessage(path.toString());
    MessageCli.CONTINENT_INFO.printMessage(continents.toString());
    MessageCli.TAX_INFO.printMessage(Integer.toString(totalFees));
  }
}
