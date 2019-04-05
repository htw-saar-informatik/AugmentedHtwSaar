package Models.UsersNotInUse;

public class Professor extends Angestellter{

    private String Rang;
    private String Sprechzeiten;

    public String getRang() {
        return Rang;
    }

    public void setRang(String rang) {
        Rang = rang;
    }

    public String getSprechzeiten() {
        return Sprechzeiten;
    }

    public void setSprechzeiten(String sprechzeiten) {
        Sprechzeiten = sprechzeiten;
    }

}
