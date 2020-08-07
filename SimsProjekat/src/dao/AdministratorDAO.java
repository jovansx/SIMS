package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.*;
import util.FConnection;

public class AdministratorDAO {
    public static Administrator getAdministrator(Integer id){
        Administrator admin=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email, kontaktTelefon, godinaRodjenja, idNaloga, obrisano from administrator where id=?");
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
                    admin.setNalog(KorisnickiNalogDAO.getNalog(rs.getInt(7)));
                    admin.setIzlazeReklame(getReklame(id));
                    admin.setListaZahteva(getZahteve(id));
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public static List<Reklama> getReklame(Integer id){
        Reklama reklama=null;
        List<Reklama> reklame = new ArrayList<Reklama>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,naziv,textReklame,cena, brojPristupa, datumIsteka, idAdmina,url obrisano from Reklama where idAdmina=?");
            ps.setInt(7, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(!rs.getBoolean(7)){
                    reklama=new Reklama();
                    reklama.setId(rs.getInt(1));
                    reklama.setNaziv(rs.getString(2));
                    reklama.setText(rs.getString(3));
                    reklama.setCena(rs.getDouble(4));
                    reklama.setBrojPristupa(rs.getInt(5));
                    reklama.setDatumIsteka(rs.getDate(6));
                    reklama.setUrl(rs.getString(7));
                    reklame.add(reklama);
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reklame;
    }

    public static Administrator getPoIdNaloga(KorisnickiNalog nalog) {
        Administrator korisnik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email,kontaktTelefon,godinaRodjenja from Administrator where idNaloga=? and obrisano=false");
            ps.setInt(1, nalog.getId());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                korisnik=new Administrator();
                korisnik.setId(rs.getInt(1));
                korisnik.setIme(rs.getString(2));
                korisnik.setPrezime(rs.getString(3));
                korisnik.setEmail(rs.getString(4));
                korisnik.setKontaktTelefon(rs.getString(5));
                korisnik.setGodinaRodjenja(rs.getDate(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(korisnik!=null) {korisnik.setNalog(nalog);}

        return korisnik;
    }

    public static List<Zahtev> getZahteve(Integer id){
        Zahtev zahtev=null;
        List<Zahtev> zahtevi = new ArrayList<Zahtev>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,naziv,opis, jeOdobren, jeObradjen, idAdmina, idKorisnika, idRecenzije, obrisano from Zahtev where idAdmina=?");
            ps.setInt(6, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(!rs.getBoolean(6)){
                    zahtev=new Zahtev();
                    zahtev.setId(rs.getInt(1));
                    zahtev.setNaziv(rs.getString(2));
                    zahtev.setOpis(rs.getString(3));
                    zahtev.setJeOdobren(rs.getBoolean(4));
                    zahtev.setJeObradjen(rs.getBoolean(5));
                    //Treba da se doda jos za registovanog korisnika set
                    zahtevi.add(zahtev);
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zahtevi;
    }
    public static List<Administrator> getListaAdministratora(){
        List<Administrator> administratori = new ArrayList<Administrator>();
        Administrator admin=null;
        try{
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("select id, ime, prezime,email, kontaktTelefon, godinaRodjenja, idNaloga, obrisano from Administrator where id=?");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(!rs.getBoolean(8)){
                    admin = new Administrator();
                    admin.setId(rs.getInt(1));
                    admin.setIme(rs.getString(2));
                    admin.setPrezime(rs.getString(3));
                    admin.setEmail(rs.getString(4));
                    admin.setKontaktTelefon(rs.getString(5));
                    admin.setGodinaRodjenja(rs.getDate(6));
                    admin.setNalog(KorisnickiNalogDAO.getNalog(rs.getInt(7)));
                    admin.setListaZahteva(getZahteve(rs.getInt(1)));
                    admin.setIzlazeReklame(getReklame(rs.getInt(1)));
                }

            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return administratori;
    }

    public static void insert(Administrator admin) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into administrator(id, obrisano, ime, prezime, email, kontaktTelefon, godinaRodjena, idNaloga) values (?,?,?,?,?,?,?)");
        ps.setInt(1, admin.getId());
        ps.setBoolean(2, false);
        if(admin.getIme()!=null) ps.setString(3, admin.getIme()); else ps.setNull(3, Types.VARCHAR);
        if(admin.getPrezime()!=null) ps.setString(4, admin.getPrezime()); else ps.setNull(4, Types.VARCHAR);
        if(admin.getEmail()!=null) ps.setString(5, admin.getEmail()); else ps.setNull(5,Types.VARCHAR);
        if(admin.getKontaktTelefon()!=null) ps.setString(6, admin.getKontaktTelefon()); else ps.setNull(6, Types.VARCHAR);
        if(admin.getGodinaRodjenja()!=null) ps.setDate(7, (Date) admin.getGodinaRodjenja()); else ps.setNull(7, Types.DATE);
        if(admin.getNalog()!=null) ps.setInt(8, admin.getNalog().getId()); else ps.setNull(8, Types.INTEGER);
        ps.executeUpdate();
        ps.close();
    }
    public static void update(Administrator admin) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update administrator set id=?, ime=?, prezime=?, email=?, kontaktTelefon=?, godinaRodjena=?, idNaloga=?)");
        ps.setInt(1, admin.getId());
        if(admin.getIme()!=null) ps.setString(2, admin.getIme()); else ps.setNull(2, Types.VARCHAR);
        if(admin.getPrezime()!=null) ps.setString(3, admin.getPrezime()); else ps.setNull(3, Types.VARCHAR);
        if(admin.getEmail()!=null) ps.setString(4, admin.getEmail()); else ps.setNull(4,Types.VARCHAR);
        if(admin.getKontaktTelefon()!=null) ps.setString(5, admin.getKontaktTelefon()); else ps.setNull(5, Types.VARCHAR);
        if(admin.getGodinaRodjenja()!=null) ps.setDate(6, (Date) admin.getGodinaRodjenja()); else ps.setNull(6, Types.DATE);
        if(admin.getNalog()!=null) ps.setInt(7, admin.getNalog().getId()); else ps.setNull(7, Types.INTEGER);
        ps.executeUpdate();
        ps.close();
    }
    //delete
    public static void delete(Administrator admin) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update administrator set obrisano=true where id=?");
        ps.setInt(1, admin.getId());
        ps.executeUpdate();
        ps.close();

    }
    //ToTable
    public static String[][] toTableData(List<Administrator> admini){
        String[][] result=new String[admini.size()][6];
        for(int i=0;i<admini.size();i++){
            result[i][0]= String.valueOf(admini.get(i).getId());
            result[i][1]=admini.get(i).getIme();
            result[i][2]=admini.get(i).getPrezime();
            result[i][3]=admini.get(i).getEmail();
            result[i][4]=admini.get(i).getGodinaRodjenja().toString();
            result[i][5]=admini.get(i).getKontaktTelefon();
        }
        return result;
    }

}
