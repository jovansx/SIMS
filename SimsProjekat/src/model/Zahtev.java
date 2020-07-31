package model;

public class Zahtev {

    private int id;
    private String naziv, opis;
    private boolean jeOdobren, jeObradjen;
    private RegistrovaniKorisnik podnosilacZahteva;
    private Recenzija recenzija;

    public Zahtev(){};
    public Zahtev(int id, String naziv, String opis, boolean jeOdobren, boolean jeObradjen, RegistrovaniKorisnik podnosilacZahteva, Recenzija recenzija) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.jeOdobren = jeOdobren;
        this.jeObradjen = jeObradjen;
        this.podnosilacZahteva = podnosilacZahteva;
        this.recenzija = recenzija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isJeOdobren() {
        return jeOdobren;
    }

    public void setJeOdobren(boolean jeOdobren) {
        this.jeOdobren = jeOdobren;
    }

    public boolean isJeObradjen() {
        return jeObradjen;
    }

    public void setJeObradjen(boolean jeObradjen) {
        this.jeObradjen = jeObradjen;
    }

    public RegistrovaniKorisnik getPodnosilacZahteva() {
        return podnosilacZahteva;
    }

    public void setPodnosilacZahteva(RegistrovaniKorisnik podnosilacZahteva) {
        this.podnosilacZahteva = podnosilacZahteva;
    }

    public Recenzija getRecenzija() {
        return recenzija;
    }

    public void setRecenzija(Recenzija recenzija) {
        this.recenzija = recenzija;
    }

    @Override
    public String toString() {
        return "Zahtev{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", jeOdobren=" + jeOdobren +
                ", jeObradjen=" + jeObradjen +
                ", podnosilacZahteva=" + podnosilacZahteva +
                ", recenzija=" + recenzija +
                '}';
    }
}
