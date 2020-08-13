package gui.panels;

import dao.RecenzijaDAO;
import dao.UrednikDAO;
import dao.ZadatakDAO;
import gui.dialogs.DialogOdobravanje;
import gui.dialogs.DialogZadaci;
import gui.dialogs.DialogZadatakDelo;
import gui.tables.TabelaOdobravanje;
import gui.tables.TabelaZadataka;
import model.Recenzija;
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
import java.sql.SQLException;
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
        //this.listaZadataka = listaZadataka;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        namesti();
    }
    public void namesti(){
        this.listaZadataka = (ArrayList<Zadatak>) ZadatakDAO.getZadatkePoUredniku(UrednikDAO.getUrednikPoId(id));
        TabelaZadataka t = new TabelaZadataka(listaZadataka);
        table = new JTable(t);

        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        final TableRowSorter<TableModel> tableSorter = new TableRowSorter<>();
        tableSorter.setModel(table.getModel());
        table.setRowSorter(tableSorter);
        table.getTableHeader().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }
        });


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

