package model;

import java.util.List;

public class Ucesnik {

    private int id;
    private String naziv, opis;
    private TipUcesnika tip;
    private List<MuzickoDelo> listaMuzickihDela;

    public Ucesnik(int id, String naziv, String opis, TipUcesnika tip, List<MuzickoDelo> listaMuzickihDela) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.tip = tip;
        this.listaMuzickihDela = listaMuzickihDela;
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

    public void setListaMuzickihDela(List<MuzickoDelo> listaMuzickihDela) {
        this.listaMuzickihDela = listaMuzickihDela;
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
