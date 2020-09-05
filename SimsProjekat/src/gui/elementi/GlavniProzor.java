package gui.elementi;

import dao.ReklamaDAO;
import gui.dialogs.DialogPrijave;
import gui.dialogs.DialogRegistracije;
import kontroler.GlavniProzorKON;
import kontroler.ToplistaKON;
import model.Izvodjenje;
import model.Reklama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class GlavniProzor extends JFrame implements ActionListener {
    protected JPanel panelOperacija;
    public JPanel panelReklama;
    protected JPanel panelAkcija;
    private JScrollPane skrol;
    private JPanel panelGlavni;
    private JButton popularnoButton;
    private JTextField pretraziF;
    private JButton pretraziButton;
    protected JButton prijavaButton;
    protected JButton registracijaButton;
    private JPanel panelOdSkrola;
    private JButton pocetnaStranicaButton;
    protected JButton odjavaButton;
    private JButton izaberiFilterButton;
    private JButton toplisteButton;
    private boolean popularnoTrenutno;
    private boolean pretraziTrenutno;
    private JLabel nothingFoundL;
    private Dimension dimension;
    protected String separator;

    private int brojElemenataIzvodjenja;
    private boolean naknadnoUcitajReklamu;
    private int maxIdReklame;
    protected String filter;
    protected String filterZanra;

    public GlavniProzor(){
        super("Muzicki sistem");

        inicijalizuj();

        setSize(dimension.width / 4 * 3, dimension.height / 4 * 3);

        ucitajReklamu();
        ucitajIzvodjenjaZaPocetnuStranu();
        podesiAkcije();

        add(panelGlavni);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void inicijalizuj(){
        brojElemenataIzvodjenja = 5;
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        separator = System.getProperty("file.separator");
        nothingFoundL = new JLabel(new ImageIcon(
                "SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "nothingFound.png"));

        skrol.getVerticalScrollBar().setUnitIncrement(16);
        panelOdSkrola.setLayout(new BoxLayout(panelOdSkrola, BoxLayout.Y_AXIS));
        panelReklama.setLayout(new BoxLayout(panelReklama, BoxLayout.Y_AXIS));
        panelAkcija.setLayout(new BoxLayout(panelAkcija, BoxLayout.Y_AXIS));

        filter = "vremeIzvodjenja desc";
        filterZanra = "svi";
    }

    private void podesiAkcije() {
        prijavaButton.addActionListener(this);
        odjavaButton.addActionListener(this);
        registracijaButton.addActionListener(this);
        pocetnaStranicaButton.addActionListener(this);
        popularnoButton.addActionListener(this);
        pretraziButton.addActionListener(this);
        toplisteButton.addActionListener(this);

        skrol.getVerticalScrollBar().addAdjustmentListener(e -> {
            JViewport vp = skrol.getViewport();
            if (vp.getView().getHeight() <= vp.getHeight() + vp.getViewPosition().y) {
                brojElemenataIzvodjenja += 1;
                ucitajIzvodjenjaZaPocetnuStranu();
            }
        });

        izaberiFilterButton.addActionListener(e -> {
            PrikazFiltera pf = new PrikazFiltera(GlavniProzor.this);
            pf.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();

        if (button == prijavaButton) {
            DialogPrijave dp = new DialogPrijave(GlavniProzor.this);
            dp.setVisible(true);
        } else if (button == odjavaButton) {
            Icon icon = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "logOut.png");
            int retVal = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zeleite da se odjavite ?", "Odjava", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
            if (retVal == 0) {
                dispose();
                GlavniProzor gp = null;
                gp = new GlavniProzor();
                gp.setVisible(true);
            }
        } else if (button == registracijaButton) {
            DialogRegistracije dr = new DialogRegistracije(GlavniProzor.this);
            dr.setVisible(true);
        } else if (button == pretraziButton) {
            if (pretraziF.getText().equals("")) return;

            podigniSkrol();
            podesiParametreZaPocetnuStranu(popularnoTrenutno, true);
            ucitajIzvodjenjaZaPocetnuStranu();
        } else if (button == pocetnaStranicaButton) {
            pocetnaStranicaButton.setBackground(new Color(188, 204, 111));
            popularnoButton.setBackground(new Color(153, 179, 185));
            podigniSkrol();
            podesiParametreZaPocetnuStranu(false, false);
            ucitajIzvodjenjaZaPocetnuStranu();
        } else if (button == popularnoButton) {
            popularnoButton.setBackground(new Color(188, 204, 111));
            pocetnaStranicaButton.setBackground(new Color(153, 179, 185));
            podigniSkrol();
            podesiParametreZaPocetnuStranu(true, false);
            ucitajIzvodjenjaZaPocetnuStranu();
        }else if(button == toplisteButton){
            PrikazTopliste dpt = new PrikazTopliste();
            dpt.setVisible(true);
        }
    }

    private void podesiParametreZaPocetnuStranu(boolean popularno, boolean pretrazi) {
        brojElemenataIzvodjenja = 5;
        popularnoTrenutno = popularno;
        pretraziTrenutno = pretrazi;
    }

    private void ucitajIzvodjenjaZaPocetnuStranu() {
        resetujElemente();

        String parametar;
        if (popularnoTrenutno) parametar = "brojPristupa";
        else parametar = "vremeIzvodjenja";

        List<Izvodjenje> izvodjenja = null;

        try {
            if (pretraziTrenutno)
                izvodjenja = GlavniProzorKON.pretrazi(pretraziF.getText(), brojElemenataIzvodjenja, filter, filterZanra);
            else
                izvodjenja = GlavniProzorKON.dobaviIzvodjenja(parametar, brojElemenataIzvodjenja);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (izvodjenja != null && izvodjenja.size() == 0) panelOdSkrola.add(nothingFoundL);

        if (izvodjenja != null) {
            for (Izvodjenje iz : izvodjenja)
                panelOdSkrola.add(new ElementIzvodjenja(iz, this));
        }

        osveziKomponentu(panelOdSkrola);
        osveziKomponentu(skrol);
    }

    private void resetujElemente() {
        panelOdSkrola.removeAll();
        osveziKomponentu(panelOdSkrola);
    }

    private void ucitajReklamu() {

        java.sql.Date danasnjiDatum = new java.sql.Date(System.currentTimeMillis());

        for (int i = 0; i < 3; i++) {
            if (naknadnoUcitajReklamu && i == 1) break;
            Reklama reklama = ReklamaDAO.getReklamaZaReklamniBafer(maxIdReklame, danasnjiDatum);
            if (reklama != null) {
                maxIdReklame = reklama.getId();
                panelReklama.add(new ElementReklame(reklama, this));
            }
        }

        naknadnoUcitajReklamu = true;
        osveziKomponentu(panelReklama);
    }

    public void obrisiReklamu(ElementReklame er) {
        panelReklama.remove(er);
        osveziKomponentu(panelReklama);
        ucitajReklamu();
    }

    public void osveziKomponentu(Component component) {
        component.validate();
        component.repaint();
    }

    private void podigniSkrol() {
        JViewport vp = skrol.getViewport();
        vp.setViewPosition(new Point(0, 0));
    }
}
