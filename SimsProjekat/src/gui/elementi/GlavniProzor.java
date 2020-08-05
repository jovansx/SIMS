package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.ReklamaDAO;
import dao.ZanrDAO;
import gui.dialogs.DialogPrijave;
import gui.dialogs.DialogRegistracije;
import kontroler.GlavniProzorKON;
import model.Izvodjenje;
import model.Reklama;
import model.Zanr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    private Toolkit tool;
    private Dimension dimension;
    protected String separator;

    private List<Element> elementi;
    private List<ElementReklame> reklame;
    private int brojElemenata;
    private boolean prviPutReklame;
    private int maxIdReklame;

    public GlavniProzor() {
        super("Muzicki sistem");

        tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();
        separator = System.getProperty("file.separator");

        Icon icon = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "nothingFound.png");
        nothingFoundL = new JLabel(icon);
        nothingFoundL.setPreferredSize(new Dimension(dimension.width / 8 * 3, dimension.height / 8 * 3));

        //Kad podesimo u GlavniProzor.from boju ne mora ovo ovde
        pocetnaStranicaButton.setBackground(Color.GREEN);
        skrol.getVerticalScrollBar().setUnitIncrement(16);      //brzina skrola
        pocetnaTrenutno = true; //Ako je false onda je popularno pritisnutno
        pretraziTrenutno = false;
        brojElemenata = 5;
        prviPutReklame = true;
        maxIdReklame = -1;
        elementi = new ArrayList<Element>();
        reklame = new ArrayList<ElementReklame>();

        setSize(dimension.width / 4 * 3, dimension.height / 4 * 3);
        panelOdSkrola.setLayout(new BoxLayout(panelOdSkrola, BoxLayout.Y_AXIS));
        panelReklama.setLayout(new BoxLayout(panelReklama, BoxLayout.Y_AXIS));
        /*Reklama r = new Reklama();
        reklame.add(new ElementReklame(r, this));
        reklame.add(new ElementReklame(r, this));
        reklame.add(new ElementReklame(r, this));
        for(ElementReklame el: reklame) {
            panelReklama.add(el);
        }
        panelReklama.validate();
        panelReklama.repaint();*/
        ucitajReklamu();
        ucitajPocetnuStranu();
        podesiAkcije();

        add(panelGlavni);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
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
                        ucitajPocetnuStranu();
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
        }else if(button == odjavaButton) {
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
        else if (button == pretraziButton){
            if(pretraziF.getText().equals("")) {
                return;
            }
            List<Izvodjenje> izvodjenja = null;
            JViewport vp = skrol.getViewport();
            vp.setViewPosition(new Point(0, 0));
            brojElemenata = 5;
            pretraziTrenutno = true;
            try {
                izvodjenja = GlavniProzorKON.pretrazi(pretraziF.getText(), brojElemenata);
            }catch(SQLException ex) {
                System.out.println("Nema rezultata !");
            }
            ucitajPretrazi(izvodjenja);
            if(izvodjenja.size() == 0) {
                panelOdSkrola.add(nothingFoundL);
            }

        }
        else if (button == pocetnaStranicaButton) {
            JViewport vp = skrol.getViewport();
            vp.setViewPosition(new Point(0, 0));
            brojElemenata = 5;
            pocetnaTrenutno = true;
            pretraziTrenutno = false;
            pocetnaStranicaButton.setBackground(Color.GREEN);
            popularnoButton.setBackground(new Color(81,110,114));
            ucitajPocetnuStranu();
        }
        else if (button == popularnoButton) {
            JViewport vp = skrol.getViewport();
            vp.setViewPosition(new Point(0, 0));
            brojElemenata = 5;
            pocetnaTrenutno = false;
            pretraziTrenutno = false;
            popularnoButton.setBackground(Color.GREEN);
            pocetnaStranicaButton.setBackground(new Color(81,110,114));
            ucitajPocetnuStranu();
        }
    }

    public void ucitajPretrazi(List<Izvodjenje> izvodjenja) {
        resetElemente();

        for (Izvodjenje iz : izvodjenja) {
            Element el = new Element(iz, this);
            elementi.add(el);
            panelOdSkrola.add(el);
        }

        panelOdSkrola.validate();
        panelOdSkrola.repaint();

        skrol.validate();
        skrol.repaint();
    }

    public void ucitajPocetnuStranu() {
        resetElemente();

        List<Izvodjenje> izvodjenja;
        if(pocetnaTrenutno) {
            izvodjenja = IzvodjenjeDAO.getIzvodjenjaZaPocetnuStranicu(brojElemenata, "vremeIzvodjenja");
        }else {
            izvodjenja = IzvodjenjeDAO.getIzvodjenjaZaPocetnuStranicu(brojElemenata, "brojPristupa");
        }

        for (Izvodjenje iz : izvodjenja) {
            Element el = new Element(iz, this);
            elementi.add(el);
            panelOdSkrola.add(el);
        }

        panelOdSkrola.validate();
        panelOdSkrola.repaint();

        skrol.validate();
        skrol.repaint();
    }

    private void resetElemente() {
        elementi.clear();
        panelOdSkrola.removeAll();
        panelOdSkrola.validate();
        panelOdSkrola.repaint();
    }

    public void ucitajReklamu() {

        java.sql.Date danasnjiDatum = new java.sql.Date(System.currentTimeMillis());

        if(prviPutReklame) {
            for (int i = 0; i < 3; i++) {
                Reklama reklama = ReklamaDAO.getReklamaZaReklamniBafer(maxIdReklame, danasnjiDatum);
                if(reklama != null) {
                    maxIdReklame = reklama.getId();
                    ElementReklame elementReklame = new ElementReklame(reklama, this);
                    reklame.add(elementReklame);
                    panelReklama.add(elementReklame);
                }
            }
            prviPutReklame = false;
        }else {
            Reklama reklama = ReklamaDAO.getReklamaZaReklamniBafer(maxIdReklame, danasnjiDatum);
            if(reklama != null) {
                maxIdReklame = reklama.getId();
                ElementReklame elementReklame = new ElementReklame(reklama, this);
                reklame.add(elementReklame);
                panelReklama.add(elementReklame);
            }
        }

        panelReklama.validate();
        panelReklama.repaint();

    }

    public void obrisiReklamu(Reklama r, ElementReklame er) {
        reklame.remove(er);
        panelReklama.remove(er);
        panelReklama.validate();
        panelReklama.repaint();
        ucitajReklamu();
    }


}
