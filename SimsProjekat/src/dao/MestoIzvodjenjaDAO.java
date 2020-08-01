package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MestoIzvodjenja;
import model.TopLista;
import util.FConnection;

public class MestoIzvodjenjaDAO {
    public static MestoIzvodjenja getMestoIzvodjenja(int pttBroj){
        MestoIzvodjenja mesto = null;
        try{
            PreparedStatement ps = FConnection.getInstance().prepareStatement("select pttBroj,drzava,nazivMesta from MestoIzvodjenja where pttBroj=?");
            ps.setInt(1, pttBroj);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                mesto = new MestoIzvodjenja();
                mesto.setPttBroj(rs.getInt(1));
                mesto.setDrzava(rs.getString(2));
                mesto.setNazivMesta(rs.getString(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesto;
    }
    public static void insert(MestoIzvodjenja mesto) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into mesto (pttBroj,drzava,nazivMesta) values (?,?,?)");
        ps.setInt(1, mesto.getPttBroj());
        ps.setString(2, mesto.getDrzava());
        ps.setString(3, mesto.getNazivMesta());
        ps.executeUpdate();
        ps.close();
    }
    public static void update(MestoIzvodjenja mesto) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update mesto set drzava=?,nazivMesta=? where pttBroj=?");
        ps.setString(1, mesto.getDrzava());
        ps.setString(2, mesto.getNazivMesta());
        ps.executeUpdate();
        ps.close();
    }
    public static String[] columns(){
        return new String[]{"Ptt broj", "Drzava","Naziv mesta"};
    }

    public static String[][] toTableData(List<MestoIzvodjenja> mesta){
        String[][] result=new String[mesta.size()][2];
        for(int i=0;i<mesta.size();i++){
            result[i][0]=String.valueOf(mesta.get(i).getPttBroj());
            result[i][1]=mesta.get(i).getDrzava();
            result[i][2] = mesta.get(i).getNazivMesta();
        }
        return result;
    }



}
