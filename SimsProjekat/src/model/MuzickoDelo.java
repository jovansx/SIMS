package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MuzickoDelo {

    private int id;
    private String nazivDela, opis, sadrzaj;
    private Date datumPostavljanja, vremeNastanka;
    private double prosecnaOcena;
    private Album albumKomPripada;
    private List<Zanr> listaZanrova;
    private List<Izvodjenje> listaIzvodjenja;
    private List<Ucesnik> listaUcesnika;
    private List<Recenzija> listaRecenzija;

    public MuzickoDelo(){}

    public MuzickoDelo(int id, String nazivDela, Date datumPostavljanja, Date vremeNastanka,Album album) {
        this.id = id;
        this.nazivDela = nazivDela;
        this.datumPostavljanja = datumPostavljanja;
        this.vremeNastanka = vremeNastanka;
        this.prosecnaOcena = 0;
        this.albumKomPripada = album;
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

    public Album getAlbumKomPripada() {
        return albumKomPripada;
    }

    public void setAlbumKomPripada(Album albumKomPripada) {
        this.albumKomPripada = albumKomPripada;
    }

    public List<Zanr> getListaZanrova() {
        return listaZanrova;
    }

    public void setListaZanrova(List<Zanr> listaZanrova) {
        this.listaZanrova = listaZanrova;
    }

    public void setListaIzvodjenja(List<Izvodjenje> listaIzvodjenja) {
        this.listaIzvodjenja = listaIzvodjenja;
    }

    public void setListaUcesnika(List<Ucesnik> listaUcesnika) {
        this.listaUcesnika = listaUcesnika;
    }

    public void setListaRecenzija(List<Recenzija> listaRecenzija) {
        this.listaRecenzija = listaRecenzija;
    }

    public void dodajZanr(Zanr zanr) {
        if (listaZanrova == null) {
            listaZanrova = new ArrayList<Zanr>();
        }
        listaZanrova.add(zanr);
    }

    public boolean obrisiZanr(Zanr zanr) {
        if (listaZanrova == null) {
            return false;
        }
        try {
            listaZanrova.remove(zanr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<Izvodjenje> getListaIzvodjenja() {
        return listaIzvodjenja;
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

    public List<Ucesnik> getListaUcesnika() {
        return listaUcesnika;
    }

    public void dodajUcesnika(Ucesnik ucesnik) {
        if (listaUcesnika == null) {
            listaUcesnika = new ArrayList<Ucesnik>();
        }
        listaUcesnika.add(ucesnik);
    }

    public boolean obrisiUcesnika(Ucesnik ucesnik) {
        if (listaUcesnika == null) {
            return false;
        }
        try {
            listaUcesnika.remove(ucesnik);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Recenzija> getListaRecenzija() {
        return listaRecenzija;
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
                ", albumKomPripada=" + albumKomPripada +
                ", listaZanrova=" + listaZanrova +
                ", listaIzvodjenja=" + listaIzvodjenja +
                ", listaUcesnika=" + listaUcesnika +
                ", listaRecenzija=" + listaRecenzija +
                '}';
    }
}
