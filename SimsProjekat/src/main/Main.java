package main;

import util.FConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("program started working");

        Connection con = FConnection.getInstance();
    }
}
