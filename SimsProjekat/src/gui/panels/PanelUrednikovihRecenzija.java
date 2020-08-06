package gui.panels;

import gui.dialogs.DialogUrednikovihRecenzija;
import model.Recenzija;
import gui.tables.TabelaAktivnosti;

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
import java.util.HashMap;

public class PanelUrednikovihRecenzija extends JPanel {
    private DialogUrednikovihRecenzija dialog;
    public ArrayList<Recenzija> recenzije;
    public JTable tabela;
    public JButton uredu;
    public HashMap<Recenzija, Double> ocjene;

    public PanelUrednikovihRecenzija(DialogUrednikovihRecenzija dr, ArrayList<Recenzija> recenzije, HashMap<Recenzija, Double> prosjecneOcjene){
        this.dialog=dr;
        this.recenzije=recenzije;
        this.ocjene=prosjecneOcjene;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(229, 255, 204));
        setLayout(null);
        namesti();
    }

    private void namesti() {
        TabelaAktivnosti t = new TabelaAktivnosti(ocjene,recenzije);
        tabela=new JTable(t);

        JScrollPane sp = new JScrollPane(tabela);
        sp.setBounds(30, 30, 630, 450);
        add(sp);

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);

        final TableRowSorter<TableModel> tableSorter = new TableRowSorter<>();
        tableSorter.setModel(tabela.getModel());
        tabela.setRowSorter(tableSorter);


        tabela.getTableHeader().addMouseListener(new MouseListener() {

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

        uredu=new JButton("U redu");
        uredu.setForeground(Color.white);
        uredu.setBounds(555, 520, 90, 30);
        uredu.setBackground(new Color(51, 102, 153));
        uredu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
        add(uredu);
    }

}
