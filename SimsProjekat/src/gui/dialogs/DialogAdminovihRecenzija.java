package gui.dialogs;

import gui.panels.PanelAdminovihRecenzija;
import model.Recenzija;

import javax.swing.*;
import java.util.ArrayList;

public class DialogAdminovihRecenzija extends JDialog{
    public PanelAdminovihRecenzija panel;
    public ArrayList<Recenzija> recenzije;

    public DialogAdminovihRecenzija(ArrayList<Recenzija> recenzije){
        this.recenzije=recenzije;
        panel=new PanelAdminovihRecenzija(this, recenzije);

        setTitle("Odobravanje recenzija");
        setSize(700, 650);
        setResizable(false);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

    }
}
