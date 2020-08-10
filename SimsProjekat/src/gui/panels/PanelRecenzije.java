package gui.panels;

import dao.IzvodjenjeDAO;
import dao.MuzickoDeloDAO;
import gui.dialogs.DialogRecenzije;
import gui.elementi.GlavniProzor;
import gui.elementi.KorisnikovProzor;
import gui.elementi.UrednikovProzor;
import gui.enums.TipRecenzije;
import kontroler.RecenzijaKON;
import model.Izvodjenje;
import model.MuzickoDelo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class PanelRecenzije extends JPanel implements ActionListener{

    private final GlavniProzor glavniProzor;
    private final DialogRecenzije dialog;
    private final String separator;
    private final int idIzvodjenja;
    private final int idMuzickogDela;
    private int ocena;
    private JLabel komentarL, ocenaL, upozorenjeL;
    private JLabel zvezdaL1, zvezdaL2, zvezdaL3, zvezdaL4, zvezdaL5;
    private JTextArea komentarArea;
    private Icon zuta, bela;
    private JButton potvrdiButton;

    public PanelRecenzije(DialogRecenzije dr, int idIzvodjenja, int idMuzickogDela, GlavniProzor gp) {

        this.idIzvodjenja = idIzvodjenja;
        this.idMuzickogDela = idMuzickogDela;
        dialog = dr;
        glavniProzor = gp;
        separator = System.getProperty("file.separator");

        setBackground(new Color(188, 204, 111));
        podesiKomponente();
        setOkvir();
        podesiOsluskivaceMisa();
        dodajKomponente();
    }

    private void podesiKomponente() {

        komentarL = new JLabel("Komentar:");
        ocenaL = new JLabel("Ocena:");
        upozorenjeL = new JLabel("");
        komentarArea = new JTextArea();

        bela = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "whiteStar.jpg");
        zuta = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "yellowStar.jpg");
        zvezdaL1 = new JLabel(bela);
        zvezdaL2 = new JLabel(bela);
        zvezdaL3 = new JLabel(bela);
        zvezdaL4 = new JLabel(bela);
        zvezdaL5 = new JLabel(bela);
        potvrdiButton = new JButton("Potvrdi");
        potvrdiButton.addActionListener(this);
    }

    private void setOkvir() {

        Border inBorder = BorderFactory.createTitledBorder("Unesite podatke");
        Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

        setBorder(BorderFactory.createCompoundBorder(outBorder, inBorder));
    }

    private void dodajKomponente() {

        GridBagLayout bg = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        setLayout(bg);

        con.gridx = 0;
        con.gridy = 0;
        con.insets = new Insets(5, 10, 5, 0);
        con.anchor = GridBagConstraints.LINE_START;
        add(komentarL, con);

        con.gridy = 1;
        con.gridwidth = 5;
        con.gridheight = 2;
        con.fill = GridBagConstraints.BOTH;
        add(komentarArea, con);

        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(ocenaL, con);

        con.gridy = 4;
        con.insets = new Insets(5, 10, 5, 10);
        add(zvezdaL1, con);

        con.gridx = 1;
        add(zvezdaL2, con);

        con.gridx = 2;
        add(zvezdaL3, con);

        con.gridx = 3;
        add(zvezdaL4, con);

        con.gridx = 4;
        add(zvezdaL5, con);

        con.gridx = 4;
        con.gridy = 5;
        add(potvrdiButton, con);

        con.gridx = 0;
        con.gridy = 6;
        con.gridwidth = 5;
        add(upozorenjeL, con);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String text = komentarArea.getText();
        TipRecenzije tipRecenzije;
        int idAutora;
        if(glavniProzor instanceof KorisnikovProzor && idIzvodjenja != 0) {
            tipRecenzije = TipRecenzije.IZVODJENJE_REGISTROVANI;
            idAutora = ((KorisnikovProzor) glavniProzor).idKorisnika;
        } else if(glavniProzor instanceof UrednikovProzor && idIzvodjenja != 0) {
            tipRecenzije = TipRecenzije.IZVODJENJE_UREDNIK;
            idAutora = ((UrednikovProzor) glavniProzor).idUrednika;
        }else if(glavniProzor instanceof KorisnikovProzor && idMuzickogDela != 0) {
            tipRecenzije = TipRecenzije.MUZICKO_DELO_REGISTROVANI;
            idAutora = ((KorisnikovProzor) glavniProzor).idKorisnika;
        } else {
            tipRecenzije = TipRecenzije.MUZICKO_DELO_UREDNIK;
            idAutora = ((UrednikovProzor) glavniProzor).idUrednika;
        }

        try {
            if(idIzvodjenja != 0)
                RecenzijaKON.upisiPodatke(ocena, text, idIzvodjenja, idAutora, tipRecenzije);
            else
                RecenzijaKON.upisiPodatke(ocena, text, idMuzickogDela, idAutora, tipRecenzije);
        } catch (Exception ex) {
            if(ex.getMessage().equals("0")) {
                upozorenjeL.setText("Morate uneti ocenu !");
                return;
            }else if(ex.getMessage().equals("prazno")) {
                upozorenjeL.setText("Morate uneti komentar !");
                return;
            }
        }

        if(idIzvodjenja != 0) {
            Izvodjenje iz = IzvodjenjeDAO.getIzvodjenje(idIzvodjenja);
            dialog.prikazIzvodjenja.ucitajRecenzije(iz, glavniProzor);
            dialog.prikazIzvodjenja.dodajRecenzijuB.setEnabled(false);
            dialog.prikazIzvodjenja.panelGlavni.validate();
            dialog.prikazIzvodjenja.panelGlavni.repaint();
        }else {
            MuzickoDelo md = MuzickoDeloDAO.getMuzickoDelo(idMuzickogDela);
            dialog.prikazMuzickogDela.ucitajRecenzije(md, glavniProzor);
            dialog.prikazMuzickogDela.dodajRecenzijuButton.setEnabled(false);
            dialog.prikazMuzickogDela.osveziLabeluProseka();
            dialog.prikazMuzickogDela.panelGlavni.validate();
            dialog.prikazMuzickogDela.panelGlavni.repaint();
        }
        dialog.dispose();
    }
    public void podesiOsluskivaceMisa() {
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                final int x = e.getX();
                final int y = e.getY();
                final Rectangle okvirZvezde1 = zvezdaL1.getBounds();
                final Rectangle okvirZvezde2 = zvezdaL2.getBounds();
                final Rectangle okvirZvezde3 = zvezdaL3.getBounds();
                final Rectangle okvirZvezde4 = zvezdaL4.getBounds();
                final Rectangle okvirZvezde5 = zvezdaL5.getBounds();

                if (okvirZvezde1 != null && okvirZvezde1.contains(x, y)) {
                    zvezdaL1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                }else if (okvirZvezde2 != null && okvirZvezde2.contains(x, y)) {
                    zvezdaL2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                }else if (okvirZvezde3 != null && okvirZvezde3.contains(x, y)) {
                    zvezdaL3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                }else if (okvirZvezde4 != null && okvirZvezde4.contains(x, y)) {
                    zvezdaL4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                }else if (okvirZvezde5 != null && okvirZvezde5.contains(x, y)) {
                    zvezdaL5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                }else {
                    dialog.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                final int x = e.getX();
                final int y = e.getY();
                final Rectangle okvirZvezde1 = zvezdaL1.getBounds();
                final Rectangle okvirZvezde2 = zvezdaL2.getBounds();
                final Rectangle okvirZvezde3 = zvezdaL3.getBounds();
                final Rectangle okvirZvezde4 = zvezdaL4.getBounds();
                final Rectangle okvirZvezde5 = zvezdaL5.getBounds();

                if (okvirZvezde1 != null && okvirZvezde1.contains(x, y)) {
                    ocena = 1;
                    zvezdaL1.setIcon(zuta);
                    zvezdaL2.setIcon(bela);
                    zvezdaL3.setIcon(bela);
                    zvezdaL4.setIcon(bela);
                    zvezdaL5.setIcon(bela);
                }else if (okvirZvezde2 != null && okvirZvezde2.contains(x, y)) {
                    ocena = 2;
                    zvezdaL1.setIcon(zuta);
                    zvezdaL2.setIcon(zuta);
                    zvezdaL3.setIcon(bela);
                    zvezdaL4.setIcon(bela);
                    zvezdaL5.setIcon(bela);
                }else if (okvirZvezde3 != null && okvirZvezde3.contains(x, y)) {
                    ocena = 3;
                    zvezdaL1.setIcon(zuta);
                    zvezdaL2.setIcon(zuta);
                    zvezdaL3.setIcon(zuta);
                    zvezdaL4.setIcon(bela);
                    zvezdaL5.setIcon(bela);
                }else if (okvirZvezde4 != null && okvirZvezde4.contains(x, y)) {
                    ocena = 4;
                    zvezdaL1.setIcon(zuta);
                    zvezdaL2.setIcon(zuta);
                    zvezdaL3.setIcon(zuta);
                    zvezdaL4.setIcon(zuta);
                    zvezdaL5.setIcon(bela);
                }else if (okvirZvezde5 != null && okvirZvezde5.contains(x, y)) {
                    ocena = 5;
                    zvezdaL1.setIcon(zuta);
                    zvezdaL2.setIcon(zuta);
                    zvezdaL3.setIcon(zuta);
                    zvezdaL4.setIcon(zuta);
                    zvezdaL5.setIcon(zuta);
                }
            }
        });
    }
}
