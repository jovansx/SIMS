package gui.panels;

import gui.dialogs.DialogAdminovihRecenzija;
import model.Recenzija;
import gui.tables.TabelaRecenzijaUrednika;

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

public class PanelAdminovihRecenzija extends JPanel {
    public DialogAdminovihRecenzija dialog;
    public ArrayList<Recenzija> recenzije;
    private JButton posalji, otkazi;
    public JTable tabela;
    public JLabel labela;

    public PanelAdminovihRecenzija(DialogAdminovihRecenzija dialog, ArrayList<Recenzija> recenzije){
        this.dialog=dialog;
        this.recenzije=recenzije;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(229, 255, 204));
        setLayout(null);
        namesti();

    }

    public void namesti(){
        if(recenzije!=null){
            TabelaRecenzijaUrednika t = new TabelaRecenzijaUrednika(recenzije);
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

        }
        else{
            labela = new JLabel("Ne postoji nijedna recenzija!");
            labela.setBounds(140, 240, 400, 30);
            add(labela);
        }



        posalji=new JButton("Odgovori");
        posalji.setForeground(Color.white);
        posalji.setBackground(new Color(51, 102, 153));
        posalji.setBounds(140, 500, 90, 30);
        posalji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
        add(posalji);

        otkazi=new JButton("Otkazi");
        otkazi.setForeground(Color.white);
        otkazi.setBounds(435, 500, 90, 30);
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
