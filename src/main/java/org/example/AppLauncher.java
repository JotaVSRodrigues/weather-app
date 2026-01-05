package org.example;


import javax.swing.*;
import java.awt.*;

public class AppLauncher
{
    public static void main( String[] args ) throws HeadlessException{
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // display our weather app gui
                new WeatherAppGui().setVisible(true);

                // System.out.println(WeatherAppNew.getLocationData("Tokyo"));

                // System.out.println(WeatherAppNew.getCurrentTime());
            }
        });
    }
}
