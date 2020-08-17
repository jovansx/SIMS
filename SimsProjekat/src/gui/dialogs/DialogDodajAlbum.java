package gui.dialogs;
import gui.panels.PanelDodajAlbum;

import javax.swing.*;

public class DialogDodajAlbum extends JDialog {
    public PanelDodajAlbum panel;
    public int id;
    public DialogDodajAlbum(int id) {
        setTitle("Izbor albuma za muzicko delo");
        setSize(300, 300);
        setResizable(false);
        this.id = id;
        panel = new PanelDodajAlbum(this,id);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
