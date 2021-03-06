package dao;

import model.Izvodjenje;
import model.TopLista;
import util.FConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopListaDAO {

    public static List<TopLista> getTopListe() {
        List<TopLista> topListe = new ArrayList<TopLista>();
        TopLista topLista = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,datumGlasanja from TopLista where obrisano=false");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                topLista = new TopLista();
                topLista.setId(rs.getInt(1));
                topLista.setDatumGlasanja(rs.getDate(2));
                topLista.setListaIzvodjenja(IzvodjenjeDAO.getIzvodjenjaTopListe(topLista));
                topListe.add(topLista);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topListe;
    }

    //Eventualno se moze namestiti da se top lista dobavlja po drugim parametrima
    //Ako zatreba, samo ucitj njena izvodjenja
    public static TopLista getTopLista(Date datum){
        TopLista topLista=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select datumGlasanja,id from TopLista where datumGlasanja=? and obrisano=false");
            ps.setDate(1, datum);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                topLista=new TopLista();
                topLista.setDatumGlasanja(rs.getDate(1));
                topLista.setId(rs.getInt(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topLista;
    }

    public static void insert(TopLista topLista) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into TopLista (datumGlasanja) values (?)");
        ps.setDate(1, (Date) topLista.getDatumGlasanja());
        ps.executeUpdate();
        /*for (Izvodjenje izvodjenje: topLista.getListaIzvodjenja()) {
            ps=FConnection.getInstance()
                    .prepareStatement("insert into IzvodjenjaUTopListi (idIzvodjenja,idTopListe) values (?,?)");
            ps.setInt(1, izvodjenje.getId());
            ps.setInt(2, topLista.getId());
            ps.executeUpdate();
        }*/
        ps.close();
    }
    public static void insert2(Izvodjenje izvodjenje,int id) throws SQLException {
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into IzvodjenjaUTopListi (idIzvodjenja,idTopListe) values (?,?)");
        ps.setInt(1, izvodjenje.getId());
        ps.setInt(2, id);
        ps.executeUpdate();
        ps.close();
    }

    public static void update(TopLista topLista) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update TopLista set datumGlasanja=? where id=?");
        ps.setDate(1, (Date) topLista.getDatumGlasanja());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(TopLista topLista)  throws SQLException {
        PreparedStatement ps = FConnection.getInstance()
                .prepareStatement("update muzicki_sistem.IzvodjenjaUTopListi set obrisano=true where idTopListe=?");
        ps.setInt(1, topLista.getId());
        ps.executeUpdate();
        ps = FConnection.getInstance()
                .prepareStatement("update TopLista set obrisano=true where id=?");
        ps.setInt(1, topLista.getId());
        ps.executeUpdate();
        ps.close();
    }

    public static String[] columns(){
        String[] result={"ID", "Datum glasanja"};
        return result;
    }

    public static String[][] toTableData(List<TopLista> topListe){
        String[][] result=new String[topListe.size()][2];
        for(int i=0;i<topListe.size();i++){
            result[i][0]=topListe.get(i).getId() + "";
            result[i][1]=topListe.get(i).getDatumGlasanja().toString();
        }
        return result;
    }
}
