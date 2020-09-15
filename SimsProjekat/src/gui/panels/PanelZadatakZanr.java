package gui.panels;


import dao.ZadatakDAO;
import gui.dialogs.DialogZadatakZanr;
import kontroler.UrednikovProzorKON;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PanelZadatakZanr extends JPanel{
    public SimpleDateFormat formatter1 = new SimpleDateFormat("dd-mm-yyyy");
    private DialogZadatakZanr dialog;
    private JLabel naziv, opis,datum,text;
    private JTextField naziv1, datum1;
    private JButton kreiraj,odustani;
    private JTextArea opis1,text1;
    public int id;
    private JScrollPane scroll;

    public PanelZadatakZanr(DialogZadatakZanr dialog, int idZadatka) {
        this.dialog = dialog;
        this.id = idZadatka;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(226, 206, 158));
        setLayout(null);
        namesti();

    }
    public void namesti(){
        Zadatak zadatak = ZadatakDAO.getZadatakPoId(id);
        Zanr z = zadatak.getZanr();
        text = new JLabel("      Tekst zadatka:");
        text.setBounds(50, 30 ,120, 50);
        text.setBackground(Color.white);
        text.setOpaque(true);
        text.setBorder(BorderFactory.createLineBorder(Color.black));
        add(text);

        text1 = new JTextArea();
        text1.setBounds(200, 30, 200, 50);
        text1.setBorder(BorderFactory.createLineBorder(Color.black));
        text1.setBackground(Color.white);
        text1.setOpaque(true);
        text1.setText(zadatak.getText());
        text1.setEditable(false);
        scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(200, 30, 200, 50);
        scroll.getViewport().setBackground(Color.white);
        scroll.getViewport().add(text1);
        add(scroll);

        naziv = new JLabel("      Naziv zanra:");
        naziv.setBounds(50, 100 ,140, 23);
        naziv.setBackground(Color.white);
        naziv.setOpaque(true);
        naziv.setBorder(BorderFactory.createLineBorder(Color.black));
        add(naziv);

        naziv1 = new JTextField(100);
        naziv1.setBounds(200, 100, 200, 23);
        naziv1.setBorder(BorderFactory.createLineBorder(Color.black));
        naziv1.setBackground(Color.white);
        naziv1.setOpaque(true);
        naziv1.setText(z.getNazivZanra());
        naziv1.setEditable(false);
        add(naziv1);

        opis = new JLabel("      Opis:");
        opis.setBounds(50, 150 ,140, 50);
        opis.setBackground(Color.white);
        opis.setOpaque(true);
        opis.setBorder(BorderFactory.createLineBorder(Color.black));
        add(opis);

        opis1 = new JTextArea();
        opis1.setBounds(200, 150, 200, 50);
        opis1.setBorder(BorderFactory.createLineBorder(Color.black));
        opis1.setBackground(Color.white);
        opis1.setOpaque(true);
        opis1.setEditable(true);
        add(opis1);


        datum = new JLabel("      Datum nastanka(dd-mm-yyyy):");
        datum.setBounds(50, 250 ,200, 23);
        datum.setBackground(Color.white);
        datum.setOpaque(true);
        datum.setBorder(BorderFactory.createLineBorder(Color.black));
        add(datum);

        datum1 = new JTextField();
        datum1.setBounds(270, 250, 130, 23);
        datum1.setBorder(BorderFactory.createLineBorder(Color.black));
        datum1.setBackground(Color.white);
        datum1.setEditable(true);
        add(datum1);

        kreiraj = new JButton("    Gotovo    ");
        kreiraj.setBounds(100,350,120,23);
        kreiraj.setBackground(new Color(62, 100, 103));
        kreiraj.setForeground(Color.WHITE);
        kreiraj.setBorder(BorderFactory.createLineBorder(Color.black));
        kreiraj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(opis1.getText() == ""){
                    JOptionPane.showMessageDialog(PanelZadatakZanr.this,"Morate uneti opis!");
                }else{
                    try {
                        z.setOpis(opis1.getText());
                        z.setDatumNastanka(new java.sql.Date(formatter1.parse(datum1.getText()).getTime()));
                        UrednikovProzorKON.opisiZanr(z);
                        JOptionPane.showMessageDialog(PanelZadatakZanr.this,"Uspesno ste opisali zanr!");
                        dialog.setVisible(false);
                        ZadatakDAO.delete(zadatak);
                        PanelZadaci.refreshData();
                    } catch (SQLException | ParseException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        add(kreiraj);
        odustani = new JButton("    Odustani   ");
        odustani.setBounds(300,350,120,23);
        odustani.setBackground(new Color(62, 100, 103));
        odustani.setForeground(Color.WHITE);
        odustani.setBorder(BorderFactory.createLineBorder(Color.black));
        odustani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        add(odustani);
    }
}
