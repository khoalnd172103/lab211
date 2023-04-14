/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Product implements Comparable<Product> {

    public static final String NAME_FORMAT = "\\S{5,}";
    public static final char SEPARATOR = '|';

    private int id;
    private String name;
    private double price;
    private int quantity;
    private String status;

    public Product(int id, String name, double price, int quantity, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Product p) {
        if (this.quantity == p.quantity) {
            return (int) (this.price - p.price);
        }
        return p.quantity - this.quantity;
        //return this.getName().compareToIgnoreCase(p.getName());
    }

    public void showProfile() {
        String str;
        str = String.format("|%4s|%10s|%10.1f|%8d|%13s|", id, name, price, quantity, status);
        System.out.println(str);
    }

}
