/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * Modelklasse zum Abilden einer Kontakt Nachricht
 * @author pi
 */
public class Kontakt {
    private int nachrichtenID;
    private int raumnummer;
    private User sender;
    private User empfaenger;
    private String betreff;
    private int prio;
    private String nachricht;
    
    public int getNachrichtenID(){return nachrichtenID;}
    public int getRaumnummer(){return raumnummer;}
    public User getsender(){return sender;}
    public User getEmpfaenger(){return empfaenger;}
    public String getBetreff(){return betreff;}
    public int getPrio(){return prio;}
    public String getNachricht(){return nachricht;}
    
    public void setNachrichtenID(int nachrichtenID) {this.nachrichtenID = nachrichtenID;}
    public void setRaumnummer(int raumnummer){this.raumnummer = raumnummer;}
    public void setSender(User sender){this.sender = sender;}
    public void setEmpfaenger(User empfaenger){this.empfaenger = empfaenger;}
    public void setBetreff(String betreff){this.betreff = betreff;}
    public void setPrio(int prio){this.prio = prio;}
    public void setNachricht(String nachricht){this.nachricht = nachricht;}
}
