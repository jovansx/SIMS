package gui.dialogs;

import gui.panels.PanelSadrzaja;

import javax.swing.*;

public class DialogSadrzaja extends JDialog {
    public PanelSadrzaja panelSadrzaja;

    public DialogSadrzaja(){
        setTitle("Dodaj sadrzaj");
        setSize(500, 400);
        setResizable(false);
        panelSadrzaja = new PanelSadrzaja(this);
        setContentPane(panelSadrzaja);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
