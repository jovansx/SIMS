package gui.elementi;

import model.Korisnik;

import javax.swing.*;
import java.awt.*;

public class PrikaziKorisnika extends JDialog {
    private JPanel glavniPanel;
    private JLabel labelaImeIPrezime;
    private JLabel labelaGodinaRodjenja;
    private JLabel labelaEmail;
    private JLabel labelaKontaktTelefon;
    private Dimension dimension;

    public PrikaziKorisnika(Korisnik korisnik, ElementRecenzija el) {
        super();
        setTitle("Detalji korisnika");
        setModal(true);

        inicijalizuj(korisnik);

        add(glavniPanel);

        setSize(dimension.width / 8, dimension.height / 8);

        setResizable(false);
        setLocationRelativeTo(el);
    }

    private void inicijalizuj(Korisnik korisnik) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();

        labelaImeIPrezime.setText("Ime i prezime : " + korisnik.getIme() + " " + korisnik.getPrezime());
        labelaGodinaRodjenja.setText("Godina rodjenja : " + korisnik.getGodinaRodjenja().toString());
        labelaEmail.setText("Email : " + korisnik.getEmail());
        labelaKontaktTelefon.setText("Kontakt telefon : " + korisnik.getKontaktTelefon());
    }

}
