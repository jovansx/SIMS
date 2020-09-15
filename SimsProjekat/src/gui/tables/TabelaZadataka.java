package gui.tables;

import model.Zadatak;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Objects;


public class TabelaZadataka extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "Id", "Text","Delo","Zanr","Ucesnik","Izvodjac"};

    private ArrayList<Zadatak>listaZadataka;

    public TabelaZadataka(ArrayList<Zadatak> listaZadataka){
        super();
        this.listaZadataka = listaZadataka;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return listaZadataka.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Zadatak z = listaZadataka.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return z.getId();
            case 1:
                return z.getText();

            case 2:
                if(Objects.isNull(z.getDelo())){
                    return '/';
                }else{
                    return z.getDelo().getId();
                }

            case 3:
                if(Objects.isNull(z.getZanr())){
                    return '/';
                }else{
                    return z.getZanr().getNazivZanra();
                }

            case 4:
                if(Objects.isNull(z.getUcesnik())){
                    return '/';
                }else{
                    return z.getUcesnik().getId();
                }
            case 5:
                if(Objects.isNull(z.getIzvodjac())){
                    return '/';
                }else{
                    return z.getIzvodjac().getId();
                }
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