package dao;

import model.PlejLista;
import model.Recenzija;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecenzijaDAO {
    public static Recenzija getRecenzija(int id){
        Recenzija recenzija = null;
        try{
            PreparedStatement ps = FConnection.getInstance().prepareStatement("select id,obrisano,ocena,komentar,idMuzickogDela,idIzvodjenja,idKorisnika,idUrednika,odobreno from Recenzija where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                if (rs.getBoolean(2)) {
                    return null;
                } else {
                    recenzija = new Recenzija();
                    recenzija.setId(rs.getInt(1));
                    recenzija.setOcena(rs.getInt(3));
                    recenzija.setKomentar(rs.getString(4));
                    recenzija.setMuzickoDelo(MuzickoDeloDAO.getMuzickoDelo(rs.getInt(5)));
                    recenzija.setIzvodnjenje(IzvodjenjeDAO.getIzvodjenje(rs.getInt(6)));
                    recenzija.setAutorRecenzije(RegistrovaniKorisnikDAO.getRegistrovaniKorisnik(rs.getInt(7)));
                    recenzija.setUrednik(UrednikDAO.getUrednikPoId(rs.getInt(8)));
                    recenzija.setOdobreno(rs.getBoolean(9));
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recenzija;
    }
    public static List<Recenzija> getRecenzijeKojeJeUrednikKreirao(){
        List<Recenzija> recenzije = new ArrayList<Recenzija>();
        Recenzija recenzija=null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,ocena,komentar,idMuzickogDela,idIzvodjenja,idKorisnika," +
                            "idUrednika,odobreno from Recenzija where idKorisnika is null and obrisano=false");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                recenzija = new Recenzija();
                recenzija.setId(rs.getInt(1));
                recenzija.setOcena(rs.getInt(2));
                recenzija.setKomentar(rs.getString(3));
                recenzija.setMuzickoDelo(MuzickoDeloDAO.getMuzickoDelo(rs.getInt(4)));
                recenzija.setIzvodnjenje(IzvodjenjeDAO.getIzvodjenje(rs.getInt(5)));
                recenzija.setAutorRecenzije(null);
                recenzija.setOdobreno(rs.getBoolean(8));
                recenzija.setUrednik(UrednikDAO.getUrednikPoId(rs.getInt(7)));
                recenzije.add(recenzija);

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recenzije;
    }
    public static List<Recenzija> getRecenzijeUrednika(int id){
        List<Recenzija> recenzije = new ArrayList<Recenzija>();
        Recenzija recenzija=null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,ocena,komentar,idMuzickogDela,idIzvodjenja,idKorisnika," +
                            "idUrednika,odobreno from Recenzija where idUrednika=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                recenzija = new Recenzija();
                recenzija.setId(rs.getInt(1));
                recenzija.setOcena(rs.getInt(2));
                recenzija.setKomentar(rs.getString(3));
                recenzija.setMuzickoDelo(MuzickoDeloDAO.getMuzickoDelo(rs.getInt(4)));
                recenzija.setIzvodnjenje(IzvodjenjeDAO.getIzvodjenje(rs.getInt(5)));
                recenzija.setAutorRecenzije(RegistrovaniKorisnikDAO.getRegistrovaniKorisnik(rs.getInt(6)));
                recenzija.setOdobreno(rs.getBoolean(8));
                recenzija.setUrednik(null);
                recenzije.add(recenzija);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recenzije;
    }
    public static double getProsjecnaOcjenaPoIdIzvodjenja(int id){
        double d=0.0;
        try{
            PreparedStatement ps = FConnection.getInstance().prepareStatement("select avg(ocena) from Recenzija where idIzvodjenja=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                d=rs.getDouble(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static double getProsjecnaOcjenaPoIdDela(int id){
        double d=0.0;
        try{
            PreparedStatement ps = FConnection.getInstance().prepareStatement("select avg(ocena) from Recenzija where idMuzickogDela=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                d=rs.getDouble(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static List<Recenzija> getRecenzijeIzvodjenja(int idIzvodjenja, int limit){
        List<Recenzija> recenzije = new ArrayList<Recenzija>();
        Recenzija recenzija = null;
        try{
            PreparedStatement ps = FConnection.getInstance().prepareStatement("select * from muzicki_sistem.Recenzija where obrisano = false and idIzvodjenja=? limit ?");
            ps.setInt(1, idIzvodjenja);
            ps.setInt(2, limit);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                recenzija = new Recenzija();
                recenzija.setId(rs.getInt(1));
                recenzija.setOcena(rs.getInt(3));
                recenzija.setKomentar(rs.getString(4));
                recenzija.setMuzickoDelo(null);
                recenzija.setIzvodnjenje(IzvodjenjeDAO.getIzvodjenje(rs.getInt(6)));
                if (rs.getInt(7) > 0){
                    recenzija.setAutorRecenzije(RegistrovaniKorisnikDAO.getRegistrovaniKorisnik(rs.getInt(7)));
                } else {
                    recenzija.setUrednik(UrednikDAO.getUrednikPoId(rs.getInt(8)));
                }
                recenzije.add(recenzija);
                }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recenzije;
    }



    public static void insert(Recenzija recenzija) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into Recenzija (id,ocena,komentar,idMuzickogDela,idIzvodjenja,idKorisnika,idUrednika,odobreno from Recenzija where id=?");
        ps.setInt(2,recenzija.getOcena());
        ps.setString(3,recenzija.getKomentar());
        ps.setInt(4,recenzija.getMuzickoDelo().getId());
        ps.setInt(5,recenzija.getIzvodnjenje().getId());
        ps.setInt(6,recenzija.getAutorRecenzije().getId());
        ps.setInt(7,recenzija.getUrednik().getId());
        ps.setBoolean(8, recenzija.isOdobreno());
        ps.executeUpdate();
        ps.close();
    }
    public static void update(Recenzija recenzija) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Recenzija set ocena=?,komentar=? where id=?");
        ps.setInt(1,recenzija.getOcena());
        ps.setString(2,recenzija.getKomentar());
        ps.executeUpdate();
        ps.close();
    }

    public static void delete(Recenzija recenzija) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Recenzija  set obrisano = true where id=?");
        ps.setInt(1, recenzija.getId());
        ps.executeUpdate();
        ps.close();
    }
    public static String[] columns(){
        return new String[]{"ID", "Ocena","Komentar","IdMuzickogDela","IdKorisnika","IdIzvodjenja","IdUrednika","Odobreno"};
    }

    public static String[][] toTableData(List<Recenzija> recenzije){
        String[][] result=new String[recenzije.size()][2];
        for(int i=0;i<recenzije.size();i++){
            result[i][0] = String.valueOf(recenzije.get(i).getId());
            result[i][1] = String.valueOf(recenzije.get(i).getOcena());
            result[i][2] = recenzije.get(i).getKomentar();
            result[i][3] = String.valueOf(recenzije.get(i).getMuzickoDelo());
            result[i][4] = String.valueOf(recenzije.get(i).getAutorRecenzije());
            result[i][5] = String.valueOf(recenzije.get(i).getIzvodnjenje());
            result[i][6] = String.valueOf(recenzije.get(i).getUrednik());
            result[i][7] = String.valueOf(recenzije.get(i).isOdobreno());
        }
        return result;
    }

    public static List<Recenzija> getRecenzijeMuzickogDela(int idDela, int limit) {
        List<Recenzija> recenzije = new ArrayList<Recenzija>();
        Recenzija recenzija = null;
        try{
            PreparedStatement ps = FConnection.getInstance().prepareStatement("select * from muzicki_sistem.Recenzija where obrisano = false and idMuzickogDela=? limit ?");
            ps.setInt(1, idDela);
            ps.setInt(2, limit);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                recenzija = new Recenzija();
                recenzija.setId(rs.getInt(1));
                recenzija.setOcena(rs.getInt(3));
                recenzija.setKomentar(rs.getString(4));
                recenzija.setMuzickoDelo(null);
                recenzija.setIzvodnjenje(IzvodjenjeDAO.getIzvodjenje(rs.getInt(6)));
                if (rs.getInt(7) > 0){
                    recenzija.setAutorRecenzije(RegistrovaniKorisnikDAO.getRegistrovaniKorisnik(rs.getInt(7)));
                } else {
                    recenzija.setUrednik(UrednikDAO.getUrednikPoId(rs.getInt(8)));
                }
                recenzije.add(recenzija);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recenzije;
    }
}
