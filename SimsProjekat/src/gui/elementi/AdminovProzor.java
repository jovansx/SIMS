package gui.elementi;

import dao.RecenzijaDAO;
import dao.UrednikDAO;
import dao.ZahtevDAO;
import gui.dialogs.*;
import kontroler.AdminovProzorKON;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminovProzor extends GlavniProzor{
    JButton profil,inbox,zadaci,promenaLozinke,odobravanje, sadrzaj, pregled;

    public AdminovProzor(){

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

        zadaci=new JButton("Zadaci");
        zadaci.setPreferredSize(new Dimension(100,40));
        zadaci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        inbox=new JButton("Obrada zahteva");
        inbox.setPreferredSize(new Dimension(100,40));
        inbox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogZahteva dz= new DialogZahteva((ArrayList<Zahtev>) ZahtevDAO.getZahteve());
                dz.setVisible(true);
            }
        });

        panelAkcija.add(inbox);

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
                DialogAdminovihRecenzija dar = new DialogAdminovihRecenzija((ArrayList<Recenzija>) RecenzijaDAO.getRecenzijeKojeJeUrednikKreirao());
                dar.setVisible(true);
            }
        });

        panelAkcija.add(odobravanje);

        pregled=new JButton("Pregled urednika");
        pregled.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogUrednika du = new DialogUrednika(AdminovProzorKON.getUrednici());
                du.setVisible(true);
            }
        });
        panelAkcija.add(pregled);
    }


}
