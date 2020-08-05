package gui.elementi;

import gui.dialogs.DialogOdobravanje;
import gui.dialogs.DialogProfil;
import model.Recenzija;
import model.Urednik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UrednikovProzor extends GlavniProzor{
    JButton profil,odobravanje,glasanje,zadaci;
    ArrayList<Recenzija> lista;

    public UrednikovProzor(Urednik urednik) {
        super();
        inicijalizuj();
    }

    public UrednikovProzor(ArrayList<Recenzija> listaRecenzija){
        super();
        this.lista = listaRecenzija;
        panelAkcija.setLayout(new BoxLayout(panelAkcija,BoxLayout.PAGE_AXIS));
        profil = new JButton("Profil");
        profil.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogProfil dp = new DialogProfil();
                dp.setVisible(true);

            }

        });
        panelAkcija.add(profil);
        odobravanje = new JButton("Odobravanje komentara");
        odobravanje.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogOdobravanje dp = new DialogOdobravanje(lista);
                dp.setVisible(true);

            }

        });
        panelAkcija.add(odobravanje);
        glasanje = new JButton("Glasanje");
        panelAkcija.add(glasanje);
        zadaci = new JButton("Lista zadataka");
        zadaci.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ;

            }

        });
        panelAkcija.add(zadaci);

    }

    private void inicijalizuj() {

        panelOperacija.remove(prijavaButton);
        panelOperacija.remove(registracijaButton);
        odjavaButton.setVisible(true);
    }
}
