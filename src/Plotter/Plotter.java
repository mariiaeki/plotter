package Plotter;

import javax.swing.SwingUtilities;

import Plotter.UI.PlotterController;
import Plotter.UI.PlotterUI;

public class Plotter {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PlotterUI ui = new PlotterUI();
                new PlotterController(ui);
            }
        });
    }

}