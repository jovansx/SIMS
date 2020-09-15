package model;

import java.util.Date;

public class Reklama {

    private int id, brojPristupa;
    private String naziv, text, url;
    private Date datumIsteka;
    private Double cena;

    public Reklama(){};

    public Reklama(int id, String naziv, String text, Double cena, Date datumIsteka, String url) {
        this.id = id;
        this.brojPristupa = 0;
        this.naziv = naziv;
        this.text = text;
        this.cena = cena;
        this.url = url;
        this.datumIsteka = datumIsteka;
    }

    public int getId() {
        return id;
    }

    public int getBrojPristupa() {
        return brojPristupa;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getText() {
        return text;
    }

    public Double getCena() {
        return cena;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrojPristupa(int brojPristupa) {
        this.brojPristupa = brojPristupa;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

}
