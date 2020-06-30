package app;



public class Product {
  private int id;
  private String name;
  private double price;
  private double amount;

  public Product(int id, String name, double price, double amount) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.amount = amount;
  }

  public Product() {
    this(-1, "", 0, 0);
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /**
   * @return the amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }
}

