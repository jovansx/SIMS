package dao;

import model.*;

import model.enums.TipIzvodjenja;
import util.FConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IzvodjenjeDAO {

    public static Izvodjenje getIzvodjenje(Integer id){
        Izvodjenje izvodjenje=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,vremeIzvodjenja,trajanje,tipIzvodjenja, brojPristupa, brojGlasova, " +
                            "ukupnoPristupa, idMesta, obrisano from Izvodjenje where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                if(!rs.getBoolean(9)){
                    izvodjenje=new Izvodjenje();
                    izvodjenje.setId(rs.getInt(1));
                    izvodjenje.setVremeIzvodjenja(rs.getDate(2));
                    izvodjenje.setTrajanje(rs.getInt(3));
                    izvodjenje.setTipIzvodjenja(TipIzvodjenja.valueOf(rs.getString(4)));
                    izvodjenje.setBrPristupa(rs.getInt(5));
                    izvodjenje.setBrGlasova(rs.getInt(6));
                    izvodjenje.setUkupnoPrisupa(rs.getInt(7));
                    izvodjenje.setMestoIzvodjenja(MestoIzvodjenjaDAO.getMestoIzvodjenja(rs.getInt(8)));
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjenje;
    }
    public static List<Izvodjenje> getIzvodjenja(){
        List<Izvodjenje> izvodjenja = new ArrayList<>();
        Izvodjenje izvodjenje=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,vremeIzvodjenja,trajanje,tipIzvodjenja, brojPristupa, brojGlasova, " +
                            "ukupnoPristupa, idMesta, obrisano from Izvodjenje where id=?");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(!rs.getBoolean(9)){
                    izvodjenje=new Izvodjenje();
                    izvodjenje.setId(rs.getInt(1));
                    izvodjenje.setVremeIzvodjenja(rs.getDate(2));
                    izvodjenje.setTrajanje(rs.getInt(3));
                    izvodjenje.setTipIzvodjenja(TipIzvodjenja.valueOf(rs.getString(4)));
                    izvodjenje.setBrPristupa(rs.getInt(5));
                    izvodjenje.setBrGlasova(rs.getInt(6));
                    izvodjenje.setUkupnoPrisupa(rs.getInt(7));
                    izvodjenje.setMestoIzvodjenja(MestoIzvodjenjaDAO.getMestoIzvodjenja(rs.getInt(8)));
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjenja;

    }
    //Trazenje izvodjaca koja su ucestovala u izvodjenju
    public static List<Izvodjac> getizvodjaci(Integer id){
        Izvodjac izvodjac =null;
        List<Izvodjac> izvodjaci=new ArrayList<Izvodjac>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select idIzvodjaca, idIzvodjenja from IzvodjacIzvodi where idIzvodjenja=?");
            ps.setInt(2, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                izvodjac=IzvodjacDAO.getIzvodjac(rs.getInt(1));
                izvodjaci.add(izvodjac);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjaci;
    }
    //Trazenje muzickih dela koja se izvode u izvodjenju
    public static List<MuzickoDelo> getMuzickaDela(Integer id){
        List<MuzickoDelo> dela=new ArrayList<>();
        MuzickoDelo delo= null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select idIzvodjenja, idMuzickogDela from IzvodjenjaMuzickogDela where idIzvodjenja=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                delo=MuzickoDeloDAO.getMuzickoDelo(rs.getInt(2));
                dela.add(delo);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dela;
    }
    //Trazenje recenzija za izvodjenje
    public static List<Recenzija> getRecenzije(Integer id){
        List<Recenzija> recenzije=new ArrayList<>();
        Recenzija recenzija=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id, obrisano, ocena, komentar, idMuzickogDela, idIzvodjenja, " +
                            "idKorisnika, idUrednika from Recenzija where idIzvodjenja=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(!rs.getBoolean(2)){
                    recenzija.setId(rs.getInt(1));
                    recenzija.setOcena(rs.getInt(3));
                    recenzija.setKomentar(rs.getString(4));
                    recenzija.setMuzickoDelo(MuzickoDeloDAO.getMuzickoDelo(rs.getInt(5)));
                    recenzija.setAutorRecenzije(RegistrovaniKorisnikDAO.getRegistrovaniKorisnik(rs.getInt(7)));
                    recenzija.setUrednik(UrednikDAO.getUrednikPoId(rs.getInt(8)));
                    recenzije.add(recenzija);
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recenzije;
    }
    //Insert
    public static void insert(Izvodjenje izvodjenje) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into Izvodjenje(id, obrisano, vremeIzvodjenja, trajanje, tipIzvodjenja, " +
                        "brojPristupa, brojGlasova, ukupnoPristupa, pttBrojMesta) values (?,?,?,?,?,?,?,?,?)");
        ps.setInt(1, izvodjenje.getId());
        ps.setBoolean(2, false);
        if(izvodjenje.getVremeIzvodjenja()!=null) ps.setDate(3, (Date) izvodjenje.getVremeIzvodjenja()); else ps.setNull(3, Types.DATE);
        ps.setInt(4, izvodjenje.getTrajanje());
        if(izvodjenje.getTipIzvodjenja()!=null) ps.setString(5, izvodjenje.getTipIzvodjenja().toString()); else ps.setNull(5,Types.VARCHAR);
        ps.setInt(6, izvodjenje.getBrPristupa());
        ps.setInt(7, izvodjenje.getBrGlasova());
        ps.setInt(8, izvodjenje.getUkupnoPrisupa());
        if(izvodjenje.getMestoIzvodjenja()!=null) ps.setInt(9, izvodjenje.getMestoIzvodjenja().getPttBroj()); else ps.setNull(9, Types.INTEGER);
        ps.executeUpdate();
        ps.close();
    }
    //Update
    public static void update(Izvodjenje izvodjenje) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Izvodjenje set id=?, obrisano=?, vremeIzvodjenja=?, trajanje=?, tipIzvodjenja=?, " +
                        "brojPristupa=?, brojGlasova=?, ukupnoPristupa=?, pttBrojMesta=?");
        ps.setInt(1, izvodjenje.getId());
        ps.setBoolean(2, false);
        if(izvodjenje.getVremeIzvodjenja()!=null) ps.setDate(3, (Date) izvodjenje.getVremeIzvodjenja()); else ps.setNull(3, Types.DATE);
        ps.setInt(4, izvodjenje.getTrajanje());
        if(izvodjenje.getTipIzvodjenja()!=null) ps.setString(5, izvodjenje.getTipIzvodjenja().toString()); else ps.setNull(5,Types.VARCHAR);
        ps.setInt(6, izvodjenje.getBrPristupa());
        ps.setInt(7, izvodjenje.getBrGlasova());
        ps.setInt(8, izvodjenje.getUkupnoPrisupa());
        if(izvodjenje.getMestoIzvodjenja()!=null) ps.setInt(9, izvodjenje.getMestoIzvodjenja().getPttBroj()); else ps.setNull(9, Types.INTEGER);
        ps.executeUpdate();
        ps.close();
    }
    //Delete
    public static void delete(Izvodjenje izvodjenje) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Izvodjenje set obrisano=true where id=?");
        ps.setInt(1, izvodjenje.getId());
        ps.executeUpdate();
        ps.close();

    }
    //ToTable
    public static String[][] toTableData(List<Izvodjenje> izvodjenja){
        String[][] result=new String[izvodjenja.size()][7];
        for(int i=0;i<izvodjenja.size();i++){
            result[i][0]= String.valueOf(izvodjenja.get(i).getId());
            result[i][1]=izvodjenja.get(i).getTipIzvodjenja().toString();
            result[i][2]=izvodjenja.get(i).getMestoIzvodjenja().toString();
            result[i][3]=izvodjenja.get(i).getVremeIzvodjenja().toString();
            result[i][4]= String.valueOf(izvodjenja.get(i).getBrGlasova());
            result[i][5]= String.valueOf(izvodjenja.get(i).getBrPristupa());
            result[i][6]= String.valueOf(izvodjenja.get(i).getUkupnoPrisupa());
        }
        return result;
    }
}
