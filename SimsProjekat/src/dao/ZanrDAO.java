package dao;

import model.MuzickoDelo;
import model.Zanr;
import util.FConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZanrDAO {

    public static Zanr getZanrPoId(int id) {
        Zanr zanr = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.zanr where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                zanr = new Zanr(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getDate(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zanr;
    }


    public static List<Zanr> getZanrove() {
        List<Zanr> zanrovi = new ArrayList<Zanr>();
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.zanr");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                zanrovi.add(new Zanr(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getDate(3)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zanrovi;
    }

    public static List<MuzickoDelo> getMuzickaDelaPoZanru(Zanr zanr){
        List<MuzickoDelo> dela=new ArrayList<MuzickoDelo>();
        try {
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.muzickodelo md where obrisano = false and md.id in (select idMuzickogDela from muzicki_sistem.zanrmuzickogdela where idZanra=?)");
            ps.setInt(1, zanr.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                dela.add(new MuzickoDelo(rs.getInt(1),rs.getString(3),rs.getDate(4), rs.getDate(5)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dela;
    }

    public static List<Zanr> getZanroviPoMuzickomDelu(MuzickoDelo muzickoDelo){
        List<Zanr> zanrovi=new ArrayList<Zanr>();
        try {
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.zanr z where z.id in (select idZanra from muzicki_sistem.zanrmuzickogdela where idMuzickogDela=?)");
            ps.setInt(1, muzickoDelo.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                zanrovi.add(new Zanr(rs.getInt(1),rs.getString(2),rs.getString(4), rs.getDate(3)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zanrovi;
    }


    //TO DO -- treba se napraviti novi poveznik i onda implementirati one operacije ovde

    public static void insert(Zanr zanr) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("insert into muzicki_sistem.zanr (nazivZanra,datumNastanka,opis) values (?,?,?)");
        ps.setString(1, zanr.getNazivZanra());
        ps.setDate(2, (Date) zanr.getDatumNastanka());
        ps.setString(3, zanr.getOpis());
        ps.executeUpdate();
        ps.close();
    }

    public static void update(Zanr zanr) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update muzicki_sistem.zanr set nazivZanra=?,datumNastanka=?,opis=? where id=?");
        ps.setString(1, zanr.getNazivZanra());
        ps.setDate(2, (Date) zanr.getDatumNastanka());
        ps.setString(3, zanr.getOpis());
        ps.setInt(4, zanr.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(Zanr zanr) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("delete from muzicki_sistem.zanr where id=?");
        ps.setInt(1, zanr.getId());
        cascadeDelete(zanr);
        ps.executeUpdate();
        ps.close();
    }

    private static void cascadeDelete(Zanr zanr) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("delete from muzicki_sistem.zanrmuzickogdela where idZanra=?");
        ps.setInt(1, zanr.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static String[] columns() {
        String[] result = {"Id", "Naziv", "DatumNastanka", "Opis"};
        return result;
    }

    public static String[][] toTableData(List<Zanr> zanrovi) {
        String[][] result = new String[zanrovi.size()][4];
        for (int i = 0; i < zanrovi.size(); i++) {
            result[i][0] = String.valueOf(zanrovi.get(i).getId());
            result[i][1] = zanrovi.get(i).getNazivZanra();
            result[i][2] = zanrovi.get(i).getDatumNastanka().toString();
            result[i][3] = zanrovi.get(i).getOpis();
        }
        return result;
    }
}
