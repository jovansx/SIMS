package model;

import model.enums.TipUcesnika;

import java.util.ArrayList;
import java.util.List;

public class Ucesnik {

    private int id;
    private String naziv, opis;
    private TipUcesnika tip;
    private List<MuzickoDelo> listaMuzickihDela;

    public Ucesnik() {
    }

    public Ucesnik(int id, String naziv, String opis, TipUcesnika tip) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public TipUcesnika getTip() {
        return tip;
    }

    public void setTip(TipUcesnika tip) {
        this.tip = tip;
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

    public void setListaMuzickihDela(List<MuzickoDelo> listaMuzickihDela) {
        this.listaMuzickihDela = listaMuzickihDela;
    }

    public boolean obrisiMuzickoDelo(MuzickoDelo muzickoDelo) {
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
        return "Ucesnik{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", tip=" + tip +
                ", listaMuzickihDela=" + listaMuzickihDela +
                '}';
    }
}
