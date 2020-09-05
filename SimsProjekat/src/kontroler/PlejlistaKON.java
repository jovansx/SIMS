package kontroler;

import model.RegistrovaniKorisnik;

public class PlejlistaKON {

    public static RegistrovaniKorisnik korisnik;

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
