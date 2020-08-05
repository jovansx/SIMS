package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.ZanrDAO;
import gui.dialogs.DialogPrijave;
import gui.dialogs.DialogRegistracije;
import kontroler.GlavniProzorKON;
import model.Izvodjenje;
import model.Zanr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class
GlavniProzor extends JFrame implements ActionListener{
    protected JPanel panelOperacija;
    private JPanel panelReklama;
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
    private int brojElemenata;

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
        pocetnaTrenutno = true; //Ako je false onda je popularno pritisnutno
        pretraziTrenutno = false;
        brojElemenata = 5;
        elementi = new ArrayList<Element>();
        skrol.getVerticalScrollBar().setUnitIncrement(16);      //brzina skrola

        setSize(dimension.width / 4 * 3, dimension.height / 4 * 3);
        panelOdSkrola.setLayout(new BoxLayout(panelOdSkrola, BoxLayout.Y_AXIS));

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
}
