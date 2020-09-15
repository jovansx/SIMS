package kontroler;

import gui.elementi.ElementPrikazaPlejlisti;
import model.RegistrovaniKorisnik;

public class PlejlistaKON {

    public static RegistrovaniKorisnik korisnik;
    public static ElementPrikazaPlejlisti prikazListe;

    public static void resetujListu(){
        prikazListe=null;
    }

    public static void postaviListu(ElementPrikazaPlejlisti e){
        prikazListe=e;
    }

    public static ElementPrikazaPlejlisti getLista(){
        return prikazListe;
    }
    public static void resetujKorisnika(){
        korisnik=null;
    }

    public static void postaviKorisnika(RegistrovaniKorisnik k){
        korisnik=k;
    }

    public static RegistrovaniKorisnik getKorisnik(){
        return korisnik;
    }

}
