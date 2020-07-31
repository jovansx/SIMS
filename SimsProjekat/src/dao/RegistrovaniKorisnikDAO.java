package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.RegistrovaniKorisnik;
import util.FConnection;

public class RegistrovaniKorisnikDAO {

    public static RegistrovaniKorisnik getRegistrovaniKorisnik(int id){
        RegistrovaniKorisnik korisnik=null;
        try {
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email,kontaktTelefon,godinaRodjenja,idNaloga,jeVidljiv from RegistrovaniKorisnik where id=? and obrisano=true");
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
                korisnik.setI(rs.getDate(6));
                korisnik.setGodinaRodjenja(rs.getDate(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return radnik;
    }

    public static FRadnik getRadnikSef(Integer id){
        FRadnik radnik=null;
        try {
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("select mbr,ime,prz,sef,plt,pre,god from radnik where mbr=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                radnik=new FRadnik();
                radnik.setMbr(rs.getInt(1));
                radnik.setIme(rs.getString(2));
                radnik.setPrezime(rs.getString(3));
                radnik.setSef(getRadnikBasic(rs.getInt(4)));
                radnik.setPlata(rs.getInt(5));
                radnik.setPremija(rs.getInt(6));
                radnik.setGodinaRodjenja(rs.getDate(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return radnik;
    }

    public static List<FRadnik> getListRadnikSef(){
        FRadnik radnik=null;
        List<FRadnik> result=new ArrayList<>();
        try {
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("select mbr,ime,prz,sef,plt,pre,god from radnik");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                radnik=new FRadnik();
                radnik.setMbr(rs.getInt(1));
                radnik.setIme(rs.getString(2));
                radnik.setPrezime(rs.getString(3));
                radnik.setSef(getRadnikBasic(rs.getInt(4)));
                radnik.setPlata(rs.getInt(5));
                radnik.setPremija(rs.getInt(6));
                radnik.setGodinaRodjenja(rs.getDate(7));
                result.add(radnik);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<FRadnik> getListRadnikSefSearch(String text){
        FRadnik radnik=null;
        List<FRadnik> result=new ArrayList<>();
        text = text.toLowerCase();
        try {
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("select mbr,ime,prz,sef,plt,pre,god from radnik where "
                            + "lower(to_char(mbr)) like '%'||?||'%' or "
                            + "lower(to_char(ime)) like '%'||?||'%' or "
                            + "lower(to_char(prz)) like '%'||?||'%' or "
                            + "lower(to_char(sef)) like '%'||?||'%' or "
                            + "lower(to_char(plt)) like '%'||?||'%' or "
                            + "lower(to_char(pre)) like '%'||?||'%' or "
                            + "lower(to_char(god)) like '%'||?||'%'");
            ps.setString(1, text);
            ps.setString(2, text);
            ps.setString(3, text);
            ps.setString(4, text);
            ps.setString(5, text);
            ps.setString(6, text);
            ps.setString(7, text);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                radnik=new FRadnik();
                radnik.setMbr(rs.getInt(1));
                radnik.setIme(rs.getString(2));
                radnik.setPrezime(rs.getString(3));
                radnik.setSef(getRadnikBasic(rs.getInt(4)));
                radnik.setPlata(rs.getInt(5));
                radnik.setPremija(rs.getInt(6));
                radnik.setGodinaRodjenja(rs.getDate(7));
                result.add(radnik);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void insert(RegistrovaniKorisnik korisnik) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into RegistrovaniKorisnik (ime,prezime,email,kontaktTelefon,godinaRodjenja,jeVidljiv) values (?,?,?,?,?,?,?)");
        if(korisnik.getIme()!=null) ps.setString(1, korisnik.getIme()); else ps.setNull(1, java.sql.Types.VARCHAR);
        if(korisnik.getPrezime()!=null) ps.setString(2, korisnik.getPrezime()); else ps.setNull(2, java.sql.Types.VARCHAR);
        if(korisnik.getEmail()!=null) ps.setString(3, korisnik.getEmail()); else ps.setNull(3, java.sql.Types.VARCHAR);
        if(korisnik.getKontaktTelefon()!=null) ps.setString(4, korisnik.getKontaktTelefon()); else ps.setNull(4, java.sql.Types.VARCHAR);
        if(korisnik.getGodinaRodjenja()!=null) ps.setDate(5, (Date) korisnik.getGodinaRodjenja()); else ps.setNull(5, Types.DATE);
        ps.setBoolean(6, korisnik.isJeVidljiv());
        ps.executeUpdate();
        ps.close();
    }

    public static void update(RegistrovaniKorisnik korisnik) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update RegistrovaniKorisnik set ime=?,prezime=?,email=?,kontaktTelefon=?,godinaRodjenja=?,jeVidljiv=? where id=?");
        if(korisnik.getIme()!=null) ps.setString(1, korisnik.getIme()); else ps.setNull(1, java.sql.Types.VARCHAR);
        if(korisnik.getPrezime()!=null) ps.setString(2, korisnik.getPrezime()); else ps.setNull(2, java.sql.Types.VARCHAR);
        if(korisnik.getEmail()!=null) ps.setString(3, korisnik.getEmail()); else ps.setNull(3, java.sql.Types.VARCHAR);
        if(korisnik.getKontaktTelefon()!=null) ps.setString(4, korisnik.getKontaktTelefon()); else ps.setNull(4, java.sql.Types.VARCHAR);
        if(korisnik.getGodinaRodjenja()!=null) ps.setDate(5, (Date) korisnik.getGodinaRodjenja()); else ps.setNull(5, Types.DATE);
        ps.setBoolean(6, korisnik.isJeVidljiv());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(RegistrovaniKorisnik korisnik)  throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update RegistrovaniKorisnik set obrisano=true where id=?");
        ps.setInt(1, korisnik.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static String[] columns(){
        String[] result={"ID", "Ime", "Prezime", "Email", "Kontankt telefon", "Godina rodjenja"};
        return result;
    }

    public static String[][] toTableData(List<RegistrovaniKorisnik> korisnici){
        String[][] result=new String[korisnici.size()][4];
        for(int i=0;i<korisnici.size();i++){
            result[i][0]=korisnici.get(i).getId() + "";
            result[i][1]=korisnici.get(i).getIme();
            result[i][2]=korisnici.get(i).getPrezime();
            result[i][3]=korisnici.get(i).getEmail();
            result[i][4]=korisnici.get(i).getKontaktTelefon();
            result[i][5]=korisnici.get(i).getGodinaRodjenja().toString();
        }
        return result;
    }
}