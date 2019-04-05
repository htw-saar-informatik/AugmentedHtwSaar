package Models;

/**
 * Abstrakte Klasse User. Von dem alle unsere Benutzerarten erben
 * @author Marco Becker, Alexander Huber
 * @version 18.07.2018
 */
public class User {
	
	private int benutzerID;
        private String benutzername;
        private String name;
        private String vorname;
        private String passwort;
        private String eMail;
        private int rollenID;
        private String rollenBezeichnung;
	
	/**
	 * Standard-Konstruktor
	 */
	public User() {}
        
        public User(String benutzername, String passwort)
        {
            this.benutzername = benutzername;
            this.passwort = passwort;
        }
        
        public User(int benutzerID, String benutzername, String name, String vorname)
        {
            this.benutzerID = benutzerID;
            this.benutzername = benutzername;
            this.name = name;
            this.vorname = vorname;
        }

        public int getBenutzerID(){ return benutzerID;}
        public String getBenutzername() { return benutzername;}
        public String getName(){ return name;}
        public String getVorname(){return vorname;}
        public String getPasswort(){return passwort;}
        public String getEMail(){return eMail;}
        public int getRollenID(){return rollenID;}
        public String getRollenBezeichnung(){return rollenBezeichnung;}

        

        public void setBenutzerID(int benutzerID) {this.benutzerID = benutzerID;}
        public void setBenutzername(String benutzername) {this.benutzername = benutzername;}
        public void setName(String name){this.name = name;}
        public void setVorname(String vorname){this.vorname = vorname;}
        public void setPasswort(String passwort){this.passwort = passwort;}
        public void setEMail(String eMail) {this.eMail = eMail;}
        public void setRollenID(int rollenID) {this.rollenID = rollenID;}
        public void setRollenbezeichnung(String rollenBezeichnung){this.rollenBezeichnung = rollenBezeichnung;}

	


}