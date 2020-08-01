package dao;

import model.PlejLista;
import model.TopLista;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlejListaDAO {
        public static PlejLista getPlejLista(int id){
            PlejLista plejlista = null;
            try{
                PreparedStatement ps = FConnection.getInstance().prepareStatement("select id,obrisano,jeJavna,naziv from PlayLista where id=?");
                ps.setInt(1, id);
                ResultSet rs=ps.executeQuery();
                if(rs.next()) {
                    if (rs.getBoolean(2)) {
                        return null;
                    } else {
                        plejlista = new PlejLista();
                        plejlista.setId(rs.getInt(1));
                        plejlista.setJeJavna(rs.getBoolean(3));
                        plejlista.setNaziv(rs.getString(4));
                    }
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return plejlista;
        }
        public static void insert(PlejLista plejlista) throws SQLException{
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("insert into PlayLista (id,jeJavna,naziv) values (?,?,?)");
            ps.setInt(1,plejlista.getId());
            ps.setBoolean(2, plejlista.isJeJavna());
            ps.setString(3, plejlista.getNaziv());
            ps.executeUpdate();
            ps.close();
        }
        public static void update(PlejLista plejlista) throws SQLException{
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("update PlayLista set jeJavna=?,naziv=? where id=?");
            ps.setBoolean(1,plejlista.isJeJavna());
            ps.setString(2,plejlista.getNaziv());
            ps.executeUpdate();
            ps.close();
        }
        public static void delete(PlejLista plejLista) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update PlayLista  set obrisano = true where id=?");
        ps.setInt(1, plejLista.getId());
        ps.executeUpdate();
        ps.close();
        }
        public static String[] columns(){
            return new String[]{"ID", "Naziv"};
        }

        public static String[][] toTableData(List<PlejLista> plejliste){
        String[][] result=new String[plejliste.size()][2];
        for(int i=0;i<plejliste.size();i++){
            result[i][0]=String.valueOf(plejliste.get(i).getId());
            result[i][1]=plejliste.get(i).getNaziv();
        }
        return result;
        }
}
