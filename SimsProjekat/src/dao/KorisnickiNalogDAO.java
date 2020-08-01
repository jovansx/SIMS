package dao;

import model.KorisnickiNalog;
import model.enums.TipKorisnika;
import util.FConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KorisnickiNalogDAO {
    public static KorisnickiNalog getNalog(Integer id){
        KorisnickiNalog korisnik=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,korisnickoIme,lozinka, tipKorisnika from KorisnickiNalog where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                korisnik = new KorisnickiNalog();
                korisnik.setId(rs.getInt(1));
                korisnik.setKorisnickoIme(rs.getString(2));
                korisnik.setLozinka(rs.getString(3));
                korisnik.setKorisnik(TipKorisnika.valueOf(rs.getString(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return korisnik;
    }
    public static List<KorisnickiNalog> getNalozi(){
        List<KorisnickiNalog> nalozi=new ArrayList<>();
        KorisnickiNalog korisnik=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,korisnickoIme,lozinka, tipKorisnika from KorisnickiNalog");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                korisnik = new KorisnickiNalog();
                korisnik.setId(rs.getInt(1));
                korisnik.setKorisnickoIme(rs.getString(2));
                korisnik.setLozinka(rs.getString(3));
                korisnik.setKorisnik(TipKorisnika.valueOf(rs.getString(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nalozi;
    }
    //Insert
    public static void insert(KorisnickiNalog nalog) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into KorisnickiNalog(id, korisnickoIme, lozinka, tipKorisnika) values (?,?,?,?)");
        ps.setInt(1, nalog.getId());
        if(nalog.getKorisnickoIme()!=null) ps.setString(2, nalog.getKorisnickoIme()); else ps.setNull(2, Types.VARCHAR);
        if(nalog.getLozinka()!=null) ps.setString(3, nalog.getLozinka()); else ps.setNull(3, Types.VARCHAR);
        if(nalog.getKorisnik()!=null) ps.setString(4, nalog.getKorisnik().toString()); else ps.setNull(4,Types.VARCHAR);
        ps.executeUpdate();
        ps.close();
    }
    //Update
    public static void update(KorisnickiNalog nalog) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update KorisnickiNalog set id=?, korisnickoIme=?, lozinka=?, tipKorisnika=?");
        ps.setInt(1, nalog.getId());
        if(nalog.getKorisnickoIme()!=null) ps.setString(2, nalog.getKorisnickoIme()); else ps.setNull(2, Types.VARCHAR);
        if(nalog.getLozinka()!=null) ps.setString(3, nalog.getLozinka()); else ps.setNull(3, Types.VARCHAR);
        if(nalog.getKorisnik()!=null) ps.setString(4, nalog.getKorisnik().toString()); else ps.setNull(4,Types.VARCHAR);
        ps.executeUpdate();
        ps.close();
    }
    //ToTable
    public static String[][] toTableData(List<KorisnickiNalog> nalozi){
        String[][] result=new String[nalozi.size()][4];
        for(int i=0;i<nalozi.size();i++){
            result[i][0]= String.valueOf(nalozi.get(i).getId());
            result[i][1]=nalozi.get(i).getKorisnickoIme();
            result[i][2]=nalozi.get(i).getLozinka();
            result[i][3]=nalozi.get(i).getKorisnik().toString();
        }
        return result;
    }
}
