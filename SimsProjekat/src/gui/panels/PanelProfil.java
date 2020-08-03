package gui.panels;

import gui.dialogs.DialogProfil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelProfil extends JPanel{
    private DialogProfil dialog;
    private String separator;
    private JLabel ime, prezime, email, telefon,godina,korisnicko,lozinka, slika;
    private JTextField ime1,prezime1,email1,telefon1,godina1,korisnicko1,lozinka1;
    private JButton sacuvaj,izmeni;
    public PanelProfil(DialogProfil dialog){
    this.dialog = dialog;
    //this.u = u;
    separator = System.getProperty("file.separator");
    setBorder(new EmptyBorder(5, 5, 5, 5));
    setLayout(new BorderLayout(0,0));
    setBackground(new Color(226, 206, 158));
    setLayout(null);
    namesti();
}
    private void namesti() {
        ime = new JLabel("      Ime:");
        ime.setBounds(200, 30 ,120, 23);
        ime.setBackground(Color.white);
        ime.setOpaque(true);
        ime.setBorder(BorderFactory.createLineBorder(Color.black));
        add(ime);

        ime1 = new JTextField();
        ime1.setBounds(350, 30, 120, 23);
        ime1.setBorder(BorderFactory.createLineBorder(Color.black));
        ime1.setBackground(Color.white);
        ime1.setOpaque(true);
        //ime1.setText(u.getIme());
        ime1.setEditable(false);
        add(ime1);

        prezime = new JLabel("      Prezime:");
        prezime.setBounds(200, 80 ,120, 23);
        prezime.setBackground(Color.white);
        prezime.setOpaque(true);
        prezime.setBorder(BorderFactory.createLineBorder(Color.black));
        add(prezime);

        prezime1 = new JTextField();
        prezime1.setBounds(350, 80, 120, 23);
        prezime1.setBorder(BorderFactory.createLineBorder(Color.black));
        prezime1.setBackground(Color.white);
        prezime1.setOpaque(true);
        //prezime1.setText(u.getPrezime());
        prezime1.setEditable(false);
        add(prezime1);

        email = new JLabel("      Email:");
        email.setBounds(200, 130 ,120, 23);
        email.setBackground(Color.white);
        email.setOpaque(true);
        email.setBorder(BorderFactory.createLineBorder(Color.black));
        add(email);

        email1 = new JTextField();
        email1.setBounds(350, 130, 120, 23);
        email1.setBorder(BorderFactory.createLineBorder(Color.black));
        email1.setBackground(Color.white);
        email1.setOpaque(true);
        //email1.setText(u.getEmail());
        email1.setEditable(false);
        add(email1);

        telefon = new JLabel("      Telefon:");
        telefon.setBounds(200, 180 ,120, 23);
        telefon.setBackground(Color.white);
        telefon.setOpaque(true);
        telefon.setBorder(BorderFactory.createLineBorder(Color.black));
        add(telefon);

        telefon1 = new JTextField();
        telefon1.setBounds(350, 180, 120, 23);
        telefon1.setBorder(BorderFactory.createLineBorder(Color.black));
        telefon1.setBackground(Color.white);
        telefon1.setOpaque(true);
        //telefon1.setText(u.getTelefon());
        telefon1.setEditable(false);
        add(telefon1);

        godina = new JLabel("      Godina rodjenja:");
        godina.setBounds(200, 230 ,120, 23);
        godina.setBackground(Color.white);
        godina.setOpaque(true);
        godina.setBorder(BorderFactory.createLineBorder(Color.black));
        add(godina);

        godina1 = new JTextField();
        godina1.setBounds(350, 230, 120, 23);
        godina1.setBorder(BorderFactory.createLineBorder(Color.black));
        godina1.setBackground(Color.white);
        godina1.setOpaque(true);
        //godina1.setText(u.getTelefon());
        godina1.setEditable(false);
        add(godina1);
        korisnicko = new JLabel("      Korisnicko ime:");
        korisnicko.setBounds(200, 280 ,120, 23);
        korisnicko.setBackground(Color.white);
        korisnicko.setOpaque(true);
        korisnicko.setBorder(BorderFactory.createLineBorder(Color.black));
        add(korisnicko);

        korisnicko1 = new JTextField();
        korisnicko1.setBounds(350, 280, 120, 23);
        korisnicko1.setBorder(BorderFactory.createLineBorder(Color.black));
        korisnicko1.setBackground(Color.white);
        korisnicko1.setOpaque(true);
        //korisnicko1.setText(u.getTelefon());
        korisnicko1.setEditable(false);
        add(korisnicko1);
        lozinka = new JLabel("      Lozinka:");
        lozinka.setBounds(200, 330 ,120, 23);
        lozinka.setBackground(Color.white);
        lozinka.setOpaque(true);
        lozinka.setBorder(BorderFactory.createLineBorder(Color.black));
        add(lozinka);

        lozinka1 = new JTextField();
        lozinka1.setBounds(350, 330, 120, 23);
        lozinka1.setBorder(BorderFactory.createLineBorder(Color.black));
        lozinka1.setBackground(Color.white);
        lozinka1.setOpaque(true);
        //lozinka1.setText(u.getTelefon());
        lozinka1.setEditable(false);
        add(lozinka1);


        Icon icon = new ImageIcon("SimsProjekat" + separator+"src"+separator +"gui"+separator+ "icons" + separator + "user.png");
        slika = new JLabel(icon);
        slika.setBounds(30, 50, 150, 150);
        add(slika);

        izmeni = new JButton("Izmeni");
        izmeni.setBackground(new Color(62, 100, 103));
        izmeni.setForeground(Color.WHITE);
        izmeni.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        izmeni.setBounds(200, 400, 120, 23);
        izmeni.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ime1.setEditable(true);
                prezime1.setEditable(true);
                email1.setEditable(true);
                telefon1.setEditable(true);
                godina1.setEditable(true);
                lozinka1.setEditable(true);
            }

        });
        add(izmeni);

        sacuvaj = new JButton("Sacuvaj");
        sacuvaj.setBackground(new Color(62, 100, 103));
        sacuvaj.setForeground(Color.WHITE);
        sacuvaj.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sacuvaj.setBounds(350, 400, 120, 23);
        sacuvaj.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {


            }

        });
        add(sacuvaj);


    }
}