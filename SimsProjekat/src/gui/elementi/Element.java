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

        StringBuilder izvodjaci = new StringBuilder();
        for (Izvodjac izv: iz.getListaIzvodjaca()) {
            izvodjaci.append(izv.getNazivIzvodjaca()).append(",");
        }
        String line = izvodjaci.toString();
        if (!line.equals("")){
            line = line.substring(0, line.length() - 1);
        }
        String line2 = "Broj pristupa : "+iz.getBrPristupa();
        String line4 = "Trajanje : "+iz.getTrajanje();
        String line3 = "Mesto izvodjenja : "+iz.getMestoIzvodjenja().getNazivMesta();
        String line5 = "Vreme izvodjenja : "+iz.getVremeIzvodjenja();
        String line6;
        if (iz.getListaRecenzija() == null){
            line6 = "Broj recenzija : 0";
        } else {
            line6 = "Broj recenzija : "+iz.getListaRecenzija().size();
        }
        labelaOpisa.setText("<html>"+line+"<br/>"+line2+"<br/>"+line3+"<br/>"+line4+"<br/>"+line5+"<br/>"+line6+"</html>");

    }

    private void podesiPanelGlavni() {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();
        int width = dimension.width/4*3;
        int height = dimension.height/4*3;
        panelGlavni.setPreferredSize(new Dimension(width/3*2, height/5));
    }
}
