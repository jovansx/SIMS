package model;

public class Recenzija {

    private int id, ocena;
    private String komentar;
    private RegistovaniKorisnik autorRecenzije;
    private Izvodnjenje izvodnjenje;
    private Urednik urednik;

    public Recenzija(int id, int ocena, String komentar, RegistovaniKorisnik autorRecenzije, Izvodnjenje izvodnjenje, Urednik urednik) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
        this.autorRecenzije = autorRecenzije;
        this.izvodnjenje = izvodnjenje;
        this.urednik = urednik;
    }

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

    public RegistovaniKorisnik getAutorRecenzije() {
        return autorRecenzije;
    }

    public void setAutorRecenzije(RegistovaniKorisnik autorRecenzije) {
        this.autorRecenzije = autorRecenzije;
    }

    public Izvodnjenje getIzvodnjenje() {
        return izvodnjenje;
    }

    public void setIzvodnjenje(Izvodnjenje izvodnjenje) {
        this.izvodnjenje = izvodnjenje;
    }

    public Urednik getUrednik() {
        return urednik;
    }

    public void setUrednik(Urednik urednik) {
        this.urednik = urednik;
    }

    @Override
    public String toString() {
        return "Recenzija{" +
                "id=" + id +
                ", ocena=" + ocena +
                ", komentar='" + komentar + '\'' +
                ", autorRecenzije=" + autorRecenzije +
                ", izvodnjenje=" + izvodnjenje +
                '}';
    }
}
