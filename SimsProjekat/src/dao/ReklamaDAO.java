package dao;

import model.Izvodjenje;
import model.Reklama;
import model.enums.TipIzvodjenja;
import util.FConnection;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReklamaDAO {

    public static List<Reklama> getReklame(){
        List<Reklama> reklame=new ArrayList<Reklama>();
        Reklama reklama = null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,naziv,textReklame,cena,brojPristupa,datumIsteka,url from Reklama where obrisano=false");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                reklama=new Reklama();
                reklama.setId(rs.getInt(1));
                reklama.setNaziv(rs.getString(2));
                reklama.setText(rs.getString(3));
                reklama.setCena(rs.getDouble(4));
                reklama.setBrojPristupa(rs.getInt(5));
                reklama.setDatumIsteka(rs.getDate(6));
                reklama.setUrl(rs.getString(7));
                reklame.add(reklama);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reklame;
    }

    public static void updateSliku(int id, String path) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Reklama set slika=? where id=?");
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

    public static ImageIcon getSliku(Reklama r, String separator) {
        Statement st = null;
        ImageIcon retImageIcon = null;
        try {
            st = FConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery("select * from Reklama where id=" + r.getId());
            if(rs.next()) {
                byte[] img = rs.getBytes("slika");
                /*if(img == null) { //Nema potrebe
                    return new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "ikoneIzvodjenja" + separator +"default.jpg");
                }*/
                retImageIcon = new ImageIcon(img);
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retImageIcon;
    }

    public static Reklama getReklamaZaReklamniBafer(int idReklame, java.sql.Date danasnjiDatum){
        Reklama reklama = null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,naziv,textReklame,cena,brojPristupa,datumIsteka,url from Reklama where obrisano=false and id > ? and datumIsteka > ? order by id asc limit 1");
            ps.setInt(1, idReklame);
            ps.setDate(2, danasnjiDatum);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                reklama=new Reklama();
                reklama.setId(rs.getInt(1));
                reklama.setNaziv(rs.getString(2));
                reklama.setText(rs.getString(3));
                reklama.setCena(rs.getDouble(4));
                reklama.setBrojPristupa(rs.getInt(5));
                reklama.setDatumIsteka(rs.getDate(6));
                reklama.setUrl(rs.getString(7));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reklama;
    }

    //Eventualno se moze namestiti da se reklama dobavlja po drugim parametrima
    public static Reklama getReklama(int id){
        Reklama reklama=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,naziv,textReklame,cena,brojPristupa,datumIsteka,url from Reklama where id=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                reklama=new Reklama();
                reklama.setId(rs.getInt(1));
                reklama.setNaziv(rs.getString(2));
                reklama.setText(rs.getString(3));
                reklama.setCena(rs.getDouble(4));
                reklama.setBrojPristupa(rs.getInt(5));
                reklama.setDatumIsteka(rs.getDate(6));
                reklama.setUrl(rs.getString(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reklama;
    }

    public static void insert(Reklama reklama, int idAdmina) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into Reklama (naziv,textReklame,cena,brojPristupa,datumIsteka,idAdmina,url) values (?,?,?,?,?,?)");
        ps.setString(1, reklama.getNaziv());
        ps.setString(2, reklama.getText());
        ps.setDouble(3, reklama.getCena());
        ps.setInt(4, reklama.getBrojPristupa());
        ps.setDate(5, new java.sql.Date(reklama.getDatumIsteka().getTime()));
        ps.setInt(6, idAdmina);
        ps.setString(7, reklama.getUrl());
        ps.executeUpdate();
        ps.close();
    }

    public static void update(Reklama reklama) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Reklama set naziv=?,textReklame=?,cena=?,brojPristupa=?,datumIsteka=?,url=? where id=?");
        ps.setString(1, reklama.getNaziv());
        ps.setString(2, reklama.getText());
        ps.setDouble(3, reklama.getCena());
        ps.setInt(4, reklama.getBrojPristupa());
        ps.setDate(5, new java.sql.Date(reklama.getDatumIsteka().getTime()));
        ps.setInt(6, reklama.getId());
        ps.setString(7, reklama.getUrl());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(Reklama reklama)  throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Reklama set obrisano=true where id=?");
        ps.setInt(1, reklama.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static String[] columns(){
        return new String[]{"ID", "Naziv", "Text", "Datum isteka", "Broj pristupa", "Cena", "Url"};
    }

    public static String[][] toTableData(List<Reklama> reklame){
        String[][] result=new String[reklame.size()][7];
        for(int i=0;i<reklame.size();i++){
            result[i][0]=reklame.get(i).getId() + "";
            result[i][1]=reklame.get(i).getNaziv();
            result[i][2]=reklame.get(i).getText();
            result[i][3]=reklame.get(i).getDatumIsteka() + "";
            result[i][4]=reklame.get(i).getBrojPristupa()+"";
            result[i][5]=reklame.get(i).getCena()+"";
            result[i][6]=reklame.get(i).getUrl();
        }
        return result;
    }
}
