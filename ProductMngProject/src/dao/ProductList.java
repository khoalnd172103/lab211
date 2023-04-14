/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import mng.Menu;
import model.Product;
import util.MyToys;
import static util.MyToys.sc;

/**
 *
 * @author Admin
 */
public class ProductList implements ProductDao {

    private static final String NAME_FORMAT = "\\S{5,}";
    public static final String ID_FORMAT = "D\\d{3}";
    private List<Product> products;
    String filename = "product.txt";

    public ProductList() {
        products = new ArrayList<>();
    }

    //done
    @Override
    public List<Product> getNewProducts() {
        if (products.isEmpty()) {
            System.out.println("Empty List.");
        }
        return products;
    }

    //done
    @Override
    public List<Product> getAllProducts() {
        products = MyToys.loadFromFile(filename);
        Collections.sort(products);
        return products;
    }

    @Override
    public Product checkProductByName(String productName) {
        for (Product products : new ProductList().getAllProducts()) {
            if (products.getName().equalsIgnoreCase(productName)) {
                return products;
            }
        }
        return null;
    }

    //done
    @Override
    public boolean seachProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
    }

    //done
    @Override
    public boolean seachProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    //done
    @Override
    public Product getProductById(int id) {
        //products = MyToys.loadFromFile(filename);
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    //done
    @Override
    public List<Product> getProductByName(String productName) {
        //products = MyToys.loadFromFile(filename);
        List<Product> tmp = new ArrayList<>();
        tmp = MyToys.loadFromFile(filename);
        List<Product> list = new ArrayList<>();
        for (Product product : tmp) {
            if (product.getName().contains(productName)) {
                list.add(product);
            }
        }
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    //done
    @Override
    public void createProduct() {
        String name, status;
        int quantity, id, checkStatus;
        double price;
        boolean checkID = true;
        boolean checkName = true;

        do {
            name = MyToys.readPattern("Input product name: ", NAME_FORMAT);
            name = name.toUpperCase();
            if (seachProductByName(name) || checkProductByName(name) != null) {
                System.out.println("Product name is dupplicated!");
            } else {
                checkName = false;
            }
        } while (checkName);
        do {
            id = MyToys.getAnInteger("Input product ID: ", "ID is required", 0, 100);
            if (seachProductById(id)) {
                System.out.println("Product ID is dupplicated!");
            } else {
                checkID = false;
            }
        } while (checkID);
        price = MyToys.getAnDouble("Input product unit price: ", "**Unit price from 0 to 10000**", 0, 10000);
        quantity = MyToys.getAnInteger("Input product quantity: ", "**Quantity from 0 to 10000**", 0, 10000);
        checkStatus = MyToys.getAnInteger("Input product status(1:Available/0:Not Available): ", "**(1:Available/0:Not Available)**", 0, 1);
        if (checkStatus == 1) {
            status = "Available";
        } else {
            status = "Not Available";
        }
        Product product = new Product(id, name, price, quantity, status);
        products.add(product);
        System.out.println("New product has been added.");
    }

    //done
    @Override
    public void searchProduct() {
        String name = MyToys.readNonBlank("Input product name: ").toUpperCase();
        List<Product> list = new ArrayList<>();
        list = new ProductList().getProductByName(name);
        if (list != null) {
            Comparator<Product> compareByName = (Product p1, Product p2) -> p1.getName().compareTo(p2.getName());
            Collections.sort(list, compareByName);
            for (Product product : list) {
                System.out.println(product.getId() + "|" + product.getName() + "|" + product.getPrice()
                        + "|" + product.getQuantity() + "|" + product.getStatus() + "|");
            }
        } else {
            System.out.println("No Product Found!");
        }
    }

    //done
    @Override
    public void printProductFromFile() {
        if (new ProductList().getAllProducts().isEmpty()) {
            System.out.println("There is no item in product file");
        }
        //Collections.sort(products);
        System.out.println("\n--------------------------------------");
        System.out.println("The product list in file");
        String header = String.format("|%4s|%10s|%10s|%8s|%13s|", "ID", "NAME", "UNIT PRICE", "QUANTITY", "STATUS");
        System.out.println(header);
        for (Product products : new ProductList().getAllProducts()) {
            products.showProfile();
        }

    }

    //done
    @Override
    public void printProduct() {
        if (products.isEmpty()) {
            System.out.println("There is no item in product list");
            return;
        }
        System.out.println("\n--------------------------------------");
        System.out.println("The product list");
        String header = String.format("|%4s|%10s|%10s|%8s|%13s|", "ID", "NAME", "UNIT PRICE", "QUANTITY", "STATUS");
        System.out.println(header);
        for (int i = 0; i < products.size(); i++) {
            products.get(i).showProfile();
        }
    }

    //done
    public void updateAndDelete() {
        String response = null, choice = null;
        int id = 0;
        boolean quit = false, changed = false;
        Menu subMenu = new Menu("Choose your option");
        subMenu.addOption("5.1-Update product information");
        subMenu.addOption("5.2-Delete product information");
        do {
//            Menu subMenu = new Menu("Choose your option");
//            subMenu.addOption("1-Update product information");
//            subMenu.addOption("2-Delete product information");
//            subMenu.addOption("3-Quit");
            subMenu.printMenu();
            choice = subMenu.getChoice2();
            switch (choice) {
                case "5.1":
                    id = MyToys.getAnInteger("Input product ID: ", "", 0, 100);
                    if (getProductById(id) != null) {
                        updateProduct(getProductById(id));
                    } else {
                        System.out.println("No Product Found!");
                    }
                    response = MyToys.readNonBlank("Do you want to continue with other products(Y/N)?: ").toUpperCase();
                    if (response.startsWith("N")) {
                        quit = true;
                    }
                    changed = true;
                    break;
                case "5.2":
                    id = MyToys.getAnInteger("Input product ID: ", "", 0, 100);
                    if (getProductById(id) != null) {
                        deleteProduct(getProductById(id));;
                    } else {
                        System.out.println("No Product Found!");
                    }
                    response = MyToys.readNonBlank("Do you want to continue(Y/N)?: ").toUpperCase();
                    if (response.startsWith("N")) {
                        quit = true;
                    }
                    changed = true;
                    break;
                default:
                    quit = true;
            }
        } while (!quit);
    }

    //done
    @Override
    public void updateProduct(Product product) {
        String name, status, response = null;
        double price;
        int quantity, checkStatus, choice = 0;
        boolean change = false, quit = false, checkName = true;
        Menu subMenu = new Menu("Choose your option");
        subMenu.addOption("1-Update product name");
        subMenu.addOption("2-Update product unit price");
        subMenu.addOption("3-Update product quantity");
        subMenu.addOption("4-Update product status");
        subMenu.addOption("5-Stop update this product");
        do {
            subMenu.printMenu();
            System.out.println("Product is selected:");
            product.showProfile();
            choice = subMenu.getChoice();
            switch (choice) {
                case 1:
                    do {
                        name = MyToys.readPattern("Input new name: ", NAME_FORMAT);
                        name = name.toUpperCase();
                        if (seachProductByName(name) || checkProductByName(name) != null) {
                            System.out.println("Product name is dupplicated!");
                        } else {
                            product.setName(name);
                            System.out.println("Updated");
                            checkName = false;
                        }
                    } while (checkName);
                    break;
                case 2:
                    price = MyToys.getAnDouble("Input new unit price: ", "**Unit price from 0 to 10000**", 0, 10000);
                    product.setPrice(price);
                    System.out.println("Updated");
                    break;
                case 3:
                    quantity = MyToys.getAnInteger("Input new quantity: ", "**Qunantity from 0 to 10000**", 0, 10000);
                    product.setQuantity(quantity);
                    System.out.println("Updated");
                    break;
                case 4:
                    checkStatus = MyToys.getAnInteger("Input new status(1:Available/0:Not Available): ", "**(1:Available/0:Not Available)**", 0, 1);
                    if (checkStatus == 1) {
                        status = "Available";
                    } else {
                        status = "Not Available";
                    }
                    product.setStatus(status);
                    System.out.println("Updated");
                    break;
                case 5:
                    quit = true;
                    break;
            }
        } while (choice > 0 && choice < subMenu.maxOption());
    }

    //done
    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
        System.out.println("Product " + product.getId() + " is deleted!");
    }

    //done
    @Override
    public void saveFile() {
        if (!products.isEmpty()) {
            MyToys.saveToFile(products);
            products.clear();
            System.out.println("New product(s) has been saved to the file.");
        } else {
            System.out.println("Nothing to save");
        }
    }
}
