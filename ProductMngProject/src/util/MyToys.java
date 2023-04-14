/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import model.Product;

/**
 *
 * @author Admin
 */
public class MyToys {
    
    public static final String filename = "product.txt";
    public static Scanner sc = new Scanner(System.in);

    public static int getAnInteger(String input, String error, int min, int max) {
        int result = 0;
        boolean check = true;
        do {
            try {
                System.out.print(input);
                result = Integer.parseInt(sc.nextLine());
                if (result < min || result > max) {
                    System.out.println(error);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println(error);
            }
        } while (check || result > max || result < min);
        return result;

    }

    public static double getAnDouble(String input, String error, int min, int max) {
        double result = 0;
        boolean check = true;
        do {
            try {
                System.out.print(input);
                result = Double.parseDouble(sc.nextLine());
                if (result < min || result > max) {
                    System.out.println(error);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println(error);
            }
        } while (check || result > max || result < min);
        return result;
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static void writeFile(String filename, List list) {
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f); // write()
            PrintWriter pw = new PrintWriter(fw); // println()
            for (Object object : list) {
                pw.print(object);
            }
            pw.close(); fw.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public static List<String> readLinesFromFile (String filename) {
        List<String> list = new ArrayList<String>();
        try {
            FileInputStream fi = new FileInputStream(filename); // read()
            ObjectInputStream fo = new ObjectInputStream(fi); // read Object()
            String b;
            while ((b=(String)(fo.readObject())) != null) {
                list.add(b);
            }
            fo.close(); fi.close();
        } catch(EOFException e) {
            //end of file reached, do nothing
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static boolean readBool(String message) {
        String input;
        System.out.print(message);
        input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }
    
    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {            
            System.out.print(message);
            input = sc.nextLine().trim();
            valid = validStr(input,pattern);
        } while (!valid);
        return input;
    }
    
    public static boolean validStr (String str, String regEx) {
        boolean input = false;
        input = str.matches(regEx);
        return input;
    }
    
    public static void saveToFile(List<Product> list) {
        if(list.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f,true);
            PrintWriter pw = new PrintWriter(fw);
            for (Product product : list) {
                pw.println(product.getId()+ "|" + product.getName() + "|"+ product.getPrice()
                            + "|" + product.getQuantity() + "|" + product.getStatus() + "|");
            }
            pw.close(); fw.close(); 
        }
        catch(Exception e) {
            System.out.println(e);
        }
    } 
    
        public static List<Product> loadFromFile(String fName) {
        List<Product> list = new ArrayList<Product>();
        try {
            FileReader fr = new FileReader(fName);
            BufferedReader bf = new BufferedReader(fr);
            String details; 
            while((details=bf.readLine())!=null) {
                //splitting details into elements
                StringTokenizer stk = new StringTokenizer(details,"|:");
                int id = Integer.parseInt(stk.nextToken());
                String name = stk.nextToken().toUpperCase();
                double price = Double.parseDouble(stk.nextToken());
                int quantity = Integer.parseInt(stk.nextToken());
                String status = stk.nextToken().toUpperCase();
                Product product = new Product(id, name, price, quantity, status);
                list.add(product);
            }
            bf.close(); fr.close();
        } catch (Exception e) {
//            System.out.println(e);
        }
        return list;
    }
}
