package gui.dialogs;

import gui.panels.PanelOdobravanje;
import model.Recenzija;

import javax.swing.*;
import java.util.ArrayList;

public class DialogOdobravanje extends JDialog {
    public PanelOdobravanje panel;
    public int id;
    public DialogOdobravanje(int idUrednika) {
        setTitle("Odobravanje komentara");
        setSize(600, 600);
        setResizable(false);
        this.id = idUrednika;
        panel = new PanelOdobravanje(this,id);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
