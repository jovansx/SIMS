package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.ReklamaDAO;
import gui.dialogs.DialogPrijave;
import gui.dialogs.DialogRegistracije;
import kontroler.GlavniProzorKON;
import model.Izvodjenje;
import model.Reklama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.SQLException;
import java.util.List;

public class
GlavniProzor extends JFrame implements ActionListener{
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
    private boolean pocetnaTrenutno;
    private boolean pretraziTrenutno;
    private JLabel nothingFoundL;
    private Dimension dimension;
    protected String separator;

    private int brojElemenata;
    private boolean prviPutReklame;
    private int maxIdReklame;

    public GlavniProzor() {
        super("Muzicki sistem");

        inicijalizuj();

        setSize(dimension.width / 4 * 3, dimension.height / 4 * 3);

        ucitajReklamu();
        //ucitajPocetnuStranu();
        ucitajIzvodjenjaZaPocetnuStranu();
        podesiAkcije();

        add(panelGlavni);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void inicijalizuj() {

        pocetnaTrenutno = true;
        brojElemenata = 5;
        prviPutReklame = true;
        maxIdReklame = -1;

        Toolkit tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();
        separator = System.getProperty("file.separator");

        Icon icon = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "nothingFound.png");
        nothingFoundL = new JLabel(icon);
        nothingFoundL.setPreferredSize(new Dimension(dimension.width / 8 * 3, dimension.height / 8 * 3));

        skrol.getVerticalScrollBar().setUnitIncrement(16);

        panelOdSkrola.setLayout(new BoxLayout(panelOdSkrola, BoxLayout.Y_AXIS));
        panelReklama.setLayout(new BoxLayout(panelReklama, BoxLayout.Y_AXIS));


    }

    private void podesiAkcije() {
        prijavaButton.addActionListener(this);
        odjavaButton.addActionListener(this);
        registracijaButton.addActionListener(this);
        pocetnaStranicaButton.addActionListener(this);
        popularnoButton.addActionListener(this);
        pretraziButton.addActionListener(this);

        skrol.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JViewport vp = skrol.getViewport();
                if (vp.getView().getHeight() <= vp.getHeight() + vp.getViewPosition().y) {
                    brojElemenata+=1;
                    if(!pretraziTrenutno) {
                        //ucitajPocetnuStranu();
                        ucitajIzvodjenjaZaPocetnuStranu();
                    }
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton)e.getSource();

        if (button == prijavaButton) {
            DialogPrijave dp = new DialogPrijave(GlavniProzor.this);
            dp.setVisible(true);
        } else if(button == odjavaButton) {
            Icon icon = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "logOut.png");
            int retVal = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zeleite da se odjavite ?", "Odjava", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
            if(retVal == 0) {
                dispose();
                GlavniProzor gp = new GlavniProzor();
                gp.setVisible(true);
            }
        }
        else if (button == registracijaButton) {
            DialogRegistracije dr = new DialogRegistracije(GlavniProzor.this);
            dr.setVisible(true);
            }
        else if (button == pretraziButton) {
            if(pretraziF.getText().equals("")) return;

            podigniSkrol();

            brojElemenata = 5;
            pretraziTrenutno = true;

            //ucitajPocetnuStranu();
            ucitajIzvodjenjaZaPocetnuStranu();
        }
        else if (button == pocetnaStranicaButton) {
            podigniSkrol();
            brojElemenata = 5;
            pocetnaTrenutno = true;
            pretraziTrenutno = false;
            pocetnaStranicaButton.setBackground(Color.GREEN);
            popularnoButton.setBackground(new Color(81,110,114));
            //ucitajPocetnuStranu();
            ucitajIzvodjenjaZaPocetnuStranu();
        }
        else if (button == popularnoButton) {
            podigniSkrol();
            brojElemenata = 5;
            pocetnaTrenutno = false;
            pretraziTrenutno = false;
            popularnoButton.setBackground(Color.GREEN);
            pocetnaStranicaButton.setBackground(new Color(81,110,114));
            //ucitajPocetnuStranu();
            ucitajIzvodjenjaZaPocetnuStranu();
        }
    }

    private void podigniSkrol() {
        JViewport vp = skrol.getViewport();
        vp.setViewPosition(new Point(0, 0));
    }

    public void ucitajIzvodjenjaZaPocetnuStranu() {
        resetElemente();

        String parametar;

        if(pocetnaTrenutno) parametar = "vremeIzvodjenja";
        else parametar = "brojPristupa";

        List<Izvodjenje> izvodjenja = null;

        try {
            if (pretraziTrenutno)
                izvodjenja = GlavniProzorKON.pretrazi(pretraziF.getText(), brojElemenata);
            else{
                izvodjenja = GlavniProzorKON.dobaviIzvodjenja(parametar, brojElemenata);
                System.out.println(izvodjenja);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        if(izvodjenja.size() == 0) panelOdSkrola.add(nothingFoundL);

        for (Izvodjenje iz : izvodjenja)
            panelOdSkrola.add(new ElementIzvodjenja(iz, this));

        refreshComponent(panelOdSkrola);
        refreshComponent(skrol);
    }
    /*
    public void ucitajPretrazi() throws SQLException {
        resetElemente();

        List<Izvodjenje> izvodjenja = GlavniProzorKON.pretrazi(pretraziF.getText(), brojElemenata);

        if(izvodjenja.size() == 0) panelOdSkrola.add(nothingFoundL);


        for (Izvodjenje iz : izvodjenja)
            panelOdSkrola.add(new ElementIzvodjenja(iz, this));

        refreshComponent(panelOdSkrola);
        refreshComponent(skrol);
    }

    public void ucitajPocetnuStranu() throws SQLException {
        resetElemente();

        String parametar;

        if(pocetnaTrenutno) parametar = "vremeIzvodjenja";
        else parametar = "brojPristupa";

        List<Izvodjenje> izvodjenja = GlavniProzorKON.dobaviIzvodjenja(parametar, brojElemenata);

        for (Izvodjenje iz : izvodjenja)
            panelOdSkrola.add(new ElementIzvodjenja(iz, this));


        refreshComponent(panelOdSkrola);
        refreshComponent(skrol);
    }

     */

    private void resetElemente() {
        panelOdSkrola.removeAll();
        refreshComponent(panelOdSkrola);
    }

    public void ucitajReklamu() {

        java.sql.Date danasnjiDatum = new java.sql.Date(System.currentTimeMillis());

        for (int i = 0; i < 3; i++) {
            if (!prviPutReklame && i==1) break;
            Reklama reklama = ReklamaDAO.getReklamaZaReklamniBafer(maxIdReklame, danasnjiDatum);
            if(reklama != null) {
                maxIdReklame = reklama.getId();
                ElementReklame elementReklame = new ElementReklame(reklama, this);
                panelReklama.add(elementReklame);
            }
        }

        prviPutReklame = false;

        refreshComponent(panelReklama);
    }

    public void obrisiReklamu(Reklama r, ElementReklame er) {
        panelReklama.remove(er);
        refreshComponent(panelReklama);
        ucitajReklamu();
    }

    public void refreshComponent(Component component) {
        component.validate();
        component.repaint();
    }


}
