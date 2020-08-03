package model;

import model.enums.TipKorisnika;

public class KorisnickiNalog {
    private int id;
    private String korisnickoIme;
    private String lozinka;
    private TipKorisnika korisnik;
    private Korisnik osoba;

    public KorisnickiNalog(){}
    public KorisnickiNalog(int i, String ki, String l, TipKorisnika k, Korisnik o){
        this.id=i;
        this.korisnickoIme=ki;
        this.lozinka=l;
        this.korisnik=k;
        this.osoba=o;
    }

    public int getId(){
        return id;
    }
    public String getKorisnickoIme(){
        return korisnickoIme;
    }
    public String getLozinka(){
        return lozinka;
    }
    public TipKorisnika getKorisnik(){ return this.korisnik; }
    public Korisnik getOsoba(){return this.osoba;}
    public void setId(int id){
        this.id=id;
    }
    public void setKorisnickoIme(String korisnickoime){
        this.korisnickoIme=korisnickoime;
    }
    public void setLozinka(String lozinka){
        this.lozinka=lozinka;
    }
    public void setKorisnik(TipKorisnika korisnik){
        this.korisnik=korisnik;
    }
    public void setOsoba(Korisnik osoba){this.osoba=osoba;}
}

