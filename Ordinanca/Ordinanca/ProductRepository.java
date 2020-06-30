package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.Product;

public class ProductRepository {
  private static ArrayList<Product> db = new ArrayList<>(List.of(new Product(1, "Product 1", 10, 6),
      new Product(2, "Product 2", 14, 2), new Product(3, "Product 3", 25, 6), new Product(4, "Product 4", 0.99, 10),
      new Product(5, "Product 5", 0.35, 22), new Product(6, "Product 6", 5.25, 7), new Product(7, "Product 7", 10, 8.5),
      new Product(8, "Product 8", 5, 9), new Product(9, "Product 9", 7.33, 13),
      new Product(10, "Product 10", 10.25, 26)));

  private int genNewId() {
    int lastIndex = db.size() - 1;
    if (lastIndex < 0)
      return 1;
    return db.get(lastIndex).getId() + 1;
  }

  public List<Product> getAll() {
    return Collections.unmodifiableList(db);
  }

  public Product find(int id) {
    for (Product product : db) {
      if (product.getId() == id)
        return product;
    }
    return null;
  }

  public int create(Product product) {
    int id = genNewId();
    Product internal = new Product(id, product.getName(), product.getPrice(), product.getAmount());
    db.add(internal);
    return id;
  }

  public void update(Product product) {
    Product internal = find(product.getId());
    if (internal == null) {
      create(product);
      return;
    }

    internal.setName(product.getName());
    internal.setPrice(product.getPrice());
    internal.setAmount(product.getAmount());
  }

  public void remove(int id) {
    Product product = find(id);
    if (product != null) {
      db.remove(product);
    }
  }
}

