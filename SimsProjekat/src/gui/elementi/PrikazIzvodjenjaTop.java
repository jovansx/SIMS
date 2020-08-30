package gui.elementi;
import dao.IzvodjenjeDAO;
import dao.RecenzijaDAO;
import jaco.mp3.player.MP3Player;
import kontroler.UrednikovProzorKON;
import model.Izvodjenje;
import model.Recenzija;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class PrikazIzvodjenjaTop extends PrikazIzvodjenja {
    private Top t;
    private JLabel mdelo,izv;
    private JButton glasaj;
    public PrikazIzvodjenjaTop(Izvodjenje iz, Top top) {
        super(iz, top);
        idIzvodjenja = iz.getId();
        t = top;
        inizijalizuj(iz);
        dodajNaGlavniPanel();
        podesiAkcije(iz, t);

        ucitajRecenzije(iz, t);

        add(panelGlavni);
        pack();

        setResizable(false);
        setLocationRelativeTo(t);
    }
    private void inizijalizuj(Izvodjenje iz) {

        Color zelena = new Color(188, 204, 111);
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy.");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        String sep = System.getProperty("file.separator");
        player = new MP3Player(new File("AudioBaza" + sep + "audio.mp3"));
        brojRecenzija = 3;

        panelGlavni = new JPanel();
        okvir = new JPanel();

        panelGlavni.setBackground(new Color(105, 135, 139));
        okvir.setBackground(Color.BLACK);

        JLabel slikaL = new JLabel();
        slikaL.setSize(200, dimension.height / 20 * 3 - 10);
        ImageIcon retImageIcon = IzvodjenjeDAO.getSlikuIzvodjenja(iz, sep);
        Image im = retImageIcon.getImage();
        Image myImg = im.getScaledInstance(slikaL.getWidth(), slikaL.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon newImage = new ImageIcon(myImg);
        slikaL.setIcon(newImage);
        okvir.add(slikaL);

        nazivDelaL = new JLabel("<html><p style=\"font-size:15px\">" + ElementIzvodjenja.generisiNazivIzvodjaca(iz) + "</p></html>");
        vremeMestoL = new JLabel(formater.format(iz.getVremeIzvodjenja()) + "     " + iz.getMestoIzvodjenja().getNazivMesta());
        pristupTrajanjeL = new JLabel(iz.getBrPristupa() + " prikaza      " + iz.getTrajanje() + " min.");
        tipIzvodjenjaL = new JLabel("Tip izvodjenja : " + iz.getTipIzvodjenja());

        pustiB = new JButton("Pusti");
        pauzirajB = new JButton("Pauziraj");
        stopirajB = new JButton("Stopiraj");
        glasaj = new JButton("Glasaj");
        mdelo = new JLabel("Muzicka dela: ");
        izv = new JLabel("Izvodjaci: ");

        pauzirajB.setEnabled(false);
        stopirajB.setEnabled(false);

        pustiB.setBackground(zelena);
        pauzirajB.setBackground(zelena);
        stopirajB.setBackground(zelena);
        glasaj.setBackground(zelena);

        String[] nizIzvodjaca = getNizIzvodjaca(iz.getListaIzvodjaca());
        comboBoxIzvodjaca = new JComboBox<String>(nizIzvodjaca);
        if (nizIzvodjaca.length == 0) {
            comboBoxIzvodjaca.setEnabled(false);
        }

        String[] nizMuzickihDela = getNizMuzickihDela(IzvodjenjeDAO.getMuzickaDela(iz.getId()));
        comboBoxMuzickihDela = new JComboBox<String>(nizMuzickihDela);
        if (nizMuzickihDela.length == 0) {
            comboBoxMuzickihDela.setEnabled(false);
        }

        panelSkrola = new JPanel();
        panelSkrola.setLayout(new BoxLayout(panelSkrola, BoxLayout.Y_AXIS));

        skrol = new JScrollPane(panelSkrola);
        skrol.getVerticalScrollBar().setUnitIncrement(10);
        skrol.setPreferredSize(new Dimension(dimension.width / 4 + dimension.width / 18, 2 * dimension.height / 12));
        skrol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void dodajNaGlavniPanel() {
        GridBagLayout bg = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        panelGlavni.setLayout(bg);

        con.fill = GridBagConstraints.BOTH;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 4;
        con.insets = new Insets(10, 20, 5, 20);
        con.anchor = GridBagConstraints.CENTER;
        panelGlavni.add(okvir, con);

        con.gridy = 1;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.LINE_END;
        con.insets = new Insets(5, 20, 15, 5);
        panelGlavni.add(pustiB, con);

        con.gridx = 1;
        con.insets = new Insets(5, 5, 15, 5);
        panelGlavni.add(pauzirajB, con);

        con.gridx = 2;
        panelGlavni.add(stopirajB, con);

        con.gridx = 3;
        con.insets = new Insets(5, 5, 15, 20);
        panelGlavni.add(glasaj,con);

        con.insets = new Insets(5, 20, 10, 20);
        con.gridy = 2;
        con.gridx = 0;
        con.gridwidth = 4;
        con.fill = GridBagConstraints.HORIZONTAL;
        panelGlavni.add(nazivDelaL, con);

        con.gridy = 3;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.FIRST_LINE_START;
        con.anchor = GridBagConstraints.LINE_START;
        panelGlavni.add(vremeMestoL, con);

        con.gridx = 2;
        con.fill = GridBagConstraints.LAST_LINE_END;
        con.anchor = GridBagConstraints.LINE_END;
        panelGlavni.add(pristupTrajanjeL, con);

        con.gridy = 4;
        con.gridx = 0;
        con.gridwidth = 4;
        panelGlavni.add(tipIzvodjenjaL, con);

        con.gridy = 5;
        con.gridwidth = 2;
        con.insets = new Insets(5, 20, 5, 2);
        panelGlavni.add(izv,con);

        con.gridx = 2;
        con.anchor = GridBagConstraints.LINE_START;
        con.insets = new Insets(5, 3, 5, 20);
        panelGlavni.add(comboBoxIzvodjaca,con);

        con.gridy = 6;
        con.gridx = 0;
        con.anchor = GridBagConstraints.LINE_END;
        con.insets = new Insets(5, 20, 5, 2);

        panelGlavni.add(mdelo,con);

        con.gridx = 2;
        con.anchor = GridBagConstraints.LINE_START;
        con.fill = GridBagConstraints.FIRST_LINE_START;
        con.insets = new Insets(5, 3, 5, 20);
        panelGlavni.add(comboBoxMuzickihDela,con);

        con.gridy = 7;
        con.gridx = 0;
        con.fill = GridBagConstraints.BOTH;
        con.insets = new Insets(5, 25, 5, 25);
        con.anchor = GridBagConstraints.CENTER;
        con.gridwidth = 4;
        panelGlavni.add(skrol, con);
    }
    private void podesiAkcije(Izvodjenje iz,Top t) {

        PrikazIzvodjenjaTop.this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                player.stop();
                if(file != null) file.delete();
            }
        });

        skrol.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JViewport vp = skrol.getViewport();
                if (vp.getView().getHeight() <= vp.getHeight() + vp.getViewPosition().y) {
                    brojRecenzija += 1;
                    ucitajRecenzije(iz, t);
                }
            }
        });
        pustiB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file == null)
                    file = IzvodjenjeDAO.getAudioIzvodjenja(idIzvodjenja, sep);
                player.play();
                pauzirajB.setEnabled(true);
                stopirajB.setEnabled(true);
                pustiB.setEnabled(false);
            }
        });

        pauzirajB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.pause();
                pauzirajB.setEnabled(false);
                stopirajB.setEnabled(true);
                pustiB.setEnabled(true);
            }
        });

        stopirajB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.stop();
                pauzirajB.setEnabled(false);
                stopirajB.setEnabled(false);
                pustiB.setEnabled(true);
            }
        });

        glasaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.stop();
                try {
                    UrednikovProzorKON.glasaj(iz);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                JOptionPane.showMessageDialog(PrikazIzvodjenjaTop.this,"Uspesno ste glasali!");
            }
        });



    }
    public void ucitajRecenzije(Izvodjenje iz, Top t) {
        panelSkrola.removeAll();
        t.osveziKomponentu(panelSkrola);
        List<Recenzija> recenzije = RecenzijaDAO.getRecenzijeIzvodjenja(iz.getId(), brojRecenzija);

        for (Recenzija r : recenzije)
            panelSkrola.add(new ElementRecenzija(r));

        t.osveziKomponentu(skrol);
    }


}