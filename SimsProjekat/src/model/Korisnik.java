package model;

import java.util.Date;

public abstract class Korisnik {

    private int id;
    private String ime, prezime, email, kontaktTelefon, korisnickoIme, lozicka;
    private Date godinaRodjenja;

    public Korisnik(int id, String ime, String prezime, String email, String kontaktTelefon, String korisnickoIme, String lozicka, Date godinaRodjenja) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.kontaktTelefon = kontaktTelefon;
        this.korisnickoIme = korisnickoIme;
        this.lozicka = lozicka;
        this.godinaRodjenja = godinaRodjenja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozicka() {
        return lozicka;
    }

    public void setLozicka(String lozicka) {
        this.lozicka = lozicka;
    }

    public Date getGodinaRodjenja() {
        return godinaRodjenja;
    }

    public void setGodinaRodjenja(Date godinaRodjenja) {
        this.godinaRodjenja = godinaRodjenja;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", email='" + email + '\'' +
                ", kontaktTelefon='" + kontaktTelefon + '\'' +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozicka='" + lozicka + '\'' +
                ", godinaRodjenja=" + godinaRodjenja +
                '}';
    }
}
