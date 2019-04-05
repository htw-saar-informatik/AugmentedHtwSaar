package Models;

/**
 * Diese Klasse spezifiziert eine Objektmodellierung eines Raumes
 * @author Marco Becker, Alexander Huber
 * @version 18.07.2018
 */
public class Raum {

	private int raumnummer;
        private User verantwortlicher;
        private int raumartID;
        private String raumartBezeichnung;
        private String htwSeite;
        
        public int getRaumnummer(){ return raumnummer;}
        public User getVerantwortlicher(){ return verantwortlicher;}
        public int getRaumartID(){return raumartID;}
        public String getRaumartBezeichnung(){return raumartBezeichnung;}
        public String getHtwSeite(){return htwSeite;}
        
        public void setRaumnummer(int raumnummer){this.raumnummer = raumnummer;}
        public void setVerantwortlicher(User verantwortlicher){this.verantwortlicher = verantwortlicher;}
        public void setRaumartID(int raumartID){this.raumartID = raumartID;}
        public void setRaumartBezeichnung(String raumartBezeichnung){this.raumartBezeichnung = raumartBezeichnung;}
        public void setHtwSeite(String htwSeite){this.htwSeite = htwSeite;}
}
