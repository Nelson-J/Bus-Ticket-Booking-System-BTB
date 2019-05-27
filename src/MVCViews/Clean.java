/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCViews;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *This class has been created to "clean" User input and to clear JTextfields in a panel
 * @author user
 */
public class Clean {
    
    public Clean(){
    }
    
    //Clears all textboxes on a view, when called
    public void resetTextFields(JPanel panel){
        JTextField temp = null;
        
        for(Component c : panel.getComponents()){
            
             if(c.getClass().toString().contains("javax.swing.JTextField")){
                 
                temp = (JTextField)c;
                temp.setText("");
             }
        }
    }
}
