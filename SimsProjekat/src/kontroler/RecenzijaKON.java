package kontroler;

import dao.RecenzijaDAO;
import gui.enums.TipRecenzije;

public class RecenzijaKON {

    public static void upisiPodatke(int ocena, String komentar, int idSadrzaja, int idAutora, TipRecenzije tip) throws Exception {
        if(ocena == 0) throw new Exception("0");
        if(komentar.equals("")) throw new Exception("prazno");

        RecenzijaDAO.napraviRecenziju(ocena, komentar, idSadrzaja, idAutora, tip);
    }
}
