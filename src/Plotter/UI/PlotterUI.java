package Plotter.UI;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Plotter.Parser.Parser;

public class PlotterUI extends JFrame {
	
	private static final long serialVersionUID = 5888598103960137807L;
	
	private static final int SPACING = 10;
	private static final int GRID_WIDTH = 400;
	private static final int GRID_HEIGHT = 400;
	
	private GridComponent pnlGrid;
	private JTextField txtFormula;
	private JButton btnPaint;
	private JButton btnClear;
	
	public PlotterUI() {
		JPanel pnlContent = new JPanel();
		pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.X_AXIS));
		pnlContent.setBorder(BorderFactory.createEmptyBorder(SPACING, SPACING, SPACING, SPACING));
		
		pnlGrid = new GridComponent(new Dimension(GRID_WIDTH, GRID_HEIGHT), 10, 10, 1, 1);
		pnlGrid.addFunction(Parser.parse("x*x - 4"));
		pnlGrid.addFunction(Parser.parse("x"));
		pnlGrid.addFunction(Parser.parse("1"));
		
		JPanel pnlCtrl = new JPanel();
		pnlCtrl.setLayout(new BoxLayout(pnlCtrl, BoxLayout.Y_AXIS));
		pnlCtrl.setBorder(BorderFactory.createEmptyBorder(0, SPACING, 0, 0));
		
		JPanel pnlFormula = new JPanel();
		pnlFormula.setLayout(new BoxLayout(pnlFormula, BoxLayout.X_AXIS));
		JLabel lblFormula = new JLabel("f(x) = ");
		txtFormula = new JTextField(12);
		txtFormula.setMaximumSize(txtFormula.getPreferredSize());
		pnlFormula.add(lblFormula);
		pnlFormula.add(txtFormula);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));
		btnPaint = new JButton("Zeichnen");
		btnClear = new JButton("Clear");

		pnlButtons.add(btnPaint);
		pnlButtons.add(Box.createRigidArea(new Dimension(SPACING, 0)));
		pnlButtons.add(btnClear);
		pnlButtons.add(Box.createRigidArea(new Dimension(SPACING,0)));

		pnlCtrl.add(pnlFormula);
		pnlCtrl.add(Box.createRigidArea(new Dimension(0, SPACING)));
		pnlCtrl.add(pnlButtons);
		
		pnlContent.add(pnlGrid);
		pnlContent.add(pnlCtrl);
		setContentPane(pnlContent);
		
		setTitle("Plotter");
		pack();
		setResizable(false);
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public String getFormula() {
		return txtFormula.getText();
	}
	
	public void clearFormula() {
		txtFormula.setText("");
	}
	
	public JButton getBtnPaint() {
		return btnPaint;
	}
	
	public JButton getBtnClear() {
		return btnClear;
	}
	
	public GridComponent getGrid() {
		return pnlGrid;
	}
	
}
