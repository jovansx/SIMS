package gui.panels;

import gui.dialogs.DialogSadrzaja;
import gui.dialogs.DialogZahteva;
import model.Zahtev;
import tables.TabelaOdobravanje;
import tables.TabelaZahteva;

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

public class PanelZahteva extends JPanel {
    private DialogZahteva dialog;
    public ArrayList<Zahtev> zahtevi;
    private JButton odobri, otkazi;
    public JTable tabela;

    public PanelZahteva(DialogZahteva dz, ArrayList<Zahtev> zahtevi){
        this.dialog=dz;
        this.zahtevi=zahtevi;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(229, 255, 204));
        setLayout(null);
        namesti();

    }
    public void namesti(){
        TabelaZahteva t = new TabelaZahteva(zahtevi);
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



        odobri=new JButton("Odgovori");
        odobri.setBackground(new Color(51, 102, 153));
        odobri.setForeground(Color.white);
        odobri.setBounds(140, 510, 90, 30);
        odobri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
        add(odobri);

        otkazi=new JButton("Otkazi");
        otkazi.setForeground(Color.white);
        otkazi.setBounds(435, 510, 90, 30);
        otkazi.setBackground(new Color(51, 102, 153));
        otkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
        add(otkazi);
    }
}
