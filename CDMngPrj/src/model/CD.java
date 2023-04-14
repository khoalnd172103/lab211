/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CD implements Serializable, Comparable {

    private String name;
    private String type;
    private String title;
    private double price;
    private int id;
    private int year;

    public CD() {
    }

    public CD(String name, String type, String title, double price, int id, int year) {
        this.name = name;
        this.type = type;
        this.title = title;
        this.price = price;
        this.id = id;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Object o) {
        return this.getTitle().compareTo(((CD) o).getTitle());
    }

    public void showProfile() {
        String str;
        str = String.format("|%4s|%10s|%5s|%5s|%10.1f|%4s|", id, title, name, type, price, year);
        System.out.println(str);
    }

}
