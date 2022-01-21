package de.eahjena.app.wi.recyclerview;

public class ItemTabelle {

    private Integer Platzierung;
    private String TeamLogo;
    private String Mannschaftsname;
    private String SpielAnzahl;
    private String SiegAnzahl;
    private String UnentschiedenAnzahl;
    private String NiederlagenAnzahl;
    private String TorAnzahl;
    private String TorDifferenz;
    private String Punkte;



    public ItemTabelle(Integer Platzierung , String TeamLogo, String Mannschaftsname , String SpielAnzahl , String SiegAnzahl, String UnentschiedenAnzahl,
                       String NiederlagenAnzahl , String TorAnzahl , String TorDifferenz , String Punkte){

        this.Platzierung = Platzierung;
        this.TeamLogo = TeamLogo;
        this.Mannschaftsname = Mannschaftsname;
        this.SpielAnzahl = SpielAnzahl;
        this.SiegAnzahl = SiegAnzahl;
        this.UnentschiedenAnzahl = UnentschiedenAnzahl;
        this.NiederlagenAnzahl = NiederlagenAnzahl;
        this.TorAnzahl = TorAnzahl;
        this.TorDifferenz = TorDifferenz;
        this.Punkte = Punkte;
    }

    public Integer getPlatzierung() { return Platzierung;    }

    public String getTeamLogo() {
        return TeamLogo;
    }

    public String getMannschaftsname() {
        return Mannschaftsname;
    }

    public String getSpielAnzahl() {
        return SpielAnzahl;
    }

    public String getSiegAnzahl() {
        return SiegAnzahl;
    }

    public String getUnentschiedenAnzahl() {
        return UnentschiedenAnzahl;
    }

    public String getNiederlagenAnzahl() {
        return NiederlagenAnzahl;
    }

    public String getTorAnzahl() {
        return TorAnzahl;
    }

    public String getTorDifferenz() {
        return TorDifferenz;
    }

    public String getPunkte() {
        return Punkte;
    }

}




