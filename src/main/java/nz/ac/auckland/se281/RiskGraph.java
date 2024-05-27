package nz.ac.auckland.se281;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
    // graph.get(country2).add(country1);
  }

  public Country getCountry(String name) throws CountryNotFoundException {
    if (!countries.containsKey(name)) {
      throw new CountryNotFoundException();
    }
    return countries.get(name);
  }

  // BFS to find the shortest path between two countries
  public List<String> bfsShortestPath(String start, String end) {
    Queue<String> queue = new LinkedList<>();
    Map<String, String> previous = new HashMap<>();
    Set<String> visited = new HashSet<>();
    queue.add(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      String current = queue.poll();
      if (current.equals(end)) {
        return reconstructPath(previous, start, end);
      }
      for (String neighbor : graph.get(current)) {
        if (!visited.contains(neighbor)) {
          queue.add(neighbor);
          visited.add(neighbor);
          previous.put(neighbor, current);
        }
      }
    }
    return Collections.emptyList(); // No path found
  }

  private List<String> reconstructPath(Map<String, String> previous, String start, String end) {
    List<String> path = new LinkedList<>();
    for (String at = end; at != null; at = previous.get(at)) {
      path.add(at);
    }
    Collections.reverse(path);
    return path;
  }
}
