package kontroler;


import dao.KorisnickiNalogDAO;
import dao.RegistrovaniKorisnikDAO;
import model.KorisnickiNalog;
import model.Korisnik;
import model.RegistrovaniKorisnik;
import model.enums.TipKorisnika;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrovaniKorisnikKON {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static Korisnik proslediPodatkePrijave(String korIme, String sifra) throws Exception {

        if (korIme.equals("") || sifra.equals("")) throw new Exception(String.valueOf(1));

        return KorisnickiNalogDAO.getPrijavljeniKorisnik(korIme, sifra);
    }

    /**
     * Prima podatke, proverava ih i kreira nalog i registrovanog korisnika
     * <p>
     * param: ime, prezime, email, telefon, godina rodjenja, korisnicko ime, lozinka
     * return: true - Ako je uspesan upis
     */
    public static boolean proslediPodatkeRegistarcije(String ime, String prezime, String email,
                                                      String telefon, String godina, String korIme, String sifra) throws Exception {
        //Ukoliko je nesto od polja prazno
        if (ime.equals("") || prezime.equals("") || email.equals("") ||
                telefon.equals("") || godina.equals("") || korIme.equals("") || sifra.equals(""))
            throw new Exception(String.valueOf(1));

        //Ako nije dobar format datuma
        Date godinaRodjenja = null;
        try {
            godinaRodjenja = sdf.parse(godina);
        } catch (Exception ex) {
            throw new Exception(String.valueOf(2));
        }

        //Ako nalog vec postoji sa tim korisnickim imenom
        KorisnickiNalog nalog = null;
        try {
            nalog = KorisnickiNalogDAO.insertValues(korIme, sifra, TipKorisnika.REGISTROVANI);
        } catch (Exception ex) {
            throw new Exception(String.valueOf(3));
        }

        //Formiranje korisnika zbog upisa u bazu
        RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik(ime, prezime, email, telefon, nalog, godinaRodjenja);
        RegistrovaniKorisnikDAO.insert(korisnik);

        return true;
    }

}
