package model;

import java.util.ArrayList;
import java.util.List;

public class PlejLista {

    private int id;
    private boolean jeJavna;
    private String naziv;
    private List<Izvodjenje> listaIzvodjenja;

    public PlejLista(int id, String naziv) {
        this.id = id;
        this.jeJavna = true;
        this.naziv = naziv;
    }
    public PlejLista(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isJeJavna() {
        return jeJavna;
    }

    public void setJeJavna(boolean jeJavna) {
        this.jeJavna = jeJavna;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Izvodjenje> getListaIzvodjenja() {
        return listaIzvodjenja;
    }

    public void dodajIzvodjenje(Izvodjenje izvodjenje) {
        if (listaIzvodjenja == null) {
            listaIzvodjenja = new ArrayList<Izvodjenje>();
        }
        listaIzvodjenja.add(izvodjenje);
    }

    public boolean obrisiIzvodjenje(Izvodjenje izvodjenje) {
        if (listaIzvodjenja == null) {
            return false;
        }
        try {
            listaIzvodjenja.remove(izvodjenje);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setListaIzvodjenja(List<Izvodjenje> izvodjenja){
        this.listaIzvodjenja=izvodjenja;
    }
    @Override
    public String toString() {
        return "PlejLista{" +
                "id=" + id +
                ", jeJavna=" + jeJavna +
                ", naziv='" + naziv + '\'' +
                ", listaIzvodjenja=" + listaIzvodjenja +
                '}';
    }
}
