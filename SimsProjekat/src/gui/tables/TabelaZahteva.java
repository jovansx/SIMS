package gui.tables;

import model.Zahtev;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaZahteva extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "Id", "Naziv","Korisnik","Odobren","Obradjen"};

    private ArrayList<Zahtev> listaZahteva;

    public TabelaZahteva(ArrayList<Zahtev> zahtevi){
        super();
        this.listaZahteva = zahtevi;
    }

    @Override
    public int getRowCount() {
        return listaZahteva.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Zahtev z = listaZahteva.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return z.getId();
            case 1:
                return z.getNaziv();
            case 2:
                return z.getPodnosilacZahteva().getNalog().getKorisnickoIme();
            case 3:
                return z.isJeOdobren();
            case 4:
                return z.isJeObradjen();

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
