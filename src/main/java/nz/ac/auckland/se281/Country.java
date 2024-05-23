package nz.ac.auckland.se281;

public class Country {
  private String name;
  private String continent;
  private int taxFees;

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

  public double getTaxFees() {
    return taxFees;
  }

  @Override
  public String toString() {
    return String.format(MessageCli.COUNTRY_INFO.toString(), name, continent, taxFees);
  }
}
