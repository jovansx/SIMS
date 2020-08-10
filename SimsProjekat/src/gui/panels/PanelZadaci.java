package gui.panels;

import gui.dialogs.DialogOdobravanje;
import gui.dialogs.DialogZadaci;
import model.Recenzija;
import model.Zadatak;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PanelZadaci extends JPanel {

    private DialogZadaci dialog;
    public JPanel dodatno;
    private JButton odobri, ne;
    public JTable table;
    public ArrayList<Zadatak> listaZadataka;

    public PanelZadaci(DialogZadaci dialog, ArrayList<Zadatak> listaZadataka) {
        this.dialog = dialog;
        this.listaZadataka = listaZadataka;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        //setLayout(null);
        namesti();
    }
    public void namesti(){

    }
}
