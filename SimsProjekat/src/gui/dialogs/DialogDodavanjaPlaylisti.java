package gui.dialogs;

import dao.PlejListaDAO;
import gui.elementi.DodajIzvodjenje;
import gui.elementi.ElementPrikazaPlejlisti;
import model.Izvodjenje;
import model.PlejLista;
import model.RegistrovaniKorisnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DialogDodavanjaPlaylisti extends JDialog{
    private JPanel panel;
    private JButton dodaj;
    private JPanel nepostojeci;
    private JPanel skrolPanel;
    private JScrollPane skrol;
    private List<PlejLista> plejliste;
    private List<DodajIzvodjenje> listaElemenata;
    private RegistrovaniKorisnik korisnik;
    private Izvodjenje izvodjenje;
    private JLabel labela;

    public DialogDodavanjaPlaylisti(List<PlejLista> lista, RegistrovaniKorisnik rg, Izvodjenje i){
        this.plejliste=lista;
        this.listaElemenata=new ArrayList<>();
        this.korisnik=rg;
        this.izvodjenje=i;

        setTitle("Playliste");
        setModal(true);
        setSize(340, 350);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        if(!plejliste.isEmpty()){
            ucitajSkrol();
        }
        else {
            nepostojeci=new JPanel();
            nepostojeci.setBackground(Color.white);
            labela=new JLabel("Nemate nijednu playlistu");
            nepostojeci.add(labela);
            add(nepostojeci, BorderLayout.CENTER);

        }
        ucitajDugmad();
        podesiAkcije();

    }

    private void podesiAkcije() {

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    kreirajNovuPlaylistu();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    private void ucitajDugmad() {
        panel = new JPanel();
        panel.setBackground(new Color(0, 77, 102));

        //Dodavanje dugmeta za dodavanje nove playliste
        dodaj=new JButton("Dodaj playlist");
        dodaj.setBackground(new Color(153, 204, 255));
        dodaj.setForeground(Color.white);

        panel.add(dodaj);
        panel.setPreferredSize(new Dimension(400, 70));
        add(panel,BorderLayout.SOUTH);
    }

    private void kreirajNovuPlaylistu() throws SQLException {
        String naziv = JOptionPane.showInputDialog("Unesite naziv plejliste");
        if(naziv.equals("")){
            JOptionPane.showMessageDialog(null,"Molimo da unesete naziv plejliste");
        }
        else{
            PlejLista p=new PlejLista();
            p.setNaziv(naziv);
            p.setJeJavna(false);
            PlejListaDAO.insert(p, korisnik.getId());
            PlejListaDAO.insertIzvodjenje(p, izvodjenje);
            JOptionPane.showMessageDialog(null, "Izvodjenje je dodano u "+p.getNaziv()+".");
        }

    }

    private void ucitajSkrol() {

        skrol=new JScrollPane();
        skrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        skrolPanel=new JPanel();
        skrolPanel.setLayout(new BoxLayout(skrolPanel, BoxLayout.Y_AXIS));
        resetElemente();

        for(PlejLista p : plejliste ){
            DodajIzvodjenje ez= new DodajIzvodjenje(this, izvodjenje,p);
            listaElemenata.add(ez);
            skrolPanel.add(ez);
            JLabel labela = new JLabel("                                        ");
            skrolPanel.add(labela);
        }

        skrolPanel.validate();
        skrolPanel.repaint();

        skrol.validate();
        skrol.repaint();

        skrol.setViewportView(skrolPanel);
        skrol.setPreferredSize(new Dimension(600, 170));
        add(skrol,BorderLayout.CENTER);
    }

    private void resetElemente() {
        listaElemenata.clear();
        skrolPanel.removeAll();
        skrolPanel.validate();
        skrolPanel.repaint();
    }
}
