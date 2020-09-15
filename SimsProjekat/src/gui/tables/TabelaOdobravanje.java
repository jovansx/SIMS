package gui.tables;

import model.Recenzija;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Objects;


public class TabelaOdobravanje extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "Id", "Muzicko delo","Izvodjenje","Autor recenzije","Urednik","Ocena","Komentar"};

    private ArrayList<Recenzija>listaRecenzija;

    public TabelaOdobravanje(ArrayList<Recenzija> listaRecenzija){
        super();
        this.listaRecenzija = listaRecenzija;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return listaRecenzija.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Recenzija r = listaRecenzija.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getId();
            case 1:
                if(Objects.isNull(r.getMuzickoDelo())){
                    return '/';
                }else{
                    return r.getMuzickoDelo().getNazivDela();
                }
            case 2:
                if(Objects.isNull(r.getIzvodnjenje())){
                    return '/';
                }else{
                    return r.getIzvodnjenje().getId();
                }

            case 3:
                if(Objects.isNull(r.getAutorRecenzije())){
                    return '/';
                }else{
                    return r.getAutorRecenzije().getId();
                }

            case 4:
                if(Objects.isNull(r.getUrednik())){
                    return '/';
                }else{
                    return r.getUrednik().getId();
                }
            case 5:
                return r.getOcena();
            case 6:
                return r.getKomentar();

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