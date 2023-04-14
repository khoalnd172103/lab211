/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /* Class for Log In interface */
package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import tools.MyTool;

/**
 *
 * @author Admin
 */
public class LogIn {

    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
        System.out.println("Login to The Dealer System");
        String name = MyTool.readNonBlank("Your account name: ");
        String pass = MyTool.readNonBlank("Your password: ");
        String role = MyTool.readNonBlank("Your role: ");
        return new Account(name, pass, role);
    }

    public Account getAcc() {
        return this.acc;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Account acc = null;
        boolean cont;
        boolean valid;
        do {
            AccountChecker accChk = new AccountChecker();
            cont = false;
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = MyTool.readBool("Invalid account-Try again ??");
            }
            if (!valid && !cont) {
                System.exit(0);
            }
        } while (cont);
        LogIn logInObj = new LogIn(acc);
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            String[] options = {"Add new dealer", "Search a dealer",
                "Remove a dealer", "Update a dealer",
                "Print all dealers", "Print continuing dealers",
                "Print UN-continuing dealers", "Write to file"

            };
            Menu mnu = new Menu(options);
            DealerList dList = new DealerList(logInObj);
            dList.initWithFile();
            int choice = 0;
            do {
                System.out.println("------------------------------------");
                choice = mnu.getChoice("Managing dealers");
                switch (choice) {
                    case 1:
                        dList.addDealer();
                        break;
                    case 2:
                        dList.searchDealer();
                        break;
                    case 3:
                        dList.removeDealer();
                        break;
                    case 4:
                        dList.updateDealer();
                        break;
                    case 5:
                        dList.printAllDealers();
                        break;
                    case 6:
                        dList.printContinuingDealers();
                        break;
                    case 7:
                        dList.printUnContinuingDealers();
                        break;
                    case 8:
                        dList.writeDealerToFile();
                        break;
                    default:
                        if (dList.isChanged()) {
                            boolean res = MyTool.readBool("Data changed. Write to file?");
                            if (res == true) {
                                dList.writeDealerToFile();
                            }
                        }
                }
            } while (choice > 0 && choice < mnu.size());
            System.out.println("Bye.");
        }
    }

}
