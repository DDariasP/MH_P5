package mh;

import mh.tipos.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author diego
 */
public class Grafo extends JFrame {

    public Lista<Particula> cm;
    public int minX, maxX, minY, maxY;
    
    public Grafo(){}

    public void Grafo(Lista<Particula> camino) {
        cm = camino;

        //crear la grafica
        XYPlot plot = new XYPlot();

        //crear la nube
        XYDataset setNube = createNube();
        //caracteristicas de la nube
        XYItemRenderer rendererN = new XYLineAndShapeRenderer(false, true);
//        rendererN.setSeriesShape(0, new Rectangle2D.Double(-3.0, 0.0, 6.0, 6.0));
        rendererN.setSeriesPaint(0, Color.MAGENTA);
        //añadir la nube a la grafica
        plot.setDataset(cm.size(), setNube);
        plot.setRenderer(cm.size(), rendererN);

        //crear y añadir los ejes
        ValueAxis domain = new NumberAxis("");
        int diffX = Math.abs((maxX - minX) / 10);
        domain.setRange(minX - diffX, maxX + diffX);
        ValueAxis range = new NumberAxis("");
        int diffY = Math.abs((maxY - minY) / 10);
        range.setRange(minY - diffY, maxY + diffY);
        plot.setDomainAxis(0, domain);
        plot.setRangeAxis(0, range);

        //crear el area de trazado
        JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        plot.setBackgroundPaint(Color.DARK_GRAY);
        chart.removeLegend();

        //crear la ventana 
        ChartPanel panel = new ChartPanel(chart);
        panel.setDomainZoomable(true);
        panel.setRangeZoomable(true);
        setContentPane(panel);
    }

    private XYDataset createNube() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        //ciudades 
        XYSeries series = new XYSeries("");
        for (int i = 0; i < cm.size(); i++) {
            series.add(cm.get(i).x, cm.get(i).y);
        }
        minY = (int) series.getMinY();
        maxY = (int) series.getMaxY();
        minX = (int) series.getMinX();
        maxX = (int) series.getMaxX();
        dataset.addSeries(series);
        return dataset;
    }

    private XYDataset createDist(int n) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        //parte-i
        XYSeries series = new XYSeries("");
        series.add(cm.get(n % cm.size()).x, cm.get(n).y);
        series.add(cm.get((n + 1) % cm.size()).x, cm.get((n + 1) % cm.size()).y);
        dataset.addSeries(series);
        return dataset;
    }
}
