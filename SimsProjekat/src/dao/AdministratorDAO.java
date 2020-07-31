package dao;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Administrator;
import model.Reklama;
import model.Zahtev;
import util.FConnection;

public class AdministratorDAO {
    public static Administrator getAdministrator(Integer id){
        Administrator admin=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email, kontaktTelefon, godinaRodjenja, idNaloga from administrator where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                admin=new Administrator();
                admin.setId(rs.getInt(1));
                admin.setIme(rs.getString(2));
                admin.setPrezime(rs.getString(3));
                admin.setEmail(rs.getString(4));
                admin.setKontaktTelefon(rs.getString(5));
                admin.setGodinaRodjenja(rs.getDate(6));
                admin.setNalog(KorisnickiNalogDAO.getNalog(rs.getInt(7)));
                admin.setIzlazeReklame(getReklame(id));
                admin.setListaZahteva(getZahtjeve(id));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }


    public static List<Reklama> getReklame(Integer id){
        Reklama reklama=null;
        List<Reklama> reklame = new ArrayList<Reklama>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,naziv,textReklame,cena, brojPristupa, brojPrikaza, idAdmina from Reklama where idAdmina=?");
            ps.setInt(7, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                reklama=new Reklama();
                reklama.setId(rs.getInt(1));
                reklama.setNaziv(rs.getString(2));
                reklama.setText(rs.getString(3));
                reklama.setCena(rs.getDouble(4));
                reklama.setBrojPristupa(rs.getInt(5));
                reklama.setBrojPrikaza(rs.getInt(6));
                reklame.add(reklama);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reklame;
    }

    public static List<Zahtev> getZahtjeve(Integer id){
        Zahtev zahtev=null;
        List<Zahtev> zahtevi = new ArrayList<Zahtev>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,naziv,opis, jeOdobren, jeObradjen, idAdmina, idKorisnika, idRecenzije from Zahtev where idAdmina=?");
            ps.setInt(6, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                zahtev=new Zahtev();
                zahtev.setId(rs.getInt(1));
                zahtev.setNaziv(rs.getString(2));
                zahtev.setOpis(rs.getString(3));
                zahtev.setJeOdobren(rs.getBoolean(4));
                zahtev.setJeObradjen(rs.getBoolean(5));
                //Treba da se doda jos za registovanog korisnika set
                zahtevi.add(zahtev);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zahtevi;
    }

}
