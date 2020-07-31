package dao;

import model.Izvodjac;
import model.enums.TipIzvodjaca;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                izvodjac.setPripadaGrupi(getGrupa(id));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjac;
    }

    public static Izvodjac getGrupa(Integer id){
        Izvodjac izvodjac=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,nazivIzvodjaca,tip, opis, pripadaGrupi from Izvodjac where pripadaGrupi=?");
            ps.setInt(5, id);
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
}

