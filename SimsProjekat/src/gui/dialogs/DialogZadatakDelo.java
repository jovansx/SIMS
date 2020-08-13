package gui.dialogs;
import gui.panels.PanelZadatakDelo;
import javax.swing.*;


public class DialogZadatakDelo  extends JDialog {
    public PanelZadatakDelo panel;
    public int id;
    public DialogZadatakDelo(int id) {
        setTitle("Opis muzickog dela");
        setSize(700, 450);
        setResizable(false);
        this.id = id;
        panel = new PanelZadatakDelo(this,id);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
