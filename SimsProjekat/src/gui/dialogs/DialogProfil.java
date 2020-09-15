package gui.dialogs;

import gui.panels.PanelProfil;

import javax.swing.*;

public class DialogProfil extends JDialog {
    public PanelProfil panel;

    public DialogProfil(int idUrednika) {
        setTitle("Profil urednika");
        setSize(500, 500);
        setResizable(false);
        panel = new PanelProfil(this,idUrednika);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}