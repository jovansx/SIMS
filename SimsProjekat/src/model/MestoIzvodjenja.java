package model;

public class MestoIzvodjenja {
    private int pttBroj;
    private String drzava, nazivMesta;

    public MestoIzvodjenja(int pttBroj, String drzava, String nazivMesta) {
        this.pttBroj = pttBroj;
        this.drzava = drzava;
        this.nazivMesta = nazivMesta;
    }

    public int getPttBroj() {
        return pttBroj;
    }

    public void setPttBroj(int pttBroj) {
        this.pttBroj = pttBroj;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    @Override
    public String toString() {
        return "MestoIzvodjenja{" +
                "pttBroj=" + pttBroj +
                ", drzava='" + drzava + '\'' +
                ", nazivMesta='" + nazivMesta + '\'' +
                '}';
    }
}
