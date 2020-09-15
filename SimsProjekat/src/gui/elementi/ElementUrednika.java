package gui.elementi;

import dao.KorisnickiNalogDAO;
import gui.dialogs.DialogUrednika;
import gui.dialogs.DialogUrednikovihRecenzija;
import gui.dialogs.DialogZadataka;
import kontroler.AdminovProzorKON;
import model.KorisnickiNalog;
import model.Recenzija;
import model.Urednik;
import model.Zadatak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ElementUrednika extends JPanel{
    private JLabel ime, prezime, korisnickoIme, email, telefon, broj;
    private JLabel ime1, prezime1, korisnickoIme1, email1, telefon1, broj1;
    private DialogUrednika dialog;
    private Urednik urednik;
    private int brRecenzija;
    private JButton recenzije;

    public ElementUrednika(DialogUrednika du, Urednik u, int brRecenzija){
        this.dialog=du;
        this.brRecenzija=brRecenzija;
        this.urednik=u;

        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(255, 204, 204));
        setPreferredSize(new Dimension(400, 240));
        setLayout(null);
        namesti();
    }

    private void namesti() {
        ime=new JLabel("Ime: ");
        ime.setFont(new Font("Courier", Font.BOLD, 12));
        ime.setBounds(20, 20, 90, 20);
        add(ime);

        ime1=new JLabel(urednik.getIme());
        ime1.setBounds(120, 20, 150,20);
        add(ime1);

        prezime=new JLabel("Prezime: ");
        prezime.setFont(new Font("Courier", Font.BOLD, 12));
        prezime.setBounds(20, 40, 90, 20);
        add(prezime);

        prezime1=new JLabel(urednik.getPrezime());
        prezime1.setBounds(120, 40, 150, 20);
        add(prezime1);

        KorisnickiNalog nalog= KorisnickiNalogDAO.getNalogUrednika(urednik.getId());

        korisnickoIme=new JLabel("Korisnicko ime: ");
        korisnickoIme.setFont(new Font("Courier", Font.BOLD,12));
        korisnickoIme.setBounds(20, 100, 90, 20);
        add(korisnickoIme);

        korisnickoIme1=new JLabel(nalog.getKorisnickoIme());
        korisnickoIme1.setBounds(120, 100, 150, 20);
        add(korisnickoIme1);

        email=new JLabel("Email: ");
        email.setFont(new Font("Courier", Font.BOLD,12));
        email.setBounds(20, 80, 90, 20);
        add(email);

        email1=new JLabel(urednik.getEmail());
        email1.setBounds(120, 80, 150, 20);
        add(email1);

        telefon=new JLabel("Telefon: ");
        telefon.setFont(new Font("Courier", Font.BOLD,12));
        telefon.setBounds(20, 60, 90, 20);
        add(telefon);

        telefon1=new JLabel(urednik.getKontaktTelefon());
        telefon1.setBounds(120, 60, 150, 20);
        add(telefon1);

        broj=new JLabel("Broj recenzija: ");
        broj.setFont(new Font("Courier", Font.BOLD,12));
        broj.setBounds(20, 130, 90, 20);
        add(broj);

        broj1=new JLabel(""+brRecenzija);
        broj1.setBounds(120, 130, 90, 20);
        add(broj1);

        recenzije=new JButton("Pregled recenzija");
        recenzije.setBounds(160, 200,150, 30 );
        recenzije.setBackground(new Color(77, 121, 255));
        recenzije.setForeground(Color.white);
        recenzije.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otvoriNoviProzor();
            }
        });
        add(recenzije);
    }

    private void otvoriNoviProzor() {
        if(brRecenzija==0){
            JOptionPane.showMessageDialog(null,"Urednik nema recenzija");
        }
        else{
            HashMap<Recenzija, Double> ocjene=
                    AdminovProzorKON.getProsjecneOcjeneKorisnika((ArrayList<Recenzija>) urednik.getListaRecenzija());
            DialogUrednikovihRecenzija du=new DialogUrednikovihRecenzija((ArrayList<Recenzija>) urednik.getListaRecenzija(), ocjene);
            du.setVisible(true);
        }
    }


}
