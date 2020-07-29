package model;

import java.util.Date;
import java.util.List;

public class Administrator extends Korisnik {

    private List<Reklama> izlazeReklame;
    private List<Zahtev> listaZahteva;

    public Administrator(int id, String ime, String prezime, String email, String kontaktTelefon, String korisnickoIme, String lozicka, Date godinaRodjenja, List<Reklama> izlazeReklame, List<Zahtev> listaZahteva) {
        super(id, ime, prezime, email, kontaktTelefon, korisnickoIme, lozicka, godinaRodjenja);
        this.izlazeReklame = izlazeReklame;
        this.listaZahteva = listaZahteva;
    }

    public List<Reklama> getIzlazeReklame() {
        return izlazeReklame;
    }

    public void setIzlazeReklame(List<Reklama> izlazeReklame) {
        this.izlazeReklame = izlazeReklame;
    }

    public List<Zahtev> getListaZahteva() {
        return listaZahteva;
    }

    public void setListaZahteva(List<Zahtev> listaZahteva) {
        this.listaZahteva = listaZahteva;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "izlazeReklame=" + izlazeReklame +
                ", listaZahteva=" + listaZahteva +
                '}';
    }
}
