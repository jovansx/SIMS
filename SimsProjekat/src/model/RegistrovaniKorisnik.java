package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistrovaniKorisnik extends Korisnik {

    private List<Izvodjenje> listaOmiljenogSadrzaja;
    private List<Recenzija> listaRecenzija;
    private List<PlejLista> listaPlejListi;
    private boolean jeVidljiv;

    public RegistrovaniKorisnik() {}

    public RegistrovaniKorisnik(int id, String ime, String prezime, String email, String kontaktTelefon, KorisnickiNalog nalog, Date godinaRodjenja) {
        super(id, ime, prezime, email, kontaktTelefon, godinaRodjenja, nalog);
        this.jeVidljiv = true;
    }

    public RegistrovaniKorisnik(String ime, String prezime, String email, String kontaktTelefon, KorisnickiNalog nalog, Date godinaRodjenja) {
        super(ime, prezime, email, kontaktTelefon, godinaRodjenja, nalog);
        this.jeVidljiv = true;
    }

    public List<Izvodjenje> getListaOmiljenogSadrzaja() {
        return listaOmiljenogSadrzaja;
    }

    public void dodajIzvodjenje(Izvodjenje izvodjenje) {
        if (listaOmiljenogSadrzaja == null) {
            listaOmiljenogSadrzaja = new ArrayList<Izvodjenje>();
        }
        listaOmiljenogSadrzaja.add(izvodjenje);
    }

    public boolean obrisiIzvodjenje(Izvodjenje izvodjenje) {
        if (listaOmiljenogSadrzaja == null) {
            return false;
        }
        try {
            listaOmiljenogSadrzaja.remove(izvodjenje);
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

    public List<PlejLista> getListaPlejListi() {
        return listaPlejListi;
    }

    public void dodajPlejListu(PlejLista plejLista) {
        if (listaPlejListi == null) {
            listaPlejListi = new ArrayList<PlejLista>();
        }
        listaPlejListi.add(plejLista);
    }

    public boolean obrisiPlejListu(PlejLista plejLista) {
        if (listaPlejListi == null) {
            return false;
        }
        try {
            listaPlejListi.remove(plejLista);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isJeVidljiv() {
        return jeVidljiv;
    }

    public void setJeVidljiv(boolean jeVidljiv) {
        this.jeVidljiv = jeVidljiv;
    }

    @Override
    public String toString() {
        return "RegKorisnik "+getIme()+" "+getPrezime();
    }
}
