/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import java.util.ArrayList;
import util.MyToys;

/**
 *
 * @author Admin
 */
public class Menu {

    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList();

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public void addOption(String newOption) {
        optionList.add(newOption);
    }

    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("There is no option in the menu");
            return;
        }
        System.out.println("\n--------------------------------------");
        System.out.print(menuTitle);
        System.out.println("\n--------------------------------------");
        for (String string : optionList) {
            System.out.println(string);
        }
    }

    public int maxOption() {
        return optionList.size();
    }

    public int getChoice() {
        int maxOption = optionList.size();
        String input = "Choose[1.." + maxOption + "]: ";
        String error = "You are require to choose the option from 1 to " + maxOption;
        return MyToys.getAnInteger2(input, error, 1, maxOption);
    }

}
