package gui.dialogs;

import gui.panels.PanelOdobravanje;
import model.Recenzija;

import javax.swing.*;
import java.util.ArrayList;

public class DialogOdobravanje extends JDialog {
    public PanelOdobravanje panel;
    public ArrayList<Recenzija> lista;
    public DialogOdobravanje(ArrayList<Recenzija> listaRecenzija) {
        setTitle("Odobravanje komentara");
        setSize(600, 600);
        setResizable(false);
        this.lista = listaRecenzija;
        panel = new PanelOdobravanje(this,lista);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
