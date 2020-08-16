package dao;

import model.MuzickoDelo;
import model.Ucesnik;
import model.Zanr;
import model.enums.TipUcesnika;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UcesnikDAO {

    public static List<Ucesnik> getUcesnike() {
        List<Ucesnik> ucesnici = new ArrayList<Ucesnik>();
        Ucesnik ucesnik = null;
        int idUcesnika = -1;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id from Ucesnik");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idUcesnika = rs.getInt(1);
                ucesnik = UcesnikDAO.getUcesnik(idUcesnika);
                ucesnici.add(ucesnik);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ucesnici;
    }

    public static List<Ucesnik> getNedovrseneUceniske() {
        List<Ucesnik> ucesnici = new ArrayList<Ucesnik>();
        Ucesnik ucesnik = null;
        int idUcesnika = -1;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id from Ucesnik where opis is null");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idUcesnika = rs.getInt(1);
                ucesnik = UcesnikDAO.getUcesnik(idUcesnika);
                ucesnici.add(ucesnik);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ucesnici;
    }
    //Prosledio sam muzicko delo kao parametar a moze i samo njegov id
    public static List<Ucesnik> getUcesniciMuzickogDela(MuzickoDelo muzickoDelo)  {
        List<Ucesnik> ucesniki =new ArrayList<Ucesnik>();
        Ucesnik ucesnik = null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select * from UcesnikMuzickogDela where obrisano=false and idMuzickogDela = ?");
            ps.setInt(1, muzickoDelo.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                ucesnik=getUcesnik(rs.getInt(1));
                ucesniki.add(ucesnik);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ucesniki;
    }

    //Eventualno se moze namestiti da se ucesnik dobavlja po korisnickom imenu i lozinci
    public static Ucesnik getUcesnik(int id) {
        Ucesnik ucesnik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,nazivUcesnika,opis,tip from Ucesnik where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ucesnik = new Ucesnik();
                ucesnik.setId(rs.getInt(1));
                ucesnik.setNaziv(rs.getString(2));
                ucesnik.setOpis(rs.getString(3));
                ucesnik.setTip(TipUcesnika.valueOf(rs.getString(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ucesnik;
    }
    public static Ucesnik getUcesnik(String nazivUcesnika) {
        Ucesnik ucesnik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,nazivUcesnika,opis,tip from Ucesnik where nazivUcesnika=?");
            ps.setString(2, nazivUcesnika);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ucesnik = new Ucesnik();
                ucesnik.setId(rs.getInt(1));
                ucesnik.setNaziv(rs.getString(2));
                ucesnik.setOpis(rs.getString(3));
                ucesnik.setTip(TipUcesnika.valueOf(rs.getString(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ucesnik;
    }

    public static void insert(Ucesnik ucesnik) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("insert into Ucesnik (nazivUcesnika,tip,opis) values (?,?,?)");
        ps.setString(1, ucesnik.getNaziv());
        ps.setString(2, ucesnik.getTip().toString().toUpperCase());
        ps.setString(3, ucesnik.getOpis());
        ps.executeUpdate();
        ps.close();
    }

    public static void update(Ucesnik ucesnik) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update Ucesnik set nazivUcesnika=?,tip=?,opis=? where id=?");
        ps.setString(1, ucesnik.getNaziv());
        ps.setString(2, ucesnik.getTip().toString().toUpperCase());
        ps.setString(3, ucesnik.getOpis());
        ps.setInt(4, ucesnik.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static String[] columns() {
        String[] result = {"ID", "Naziv ucesnika", "Opis", "Tip ucesnika"};
        return result;
    }

    public static String[][] toTableData(List<Ucesnik> ucesnici) {
        String[][] result = new String[ucesnici.size()][4];
        for (int i = 0; i < ucesnici.size(); i++) {
            result[i][0] = ucesnici.get(i).getId() + "";
            result[i][1] = ucesnici.get(i).getNaziv();
            result[i][2] = ucesnici.get(i).getOpis();
            result[i][3] = ucesnici.get(i).getTip().toString();
        }
        return result;
    }
}