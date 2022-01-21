package de.eahjena.app.wi.recyclerview;

public class ItemDetail {


    private String LogoHeimDetail;
    private String LogoGastDetail;
    private String EndergebnisDetail;
    private String ZwischenergebnisDetail;
    private String SpielstartDetail;
    private String StadionDetail;
    private String ZuschauerDetail;




    public ItemDetail(String LogoHeimDetail, String LogoGastDetail, String EndergebnisDetail, String ZwischenergebnisDetail,
                      String SpielstartDetail, String StadionDetail, String ZuschauerDetail ){

        this.LogoHeimDetail = LogoHeimDetail;
        this.LogoGastDetail = LogoGastDetail;
        this.EndergebnisDetail = EndergebnisDetail;
        this.ZwischenergebnisDetail = ZwischenergebnisDetail;
        this.SpielstartDetail = SpielstartDetail;
        this.StadionDetail = StadionDetail;
        this.ZuschauerDetail = ZuschauerDetail;

    }

    public String getLogoHeimDetail() { return LogoHeimDetail;    }

    public String getLogoGastDetail() {
        return LogoGastDetail;
    }

    public String getEndergebnisDetail() {
        return EndergebnisDetail;
    }

    public String getZwischenergebnisDetail() {
        return ZwischenergebnisDetail;
    }

    public String getSpielstartDetail() {
        return SpielstartDetail;
    }

    public String getStadionDetail() {
        return StadionDetail;
    }

    public String getZuschauerDetail() {
        return ZuschauerDetail;
    }

}




