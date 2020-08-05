package gui.dialogs;

import gui.panels.PanelUrednikovihRecenzija;
import model.Recenzija;

import javax.swing.*;
import java.nio.file.DirectoryNotEmptyException;
import java.util.ArrayList;
import java.util.HashMap;

public class DialogUrednikovihRecenzija extends JDialog {
    public PanelUrednikovihRecenzija panel;
    public ArrayList<Recenzija> recenzije;
    public HashMap<Recenzija, Double> ocjene;

    public DialogUrednikovihRecenzija(ArrayList<Recenzija> recenzije, HashMap<Recenzija, Double> ocjene){
        this.recenzije=recenzije;
        this.ocjene=ocjene;
        panel=new PanelUrednikovihRecenzija(this,recenzije,ocjene);
        setTitle("Recenzije");
        setSize(700, 650);
        setResizable(false);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
