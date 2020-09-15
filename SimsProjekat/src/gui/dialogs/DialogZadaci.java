package gui.dialogs;
import gui.panels.PanelZadaci;
import model.Zadatak;

import javax.swing.*;
import java.util.ArrayList;

public class DialogZadaci  extends JDialog {
    public PanelZadaci panel;

    public DialogZadaci(int idUrednika) {
        setTitle("Zadaci urednika");
        setSize(500, 500);
        setResizable(false);
        panel = new PanelZadaci(this,idUrednika);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
