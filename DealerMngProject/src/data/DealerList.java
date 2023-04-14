/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /* Class for a list of dealers */
package data;

import java.util.List;
import java.util.ArrayList;
import tools.MyTool;
import mng.LogIn;

/**
 *
 * @author Admin
 */
public class DealerList extends ArrayList<Dealer> {

    LogIn loginObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false;

    public DealerList(LogIn logInObj) {
        super();
        this.loginObj = logInObj;
    }

    private void loadDealerFromFile() {
        List<String> lines = MyTool.readLinesFromFile(dataFile);
        for (String line : lines) {
            Dealer dealer = new Dealer(line);
            this.add(dealer);
        }
    }

    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }

    public DealerList getContinuingList() {
        DealerList dList = new DealerList(loginObj);
        for (Dealer dealer : this) {
            if (dealer.isContinuing()) {
                dList.add(dealer);
            }
        }
        return dList;
    }

    public DealerList getUnContinuingList() {
        DealerList dList = new DealerList(loginObj);
        for (Dealer dealer : this) {
            if (!dealer.isContinuing()) {
                dList.add(dealer);
            }
        }
        return dList;
    }

    private int searchDealer(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().toUpperCase().equals(ID.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealer() {
        String ID = MyTool.readNonBlank("Input ID for searching: ");
        int pos = this.searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " not found!");
        } else {
            System.out.println(this.get(pos));
        }
    }
    
    public void addDealer() {
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        int pos;
        do {
            ID = MyTool.readPattern("ID of new dealer: ", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new dealer: ").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer: ");
        phone = MyTool.readPattern("Phone number: ", Dealer.PHONE_FORMAT);
        continuing = true; 
        Dealer d = new Dealer(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("New dealer has been added.");
        changed = true;
    }

    public void removeDealer() {
        String ID = MyTool.readNonBlank("Input ID for removing: ");
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " not found!");
        } else {
            this.get(pos).setContinuing(false);
            this.remove(pos);
            System.out.println("Removed");
            changed = true;
        }
    }

    public void updateDealer() {
        String ID = "";
        ID = MyTool.readNonBlank("Input ID for updating: ");
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " not found!");
        } else {
            Dealer d = this.get(pos);
            String newName = "";
            System.out.print("New name, ENTER for omitting: ");
            newName = MyTool.SC.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName((newName));
                changed = true;
            }
            String newAddr = "";
            System.out.print("New address, ENTER for omitting: ");
            newAddr = MyTool.SC.nextLine().trim();
            if (!newAddr.isEmpty()) {
                d.setAddr((newAddr));
                changed = true;
            }
            String newPhone = "";
            do {
                System.out.print("New phone, ENTER for omitting: ");
                newPhone = MyTool.SC.nextLine().trim();
                if (newPhone.isEmpty()) {
                    break;
                }
            } while (!MyTool.validStr(newPhone, PHONEPATTERN));
            if (!newPhone.isEmpty()) {
                d.setPhone((newPhone));
                changed = true;
            }
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            System.out.println(this);
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public void printUnContinuingDealers() {
        this.getUnContinuingList().printAllDealers();
    }

    // Write dealer list to file
    public void writeDealerToFile() {
        if (changed) {
            MyTool.writeFile(dataFile, this);
            changed = false;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
