package org.example;


import javax.swing.*;
import java.awt.*;

public class AppLauncher
{
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // display our weather app gui
                new WeatherAppGui().setVisible(true);
            }
        });
    }
}

//        if (!GraphicsEnvironment.isHeadless()) {
//        new WeatherAppGui();
//        } else {
//                System.out.println("Rodando em modo headless");
//        }