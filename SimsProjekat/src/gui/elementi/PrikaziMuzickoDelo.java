package gui.elementi;

import dao.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
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
    private JPanel panelSkrola;
    private JScrollPane skrol;
    private Dimension dimension;
    private JComboBox comboBoxZanrova;
    private JComboBox comboBoxUcesnika;
    private JComboBox comboBoxIzvodjenja;
    private int brojElemenata;
    private String prosecnaOcena;

    public PrikaziMuzickoDelo(MuzickoDelo muzickoDelo, GlavniProzor gp) {
        super();
        setModal(true);
        setTitle("Prikaz informacija izvodjaca");

        popuniMuzickoDelo(muzickoDelo);

        inicijalizuj(muzickoDelo);

        dodeliAcije(muzickoDelo, gp);

        ucitajRecenzije(muzickoDelo, gp);

        osveziLabeluProseka();

        add(panelGlavni);

        setSize(dimension.width / 3, dimension.height / 2);

        setResizable(false);
        setLocationRelativeTo(gp);

    }

    private void osveziLabeluProseka() {
        labelaProsecnaOcena.setText("Prosecna ocena : " + prosecnaOcena);
    }

    private void ucitajRecenzije(MuzickoDelo muzickoDelo, GlavniProzor gp) {
        resetRecenzije(gp);

        List<Recenzija> recenzije = RecenzijaDAO.getRecenzijeMuzickogDela(muzickoDelo.getId(), brojElemenata);
        for (Recenzija r : recenzije) {
            ElementRecenzija el = new ElementRecenzija(r);
            panelSkrola.add(el);
        }

        gp.osveziKomponentu(skrol);

        prosecnaOcena = generisiProsecnuOcenu(recenzije);
    }

    private void resetRecenzije(GlavniProzor gp) {
        panelSkrola.removeAll();
        gp.osveziKomponentu(panelSkrola);
    }

    private void dodeliAcije(MuzickoDelo muzickoDelo, GlavniProzor gp) {

        prikaziIzvodjenjaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxIzvodjenja.getSelectedIndex();
                if (index < 0) {
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
                if (index < 0) {
                    JOptionPane.showMessageDialog(PrikaziMuzickoDelo.this, "Ne postoji ucesnik");
                } else {
                    PrikazUcesnika pu = new PrikazUcesnika(muzickoDelo.getListaUcesnika().get(index), gp);
                    PrikaziMuzickoDelo.this.dispose();
                    pu.setVisible(true);
                }
            }
        });

        skrol.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JViewport vp = skrol.getViewport();
                if (vp.getView().getHeight() <= vp.getHeight() + vp.getViewPosition().y) {
                    brojElemenata += 1;
                    ucitajRecenzije(muzickoDelo, gp);
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
        labelaDatumPostavljanja.setText("Datum postavljanja : " + muzickoDelo.getDatumPostavljanja());
        labelaVremeNastanka.setText("Vreme nastanka : " + muzickoDelo.getVremeNastanka());

        if (muzickoDelo.getOpis() == null) {
            labelaOpis.setText("Opis : Nema opisa");
        } else {
            labelaOpis.setText("Opis : " + muzickoDelo.getOpis());
        }

        if (muzickoDelo.getOpis() == null) {
            labelaSadrzaj.setText("Opis : Nema sadrzaja");
        } else {
            labelaSadrzaj.setText("Opis : " + muzickoDelo.getSadrzaj());
        }

        if (muzickoDelo.getAlbumKomPripada() == null) {
            labelaAlbum.setText("Pripada albumu : Ne pripada");
        } else {
            labelaAlbum.setText("Pripada albumu : " + muzickoDelo.getAlbumKomPripada().getNazivDela());
        }

        comboBoxZanrova = new JComboBox<String>(getNizZanrova(muzickoDelo.getListaZanrova()));
        comboBoxZanrova.setBackground(new Color(186, 186, 178));
        panelGlavni.add(comboBoxZanrova);

        comboBoxIzvodjenja = new JComboBox<String>(PrikaziIzvodjaca.getNizIzvodjenja(muzickoDelo.getListaIzvodjenja()));
        comboBoxIzvodjenja.setBackground(new Color(186, 186, 178));
        panelGlavni.add(comboBoxIzvodjenja);

        comboBoxUcesnika = new JComboBox<String>(getNizUcesnika(muzickoDelo.getListaUcesnika()));
        comboBoxUcesnika.setBackground(new Color(186, 186, 178));
        panelGlavni.add(comboBoxUcesnika);

        panelSkrola.setBackground(new Color(153, 179, 185));
        panelSkrola.setLayout(new BoxLayout(panelSkrola, BoxLayout.Y_AXIS));

        skrol.getVerticalScrollBar().setUnitIncrement(10);
        skrol.setPreferredSize(new Dimension(dimension.width / 4 + dimension.width / 18, 2 * dimension.height / 12));
        brojElemenata = 3;
    }

    private String generisiProsecnuOcenu(List<Recenzija> recenzije) {
        int sum = 0;
        for (Recenzija r : recenzije) {
            sum += r.getOcena();
        }
        double prosek;
        if (recenzije.size() == 0)
            prosek = 0;
        else
            prosek = (double) sum / recenzije.size();

        return String.valueOf(String.format("%.2f", prosek));
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
