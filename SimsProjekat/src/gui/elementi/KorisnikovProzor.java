package gui.elementi;

import model.RegistrovaniKorisnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class KorisnikovProzor extends GlavniProzor {
    JButton profilButton,ponistiRecenzijuButton, odobravanje,glasanje,zadaci;
    public int idKorisnika;

    public KorisnikovProzor(RegistrovaniKorisnik korisnik) {
        super();
        idKorisnika = korisnik.getId();
        inicijalizuj();

        podesiNaknadneAkcije(korisnik);

    }

    private void podesiNaknadneAkcije(RegistrovaniKorisnik korisnik) {

        profilButton.addActionListener(e -> {
            PrikazProfila pp = new PrikazProfila(KorisnikovProzor.this, korisnik);
            pp.setVisible(true);
        });

        ponistiRecenzijuButton.addActionListener(e -> {
            PrikazRecenzija rp = new PrikazRecenzija(KorisnikovProzor.this, korisnik);
            rp.setVisible(true);
        });

    }

    private void inicijalizuj() {
        panelOperacija.remove(prijavaButton);
        panelOperacija.remove(registracijaButton);
        odjavaButton.setVisible(true);

        profilButton = new JButton("Moj profil");
        profilButton.setBackground(new Color(59, 86, 90));
        panelAkcija.add(profilButton);

        ponistiRecenzijuButton = new JButton("Ponistavanje recenzije");
        ponistiRecenzijuButton.setBackground(new Color(59, 86, 90));
        panelAkcija.add(ponistiRecenzijuButton);


    }
}
