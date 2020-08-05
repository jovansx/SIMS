package dao;

import model.Administrator;
import model.KorisnickiNalog;
import model.RegistrovaniKorisnik;
import model.Urednik;
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
                if (rs.getString(4).equals("ADMINISTRATOR")){
                    korisnik.setOsoba(getAdmin(rs.getInt(1)));
                }
                else if (rs.getString(4).equals("UREDNIK")){
                    korisnik.setOsoba(getUrednik(rs.getInt(1)));
                }
                else if(rs.getString(4).equals("REGISTROVANI")){
                    korisnik.setOsoba(getRegistrovaniKorisnik(rs.getInt(1)));
                }
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
    //Nadji administratora
    public static Administrator getAdmin(int id){
        Administrator admin=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email, kontaktTelefon, godinaRodjenja, idNaloga, " +
                            "obrisano from administrator where idNaloga=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                if(rs.getBoolean(8)){
                    admin=new Administrator();
                    admin.setId(rs.getInt(1));
                    admin.setIme(rs.getString(2));
                    admin.setPrezime(rs.getString(3));
                    admin.setEmail(rs.getString(4));
                    admin.setKontaktTelefon(rs.getString(5));
                    admin.setGodinaRodjenja(rs.getDate(6));
                    admin.setIzlazeReklame(AdministratorDAO.getReklame(id));
                    admin.setListaZahteva(AdministratorDAO.getZahteve(id));
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
    //Nadji urednika
    public static Urednik getUrednik(int id){
        Urednik urednik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id, ime, prezime, email, kontaktTelefon, godinaRodjenja, idNaloga " +
                            "from Urednik where idNaloga=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                urednik = new Urednik(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), null, rs.getDate(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return urednik;
    }
    //Nadji registrovanogKorisnika
    public static RegistrovaniKorisnik getRegistrovaniKorisnik(int id) {
        RegistrovaniKorisnik korisnik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email,kontaktTelefon,godinaRodjenja,idNaloga,jeVidljiv from RegistrovaniKorisnik where idNaloga=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                korisnik=new RegistrovaniKorisnik();
                korisnik.setId(rs.getInt(1));
                korisnik.setIme(rs.getString(2));
                korisnik.setPrezime(rs.getString(3));
                korisnik.setEmail(rs.getString(4));
                korisnik.setKontaktTelefon(rs.getString(5));
                korisnik.setGodinaRodjenja(rs.getDate(6));
                korisnik.setJeVidljiv(rs.getBoolean(8));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return korisnik;
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
