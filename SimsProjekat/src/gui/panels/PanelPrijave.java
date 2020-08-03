package gui.panels;

import gui.dialogs.DialogPrijave;
import gui.dialogs.DialogRegistracije;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*import view.meni.ZajednickiMenuBar;
import view.prozori.DostavljacevProzor;
import view.prozori.MainWindow;
import view.prozori.MusterijinProzor;
import view.prozori.RadnikovProzor;
import view.prozori.VlasnikovProzor;*/

//import model.entiteti.*;

public class PanelPrijave extends JPanel implements ActionListener {

    private DialogPrijave dialog;
    private String separator;
    private JLabel name, lastName, labela, upozorenja;
    private JTextField nameField;
    private JPasswordField lastNameField;
    private JButton button;

    public PanelPrijave(DialogPrijave dialog) {

        this.dialog = dialog;
        separator = System.getProperty("file.separator");

        setBackground(new Color(226, 206, 158));
        podesiKomponente();
        setPanel();
        podesiLayout();

    }

    private void podesiKomponente() {

        name = new JLabel("Korisnicko ime:");
        lastName = new JLabel("Sifra:");
        upozorenja = new JLabel("");
        upozorenja.setForeground(new Color(62, 100, 103));
        nameField = new JTextField(15);
        lastNameField = new JPasswordField(15);
        button = new JButton("Prijava");
        button.setBackground(new Color(62, 100, 103));
        button.setForeground(Color.white);
        button.addActionListener(this);
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
        add(button, con);

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

        /*int counter = 0;
        String username = nameField.getText();
        @SuppressWarnings("deprecation")
        String password = lastNameField.getText();

        MyReader reader = new MyReader("src" + separator + "podaci" + separator + "logInPodaci.csv", "UTF8");
        ArrayList<String[]> lista = reader.readlinesWithSeparator(",");

        for (String[] podaci: lista) {
            if (podaci[0].equals(username) && podaci[1].equals(password)) {
                counter++;
                ManagerKorisnici menagerK = ManagerKorisnici.getInstance();
                MainWindow window = new MainWindow();

                upozorenja.setText("Nakon unosa pritisnite 'Prijava'");
                nameField.setText(""); lastNameField.setText("");

                Osoba osoba = menagerK.returnUser(username, password);
                if (osoba == null) {
                    continue;
                }else {
                    if (osoba instanceof Musterija) {
                        window.podesiPanel(new MusterijinProzor((Musterija) osoba, window));
                    }else if(osoba instanceof Dostavljac) {
                        window.podesiPanel(new DostavljacevProzor((Dostavljac) osoba));
                    }else if(osoba instanceof Vlasnik) {
                        window.podesiPanel(new VlasnikovProzor((Vlasnik) osoba));
                    }else {
                        window.podesiPanel(new RadnikovProzor((Radnik) osoba));
                    }
                }
                window.podesiMenuBar(new ZajednickiMenuBar(window, dialog));
                window.setVisible(true);

                dialog.setVisible(false);
                break;
            }
        }

        if (counter == 0) {
            upozorenja.setText("Nesto ste pogresno uneli !");
            nameField.setText(""); lastNameField.setText("");
        }*/
    }
}