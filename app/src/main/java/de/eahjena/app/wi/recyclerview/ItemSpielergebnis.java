package de.eahjena.app.wi.recyclerview;

public class ItemSpielergebnis {

    private String Heimmannschaft;
    private String Gastmannschaft;
    private String Endergebnis;
    private String Zwischenergebnis;
    private String Stadion;
    private String Zuschauer;
    private String Spielstart;
    private String LogoHeim;
    private String LogoGast;
    private String matchID;



    public ItemSpielergebnis(String Heimmannschaft , String Gastmannschaft , String Endergebnis, String Zwischenergebnis, String Stadion, String Zuschauer, String Spielstart,
                             String LogoHeim, String LogoGast, String matchID){


        this.Heimmannschaft = Heimmannschaft;
        this.Gastmannschaft = Gastmannschaft;
        this.Endergebnis = Endergebnis;
        this.LogoHeim = LogoHeim;
        this.LogoGast = LogoGast;
        this.Zwischenergebnis = Zwischenergebnis;
        this.Stadion = Stadion;
        this.Zuschauer = Zuschauer;
        this.Spielstart = Spielstart;
        this.matchID = matchID;
    }

    public String getHeimmannschaft() {
        return Heimmannschaft;
    }

    public String getGastmannschaft() {
        return Gastmannschaft;
    }

    public String getEndergebnis() {
        return Endergebnis;
    }

    public String getLogoHeim() {
        return LogoHeim;
    }

    public String getLogoGast() {
        return LogoGast;
    }

    public String getZwischenergebnis() {
        return Zwischenergebnis;
    }

    public String getSpielstart() {
        return Spielstart;
    }

    public String getStadion() {
        return Stadion;
    }

    public String getZuschauer() {
        return Zuschauer;
    }

    public String getMatchID() {return matchID;}

    }






