package model;

public class Zadatak {

    private int id;
    private MuzickoDelo delo;
    private Zanr zanr;
    private Ucesnik ucesnik;
    private Izvodjac izvodjac;

    public Zadatak(int id, MuzickoDelo delo, Zanr zanr, Ucesnik ucesnik, Izvodjac izvodjac) {
        this.id = id;
        this.delo = delo;
        this.zanr = zanr;
        this.ucesnik = ucesnik;
        this.izvodjac = izvodjac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Zadatak{" +
                "id=" + id +
                ", delo=" + delo +
                ", zanr=" + zanr +
                ", ucesnik=" + ucesnik +
                ", izvodjac=" + izvodjac +
                '}';
    }
}
