package model;

import java.util.List;

public class PlejLista {

    private int id;
    private boolean jeJavna;
    private String naziv;
    private List<MuzickoDelo> listaMuzickihDela;

    public PlejLista(int id, String naziv, List<MuzickoDelo> listaMuzickihDela) {
        this.id = id;
        this.jeJavna = true;
        this.naziv = naziv;
        this.listaMuzickihDela = listaMuzickihDela;
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

    public void setListaMuzickihDela(List<MuzickoDelo> listaMuzickihDela) {
        this.listaMuzickihDela = listaMuzickihDela;
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
