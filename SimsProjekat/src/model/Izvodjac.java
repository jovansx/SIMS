package model;

import model.enums.TipIzvodjaca;

import java.util.ArrayList;
import java.util.List;

public class Izvodjac {

    private int id;
    private String nazivIzvodjaca, opis;
    private TipIzvodjaca tipIzvodjaca;
    private Izvodjac pripadaGrupi;
    private List<Izvodjac> imaClanove;
    private List<Izvodjenje> listaIzvodjenja;

    public Izvodjac(int id, String nazivIzvodjaca, TipIzvodjaca tipIzvodjaca) {
        this.id = id;
        this.nazivIzvodjaca = nazivIzvodjaca;
        this.tipIzvodjaca = tipIzvodjaca;
        this.opis = "";
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

    public List<Izvodjac> getImaClanove() {
        return imaClanove;
    }

    public List<Izvodjenje> getListaIzvodjenja() {
        return listaIzvodjenja;
    }

    public void dodajClana(Izvodjac izvodjac) {
        if (imaClanove == null) {
            imaClanove = new ArrayList<Izvodjac>();
        }
        imaClanove.add(izvodjac);
    }

    public boolean obrisiClana(Izvodjac izvodjac) {
        if (imaClanove == null) {
            return false;
        }
        try {
            imaClanove.remove(izvodjac);
            return true;
        } catch (Exception e) {
            return false;
        }
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
        return "Izvodjac{" +
                "id=" + id +
                ", nazivIzvodjaca='" + nazivIzvodjaca + '\'' +
                ", opis='" + opis + '\'' +
                ", tipIzvodjaca=" + tipIzvodjaca +
                ", pripadaGrupi=" + pripadaGrupi +
                ", imaClanoce=" + imaClanove +
                ", listaIzvodjenja=" + listaIzvodjenja +
                '}';
    }
}
