/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Product;

/**
 *
 * @author Admin
 */
public interface ProductDao {
    List<Product> getNewProducts();
    List<Product> getAllProducts();
    Product checkProductByName(String productName);
    boolean seachProductById(int id);
    boolean seachProductByName(String productName);
    Product getProductById(int id);
    List<Product> getProductByName(String productName);
    void createProduct();
    void printProduct();
    void searchProduct();
    void printProductFromFile();
    void updateProduct(Product product);
    void deleteProduct(Product product);
    void saveFile();
}
