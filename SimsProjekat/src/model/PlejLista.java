package model;

import java.util.ArrayList;
import java.util.List;

public class PlejLista {

    private int id;
    private boolean jeJavna;
    private String naziv;
    private List<MuzickoDelo> listaMuzickihDela;

    public PlejLista(int id, String naziv) {
        this.id = id;
        this.jeJavna = true;
        this.naziv = naziv;
    }

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

    public List<MuzickoDelo> getListaMuzickihDela() {
        return listaMuzickihDela;
    }

    public void dodajMuzickoDelo(MuzickoDelo muzickoDelo) {
        if (listaMuzickihDela == null) {
            listaMuzickihDela = new ArrayList<MuzickoDelo>();
        }
        listaMuzickihDela.add(muzickoDelo);
    }

    public boolean obrisiRecenziju(MuzickoDelo muzickoDelo) {
        if (listaMuzickihDela == null) {
            return false;
        }
        try {
            listaMuzickihDela.remove(muzickoDelo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "PlejLista{" +
                "id=" + id +
                ", jeJavna=" + jeJavna +
                ", naziv='" + naziv + '\'' +
                ", listaMuzickihDela=" + listaMuzickihDela +
                '}';
    }
}
