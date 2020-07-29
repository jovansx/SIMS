package model;

import java.util.Date;
import java.util.List;

public class Album extends MuzickoDelo {

    private int id;
    private TipAlbuma tipAlbuma;
    private List<MuzickoDelo> listaMuzickihDela;

    public Album(int id, String nazivDela, Date datumPostavljanja, Date vremeNastanka) {
        super(id, nazivDela, datumPostavljanja, vremeNastanka);
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

    public void setListaMuzickihDela(List<MuzickoDelo> listaMuzickihDela) {
        this.listaMuzickihDela = listaMuzickihDela;
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
