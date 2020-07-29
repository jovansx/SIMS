package model;

import java.util.Date;
import java.util.List;

public class Izvodjenje {

    private int id, brPristupa, brGlasova, ukupnoPrisupa, trajanje;
    private Date vremeIzvodjenja;
    private TipIzvodjenja tipIzvodjenja;
    private List<Recenzija> recenzije;
    private List<Izvodjac> listaIzvodjaca;
    private List<MuzickoDelo> listaMuzickihDela;
    private MestoIzvodjenja mestoIzvodjenja;

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

    public List<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(List<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public List<Izvodjac> getListaIzvodjaca() {
        return listaIzvodjaca;
    }

    public void setListaIzvodjaca(List<Izvodjac> listaIzvodjaca) {
        this.listaIzvodjaca = listaIzvodjaca;
    }

    public List<MuzickoDelo> getListaMuzickihDela() {
        return listaMuzickihDela;
    }

    public void setListaMuzickihDela(List<MuzickoDelo> listaMuzickihDela) {
        this.listaMuzickihDela = listaMuzickihDela;
    }

    public MestoIzvodjenja getMestoIzvodjenja() {
        return mestoIzvodjenja;
    }

    public void setMestoIzvodjenja(MestoIzvodjenja mestoIzvodjenja) {
        this.mestoIzvodjenja = mestoIzvodjenja;
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
                ", recenzije=" + recenzije +
                ", listaIzvodjaca=" + listaIzvodjaca +
                ", listaMuzickihDela=" + listaMuzickihDela +
                ", mestoIzvodjenja=" + mestoIzvodjenja +
                '}';
    }
}
