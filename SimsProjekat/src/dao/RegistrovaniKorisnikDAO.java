package dao;

import model.KorisnickiNalog;
import model.RegistrovaniKorisnik;
import util.FConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrovaniKorisnikDAO {

    public static List<RegistrovaniKorisnik> getRegistrovaneKorisnike() {
        List<RegistrovaniKorisnik> korisnici = new ArrayList<RegistrovaniKorisnik>();
        RegistrovaniKorisnik korisnik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email,kontaktTelefon,godinaRodjenja,jeVidljiv,idNaloga from RegistrovaniKorisnik where obrisano=false");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                korisnik = new RegistrovaniKorisnik();
                korisnik.setId(rs.getInt(1));
                korisnik.setIme(rs.getString(2));
                korisnik.setPrezime(rs.getString(3));
                korisnik.setEmail(rs.getString(4));
                korisnik.setKontaktTelefon(rs.getString(5));
                korisnik.setGodinaRodjenja(rs.getDate(6));
                korisnik.setJeVidljiv(rs.getBoolean(7));
                KorisnickiNalog nalog = KorisnickiNalogDAO.getNalog(rs.getInt(8));
                korisnik.setNalog(nalog);
                korisnici.add(korisnik);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return korisnici;
    }

    //Eventualno se moze namestiti da se korisnik dobavlja po korisnickom imenu i lozinci
    public static RegistrovaniKorisnik getRegistrovaniKorisnik(int id) {
        RegistrovaniKorisnik korisnik = null;
        KorisnickiNalog nalog = null;
        int idNaloga = -1;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email,kontaktTelefon,godinaRodjenja,idNaloga,jeVidljiv from RegistrovaniKorisnik where id=? and obrisano=false");
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
                idNaloga = rs.getInt(7);
                korisnik.setJeVidljiv(rs.getBoolean(8));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nalog = KorisnickiNalogDAO.getNalog(idNaloga);
        if(korisnik!=null) {korisnik.setNalog(nalog);}

        return korisnik;
    }

    public static RegistrovaniKorisnik getPoIdNaloga(KorisnickiNalog nalog) {
        RegistrovaniKorisnik korisnik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email,kontaktTelefon,godinaRodjenja,jeVidljiv from RegistrovaniKorisnik where idNaloga=? and obrisano=false");
            ps.setInt(1, nalog.getId());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                korisnik=new RegistrovaniKorisnik();
                korisnik.setId(rs.getInt(1));
                korisnik.setIme(rs.getString(2));
                korisnik.setPrezime(rs.getString(3));
                korisnik.setEmail(rs.getString(4));
                korisnik.setKontaktTelefon(rs.getString(5));
                korisnik.setGodinaRodjenja(rs.getDate(6));
                korisnik.setJeVidljiv(rs.getBoolean(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(korisnik!=null) {korisnik.setNalog(nalog);}

        return korisnik;
    }

    public static void insert(RegistrovaniKorisnik korisnik) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into RegistrovaniKorisnik (ime,prezime,email,kontaktTelefon,godinaRodjenja,jeVidljiv,idNaloga) values (?,?,?,?,?,?,?)");
        ps.setString(1, korisnik.getIme());
        ps.setString(2, korisnik.getPrezime());
        ps.setString(3, korisnik.getEmail());
        ps.setString(4, korisnik.getKontaktTelefon());
        ps.setDate(5, new java.sql.Date(korisnik.getGodinaRodjenja().getTime()));
        ps.setBoolean(6, korisnik.isJeVidljiv());
        ps.setInt(7, korisnik.getNalog().getId());
        ps.executeUpdate();
        ps.close();
    }

    public static void update(RegistrovaniKorisnik korisnik) {
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("update RegistrovaniKorisnik set ime=?,prezime=?,email=?,kontaktTelefon=?,godinaRodjenja=?,jeVidljiv=? where id=?");

            if(korisnik.getIme()!=null) ps.setString(1, korisnik.getIme()); else ps.setNull(1, java.sql.Types.VARCHAR);
            if(korisnik.getPrezime()!=null) ps.setString(2, korisnik.getPrezime()); else ps.setNull(2, java.sql.Types.VARCHAR);
            if(korisnik.getEmail()!=null) ps.setString(3, korisnik.getEmail()); else ps.setNull(3, java.sql.Types.VARCHAR);
            if(korisnik.getKontaktTelefon()!=null) ps.setString(4, korisnik.getKontaktTelefon()); else ps.setNull(4, java.sql.Types.VARCHAR);
            //TODO popravi ovaj cast Datecdx
            if(korisnik.getGodinaRodjenja()!=null) ps.setDate(5, new java.sql.Date(korisnik.getGodinaRodjenja().getTime())); else ps.setNull(5, Types.DATE);
            ps.setBoolean(6, korisnik.isJeVidljiv());
            ps.setInt(7, korisnik.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void delete(RegistrovaniKorisnik korisnik)  throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update RegistrovaniKorisnik set obrisano=true where id=?");
        ps.setInt(1, korisnik.getId());
        ps.executeUpdate();
        ps.close();
        // kad dragana doradi dao ovo treba da se otkomentarise
        //KorisnickiNalogDAO.delete(korisnik.getNalog());
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

    public static boolean proveraVidljivostiKorisnika(RegistrovaniKorisnik autorRecenzije) {
        boolean retVal = true;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from RegistrovaniKorisnik where id=? and obrisano=false");
            ps.setInt(1, autorRecenzije.getId());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                retVal = rs.getBoolean(9);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retVal;
    }
}