package gui.dialogs;

import gui.elementi.ElementOdobravanjaRecenzije;
import model.Recenzija;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DialogAdminovihRecenzija extends JDialog{
    public ArrayList<Recenzija> recenzije;
    private ArrayList<ElementOdobravanjaRecenzije> elementiRecenzije;
    private JPanel skrolPanel;
    private JScrollPane skrol;

    public DialogAdminovihRecenzija(ArrayList<Recenzija> recenzije){
        this.recenzije=recenzije;
        this.elementiRecenzije=new ArrayList<>();

        setTitle("Odobravanje recenzija");
        setSize(500, 650);
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
            ElementOdobravanjaRecenzije ez= new ElementOdobravanjaRecenzije(this, r);
            elementiRecenzije.add(ez);
            skrolPanel.add(ez);
            JLabel labela = new JLabel("                                        ");
            labela.setForeground(new Color(153, 204, 255));
            skrolPanel.add(labela);
        }

        skrolPanel.validate();
        skrolPanel.repaint();

        skrol.validate();
        skrol.repaint();

        skrol.setViewportView(skrolPanel);
        skrol.setPreferredSize(new Dimension(400, 540));
        add(skrol,BorderLayout.CENTER);
    }

    private void resetElemente() {
        elementiRecenzije.clear();
        skrolPanel.removeAll();
        skrolPanel.validate();
        skrolPanel.repaint();
    }

    public void deleteComponent(ElementOdobravanjaRecenzije elementOdobravanjaRecenzije) {
        this.remove(elementOdobravanjaRecenzije);
        skrolPanel.remove(elementOdobravanjaRecenzije);
        elementiRecenzije.remove(elementOdobravanjaRecenzije);
        skrolPanel.validate();
        skrolPanel.repaint();
    }
}
