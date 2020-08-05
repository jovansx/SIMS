package dao;

import model.*;
import util.FConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuzickoDeloDAO {
    public static MuzickoDelo getMuzickoDelo(int id){
        MuzickoDelo delo = null;
        try{
            PreparedStatement ps = FConnection.getInstance().prepareStatement("select id,obrisano,nazivDela,datumPostavljanja,vremeNastanka,prosecnaOcena,opis,sadrzaj,pripadaAlbumu from MuzickoDelo where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                if(rs.getBoolean(2)){
                    return null;
                }else{
                    delo = new MuzickoDelo();
                    delo.setId(rs.getInt(1));
                    delo.setNazivDela(rs.getString(3));
                    delo.setDatumPostavljanja(rs.getDate(4));
                    delo.setVremeNastanka(rs.getDate(5));
                    delo.setProsecnaOcena(rs.getDouble(6));
                    delo.setOpis(rs.getString(7));
                    delo.setSadrzaj(rs.getString(8));
                    if(rs.getInt(9) > 0){
                        delo.setAlbumKomPripada(AlbumDAO.getAlbum(rs.getInt(9)));
                    }else {
                        delo.setAlbumKomPripada(null);
                    }
                }
            }
            rs.close();
            ps.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return delo;
    }
    public static List<MuzickoDelo> getMuzickaDela() {
        List<MuzickoDelo> dela = new ArrayList<MuzickoDelo>();
        MuzickoDelo delo = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,nazivDela,datumPostavljanja,vremeNastanka,prosecnaOcena,opis,sadrzaj,pripadaAlbumu from MuzickoDelo where id=? and where obrisano = false");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                delo = new MuzickoDelo();
                delo.setId(rs.getInt(1));
                delo.setNazivDela(rs.getString(2));
                delo.setDatumPostavljanja(rs.getDate(3));
                delo.setVremeNastanka(rs.getDate(4));
                delo.setProsecnaOcena(rs.getDouble(5));
                delo.setOpis(rs.getString(6));
                delo.setSadrzaj(rs.getString(7));
                if(rs.getInt(9) > 0){
                    delo.setAlbumKomPripada(AlbumDAO.getAlbum(rs.getInt(9)));
                }else {
                    delo.setAlbumKomPripada(null);
                }
                dela.add(delo);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dela;
    }

    public static List<MuzickoDelo> getMuzickaDelaIzvodjenja(int idIzvodjenja) {
        List<MuzickoDelo> dela = new ArrayList<MuzickoDelo>();
        MuzickoDelo delo = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select idMuzickogDela from IzvodjenjaMuzickogDela where idIzvodjenja=?");
            ps.setInt(1, idIzvodjenja);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                delo = MuzickoDeloDAO.getMuzickoDelo(rs.getInt(1));
                dela.add(delo);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dela;
    }

    public static void insert(MuzickoDelo delo) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into MuzickoDelo (id,nazivDela,datumPostavljanja,vremeNastanka,prosecnaOcena,opis,sadrzaj,pripadaAlbumu) values (?,?,?,?,?,?,?,?)");
        ps.setInt(1, delo.getId());
        ps.setString(2, delo.getNazivDela());
        ps.setDate(3, (Date) delo.getDatumPostavljanja());
        ps.setDate(4, (Date) delo.getVremeNastanka());
        ps.setDouble(5,delo.getProsecnaOcena());
        ps.setString(6,delo.getOpis());
        ps.setString(7,delo.getSadrzaj());
        ps.setInt(8,delo.getAlbumKomPripada().getId());
        ps.executeUpdate();
        ps.close();
    }
    public static void update(MuzickoDelo delo) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update MuzickoDelo set prosecnaOcena=?,opis=?, sadrzaj=? where id=?");
        ps.setDouble(1, delo.getProsecnaOcena());
        ps.setString(2, delo.getOpis());
        ps.setString(3, delo.getSadrzaj());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(MuzickoDelo delo) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update MuzickoDdelo  set obrisano = true where id=?");
        ps.setInt(1, delo.getId());
        ps.executeUpdate();
        ps.close();
    }
    public static String[] columns(){
        return new String[]{"ID", "Naziv dela","Datum","Vreme","Prosecna ocena","Opis","Sadrzaj","Id albuma"};
    }

    public static String[][] toTableData(List<MuzickoDelo> dela){
        String[][] result=new String[dela.size()][2];
        for(int i=0;i<dela.size();i++){
            result[i][0]=String.valueOf(dela.get(i).getId());
            result[i][1]=dela.get(i).getNazivDela();
            result[i][2] = String.valueOf(dela.get(i).getDatumPostavljanja());
            result[i][3] = String.valueOf(dela.get(i).getVremeNastanka());
            result[i][4] = String.valueOf(dela.get(i).getProsecnaOcena());
            result[i][5] = dela.get(i).getOpis();
            result[i][6] = dela.get(i).getSadrzaj();
            result[i][7] = String.valueOf(dela.get(i).getAlbumKomPripada().getId());
        }
        return result;
    }

}
