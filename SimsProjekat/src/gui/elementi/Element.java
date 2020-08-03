package gui.elementi;

import dao.IzvodjenjeDAO;
import model.Izvodjac;
import model.Izvodjenje;
import model.MuzickoDelo;
import model.Zanr;
import util.FConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Element extends JPanel{
    private JPanel panelGlavni;
    private JLabel labelaNaslova;
    private JLabel labelaOpisa;
    private JLabel labelaIkone;
    private String separator;
    private Toolkit tool;
    private Dimension dimension;

    public Element(Izvodjenje iz) {
        super();

        tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();
        separator = System.getProperty("file.separator");
        panelGlavni.setPreferredSize(
                new Dimension(dimension.width/4*3,dimension.height/20*3));
        labelaIkone.setSize(200, dimension.height/20*3 - 10);

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

        ImageIcon retImageIcon = IzvodjenjeDAO.getSlikuIzvodjenja(iz, separator);
        Image im = retImageIcon.getImage();
        Image myImg = im.getScaledInstance(labelaIkone.getWidth(), labelaIkone.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon newImage = new ImageIcon(myImg);
        labelaIkone.setIcon(newImage);
        iz.setImage(newImage);
    }
}
