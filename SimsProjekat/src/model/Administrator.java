package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Administrator extends Korisnik {

    private List<Reklama> izlazeReklame;
    private List<Zahtev> listaZahteva;

    public Administrator(int id, String ime, String prezime, String email, String kontaktTelefon, String korisnickoIme, String lozicka, Date godinaRodjenja) {
        super(id, ime, prezime, email, kontaktTelefon, korisnickoIme, lozicka, godinaRodjenja);
    }

    public List<Reklama> getIzlazeReklame() {
        return izlazeReklame;
    }

    public List<Zahtev> getListaZahteva() {
        return listaZahteva;
    }

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
