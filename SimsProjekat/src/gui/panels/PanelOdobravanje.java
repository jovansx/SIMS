package gui.panels;

import dao.RecenzijaDAO;
import gui.dialogs.DialogOdobravanje;
import kontroler.UrednikovProzorKON;
import model.Recenzija;
import gui.tables.TabelaOdobravanje;

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

public class PanelOdobravanje extends JPanel {
    private DialogOdobravanje dialog;
    public JPanel dodatno;
    private JButton odobri, ne;
    public JTable table;
    public ArrayList<Recenzija>listaRecenzija;
    public int id;

    public PanelOdobravanje(DialogOdobravanje dialog, int idUrednika) {
        this.dialog = dialog;
        this.id= idUrednika;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        namesti();

    }
    public void namesti() {
        listaRecenzija = (ArrayList<Recenzija>) RecenzijaDAO.getRecenzijeUrednika(id);

        TabelaOdobravanje t = new TabelaOdobravanje(listaRecenzija);
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
        odobri = new JButton("    Odobri    ");
        odobri.setBackground(Color.white);
        odobri.setOpaque(true);
        odobri.setBorder(BorderFactory.createLineBorder(Color.black));

        odobri.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int rIndex = table.getSelectedRow();
                if (rIndex < 0) {
                    JOptionPane.showMessageDialog(PanelOdobravanje.this, "Morate selektovati bar jednu recenziju!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int id = (int) table.getModel().getValueAt(rIndex,0);
                    Recenzija r = RecenzijaDAO.getRecenzija(id);
                    try {
                        UrednikovProzorKON.odluka(true,r);
                        JOptionPane.showMessageDialog(PanelOdobravanje.this, "Recenzija je odobrena!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    listaRecenzija.remove(r);
                    refreshData();


                }
            }
        });
        dodatno.add(odobri);

        ne = new JButton("   Ne odobri    ");
        ne.setBackground(Color.white);
        ne.setOpaque(true);
        ne.setBorder(BorderFactory.createLineBorder(Color.black));

        ne.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int rIndex = table.getSelectedRow();
                if (rIndex < 0) {
                    JOptionPane.showMessageDialog(PanelOdobravanje.this, "Morate selektovati bar jednu recenziju!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int id = (int) table.getModel().getValueAt(rIndex,0);
                    Recenzija r = RecenzijaDAO.getRecenzija(id);
                    try {
                        UrednikovProzorKON.odluka(false,r);
                        JOptionPane.showMessageDialog(PanelOdobravanje.this, "Recenzija nije odobrena!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    refreshData();

                }
            }
        });
        dodatno.add(ne);
        add(dodatno, BorderLayout.SOUTH);
    }

    public void refreshData() {
        TabelaOdobravanje t = (TabelaOdobravanje) table.getModel();
        t.fireTableDataChanged();
    }


}