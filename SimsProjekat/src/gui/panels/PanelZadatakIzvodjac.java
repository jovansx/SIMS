package gui.panels;

import dao.*;

import gui.dialogs.DialogZadatakIzvodjac;
import kontroler.UrednikovProzorKON;
import model.*;
import model.enums.TipIzvodjaca;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Objects;

public class PanelZadatakIzvodjac extends JPanel{
    private DialogZadatakIzvodjac dialog;
    private JLabel naziv, opis, text,grupa,clanovi;
    private JTextField naziv1;
    private JButton kreiraj, odustani;
    private JTextArea opis1,sadrzaj1,text1;
    private JComboBox<String> combo,combo1,combo2;
    private JRadioButton solista,bend,orkestar,clan,kamerman,dirigent;
    private ButtonGroup group;
    public int id;
    private JScrollPane scroll;

    public PanelZadatakIzvodjac(DialogZadatakIzvodjac dialog, int idZadatka) {
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
        Izvodjac izvodjac = zadatak.getIzvodjac();
        ArrayList<Izvodjac> grupe = (ArrayList<Izvodjac>) IzvodjacDAO.getSveGrupe();
        ArrayList<Izvodjac> cl = (ArrayList<Izvodjac>) IzvodjacDAO.getSveClanove();

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

        naziv = new JLabel("      Naziv izvodjaca:");
        naziv.setBounds(50, 100 ,120, 23);
        naziv.setBackground(Color.white);
        naziv.setOpaque(true);
        naziv.setBorder(BorderFactory.createLineBorder(Color.black));
        add(naziv);

        naziv1 = new JTextField(100);
        naziv1.setBounds(200, 100, 200, 23);
        naziv1.setBorder(BorderFactory.createLineBorder(Color.black));
        naziv1.setBackground(Color.white);
        naziv1.setOpaque(true);
        naziv1.setText(izvodjac.getNazivIzvodjaca());
        naziv1.setEditable(false);
        add(naziv1);

        opis = new JLabel("      Opis:");
        opis.setBounds(50, 150 ,120, 50);
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

        solista = new JRadioButton("Solista");
        solista.setBounds(50,280,100,20);
        solista.setActionCommand("Solista");
        solista.setBackground(Color.white);
        add(solista);

        bend = new JRadioButton("Bend");
        bend.setBounds(150,280,100,20);
        bend.setActionCommand("Bend");
        bend.setBackground(Color.white);
        add(bend);

        orkestar = new JRadioButton("Orkestar");
        orkestar.setBounds(250,280,100,20);
        orkestar.setActionCommand("Orkestar");
        orkestar.setBackground(Color.white);
        add(orkestar);

        clan = new JRadioButton("Clan grupe");
        clan.setBounds(350,280,100,20);
        clan.setActionCommand("Clan grupe");
        clan.setBackground(Color.white);
        add(clan);

        kamerman = new JRadioButton("Kamerman");
        kamerman.setBounds(50,320,100,20);
        kamerman.setActionCommand("Kamerman");
        kamerman.setBackground(Color.white);
        add( kamerman);

        dirigent = new JRadioButton("Dirigent");
        dirigent.setBounds(150,320,100,20);
        dirigent.setActionCommand("Dirigent");
        dirigent.setBackground(Color.white);
        add(dirigent);

        group = new ButtonGroup();
        group.add(solista);
        group.add(bend);
        group.add(orkestar);
        group.add(clan);
        group.add(kamerman);
        group.add(dirigent);

        grupa = new JLabel("      Grupa/orkestar:");
        grupa.setBounds(420, 30 ,120, 23);
        grupa.setBackground(Color.white);
        grupa.setOpaque(true);
        grupa.setBorder(BorderFactory.createLineBorder(Color.black));
        add(grupa);

        combo = new JComboBox<String>();
        combo.setBounds(550, 30, 120, 23);
        for(Izvodjac i :grupe){
            combo.setSelectedItem(null);
            combo.addItem(i.getNazivIzvodjaca());
        }
        combo.setSelectedItem(null);

        clanovi = new JLabel("      Clanovi grupe/orkestra:");
        clanovi.setBounds(420, 100 ,120, 23);
        clanovi.setBackground(Color.white);
        clanovi.setOpaque(true);
        clanovi.setBorder(BorderFactory.createLineBorder(Color.black));
        add(clanovi);

        combo1 = new JComboBox<String>();
        combo1.setBounds(550, 100, 120, 23);
        for(Izvodjac i :cl){
            combo1.setSelectedItem(null);
            combo1.addItem(i.getNazivIzvodjaca());
        }
        combo1.setSelectedItem(null);
        add(combo1);
        add(combo);


        combo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event) {
                String value = (String) event.getItem();
                izvodjac.setPripadaGrupi(IzvodjacDAO.getIzvodjacNaziv(value));
            }
        });

        combo1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event) {
                String value = (String) event.getItem();
                if(!Objects.isNull(izvodjac.getImaClanove())){
                    izvodjac.getImaClanove().add(IzvodjacDAO.getIzvodjacNaziv(value));
                }else{
                    izvodjac.setImaClanove(new ArrayList<>());
                    izvodjac.getImaClanove().add(IzvodjacDAO.getIzvodjacNaziv(value));
                }


            }
        });

        kreiraj = new JButton("    Gotovo    ");
        kreiraj.setBounds(100,350,120,23);
        kreiraj.setBackground(new Color(62, 100, 103));
        kreiraj.setForeground(Color.WHITE);
        kreiraj.setBorder(BorderFactory.createLineBorder(Color.black));
        kreiraj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(opis1.getText() == ""){
                    JOptionPane.showMessageDialog(PanelZadatakIzvodjac.this,"Morate uneti opis!");
                }else{
                    try {
                        izvodjac.setOpis(opis1.getText());
                        if(solista.isSelected()){
                            izvodjac.setTipIzvodjaca(TipIzvodjaca.SOLISTA);
                        }if(bend.isSelected()){
                            izvodjac.setTipIzvodjaca(TipIzvodjaca.BEND);
                        }if(orkestar.isSelected()){
                            izvodjac.setTipIzvodjaca(TipIzvodjaca.ORKESTAR);
                        }if(clan.isSelected()){
                            izvodjac.setTipIzvodjaca(TipIzvodjaca.CLAN_GRUPE);
                        }if(kamerman.isSelected()){
                            izvodjac.setTipIzvodjaca(TipIzvodjaca.KAMERMAN);
                        }if(dirigent.isSelected()){
                            izvodjac.setTipIzvodjaca(TipIzvodjaca.DIRIGENT);
                        }
                        ArrayList<String> imena = new ArrayList<>();
                        for(int i =0;i< izvodjac.getImaClanove().size();i++){
                            imena.add(izvodjac.getImaClanove().get(i).getNazivIzvodjaca());
                        }
                        ArrayList<String> pomocna = new ArrayList<>();
                        for(int i =0;i<imena.size();i++){
                            if(!pomocna.contains(imena.get(i))){
                                pomocna.add(imena.get(i));
                            }
                        }
                        ArrayList<Izvodjac> konacna = new ArrayList<>();
                        for(int i =0;i<pomocna.size();i++){
                            konacna.add(IzvodjacDAO.getIzvodjacNaziv(pomocna.get(i)));
                        }
                        izvodjac.setImaClanove(konacna);
                        UrednikovProzorKON.opisiIzvodjaca(izvodjac);
                        JOptionPane.showMessageDialog(PanelZadatakIzvodjac.this,"Uspesno ste opisali izvodjaca!");
                        dialog.setVisible(false);
                        ZadatakDAO.delete(zadatak);
                        PanelZadaci.refreshData();
                    } catch (SQLException throwables) {
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
