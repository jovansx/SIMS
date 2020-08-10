package gui.elementi;

import dao.*;
import gui.dialogs.*;
import kontroler.AdminovProzorKON;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdminovProzor extends GlavniProzor{
    JButton profil,inbox,zadaci,promenaLozinke,odobravanje, sadrzaj, pregled;
    Administrator admin;

    public AdminovProzor(Administrator administrator) {
        super();
        this.admin=administrator;
        inicijalizuj();
    }

    public AdminovProzor(){}

    private void inicijalizuj() {
        panelOperacija.remove(prijavaButton);
        panelOperacija.remove(registracijaButton);
        odjavaButton.setVisible(true);

        panelAkcija.setLayout(new BoxLayout(panelAkcija,BoxLayout.PAGE_AXIS));
        profil = new JButton("         Profil        ");
        profil.setPreferredSize(new Dimension(100,40));
        profil.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogProfilAdmin dp = new DialogProfilAdmin(admin);
                dp.setVisible(true);

            }

        });

        panelAkcija.add(profil);

        zadaci=new JButton("       Zadaci        ");
        zadaci.setPreferredSize(new Dimension(100,40));
        zadaci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziZadatke();

            }
        });
        panelAkcija.add(zadaci);

        sadrzaj=new JButton("  Dodaj sadrzaj  ");
        sadrzaj.setPreferredSize(new Dimension(100,40));
        sadrzaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogSadrzaja ds = new DialogSadrzaja();
                ds.setVisible(true);
            }
        });

        panelAkcija.add(sadrzaj);

        inbox=new JButton(" Obrada zahteva");
        inbox.setPreferredSize(new Dimension(100,40));
        inbox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                prikaziDialogZahteva();
            }
        });

        panelAkcija.add(inbox);

        promenaLozinke=new JButton("Promena lozinke");
        promenaLozinke.setPreferredSize(new Dimension(100,40));
        promenaLozinke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogPromjeneLoznikeAdmin dz = new DialogPromjeneLoznikeAdmin(admin);
                dz.setVisible(true);
            }
        });

        panelAkcija.add(promenaLozinke);

        odobravanje=new JButton("Odobri rezenciju");
        odobravanje.setPreferredSize(new Dimension(100,40));
        odobravanje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odobravanjeRecenija();

            }
        });

        panelAkcija.add(odobravanje);

        pregled=new JButton("Pregled urednika");
        pregled.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pregledUrednika();
            }
        });
        panelAkcija.add(pregled);
    }

    private void prikaziZadatke() {
        if(MuzickoDeloDAO.getNedovrsenaMuzickaDela().isEmpty() && IzvodjacDAO.getNedovrseneIzvodjace().isEmpty() &&
        UcesnikDAO.getNedovrseneUceniske().isEmpty() && ZanrDAO.getNedovrseneZanrove().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nema sadrzaja za prikazati");
        }
        else{
            DialogZadataka dz = new DialogZadataka(ZanrDAO.getNedovrseneZanrove(),UcesnikDAO.getNedovrseneUceniske(),
                    IzvodjacDAO.getNedovrseneIzvodjace(), MuzickoDeloDAO.getNedovrsenaMuzickaDela());
            dz.setVisible(true);
        }
    }

    private void pregledUrednika() {
        if(AdminovProzorKON.getUrednici().size()==0){
            JOptionPane.showMessageDialog(null, "Nijedan urednik jos ne postoji");
        }
        else{
            DialogUrednika du = new DialogUrednika(AdminovProzorKON.getUrednici());
            du.setVisible(true);
        }
    }

    private void odobravanjeRecenija() {
        if(RecenzijaDAO.getRecenzijeKojeJeUrednikKreirao().size()==0){
            JOptionPane.showMessageDialog(null, "Ne postoje neodobrene recenzije");
        }
        else{
            DialogAdminovihRecenzija dar = new DialogAdminovihRecenzija((ArrayList<Recenzija>) RecenzijaDAO.getRecenzijeKojeJeUrednikKreirao());
            dar.setVisible(true);
        }

    }

    private void prikaziDialogZahteva() {

        if(ZahtevDAO.getZahteve().size()==0){
            JOptionPane.showMessageDialog(null,"Nema zahtjeva");
        }
        else{
            DialogZahteva dz= new DialogZahteva((ArrayList<Zahtev>) ZahtevDAO.getZahteve());
            dz.setVisible(true);
        }
    }

}
