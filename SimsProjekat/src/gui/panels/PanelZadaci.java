package gui.panels;

import dao.UrednikDAO;
import dao.ZadatakDAO;
import gui.dialogs.*;
import gui.tables.TabelaZadataka;
import model.Zadatak;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

public class PanelZadaci extends JPanel {

    private DialogZadaci dialog;
    public JPanel dodatno;
    private JButton prihvati;
    public static JTable table;
    public ArrayList<Zadatak> listaZadataka;
    public int id;

    public PanelZadaci(DialogZadaci dialog, int idUrednika) {
        this.id = idUrednika;
        this.dialog = dialog;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        namesti();
    }
    public void namesti(){
        listaZadataka = (ArrayList<Zadatak>) ZadatakDAO.getZadatkePoUredniku(UrednikDAO.getUrednikPoId(id));
        TabelaZadataka t = new TabelaZadataka(listaZadataka);
        table = new JTable(t);

        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        final TableRowSorter<TableModel> tableSorter = new TableRowSorter<>();
        tableSorter.setModel(table.getModel());
        table.setRowSorter(tableSorter);
        dodatno = new JPanel(new FlowLayout(FlowLayout.LEFT));
        prihvati= new JButton("    Prihvati    ");
        prihvati.setBackground(Color.white);
        prihvati.setOpaque(true);
        prihvati.setBorder(BorderFactory.createLineBorder(Color.black));

        prihvati.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int rIndex = table.getSelectedRow();
                if (rIndex < 0) {
                    JOptionPane.showMessageDialog(PanelZadaci.this, "Morate selektovati bar jedan zadatak!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int id = (int) table.getModel().getValueAt(rIndex,0);
                    Zadatak z = ZadatakDAO.getZadatakPoId(id);
                    if(!Objects.isNull(z.getDelo())){
                        DialogZadatakDelo dzd = new DialogZadatakDelo(z.getId());
                        dzd.setVisible(true);
                        listaZadataka.remove(z);
                        PanelZadaci.refreshData();
                    }if(!Objects.isNull(z.getIzvodjac())){
                        DialogZadatakIzvodjac dzi = new DialogZadatakIzvodjac(z.getId());
                        dzi.setVisible(true);
                        listaZadataka.remove(z);
                        PanelZadaci.refreshData();
                    }if(!Objects.isNull(z.getZanr())){
                        DialogZadatakZanr dzz = new DialogZadatakZanr(z.getId());
                        dzz.setVisible(true);
                        listaZadataka.remove(z);
                        PanelZadaci.refreshData();
                    }if(!Objects.isNull(z.getUcesnik())){
                        DialogZadatakUcesnik dzu= new DialogZadatakUcesnik(z.getId());
                        dzu.setVisible(true);
                        listaZadataka.remove(z);
                        PanelZadaci.refreshData();
                    }


                }
            }
        });
        dodatno.add(prihvati);
        add(dodatno, BorderLayout.SOUTH);
    }

    public static  void refreshData() {
        TabelaZadataka t = (TabelaZadataka) table.getModel();
        t.fireTableDataChanged();
    }



}

