package dao;

import model.Album;
import model.Izvodjac;
import model.Izvodjenje;
import model.MuzickoDelo;
import model.enums.TipIzvodjaca;
import model.enums.TipIzvodjenja;
import util.FConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IzvodjacDAO {
    public static Izvodjac getIzvodjac(Integer id){
        Izvodjac izvodjac=null;

        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivIzvodjaca,tip, opis, pripadaGrupi from Izvodjac where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                izvodjac=new Izvodjac();
                izvodjac.setId(rs.getInt(1));
                izvodjac.setNazivIzvodjaca(rs.getString(2));
                izvodjac.setTipIzvodjaca(TipIzvodjaca.valueOf(rs.getString(3)));
                izvodjac.setOpis(rs.getString(4));


                if(rs.getInt(5) > 0){
                    izvodjac.setPripadaGrupi(getGrupa(rs.getInt(5)));
                } else {
                    izvodjac.setPripadaGrupi(null);
                }

                izvodjac.setImaClanove(getClanoveIzvodjaca(izvodjac.getId()));

                //izvodjac.setListaIzvodjenja(IzvodjenjeDAO.getIzvodjenjaIzvodjaca(izvodjac.getId()));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjac;
    }

    private static List<Izvodjac> getClanoveIzvodjaca(int idIzvodjaca) {
        List<Izvodjac> izvodjaci = new ArrayList<Izvodjac>();
        Izvodjac izvodjac=null;

        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivIzvodjaca,tip, opis, pripadaGrupi from Izvodjac where pripadaGrupi=?");
            ps.setInt(1, idIzvodjaca);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                izvodjac=new Izvodjac();
                izvodjac.setId(rs.getInt(1));
                izvodjac.setNazivIzvodjaca(rs.getString(2));
                izvodjac.setTipIzvodjaca(TipIzvodjaca.valueOf(rs.getString(3)));
                izvodjac.setOpis(rs.getString(4));


                if(rs.getInt(5) > 0){
                    izvodjac.setPripadaGrupi(getGrupa(rs.getInt(5)));
                } else {
                    izvodjac.setPripadaGrupi(null);
                }

                izvodjac.setImaClanove(getClanoveIzvodjaca(izvodjac.getId()));

                izvodjaci.add(izvodjac);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjaci;
    }

    //Trazi grupu kojoj pripada izvodjac
    public static Izvodjac getGrupa(Integer id){
        Izvodjac izvodjac=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivIzvodjaca,tip, opis, pripadaGrupi from Izvodjac where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                izvodjac=new Izvodjac();
                izvodjac.setId(rs.getInt(1));
                izvodjac.setNazivIzvodjaca(rs.getString(2));
                izvodjac.setTipIzvodjaca(TipIzvodjaca.valueOf(rs.getString(3)));
                izvodjac.setOpis(rs.getString(4));
                izvodjac.setPripadaGrupi(null);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjac;
    }
    //Lista svih izvodjaca, bez liste izvodjenja i podizvodjaca
    public static List<Izvodjac> getIzvodjaci(){
        Izvodjac izvodjac=null;
        List<Izvodjac> izvodjaci = new ArrayList<>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivIzvodjaca,tip, opis, pripadaGrupi from Izvodjac");

            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                izvodjac=new Izvodjac();
                izvodjac.setId(rs.getInt(1));
                izvodjac.setNazivIzvodjaca(rs.getString(2));
                izvodjac.setTipIzvodjaca(TipIzvodjaca.valueOf(rs.getString(3)));
                izvodjac.setOpis(rs.getString(4));
                izvodjac.setPripadaGrupi(getIzvodjac(rs.getInt(1)));
                izvodjaci.add(izvodjac);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjaci;
    }

    public static List<Izvodjac> getIzvodjaciIzvodjenja(int idIzvodjenja) {
        List<Izvodjac> izvodjaci = new ArrayList<Izvodjac>();
        Izvodjac izvodjac = null;
        try {
            PreparedStatement ps = FConnection.getInstance()
                    .prepareStatement("select * from IzvodjacIzvodi where obrisano=false and idIzvodjenja=?");
            ps.setInt(1, idIzvodjenja);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                izvodjac = IzvodjacDAO.getIzvodjac(rs.getInt(1));
                izvodjaci.add(izvodjac);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return izvodjaci;
    }

    //Trazi izvodjenja koja taj izvodjac izvodi
    public static List<Izvodjenje> getIzvodjenja(Integer id){
        Izvodjenje izvodjenje=null;
        List<Izvodjenje> izvodjenja = new ArrayList<>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select i.id, i.obrisano, i.vremeIzvodjenja, i.trajanje, i.tipIzvodjenja, " +
                            "i.brojPristupa, i.brojGlasova, i.ukupnoPristupa from Izvodjenje i,IzvodjacIzvodi ii where ii.idIzvodjenja=i.id and ii.idIzvodjaca=? ");

            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(!rs.getBoolean(2)){
                    izvodjenje=new Izvodjenje();
                    izvodjenje.setId(rs.getInt(1));
                    izvodjenje.setVremeIzvodjenja(rs.getDate(3));
                    izvodjenje.setTrajanje(rs.getInt(4));
                    izvodjenje.setTipIzvodjenja(TipIzvodjenja.valueOf(rs.getString(5)));
                    izvodjenje.setBrPristupa(rs.getInt(6));
                    izvodjenje.setBrGlasova(rs.getInt(7));
                    izvodjenje.setUkupnoPrisupa(rs.getInt(8));
                    izvodjenja.add(izvodjenje);
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjenja;
    }

    //Trazi izvodjace koji su u tom sastavu
    public static List<Izvodjac> getSastavGrupe(Integer id){
        Izvodjac izvodjac=null;
        List<Izvodjac> izvodjaci = new ArrayList<>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivIzvodjaca,tip, opis, pripadaGrupi from Izvodjac where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                izvodjac=new Izvodjac();
                izvodjac.setId(rs.getInt(1));
                izvodjac.setNazivIzvodjaca(rs.getString(2));
                izvodjac.setTipIzvodjaca(TipIzvodjaca.valueOf(rs.getString(3)));
                izvodjac.setOpis(rs.getString(4));
                izvodjac.setPripadaGrupi(getGrupa(rs.getInt(1)));
                izvodjaci.add(izvodjac);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjaci;
    }

    //Insert
    public static void insert(Izvodjac izvodjac) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("insert into Izvodjac(id, nazivIzvodjaca, tip, opis, pripadaGrupi) values (?,?,?,?,?)");
        ps.setInt(1, izvodjac.getId());
        if(izvodjac.getNazivIzvodjaca()!=null) ps.setString(2, izvodjac.getNazivIzvodjaca()); else ps.setNull(2, Types.VARCHAR);
        if(izvodjac.getTipIzvodjaca()!=null) ps.setString(3, izvodjac.getTipIzvodjaca().toString()); else ps.setNull(3, Types.DATE);
        if(izvodjac.getOpis()!=null) ps.setString(4, izvodjac.getOpis()); else ps.setNull(4,Types.VARCHAR);
        if(izvodjac.getPripadaGrupi()!=null) ps.setInt(5, izvodjac.getPripadaGrupi().getId()); else ps.setNull(5, Types.INTEGER);
        ps.executeUpdate();
        ps.close();
    }
    //Update
    public static void update(Izvodjac izvodjac) throws SQLException{
        PreparedStatement ps=FConnection.getInstance()
                .prepareStatement("update Izvodjac set id=?, nazivIzvodjaca=?, tip=?, opis=?, pripadaGrupi=?");
        ps.setInt(1, izvodjac.getId());
        if(izvodjac.getNazivIzvodjaca()!=null) ps.setString(2, izvodjac.getNazivIzvodjaca()); else ps.setNull(2, Types.VARCHAR);
        if(izvodjac.getTipIzvodjaca()!=null) ps.setString(3, izvodjac.getTipIzvodjaca().toString()); else ps.setNull(3, Types.DATE);
        if(izvodjac.getOpis()!=null) ps.setString(4, izvodjac.getOpis()); else ps.setNull(4,Types.VARCHAR);
        if(izvodjac.getPripadaGrupi()!=null) ps.setInt(5, izvodjac.getPripadaGrupi().getId()); else ps.setNull(5, Types.INTEGER);
        ps.executeUpdate();
        ps.close();
    }
    //ToTable
    public static String[][] toTableData(List<Izvodjac> izvodjaci){
        String[][] result=new String[izvodjaci.size()][5];
        for(int i=0;i<izvodjaci.size();i++){
            result[i][0]= String.valueOf(izvodjaci.get(i).getId());
            result[i][1]=izvodjaci.get(i).getNazivIzvodjaca();
            result[i][2]=izvodjaci.get(i).getTipIzvodjaca().toString();
            result[i][3]=izvodjaci.get(i).getOpis();
            result[i][4]= izvodjaci.get(i).getPripadaGrupi().toString();
        }
        return result;
    }

}


