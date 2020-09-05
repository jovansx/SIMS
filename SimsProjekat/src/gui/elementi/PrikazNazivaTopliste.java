package gui.elementi;
import model.TopLista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrikazNazivaTopliste extends JPanel{
    private JLabel labela;
    private JPanel panel;
    private PrikazTopliste prikaz;
    private String naziv;

    public PrikazNazivaTopliste(TopLista t,PrikazTopliste p) {
        super();
        this.prikaz = p;
        prikaz.setTitle("Topliste");
        inicijalizuj(t);

        add(panel);

        podesiAkcije(t);
    }
    private void inicijalizuj(TopLista t) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();
        String separator = System.getProperty("file.separator");

        panel.setPreferredSize(new Dimension(dimension.width / 4 * 3, dimension.height / 20 * 3));

        labela.setText(nazivTopliste(t));
    }
    public String provera(int month){
        String v = "";
        if(month == 1){
            v = "decembar";
        }else if(month == 2){
            v = "januar";
        }else if(month == 3){
            v = "februar";
        }else if(month == 4){
            v = "mart";
        }else if(month == 5){
            v = "april";
        }else if(month == 6){
            v = "maj";
        }else if(month == 7){
            v = "jun";
        }else if(month == 8){
            v = "jul";
        }else if(month == 9){
            v = "avgust";
        }else if(month == 10){
            v = "septembar";
        }else if(month == 11){
            v = "oktobar";
        }else if(month == 12){
            v ="novembar";
        }
        return v;
    }
    public String nazivTopliste(TopLista t){
        int month = t.getDatumGlasanja().getMonth();
        int year = t.getDatumGlasanja().getYear()+1900;
        String value = provera(month);
        String s = "Toplista meseca "+value +" godine "+ year;
        naziv = s;
        return s;
    }
    private void podesiAkcije(TopLista t) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Top tt = new Top(t.getListaIzvodjenja(),true);
                tt.setTitle(naziv);
                tt.setVisible(true);
            }
        });
    }

}
