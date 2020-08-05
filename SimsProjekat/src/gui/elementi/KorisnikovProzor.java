package gui.elementi;

import model.RegistrovaniKorisnik;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KorisnikovProzor extends GlavniProzor{
    //JButton profil,odobravanje,glasanje,zadaci;

    public KorisnikovProzor(RegistrovaniKorisnik korisnik) {
        super();
        inicijalizuj();

    }

    private void inicijalizuj() {

        panelOperacija.remove(prijavaButton);
        panelOperacija.remove(registracijaButton);

        odjavaButton.setVisible(true);
        odjavaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odjaviSe();
            }
        });
    }

    private void odjaviSe() {

        Icon icon = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "logOut.png");
        int retVal = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zeleite da se odjavite ?", "Odjava", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if(retVal == 0) {
            dispose();
            GlavniProzor gp = new GlavniProzor();
            gp.setVisible(true);
        }
    }
}
