package gui.elementi;

import dao.*;
import gui.dialogs.DialogRecenzije;
import gui.enums.TipRecenzije;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

public class PrikazMuzickogDela extends JDialog {

    public GlavniProzor glavniProzor;
    public JPanel panelGlavni;
    private JLabel labelaNaziva;
    private JLabel labelaOpis;
    private JLabel labelaSadrzaj;
    private JLabel labelaDatumPostavljanja;
    private JLabel labelaVremeNastanka;
    private JLabel labelaProsecnaOcena;
    private JLabel labelaAlbum;
    private JButton prikaziIzvodjenjaButton;
    private JButton prikaziUcesnikeButton;
    public JButton dodajRecenzijuButton;
    private JPanel panelSkrola;
    private JScrollPane skrol;
    private Dimension dimension;
    private JComboBox comboBoxZanrova;
    private JComboBox comboBoxUcesnika;
    private JComboBox comboBoxIzvodjenja;
    private int brojElemenata;
    private String prosecnaOcena;
    public int idMuzickogDela;

    public PrikazMuzickogDela(MuzickoDelo muzickoDelo, GlavniProzor gp) {
        super();
        idMuzickogDela = muzickoDelo.getId();
        glavniProzor = gp;
        setModal(true);
        setTitle("Prikaz muzickog dela");

        popuniMuzickoDelo(muzickoDelo);

        inicijalizuj(muzickoDelo);
        onemoguciDodavanjeRecenzije(muzickoDelo.getId(), gp);
        dodajKomponente();

        podesiAkcije(muzickoDelo, gp);

        ucitajRecenzije(muzickoDelo, gp);

        osveziLabeluProseka();

        add(panelGlavni);

        pack();
        setResizable(false);
        setLocationRelativeTo(gp);
    }

    public void osveziLabeluProseka() {
        labelaProsecnaOcena.setText("Prosecna ocena : " + prosecnaOcena);
    }

    private void popuniMuzickoDelo(MuzickoDelo muzickoDelo) {
        muzickoDelo.setListaZanrova(ZanrDAO.getZanroviPoMuzickomDelu(muzickoDelo));
        muzickoDelo.setListaIzvodjenja(IzvodjenjeDAO.getIzvodjenjaMuzickogDela(muzickoDelo));
        for (Izvodjenje izvodjenje : muzickoDelo.getListaIzvodjenja()) {
            izvodjenje.setListaMuzickihDela(MuzickoDeloDAO.getMuzickaDelaIzvodjenja(izvodjenje.getId()));
        }

        muzickoDelo.setListaUcesnika(UcesnikDAO.getUcesniciMuzickogDela(muzickoDelo));
    }

    private void onemoguciDodavanjeRecenzije(int idMuzickogDela, GlavniProzor gp) {
        if(gp instanceof KorisnikovProzor) {
            dodajRecenzijuButton.setVisible(true);
            dalJeRecenziranSadrzaj(idMuzickogDela, ((KorisnikovProzor) gp).idKorisnika, TipRecenzije.MUZICKO_DELO_REGISTROVANI);
        }else if(gp instanceof UrednikovProzor){
            dodajRecenzijuButton.setVisible(true);
            dalJeRecenziranSadrzaj(idMuzickogDela, ((UrednikovProzor) gp).idUrednika, TipRecenzije.MUZICKO_DELO_UREDNIK);
        }
    }

    private void dalJeRecenziranSadrzaj(int idSadrzaja, int idAutoraSadrazaja, TipRecenzije tipRecenzije) {
        boolean retVal = RecenzijaDAO.dalJeRecenzirao(idSadrzaja, idAutoraSadrazaja, tipRecenzije);
        dodajRecenzijuButton.setEnabled(!retVal);
    }

    private void inicijalizuj(MuzickoDelo muzickoDelo) {

        prikaziUcesnikeButton = new JButton("Prikazi ucesnika");
        prikaziIzvodjenjaButton = new JButton("Prikazi izvodjenje");
        dodajRecenzijuButton = new JButton("Dodaj");
        dodajRecenzijuButton.setVisible(false);
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        panelGlavni = new JPanel();
        panelGlavni.setBackground(new Color(105, 135, 139));

        labelaNaziva = new JLabel("Naziv : " + muzickoDelo.getNazivDela());
        labelaDatumPostavljanja = new JLabel("Datum postavljanja : " + muzickoDelo.getDatumPostavljanja());
        labelaVremeNastanka = new JLabel("Vreme nastanka : " + muzickoDelo.getVremeNastanka());
        labelaOpis = new JLabel("Opis : Nema opisa");
        labelaSadrzaj = new JLabel("Sadrzaj : Nema sadrzaja");
        labelaAlbum = new JLabel("Pripada albumu : Ne pripada");
        labelaProsecnaOcena = new JLabel("Prosecna ocena : " + prosecnaOcena);

        if (muzickoDelo.getOpis() != null)
            labelaOpis.setText("Opis : " + muzickoDelo.getOpis());

        if (muzickoDelo.getSadrzaj() != null)
            labelaOpis.setText("Sadrzaj : " + muzickoDelo.getSadrzaj());

        if (muzickoDelo.getAlbumKomPripada() != null)
            labelaAlbum.setText("Pripada albumu : " + muzickoDelo.getAlbumKomPripada().getNazivDela());

        comboBoxZanrova = new JComboBox<>(getNizZanrova(muzickoDelo.getListaZanrova()));
        comboBoxZanrova.setBackground(new Color(186, 186, 178));

        comboBoxIzvodjenja = new JComboBox<>(PrikazIzvodjaca.getNizIzvodjenja(muzickoDelo.getListaIzvodjenja()));
        comboBoxIzvodjenja.setBackground(new Color(186, 186, 178));

        comboBoxUcesnika = new JComboBox<>(getNizUcesnika(muzickoDelo.getListaUcesnika()));
        comboBoxUcesnika.setBackground(new Color(186, 186, 178));

        panelSkrola = new JPanel();
        panelSkrola.setBackground(new Color(153, 179, 185));
        panelSkrola.setLayout(new BoxLayout(panelSkrola, BoxLayout.Y_AXIS));

        skrol = new JScrollPane(panelSkrola);
        skrol.getVerticalScrollBar().setUnitIncrement(10);
        skrol.setPreferredSize(new Dimension(dimension.width / 4 + dimension.width / 18, 2 * dimension.height / 12));
        skrol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        brojElemenata = 3;
    }

