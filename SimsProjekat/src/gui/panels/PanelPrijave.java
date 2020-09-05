package gui.panels;

import gui.dialogs.DialogPrijave;
import gui.elementi.AdminovProzor;
import gui.elementi.GlavniProzor;
import gui.elementi.KorisnikovProzor;
import gui.elementi.UrednikovProzor;
import kontroler.PrijavaKON;
import kontroler.RegistrovaniKorisnikKON;
import model.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

public class PanelPrijave extends JPanel implements ActionListener {

    private DialogPrijave dialog;
    private String separator;
    private JLabel name, lastName, labela, upozorenja, promenaLozinkeL;
    private JTextField nameField;
    private JPasswordField lastNameField;
    private JButton prijavaButton;
    private GlavniProzor glavniProzor;

    public PanelPrijave(DialogPrijave dialog, GlavniProzor gp) {

        this.dialog = dialog;
        this.glavniProzor = gp;
        separator = System.getProperty("file.separator");

        setBackground(new Color(226, 206, 158));
        podesiKomponente();
        setPanel();
        podesiLayout();
        dodajOsluskivaceMisa();
    }

    private void dodajOsluskivaceMisa() {

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                final int x = e.getX();
                final int y = e.getY();
                // only display a hand if the cursor is over the items
                final Rectangle cellBounds = promenaLozinkeL.getBounds();
                if (cellBounds != null && cellBounds.contains(x, y)) {
                    promenaLozinkeL.setForeground(Color.red);
                    promenaLozinkeL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                } else {
                    dialog.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    promenaLozinkeL.setForeground(Color.black);
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                final int x = e.getX();
                final int y = e.getY();
                final Rectangle cellBounds = promenaLozinkeL.getBounds();

                if(cellBounds != null && ! cellBounds.contains(x, y)) return;

                boolean retVal = PrijavaKON.proveriKorisnickoIme(nameField.getText());
                Icon icon = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "warning.jpg");

                if(!retVal) {
                    JOptionPane.showMessageDialog(dialog, "Morate uneti korisnicko ime !", "Povratak lozninke zahtev", JOptionPane.WARNING_MESSAGE, icon);
                    return; }

                try {
                    retVal = PrijavaKON.posaljiMailPovratkaLozinke(nameField.getText());
                } catch (Exception ex) {
                    if(ex.getMessage().equals("neuspeh")) {
                        /*
                        JOptionPane.showMessageDialog(dialog,
                                """
                                        Korisnicka strana:
                                        1 - Proverite Vasu internet konekciju.
                                        Sistemska strana:
                                        2 - Proverite da li ste iskljucili bezbednost:
                                        https://myaccount.google.com/lesssecureapps
                                        3 - Proverite da li ste podesili username i password,sa implementacione strane !""",
                                  "Problemi sa konekcijom", JOptionPane.WARNING_MESSAGE, icon);

                         */
                        return;
                    }
                }

                if(!retVal) {
                    JOptionPane.showMessageDialog(dialog, "Nepostojece korisnicko ime !", "Nema vas u bazi", JOptionPane.WARNING_MESSAGE, icon);
                }else {
                    dialog.dispose();
                    JOptionPane.showMessageDialog(dialog, "Uspesna promena lozinke !\n" +
                            "Sada mozete da se prijavite sa novom lozinkom.", "Bravo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void podesiKomponente() {

        name = new JLabel("Korisnicko ime:");
        lastName = new JLabel("Sifra:");
        upozorenja = new JLabel("Unesite validne podatke");
        promenaLozinkeL = new JLabel("Zaboravili ste lozinku ?");
        upozorenja.setForeground(new Color(62, 100, 103));
        nameField = new JTextField(15);
        lastNameField = new JPasswordField(15);
        prijavaButton = new JButton("Prijava");
        prijavaButton.setBackground(new Color(62, 100, 103));
        prijavaButton.setForeground(Color.white);
        prijavaButton.addActionListener(this);
        Icon icon = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "ikona.png");
        labela = new JLabel(icon);

    }

    private void setPanel() {

        Border inBorder = BorderFactory.createTitledBorder("Unesite podatke");
        Border outBorder = BorderFactory.createEmptyBorder(0, 10, 10, 10);

        setBorder(BorderFactory.createCompoundBorder(outBorder, inBorder));
    }

    private void podesiLayout() {

        GridBagLayout bg = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        setLayout(bg);

        con.fill = GridBagConstraints.BOTH;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 2;
        con.insets = new Insets(5, 20, 15, 5);
        con.anchor = GridBagConstraints.LINE_END;
        add(labela, con);

        con.fill = GridBagConstraints.NONE;
        con.gridy = 1;
        con.gridwidth = 1;
        add(name, con);

        con.gridy = 2;
        add(lastName, con);

        con.gridx = 1;
        con.gridy = 3;
        add(promenaLozinkeL, con);

        con.gridy = 4;
        add(prijavaButton, con);

        con.gridy = 1;
        con.insets = new Insets(5, 5, 15, 20);
        con.anchor = GridBagConstraints.LINE_START;
        add(nameField, con);

        con.gridy = 2;
        add(lastNameField, con);

        con.gridx = 0;
        con.gridy = 4;
        con.gridwidth = 2;
        add(upozorenja, con);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        String korIme = nameField.getText();
        String sifra = new String(lastNameField.getPassword());

        Korisnik korisnik;
        try {
            korisnik = RegistrovaniKorisnikKON.proslediPodatkePrijave(korIme, sifra);
            if(korisnik == null) {
                JOptionPane.showMessageDialog(this, "Ne postoji takav nalog");
                return;
            }
        } catch (Exception ex) {
            String tipIzuzetka = ex.getMessage();
            if (tipIzuzetka.equals("1")) {
                upozorenja.setText("Morate popuniti sva polja !");
            }
            return;
        }

        dialog.dispose();       //Ugasi dialog prijave
        glavniProzor.dispose();     //Ugasi pocetni prozor
        GlavniProzor prozorPrijavljenog = null;
        if(korisnik instanceof RegistrovaniKorisnik) {
            prozorPrijavljenog = new KorisnikovProzor((RegistrovaniKorisnik) korisnik);
        }
        else if(korisnik instanceof Urednik) {
            prozorPrijavljenog = new UrednikovProzor((Urednik) korisnik);
        }
        else {
            prozorPrijavljenog = new AdminovProzor((Administrator) korisnik);
        }
        prozorPrijavljenog.setVisible(true);
    }

}