package dao;

import model.Administrator;
import model.Album;
import model.MuzickoDelo;
import model.enums.TipAlbuma;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    public static Album getAlbum(Integer id){
        Album album=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivDela,datumPostavljanja,vremeNastanka, prosecnaOcena, opis, sadrzaj, tipAlbuma, pripadaAlbumu from MuzickoDelo where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                album=new Album();
                album.setId(rs.getInt(1));
                album.setNazivDela(rs.getString(2));
                album.setDatumPostavljanja(rs.getDate(3));
                album.setVremeNastanka(rs.getDate(4));
                album.setProsecnaOcena(rs.getDouble(5));
                album.setOpis(rs.getString(6));
                album.setSadrzaj(rs.getString(7));
                album.setTipAlbuma(TipAlbuma.valueOf(rs.getString(8)));
                album.setListaMuzickihDela(getDjela(id));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return album;
    }

    public static List<MuzickoDelo> getDjela(Integer id){
        MuzickoDelo delo =null;
        List<MuzickoDelo> dela=new ArrayList<MuzickoDelo>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivDela,datumPostavljanja,vremeNastanka, prosecnaOcena, opis, sadrzaj, tipAlbuma, pripadaAlbumu from MuzickoDelo where pripadaAlbumu=?");
            ps.setInt(8, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                delo=new MuzickoDelo();
                delo.setId(rs.getInt(1));
                delo.setNazivDela(rs.getString(2));
                delo.setDatumPostavljanja(rs.getDate(3));
                delo.setVremeNastanka(rs.getDate(4));
                delo.setProsecnaOcena(rs.getDouble(5));
                delo.setOpis(rs.getString(6));
                delo.setSadrzaj(rs.getString(7));
                dela.add(delo);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dela;

    }
}
