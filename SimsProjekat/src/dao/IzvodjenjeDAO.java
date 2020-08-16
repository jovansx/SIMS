package dao;

import model.*;

import model.enums.TipIzvodjenja;
import util.FConnection;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IzvodjenjeDAO {

    public static File getAudioIzvodjenja(int id, String sep) {
        File file = null;
        Statement st = null;
        try {
            st = FConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery("select audio from Izvodjenje where id=" + id);
            file = new File("AudioBaza" + sep + "audio.mp3");
            FileOutputStream output = new FileOutputStream(file);
            if(rs.next()) {
                InputStream input = rs.getBinaryStream("audio");
                byte[] buffer = new byte[1024];
                while(input.read(buffer) > 0) {
                    output.write(buffer);
                }
            }
            rs.close();
            st.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return file;
    }

    /**
     Metoda za unos audia, jer nece direktno u bazi
     */
    public static void updateAudio(int id, String path) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Izvodjenje set audio=? where id=?");
        File file = new File(path);
        try {
            FileInputStream input = new FileInputStream(file);
            ps.setBinaryStream(1, input);
            ps.setInt(2, id);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ps.executeUpdate();
        ps.close();
    }

    public static ImageIcon getSlikuIzvodjenja(Izvodjenje iz, String separator) {
        Statement st = null;
        ImageIcon retImageIcon = null;
        try {
            st = FConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery("select * from Izvodjenje where id=" + iz.getId());
            if(rs.next()) {
                byte[] img = rs.getBytes("slika");
                if(img == null) {
                    return new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "ikoneIzvodjenja" + separator +"default.jpg");
                }
                retImageIcon = new ImageIcon(img);
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retImageIcon;
    }

    /**
        Metoda za unos slike, jer nece direktno u bazi
    */
    public static void updateSliku(int id, String path) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Izvodjenje set slika=? where id=?");
        InputStream is;
        try {
            is = new FileInputStream(new File(path));
        }catch(Exception ex) {
            is = null;
        }
        ps.setBlob(1, is);
        ps.setInt(2, id);
        ps.executeUpdate();
        ps.close();
    }

    public static Izvodjenje getIzvodjenje(Integer id){
        Izvodjenje izvodjenje=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,vremeIzvodjenja,trajanje,tipIzvodjenja, brojPristupa, brojGlasova, " +
                            "ukupnoPristupa, pttBrojMesta, obrisano from Izvodjenje where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                if(!rs.getBoolean(9)){
                    izvodjenje=new Izvodjenje();
                    izvodjenje.setId(rs.getInt(1));
                    izvodjenje.setVremeIzvodjenja(rs.getDate(2));
                    izvodjenje.setTrajanje(rs.getInt(3));
                    izvodjenje.setTipIzvodjenja(TipIzvodjenja.valueOf(rs.getString(4)));
                    izvodjenje.setBrPristupa(rs.getInt(5));
                    izvodjenje.setBrGlasova(rs.getInt(6));
                    izvodjenje.setUkupnoPrisupa(rs.getInt(7));
                    izvodjenje.setMestoIzvodjenja(MestoIzvodjenjaDAO.getMestoIzvodjenja(rs.getInt(8)));
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjenje;
    }

    public static List<Izvodjenje> getIzvodjenjaZaPocetnuStranicu(int brojElemenata, String kriterijum){
        List<Izvodjenje> izvodjenja=new ArrayList<Izvodjenje>();
        Izvodjenje izvodjenje = null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,vremeIzvodjenja,trajanje,tipIzvodjenja,brojPristupa,brojGlasova,ukupnoPristupa,pttBrojMesta from Izvodjenje where obrisano=false order by " + kriterijum + " desc limit ?");
            ps.setInt(1, brojElemenata);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                izvodjenje=new Izvodjenje();
                izvodjenje.setId(rs.getInt(1));
                izvodjenje.setVremeIzvodjenja(rs.getDate(2));
                izvodjenje.setTrajanje(rs.getInt(3));
                izvodjenje.setTipIzvodjenja(TipIzvodjenja.valueOf(rs.getString(4)));
                izvodjenje.setBrPristupa(rs.getInt(5));
                izvodjenje.setBrGlasova(rs.getInt(6));
                izvodjenje.setUkupnoPrisupa(rs.getInt(7));
                izvodjenje.setMestoIzvodjenja(MestoIzvodjenjaDAO.getMestoIzvodjenja(rs.getInt(8)));
                izvodjenje.setListaMuzickihDela(MuzickoDeloDAO.getMuzickaDelaIzvodjenja(rs.getInt(1)));
                izvodjenje.setListaIzvodjaca(IzvodjacDAO.getIzvodjaciIzvodjenja(rs.getInt(1)));
                izvodjenja.add(izvodjenje);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjenja;
    }


    public static List<Izvodjenje> getIzvodjenjaPretrage(String textPretrage, int brojElemenata, String filter, String filterZanra) throws SQLException {
        List<Izvodjenje> izvodjenja=new ArrayList<Izvodjenje>();
        Izvodjenje izvodjenje = null;
        String dodatakUpituUSlucajuFilteraZanra = "";
        if (!filterZanra.equals("svi")){
            dodatakUpituUSlucajuFilteraZanra = " and id in (SELECT idIzvodjenja FROM muzicki_sistem.ZanrMuzickogDela as zmd" +
                    " inner join muzicki_sistem.MuzickoDelo as md inner join muzicki_sistem.IzvodjenjaMuzickogDela as imd" +
                    " where zmd.idMuzickogDela = md.id and md.id = imd.idMuzickogDela and zmd.NazivZanra = '" +filterZanra+ "')";
        }

        PreparedStatement ps= FConnection.getInstance()
                .prepareStatement("select id,vremeIzvodjenja,trajanje,tipIzvodjenja,brojPristupa,brojGlasova,ukupnoPristupa,pttBrojMesta from Izvodjenje where obrisano=false and " +
                        "id in(select distinct idIzvodjenja from IzvodjenjaMuzickogDela where idMuzickogDela in" +
                        "(select id from MuzickoDelo where lower(nazivDela) like '%"+ textPretrage + "%'))" +dodatakUpituUSlucajuFilteraZanra
                        + " order by " + filter + " limit ?");
        ps.setInt(1, brojElemenata);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            izvodjenje=new Izvodjenje();
            izvodjenje.setId(rs.getInt(1));
            izvodjenje.setVremeIzvodjenja(rs.getDate(2));
            izvodjenje.setTrajanje(rs.getInt(3));
            izvodjenje.setTipIzvodjenja(TipIzvodjenja.valueOf(rs.getString(4)));
            izvodjenje.setBrPristupa(rs.getInt(5));
            izvodjenje.setBrGlasova(rs.getInt(6));
            izvodjenje.setUkupnoPrisupa(rs.getInt(7));
            izvodjenje.setMestoIzvodjenja(MestoIzvodjenjaDAO.getMestoIzvodjenja(rs.getInt(8)));
            izvodjenje.setListaMuzickihDela(MuzickoDeloDAO.getMuzickaDelaIzvodjenja(rs.getInt(1)));
            izvodjenje.setListaIzvodjaca(IzvodjacDAO.getIzvodjaciIzvodjenja(rs.getInt(1)));
            izvodjenja.add(izvodjenje);
        }
        rs.close();
        ps.close();

        return izvodjenja;
    }

    //Trazenje izvodjaca koja su ucestovala u izvodjenju
    public static List<Izvodjac> getizvodjaci(Integer id){
        Izvodjac izvodjac =null;
        List<Izvodjac> izvodjaci=new ArrayList<Izvodjac>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select idIzvodjaca, idIzvodjenja from IzvodjacIzvodi where idIzvodjenja=?");
            ps.setInt(2, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                izvodjac=IzvodjacDAO.getIzvodjac(rs.getInt(1));
                izvodjaci.add(izvodjac);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjaci;
    }
    //Trazenje muzickih dela koja se izvode u izvodjenju
    public static List<MuzickoDelo> getMuzickaDela(Integer id){
        List<MuzickoDelo> dela=new ArrayList<>();
        MuzickoDelo delo= null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select idIzvodjenja, idMuzickogDela from IzvodjenjaMuzickogDela where idIzvodjenja=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                delo=MuzickoDeloDAO.getMuzickoDelo(rs.getInt(2));
                dela.add(delo);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dela;
    }

    //Trazenje recenzija za izvodjenje
    public static List<Recenzija> getRecenzije(Integer id){
        List<Recenzija> recenzije=new ArrayList<>();
        Recenzija recenzija=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id, obrisano, ocena, komentar, idMuzickogDela, idIzvodjenja, " +
                            "idKorisnika, idUrednika from Recenzija where idIzvodjenja=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(!rs.getBoolean(2)){
                    recenzija.setId(rs.getInt(1));
                    recenzija.setOcena(rs.getInt(3));
                    recenzija.setKomentar(rs.getString(4));
                    recenzija.setMuzickoDelo(MuzickoDeloDAO.getMuzickoDelo(rs.getInt(5)));
                    recenzija.setAutorRecenzije(RegistrovaniKorisnikDAO.getRegistrovaniKorisnik(rs.getInt(7)));
                    recenzija.setUrednik(UrednikDAO.getUrednikPoId(rs.getInt(8)));
                    recenzije.add(recenzija);
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recenzije;
    }

    //Funkcija koja vraca izvodjenja u plejlisti
    public static List<Izvodjenje> izvodjenjaUPlejlisti(int id){
        List<Izvodjenje> izvodjenja=new ArrayList<>();
        Izvodjenje izvodjenje=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select i.id,i.vremeIzvodjenja,i.trajanje,i.tipIzvodjenja,i.brojPristupa," +
                            "i.brojGlasova,i.ukupnoPristupa,i.pttBrojMesta from Izvodjenje i,IzvodjenjaPlaylisti ip " +
                            "where ip.idPlayListe=? and ip.idIzvodjenja=i.id");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                izvodjenje=new Izvodjenje();
                izvodjenje.setId(rs.getInt(1));
                izvodjenje.setVremeIzvodjenja(rs.getDate(2));
                izvodjenje.setTrajanje(rs.getInt(3));
                izvodjenje.setTipIzvodjenja(TipIzvodjenja.valueOf(rs.getString(4)));
                izvodjenje.setBrPristupa(rs.getInt(5));
                izvodjenje.setBrGlasova(rs.getInt(6));
                izvodjenje.setUkupnoPrisupa(rs.getInt(7));
                izvodjenje.setMestoIzvodjenja(MestoIzvodjenjaDAO.getMestoIzvodjenja(rs.getInt(8)));
                izvodjenje.setListaMuzickihDela(MuzickoDeloDAO.getMuzickaDelaIzvodjenja(rs.getInt(1)));
                izvodjenje.setListaIzvodjaca(IzvodjacDAO.getIzvodjaciIzvodjenja(rs.getInt(1)));
                izvodjenja.add(izvodjenje);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return izvodjenja;

    }
    //Insert
    public static void insert(Izvodjenje izvodjenje) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into Izvodjenje(id, obrisano, vremeIzvodjenja, trajanje, tipIzvodjenja, " +
                        "brojPristupa, brojGlasova, ukupnoPristupa, pttBrojMesta) values (?,?,?,?,?,?,?,?,?)");
        ps.setInt(1, izvodjenje.getId());
        ps.setBoolean(2, false);
        if(izvodjenje.getVremeIzvodjenja()!=null) ps.setDate(3, (Date) izvodjenje.getVremeIzvodjenja()); else ps.setNull(3, Types.DATE);
        ps.setInt(4, izvodjenje.getTrajanje());
        if(izvodjenje.getTipIzvodjenja()!=null) ps.setString(5, izvodjenje.getTipIzvodjenja().toString()); else ps.setNull(5,Types.VARCHAR);
        ps.setInt(6, izvodjenje.getBrPristupa());
        ps.setInt(7, izvodjenje.getBrGlasova());
        ps.setInt(8, izvodjenje.getUkupnoPrisupa());
        if(izvodjenje.getMestoIzvodjenja()!=null) ps.setInt(9, izvodjenje.getMestoIzvodjenja().getPttBroj()); else ps.setNull(9, Types.INTEGER);
        ps.executeUpdate();
        ps.close();
    }
    //Update
    public static void update(Izvodjenje izvodjenje) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Izvodjenje set id=?, obrisano=?, vremeIzvodjenja=?, trajanje=?, tipIzvodjenja=?, " +
                        "brojPristupa=?, brojGlasova=?, ukupnoPristupa=?, pttBrojMesta=?");
        ps.setInt(1, izvodjenje.getId());
        ps.setBoolean(2, false);
        if(izvodjenje.getVremeIzvodjenja()!=null) ps.setDate(3, (Date) izvodjenje.getVremeIzvodjenja()); else ps.setNull(3, Types.DATE);
        ps.setInt(4, izvodjenje.getTrajanje());
        if(izvodjenje.getTipIzvodjenja()!=null) ps.setString(5, izvodjenje.getTipIzvodjenja().toString()); else ps.setNull(5,Types.VARCHAR);
        ps.setInt(6, izvodjenje.getBrPristupa());
        ps.setInt(7, izvodjenje.getBrGlasova());
        ps.setInt(8, izvodjenje.getUkupnoPrisupa());
        if(izvodjenje.getMestoIzvodjenja()!=null) ps.setInt(9, izvodjenje.getMestoIzvodjenja().getPttBroj()); else ps.setNull(9, Types.INTEGER);
        ps.executeUpdate();
        ps.close();
    }

    public static void updatePristup(Izvodjenje izvodjenje) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Izvodjenje set brojPristupa=? where id=?");
        ps.setInt(2, izvodjenje.getId());
        ps.setInt(1, izvodjenje.getBrPristupa());
        ps.executeUpdate();
        ps.close();
    }
    //Delete
    public static void delete(Izvodjenje izvodjenje) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Izvodjenje set obrisano=true where id=?");
        ps.setInt(1, izvodjenje.getId());
        ps.executeUpdate();
        ps.close();

    }
    //ToTable
    public static String[][] toTableData(List<Izvodjenje> izvodjenja){
        String[][] result=new String[izvodjenja.size()][7];
        for(int i=0;i<izvodjenja.size();i++){
            result[i][0]= String.valueOf(izvodjenja.get(i).getId());
            result[i][1]=izvodjenja.get(i).getTipIzvodjenja().toString();
            result[i][2]=izvodjenja.get(i).getMestoIzvodjenja().toString();
            result[i][3]=izvodjenja.get(i).getVremeIzvodjenja().toString();
            result[i][4]= String.valueOf(izvodjenja.get(i).getBrGlasova());
            result[i][5]= String.valueOf(izvodjenja.get(i).getBrPristupa());
            result[i][6]= String.valueOf(izvodjenja.get(i).getUkupnoPrisupa());
        }
        return result;
    }

    public static List<Izvodjenje> popuniListeIzvodjaca(Izvodjac izvodjac) {
        List<Izvodjenje> izvodjenja=new ArrayList<Izvodjenje>();
        Izvodjenje izvodjenje = null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select * from IzvodjacIzvodi where obrisano=false and idIzvodjaca = ?");
            ps.setInt(1, izvodjac.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                izvodjenje=getIzvodjenje(rs.getInt(2));
                izvodjenja.add(izvodjenje);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjenja;
    }

    public static List<Izvodjenje> getIzvodjenjaMuzickogDela(MuzickoDelo muzickoDelo) {
        List<Izvodjenje> izvodjenja=new ArrayList<Izvodjenje>();
        Izvodjenje izvodjenje = null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select * from IzvodjenjaMuzickogDela where idMuzickogDela = ?");
            ps.setInt(1, muzickoDelo.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                izvodjenje=getIzvodjenje(rs.getInt(1));
                izvodjenja.add(izvodjenje);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjenja;
    }
}
