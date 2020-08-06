package gui.elementi;

import model.RegistrovaniKorisnik;

public class KorisnikovProzor extends GlavniProzor {
    //JButton profil,odobravanje,glasanje,zadaci;

    public KorisnikovProzor(RegistrovaniKorisnik korisnik) {
        super();
        inicijalizuj();

    }

    private void inicijalizuj() {

        panelOperacija.remove(prijavaButton);
        panelOperacija.remove(registracijaButton);
        odjavaButton.setVisible(true);
    }
}
