package dao;

import model.Izvodjac;
import model.Izvodjenje;

import model.enums.TipIzvodjenja;
import util.FConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IzvodjenjeDAO {
    public static Izvodjenje getIzvodjenje(Integer id){
        Izvodjenje izvodjenje=null;
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select id,vremeIzvodjenja,trajanje,tipIzvodjenja, brojPristupa, brojGlasova, ukupnoPristupa, idMesta from Izvodjenje where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                izvodjenje=new Izvodjenje();
                izvodjenje.setId(rs.getInt(1));
                izvodjenje.setVremeIzvodjenja(rs.getDate(2));
                izvodjenje.setTrajanje(rs.getInt(3));
                izvodjenje.setTipIzvodjenja(TipIzvodjenja.valueOf(rs.getString(4)));
                izvodjenje.setBrPristupa(rs.getInt(5));
                izvodjenje.setBrGlasova(rs.getInt(6));
                izvodjenje.setUkupnoPrisupa(rs.getInt(7));
                //Treba dodati mestoIzvodjenja
                izvodjenje.setMestoIzvodjenja(null);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjenje;
    }

    public static List<Izvodjac> getizvodjaci(Integer id){
        Izvodjac izvodjac =null;
        List<Izvodjac> izvodjaci=new ArrayList<Izvodjac>();
        try {
            PreparedStatement ps= FConnection.getInstance()
                    .prepareStatement("select idIzvodjaca, idIzvodjenja from IzvodjacIzvodi where idIzvodjenja=?");
            ps.setInt(2, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                izvodjac=IzvodjacDAO.getIzvodjac(rs.getInt(1));
                izvodjaci.add(izvodjac);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izvodjaci;
    }
    //Treba dodati jos za muzickaDela i recenzije
}
