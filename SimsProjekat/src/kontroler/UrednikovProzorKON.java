package kontroler;

import dao.UrednikDAO;
import model.Urednik;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UrednikovProzorKON {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static boolean provera(Urednik urednik, String ime, String prezime, String email,
                                  String telefon, String godina) throws Exception {
        //Ukoliko je neko od polja prazno
        if (ime.equals("") || prezime.equals("") || email.equals("") ||
                telefon.equals("") || godina.equals(""))
            throw new Exception(String.valueOf(1));

        //Ako nije dobar format datuma
        Date godinaRodjenja = null;
        try {
            godinaRodjenja = sdf.parse(godina);
        } catch (Exception ex) {
            throw new Exception(String.valueOf(2));
        }
        urednik.setIme(ime);
        urednik.setPrezime(prezime);
        urednik.setEmail(email);
        urednik.setKontaktTelefon(telefon);
        urednik.setGodinaRodjenja(godinaRodjenja);
        UrednikDAO.update(urednik);
        return true;
    }
    public static boolean proveraLozinke(Urednik urednik, String l, String l1, String l2) throws Exception {

        //Ukoliko je neko od polja prazno
        if (l.equals("") || l1.equals("") || l2.equals(""))
            throw new Exception(String.valueOf(1));

        if (urednik.getNalog().getLozinka().equals(l)) {
            if (l1.equals(l2)) {
                urednik.getNalog().setLozinka(l1);
                UrednikDAO.update(urednik);

            } else {
                throw new Exception(String.valueOf(2));
                // ne poklapaju se nove lozinke
            }
        } else {
            throw new Exception(String.valueOf(1));
            // nije dobra stara lozinka
        }

        return true;
    }
}
