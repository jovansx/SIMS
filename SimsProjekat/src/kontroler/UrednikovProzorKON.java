package kontroler;

import dao.IzvodjenjeDAO;
import dao.MuzickoDeloDAO;
import dao.RecenzijaDAO;
import dao.UrednikDAO;
import model.Izvodjenje;
import model.MuzickoDelo;
import model.Recenzija;
import model.Urednik;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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

    public static boolean odluka(boolean value,Recenzija r) throws SQLException {
        if(value){
            r.setOdobreno(true);
            r.setObradjeno(true);
            r.getMuzickoDelo().dodajRecenziju(r);
        }else{
            r.setOdobreno(false);
            r.setObradjeno(true);
        }
        RecenzijaDAO.update(r);
        return true;
    }

    public static boolean opisiMuzickoDelo(MuzickoDelo md) throws SQLException {
        MuzickoDeloDAO.update(md);
        return true;
    }

    public static boolean dodajIzvodjenje(int id, Izvodjenje izvodjenje) throws SQLException {
        MuzickoDelo md = MuzickoDeloDAO.getMuzickoDelo(id);
        if(Objects.isNull(md.getListaIzvodjenja())){
            ArrayList<Izvodjenje> lista = new ArrayList<>();
            lista.add(izvodjenje);
            md.setListaIzvodjenja(lista);
        }else{
            ArrayList<Izvodjenje> lista = (ArrayList<Izvodjenje>) md.getListaIzvodjenja();
            lista.add(izvodjenje);
            md.setListaIzvodjenja(lista);
        }
        IzvodjenjeDAO.insert(izvodjenje);
        return true;
    }

}
