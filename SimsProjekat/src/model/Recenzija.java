package model;

public class Recenzija {

    private int id, ocena;
    private String komentar;
    private RegistrovaniKorisnik autorRecenzije;
    private Izvodjenje izvodnjenje;
    private Urednik urednik;
    private MuzickoDelo delo;

    public Recenzija(int id, int ocena, String komentar, RegistrovaniKorisnik autorRecenzije, Izvodjenje izvodnjenje, Urednik urednik,MuzickoDelo delo) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
        this.autorRecenzije = autorRecenzije;
        this.izvodnjenje = izvodnjenje;
        this.urednik = urednik;
        this.delo = delo;
    }
    public Recenzija(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public RegistrovaniKorisnik getAutorRecenzije() {
        return autorRecenzije;
    }

    public void setAutorRecenzije(RegistrovaniKorisnik autorRecenzije) {
        this.autorRecenzije = autorRecenzije;
    }

    public Izvodjenje getIzvodnjenje() {
        return izvodnjenje;
    }

    public void setIzvodnjenje(Izvodjenje izvodnjenje) {
        this.izvodnjenje = izvodnjenje;
    }

    public Urednik getUrednik() {
        return urednik;
    }

    public void setUrednik(Urednik urednik) {
        this.urednik = urednik;
    }

    public MuzickoDelo getMuzickoDelo() {
        return delo;
    }

    public void setMuzickoDelo(MuzickoDelo delo) {
        this.delo = delo;
    }

    @Override
    public String toString() {
        return "Recenzija{" +
                "id=" + id +
                ", ocena=" + ocena +
                ", komentar='" + komentar + '\'' +
                ", autorRecenzije=" + autorRecenzije +
                ", izvodnjenje=" + izvodnjenje +
                ", delo="+ delo+
                '}';
    }
}
