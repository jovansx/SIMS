package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.ZanrDAO;
import gui.dialogs.DialogPrijave;
import gui.dialogs.DialogRegistracije;
import model.Izvodjenje;
import model.Zanr;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GlavniProzor extends JFrame{
    private JPanel panelOperacija;
    private JPanel panelReklama;
    protected JPanel panelAkcija;
    private JScrollPane skrol;
    private JPanel panelGlavni;
    private JButton popularnoButton;
    private JTextField textField1;
    private JButton pretraziButton;
    private JButton prijavaButton;
    private JButton registracijaButton;
    private JPanel panelOdSkrola;
    private JButton pocetnaStranicaButton;
    private List<Element> elementi;

    public GlavniProzor(){
        super("Muzicki sistem");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panelGlavni);
        setSize(1100, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        elementi = new ArrayList<Element>();

        prijavaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogPrijave dp = new DialogPrijave(GlavniProzor.this);
                dp.setVisible(true);
            }
        });
        registracijaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogRegistracije dr = new DialogRegistracije(GlavniProzor.this);
                dr.setVisible(true);
            }
        });

        pocetnaStranicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ucitajPocetnuStranu();
            }
        });


        popularnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetElemente();
            }
        });
    }

    public void ucitajPocetnuStranu(){
        List<Zanr> zanrovi = ZanrDAO.getZanrove();

        for (Zanr z : zanrovi) {
            Element el = new Element(z);
            elementi.add(el);
            panelOdSkrola.add(el);
        }

        panelOdSkrola.validate();
        panelOdSkrola.repaint();
        System.out.println("ucitano");
    }

    /*public void dodajElement(){
        Element el = new Element();
        elementi.add(el);

        panelOdSkrola.add(el);
        panelOdSkrola.validate();
        panelOdSkrola.repaint();
        System.out.println("dodajem");
    }

     */
    public void resetElemente(){
        elementi.clear();

        panelOdSkrola.removeAll();
        panelOdSkrola.validate();
        panelOdSkrola.repaint();
        System.out.println("brisem");
    }
}
