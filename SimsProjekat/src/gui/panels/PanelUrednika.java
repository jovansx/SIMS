package gui.panels;

import dao.RecenzijaDAO;
import gui.dialogs.DialogUrednika;
import gui.dialogs.DialogUrednikovihRecenzija;
import kontroler.AdminovProzorKON;
import model.Recenzija;
import model.Urednik;
import gui.tables.TabelaUrednika;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

//Panel za  prikaz urednika i broja recenzija koje su napisali
public class PanelUrednika extends JPanel {
    private DialogUrednika dialog;
    public ArrayList<Urednik> urednici;



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

        JLabel labela = new JLabel("Da biste vidjeli recenzije urednika pritisnite na zeljenjog urednika");
        labela.setBounds(32, 20, 400, 30);
        labela.setForeground(new Color(51, 102, 153));
        add(labela);

        TabelaUrednika tabelaUrednika=new TabelaUrednika(urednici);
        JTable tabela =new JTable(tabelaUrednika);
        JScrollPane sp = new JScrollPane(tabela);
        sp.setBounds(30, 60, 630, 450);
        add(sp);

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);

        final TableRowSorter<TableModel> tableSorter = new TableRowSorter<>();
        tableSorter.setModel(tabela.getModel());
        tabela.setRowSorter(tableSorter);


        tabela.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                int row= tabela.rowAtPoint(me.getPoint());
                ArrayList<Recenzija> recenzije = (ArrayList<Recenzija>) RecenzijaDAO.getRecenzijeUrednika((int)tabela.getValueAt(row,0));
                HashMap<Recenzija, Double> ocjene= AdminovProzorKON.getProsjecneOcjeneKorisnika(recenzije);
                DialogUrednikovihRecenzija dr=new DialogUrednikovihRecenzija(recenzije, ocjene);
                dr.setVisible(true);
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
