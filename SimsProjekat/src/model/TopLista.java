package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopLista {

    private int id;
    private Date datumGlasanja;
    private List<Izvodjenje> listaIzvodjenja;

    public TopLista() {
    }

    public TopLista(int id, Date datumGlasanja) {
        this.id = id;
        this.datumGlasanja = datumGlasanja;
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

    public void setListaIzvodjenja(List<Izvodjenje> lista){
        this.listaIzvodjenja = lista;
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

    @Override
    public String toString() {
        return "TopLista{" +
                "id=" + id +
                ", datumGlasanja=" + datumGlasanja +
                ", listaIzvodjenja=" + listaIzvodjenja +
                '}';
    }
}
