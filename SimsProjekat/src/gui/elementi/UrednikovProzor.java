package gui.elementi;

import dao.RecenzijaDAO;
import dao.UrednikDAO;
import dao.ZadatakDAO;
import gui.dialogs.DialogOdobravanje;
import gui.dialogs.DialogProfil;
import gui.dialogs.DialogZadaci;
import model.Recenzija;
import model.Urednik;
import model.Zadatak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UrednikovProzor extends GlavniProzor{
    public int idUrednika;
    JButton profil,odobravanje,glasanje,zadaci;
    ArrayList<Recenzija> lista;
    ArrayList<Zadatak> lista1;

    public UrednikovProzor(Urednik urednik) {
        super();
        idUrednika = urednik.getId();
        //lista = (ArrayList<Recenzija>) RecenzijaDAO.getRecenzijeUrednika(idUrednika);
        //lista1 = (ArrayList<Zadatak>) ZadatakDAO.getZadatkePoUredniku(UrednikDAO.getUrednikPoId(idUrednika));
        inicijalizuj();
    }
    private void inicijalizuj() {

        panelOperacija.remove(prijavaButton);
        panelOperacija.remove(registracijaButton);
        odjavaButton.setVisible(true);

        panelAkcija.setLayout(new BoxLayout(panelAkcija,BoxLayout.PAGE_AXIS));
        profil = new JButton("Profil");
        profil.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogProfil dp = new DialogProfil(idUrednika);
                dp.setVisible(true);

            }

        });
        panelAkcija.add(profil);
        odobravanje = new JButton("Odobravanje komentara");
        odobravanje.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogOdobravanje dp = new DialogOdobravanje(idUrednika);
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
                DialogZadaci dz = new DialogZadaci(idUrednika);
                dz.setVisible(true);

            }

        });
        panelAkcija.add(zadaci);
    }
}
