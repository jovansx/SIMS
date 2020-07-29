package model;

import java.util.Date;
import java.util.List;

public class TopLista {

    private int id;
    private Date datumGlasanja;
    private List<Izvodjenje> listaIzvodjenja;

    public TopLista(int id, Date datumGlasanja, List<Izvodjenje> listaIzvodjenja) {
        this.id = id;
        this.datumGlasanja = datumGlasanja;
        this.listaIzvodjenja = listaIzvodjenja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatumGlasanja() {
        return datumGlasanja;
    }

    public void setDatumGlasanja(Date datumGlasanja) {
        this.datumGlasanja = datumGlasanja;
    }

    public List<Izvodjenje> getListaIzvodjenja() {
        return listaIzvodjenja;
    }

    public void setListaIzvodjenja(List<Izvodjenje> listaIzvodjenja) {
        this.listaIzvodjenja = listaIzvodjenja;
    }

    @Override
    public String toString() {
        return "TopLista{" +
                "id=" + id +
                ", datumGlasanja=" + datumGlasanja +
                ", listaIzvodjenja=" + listaIzvodjenja +
                '}';
    }
}
