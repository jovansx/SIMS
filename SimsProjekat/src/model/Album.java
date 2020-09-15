package model;

import model.enums.TipAlbuma;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class
Album extends MuzickoDelo {

    private int id;
    private TipAlbuma tipAlbuma;
    private List<MuzickoDelo> listaMuzickihDela;

    public Album(){}
    public Album(int id, String nazivDela, Date datumPostavljanja, Date vremeNastanka, TipAlbuma tipAlbuma) {
        super(id, nazivDela, datumPostavljanja, vremeNastanka, null);
        this.tipAlbuma = tipAlbuma;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public TipAlbuma getTipAlbuma() {
        return tipAlbuma;
    }

    public void setTipAlbuma(TipAlbuma tipAlbuma) {
        this.tipAlbuma = tipAlbuma;
    }

    public List<MuzickoDelo> getListaMuzickihDela() {
        return listaMuzickihDela;
    }

    public void setListaMuzickihDela(List<MuzickoDelo> dela) {this.listaMuzickihDela=dela;}
    public void dodajMuzickoDelo(MuzickoDelo muzickoDelo) {
        if (listaMuzickihDela == null) {
            listaMuzickihDela = new ArrayList<MuzickoDelo>();
        }
        listaMuzickihDela.add(muzickoDelo);
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
        return "Album{" +
                "id=" + id +
                ", tipAlbuma=" + tipAlbuma +
                ", listaMuzickihDela=" + listaMuzickihDela +
                '}';
    }
}
