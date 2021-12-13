package de.eahjena.app.wi.recyclerview;

public class beispielitemspielergebnis {
    private int LogoHeimmannschaft;
    private int LogoGastmannschaft;
    private String Heimmannschaft;
    private String Gastmannschaft;
    private String Endergebnis;

    public beispielitemspielergebnis(int LHeimmannschaft, int LGastmannschaft, String HHeimmannschaft, String GGastmannschaft, String EEndergebnis)

    {
        LogoHeimmannschaft = LHeimmannschaft;
        LogoGastmannschaft = LGastmannschaft;
        Heimmannschaft = HHeimmannschaft;
        Gastmannschaft = GGastmannschaft;
        Endergebnis = EEndergebnis;
    }

    public int getLogoHeimmannschaft() {
        return LogoHeimmannschaft;
    }

    public int getLogoGastmannschaft() {
        return LogoGastmannschaft;
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
}
