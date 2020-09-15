package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Administrator extends Korisnik {

    private List<Reklama> izlazeReklame;
    private List<Zahtev> listaZahteva;

    public Administrator() {}
    public Administrator(int id, String ime, String prezime, String email, String kontaktTelefon, KorisnickiNalog nalog, Date godinaRodjenja) {
        super(id, ime, prezime, email, kontaktTelefon, godinaRodjenja, nalog);
    }

    public List<Reklama> getIzlazeReklame() {
        return izlazeReklame;
    }

    public List<Zahtev> getListaZahteva() {
        return listaZahteva;
    }

    public void setIzlazeReklame(List<Reklama> reklame){this.izlazeReklame=reklame;}

    public void setListaZahteva(List<Zahtev> zahtjevi) {this.listaZahteva=zahtjevi;}
    public void dodajReklamu(Reklama reklama) {
        if (izlazeReklame == null) {
            izlazeReklame = new ArrayList<Reklama>();
        }
        izlazeReklame.add(reklama);
    }

    public boolean obrisiReklamu(Reklama reklama) {
        if (izlazeReklame == null) {
            return false;
        }
        try {
            izlazeReklame.remove(reklama);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void dodajZahtev(Zahtev zahtev) {
        if (listaZahteva == null) {
            listaZahteva = new ArrayList<Zahtev>();
        }
        listaZahteva.add(zahtev);
    }

    public boolean obrisiZahtev(Zahtev zahtev) {
        if (listaZahteva == null) {
            return false;
        }
        try {
            listaZahteva.remove(zahtev);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "izlazeReklame=" + izlazeReklame +
                ", listaZahteva=" + listaZahteva +
                '}';
    }
}
