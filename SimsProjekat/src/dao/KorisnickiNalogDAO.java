package dao;

import model.*;
import model.enums.TipKorisnika;
import util.FConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KorisnickiNalogDAO {
    public static KorisnickiNalog getNalog(Integer id){
        KorisnickiNalog korisnik=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,korisnickoIme,lozinka, tipKorisnika from KorisnickiNalog where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                korisnik = new KorisnickiNalog();
                korisnik.setId(rs.getInt(1));
                korisnik.setKorisnickoIme(rs.getString(2));
                korisnik.setLozinka(rs.getString(3));
                korisnik.setKorisnik(TipKorisnika.valueOf(rs.getString(4)));
                if (rs.getString(4).equals("ADMINISTRATOR")){
                    korisnik.setOsoba(getAdmin(rs.getInt(1)));
                }
                else if (rs.getString(4).equals("UREDNIK")){
                    korisnik.setOsoba(getUrednik(rs.getInt(1)));
                }
                else if(rs.getString(4).equals("REGISTROVANI")){
                    korisnik.setOsoba(getRegistrovaniKorisnik(rs.getInt(1)));
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return korisnik;
    }

    public static KorisnickiNalog getNalogPoKorisnickomImenu(String korIme){
        KorisnickiNalog nalog=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,korisnickoIme,lozinka,tipKorisnika from KorisnickiNalog where korisnickoIme=? and obrisano=false");
            ps.setString(1, korIme);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                nalog = new KorisnickiNalog();
                nalog.setId(rs.getInt(1));
                nalog.setKorisnickoIme(rs.getString(2));
                nalog.setLozinka(rs.getString(3));
                nalog.setKorisnik(TipKorisnika.valueOf(rs.getString(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nalog;
    }
    public static List<KorisnickiNalog> getNalozi(){
        List<KorisnickiNalog> nalozi=new ArrayList<>();
        KorisnickiNalog korisnik=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,korisnickoIme,lozinka, tipKorisnika from KorisnickiNalog");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                korisnik = new KorisnickiNalog();
                korisnik.setId(rs.getInt(1));
                korisnik.setKorisnickoIme(rs.getString(2));
                korisnik.setLozinka(rs.getString(3));
                korisnik.setKorisnik(TipKorisnika.valueOf(rs.getString(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nalozi;
    }
    //Nadji administratora
    public static Administrator getAdmin(int id){
        Administrator admin=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email, kontaktTelefon, godinaRodjenja, idNaloga, " +
                            "obrisano from Administrator where idNaloga=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                if(!rs.getBoolean(8)){
                    admin=new Administrator();
                    admin.setId(rs.getInt(1));
                    admin.setIme(rs.getString(2));
                    admin.setPrezime(rs.getString(3));
                    admin.setEmail(rs.getString(4));
                    admin.setKontaktTelefon(rs.getString(5));
                    admin.setGodinaRodjenja(rs.getDate(6));
                    //admin.setIzlazeReklame(AdministratorDAO.getReklame(id));
                    //admin.setListaZahteva(AdministratorDAO.getZahteve(id));
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    //Nadji Nalog Admina
    public static KorisnickiNalog getNalogAdmina(Integer id){
        KorisnickiNalog nalog=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select k.id, k.korisnickoIme, k.lozinka, k.tipKorisnika from korisnickinalog k, " +
                            "administrator a where a.idNaloga=k.id and a.id=?;");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                nalog = new KorisnickiNalog();
                nalog.setId(rs.getInt(1));
                nalog.setKorisnickoIme(rs.getString(2));
                nalog.setLozinka(rs.getString(3));
                nalog.setKorisnik(TipKorisnika.valueOf(rs.getString(4)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nalog;

    }
    //Nadji urednika
    public static Urednik getUrednik(int id){
        Urednik urednik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id, ime, prezime, email, kontaktTelefon, godinaRodjenja, idNaloga " +
                            "from Urednik where idNaloga=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                urednik = new Urednik(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), null, rs.getDate(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return urednik;
    }
    //Nadji registrovanogKorisnika
    public static RegistrovaniKorisnik getRegistrovaniKorisnik(int id) {
        RegistrovaniKorisnik korisnik = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,ime,prezime,email,kontaktTelefon,godinaRodjenja,idNaloga,jeVidljiv from RegistrovaniKorisnik where idNaloga=? and obrisano=false");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                korisnik=new RegistrovaniKorisnik();
                korisnik.setId(rs.getInt(1));
                korisnik.setIme(rs.getString(2));
                korisnik.setPrezime(rs.getString(3));
                korisnik.setEmail(rs.getString(4));
                korisnik.setKontaktTelefon(rs.getString(5));
                korisnik.setGodinaRodjenja(rs.getDate(6));
                korisnik.setJeVidljiv(rs.getBoolean(8));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return korisnik;
    }

    /**
     * Param: korIme - Korisnicko ime
     * return: true - Ne postoji to vec korisnicko ime
     *         false - Postoji vec to korisnicko ime
     */
    private static boolean proveriKorisnickoIme(String korIme) throws SQLException {
        PreparedStatement ps = FConnection.getInstance().
                prepareStatement("select * from KorisnickiNalog where korisnickoIme=?");
        ps.setString(1, korIme);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return false;
        }
        rs.close();
        ps.close();
        return true;
    }

    public static KorisnickiNalog insertValues(String korIme, String lozinka, TipKorisnika tip) throws Exception {

        if(!KorisnickiNalogDAO.proveriKorisnickoIme(korIme)) {  //Ako vec postoji korisnicko ime
            throw new Exception(String.valueOf(3));
        }

        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("insert into KorisnickiNalog(korisnickoIme,lozinka,tipKorisnika) values (?,?,?)");
            ps.setString(1, korIme);
            ps.setString(2, lozinka);
            ps.setString(3, tip.toString());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }

        return KorisnickiNalogDAO.getNalogPoKorisnickomImenu(korIme);
    }

    public static Korisnik getPrijavljeniKorisnik(String korIme, String sifra) {

        Korisnik korisnik = null;

        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select id,tipKorisnika from KorisnickiNalog where korisnickoIme=? and lozinka=? and obrisano=false");
            ps.setString(1, korIme);
            ps.setString(2, sifra);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                if(rs.getString(2).equals("REGISTROVANI")) {
                    korisnik = KorisnickiNalogDAO.getRegistrovaniKorisnik(rs.getInt(1));
                }else if(rs.getString(2).equals("UREDNIK")) {
                    korisnik = KorisnickiNalogDAO.getUrednik(rs.getInt(1));
                }else {
                    korisnik = KorisnickiNalogDAO.getAdmin(rs.getInt(1));
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return korisnik;
    }

    //Insert
    public static void insert(KorisnickiNalog nalog) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into KorisnickiNalog(id, korisnickoIme, lozinka, tipKorisnika) values (?,?,?,?)");
        ps.setInt(1, nalog.getId());
        if(nalog.getKorisnickoIme()!=null) ps.setString(2, nalog.getKorisnickoIme()); else ps.setNull(2, Types.VARCHAR);
        if(nalog.getLozinka()!=null) ps.setString(3, nalog.getLozinka()); else ps.setNull(3, Types.VARCHAR);
        if(nalog.getKorisnik()!=null) ps.setString(4, nalog.getKorisnik().toString()); else ps.setNull(4,Types.VARCHAR);
        ps.executeUpdate();
        ps.close();
    }
    //Update
    public static void update(KorisnickiNalog nalog) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update KorisnickiNalog set id=?, korisnickoIme=?, lozinka=?, tipKorisnika=?");
        ps.setInt(1, nalog.getId());
        if(nalog.getKorisnickoIme()!=null) ps.setString(2, nalog.getKorisnickoIme()); else ps.setNull(2, Types.VARCHAR);
        if(nalog.getLozinka()!=null) ps.setString(3, nalog.getLozinka()); else ps.setNull(3, Types.VARCHAR);
        if(nalog.getKorisnik()!=null) ps.setString(4, nalog.getKorisnik().toString()); else ps.setNull(4,Types.VARCHAR);
        ps.executeUpdate();
        ps.close();
    }

    public static void updateLozinka(int id, String lozinka){
        PreparedStatement ps= null;
        try {
            ps = FConnection.getInstance()
                    .prepareStatement("update KorisnickiNalog set lozinka=? where id=?");
            ps.setString(1, lozinka);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //ToTable
    public static String[][] toTableData(List<KorisnickiNalog> nalozi){
        String[][] result=new String[nalozi.size()][4];
        for(int i=0;i<nalozi.size();i++){
            result[i][0]= String.valueOf(nalozi.get(i).getId());
            result[i][1]=nalozi.get(i).getKorisnickoIme();
            result[i][2]=nalozi.get(i).getLozinka();
            result[i][3]=nalozi.get(i).getKorisnik().toString();
        }
        return result;
    }
}
