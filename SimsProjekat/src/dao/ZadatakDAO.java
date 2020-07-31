package dao;

import model.*;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZadatakDAO {

    public static Zadatak getZadatakPoId(int id) {
        Zadatak zadatak = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.zadatak where id=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MuzickoDelo muzickoDelo = MuzickoDeloDAO.getMuzickoDeloPoId(rs.getInt(4));
                Zanr zanr = ZanrDAO.getZanrPoNazivu(rs.getString(5));
                Ucesnik ucesnik = UcesnikDAO.getUcesnikPoId(rs.getInt(6));
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
                    .prepareStatement("select * from muzicki_sistem.zadatak where obrisano=false");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MuzickoDelo muzickoDelo = MuzickoDeloDAO.getMuzickoDeloPoId(rs.getInt(4));
                Zanr zanr = ZanrDAO.getZanrPoNazivu(rs.getString(5));
                Ucesnik ucesnik = UcesnikDAO.getUcesnikPoId(rs.getInt(6));
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
                    .prepareStatement("select * from muzicki_sistem.zadatak where idUrednika=? and obrisano=false");
            ps.setInt(1, urednik.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MuzickoDelo muzickoDelo = MuzickoDeloDAO.getMuzickoDeloPoId(rs.getInt(4));
                Zanr zanr = ZanrDAO.getZanrPoNazivu(rs.getString(5));
                Ucesnik ucesnik = UcesnikDAO.getUcesnikPoId(rs.getInt(6));
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

    public static void insert(Zadatak zadatak) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("insert into muzicki_sistem.zadatak (textZadatka,idMuzickogDela,nazivZanra,idUcesnika,idIzvodjaca,idUrednika) values (?,?,?,?,?,?)");
        ps.setString(1, zadatak.getText());
        ps.setInt(2, zadatak.getDelo().getId());
        ps.setString(3, zadatak.getZanr().getNazivZanra());
        ps.setInt(4, zadatak.getUcesnik().getId());
        ps.setInt(5, zadatak.getIzvodjac().getId());

        Urednik urednik = UrednikDAO.getUrednikZadatka(zadatak);

        ps.setInt(6, urednik.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static void update(Zadatak zadatak) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update muzicki_sistem.zadatak set textZadatka=?,idMuzickogDela=?,nazivZanra=?,idUcesnika?,idIzvodjaca=? where id=? and obrisano=false");
        ps.setString(1, zadatak.getText());
        ps.setInt(2, zadatak.getDelo().getId());
        ps.setString(3, zadatak.getZanr().getNazivZanra());
        ps.setInt(4, zadatak.getUcesnik().getId());
        ps.setInt(5, zadatak.getIzvodjac().getId());
        ps.setInt(6, zadatak.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(Zadatak zadatak) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update muzicki_sistem.zadatak set obrisan=? where id=?");
        ps.setBoolean(1, true);
        ps.setInt(2, zadatak.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static String[] columns() {
        String[] result = {"Id", "TextZadatka", "IdMuzickogDela", "NazivZanra", "IdUcesnika", "IdIzvodjaca", "IdUrednika"};
        return result;
    }

    public static String[][] toTableData(List<Zadatak> zadaci) {
        String[][] result = new String[zadaci.size()][4];
        for (int i = 0; i < zadaci.size(); i++) {
            result[i][0] = String.valueOf(zadaci.get(i).getId());
            result[i][1] = zadaci.get(i).getText();
            result[i][2] = String.valueOf(zadaci.get(i).getDelo().getId());
            result[i][3] = String.valueOf(zadaci.get(i).getZanr().getNazivZanra());
            result[i][4] = String.valueOf(zadaci.get(i).getUcesnik().getId());
            result[i][5] = String.valueOf(zadaci.get(i).getIzvodjac().getId());
            Urednik urednik = UrednikDAO.getUrednikZadatka(zadaci.get(i));
            result[i][6] = String.valueOf(urednik.getId());
        }
        return result;
    }
}


