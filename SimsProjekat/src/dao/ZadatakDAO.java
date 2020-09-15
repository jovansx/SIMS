package dao;

import model.*;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ZadatakDAO {

    public static Zadatak getZadatakPoId(int id) {
        Zadatak zadatak = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Zadatak where id=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MuzickoDelo muzickoDelo = MuzickoDeloDAO.getMuzickoDelo(rs.getInt(4));
                Zanr zanr = ZanrDAO.getZanrPoNazivu(rs.getString(5));
                Ucesnik ucesnik = UcesnikDAO.getUcesnik(rs.getInt(6));
                Izvodjac izvodjac = IzvodjacDAO.getIzvodjac(rs.getInt(7));
                zadatak = new Zadatak(rs.getInt(1), rs.getString(3), muzickoDelo, zanr, ucesnik, izvodjac);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zadatak;
    }

    public static List<Zadatak> getZadatke() {
        List<Zadatak> zadaci = new ArrayList<Zadatak>();
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Zadatak where obrisano=false");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MuzickoDelo muzickoDelo = MuzickoDeloDAO.getMuzickoDelo(rs.getInt(4));
                Zanr zanr = ZanrDAO.getZanrPoNazivu(rs.getString(5));
                Ucesnik ucesnik = UcesnikDAO.getUcesnik(rs.getInt(6));
                Izvodjac izvodjac = IzvodjacDAO.getIzvodjac(rs.getInt(7));
                zadaci.add(new Zadatak(rs.getInt(1), rs.getString(3), muzickoDelo, zanr, ucesnik, izvodjac));

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zadaci;
    }

    public static List<Zadatak> getZadatkePoUredniku(Urednik urednik) {
        List<Zadatak> zadaci = new ArrayList<Zadatak>();
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Zadatak where idUrednika=? and obrisano=false");
            ps.setInt(1, urednik.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MuzickoDelo muzickoDelo = MuzickoDeloDAO.getMuzickoDelo(rs.getInt(4));
                Zanr zanr = ZanrDAO.getZanrPoNazivu(rs.getString(5));
                Ucesnik ucesnik = UcesnikDAO.getUcesnik(rs.getInt(6));
                Izvodjac izvodjac = IzvodjacDAO.getIzvodjac(rs.getInt(7));
                zadaci.add(new Zadatak(rs.getInt(1), rs.getString(3), muzickoDelo, zanr, ucesnik, izvodjac));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zadaci;
    }

    public static void insert(Zadatak zadatak, int idUrednika) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("insert into muzicki_sistem.Zadatak (textZadatka,idMuzickogDela,nazivZanra,idUcesnika,idIzvodjaca,idUrednika) values (?,?,?,?,?,?)");
        ps.setString(1, zadatak.getText());
        if(zadatak.getDelo()!=null) ps.setInt(2, zadatak.getDelo().getId()); else ps.setNull(2, Types.INTEGER);
        if(zadatak.getZanr()!=null) ps.setString(3, zadatak.getZanr().getNazivZanra()); else ps.setNull(3, Types.VARCHAR);
        if(zadatak.getUcesnik()!=null) ps.setInt(4, zadatak.getUcesnik().getId()); else ps.setNull(4, Types.INTEGER);
        if(zadatak.getIzvodjac()!=null) ps.setInt(5, zadatak.getIzvodjac().getId()); else ps.setNull(5, Types.INTEGER);
        ps.setInt(6, idUrednika);
        ps.executeUpdate();
        ps.close();
    }

    public static void update(Zadatak zadatak) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update muzicki_sistem.Zadatak set textZadatka=?,idMuzickogDela=?,nazivZanra=?,idUcesnika?,idIzvodjaca=? where id=? and obrisano=false");
        ps.setString(1, zadatak.getText());
        if(zadatak.getDelo()!=null) ps.setInt(2, zadatak.getDelo().getId()); else ps.setNull(2, Types.INTEGER);
        if(zadatak.getZanr()!=null) ps.setString(3, zadatak.getZanr().getNazivZanra()); else ps.setNull(3, Types.VARCHAR);
        if(zadatak.getUcesnik()!=null) ps.setInt(4, zadatak.getUcesnik().getId()); else ps.setNull(4, Types.INTEGER);
        if(zadatak.getIzvodjac()!=null) ps.setInt(5, zadatak.getIzvodjac().getId()); else ps.setNull(5, Types.INTEGER);
        ps.setInt(6, zadatak.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(Zadatak zadatak) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update muzicki_sistem.Zadatak set obrisano=? where id=?");
        ps.setBoolean(1, true);
        ps.setInt(2, zadatak.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static String[] columns() {
        String[] result = {"Id", "TextZadatka", "IdMuzickogDela", "NazivZanra", "IdUcesnika", "IdIzvodjaca"};
        return result;
    }

    public static String[][] toTableData(List<Zadatak> zadaci) {
        String[][] result = new String[zadaci.size()][6];
        for (int i = 0; i < zadaci.size(); i++) {
            result[i][0] = String.valueOf(zadaci.get(i).getId());
            result[i][1] = zadaci.get(i).getText();
            result[i][2] = String.valueOf(zadaci.get(i).getDelo().getId());
            result[i][3] = String.valueOf(zadaci.get(i).getZanr().getNazivZanra());
            result[i][4] = String.valueOf(zadaci.get(i).getUcesnik().getId());
            result[i][5] = String.valueOf(zadaci.get(i).getIzvodjac().getId());
        }
        return result;
    }
}


