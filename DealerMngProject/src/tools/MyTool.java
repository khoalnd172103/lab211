/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class MyTool {

    public static final Scanner SC = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*") // AT LEAST 1 CHARACTER
                && str.matches(".*[\\d]+.*") // AT LEAST 1 DIGIT
                && str.matches(".*[\\W]+.*"); // AT LEAST 1 SPECIAL CHAR

    }

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String dataToStr(Date date, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        return dF.format(date);
    }

    // Convert bool string to boolean
    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    // Tools for inputting data
    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message);
            input = SC.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message);
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    public static boolean readBool(String message) {
        String input;
        System.out.print(message + "[1/0-Y/N-T/F]: ");
        input = SC.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static List<String> readLinesFromFile(String filename) {
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    list.add(line);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void writeFile(String filename, List list) {
        try {
            FileWriter fw = new FileWriter(filename, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Object o : list) {
                bw.write(o.toString());
                //bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    //TEST
    public static void main(String[] args) {
        System.out.println("Tests th phone numbers:");
        System.out.println(validStr("012345678", "\\d{9}|\\d{11}"));
        System.out.println(validStr("01234567891", "\\d{9}|\\d{11}"));
        System.out.println(validStr("12345678", "\\d{9}|\\d{11}"));

        System.out.println(validPassword("qwerty", 8));
        System.out.println(validPassword("qwertyABC", 8));
        System.out.println(validPassword("12345678", 8));
        System.out.println(validPassword("qbc123456", 8));
        System.out.println(validPassword("qbc@123456", 8));

        System.out.println("Tests with IDs:");
        System.out.println(validStr("A0001", "D\\d{3}"));
        System.out.println(validStr("10001", "D\\d{3}"));
        System.out.println(validStr("D0001", "D\\d{3}"));
        System.out.println(validStr("D101", "D\\d{3}"));

        Date d = parseDate("2022:12:07", "yyyy:MM:dd");
        System.out.println(d);
        System.out.println(dataToStr(d, "dd/MM/yyyy"));
        d = parseDate("12/07/2022", "MM/dd/yyyy");
        System.out.println(d);
        d = parseDate("2022/07/12", "yyyy/dd/MM");
        System.out.println(d);
        d = parseDate("2000/29/02", "yyyy/dd/MM");
        System.out.println(d);
        d = parseDate("2000/30/02", "yyyy/dd/MM");
        System.out.println(d);
        d = parseDate("2000/40/16", "yyyy/dd/MM");
        System.out.println(d);
        String input = readNonBlank("Input a non-blank string");
        System.out.println(input); 
        input = readPattern("Phone 9/11 digits", "\\d{9}|\\d{11}");
        System.out.println(input); 
        input = readPattern("ID- format X00000", "X\\d{5}");
        System.out.println(input); 
        boolean b = readBool("Input boolean");
        System.out.println(b); 
    }

}
