package gui.dialogs;

import dao.IzvodjacDAO;
import dao.MuzickoDeloDAO;
import dao.UcesnikDAO;
import dao.ZanrDAO;
import gui.elementi.ElementZadatka;
import kontroler.AdminovProzorKON;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DialogZadataka extends JDialog {
    private JPanel panel;
    private List<ElementZadatka> zadaci;
    private JButton posalji, otkazi;
    private JPanel skrolPanel;
    private JScrollPane skrol;
    private List<Zanr> zanrovi;
    private List<Ucesnik> ucesnici;
    private List<Izvodjac> izvodjaci;
    private List<MuzickoDelo> djela;

    public DialogZadataka(List<Zanr> zanrovi, List<Ucesnik> ucesnici, List<Izvodjac> izvodjaci, List<MuzickoDelo> djela){
        this.djela= djela;
        this.izvodjaci= izvodjaci;
        this.ucesnici= ucesnici;
        this.zanrovi= zanrovi;
        this.zadaci=new ArrayList<>();

        setTitle("Zadaci");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 710);
        setLocationRelativeTo(null);

        AdminovProzorKON.resetZadatke();

        ucitajSkrol();
        ucitajDugmad();

    }

    private void ucitajDugmad() {

        panel = new JPanel();
        panel.setBackground(new Color(0, 77, 102));

        posalji=new JButton("U redu");
        posalji.setBackground(new Color(153, 204, 255));
        posalji.setForeground(Color.white);
        posalji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(posalji);

        otkazi=new JButton("Otkazi");
        otkazi.setForeground(Color.white);
        otkazi.setBackground(new Color(153, 204, 255));
        otkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminovProzorKON.resetZadatke();
                setVisible(false);
            }
        });
        panel.add(otkazi);
        panel.setPreferredSize(new Dimension(400, 70));
        add(panel,BorderLayout.SOUTH);
    }

    private void ucitajSkrol() {
        skrol=new JScrollPane();
        skrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        skrolPanel=new JPanel();
        skrolPanel.setLayout(new BoxLayout(skrolPanel, BoxLayout.Y_AXIS));

        resetElemente();

        for(MuzickoDelo md: djela){
            Zadatak z= new Zadatak(md);
            ElementZadatka ez= new ElementZadatka(z, this);
            zadaci.add(ez);
            skrolPanel.add(ez);
            JLabel labela = new JLabel("                                        ");
            labela.setForeground(new Color(153, 204, 255));
            skrolPanel.add(labela);
        }
        for(Izvodjac i: izvodjaci){
            Zadatak z= new Zadatak(i);
            ElementZadatka ez=new ElementZadatka(z, this);
            zadaci.add(ez);
            skrolPanel.add(ez);
            JLabel labela = new JLabel("                                         ");
            labela.setForeground(new Color(153, 204, 255));
            skrolPanel.add(labela);
        }
        for(Ucesnik u: ucesnici){
            Zadatak z= new Zadatak(u);
            ElementZadatka ez= new ElementZadatka(z, this);
            zadaci.add(ez);
            skrolPanel.add(ez);
            JLabel labela = new JLabel("                                         ");
            labela.setForeground(new Color(153, 204, 255));
            skrolPanel.add(labela);
        }
        for(Zanr zanr: zanrovi){
            Zadatak z= new Zadatak(zanr);
            ElementZadatka ez= new ElementZadatka(z, this);
            zadaci.add(ez);
            skrolPanel.add(ez);
            JLabel labela = new JLabel("                                          ");
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
