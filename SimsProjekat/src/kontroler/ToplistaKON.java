package kontroler;

import dao.IzvodjenjeDAO;
import dao.TopListaDAO;
import gui.sorters.ToplistaSorter;
import model.Izvodjenje;
import model.TopLista;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ToplistaKON {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    public static List<Izvodjenje> izglasana(int month, int year){
        int m = month;
        if(month == 1){
            m = 12;
        }else {
            m = month-1;
        }
        List<Izvodjenje> lista = new ArrayList<>();
        for(Izvodjenje i : IzvodjenjeDAO.getIzvodjenja()){
            if(i.getVremeIzvodjenja().getMonth()+1 == m &&
                    i.getVremeIzvodjenja().getYear()+1900 == year){
                lista.add(i);
            }
        }
        lista.sort(new ToplistaSorter(false));
        return lista;

    }
    public static List<Izvodjenje> izglasana1(int year){
        List<Izvodjenje> lista = new ArrayList<>();
        for(Izvodjenje i : IzvodjenjeDAO.getIzvodjenja()){
            if(i.getVremeIzvodjenja().getYear()+1900 == year){
                lista.add(i);
            }
        }
        lista.sort(new ToplistaSorter(false));
        return lista;

    }
    public static void upisi(TopLista t,int id) throws SQLException {
        for(Izvodjenje i: t.getListaIzvodjenja()){
            TopListaDAO.insert2(i,id);
        }
    }
    public static void napraviMesecnuToplistu(int month,int year) throws ParseException, SQLException {
        List<Izvodjenje> izv = izglasana(month,year);
        TopLista t = new TopLista();
        String s ="";
        if(month == 0 || month == 1 || month == 2 || month == 3|| month == 4||
        month == 5 || month == 6 || month == 7 || month == 8 || month == 9) {
            s = "01.0" + month + "." + year + ".";
        }else{
            s = "01." + month + "." + year + ".";
        }
        t.setDatumGlasanja(new java.sql.Date(sdf.parse(s).getTime()));
        for(Izvodjenje i: izv){
            t.dodajIzvodjenje(i);
        }
        TopListaDAO.insert(t);
        TopLista tt = TopListaDAO.getTopLista((Date) t.getDatumGlasanja());
        upisi(t,tt.getId());
    }
    public static void napraviGodisnjuToplistu(int year) throws ParseException, SQLException {
        List<Izvodjenje> izv = izglasana1(year);
        TopLista t = new TopLista();
        String s ="";
        s = "01.01." +year + ".";
        t.setDatumGlasanja(new java.sql.Date(sdf.parse(s).getTime()));
        for(Izvodjenje i: izv){
            t.dodajIzvodjenje(i);
        }
        TopListaDAO.insert(t);
        TopLista tt = TopListaDAO.getTopLista((Date) t.getDatumGlasanja());
        upisi(t,tt.getId());
    }
    public static List<TopLista> getTopliste(){
        List<TopLista> lista = TopListaDAO.getTopListe();
        return lista;
    }
}
