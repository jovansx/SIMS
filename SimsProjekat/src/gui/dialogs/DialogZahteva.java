package gui.dialogs;

import gui.panels.PanelZahteva;
import model.Zahtev;

import javax.swing.*;
import java.util.ArrayList;

public class DialogZahteva extends JDialog {
    public PanelZahteva panel;
    public ArrayList<Zahtev> zahtevi;

    public DialogZahteva(ArrayList<Zahtev> zahtevi){
        this.zahtevi=zahtevi;
        panel=new PanelZahteva(this,zahtevi);

        setTitle("Obrada zahteva");
        setSize(700, 650);
        setResizable(false);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
