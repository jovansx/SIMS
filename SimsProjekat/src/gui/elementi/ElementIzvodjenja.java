package gui.elementi;

import dao.IzvodjenjeDAO;
import model.Izvodjac;
import model.Izvodjenje;
import model.MuzickoDelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ElementIzvodjenja extends JPanel {
    private JPanel panelGlavni;
    private JLabel labelaNaslova;
    private JLabel labelaOpisa;
    private JLabel labelaIkone;

    public ElementIzvodjenja(Izvodjenje iz, GlavniProzor gp) {
        super();

        inicijalizuj(iz);

        add(panelGlavni);

        podesiAkcije(iz, gp);
    }

    private void inicijalizuj(Izvodjenje iz) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();
        String separator = System.getProperty("file.separator");

        panelGlavni.setPreferredSize(new Dimension(dimension.width / 4 * 3, dimension.height / 20 * 3));
        labelaIkone.setSize(200, dimension.height / 20 * 3 - 10);

        labelaNaslova.setText(generisiNazivIzvodjaca(iz));
        labelaOpisa.setText(generisiOpisElementa(iz));

        ImageIcon retImageIcon = IzvodjenjeDAO.getSlikuIzvodjenja(iz, separator);
        Image im = retImageIcon.getImage();
        Image myImg = im.getScaledInstance(labelaIkone.getWidth(), labelaIkone.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon newImage = new ImageIcon(myImg);
        labelaIkone.setIcon(newImage);
        iz.setImage(newImage);
    }

    private void podesiAkcije(Izvodjenje iz, GlavniProzor gp) {

        panelGlavni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                dodajPristup(iz);

                PrikazIzvodjenja pi = new PrikazIzvodjenja(iz, gp);
                pi.setVisible(true);
            }
        });

    }

    private void dodajPristup(Izvodjenje iz) {
        iz.setBrPristupa(iz.getBrPristupa() + 1);
        labelaOpisa.setText(generisiOpisElementa(iz));

        try {
            IzvodjenjeDAO.updatePristup(iz);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private String generisiOpisElementa(Izvodjenje iz) {
        String line2 = "Broj pristupa : " + iz.getBrPristupa();
        String line4 = "Trajanje : " + iz.getTrajanje();
        String line3 = "Mesto izvodjenja : " + iz.getMestoIzvodjenja().getNazivMesta();
        String line5 = "Vreme izvodjenja : " + iz.getVremeIzvodjenja();
        String line6;
        if (iz.getListaRecenzija() == null) {
            line6 = "Broj recenzija : 0";
        } else {
            line6 = "Broj recenzija : " + iz.getListaRecenzija().size();
        }
        return "<html>" + generisiIzvodjaceIzvodjenja(iz) + "<br/>" + line2 + "<br/>" + line3 + "<br/>" + line4 + "<br/>" + line5 + "<br/>" + line6 + "</html>";
    }

    public static String generisiNazivIzvodjaca(Izvodjenje izvodjenje) {
        StringBuilder name = new StringBuilder();
        for (MuzickoDelo mz : izvodjenje.getListaMuzickihDela()) {
            name.append(mz.getNazivDela()).append(",");
        }
        name = new StringBuilder(name.substring(0, name.length() - 1));
        return name.toString();
    }

    public static String generisiIzvodjaceIzvodjenja(Izvodjenje izvodjenje) {
        StringBuilder izvodjaci = new StringBuilder();
        for (Izvodjac izv : izvodjenje.getListaIzvodjaca()) {
            izvodjaci.append(izv.getNazivIzvodjaca()).append(",");
        }
        String line = izvodjaci.toString();
        if (!line.equals("")) {
            line = line.substring(0, line.length() - 1);
        }
        return line;
    }
}
