/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /* Class for a menu */
package mng;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Menu extends ArrayList<String> {

    public Menu() {
        super();
    }

    public Menu(String[] items) {
        super();
        for (String item : items) {
            this.add(item);
        }
    }

    public int getChoice(String title) {
        int result = 0;
        System.out.println(title);
        for (int i = 0; i < this.size(); i++) {
            System.out.println(i + 1 + "-" + this.get(i));
        }
        System.out.println("Other for quit");
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Choose {1..8}: ");
            result = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
        }
        return result;
    }
}
