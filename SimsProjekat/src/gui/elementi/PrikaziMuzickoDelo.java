package gui.elementi;

import dao.*;
import model.Izvodjenje;
import model.MuzickoDelo;
import model.Ucesnik;
import model.Zanr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PrikaziMuzickoDelo extends JDialog {
    private JPanel panelGlavni;
    private JLabel labelaNaziva;
    private JLabel labelaOpis;
    private JLabel labelaSadrzaj;
    private JLabel labelaDatumPostavljanja;
    private JLabel labelaVremeNastanka;
    private JLabel labelaProsecnaOcena;
    private JLabel labelaAlbum;
    private JButton prikaziIzvodjenjaButton;
    private JButton prikaziUcesnikeButton;
    private Dimension dimension;
    private JComboBox comboBoxZanrova;
    private JComboBox comboBoxUcesnika;
    private JComboBox comboBoxIzvodjenja;

    public PrikaziMuzickoDelo(MuzickoDelo muzickoDelo, GlavniProzor gp) {
        super();
        setModal(true);
        setTitle("Prikaz informacija izvodjaca");

        popuniMuzickoDelo(muzickoDelo);

        inicijalizuj(muzickoDelo);

        dodeliAcije(muzickoDelo, gp);

        add(panelGlavni);

        setSize(dimension.width / 3, dimension.height / 4);

        setResizable(false);
        setLocationRelativeTo(gp);

    }

    private void dodeliAcije(MuzickoDelo muzickoDelo, GlavniProzor gp) {

        prikaziIzvodjenjaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxIzvodjenja.getSelectedIndex();
                if (index < 0){
                    JOptionPane.showMessageDialog(PrikaziMuzickoDelo.this, "Ne postoji muzicko delo");
                } else {
                    PrikazIzvodjenja pi = new PrikazIzvodjenja(muzickoDelo.getListaIzvodjenja().get(index), gp);
                    PrikaziMuzickoDelo.this.dispose();
                    pi.setVisible(true);
                }
            }
        });

        prikaziUcesnikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxUcesnika.getSelectedIndex();
                if (index < 0){
                    JOptionPane.showMessageDialog(PrikaziMuzickoDelo.this, "Ne postoji ucesnik");
                } else {
                    PrikazUcesnika pu = new PrikazUcesnika(muzickoDelo.getListaUcesnika().get(index), gp);
                    PrikaziMuzickoDelo.this.dispose();
                    pu.setVisible(true);
                }
            }
        });

    }

    private void popuniMuzickoDelo(MuzickoDelo muzickoDelo) {
        muzickoDelo.setListaZanrova(ZanrDAO.getZanroviPoMuzickomDelu(muzickoDelo));
        muzickoDelo.setListaIzvodjenja(IzvodjenjeDAO.getIzvodjenjaMuzickogDela(muzickoDelo));
        for (Izvodjenje izvodjenje : muzickoDelo.getListaIzvodjenja()) {
            izvodjenje.setListaMuzickihDela(MuzickoDeloDAO.getMuzickaDelaIzvodjenja(izvodjenje.getId()));
        }

        muzickoDelo.setListaUcesnika(UcesnikDAO.getUcesniciMuzickogDela(muzickoDelo));


    }

    private void inicijalizuj(MuzickoDelo muzickoDelo) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();

        labelaNaziva.setText("Naziv : " + muzickoDelo.getNazivDela());
        labelaOpis.setText("Opis : " + muzickoDelo.getOpis());
        labelaSadrzaj.setText("Sadrzaj : " + muzickoDelo.getSadrzaj());
        labelaProsecnaOcena.setText("Prosecna ocena : " + muzickoDelo.getProsecnaOcena());
        labelaDatumPostavljanja.setText("Datum postavljanja : " + muzickoDelo.getDatumPostavljanja());
        labelaVremeNastanka.setText("Vreme nastanka : " + muzickoDelo.getVremeNastanka());

        if (muzickoDelo.getAlbumKomPripada() == null) {
            labelaAlbum.setText("Pripada albumu : Ne pripada");
        } else {
            labelaAlbum.setText("Pripada albumu : " + muzickoDelo.getAlbumKomPripada().getNazivDela());
        }


        comboBoxZanrova = new JComboBox<String>(getNizZanrova(muzickoDelo.getListaZanrova()));
        comboBoxZanrova.setBackground(new Color(186,186,178));
        panelGlavni.add(comboBoxZanrova);

        comboBoxIzvodjenja = new JComboBox<String>(PrikaziIzvodjaca.getNizIzvodjenja(muzickoDelo.getListaIzvodjenja()));
        comboBoxIzvodjenja.setBackground(new Color(186,186,178));
        panelGlavni.add(comboBoxIzvodjenja);

        comboBoxUcesnika = new JComboBox<String>(getNizUcesnika(muzickoDelo.getListaUcesnika()));
        comboBoxUcesnika.setBackground(new Color(186,186,178));
        panelGlavni.add(comboBoxUcesnika);
    }

    private static String[] getNizUcesnika(List<Ucesnik> listaUcesnika) {
        String[] itemsArray = new String[listaUcesnika.size()];
        int index = 0;
        for (Ucesnik ucesnik : listaUcesnika) {
            itemsArray[index] = ucesnik.getNaziv();
            index++;
        }
        return itemsArray;
    }

    private static String[] getNizZanrova(List<Zanr> listaZanrova) {
        String[] itemsArray = new String[listaZanrova.size()];
        int index = 0;
        for (Zanr zanr : listaZanrova) {
            itemsArray[index] = zanr.getNazivZanra();
            index++;
        }
        return itemsArray;
    }


}
