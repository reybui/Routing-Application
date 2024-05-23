package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RiskGraph {

  private Map<String, Country> countries;
  private Map<String, List<String>> graph;

  public RiskGraph() {
    countries = new HashMap<>();
    graph = new HashMap<>();
  }

  // adds nodes on the graph and adds a country to the countries list
  public void addCountry(Country country) {
    countries.put(country.getName(), country);
    graph.putIfAbsent(country.getName(), new LinkedList<>());
  }

  // adds an edge between two countries
  public void addEdge(String country1, String country2) {
    graph.get(country1).add(country2);
    graph.get(country2).add(country1);
  }

  public Country getCountry(String name) throws CountryNotFoundException {
    if (!countries.containsKey(name)) {
      throw new CountryNotFoundException();
    }
    return countries.get(name);
  }
}
