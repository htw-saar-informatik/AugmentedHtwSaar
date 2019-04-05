package Models.UsersNotInUse;

public class Mitarbeiter extends Angestellter{

    private String Fachgebiet;

    public String getFachgebiet() {
        return Fachgebiet;
    }

    public void setFachgebiet(String fachgebiet) {
        Fachgebiet = fachgebiet;
    }

}
