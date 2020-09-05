package dao;

import model.Izvodjenje;
import model.PlejLista;
import model.Reklama;
import model.TopLista;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

        //Funkcija koja na osnovu idKorisnika dobavlja njegove plejliste koje posjeduje
        public static List<PlejLista> plejlisteKorisnika(int id){
            List<PlejLista> liste=new ArrayList<>();
            PlejLista p=null;
            try {
                PreparedStatement ps= FConnection.getInstance()
                        .prepareStatement("select id,jeJavna, naziv from PlayLista where idKorisnika=? and obrisano=false");
                ps.setInt(1, id);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    p = new PlejLista();
                    p.setId(rs.getInt(1));
                    p.setJeJavna(rs.getBoolean(2));
                    p.setNaziv(rs.getString(3));
                    p.setListaIzvodjenja(IzvodjenjeDAO.izvodjenjaUPlejlisti(rs.getInt(1)));
                    liste.add(p);
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return liste;
        }
        public static void insert(PlejLista plejlista, int id) throws SQLException{
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("insert into PlayLista (jeJavna,naziv, idKorisnika) values (?,?,?)");
            ps.setBoolean(1, plejlista.isJeJavna());
            ps.setString(2, plejlista.getNaziv());
            ps.setInt(3, id);
            ps.executeUpdate();
            ps.close();
        }
        public static void insertIzvodjenje(PlejLista plejlista, Izvodjenje izvodjenje) throws SQLException {
            PreparedStatement ps=FConnection.getInstance()
                    .prepareStatement("insert into IzvodjenjaPlayListi (idPlayListe,idIzvodjenja, obrisano) values (?,?,?)");
            ps.setInt(1, plejlista.getId());
            ps.setInt(2, izvodjenje.getId());
            ps.setBoolean(3, false);
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
