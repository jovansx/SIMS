package kontroler;

import dao.*;
import model.*;

import java.lang.reflect.GenericArrayType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminovProzorKON {
    private static HashMap<Zadatak, Integer> zadaci;

    public static HashMap<Zadatak, Integer> getZadaci(){
        return zadaci;
    }

    public static void resetZadatke(){
        zadaci=new HashMap<Zadatak, Integer>();
    }

    public static void dodajZadatak(Zadatak z, Integer s){
        if(zadaci.isEmpty()){
            zadaci=new HashMap<Zadatak, Integer>();
            zadaci.put(z, s);
        }
        else{
            zadaci.put(z, s);
        }
    }

    /** Upisuje zadatke u bazu */
    public static void upisiZadatke() throws SQLException {
        for(Map.Entry<Zadatak, Integer> entry: zadaci.entrySet()){
            ZadatakDAO.insert(entry.getKey(), entry.getValue());
        }
    }
    /** Dodjeljuje urednicima njihove recenzije */
    public static ArrayList<Urednik> getUrednici(){
        ArrayList<Urednik> urednici= (ArrayList<Urednik>) UrednikDAO.getUrednike();
        for(Urednik u: urednici){
            u.setListaRecenzija((ArrayList<Recenzija>) RecenzijaDAO.getRecenzijeUrednika(u.getId()));
        }
        return urednici;
    }
    /** Dobavljanje prosjecne ocjene za svako djelo i izvodjenje koje je urednik recenzirao*/
    public static HashMap<Recenzija, Double> getProsjecneOcjeneKorisnika(ArrayList<Recenzija> recenzije){
        Double d=0.0;
        HashMap<Recenzija, Double> prosjecneOcjene=new HashMap<>();

        for(Recenzija r: recenzije){
            if(r.getMuzickoDelo()==null){
                prosjecneOcjene.put(r, RecenzijaDAO.getProsjecnaOcjenaPoIdIzvodjenja(r.getIzvodnjenje().getId()));
            }
            else {
                prosjecneOcjene.put(r, RecenzijaDAO.getProsjecnaOcjenaPoIdDela(r.getMuzickoDelo().getId()));
            }
        }

        return prosjecneOcjene;
    }

    public static String[] getNaziviUrednika(){
        ArrayList<Urednik> urednici= (ArrayList<Urednik>) UrednikDAO.getUrednike();
        String naziv="";
        String[] listaUrednika = new String[urednici.size()];
        int i=0;
        for(Urednik u: urednici){
            naziv=u.getId()+" "+u.getIme()+" "+u.getPrezime()+" ("+u.getNalog().getKorisnickoIme()+")";
            listaUrednika[i]=naziv;
            i++;
        }
        return listaUrednika;
    }

    public static int provjeriLozinku(Administrator ad, String l, String n, String n1){
        int retVal = -1;
        KorisnickiNalog a=KorisnickiNalogDAO.getNalogAdmina(ad.getId());

        if(a.getLozinka().equals(l) && n.equals(n1)){
            retVal = 0;
        }
        if(a.getLozinka().equals(l) && !n.equals(n1)){
            retVal = 1;
        }
        if(!a.getLozinka().equals(l) && n.equals(n1)){
            retVal = 2;
        }
        if(!a.getLozinka().equals(l) && !n.equals(n1)){
            retVal=3;
        }
        return retVal;
    }

}
