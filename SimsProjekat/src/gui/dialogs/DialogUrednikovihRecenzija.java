package gui.dialogs;

import dao.RecenzijaDAO;
import gui.elementi.ElementUrednika;
import gui.elementi.ElementUrednikovihRecenzija;
import gui.panels.PanelUrednikovihRecenzija;
import model.Recenzija;
import model.Urednik;

import javax.swing.*;
import java.awt.*;
import java.nio.file.DirectoryNotEmptyException;
import java.util.ArrayList;
import java.util.HashMap;

public class DialogUrednikovihRecenzija extends JDialog {
    public ArrayList<Recenzija> recenzije;
    public HashMap<Recenzija, Double> ocjene;
    public ArrayList<ElementUrednikovihRecenzija> elementiRecenzija;
    private JPanel skrolPanel;
    private JScrollPane skrol;

    public DialogUrednikovihRecenzija(ArrayList<Recenzija> recenzije, HashMap<Recenzija, Double> ocjene){
        this.recenzije=recenzije;
        this.ocjene=ocjene;
        this.elementiRecenzija=new ArrayList<>();

        setTitle("Pregled urednikovih recenzija");
        setSize(600, 650);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        skrol=new JScrollPane();
        skrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        skrolPanel=new JPanel();
        skrolPanel.setLayout(new BoxLayout(skrolPanel, BoxLayout.Y_AXIS));
        ucitajSkrol();

    }

    private void ucitajSkrol() {
        resetElemente();

        for(Recenzija r: recenzije){
            ElementUrednikovihRecenzija ez= new ElementUrednikovihRecenzija(this, r, ocjene.get(r));
            elementiRecenzija.add(ez);
            skrolPanel.add(ez);
            JLabel labela = new JLabel("                                        ");
            labela.setForeground(new Color(77, 121, 255));
            skrolPanel.add(labela);
        }

        skrolPanel.validate();
        skrolPanel.repaint();

        skrol.validate();
        skrol.repaint();

        skrol.setViewportView(skrolPanel);
        skrol.setPreferredSize(new Dimension(600, 170));
        add(skrol,BorderLayout.CENTER);
    }

    private void resetElemente() {
        elementiRecenzija.clear();
        skrolPanel.removeAll();
        skrolPanel.validate();
        skrolPanel.repaint();
    }
}
