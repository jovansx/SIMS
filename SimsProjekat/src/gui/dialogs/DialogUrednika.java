package gui.dialogs;

import gui.panels.PanelUrednika;
import model.Urednik;

import javax.swing.*;
import java.util.ArrayList;

public class DialogUrednika extends JDialog {
    public PanelUrednika panel;
    public ArrayList<Urednik> urednici;

    public DialogUrednika(ArrayList<Urednik> urednici){
        this.urednici=urednici;
        panel= new PanelUrednika(this, urednici);
        setTitle("Pregled aktivnosti urednika");
        setSize(700, 650);
        setResizable(false);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
