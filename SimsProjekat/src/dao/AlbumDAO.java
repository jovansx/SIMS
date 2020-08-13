package dao;

import model.Administrator;
import model.Album;
import model.Izvodjenje;
import model.MuzickoDelo;
import model.enums.TipAlbuma;
import util.FConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    public static Album getAlbum(Integer idMuzickogDela){
        Album album=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivDela,datumPostavljanja, vremeNastanka, prosecnaOcena, " +
                            "opis, sadrzaj, tipAlbuma, pripadaAlbumu,obrisano from MuzickoDelo where id=?");
            ps.setInt(1, idMuzickogDela);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                if(!rs.getBoolean(9) && rs.getString(8)!=null){
                    album=new Album();
                    album.setId(rs.getInt(1));
                    album.setNazivDela(rs.getString(2));
                    album.setDatumPostavljanja(rs.getDate(3));
                    album.setVremeNastanka(rs.getDate(4));
                    album.setProsecnaOcena(rs.getDouble(5));
                    album.setOpis(rs.getString(6));
                    album.setSadrzaj(rs.getString(7));
                    album.setTipAlbuma(TipAlbuma.valueOf(rs.getString(8)));
                    album.setListaMuzickihDela(getDela(idMuzickogDela));
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return album;
    }
    public static Album getAlbumPoNazivu(String nazivDela){
        Album album=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivDela,datumPostavljanja, vremeNastanka, prosecnaOcena, " +
                            "opis, sadrzaj, tipAlbuma, pripadaAlbumu,obrisano from MuzickoDelo where nazivDela=?");
            ps.setString(2,nazivDela);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                if(!rs.getBoolean(9) && rs.getString(8)!=null){
                    album=new Album();
                    album.setId(rs.getInt(1));
                    album.setNazivDela(rs.getString(2));
                    album.setDatumPostavljanja(rs.getDate(3));
                    album.setVremeNastanka(rs.getDate(4));
                    album.setProsecnaOcena(rs.getDouble(5));
                    album.setOpis(rs.getString(6));
                    album.setSadrzaj(rs.getString(7));
                    album.setTipAlbuma(TipAlbuma.valueOf(rs.getString(8)));
                    album.setListaMuzickihDela(getDela(album.getId()));
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return album;
    }
    public static List<MuzickoDelo> getDela(Integer id){
        MuzickoDelo delo =null;
        List<MuzickoDelo> dela=new ArrayList<MuzickoDelo>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivDela,datumPostavljanja,vremeNastanka, prosecnaOcena, " +
                            "opis, sadrzaj, tipAlbuma, pripadaAlbumu,obrisano from MuzickoDelo where pripadaAlbumu=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(!rs.getBoolean(9)) {
                    delo=new MuzickoDelo();
                    delo.setId(rs.getInt(1));
                    delo.setNazivDela(rs.getString(3));
                    delo.setDatumPostavljanja(rs.getDate(4));
                    delo.setVremeNastanka(rs.getDate(5));
                    delo.setProsecnaOcena(rs.getDouble(6));
                    delo.setOpis(rs.getString(7));
                    delo.setSadrzaj(rs.getString(8));
                    dela.add(delo);
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dela;
    }

    public static List<Album> getListaAlbuma(){
        List<Album> albumi = new ArrayList<Album>();
        Album album=null;
        try{
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivDela,datumPostavljanja, vremeNastanka, prosecnaOcena, " +
                            "opis, sadrzaj, tipAlbuma, pripadaAlbumu,obrisano from MuzickoDelo");
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                if(!rs.getBoolean(9) && rs.getString(8)!=null){
                    album=new Album();
                    album.setId(rs.getInt(1));
                    album.setNazivDela(rs.getString(2));
                    album.setDatumPostavljanja(rs.getDate(3));
                    album.setVremeNastanka(rs.getDate(4));
                    album.setProsecnaOcena(rs.getDouble(5));
                    album.setOpis(rs.getString(6));
                    album.setSadrzaj(rs.getString(7));
                    album.setTipAlbuma(TipAlbuma.valueOf(rs.getString(8)));
                    album.setListaMuzickihDela(getDela(rs.getInt(1)));
                    albumi.add(album);
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return albumi;
    }
    public static void insert(Album album, int id) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into album(id, obrisano, nazivDela, datumPostavljanja, vremeNastanka, " +
                        "prosecnaOcena, opis, sadrzaj, tipAlbuma, pripadaAlbumu) values (?,?,?,?,?,?,?,?,?,?)");
        ps.setInt(1, album.getId());
        ps.setBoolean(2, false);
        if(album.getNazivDela()!=null) ps.setString(3, album.getNazivDela()); else ps.setNull(3, Types.VARCHAR);
        if(album.getDatumPostavljanja()!=null) ps.setDate(4, (Date) album.getDatumPostavljanja()); else ps.setNull(4, Types.DATE);
        if(album.getVremeNastanka()!=null) ps.setDate(5, (Date) album.getVremeNastanka()); else ps.setNull(5,Types.DATE);
        ps.setDouble(6, album.getProsecnaOcena());
        if(album.getOpis()!=null) ps.setString(7, album.getOpis()); else ps.setNull(7, Types.VARCHAR);
        if(album.getSadrzaj()!=null) ps.setString(8, album.getSadrzaj()); else ps.setNull(8, Types.VARCHAR);
        if(album.getTipAlbuma()!=null) ps.setString(9, album.getTipAlbuma().toString()); else ps.setNull(9, Types.VARCHAR);
        ps.setNull(10, Types.INTEGER);
        ps.executeUpdate();
        ps.close();
    }
    public static void update(Album album, int id) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update album set id=?, obrisano=?, nazivDela=?, datumPostavljanja=?, vremeNastanka=?, " +
                        "prosecnaOcena=?, opis=?, sadrzaj=?, tipAlbuma=?, pripadaAlbumu=?");
        ps.setInt(1, album.getId());
        ps.setBoolean(2, false);
        if(album.getNazivDela()!=null) ps.setString(3, album.getNazivDela()); else ps.setNull(3, Types.VARCHAR);
        if(album.getDatumPostavljanja()!=null) ps.setDate(4, (Date) album.getDatumPostavljanja()); else ps.setNull(4, Types.DATE);
        if(album.getVremeNastanka()!=null) ps.setDate(5, (Date) album.getVremeNastanka()); else ps.setNull(5,Types.DATE);
        ps.setDouble(6, album.getProsecnaOcena());
        if(album.getOpis()!=null) ps.setString(7, album.getOpis()); else ps.setNull(7, Types.VARCHAR);
        if(album.getSadrzaj()!=null) ps.setString(8, album.getSadrzaj()); else ps.setNull(8, Types.VARCHAR);
        if(album.getTipAlbuma()!=null) ps.setString(9, album.getTipAlbuma().toString()); else ps.setNull(9, Types.VARCHAR);
        ps.setInt(10, id);
        ps.executeUpdate();
        ps.close();
    }
    //Delete
    public static void delete(Album album) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update album set obrisano=true where id=?");
        ps.setInt(1, album.getId());
        ps.executeUpdate();
        ps.close();
    }
    //ToTable
    public static String[][] toTableData(List<Album> albumi){
        String[][] result=new String[albumi.size()][7];
        for(int i=0;i<albumi.size();i++){
            result[i][0]= String.valueOf(albumi.get(i).getId());
            result[i][1]=albumi.get(i).getNazivDela();
            result[i][2]=albumi.get(i).getSadrzaj();
            result[i][3]=albumi.get(i).getOpis();
            result[i][4]=albumi.get(i).getTipAlbuma().toString();
            result[i][5]=albumi.get(i).getVremeNastanka().toString();
            result[i][6]=albumi.get(i).getDatumPostavljanja().toString();
        }
        return result;
    }
}
