package model;

import java.util.Date;

public abstract class Korisnik {

    private int id;
    private String ime, prezime, email, kontaktTelefon;
    private Date godinaRodjenja;
    private KorisnickiNalog nalog;

    public Korisnik(){}
    public Korisnik(int id, String ime, String prezime, String email, String kontaktTelefon, Date godinaRodjenja, KorisnickiNalog nalog) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.kontaktTelefon = kontaktTelefon;
        this.godinaRodjenja = godinaRodjenja;
        this.nalog=nalog;
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

    public KorisnickiNalog getNalog(){ return this.nalog; }

    public void setNalog(KorisnickiNalog nalog) {this.nalog=nalog; }

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
                ", nalog='" + nalog + '\'' +
                ", godinaRodjenja=" + godinaRodjenja +
                '}';
    }
}
