package kontroler;

import dao.IzvodjenjeDAO;
import model.Izvodjenje;

import java.sql.SQLException;
import java.util.List;

public class GlavniProzorKON {

    public static List<Izvodjenje> pretrazi(String textPretrage, int brojElemenata, String filter, String filterZanra) throws SQLException {

        return IzvodjenjeDAO.getIzvodjenjaPretrage(textPretrage, brojElemenata, filter, filterZanra);
    }

    public static List<Izvodjenje> dobaviIzvodjenja(String parametar, int brojElemenata) throws SQLException {

        return IzvodjenjeDAO.getIzvodjenjaZaPocetnuStranicu(brojElemenata, parametar);
    }
}
