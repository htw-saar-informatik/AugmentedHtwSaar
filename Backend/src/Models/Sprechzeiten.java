package Models;

/**
 * Modell Klasse zum Abbilden der Sprechzeiten
 * @author pi
 */
public class Sprechzeiten {
	
	private int raumnummer;
	private User benutzerID;        
	private String montag;
	private String dienstag;
	private String mittwoch;
	private String donnerstag;
	private String freitag;
        private String nachricht;
	
	public Sprechzeiten() {}
	public Sprechzeiten(int raumnummer, User benutzerID, String montag, String dienstag, String mittwoch, String donnerstag, String freitag)
	{
		this.raumnummer = raumnummer;
		this.benutzerID = benutzerID;
		this.montag = montag;
		this.dienstag = dienstag;
		this.mittwoch = mittwoch;
		this.donnerstag = donnerstag;
		this.freitag = freitag;
	}

	public int getRaumNr() { return raumnummer;}
	public User getBenutzerID() {return benutzerID;}        
	public String getMontag() { return montag;}
	public String getDienstag() { return dienstag;}
	public String getMittwoch() {return mittwoch;}
	public String getDonnerstag() { return donnerstag;}
	public String getFreitag() { return freitag;}
        public String getNachricht() {return nachricht;}
	
	public void setRaumNr(int raumnummer) {this.raumnummer = raumnummer;}
	public void setBenutzerID(User benutzerID) {this.benutzerID = benutzerID;}
	public void setMontag(String montag) {this.montag = montag;}
	public void setDienstag(String dienstag) {this.dienstag = dienstag;}
	public void setMittwoch(String mittwoch) {this.mittwoch = mittwoch;}
	public void setDonnerstag(String donnerstag) {this.donnerstag = donnerstag;}
	public void setFreitag(String freitag) {this.freitag = freitag;}
        public void setNachricht(String nachricht) {this.nachricht = nachricht;}
	
}
