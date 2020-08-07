package gui.dialogs;

import dao.IzvodjacDAO;
import dao.MuzickoDeloDAO;
import dao.UcesnikDAO;
import dao.ZanrDAO;
import gui.elementi.AdminovProzor;
import gui.elementi.ElementZadatka;
import kontroler.AdminovProzorKON;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
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
    private int brojElemenata;

    public DialogZadataka(){
        this.djela= MuzickoDeloDAO.getNedovrsenaMuzickaDela();
        this.izvodjaci= IzvodjacDAO.getNedovrseneIzvodjace();
        this.ucesnici= UcesnikDAO.getNedovrseneUceniske();
        this.zanrovi= ZanrDAO.getNedovrseneZanrove();
        this.brojElemenata=4;
        this.zadaci=new ArrayList<>();

        AdminovProzorKON.resetZadatke();

        setTitle("Zadaci");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 710);
        setLocationRelativeTo(null);

        skrol=new JScrollPane();
        skrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        skrolPanel=new JPanel();
        skrolPanel.setLayout(new BoxLayout(skrolPanel, BoxLayout.Y_AXIS));
        ucitajSkrol();
        skrol.setViewportView(skrolPanel);
        skrol.setPreferredSize(new Dimension(400, 540));
        add(skrol,BorderLayout.CENTER);


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
    }

    private void resetElemente(){
        zadaci.clear();
        skrolPanel.removeAll();
        skrolPanel.validate();
        skrolPanel.repaint();
    }

}