/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCModels;

/**
 *
 * @author user
 */
public class Client {
    private int clientIDCardNumber;
    private String clientFirstName;
    private String clientLastName;

    public Client() {
    }

    public Client(int clientIDCardNumber, String clientFirstName) {
        this.clientIDCardNumber = clientIDCardNumber;
        this.clientFirstName = clientFirstName;
    }

    public Client(int clientIDCardNumber, String clientFirstName, String clientLastName) {
        this.clientIDCardNumber = clientIDCardNumber;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
    }
    
    //Getters
    public int getClientIDCardNumber() {
        return clientIDCardNumber;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    //Setters
    public void setClientIDCardNumber(int clientIDCardNumber) {
        this.clientIDCardNumber = clientIDCardNumber;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }   
}