    private void dodajKomponente() {
        GridBagLayout bg = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        panelGlavni.setLayout(bg);

        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 2;
        con.insets = new Insets(5, 20, 5, 20);
        con.anchor = GridBagConstraints.CENTER;
        panelGlavni.add(labelaNaziva, con);

        con.gridy = 1;
        panelGlavni.add(labelaDatumPostavljanja, con);

        con.gridy = 2;
        panelGlavni.add(labelaVremeNastanka, con);

        con.gridy = 3;
        panelGlavni.add(labelaOpis, con);

        con.gridy = 4;
        panelGlavni.add(labelaSadrzaj, con);

        con.gridy = 5;
        panelGlavni.add(labelaAlbum, con);

        con.gridy = 6;
        panelGlavni.add(labelaProsecnaOcena, con);

        con.gridy = 7;
        con.gridwidth = 1;
        con.anchor = GridBagConstraints.LINE_END;
        con.insets = new Insets(5, 20, 5, 2);
        panelGlavni.add(comboBoxZanrova, con);

        con.gridy = 8;
        panelGlavni.add(comboBoxIzvodjenja, con);

        con.gridx = 1;
        con.anchor = GridBagConstraints.LINE_START;
        con.insets = new Insets(5, 3, 5, 20);

        panelGlavni.add(prikaziIzvodjenjaButton, con);

        con.gridy = 9;
        con.gridx = 0;
        con.anchor = GridBagConstraints.LINE_END;
        con.insets = new Insets(5, 20, 5, 2);
        panelGlavni.add(comboBoxUcesnika, con);

        con.gridx = 1;
        con.anchor = GridBagConstraints.LINE_START;
        con.insets = new Insets(5, 3, 5, 20);
        panelGlavni.add(prikaziUcesnikeButton, con);

        con.gridy = 10;
        con.gridx = 0;
        con.fill = GridBagConstraints.BOTH;
        con.insets = new Insets(5, 25, 5, 25);
        con.anchor = GridBagConstraints.CENTER;
        con.gridwidth = 2;
        panelGlavni.add(skrol, con);

        con.gridy = 11;
        con.gridx = 1;
        con.fill = GridBagConstraints.NONE;
        con.insets = new Insets(5, 0, 10, 10);
        con.anchor = GridBagConstraints.LINE_END;
        con.gridwidth = 1;
        panelGlavni.add(dodajRecenzijuButton, con);
    }

    private void podesiAkcije(MuzickoDelo muzickoDelo, GlavniProzor gp) {

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

        prikaziUcesnikeButton.addActionListener(e -> {
            int index = comboBoxUcesnika.getSelectedIndex();
            if (index < 0) {
                JOptionPane.showMessageDialog(PrikazMuzickogDela.this, "Ne postoji ucesnik");
            } else {
                PrikazUcesnika pu = new PrikazUcesnika(muzickoDelo.getListaUcesnika().get(index), gp);
                PrikazMuzickogDela.this.dispose();
                pu.setVisible(true);
            }
        });

        dodajRecenzijuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogRecenzije dr = new DialogRecenzije(null, PrikazMuzickogDela.this);
                dr.setVisible(true);
            }
        });

        prikaziIzvodjenjaButton.addActionListener(e -> {
            int index = comboBoxIzvodjenja.getSelectedIndex();
            if (index < 0) {
                JOptionPane.showMessageDialog(PrikazMuzickogDela.this, "Ne postoji muzicko delo");
            } else {
                PrikazIzvodjenja pi = new PrikazIzvodjenja(muzickoDelo.getListaIzvodjenja().get(index), gp);
                PrikazMuzickogDela.this.dispose();
                pi.setVisible(true);
            }
        });
    }

    public void ucitajRecenzije(MuzickoDelo muzickoDelo, GlavniProzor gp) {
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

    public static String[] getNizZanrova(List<Zanr> listaZanrova) {
        String[] itemsArray = new String[listaZanrova.size()];
        int index = 0;
        for (Zanr zanr : listaZanrova) {
            itemsArray[index] = zanr.getNazivZanra();
            index++;
        }
        return itemsArray;
    }
}