package model;

import java.util.Date;
import java.util.List;

public class RegistovaniKorisnik extends Korisnik {

    private List<Izvodjenja> listaOmiljenogSadrzaja;
    private List<Recenzija> listaRecenzija;
    private List<PlejLista> listaPlejListi;
    private boolean jeVidljiv;

    public RegistovaniKorisnik(int id, String ime, String prezime, String email, String kontaktTelefon, String korisnickoIme, String lozicka, Date godinaRodjenja, List<Izvodjenja> listaOmiljenogSadrzaja, List<Recenzija> listaRecenzija, List<PlejLista> listaPlejListi) {
        super(id, ime, prezime, email, kontaktTelefon, korisnickoIme, lozicka, godinaRodjenja);
        this.listaOmiljenogSadrzaja = listaOmiljenogSadrzaja;
        this.listaRecenzija = listaRecenzija;
        this.listaPlejListi = listaPlejListi;
        this.jeVidljiv = true;
    }

    public List<Izvodjenja> getListaOmiljenogSadrzaja() {
        return listaOmiljenogSadrzaja;
    }

    public void setListaOmiljenogSadrzaja(List<Izvodjenja> listaOmiljenogSadrzaja) {
        this.listaOmiljenogSadrzaja = listaOmiljenogSadrzaja;
    }

    public List<Recenzija> getListaRecenzija() {
        return listaRecenzija;
    }

    public void setListaRecenzija(List<Recenzija> listaRecenzija) {
        this.listaRecenzija = listaRecenzija;
    }

    public List<PlejLista> getListaPlejListi() {
        return listaPlejListi;
    }

    public void setListaPlejListi(List<PlejLista> listaPlejListi) {
        this.listaPlejListi = listaPlejListi;
    }

    public boolean isJeVidljiv() {
        return jeVidljiv;
    }

    public void setJeVidljiv(boolean jeVidljiv) {
        this.jeVidljiv = jeVidljiv;
    }

    @Override
    public String toString() {
        return "RegistovaniKorisnik{" +
                "listaOmiljenogSadrzaja=" + listaOmiljenogSadrzaja +
                ", listaRecenzija=" + listaRecenzija +
                ", listaPlejListi=" + listaPlejListi +
                ", jeVidljiv=" + jeVidljiv +
                '}';
    }
}
