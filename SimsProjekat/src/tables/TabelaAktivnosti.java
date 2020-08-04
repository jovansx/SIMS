package tables;

import model.Recenzija;
import model.Urednik;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public class TabelaAktivnosti extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "Id", "Komentar", "Ocjena", "ProsjecnaOcjena"};

    private HashMap<Recenzija,Double> mapaRecenzija;
    private ArrayList<Recenzija> recenzije;

    public TabelaAktivnosti(HashMap<Recenzija,Double> mapaRecenzija,ArrayList<Recenzija> recenzije){
        super();
        this.mapaRecenzija=mapaRecenzija;
        this.recenzije=recenzije;
    }

    @Override
    public int getRowCount() {
        return recenzije.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recenzija r=recenzije.get(rowIndex);
        Double d=mapaRecenzija.get(r);
        switch (columnIndex) {
            case 0:
                return r.getId();
            case 1:
                return r.getKomentar();
            case 2:
                return r.getOcena();
            case 3:
                return d;

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
