package dao;

import model.*;
import util.FConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZanrDAO {

    public static Zanr getZanrPoNazivu(String naziv) {
        Zanr zanr = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Zanr where nazivZanra=?");
            ps.setString(1, naziv);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                zanr = new Zanr(rs.getString(1), rs.getString(3), rs.getDate(2));
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
                    .prepareStatement("select * from muzicki_sistem.Zanr");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                zanrovi.add(new Zanr(rs.getString(1), rs.getString(3), rs.getDate(2)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zanrovi;
    }
    public static List<Zanr> getNedovrseneZanrove() {
        List<Zanr> zanrovi = new ArrayList<Zanr>();
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Zanr where opis is null");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                zanrovi.add(new Zanr(rs.getString(1), rs.getString(3), rs.getDate(2)));
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
                    .prepareStatement("select * from muzicki_sistem.Muzickodelo md where obrisano = false and md.id in (select idMuzickogDela from muzicki_sistem.Zanrmuzickogdela where nazivZanra=?)");
            ps.setString(1, zanr.getNazivZanra());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Album a = null;
                if (rs.getInt(9)>0){
                    a  = AlbumDAO.getAlbum(rs.getInt(9));
                }
                dela.add(new MuzickoDelo(rs.getInt(1),rs.getString(3),rs.getDate(4), rs.getDate(5), a));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dela;
    }

    public static List<Zanr> getZanroviPoUredniku(Urednik urednik){
        List<Zanr> zanrovi=new ArrayList<Zanr>();
        try {
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Zanr z where z.nazivZanra in (select nazivZanra from muzicki_sistem.UrednikPoznajeZanrove where idUrednika=?)");
            ps.setInt(1, urednik.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                zanrovi.add(new Zanr(rs.getString(1),rs.getString(3), rs.getDate(2)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zanrovi;
    }

    public static void insert(Zanr zanr) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("insert into muzicki_sistem.Zanr (nazivZanra,datumNastanka,opis) values (?,?,?)");
        ps.setString(1, zanr.getNazivZanra());
        ps.setDate(2, (Date) zanr.getDatumNastanka());
        ps.setString(3, zanr.getOpis());
        ps.executeUpdate();
        ps.close();
    }

    public static void update(Zanr zanr) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update muzicki_sistem.Zanr set datumNastanka=?,opis=? where nazivZanra=?");
        ps.setDate(1, (Date) zanr.getDatumNastanka());
        ps.setString(2, zanr.getOpis());
        ps.setString(3, zanr.getNazivZanra());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(Zanr zanr) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("delete from muzicki_sistem.Zanr where nazivZanra=?");
        ps.setString(1, zanr.getNazivZanra());
        cascadeDelete(zanr);
        ps.executeUpdate();
        ps.close();
    }

    private static void cascadeDelete(Zanr zanr) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("delete from muzicki_sistem.ZanrMuzickogDela where nazivZanra=?");
        ps.setString(1, zanr.getNazivZanra());
        ps.executeUpdate();
        ps.close();

        ps = FConnection.getInstance()
                .prepareStatement("delete from muzicki_sistem.UrednikPoznajeZanrove where nazivZanra=?");
        ps.setString(1, zanr.getNazivZanra());
        ps.executeUpdate();
        ps.close();
    }

    public static String[] columns() {
        String[] result = {"Naziv", "DatumNastanka", "Opis"};
        return result;
    }

    public static String[][] toTableData(List<Zanr> zanrovi) {
        String[][] result = new String[zanrovi.size()][4];
        for (int i = 0; i < zanrovi.size(); i++) {
            result[i][0] = zanrovi.get(i).getNazivZanra();
            result[i][1] = zanrovi.get(i).getDatumNastanka().toString();
            result[i][2] = zanrovi.get(i).getOpis();
        }
        return result;
    }

    public static List<Zanr> getZanroviPoMuzickomDelu(MuzickoDelo muzickoDelo) {
        List<Zanr> zanrovi=new ArrayList<Zanr>();
        Zanr zanr = null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select * from ZanrMuzickogDela where obrisano=false and idMuzickogDela = ?");
            ps.setInt(1, muzickoDelo.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                zanr=getZanrPoNazivu(rs.getString(1));
                zanrovi.add(zanr);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zanrovi;
    }
}
