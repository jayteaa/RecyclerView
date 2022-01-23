package de.eahjena.app.wi.recyclerview;

public class SpielstandDetail {



    private String SpielminuteTor;
    private String SpielstandUpdate;
    private String Torschütze;



    public SpielstandDetail(String SpielminuteTor, String SpielstandUpdate, String Torschütze){

        this.SpielminuteTor = SpielminuteTor;
        this.SpielstandUpdate = SpielstandUpdate;
        this.Torschütze = Torschütze;


    }

    public String getSpielminuteTor() { return SpielminuteTor;    }

    public String getSpielstandUpdate() { return SpielstandUpdate;    }

    public String getTorschütze() {
        return Torschütze;
    }


}




