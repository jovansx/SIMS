package kontroler;

import dao.IzvodjenjeDAO;
import model.Izvodjenje;

import java.sql.SQLException;
import java.util.List;

public class GlavniProzorKON {

    public static List<Izvodjenje> pretrazi(String textPretrage, int brojElemenata) throws SQLException {
        List<Izvodjenje> izvodjenja = null;
        izvodjenja = IzvodjenjeDAO.getIzvodjenjaPretrage(textPretrage, brojElemenata);
        return izvodjenja;
    }
}
