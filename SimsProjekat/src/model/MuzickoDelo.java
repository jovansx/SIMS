package model;

import java.util.Date;
import java.util.List;

public class MuzickoDelo {

    private int id;
    private String nazivDela, opis, sadrzaj;
    private Date datumPostavljanja, vremeNastanka;
    private double prosecnaOcena;
    private List<Zanr> listaZanrova;
    private List<Izvodjenje> listaIzvodjenja;
    private List<Ucesnik> listaUcesnika;
    private List<Recenzija> listaRecenzija;
    private List<Album> listaAlbuma;

    public MuzickoDelo(int id, String nazivDela, Date datumPostavljanja, Date vremeNastanka) {
        this.id = id;
        this.nazivDela = nazivDela;
        this.datumPostavljanja = datumPostavljanja;
        this.vremeNastanka = vremeNastanka;
        this.prosecnaOcena = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivDela() {
        return nazivDela;
    }

    public void setNazivDela(String nazivDela) {
        this.nazivDela = nazivDela;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Date getDatumPostavljanja() {
        return datumPostavljanja;
    }

    public void setDatumPostavljanja(Date datumPostavljanja) {
        this.datumPostavljanja = datumPostavljanja;
    }

    public Date getVremeNastanka() {
        return vremeNastanka;
    }

    public void setVremeNastanka(Date vremeNastanka) {
        this.vremeNastanka = vremeNastanka;
    }

    public double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public List<Zanr> getListaZanrova() {
        return listaZanrova;
    }

    public void setListaZanrova(List<Zanr> listaZanrova) {
        this.listaZanrova = listaZanrova;
    }

    public List<Izvodjenje> getListaIzvodjenja() {
        return listaIzvodjenja;
    }

    public void setListaIzvodjenja(List<Izvodjenje> listaIzvodjenja) {
        this.listaIzvodjenja = listaIzvodjenja;
    }

    public List<Ucesnik> getListaUcesnika() {
        return listaUcesnika;
    }

    public void setListaUcesnika(List<Ucesnik> listaUcesnika) {
        this.listaUcesnika = listaUcesnika;
    }

    public List<Recenzija> getListaRecenzija() {
        return listaRecenzija;
    }

    public void setListaRecenzija(List<Recenzija> listaRecenzija) {
        this.listaRecenzija = listaRecenzija;
    }

    public List<Album> getListaAlbuma() {
        return listaAlbuma;
    }

    public void setListaAlbuma(List<Album> listaAlbuma) {
        this.listaAlbuma = listaAlbuma;
    }

    @Override
    public String toString() {
        return "MuzickoDelo{" +
                "id=" + id +
                ", nazivDela='" + nazivDela + '\'' +
                ", opis='" + opis + '\'' +
                ", sadrzaj='" + sadrzaj + '\'' +
                ", datumPostavljanja=" + datumPostavljanja +
                ", vremeNastanka=" + vremeNastanka +
                ", prosecnaOcena=" + prosecnaOcena +
                ", listaZanrova=" + listaZanrova +
                ", listaIzvodjenja=" + listaIzvodjenja +
                ", listaUcesnika=" + listaUcesnika +
                ", listaRecenzija=" + listaRecenzija +
                ", listaAlbuma=" + listaAlbuma +
                '}';
    }
}
