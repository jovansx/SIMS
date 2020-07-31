package dao;

import model.Reklama;
import util.FConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReklamaDAO {

    public static List<Reklama> getReklame(){
        List<Reklama> reklame=new ArrayList<Reklama>();
        Reklama reklama = null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,naziv,textReklame,cena,brojPristupa,brojPrikaza from Reklama where obrisano=false");
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
    //Eventualno se moze namestiti da se reklama dobavlja po drugim parametrima
    public static Reklama getReklama(int id){
        Reklama reklama=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,naziv,textReklame,cena,brojPristupa,brojPrikaza from Reklama where id=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                reklama=new Reklama();
                reklama.setId(rs.getInt(1));
                reklama.setNaziv(rs.getString(2));
                reklama.setText(rs.getString(3));
                reklama.setCena(rs.getDouble(4));
                reklama.setBrojPristupa(rs.getInt(5));
                reklama.setBrojPrikaza(rs.getInt(6));
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
                .prepareStatement("insert into Reklama (naziv,textReklame,cena,brojPristupa,brojPrikaza,idAdmina) values (?,?,?,?,?,?)");
        ps.setString(1, reklama.getNaziv());
        ps.setString(2, reklama.getText());
        ps.setDouble(3, reklama.getCena());
        ps.setInt(4, reklama.getBrojPristupa());
        ps.setInt(5, reklama.getBrojPrikaza());
        ps.setInt(6, idAdmina);
        ps.executeUpdate();
        ps.close();
    }

    public static void update(Reklama reklama) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Reklama set naziv=?,textReklame=?,cena=?,brojPristupa=?,brojPrikaza=? where id=?");
        ps.setString(1, reklama.getNaziv());
        ps.setString(2, reklama.getText());
        ps.setDouble(3, reklama.getCena());
        ps.setInt(4, reklama.getBrojPristupa());
        ps.setInt(5, reklama.getBrojPrikaza());
        ps.setInt(6, reklama.getId());
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
        String[] result={"ID", "Naziv", "Text", "Broj prikaza", "Broj pristupa", "Cena"};
        return result;
    }

    public static String[][] toTableData(List<Reklama> reklame){
        String[][] result=new String[reklame.size()][6];
        for(int i=0;i<reklame.size();i++){
            result[i][0]=reklame.get(i).getId() + "";
            result[i][1]=reklame.get(i).getNaziv();
            result[i][2]=reklame.get(i).getText();
            result[i][3]=reklame.get(i).getBrojPrikaza() + "";
            result[i][4]=reklame.get(i).getBrojPristupa()+"";
            result[i][5]=reklame.get(i).getCena()+"";
        }
        return result;
    }
}
