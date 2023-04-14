/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import dao.CDList;
import java.util.ArrayList;
import java.util.List;
import model.CD;
import util.MyToys;

/**
 *
 * @author Admin
 */
public class CDSystem {

    public static void main(String[] args) {
        Menu menu = new Menu("Welcome To CD House Program");
        menu.addOption("1-Add CD to the datalog");
        menu.addOption("2-Search CD by CD title");
        menu.addOption("3-Display the catalog");
        menu.addOption("4-Update CD");
        menu.addOption("5-Save CD to file");
        menu.addOption("6-Print list CDs from file");
        menu.addOption("7-Quit");

        int choice = 0;
        String response;
        CD cd;
        boolean changed = false;
        CDList cdList = new CDList();
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    System.out.println("\n--------------------------------------");
                    System.out.println("You are preparing to input a new CD profile");
                    cdList.createCD();
                    changed = true;
                    break;
                case 2:
                    //search cd in file
                    System.out.println("\n--------------------------------------");
                    System.out.println("You are required to input a CD title to search");
                    cdList.searchCD();
                    break;
                case 3:
                    cdList.printCD();
                    break;
                case 4:
                    cdList.updateAndDelete();
                    break;
                case 5:
                    cdList.saveFile();
                    changed = false;
                    break;
                case 6:
                    cdList.printCDFromFile();
                    break;
                case 7:
                    if (changed && cdList.getNumOfCD() != 0) {
                        response = MyToys.readNonBlank("Save change (Y/N): ");
                        if (response.startsWith("y")) {
                            cdList.saveFile();
                        }
                    }
                    System.out.println("Bye bye, see you next time!");
                    break;
            }
        } while (choice > 0 && choice < menu.maxOption());
    }
}
