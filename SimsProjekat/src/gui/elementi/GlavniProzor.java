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

public class GlavniProzor extends JFrame implements ActionListener{
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
    private List<Element> elementi;

    public GlavniProzor() {
        super("Muzicki sistem");

        podesiPanelaSkrola();

        add(panelGlavni);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        elementi = new ArrayList<Element>();

        ucitajPocetnuStranu();

        podesiAkcije();
    }

    private void podesiPanelaSkrola() {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();
        int width = dimension.width / 4 * 3;
        int height = dimension.height / 4 * 3;
        this.setSize(width, height);
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
                    System.out.println("end");
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
            ucitajPocetnuStranu();
        }
        else if (button == popularnoButton) {
            ucitajPopularanSadrzaj();
        }
    }

    private void ucitajPopularanSadrzaj() {

    }

    public void ucitajPocetnuStranu() {
        resetElemente();

        List<Izvodjenje> izvodjenja = IzvodjenjeDAO.getIzvodjenja();

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