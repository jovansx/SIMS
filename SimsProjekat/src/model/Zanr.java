package model;

import java.util.Date;

public class Zanr {

    private String nazivZanra, opis;
    private Date datumNastanka;

    public Zanr(){}

    public Zanr(String nazivZanra, String opis, Date datumNastanka) {
        this.nazivZanra = nazivZanra;
        this.opis = opis;
        this.datumNastanka = datumNastanka;
    }

    public String getNazivZanra() {
        return nazivZanra;
    }

    public void setNazivZanra(String nazivZanra) {
        this.nazivZanra = nazivZanra;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatumNastanka() {
        return datumNastanka;
    }

    public void setDatumNastanka(Date datumNastanka) {
        this.datumNastanka = datumNastanka;
    }

    @Override
    public String toString() {
        return "Zanr{" +
                "nazivZanra='" + nazivZanra + '\'' +
                ", opis='" + opis + '\'' +
                ", datumNastanka=" + datumNastanka +
                '}';
    }
}
