package gui.elementi;

import dao.IzvodjacDAO;
import dao.IzvodjenjeDAO;
import dao.RecenzijaDAO;
import gui.dialogs.DialogRecenzije;
import gui.enums.TipRecenzije;
import jaco.mp3.player.MP3Player;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class PrikazIzvodjenja extends JDialog {

    protected JButton prikaziIzvodjacaB, prikaziMuzickoDeloB;
    protected JButton pustiB, pauzirajB, stopirajB, preuzmiB, dodajB;
    public JButton dodajRecenzijuB;
    protected JComboBox<String> comboBoxIzvodjaca, comboBoxMuzickihDela;
    public JPanel panelGlavni, panelSkrola, okvir;
    protected JLabel pristupTrajanjeL, vremeMestoL;
    protected JLabel tipIzvodjenjaL, nazivDelaL;
    public GlavniProzor glavniProzor;
    protected JScrollPane skrol;
    protected int brojRecenzija;
    public int idIzvodjenja;
    protected String sep;
    protected MP3Player player;
    protected File file;

    public PrikazIzvodjenja(Izvodjenje iz, GlavniProzor gp) {
        super();
        idIzvodjenja = iz.getId();
        glavniProzor = gp;
        setModal(true);
        setTitle("Prikaz informacija izvodjenja");

        popuniIzvodjenje(iz);

        inizijalizuj(iz);
        onemoguciDodavanjeRecenzije(iz.getId(), gp);

        dodajNaGlavniPanel();
        podesiAkcije(iz, gp);

        ucitajRecenzije(iz, gp);

        add(panelGlavni);
        pack();

        setResizable(false);
        setLocationRelativeTo(gp);
    }
    public PrikazIzvodjenja(Izvodjenje iz,Top top){
        idIzvodjenja = iz.getId();
        setModal(true);
        setTitle("Prikaz informacija izvodjenja");
        popuniIzvodjenje(iz);

    }

    private void onemoguciDodavanjeRecenzije(int idIzvodjenja, GlavniProzor gp) {
        if(gp instanceof KorisnikovProzor) {
            dodajRecenzijuB.setVisible(true);
            dalJeRecenziranSadrzaj(idIzvodjenja, ((KorisnikovProzor) gp).idKorisnika, TipRecenzije.IZVODJENJE_REGISTROVANI);
        }else if(gp instanceof UrednikovProzor){
            dodajRecenzijuB.setVisible(true);
            dalJeRecenziranSadrzaj(idIzvodjenja, ((UrednikovProzor) gp).idUrednika, TipRecenzije.IZVODJENJE_UREDNIK);
        }
    }

    private void dalJeRecenziranSadrzaj(int idSadrzaja, int idAutoraSadrazaja, TipRecenzije tipRecenzije) {
        boolean retVal = RecenzijaDAO.dalJeRecenzirao(idSadrzaja, idAutoraSadrazaja, tipRecenzije);
        dodajRecenzijuB.setEnabled(!retVal);
    }

    private void popuniIzvodjenje(Izvodjenje iz) {
        iz.setListaIzvodjaca(IzvodjacDAO.popuniListeIzvodjenja(iz));
    }

    private void inizijalizuj(Izvodjenje iz) {

        Color zelena = new Color(188, 204, 111);
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy.");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        sep = System.getProperty("file.separator");
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

        nazivDelaL = new JLabel("<html><p style=\"font-size:15px\">" + ElementIzvodjenja.generisiNazivIzvodjaca(iz) +"</p></html>");
        vremeMestoL = new JLabel(formater.format(iz.getVremeIzvodjenja()) + "     " + iz.getMestoIzvodjenja().getNazivMesta());
        pristupTrajanjeL = new JLabel(iz.getBrPristupa() + " prikaza      " + iz.getTrajanje() + " min.");
        tipIzvodjenjaL = new JLabel("Tip izvodjenja : " + iz.getTipIzvodjenja());

        prikaziIzvodjacaB = new JButton("Prikazi izvodjaca");
        prikaziMuzickoDeloB = new JButton("Prikazi muzicko delo");
        dodajRecenzijuB = new JButton("Dodaj");
        pustiB = new JButton("Pusti");
        pauzirajB = new JButton("Pauziraj");
        stopirajB = new JButton("Stopiraj");
        preuzmiB = new JButton("Preuzmi");
        dodajB = new JButton("Dodaj");

        pauzirajB.setEnabled(false);
        stopirajB.setEnabled(false);
        dodajRecenzijuB.setVisible(false);

        pustiB.setBackground(zelena);
        pauzirajB.setBackground(zelena);
        stopirajB.setBackground(zelena);
        preuzmiB.setBackground(zelena);
        dodajB.setBackground(zelena);
        dodajRecenzijuB.setBackground(zelena);
        prikaziIzvodjacaB.setBackground(zelena);
        prikaziMuzickoDeloB.setBackground(zelena);

        String[] nizIzvodjaca = getNizIzvodjaca(iz.getListaIzvodjaca());
        comboBoxIzvodjaca = new JComboBox<String>(nizIzvodjaca);
        if(nizIzvodjaca.length == 0) {
            comboBoxIzvodjaca.setEnabled(false);
            prikaziIzvodjacaB.setEnabled(false);
        }

        String[] nizMuzickihDela = getNizMuzickihDela(iz.getListaMuzickihDela());
        comboBoxMuzickihDela = new JComboBox<String>(nizMuzickihDela);
        if(nizMuzickihDela.length == 0) {
            comboBoxMuzickihDela.setEnabled(false);
            prikaziMuzickoDeloB.setEnabled(false);
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
        panelGlavni.add(preuzmiB, con);

        //Ukoliko je PlejlistaKON.getKorisnik()!=null dodaj button dodajB
        con.gridx=4;
        panelGlavni.add(dodajB, con);

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
        panelGlavni.add(comboBoxIzvodjaca, con);

        con.gridx = 2;
        con.anchor = GridBagConstraints.LINE_START;
        con.insets = new Insets(5, 3, 5, 20);
        panelGlavni.add(prikaziIzvodjacaB, con);

        con.gridy = 6;
        con.gridx = 0;
        con.anchor = GridBagConstraints.LINE_END;
        con.insets = new Insets(5, 20, 5, 2);

        panelGlavni.add(comboBoxMuzickihDela, con);

        con.gridx = 2;
        con.anchor = GridBagConstraints.LINE_START;
        con.fill = GridBagConstraints.FIRST_LINE_START;
        con.insets = new Insets(5, 3, 5, 20);
        panelGlavni.add(prikaziMuzickoDeloB, con);

        con.gridy = 7;
        con.gridx = 0;
        con.fill = GridBagConstraints.BOTH;
        con.insets = new Insets(5, 25, 5, 25);
        con.anchor = GridBagConstraints.CENTER;
        con.gridwidth = 4;
        panelGlavni.add(skrol, con);

        con.gridy = 9;
        con.gridx = 3;
        con.fill = GridBagConstraints.NONE;
        con.insets = new Insets(5, 0, 10, 10);
        con.anchor = GridBagConstraints.LINE_END;
        con.gridwidth = 1;
        panelGlavni.add(dodajRecenzijuB, con);
    }

    private void podesiAkcije(Izvodjenje iz, GlavniProzor gp) {

        PrikazIzvodjenja.this.addWindowListener(new java.awt.event.WindowAdapter() {
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
                    ucitajRecenzije(iz, gp);
                }
            }
        });

        prikaziIzvodjacaB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxIzvodjaca.getSelectedIndex();

                PrikazIzvodjaca pi = new PrikazIzvodjaca(iz.getListaIzvodjaca().get(index), gp);
                PrikazIzvodjenja.this.dispose();
                pi.setVisible(true);
                player.stop();
            }
        });

        prikaziMuzickoDeloB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxMuzickihDela.getSelectedIndex();

                PrikazMuzickogDela pi = new PrikazMuzickogDela(iz.getListaMuzickihDela().get(index), gp);
                PrikazIzvodjenja.this.dispose();
                pi.setVisible(true);
                player.stop();
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

        preuzmiB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int response = chooser.showOpenDialog(null);
                if(response == JFileChooser.APPROVE_OPTION)
                {
                    File destionationFile = new File(chooser.getSelectedFile().getAbsolutePath() + sep + "audio.mp3");

                    if(file == null)
                        file = IzvodjenjeDAO.getAudioIzvodjenja(idIzvodjenja, sep);
                    InputStream is = null;
                    OutputStream os = null;
                    try {
                        is = new FileInputStream(file);
                        os = new FileOutputStream(destionationFile);
                        byte[] buffer = new byte[1024];
                        int length;
                        while((length = is.read(buffer)) > 0)
                            os.write(buffer, 0, length);

                        is.close();
                        os.close();
                    } catch (IOException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
        });

        dodajRecenzijuB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogRecenzije dr = new DialogRecenzije(PrikazIzvodjenja.this, null);
                dr.setVisible(true);
            }
        });
    }

    public void ucitajRecenzije(Izvodjenje iz, GlavniProzor gp) {

        resetRecenzije(gp);

        List<Recenzija> recenzije = RecenzijaDAO.getRecenzijeIzvodjenja(iz.getId(), brojRecenzija);

        for (Recenzija r : recenzije)
            panelSkrola.add(new ElementRecenzija(r));

        gp.osveziKomponentu(skrol);
    }

    private void resetRecenzije(GlavniProzor gp) {
        panelSkrola.removeAll();
        gp.osveziKomponentu(panelSkrola);
    }

    public static String[] getNizIzvodjaca(List<Izvodjac> listaIzvodjaca) {
        String[] itemsArray = new String[listaIzvodjaca.size()];
        int index = 0;
        for (Izvodjac izvodjac : listaIzvodjaca) {
            itemsArray[index] = izvodjac.getNazivIzvodjaca();
            index++;
        }
        return itemsArray;
    }

    public static String[] getNizMuzickihDela(List<MuzickoDelo> listaMuzickihDela) {
        String[] itemsArray = new String[listaMuzickihDela.size()];
        int index = 0;
        for (MuzickoDelo muzickoDelo : listaMuzickihDela) {
            itemsArray[index] = muzickoDelo.getNazivDela();
            index++;
        }
        return itemsArray;
    }
}