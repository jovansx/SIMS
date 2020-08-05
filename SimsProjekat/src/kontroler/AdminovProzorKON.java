package kontroler;

import dao.RecenzijaDAO;
import dao.UrednikDAO;
import model.Recenzija;
import model.Urednik;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminovProzorKON {
    public static ArrayList<Urednik> getUrednici(){
        ArrayList<Urednik> urednici= (ArrayList<Urednik>) UrednikDAO.getUrednike();
        for(Urednik u: urednici){
            u.setListaRecenzija((ArrayList<Recenzija>) RecenzijaDAO.getRecenzijeUrednika(u.getId()));
        }
        return urednici;
    }

    public static HashMap<Recenzija, Double> getProsjecneOcjeneKorisnika(ArrayList<Recenzija> recenzije){
        Double d=0.0;
        HashMap<Recenzija, Double> prosjecneOcjene=new HashMap<>();

        for(Recenzija r: recenzije){
            if(r.getMuzickoDelo()==null){
                prosjecneOcjene.put(r, RecenzijaDAO.getProsjecnaOcjenaPoIdIzvodjenja(r.getIzvodnjenje().getId()));
            }
            else {
                prosjecneOcjene.put(r, RecenzijaDAO.getProsjecnaOcjenaPoIdDela(r.getMuzickoDelo().getId()));
            }
        }

        return prosjecneOcjene;
    }

}
