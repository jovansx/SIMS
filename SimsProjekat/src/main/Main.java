package main;

import gui.elementi.GlavniProzor;
import gui.dialogs.DialogPrijave;
import javax.swing.*;

import gui.elementi.NoviProzor;
import util.FConnection;
import java.io.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("program started working");
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NoviProzor gp = new NoviProzor();
                gp.setVisible(true);
            }
        });
    }
}
