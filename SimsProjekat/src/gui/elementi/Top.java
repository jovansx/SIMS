package gui.elementi;

import kontroler.UrednikovProzorKON;
import model.Izvodjenje;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class Top extends JFrame {
    private JScrollPane skrol;
    private JPanel skrolPanel;
    private JPanel glavniPanel;
    private Dimension dimension;
    private String separator;
    private JLabel nothingFoundL;
    private int brojElemenataIzvodjenja;
    private int year;
    private boolean value;
    private int month;
    private List<Izvodjenje> izvodjenja;
    public Top(boolean value, int month,int year) {
        super();
        this.value = value;
        this.year = year;
        this.month = month;
        this.izvodjenja = null;
        String m = proveraMeseca(month);
        if (value) {
            setTitle("Glasanje za topliste meseca " + m + " godine " + year);
        } else {
            setTitle("Glasanje za topliste godine " + year);
        }
        inicijalizuj();

        setSize(dimension.width / 5 * 3, dimension.height / 5 * 3);

        namesti();
        add(glavniPanel);

        setResizable(false);
        setLocationRelativeTo(null);
    }
    public String proveraMeseca(int month){
        String s = "";
        if(month == 1){
            s = "DECEMBAR";
        }else if(month == 2){
            s = "JANUAR";
        }else if(month == 3){
            s = "FEBRUAR";
        }else if(month == 4){
            s = "MART";
        }else if(month == 5){
            s = "APRIL";
        }else if(month == 6){
            s = "MAJ";
        }else if(month == 7){
            s = "JUN";
        }else if(month == 8){
            s = "JUL";
        }else if(month == 9){
            s = "AVGUST";
        }else if(month == 10){
            s = "SEPTEMBAR";
        }else if(month == 11){
            s = "OKTOBAR";
        }else if(month == 12){
            s = "NOVEMBAR";
        }
        return s;
    }
    private void inicijalizuj(){
        brojElemenataIzvodjenja = 5;
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        separator = System.getProperty("file.separator");
        nothingFoundL = new JLabel(new ImageIcon(
                "SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "nothingFound.png"));

        skrol.getVerticalScrollBar().setUnitIncrement(16);
        skrolPanel.setLayout(new BoxLayout(skrolPanel, BoxLayout.Y_AXIS));
    }
    public void osveziKomponentu(Component component) {
        component.validate();
        component.repaint();
    }

    private void namesti(){
        skrolPanel.removeAll();

        izvodjenja = UrednikovProzorKON.getListaZaGlasanje(month,year);
        if (izvodjenja != null && izvodjenja.size() == 0)skrolPanel.add(nothingFoundL);

        if (izvodjenja != null) {
            for (Izvodjenje iz : izvodjenja) {
                skrolPanel.add(new Toop(iz,Top.this));
            }
        }
        osveziKomponentu(skrolPanel);
        osveziKomponentu(skrol);
    }
}
