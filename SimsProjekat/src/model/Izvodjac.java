package model;

import java.util.List;

public class Izvodjac {

    private int id;
    private String nazivIzvodjaca, opis;
    private TipIzvodjaca tipIzvodjaca;
    private Izvodjac pripadaGrupi;
    private List<Izvodjac> imaClanoce;
    private List<Izvodjenje> listaIzvodjenja;

    public Izvodjac(int id, String nazivIzvodjaca, TipIzvodjaca tipIzvodjaca) {
        this.id = id;
        this.nazivIzvodjaca = nazivIzvodjaca;
        this.tipIzvodjaca = tipIzvodjaca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivIzvodjaca() {
        return nazivIzvodjaca;
    }

    public void setNazivIzvodjaca(String nazivIzvodjaca) {
        this.nazivIzvodjaca = nazivIzvodjaca;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public TipIzvodjaca getTipIzvodjaca() {
        return tipIzvodjaca;
    }

    public void setTipIzvodjaca(TipIzvodjaca tipIzvodjaca) {
        this.tipIzvodjaca = tipIzvodjaca;
    }

    public Izvodjac getPripadaGrupi() {
        return pripadaGrupi;
    }

    public void setPripadaGrupi(Izvodjac pripadaGrupi) {
        this.pripadaGrupi = pripadaGrupi;
    }

    public List<Izvodjac> getImaClanoce() {
        return imaClanoce;
    }

    public void setImaClanoce(List<Izvodjac> imaClanoce) {
        this.imaClanoce = imaClanoce;
    }

    public List<Izvodjenje> getListaIzvodjenja() {
        return listaIzvodjenja;
    }

    public void setListaIzvodjenja(List<Izvodjenje> listaIzvodjenja) {
        this.listaIzvodjenja = listaIzvodjenja;
    }

    @Override
    public String toString() {
        return "Izvodjac{" +
                "id=" + id +
                ", nazivIzvodjaca='" + nazivIzvodjaca + '\'' +
                ", opis='" + opis + '\'' +
                ", tipIzvodjaca=" + tipIzvodjaca +
                ", pripadaGrupi=" + pripadaGrupi +
                ", imaClanoce=" + imaClanoce +
                ", listaIzvodjenja=" + listaIzvodjenja +
                '}';
    }
}
