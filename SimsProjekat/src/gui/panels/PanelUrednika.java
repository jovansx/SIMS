package gui.panels;

import gui.dialogs.DialogUrednika;
import model.Recenzija;
import model.Urednik;
import tables.TabelaAktivnosti;
import tables.TabelaUrednika;

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

public class PanelUrednika extends JPanel {
    private DialogUrednika dialog;
    public ArrayList<Urednik> urednici;
    public JButton ocjene, sadrzaj;


    public PanelUrednika(DialogUrednika dialog, ArrayList<Urednik> urednici){
        this.dialog=dialog;
        this.urednici=urednici;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(229, 255, 204));
        setLayout(null);
        namesti();
    }

    private void namesti() {

        TabelaUrednika tabelaUrednika=new TabelaUrednika(urednici);
        JTable tabela =new JTable(tabelaUrednika);
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





    }


}
