package gui.panels;

import gui.dialogs.DialogSadrzaja;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSadrzaja extends JPanel {
    private DialogSadrzaja dialog;
    private JButton otkazi;
    private JButton dodaj;
    private String separator;
    private JLabel slika;

    public PanelSadrzaja(DialogSadrzaja dialog){
        this.dialog=dialog;

        separator = System.getProperty("file.separator");
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0,0));
        setBackground(new Color(229, 255, 204));
        setLayout(null);
        namesti();
    }

    public void namesti(){

        ImageIcon icon =new ImageIcon("SimsProjekat" + separator+"src"+separator +"gui"+separator+ "icons" + separator + "content.png");
        slika = new JLabel(icon);
        slika.setBounds(125, 20, 250, 200);
        add(slika);

        JLabel tip = new JLabel("Tip sadrzaja");
        tip.setBounds(72, 200, 100, 30);
        add(tip);

        String[] izborSadrzaja = { "Zanr", "Ucesnik", "Izvodjac", "Muzicko delo"};
        JComboBox<String> medija= new JComboBox<String>(izborSadrzaja);
        medija.setBounds(70, 240, 120, 30);
        add(medija);

        JLabel unos=new JLabel("Naziv:");
        unos.setBounds(300, 200, 140, 30);
        add(unos);

        JTextField naziv= new JTextField();
        naziv.setBounds(300, 240, 140, 30);
        add(naziv);

        otkazi=new JButton("Otkazi");
        otkazi.setBounds(260, 310, 100, 40);
        otkazi.setBackground(new Color(0, 122, 153));
        otkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
        add(otkazi);

        dodaj=new JButton("Dodaj");
        dodaj.setBounds(140, 310, 100, 40);
        dodaj.setBackground(new Color(0, 122, 153));
        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });
        add(dodaj);
    }
}
