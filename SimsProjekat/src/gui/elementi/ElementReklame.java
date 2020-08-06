package gui.elementi;

import dao.ReklamaDAO;
import model.Reklama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ElementReklame extends JPanel{
    private JPanel panelReklame;
    private JLabel labelaIkone;
    private JLabel labelaNaziva;
    private JLabel labelaOpisa;
    private Toolkit tool;
    private Dimension dimension;

    public ElementReklame(Reklama r, GlavniProzor gp) {
        super();

        tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();

        panelReklame.setPreferredSize(
                new Dimension(dimension.width/24*3 + 30, dimension.height/16*3 + 10));
        labelaIkone.setSize(dimension.width/24*3 + 10, dimension.height/16*3-25);

        add(panelReklame);

        labelaNaziva.setText(r.getNaziv());
        labelaOpisa.setText(r.getText());

        String separator = System.getProperty("file.separator");

        ImageIcon retImageIcon = ReklamaDAO.getSliku(r, separator);
        Image im = retImageIcon.getImage();
        Image myImg = im.getScaledInstance(labelaIkone.getWidth(), labelaIkone.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImage = new ImageIcon(myImg);
        labelaIkone.setIcon(newImage);

        panelReklame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI(r.getUrl()));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
                gp.obrisiReklamu(r, ElementReklame.this);
            }
        });
    }
}
