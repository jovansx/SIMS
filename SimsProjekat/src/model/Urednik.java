package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Urednik extends Korisnik {

    private List<Zadatak> listaZadataka;
    private List<Zanr> listaZanrova;
    private List<Recenzija> listaRecenzija;

    public Urednik(){

    }
    public Urednik(int id, String ime, String prezime, String email, String kontaktTelefon, KorisnickiNalog nalog, Date godinaRodjenja) {
        super(id, ime, prezime, email,kontaktTelefon,godinaRodjenja, nalog);
    }

    public List<Zadatak> getListaZadataka() {
        return listaZadataka;
    }

    public void dodajZadatak(Zadatak zadatak) {
        if (listaRecenzija == null) {
            listaZadataka = new ArrayList<Zadatak>();
        }
        listaZadataka.add(zadatak);
    }

    public boolean obrisiZadatak(Zadatak zadatak) {
        if (listaRecenzija == null) {
            return false;
        }
        try {
            listaZadataka.remove(zadatak);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Zanr> getListaZanrova() {
        return listaZanrova;
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

    public void setListaRecenzija(ArrayList<Recenzija> recenzije){ this.listaRecenzija=recenzije; }

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
        return "Urednik{" +
                "listaZadataka=" + listaZadataka +
                ", listaZanrova=" + listaZanrova +
                '}';
    }
}
