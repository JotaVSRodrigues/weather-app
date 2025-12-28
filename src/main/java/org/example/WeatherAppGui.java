package org.example;

import javax.swing.*;

public class WeatherAppGui extends JFrame {
    public WeatherAppGui(){
        // setup our gui and add a title
        super("Weather App");

        // configure gui to end the programs process once it has been closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set the size of our gui (in pixels)
        setSize(450, 650);

        // load our gui at the center os screen
        setLocationRelativeTo(null);


        // make our layout manager null to manually position our componentes within the gui
        setLayout(null);

        // prevent any resize of our gui
        setResizable(false);

        addGuiComponentes();
    }

    private void addGuiComponentes() {
        // search field
        JTextField searchTextField = new JTextField();

        // set the location and size of our component
        searchTextField.setBounds(15, 15, 350, 45);
    }
}
