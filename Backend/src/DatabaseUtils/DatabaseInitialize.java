package DatabaseUtils;

/**
 * Diese Klasse ist für die Inititalisierung der Datenbank zuständig
 * Sieht noch Schei??????e aus, muss definitv noch umgeschrieben, erfüllt jedoch erstmal seinen Zweck
 * @author Alexander Huber
 * @version 02.08.2018
 */
public class DatabaseInitialize {

    /**
     * Initzialisiert die Datenbank mit den nötigen Tabellen.
     * @return
     */
    public static String InitializeDatabase(){
        StringBuilder builder = new StringBuilder();
        builder.append(initalizeRollenbezeichnung());
        builder.append(InitializeUser());
        builder.append(InitializeRaum());
        //builder.append(InitializeStudent());
        //builder.append(InitializeAngestellter());
        //builder.append(InitializeProfessor());
        //builder.append(InitializeMitarbeiter());
        builder.append(InitilaizeSchwarzesBrett());
        //builder.append(InitializeFormular());
        //builder.append(InitializeFreiesFormular());
        return builder.toString();
    }

    
       public static String InitializeUser() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE User (");
        builder.append("BenutzerID INTEGER, ");
        builder.append("Benutzername VARCHAR(255), ");
        builder.append("Name VARCHAR(255), ");
        builder.append("Vorname VARCHAR(255), ");
        builder.append("Passwort VARCHAR(255), ");
        builder.append("EMail VARCHAR(255), ");
        //builder.append("Fachbereich VARCHAR(255), ");
        builder.append("RollenID INTEGER,");
        //builder.append("Angemeldet BIT,");
        builder.append("PRIMARY KEY(BenutzerID)");
        builder.append("FOREIGN KEY(RollenID) REFERENCES StammtabelleRolle(RollenID)");
        builder.append(");\n");
        return builder.toString();
    }

    public static String InitializeRaum() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Raum (");
        //builder.append("Gebäudenummer INTEGER, ");
        //builder.append("Stockwerk INTEGER, ");
        //builder.append("Raumnummer VARCHAR(255), ");
        builder.append("Raumnummer VARCHAR(255), ");
        builder.append("Verantwortlicher INTEGER, ");
        builder.append("HTWSeite VARCHAR(255), ");
        //builder.append("FOREIGN KEY(Verantwortlicher) REFERENCES User(UserId), ");
        //builder.append("PRIMARY KEY(Gebäudenummer, Stockwerk, Raumnummer)");
        builder.append("PRIMARY KEY(Raumnummer)");
        builder.append(");\n");
        return builder.toString();
    }

 
    public static String InitilaizeSchwarzesBrett() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE SchwarzesBrett (");
        builder.append("RaumId INTEGER, ");
        builder.append("Name VARCHAR(255), ");
        builder.append("Beschreibung VARCHAR(255), ");
        builder.append("FOREIGN KEY(RaumId) REFERENCES Raum(Raumnummer), ");
        builder.append("PRIMARY KEY(RaumId)");
        builder.append(");\n");
        return builder.toString();
    }
    
    public static String initalizeRollenbezeichnung()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE StammtabelleRolle (");
        builder.append("RollenID INTEGER, ");
        builder.append("Bezeichnung, VARCHAR(255),");
        builder.append(");\n");
        return builder.toString();
    }

/*
    public static String InitializeStudent() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Student (");
        builder.append("Matrikelnummer INTEGER, ");
        builder.append("Semester INTEGER, ");
        builder.append("FOREIGN KEY(Matrikelnummer) REFERENCES User(UserId), ");
        builder.append("PRIMARY KEY(Matrikelnummer)");
        builder.append(");\n");
        return builder.toString();
    }

    //TODO: FOREIGN KEY muss noch angepasst werden. Da er sich auf einen Zusammengesetzten Schlüssel beziehen muss
    public static String InitializeAngestellter() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Angestellter (");
        builder.append("Personalnummer INTEGER, ");
        builder.append("Raum INTEGER, ");
        builder.append("FOREIGN KEY(Personalnummer) REFERENCES User(UserId), ");
        builder.append("FOREIGN KEY(Raum) REFERENCES Raum(Raumnummer), ");
        builder.append("PRIMARY KEY(Personalnummer)");
        builder.append(");\n");
        return builder.toString();
    }

    public static String InitializeFormular() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Formular (");
        builder.append("Typ VARCHAR(255), ");
        builder.append("SchwarzesBrett VARCHAR(255), ");
        builder.append("FOREIGN KEY(SchwarzesBrett) REFERENCES SchwarzesBrett(RaumId), ");
        builder.append("PRIMARY KEY(Typ)");
        builder.append(");\n");
        return builder.toString();
    }

    public static String InitializeFreiesFormular() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE FreiesFormular (");
        builder.append("FormularId INTEGER, ");
        builder.append("Pfad VARCHAR(255), ");
        builder.append("FOREIGN KEY(FormularId) REFERENCES Formular(Typ), ");
        builder.append("PRIMARY KEY(FormularId)");
        builder.append(");\n");
        return builder.toString();
    }

    public static String InitializeProfessor() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Professor (");
        builder.append("AngestelltenId INTEGER, ");
        builder.append("Rang VARCHAR(255), ");
        builder.append("Sprechzeiten VARCHAR(255), ");
        builder.append("FOREIGN KEY(AngestelltenId) REFERENCES Angestellter(Personalnummer), ");
        builder.append("PRIMARY KEY(AngestelltenId)");
        builder.append(");\n");
        return builder.toString();

    }

    public static String InitializeMitarbeiter() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Mitarbeiter (");
        builder.append("AngestelltenId INTEGER, ");
        builder.append("Fachgebiet VARCHAR(255), ");
        builder.append("FOREIGN KEY(AngestelltenId) REFERENCES Angestellter(Personalnummer), ");
        builder.append("PRIMARY KEY(AngestelltenId)");
        builder.append(");\n");
        return builder.toString();
    }
*/
        //createStatement().executeUpdate(DatabaseInitialize.Initialize());
}
