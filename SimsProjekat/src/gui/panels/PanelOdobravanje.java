package gui.panels;

import gui.dialogs.DialogOdobravanje;
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
import java.util.ArrayList;

public class PanelOdobravanje extends JPanel {
    private DialogOdobravanje dialog;
    public JPanel dodatno;
    private JButton odobri, ne;
    public JTable table;
    public ArrayList<Recenzija>listaRecenzija;

    public PanelOdobravanje(DialogOdobravanje dialog, ArrayList<Recenzija> listaRecenzija) {
        this.dialog = dialog;
        this.listaRecenzija = listaRecenzija;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(226, 206, 158));
        //setLayout(null);
        namesti();

    }
    public void namesti() {

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
                }
            }
        });
        dodatno.add(odobri);
        add(dodatno, BorderLayout.SOUTH);
    }

}