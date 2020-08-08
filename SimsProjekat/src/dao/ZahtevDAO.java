package dao;

import model.Administrator;
import model.Recenzija;
import model.RegistrovaniKorisnik;
import model.Zahtev;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZahtevDAO {

    public static Zahtev getZahtevPoId(int id) {
        Zahtev zahtev = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Zahtev where id=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                RegistrovaniKorisnik registrovaniKorisnik = RegistrovaniKorisnikDAO.getRegistrovaniKorisnik(rs.getInt(8));;
                Recenzija recenzija = RecenzijaDAO.getRecenzija(rs.getInt(9));
                zahtev = new Zahtev(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getBoolean(6), registrovaniKorisnik, recenzija);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zahtev;
    }

    public static List<Zahtev> getZahteve() {
        List<Zahtev> zahtevi = new ArrayList<Zahtev>();
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from muzicki_sistem.Zahtev where obrisano=false");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RegistrovaniKorisnik registrovaniKorisnik = RegistrovaniKorisnikDAO.getRegistrovaniKorisnik(rs.getInt(8));;
                Recenzija recenzija = RecenzijaDAO.getRecenzija(rs.getInt(9));
                zahtevi.add(new Zahtev(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getBoolean(6), registrovaniKorisnik, recenzija));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zahtevi;
    }

    public static void insert(Zahtev zahtev, int idAdmina) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("insert into muzicki_sistem.Zahtev (naziv,opis,jeOdobren,jeObradjen,idAdmina,idKorisnika,idRecenzije) values (?,?,?,?,?,?,?)");
        ps.setString(1, zahtev.getNaziv());
        ps.setString(2, zahtev.getOpis());
        ps.setBoolean(3, zahtev.isJeOdobren());
        ps.setBoolean(4, zahtev.isJeObradjen());
        ps.setInt(5, idAdmina);
        ps.setInt(6, zahtev.getPodnosilacZahteva().getId());
        ps.setInt(7, zahtev.getRecenzija().getId());
        ps.executeUpdate();
        ps.close();
    }

    public static void insertBezAdmina(Zahtev zahtev) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("insert into muzicki_sistem.Zahtev (naziv,opis,idKorisnika,idRecenzije) values (?,?,?,?)");
        ps.setString(1, zahtev.getNaziv());
        ps.setString(2, zahtev.getOpis());
        ps.setInt(3, zahtev.getPodnosilacZahteva().getId());
        ps.setInt(4, zahtev.getRecenzija().getId());
        ps.executeUpdate();
        ps.close();
    }

    public static void update(Zahtev zahtev) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update muzicki_sistem.Zahtev set naziv=?,opis=?,jeOdobren=?,jeObradje?,idKorisnika=?,idRecenzije=? where id=? and obrisano=false");
        ps.setString(1, zahtev.getNaziv());
        ps.setString(2, zahtev.getOpis());
        ps.setBoolean(3, zahtev.isJeOdobren());
        ps.setBoolean(4, zahtev.isJeObradjen());
        ps.setInt(5, zahtev.getPodnosilacZahteva().getId());
        ps.setInt(6, zahtev.getRecenzija().getId());
        ps.setInt(7, zahtev.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(Zahtev zahtev) throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update muzicki_sistem.Zahtev set obrisano=? where id=?");
        ps.setBoolean(1, true);
        ps.setInt(2, zahtev.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static String[] columns() {
        String[] result = {"Id", "Naziv", "Opis", "JeOdobren", "JeObradjen", "IdKorisnika", "IdRecenzije"};
        return result;
    }

    public static String[][] toTableData(List<Zahtev> zahtevi) {
        String[][] result = new String[zahtevi.size()][4];
        for (int i = 0; i < zahtevi.size(); i++) {
            result[i][0] = String.valueOf(zahtevi.get(i).getId());
            result[i][1] = zahtevi.get(i).getNaziv();
            result[i][2] = zahtevi.get(i).getOpis();
            result[i][3] = String.valueOf(zahtevi.get(i).isJeOdobren());
            result[i][4] = String.valueOf(zahtevi.get(i).isJeObradjen());
            result[i][5] = String.valueOf(zahtevi.get(i).getPodnosilacZahteva().getId());
            result[i][6] = String.valueOf(zahtevi.get(i).getRecenzija().getId());
        }
        return result;
    }
}