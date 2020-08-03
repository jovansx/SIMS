package gui.dialogs;

import gui.panels.PanelProfil;

import javax.swing.*;

public class DialogProfil extends JDialog {
    public PanelProfil panel;

    public DialogProfil() {
        setTitle("Profil urednika");
        setSize(500, 500);
        setResizable(false);
        panel = new PanelProfil(this);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}