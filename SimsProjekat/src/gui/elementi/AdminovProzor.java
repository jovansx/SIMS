package gui.elementi;

import gui.dialogs.DialogProfil;
import gui.dialogs.DialogProfilAdmin;
import gui.dialogs.DialogSadrzaja;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdminovProzor extends GlavniProzor{
    JButton profil,inbox,zadaci,promenaLozinke,odobravanje, sadrzaj;
    ArrayList<Recenzija> recenzije;
    ArrayList<Zanr> zanrovi;
    ArrayList<Ucesnik> ucesnici;
    ArrayList<Izvodjac> izvodjaci;
    ArrayList<MuzickoDelo> djela;
    public AdminovProzor(ArrayList<Recenzija> recenzije, ArrayList<Zanr> zanrovi, ArrayList<Ucesnik> ucesnici, ArrayList<Izvodjac> izvodjaci, ArrayList<MuzickoDelo> djela){
        this.recenzije=recenzije;
        this.zanrovi=zanrovi;
        this.djela=djela;
        this.izvodjaci=izvodjaci;
        this.ucesnici=ucesnici;

        panelAkcija.setLayout(new BoxLayout(panelAkcija,BoxLayout.PAGE_AXIS));
        profil = new JButton("Profil");
        profil.setPreferredSize(new Dimension(100,40));
        profil.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogProfilAdmin dp = new DialogProfilAdmin();
                dp.setVisible(true);

            }

        });

        panelAkcija.add(profil);

        inbox=new JButton("Inbox");
        inbox.setPreferredSize(new Dimension(100,40));
        inbox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ;

            }
        });

        panelAkcija.add(inbox);

        zadaci=new JButton("Zadaci");
        zadaci.setPreferredSize(new Dimension(100,40));
        zadaci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
        panelAkcija.add(zadaci);

        sadrzaj=new JButton("Dodaj sadrzaj");
        sadrzaj.setPreferredSize(new Dimension(100,40));
        sadrzaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogSadrzaja ds = new DialogSadrzaja();
                ds.setVisible(true);
            }
        });

        panelAkcija.add(sadrzaj);

        promenaLozinke=new JButton("Promena lozinke");
        promenaLozinke.setPreferredSize(new Dimension(100,40));
        promenaLozinke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });

        panelAkcija.add(promenaLozinke);

        odobravanje=new JButton("Odobri rezenciju");
        odobravanje.setPreferredSize(new Dimension(100,40));
        odobravanje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });

        panelAkcija.add(odobravanje);


    }

}
