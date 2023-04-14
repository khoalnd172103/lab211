/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.CD;

/**
 *
 * @author Admin
 */
public class MyToys {

    public static final String filename = "cd.dat";
    public static final Scanner sc = new Scanner(System.in);

    public static int getAnInteger(String input, String error) {
        int result = 0;
        boolean check = true;
        do {
            try {
                System.out.print(input);
                result = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println(error);
            }
        } while (check);
        return result;

    }
    
    public static int getAnInteger2(String input, String error, int min, int max) {
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

    public static double getAnDouble(String input, String error) {
        double result = 0;
        boolean check = true;
        do {
            try {
                System.out.print(input);
                result = Double.parseDouble(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println(error);
            }
        } while (check);
        return result;
    }

    //Convert bool string to boolean
    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    //Tools for inputting data
    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static boolean readBool(String message) {
        String input;
        System.out.print(message + "[1/0-Y/N-T/F]: ");
        input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static List<CD> loadFromFile(String fName, List<CD> list) {
        if (list.size() > 0) {
            list.clear();
        }
        FileInputStream fi = null;
        ObjectInputStream fo = null;
        try {
            fi = new FileInputStream(fName);
            fo = new ObjectInputStream(fi);
            CD obj;
            while (true) {
                obj = (CD) (fo.readObject());
                list.add(obj);
            }
        } catch (Exception e) {
//            System.out.println(e);
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /*Method for reading lines from text file
    Create an array list, named as list
    Open file
    while (still read succesfully a line in the file) {
         trim the line;
         if line is not empty, add line to the list
    }
    Close file
    return list;
    IMPLEMENT IT
     */
    public static List<String> readLinesFromFile(String filename) {
        List<String> list = new ArrayList<String>();
        try {
            FileInputStream fi = new FileInputStream(filename); // read()
            ObjectInputStream fo = new ObjectInputStream(fi); // read Object()
            String b;
            while ((b = (String) (fo.readObject())) != null) {
                list.add(b);
            }
            fo.close();
            fi.close();
        } catch (EOFException e) {
            //end of file reached, do nothing
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //check if the file has the object saved in or not
    public static boolean hasObject(String filePath) {
        FileInputStream fi;
        boolean check = true;
        try {
            fi = new FileInputStream(filePath);
            ObjectInputStream fo = new ObjectInputStream(fi);
            if (fo.readObject() == null) {
                check = false;
            }
            fo.close();
        } catch (FileNotFoundException e) {
            check = false;
        } catch (IOException e) {
            check = false;
        } catch (ClassNotFoundException e) {
            check = false;
            e.printStackTrace();
        }
        return check;
    }

    public static void saveToFile(CD[] list, int numOfItem) {
        FileOutputStream fi = null;
        ObjectOutputStream fo = null;
        try {
            if (!hasObject(filename)) {
                fi = new FileOutputStream(filename);
                fo = new ObjectOutputStream(fi);
            } else {
                fi = new FileOutputStream(filename, true);
                fo = new ObjectOutputStream(fi) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            }
            for (int i = 0; i < numOfItem; i++) {
                fo.writeObject(list[i]);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*Method for writing a list to a text file line-by-line
    Open the file for writing
    For each object in the list, write the object to file
    Close the file
    IMPLEMENT IT
     */
    public static void writeFile(String filename, List list) {
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f); // write()
            PrintWriter pw = new PrintWriter(fw); // println()
            for (Object object : list) {
                pw.print(object);
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
