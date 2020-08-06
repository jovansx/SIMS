package gui.elementi;

import dao.UrednikDAO;
import gui.dialogs.DialogZadataka;
import kontroler.AdminovProzorKON;
import model.Urednik;
import model.Zadatak;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ElementZadatka extends JPanel {
    private JLabel naziv, tip, komentar, urednik;
    private JTextField naziv1, tip1, komentar1;
    private JComboBox<String> urednici;
    private DialogZadataka dialog;
    private Zadatak zadatak;
    private JButton posalji;

    public ElementZadatka(Zadatak z, DialogZadataka dz){
        this.dialog=dz;
        this.zadatak=z;
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(229, 255, 204));
        setPreferredSize(new Dimension(400, 220));
        setLayout(null);
        namesti();
    }

    private void namesti() {
        String tekst = "";
        String tipZadatka="";
        if(zadatak.getDelo()!=null){
            tekst=zadatak.getDelo().getNazivDela();
            tipZadatka="MUZICKO DELO";
        }
        else if(zadatak.getIzvodjac()!=null){
            tekst= zadatak.getIzvodjac().getNazivIzvodjaca();
            tipZadatka="IZVODJAC";
        }
        else if(zadatak.getUcesnik()!=null){
            tekst=zadatak.getUcesnik().getNaziv();
            tipZadatka="UCESNIK";
        }
        else{
            tekst=zadatak.getZanr().getNazivZanra();
            tipZadatka="ZANR";
        }

        naziv=new JLabel("Naziv");
        naziv.setBounds(10, 10, 70, 30);
        add(naziv);

        tip=new JLabel("Tip");
        tip.setBounds(10, 50, 70, 30);
        add(tip);

        komentar=new JLabel("Komentar:");
        komentar.setBounds(10, 90, 70, 30);
        add(komentar);

        urednik=new JLabel("Urednik:");
        urednik.setBounds(10, 130, 70, 30);
        add(urednik);

        naziv1=new JTextField(tekst);
        naziv1.setBounds(90, 10, 200, 30);
        naziv1.setEditable(false);
        add(naziv1);

        tip1=new JTextField(tipZadatka);
        tip1.setBounds(90, 50, 200, 30);
        tip1.setEditable(false);
        add(tip1);

        komentar1=new JTextField();
        komentar1.setBounds(90, 90, 200, 30);
        komentar1.setEditable(true);
        add(komentar1);

        urednici=new JComboBox<String>(AdminovProzorKON.getNaziviUrednika());
        urednici.setBounds(90, 130, 200, 30);
        add(urednici);

        //Trebalo bi dodati u Zadatak atribut idUrednika, da bi znali koji je zadatak kom Uredniku dodjeljen
        posalji= new JButton("Posalji");
        posalji.setBounds(220, 170, 70, 30);
        posalji.setBackground(new Color(0, 77, 102));
        posalji.setForeground(Color.white);
        posalji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
        add(posalji);

    }


}
