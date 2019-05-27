/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCModels;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList L1 = new ArrayList();
        String[] s = {"twenty", "ten", "eleven", "one", "zero", "fifty"};
        String[] s2 = {"nty", "en", "ven", "e", "ero", "fif"};
        String[] StrArr;

        L1.add(s);
        L1.add(s2);
        StrArr = (String[]) L1.get(1);

        for (int i = 0; i < StrArr.length; i++) {
            System.out.println(StrArr[i]);
        }

    }

}
