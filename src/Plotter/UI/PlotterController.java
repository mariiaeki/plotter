package Plotter.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;

import Plotter.Function.Function;
import Plotter.Parser.Parser;

public class PlotterController implements ActionListener, MouseWheelListener {
	
	private static final String ACT_PAINT = "paint";
	private static final String ACT_CLEAR = "clear";
	
	private PlotterUI ui;
	private JButton btnPaint;
	private JButton btnClear;
	private GridComponent grid;
	
	public PlotterController(PlotterUI ui) {
		this.ui = ui;
		btnPaint = ui.getBtnPaint();
		btnClear = ui.getBtnClear();
		grid = ui.getGrid();
		
		btnPaint.setActionCommand(ACT_PAINT);
		btnClear.setActionCommand(ACT_CLEAR);
		
		btnPaint.addActionListener(this);
		btnClear.addActionListener(this);
		grid.addMouseWheelListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent act) {
		switch (act.getActionCommand()) {
		case ACT_PAINT:
			try {
				Function inputFunction = Parser.parse(ui.getFormula());
				ui.clearFormula();
				grid.addFunction(inputFunction);
				grid.repaint();
			} catch (UnsupportedOperationException e) {
				System.err.println("ERROR: Invalid input");
			}
			break;
		case ACT_CLEAR:
			ui.clearFormula();
			grid.clearFunctions();
			grid.repaint();
			break;
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double scrolled = e.getUnitsToScroll();
		if (scrolled > 0) {
			scrolled = 1.25;
		} else if (scrolled < 0) {
			scrolled = 0.8;
		} else {
			scrolled = 1.0;
		}
		grid.setXSize(scrolled * grid.getXSize());
		grid.setYSize(scrolled * grid.getYSize());
	}
	
}
