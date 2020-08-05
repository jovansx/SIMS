package gui.elementi;

import model.Izvodjac;
import model.MuzickoDelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrikaziMuzickoDelo extends JDialog {
    private JPanel panelGlavni;
    private JLabel labelaNaziva;
    private JLabel labelaOpis;
    private JLabel labelaSadrzaj;
    private JLabel labelaDatumPostavljanja;
    private JLabel labelaVremeNastanka;
    private JLabel labelaProsecnaOcena;
    private JLabel labelaAlbum;
    public PrikaziMuzickoDelo(MuzickoDelo muzickoDelo, PrikazElementa pe) {
        super();
        setModal(true);
        setTitle("Prikaz informacija izvodjaca");

        //popuniIzvodjaca(muzickoDelo);

        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();

        podesiKomponente(muzickoDelo, dimension, pe);

        add(panelGlavni);

        setSize(dimension.width / 4, dimension.height / 4);

        setResizable(false);
        setLocationRelativeTo(pe);
    }

    private void podesiKomponente(MuzickoDelo muzickoDelo, Dimension dim, PrikazElementa pe){
        labelaNaziva.setText("Naziv : "+muzickoDelo.getNazivDela());
        labelaOpis.setText("Opis : "+muzickoDelo.getOpis());
        labelaSadrzaj.setText("Sadrzaj : "+muzickoDelo.getSadrzaj());
        labelaProsecnaOcena.setText("Prosecna ocena : "+muzickoDelo.getProsecnaOcena());
        labelaDatumPostavljanja.setText("Datum postavljanja : "+muzickoDelo.getDatumPostavljanja());
        labelaVremeNastanka.setText("Vreme nastanka : "+muzickoDelo.getVremeNastanka());

        if (muzickoDelo.getAlbumKomPripada()==null){
            labelaAlbum.setText("Pripada albumu : Ne pripada");
        } else {
            labelaAlbum.setText("Pripada albumu : "+muzickoDelo.getAlbumKomPripada().getNazivDela());
        }

        /*
        comboBoxClanova = new JComboBox<String>(PrikazElementa.getNizIzvodjaca(izvodjac.getImaClanove()));
        comboBoxClanova.setBackground(new Color(186,186,178));
        panelGlavni.add(comboBoxClanova);

        comboBoxIzvodjenja = new JComboBox<String>(getNizIzvodjenja(izvodjac.getListaIzvodjenja()));
        comboBoxIzvodjenja.setBackground(new Color(186,186,178));
        panelGlavni.add(comboBoxIzvodjenja);

        buttonPrikaziIzvodjaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxClanova.getSelectedIndex();
                if (index < 0){
                    JOptionPane.showMessageDialog(PrikaziIzvodjaca.this, "Ne postoji izvodjac");
                } else {
                    PrikaziIzvodjaca pi = new PrikaziIzvodjaca(izvodjac.getImaClanove().get(index), pe);
                    pi.setVisible(true);
                }
            }
        });

         */

    }

}
