package gui.tables;

import model.Urednik;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaUrednika extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "Id","Ime", "Prezime","Email","Telefon","Korisnicko Ime", "Broj Recenzija"};

    private ArrayList<Urednik> listaUrednika;

    public TabelaUrednika(ArrayList<Urednik> urednici){
        super();
        this.listaUrednika=urednici;
    }

    @Override
    public int getRowCount() {
        return listaUrednika.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Urednik u = listaUrednika.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return u.getId();
            case 1:
                return u.getIme();
            case 2:
                return u.getPrezime();
            case 3:
                return u.getEmail();
            case 4:
                return u.getKontaktTelefon();
            case 5:
                return u.getNalog().getKorisnickoIme();
            case 6:
                return u.getListaRecenzija().size();
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
