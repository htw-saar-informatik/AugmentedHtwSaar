package Models.UsersNotInUse;

import Models.Raum;
import Models.User;

public abstract class Angestellter extends User {


    private int personalnummer;
    private Raum raum;


    /**
     * @return
     */
    public int getPersonalnummer() {
        return personalnummer;
    }


    /**
     * @param personalnummer
     */
    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }

    /**
     * @return
     */
    public Raum getRaum() {
        return raum;
    }

    /**
     * @param raum
     */
    public void setRaum(Raum raum) {
        this.raum = raum;
    }

}
