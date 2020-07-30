package dao;

import model.KorisnickiNalog;
import model.enums.TipKorisnika;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KorisnickiNalogDAO {
    public static KorisnickiNalog getNalog(Integer id){
        KorisnickiNalog korisnik=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,korisnickoIme,lozinka, tipKorisnika from KorisnickiNalog where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                korisnik = new KorisnickiNalog();
                korisnik.setId(rs.getInt(1));
                korisnik.setKorisnickoIme(rs.getString(2));
                korisnik.setLozinka(rs.getString(3));
                korisnik.setKorisnik(TipKorisnika.valueOf(rs.getString(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return korisnik;
    }
}
