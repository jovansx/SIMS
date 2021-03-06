package dao;

import model.*;
import util.FConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UrednikDAO {

    //TO DO UCITATI I SVE LISTE

    public static Urednik getUrednikPoId(int id) {
        Urednik urednik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Urednik where id=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                KorisnickiNalog korisnickiNalog = KorisnickiNalogDAO.getNalog(rs.getInt(8));
                urednik = new Urednik(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), korisnickiNalog, rs.getDate(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return urednik;
    }

    public static Urednik getPoIdNaloga(KorisnickiNalog nalog) {
        Urednik korisnik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email,kontaktTelefon,godinaRodjenja from Urednik where idNaloga=? and obrisano=false");
            ps.setInt(1, nalog.getId());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                korisnik=new Urednik();
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

    public static Urednik getUrednikZadatka(Zadatak zadatak) {
        Urednik urednik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Urednik where obrisano=false and id in (select idUrednika from muzicki_sistem.Zadatak where obrisano = false and id=?)");
            ps.setInt(1, zadatak.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                KorisnickiNalog korisnickiNalog = KorisnickiNalogDAO.getNalog(rs.getInt(8));
                urednik = new Urednik(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), korisnickiNalog, rs.getDate(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return urednik;
    }

    public static List<Urednik> getUrednike() {
        List<Urednik> urednici = new ArrayList<Urednik>();
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Urednik where obrisano=false");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KorisnickiNalog korisnickiNalog = KorisnickiNalogDAO.getNalog(rs.getInt(8));
                urednici.add(new Urednik(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), korisnickiNalog, rs.getDate(7)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return urednici;
    }

    public static void insert(Urednik urednik) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("insert into muzicki_sistem.Urednik (ime,prezime,email,kontaktTelefon,godinaRodjenja,idNaloga) values (?,?,?,?,?,?)");
        ps.setString(1, urednik.getIme());
        ps.setString(2, urednik.getPrezime());
        ps.setString(3, urednik.getEmail());
        ps.setString(4, urednik.getKontaktTelefon());
        ps.setDate(5, (Date) urednik.getGodinaRodjenja());
        ps.setInt(6, urednik.getNalog().getId());
        ps.executeUpdate();
        ps.close();

        insertZanroveKojePoznajeUrednik(urednik);
    }

    public static void insertZanroveKojePoznajeUrednik(Urednik urednik) throws SQLException {
        for (Zanr z: urednik.getListaZanrova()) {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("insert into muzicki_sistem.UrednikPoznajeZanrove (idUrednika,nazivZanra) values (?,?)");
            ps.setInt(1, urednik.getId());
            ps.setString(2, z.getNazivZanra());
            ps.executeUpdate();
            ps.close();
        }
    }

    public static void update(Urednik urednik) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update Urednik set ime=?,prezime=?,email=?,kontaktTelefon=?,godinaRodjenja=? where id=?");
        ps.setString(1, urednik.getIme());
        ps.setString(2, urednik.getPrezime());
        ps.setString(3, urednik.getEmail());
        ps.setString(4, urednik.getKontaktTelefon());
        ps.setDate(5, (Date) urednik.getGodinaRodjenja());
        ps.setInt(6,urednik.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(Urednik urednik) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update muzicki_sistem.Urednik set obrisan=? where id=?");
        ps.setBoolean(1, true);
        ps.setInt(2, urednik.getId());

        for (Zadatak z: urednik.getListaZadataka()) {
            ZadatakDAO.delete(z);
        }
        ps.executeUpdate();
        ps.close();
    }

    public static String[] columns() {
        String[] result = {"Id", "Ime", "Prezime", "Email", "KontaktTelefon", "GodinaRodjenja", "IdNaloga"};
        return result;
    }

    public static String[][] toTableData(List<Urednik> urednik) {
        String[][] result = new String[urednik.size()][4];
        for (int i = 0; i < urednik.size(); i++) {
            result[i][0] = String.valueOf(urednik.get(i).getId());
            result[i][1] = urednik.get(i).getIme();
            result[i][2] = urednik.get(i).getPrezime();
            result[i][3] = urednik.get(i).getEmail();
            result[i][4] = urednik.get(i).getKontaktTelefon();
            result[i][5] = urednik.get(i).getGodinaRodjenja().toString();
            result[i][0] = String.valueOf(urednik.get(i).getNalog().getId());
        }
        return result;
    }
}
