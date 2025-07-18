package nz.ac.auckland.se281;

/** Represents a country with its name, continent, and tax fees. */
public class Country {
  private String name;
  private String continent;
  private int taxFees;

  /**
   * Creates a new country object.
   *
   * @param name country name.
   * @param continent continent name.
   * @param taxFees tax fees.
   */
  public Country(String name, String continent, int taxFees) {
    this.name = name;
    this.continent = continent;
    this.taxFees = taxFees;
  }

  public String getName() {
    return name;
  }

  public String getContinent() {
    return continent;
  }

  public int getTaxFees() {
    return taxFees;
  }
}
