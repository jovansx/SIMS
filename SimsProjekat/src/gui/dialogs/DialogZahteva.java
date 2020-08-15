package gui.dialogs;

import gui.elementi.ElementZahtjeva;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DialogZahteva extends JDialog {
    private JPanel panel;
    private List<ElementZahtjeva> zadaci;
    private JButton posalji, otkazi;
    private JPanel skrolPanel;
    private JScrollPane skrol;
    public ArrayList<Zahtev> zahtevi;


    public DialogZahteva(ArrayList<Zahtev> zahtevi){
        this.zahtevi=zahtevi;
        zadaci=new ArrayList<>();

        setTitle("Obrada zahteva");
        setSize(500, 650);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ucitajSkrol();
    }

    private void ucitajSkrol() {
        skrol=new JScrollPane();
        skrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        skrolPanel=new JPanel();
        skrolPanel.setLayout(new BoxLayout(skrolPanel, BoxLayout.Y_AXIS));

        resetElemente();

        for( Zahtev z: zahtevi){
            ElementZahtjeva ez= new ElementZahtjeva(z, this);
            zadaci.add(ez);
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

    private void resetElemente(){
        zadaci.clear();
        skrolPanel.removeAll();
        skrolPanel.validate();
        skrolPanel.repaint();
    }
}
