/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import dao.ProductList;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import util.MyToys;

/**
 *
 * @author Admin
 */
public class ProductSystem {

    public static void main(String[] args) {
        Menu menu = new Menu("Welcome To Product Management System");
        menu.addOption("1-Print");
        menu.addOption("2-Create a product");
        menu.addOption("3-Check exits product");
        menu.addOption("4-Search product information by name");
        menu.addOption("5-Update product");
        menu.addOption("6-Save products to file");
        menu.addOption("7-Print list products from the file");
        menu.addOption("8-Quit");

        int choice = 0, id = 0;
        String response, name;
        Product product;
        boolean changed = false;
        ProductList productList = new ProductList();
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    productList.printProduct();
                    break;
                case 2:
                    System.out.println("\n--------------------------------------");
                    System.out.println("You are preparing to input a new product profile");
                    productList.createProduct();
                    changed = true;
                    break;
                case 3:
                    System.out.println("\n--------------------------------------");
                    System.out.println("You are required to input a product name to check exist");
                    name = MyToys.readNonBlank("Enter name of the product: ");
                    product = new ProductList().checkProductByName(name);
                    if (product != null) {
                        System.out.println("Exist Product!");
                    } else {
                        System.out.println("No Product Found!");
                    }
                    break;
                case 4:
                    System.out.println("\n--------------------------------------");
                    System.out.println("You are required to input a part of product name to search");
                    productList.searchProduct();
                    break;
                case 5:
                    productList.updateAndDelete();
                    break;
                case 6:
                    productList.saveFile();
                    changed = true;
                    break;
                case 7:
                    productList.printProductFromFile();
                    break;
                case 8:
                    if (changed) {
                        response = MyToys.readNonBlank("Save change (Y/N): ");
                        if (response.startsWith("y")) {
                            productList.saveFile();
                        }
                    }
                    System.out.println("Bye bye, see you next time!");
                    break;
            }
        } while (choice > 0 && choice < menu.maxOption());
    }
}
