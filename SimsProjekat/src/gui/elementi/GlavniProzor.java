package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.ZanrDAO;
import gui.dialogs.DialogPrijave;
import gui.dialogs.DialogRegistracije;
import model.Izvodjenje;
import model.Zanr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;

public class
GlavniProzor extends JFrame implements ActionListener{
    private JPanel panelOperacija;
    private JPanel panelReklama;
    protected JPanel panelAkcija;
    private JScrollPane skrol;
    private JPanel panelGlavni;
    private JButton popularnoButton;
    private JTextField textField1;
    private JButton pretraziButton;
    private JButton prijavaButton;
    private JButton registracijaButton;
    private JPanel panelOdSkrola;
    private JButton pocetnaStranicaButton;
    private boolean pocetnaTrenutno;

    private List<Element> elementi;
    private int brojElemenata;

    public GlavniProzor() {
        super("Muzicki sistem");

        //Kad podesimo u GlavniProzor.from boju ne mora ovo ovde
        pocetnaStranicaButton.setBackground(Color.GREEN);
        pocetnaTrenutno = true; //Ako je false onda je popularno pritisnutno
        brojElemenata = 5;
        elementi = new ArrayList<Element>();
        skrol.getVerticalScrollBar().setUnitIncrement(16);      //brzina skrola

        podesiPanelSkrola();
        ucitajPocetnuStranu();
        podesiAkcije();
        add(panelGlavni);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void podesiPanelSkrola() {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();
        int width = dimension.width / 4 * 3;
        int height = dimension.height / 4 * 3;
        setSize(width, height);
        panelOdSkrola.setLayout(new BoxLayout(panelOdSkrola, BoxLayout.Y_AXIS));
    }

    private void podesiAkcije() {
        prijavaButton.addActionListener(this);
        registracijaButton.addActionListener(this);
        pocetnaStranicaButton.addActionListener(this);
        popularnoButton.addActionListener(this);

        skrol.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JViewport vp = skrol.getViewport();
                if (vp.getView().getHeight() <= vp.getHeight() + vp.getViewPosition().y) {
                    brojElemenata+=1;
                    ucitajPocetnuStranu();
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
        }
        else if (button == registracijaButton) {
            DialogRegistracije dr = new DialogRegistracije(GlavniProzor.this);
            dr.setVisible(true);
            }
        else if (button == pocetnaStranicaButton) {
            JViewport vp = skrol.getViewport();
            vp.setViewPosition(new Point(0, 0));
            brojElemenata = 5;
            pocetnaTrenutno = true;
            pocetnaStranicaButton.setBackground(Color.GREEN);
            popularnoButton.setBackground(new Color(81,110,114));
            ucitajPocetnuStranu();
        }
        else if (button == popularnoButton) {
            JViewport vp = skrol.getViewport();
            vp.setViewPosition(new Point(0, 0));
            brojElemenata = 5;
            pocetnaTrenutno = false;
            popularnoButton.setBackground(Color.GREEN);
            pocetnaStranicaButton.setBackground(new Color(81,110,114));
            ucitajPocetnuStranu();
        }
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
            Element el = new Element(iz);
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
