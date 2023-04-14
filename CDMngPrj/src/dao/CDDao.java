/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.CD;

/**
 *
 * @author Admin
 */
public interface CDDao {
    void getNewCDs();
    List<CD> getAllCDs();
    boolean searchCDByTitle(String CDTitle);
    boolean searchCDByID(int id);
    CD getCDById(int id);
    List<CD> getCDByTitle(String CDTitle);
    void createCD();
    void printCD();
    void searchCD();
    void printCDFromFile();
    void updateCD(CD cd);
    void deleteCD(CD cd);
    void saveFile();
}
