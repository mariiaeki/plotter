package Plotter.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import Plotter.Function.Function;

public class GridComponent extends JComponent {
	
	private static final long serialVersionUID = 723889718236630903L;

	private static final String varID = "x";
	
	private Dimension size;
	private double xSize, ySize;
	private double xStep, yStep;
	private Line2D xAxis, yAxis;
	private Line2D[] xGrid, yGrid;
	
	private List<Function> functions;
	private int[] xPoints, yPoints;
	
	public GridComponent(Dimension size, double xSize, double ySize, double xStep, double yStep) {
		if (xSize < xStep || ySize < yStep) {
			throw new IllegalArgumentException();
		}
		
		this.size = size;
		this.xSize = xSize;
		this.ySize = ySize;
		this.xStep = xStep;
		this.yStep = yStep;
		
		xAxis = new Line2D.Double(0.0, size.height / 2.0, size.width - 1.0, size.height / 2.0);
		yAxis = new Line2D.Double(size.width / 2.0, 0.0, size.width / 2.0, size.height - 1.0);
		
		updateGrid();
		
		functions = new LinkedList<Function>();
		xPoints = new int[size.width];
		yPoints = new int[size.width];
		for (int i = 0; i < size.width; i++) {
			xPoints[i] = i;
		}
		
		setSize(getPreferredSize());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public boolean addFunction(Function f) {
		return functions.add(f);
	}
	
	public void clearFunctions() {
		functions.clear();
	}
	
	public double getXSize() {
		return xSize;
	}
	
	public double getYSize() {
		return ySize;
	}
	
	public boolean setXSize(double xSize) {
		double maxXSize = size.width * xStep / 2.0;
		if (xSize > maxXSize) {
			this.xSize = maxXSize;
			updateGrid();
			repaint();
			return false;
		} else if (xSize < xStep){
			this.xSize = xStep;
			updateGrid();
			repaint();
			return false;
		} else {
			this.xSize = xSize;
			updateGrid();
			repaint();
			return true;
		}
	}
	
	public boolean setYSize(double ySize) {
		double maxYSize = size.height * yStep / 2.0;
		if (ySize > maxYSize) {
			this.ySize = maxYSize;
			updateGrid();
			repaint();
			return false;
		} else if (ySize < yStep){
			this.ySize = yStep;
			updateGrid();
			repaint();
			return false;
		} else {
			this.ySize = ySize;
			updateGrid();
			repaint();
			return true;
		}
	}
	
	private void paintFunction(Function f, Graphics g, Color c) {
		Map<String, Double> assignment = new HashMap<>();
		for (int i = 0; i < size.width; i++) {
			double xCoord = i * xSize / xStep / size.width - xSize / 2;
			assignment.put(varID, xCoord);
			double value;
			try {
				value = f.evaluate(assignment);
			} catch (UnsupportedOperationException e) {
				System.err.println("ERROR: cannot plot function f(x) = " + f.toString());
				return;
			} catch (ArithmeticException e) {
				System.err.println("WARNING: arithmetic exception at " + xCoord);
				value = 0;
			}
			yPoints[i] = (int)Math.round(size.height / 2 - value * yStep * size.height / ySize);
		}
		g.setColor(c);
		g.drawPolyline(xPoints, yPoints, size.width);
	}
	
	private void updateGrid() {
		int horizLines = (int)(ySize / yStep) * 2;
		int vertLines = (int)(xSize / xStep) * 2;
		xGrid = new Line2D[horizLines];
		yGrid = new Line2D[vertLines];
		int xDiff = (int)(xStep / xSize * size.width);
		int yDiff = (int)(yStep / ySize * size.height);
		for (int i = 1; i <= xGrid.length / 2; i++) {
			xGrid[2 * i - 1] = new Line2D.Double(0, size.height / 2.0 + i * yDiff, size.width - 1.0, size.height / 2.0 + i * yDiff);
			xGrid[2 * i - 2] = new Line2D.Double(0, size.height / 2.0 - i * yDiff, size.width - 1.0, size.height / 2.0 - i * yDiff);
		}
		for (int i = 1; i <= yGrid.length / 2; i++) {
			yGrid[2 * i - 1] = new Line2D.Double(size.width / 2.0 + i * xDiff, 0, size.width / 2.0 + i * xDiff, size.height);
			yGrid[2 * i - 2] = new Line2D.Double(size.width / 2.0 - i * xDiff, 0, size.width / 2.0 - i * xDiff, size.height);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// clear component
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, size.width, size.height);
		
		// draw grid lines
		g2d.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < xGrid.length; i++) {
			g2d.draw(xGrid[i]);
		}
		for (int i = 0; i < yGrid.length; i++) {
			g2d.draw(yGrid[i]);
		}
		
		// draw axes
		g2d.setColor(Color.BLACK);
		g2d.draw(xAxis);
		g2d.draw(yAxis);
		
		// draw functions
		for (Function f : functions) {
			paintFunction(f, g, Color.RED);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return size;
	}
	
	@Override
	public Dimension getMaximumSize() {
		return size;
	}
	
	@Override
	public Dimension getMinimumSize() {
		return size;
	}
	
}
