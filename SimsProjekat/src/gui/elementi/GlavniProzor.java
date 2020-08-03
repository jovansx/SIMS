package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.ZanrDAO;
import gui.dialogs.DialogPrijave;
import gui.dialogs.DialogRegistracije;
import model.Izvodjenje;
import model.Zanr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GlavniProzor extends JFrame{
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
    private Toolkit tool;
    private Dimension dimension;
    int width;

    public GlavniProzor(){
        super("Muzicki sistem");
        tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();
        width = dimension.width/4*3;
        int height = dimension.height/4*3;
        this.setSize(width, height);
        panelOdSkrola.setLayout(new BoxLayout(panelOdSkrola, BoxLayout.Y_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panelGlavni);
        setResizable(false);
        setLocationRelativeTo(null);
        elementi = new ArrayList<Element>();
        ucitajPocetnuStranu();
        skrol.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JViewport vp = skrol.getViewport();
                if(vp.getView().getHeight() <= vp.getHeight() + vp.getViewPosition().y){
                    System.out.println(vp.getViewPosition().y);
                }
            }
        });

        prijavaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogPrijave dp = new DialogPrijave(GlavniProzor.this);
                dp.setVisible(true);
            }
        });
        registracijaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogRegistracije dr = new DialogRegistracije(GlavniProzor.this);
                dr.setVisible(true);
            }
        });

        pocetnaStranicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ucitajPocetnuStranu();
            }
        });


        popularnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetElemente();
            }
        });
    }

    public void ucitajPocetnuStranu(){
        List<Zanr> zanrovi = ZanrDAO.getZanrove();

        for (Zanr z : zanrovi) {
            Element el = new Element(z);
            elementi.add(el);
            panelOdSkrola.add(el);
        }

        panelOdSkrola.validate();
        panelOdSkrola.repaint();
        //panelOdSkrola.setPreferredSize(new Dimension(width/3*2, panelOdSkrola.getComponentCount()));
        skrol.validate();
        skrol.repaint();
    }

    /*public void dodajElement(){
        Element el = new Element();
        elementi.add(el);

        panelOdSkrola.add(el);
        panelOdSkrola.validate();
        panelOdSkrola.repaint();
        System.out.println("dodajem");
    }

     */
    public void resetElemente(){
        elementi.clear();

        panelOdSkrola.removeAll();
        panelOdSkrola.validate();
        panelOdSkrola.repaint();
        System.out.println("brisem");
    }
}
