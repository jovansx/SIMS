package gui.elementi;

import model.Izvodjac;
import model.Izvodjenje;
import model.MuzickoDelo;
import model.Zanr;

import javax.swing.*;
import java.awt.*;

public class Element extends JPanel{
    private JPanel panelGlavni;
    private JLabel labelaNaslova;
    private JLabel labelaOpisa;
    private JLabel labelaIkone;

    public Element(Izvodjenje iz) {
        super();

        podesiPanelGlavni();

        add(panelGlavni);

        StringBuilder name = new StringBuilder();
        for (MuzickoDelo mz : iz.getListaMuzickihDela()) {
            name.append(mz.getNazivDela()).append(",");
        }
        name = new StringBuilder(name.substring(0, name.length() - 1));

        labelaNaslova.setText(name.toString());

        /*String izvodjaci = "";
        for (Izvodjac izv: iz.getListaIzvodjaca()) {
            izvodjaci = izvodjaci+","+izv.getNazivIzvodjaca();
        }
        izvodjaci = izvodjaci.substring(1,izvodjaci.length()-1);
        labelaOpisa.setText("Izvodjaci : "+ izvodjaci);*/

    }

    private void podesiPanelGlavni() {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();
        int width = dimension.width/4*3;
        int height = dimension.height/4*3;
        panelGlavni.setPreferredSize(new Dimension(width/3*2, height/5));
    }
}
