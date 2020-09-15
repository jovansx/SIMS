package model;

public class Zadatak {

    private int id;
    private String text;
    private MuzickoDelo delo;
    private Zanr zanr;
    private Ucesnik ucesnik;
    private Izvodjac izvodjac;
    private boolean obrisano;

    public Zadatak(int id, String text, MuzickoDelo delo, Zanr zanr, Ucesnik ucesnik, Izvodjac izvodjac) {
        this.id = id;
        this.text = text;
        this.delo = delo;
        this.zanr = zanr;
        this.ucesnik = ucesnik;
        this.izvodjac = izvodjac;
        this.obrisano = false;
    }
    public Zadatak(MuzickoDelo delo){
        this.id = -1;
        this.text = "";
        this.delo = delo;
        this.izvodjac = null;
        this.zanr = null;
        this.ucesnik = null;
        this.obrisano = false;
    }

    public Zadatak(Izvodjac izvodjac){
        this.id = -1;
        this.text = "";
        this.delo = null;
        this.izvodjac = izvodjac;
        this.zanr = null;
        this.ucesnik = null;
        this.obrisano = false;
    }
    public Zadatak(Zanr zanr){
        this.id = -1;
        this.text = "";
        this.delo = null;
        this.izvodjac = null;
        this.zanr = zanr;
        this.ucesnik = null;
        this.obrisano = false;
    }
    public Zadatak(Ucesnik ucesnik){
        this.id = -1;
        this.text = "";
        this.delo = null;
        this.izvodjac = null;
        this.zanr = null;
        this.ucesnik = ucesnik;
        this.obrisano = false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MuzickoDelo getDelo() {
        return delo;
    }

    public void setDelo(MuzickoDelo delo) {
        this.delo = delo;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Ucesnik getUcesnik() {
        return ucesnik;
    }

    public void setUcesnik(Ucesnik ucesnik) {
        this.ucesnik = ucesnik;
    }

    public Izvodjac getIzvodjac() {
        return izvodjac;
    }

    public void setIzvodjac(Izvodjac izvodjac) {
        this.izvodjac = izvodjac;
    }
    public boolean isObrisano(){ return obrisano;}

    public void setObrisano(boolean obrisano){this.obrisano = obrisano;}

    @Override
    public String toString() {
        return "Zadatak{" +
                "id=" + id +
                ", delo=" + delo +
                ", zanr=" + zanr +
                ", ucesnik=" + ucesnik +
                ", izvodjac=" + izvodjac +
                ", obrisano="+
                '}';
    }
}
