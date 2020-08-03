package gui.elementi;

import model.Izvodjenje;
import model.Zanr;

import javax.swing.*;
import java.awt.*;

public class Element extends JPanel{
    private JPanel panelGlavni;
    private JLabel labelaNaslova;
    private JLabel labelaOpisa;
    private JLabel labelaIkone;
    private Toolkit tool;
    private Dimension dimension;

    public Element(Zanr z) {
        super();
        tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();
        int width = dimension.width/4*3;
        int height = dimension.height/4*3;
        panelGlavni.setPreferredSize(new Dimension(width/3*2, height/5));
        add(panelGlavni);
        /*
        labelaNaslova.setText(i.getListaMuzickihDela().get(0).getNazivDela());

        labelaOpisa.setText("");
        for (Izvodjac izv: i.getListaIzvodjaca()) {
            labelaOpisa.setText(labelaOpisa.getText()+","+izv.getNazivIzvodjaca());
        }
        labelaOpisa.setText("Izvodjaci : "+ labelaOpisa.getText());

         */
        labelaNaslova.setText(z.getNazivZanra());
        labelaOpisa.setText(z.getOpis());


    }
}
