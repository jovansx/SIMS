package tables;

import model.Recenzija;
import model.Zahtev;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaRecenzijaUrednika extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "Id","Kreator","Muzicko Delo","ID Izvodjenja","Odobrena"};

    private ArrayList<Recenzija> listaRecenzija;

    public TabelaRecenzijaUrednika(ArrayList<Recenzija> recenzije){
        super();
        this.listaRecenzija = recenzije;
    }

    @Override
    public int getRowCount() {
        return listaRecenzija.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Recenzija r = listaRecenzija.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return r.getId();
            case 1:
                if(r.getUrednik()!=null) {
                    return r.getUrednik().getNalog().getKorisnickoIme();
                }
                else{
                    return "-";
                }
            case 2:
                if(r.getMuzickoDelo()!=null){
                    return r.getMuzickoDelo().getNazivDela();
                }
                else{
                    return "-";
                }
            case 3:
                if(r.getIzvodnjenje()!=null){
                    return r.getIzvodnjenje().getId();
                }
                else{
                    return "-";
                }
            case 4:
                return r.isOdobreno();

            default:
                break;
        }
        return null;
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


}
