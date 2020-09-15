package model;

import model.enums.TipIzvodjenja;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Izvodjenje {

    private int id, brPristupa, brGlasova, ukupnoPrisupa, trajanje;
    private Date vremeIzvodjenja;
    private TipIzvodjenja tipIzvodjenja;
    private List<Recenzija> listaRecenzija;
    private List<Izvodjac> listaIzvodjaca;
    private List<MuzickoDelo> listaMuzickihDela;
    private MestoIzvodjenja mestoIzvodjenja;

    public Izvodjenje(){
        this.brPristupa = 0;
        this.brGlasova = 0;
        this.ukupnoPrisupa = 0;
        this.trajanje = 0;
    };
    public Izvodjenje(int id, Date vremeIzvodjenja, TipIzvodjenja tipIzvodjenja, MestoIzvodjenja mestoIzvodjenja) {
        this.id = id;
        this.brPristupa = 0;
        this.brGlasova = 0;
        this.ukupnoPrisupa = 0;
        this.trajanje = 0;
        this.vremeIzvodjenja = vremeIzvodjenja;
        this.tipIzvodjenja = tipIzvodjenja;
        this.mestoIzvodjenja = mestoIzvodjenja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrPristupa() {
        return brPristupa;
    }

    public void setBrPristupa(int brPristupa) {
        this.brPristupa = brPristupa;
    }

    public int getBrGlasova() {
        return brGlasova;
    }

    public void setBrGlasova(int brGlasova) {
        this.brGlasova = brGlasova;
    }

    public int getUkupnoPrisupa() {
        return ukupnoPrisupa;
    }

    public void setUkupnoPrisupa(int ukupnoPrisupa) {
        this.ukupnoPrisupa = ukupnoPrisupa;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public Date getVremeIzvodjenja() {
        return vremeIzvodjenja;
    }

    public void setVremeIzvodjenja(Date vremeIzvodjenja) {
        this.vremeIzvodjenja = vremeIzvodjenja;
    }

    public TipIzvodjenja getTipIzvodjenja() {
        return tipIzvodjenja;
    }

    public void setTipIzvodjenja(TipIzvodjenja tipIzvodjenja) {
        this.tipIzvodjenja = tipIzvodjenja;
    }

    public List<Recenzija> getListaRecenzija() {
        return listaRecenzija;
    }

    public void setListaRecenzija(List<Recenzija> recenzije){this.listaRecenzija=recenzije;}

    public List<Izvodjac> getListaIzvodjaca() {
        return listaIzvodjaca;
    }

    public void setListaIzvodjaca(List<Izvodjac> izvodjaci){this.listaIzvodjaca=izvodjaci;}

    public List<MuzickoDelo> getListaMuzickihDela() {
        return listaMuzickihDela;
    }

    public void setListaMuzickihDela(List<MuzickoDelo> dela){this.listaMuzickihDela=dela;}

    public MestoIzvodjenja getMestoIzvodjenja() {
        return mestoIzvodjenja;
    }

    public void setMestoIzvodjenja(MestoIzvodjenja mestoIzvodjenja) {
        this.mestoIzvodjenja = mestoIzvodjenja;
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

    public void dodajRecenziju(Recenzija recenzija) {
        if (listaRecenzija == null) {
            listaRecenzija = new ArrayList<Recenzija>();
        }
        listaRecenzija.add(recenzija);
    }

    public boolean obrisiRecenziju(Recenzija recenzija) {
        if (listaRecenzija == null) {
            return false;
        }
        try {
            listaRecenzija.remove(recenzija);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void dodajIzvodjaca(Izvodjac izvodjac) {
        if (listaIzvodjaca == null) {
            listaIzvodjaca = new ArrayList<Izvodjac>();
        }
        listaIzvodjaca.add(izvodjac);
    }

    public boolean obrisiIzvodjaca(Izvodjac izvodjac) {
        if (listaIzvodjaca == null) {
            return false;
        }
        try {
            listaIzvodjaca.remove(izvodjac);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Izvodjenje{" +
                "id=" + id +
                ", brPristupa=" + brPristupa +
                ", brGlasova=" + brGlasova +
                ", ukupnoPrisupa=" + ukupnoPrisupa +
                ", trajanje=" + trajanje +
                ", vremeIzvodjenja=" + vremeIzvodjenja +
                ", tipIzvodjenja=" + tipIzvodjenja +
                ", recenzije=" + listaRecenzija +
                ", listaIzvodjaca=" + listaIzvodjaca +
                ", listaMuzickihDela=" + listaMuzickihDela +
                ", mestoIzvodjenja=" + mestoIzvodjenja +
                '}';
    }
}
