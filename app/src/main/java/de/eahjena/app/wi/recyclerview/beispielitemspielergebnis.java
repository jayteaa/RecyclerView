package de.eahjena.app.wi.recyclerview;

public class beispielitemspielergebnis {

    private String Heimmannschaft;
    private String Gastmannschaft;
    private String Endergebnis;
    private String LogoHeim;
    private String LogoGast;



    public beispielitemspielergebnis(String Heimmannschaft , String Gastmannschaft , String Endergebnis , String LogoHeim, String LogoGast){
        this.Heimmannschaft = Heimmannschaft;
        this.Gastmannschaft = Gastmannschaft;
        this.Endergebnis = Endergebnis;
        this.LogoHeim = LogoHeim;
        this.LogoGast = LogoGast;
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
}




