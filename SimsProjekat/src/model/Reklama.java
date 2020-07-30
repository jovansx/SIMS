package model;

public class Reklama {

    private int id, brojPristupa, brojPrikaza;
    private String naziv, text;
    private Double cena;

    public Reklama(){};
    public Reklama(int id, String naziv, String text, Double cena) {
        this.id = id;
        this.brojPristupa = 0;
        this.brojPrikaza = 15;
        this.naziv = naziv;
        this.text = text;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public int getBrojPristupa() {
        return brojPristupa;
    }

    public int getBrojPrikaza() {
        return brojPrikaza;
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

    public void setBrojPrikaza(int brojPrikaza) {
        this.brojPrikaza = brojPrikaza;
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

    @Override
    public String toString() {
        return "Reklama{" +
                "id=" + id +
                ", brojPristupa=" + brojPristupa +
                ", brojPrikaza=" + brojPrikaza +
                ", naziv='" + naziv + '\'' +
                ", text='" + text + '\'' +
                ", cena=" + cena +
                '}';
    }
}
