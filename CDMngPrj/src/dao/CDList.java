/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mng.Menu;
import model.CD;
import util.MyToys;

/**
 *
 * @author Admin
 */
public class CDList implements CDDao {

    private List<CD> cd;
    private CD[] list;
    private int numOfCD;
    private final int MAX = 2;
    String filename = "cd.dat";

    public int getNumOfCD() {
        return numOfCD;
    }

    public CDList() {
        cd = new ArrayList<>();
        list = new CD[MAX];
        numOfCD = 0;
    }

    //done
    @Override
    public void getNewCDs() {
        if (numOfCD == 0) {
            System.out.println("Empty List.");
        }
        for (int i = 0; i < numOfCD; i++) {
            list[i].showProfile();
        }
    }

    //done
    @Override
    public List<CD> getAllCDs() {
        cd = MyToys.loadFromFile(filename, cd);
//        if (cd.isEmpty()) {
//            System.out.println("Empty File.");
//        }
        Collections.sort(cd);
        return cd;
    }

    //done
    @Override
    public boolean searchCDByTitle(String CDTitle) {
        for (CD x : cd) {
            if (x.getTitle().equalsIgnoreCase(CDTitle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean searchCDByID(int id) {
//        for (CD x : list) {
//            if (x.getId() == id) {
//                return true;
//            }
//        }
        for (int i = 0; i < numOfCD; i++) {
            if (list[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CD getCDById(int id) {
//        for (CD x : cd) {
//            if (x.getId() == id) {
//                return x;
//            }
//        }
        for (int i = 0; i < numOfCD; i++) {
            if (list[i].getId() == id) {
                return list[i];
            }
        }
        return null;
    }

    @Override
    public List<CD> getCDByTitle(String CDTitle) {
        cd = MyToys.loadFromFile(filename, cd);
        if (cd.isEmpty()) {
            System.out.println("Empty File.");
        }
        List<CD> list = new ArrayList<>();
        for (CD x : cd) {
            if (x != null && x.getTitle().contains(CDTitle)) {
                list.add(x);
            }
        }
        return list;
    }

    //done
    @Override
    public void createCD() {
        String name, title, type;
        int id, year;
        double price;
        boolean checkID = true, checkName = true, checkType = true;
        if (numOfCD >= MAX) {
            System.out.println("List is full, cannot add CD.");
        } else {
            do {
                id = MyToys.getAnInteger("Input product ID: ", "ID is required and ID is an integer");
                if (searchCDByID(id)) {
                    System.out.println("CD's ID is dupplicated!");
                } else {
                    checkID = false;
                }
            } while (checkID);
            title = MyToys.readNonBlank("Input CD title: ").toUpperCase();
            do {
                name = MyToys.readNonBlank("Input CD name(game/movie/music): ").toUpperCase();
                if (name.equalsIgnoreCase("game") || name.equalsIgnoreCase("movie") || name.equalsIgnoreCase("music")) {
                    checkName = false;
                } else {
                    System.out.println("**CD collection name(game/movie/music)**");
                }
            } while (checkName);
            do {
                type = MyToys.readNonBlank("Input CD type(audio/video): ").toUpperCase();
                if (type.equalsIgnoreCase("audio") || type.equalsIgnoreCase("video")) {
                    checkType = false;
                } else {
                    System.out.println("**CD type(audio/video)**");
                }
            } while (checkType);
            price = MyToys.getAnDouble("Input CD price: ", "**Price is required**");
            year = MyToys.getAnInteger("Input CD publishing year: ", "**Year is required**");
            list[numOfCD] = new CD(name, type, title, price, id, year);
            numOfCD++;
            System.out.println("New product has been added.");
        }

    }

    //id + title + name + type + price + year
    //done
    @Override
    public void printCD() {
        if (numOfCD == 0) {
            System.out.println("There is no item in CD list");
            return;
        }
        System.out.println("\n--------------------------------------");
        System.out.println("The CD list");
        String header = String.format("|%4s|%10s|%5s|%5s|%10s|%4s|", "ID", "Title", "Name", "Type", "Unit Price", "Year");
        System.out.println(header);
        for (int i = 0; i < numOfCD; i++) {
            list[i].showProfile();
        }
    }

    //done
    @Override
    public void searchCD() {
        String title = MyToys.readNonBlank("Input CD title: ").toUpperCase();
        List<CD> list = new ArrayList<>();
        list = getCDByTitle(title);
        if (list.isEmpty()) {
            System.out.println("No CD Found!");
        } else {
            for (CD x : list) {
                System.out.println(x.getId() + "|" + x.getTitle() + "|" + x.getName()
                        + "|" + x.getType() + "|" + x.getPrice() + "|" + x.getYear() + "|");
            }
        }
    }

    //done
    @Override
    public void printCDFromFile() {
        if (getAllCDs().isEmpty()) {
            System.out.println("There is nothing in cd file");
        } else {
            //Collections.sort(products);
            System.out.println("\n--------------------------------------");
            System.out.println("The product list in file");
            String header = String.format("|%4s|%10s|%5s|%5s|%10s|%4s|", "ID", "Title", "Name", "Type", "Unit Price", "Year");
            System.out.println(header);
            for (CD x : new CDList().getAllCDs()) {
                x.showProfile();
            }
        }
    }

    //done
    public void updateAndDelete() {
        String response = null;
        int id = 0, choice = 0;
        boolean quit = false, changed = false;
        Menu subMenu = new Menu("Choose your option");
        subMenu.addOption("1-Update CD information");
        subMenu.addOption("2-Delete CD information");
        subMenu.addOption("3-Quit");
        do {
            subMenu.printMenu();
            choice = subMenu.getChoice();
            switch (choice) {
                case 1:
                    id = MyToys.getAnInteger("Input product ID: ", "ID is required and ID is an integer");
                    if (searchCDByID(id)) {
                        updateCD(getCDById(id));
                    } else {
                        System.out.println("No Product Found!");
                    }
                    response = MyToys.readNonBlank("Do you want to continue with other CDs(Y/N)?: ").toUpperCase();
                    if (response.startsWith("N")) {
                        quit = true;
                    }
                    changed = true;
                    break;
                case 2:
                    id = MyToys.getAnInteger("Input product ID: ", "ID is required and ID is an integer");
                    if (searchCDByID(id)) {
                        deleteCD(getCDById(id));;
                    } else {
                        System.out.println("No Product Found!");
                    }
                    response = MyToys.readNonBlank("Do you want to continue with other CDs(Y/N)?: ").toUpperCase();
                    if (response.startsWith("N")) {
                        quit = true;
                    }
                    changed = true;
                    break;
                case 3:
                    quit = true;
                    break;
            }
        } while (!quit);
    }

    //id + title + name + type + price + year
    //done
    @Override
    public void updateCD(CD cd) {
        String name, title, type;
        double price;
        int year, choice = 0;
        boolean quit = false, checkName = true, checkType = true;
        Menu subMenu = new Menu("Choose your option");
        subMenu.addOption("1-Update CD title");
        subMenu.addOption("2-Update CD name");
        subMenu.addOption("3-Update CD type");
        subMenu.addOption("4-Update CD price");
        subMenu.addOption("5-Update CD year");
        subMenu.addOption("6-Stop update this CD");
        do {
            subMenu.printMenu();
            System.out.println("\n--------------------------------------");
            System.out.println("CD is selected: ");
            String header = String.format("|%4s|%10s|%5s|%5s|%10s|%4s|", "ID", "Title", "Name", "Type", "Unit Price", "Year");
            System.out.println(header);
            cd.showProfile();
            choice = subMenu.getChoice();
            switch (choice) {
                case 1:
                    title = MyToys.readNonBlank("Input new CD title: ").toUpperCase();
                    cd.setTitle(title);
                    System.out.println("Updated");
                    break;
                case 2:
                    do {
                        name = MyToys.readNonBlank("Input CD name(game/movie/music): ").toUpperCase();
                        if (name.equalsIgnoreCase("game") || name.equalsIgnoreCase("movie") || name.equalsIgnoreCase("music")) {
                            cd.setName(name);
                            checkName = false;
                            System.out.println("Updated");
                        } else {
                            System.out.println("**CD collection name(game/movie/music)**");
                        }
                    } while (checkName);
                    break;
                case 3:
                    do {
                        type = MyToys.readNonBlank("Input CD type(audio/video): ").toUpperCase();
                        if (type.equalsIgnoreCase("audio") || type.equalsIgnoreCase("video")) {
                            cd.setType(type);
                            checkType = false;
                            System.out.println("Updated");
                        } else {
                            System.out.println("**CD type(audio/video)**");
                        }
                    } while (checkType);
                    break;
                case 4:
                    price = MyToys.getAnDouble("Input new CD price: ", "**Price is required**");
                    cd.setPrice(price);
                    System.out.println("Updated");
                    break;
                case 5:
                    year = MyToys.getAnInteger("Input new publishing year: ", "**Year is required**");
                    cd.setYear(year);
                    System.out.println("Updated");
                    break;
                case 6:
                    quit = true;
                    break;
            }
        } while (choice > 0 && choice < subMenu.maxOption());

    }

    //done
    public int searchByID(CD cd) {
        for (int i = 0; i < numOfCD; i++) {
            if (list[i] == cd) {
                return i;
            }
        }
        return -1;
    }

    //done
    @Override
    public void deleteCD(CD cd) {
        int pos = searchByID(cd);
        if (pos >= 0 && pos < numOfCD) {
            for (int i = pos; i < (numOfCD - 1); i++) {
                list[i] = list[i + 1];
            }
            numOfCD--;
            System.out.println("Product " + cd.getId() + " is deleted!");
        }
    }

    //done
    @Override
    public void saveFile() {
        if (numOfCD != 0) {
            MyToys.saveToFile(list, numOfCD);
            numOfCD = 0;
            System.out.println("New product(s) has been saved to the file.");
        } else {
            System.out.println("Nothing to save");
        }
    }

}
