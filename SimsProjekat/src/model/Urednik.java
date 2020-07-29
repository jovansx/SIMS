package model;

import java.util.Date;
import java.util.List;

public class Urednik extends Korisnik {

    private List<Zadatak> listaZadataka;
    private List<Zanr> listaZanrova;
    private List<Recenzija> listaRecenzija;

    public Urednik(int id, String ime, String prezime, String email, String kontaktTelefon, String korisnickoIme, String lozicka, Date godinaRodjenja, List<Zadatak> listaZadataka, List<Zanr> listaZanrova) {
        super(id, ime, prezime, email, kontaktTelefon, korisnickoIme, lozicka, godinaRodjenja);
        this.listaZadataka = listaZadataka;
        this.listaZanrova = listaZanrova;
    }

    public List<Zadatak> getListaZadataka() {
        return listaZadataka;
    }

    public void setListaZadataka(List<Zadatak> listaZadataka) {
        this.listaZadataka = listaZadataka;
    }

    public List<Zanr> getListaZanrova() {
        return listaZanrova;
    }

    public void setListaZanrova(List<Zanr> listaZanrova) {
        this.listaZanrova = listaZanrova;
    }

    @Override
    public String toString() {
        return "Urednik{" +
                "listaZadataka=" + listaZadataka +
                ", listaZanrova=" + listaZanrova +
                '}';
    }
}
