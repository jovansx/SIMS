package kontroler;

import dao.*;
import gui.sorters.ToplistaSorter;
import model.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UrednikovProzorKON {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static boolean provera(Urednik urednik, String ime, String prezime, String email,
                                  String telefon, Date godina) throws Exception {
        //Ukoliko je neko od polja prazno
        if (ime.equals("") || prezime.equals("") || email.equals("") ||
                telefon.equals("") || godina.equals(""))
            throw new Exception(String.valueOf(1));
        urednik.setIme(ime);
        urednik.setPrezime(prezime);
        urednik.setEmail(email);
        urednik.setKontaktTelefon(telefon);
        urednik.setGodinaRodjenja(godina);
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
                KorisnickiNalogDAO.update(urednik.getNalog());
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
            if(!Objects.isNull(r.getIzvodnjenje())){
                if(!Objects.isNull(r.getIzvodnjenje().getListaRecenzija())){
                    r.getIzvodnjenje().dodajRecenziju(r);
                }else{
                    ArrayList<Recenzija> lista = new ArrayList<>();
                    lista.add(r);
                    r.getIzvodnjenje().setListaRecenzija(lista);
                }
            }
            if(!Objects.isNull(r.getMuzickoDelo())) {
                if (!Objects.isNull(r.getMuzickoDelo().getListaRecenzija())) {
                    r.getMuzickoDelo().dodajRecenziju(r);
                } else {
                    ArrayList<Recenzija> lista = new ArrayList<>();
                    lista.add(r);
                    r.getMuzickoDelo().setListaRecenzija(lista);
                }
            }
        }else{
            r.setOdobreno(false);
            r.setObradjeno(true);
        }
        RecenzijaDAO.update(r);
        return true;
    }

    public static boolean opisiMuzickoDelo(MuzickoDelo md) throws SQLException {
        MuzickoDeloDAO.update(md);
        for(int i =0;i<md.getListaZanrova().size();i++){
            ZanrDAO.insert2(md.getListaZanrova().get(i).getNazivZanra(),md);
        }
        for(int i =0;i<md.getListaUcesnika().size();i++){
            UcesnikDAO.insert2(md.getListaUcesnika().get(i).getId(),md);
        }
        return true;
    }

    public static boolean opisiIzvodjaca(Izvodjac izvodjac) throws SQLException {
        IzvodjacDAO.update(izvodjac);
        return true;
    }
    public static boolean opisiZanr(Zanr zanr) throws SQLException {
        ZanrDAO.update(zanr);
        return true;
    }
    public static boolean opisiUcesnika(Ucesnik ucesnik) throws SQLException {
        UcesnikDAO.update(ucesnik);
        for(int i =0;i<ucesnik.getListaMuzickihDela().size();i++){
            UcesnikDAO.insert2(ucesnik.getId(),ucesnik.getListaMuzickihDela().get(i));
        }
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
        IzvodjenjeDAO.insert2(izvodjenje,md.getId());
        for(int i =0;i<izvodjenje.getListaIzvodjaca().size();i++){
            IzvodjacDAO.insert2(izvodjenje.getListaIzvodjaca().get(i),izvodjenje);
        }
        return true;
    }
    public static List<Izvodjenje> getListaZaGlasanje(int month,int year){
        int m = month;
        if(month == 1){
            m = 12;
        }else {
            m = month-1;
        }
        List<Izvodjenje> lista = new ArrayList<>();
        for(Izvodjenje i :IzvodjenjeDAO.getIzvodjenja()){
            if(i.getVremeIzvodjenja().getMonth() == m &&
                    i.getVremeIzvodjenja().getYear()+1900 == year){
                lista.add(IzvodjenjeDAO.getIzvodjenje(i.getId()));
            }
        }
        lista.sort(new ToplistaSorter(true));
        return lista;
    }

    public static void glasaj(Izvodjenje izvodjenje) throws SQLException {
        int br = izvodjenje.getBrGlasova();
        izvodjenje.setBrGlasova(br+1);
        IzvodjenjeDAO.update(izvodjenje);
    }
}
