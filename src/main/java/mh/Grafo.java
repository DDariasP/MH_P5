package mh;

import mh.tipos.*;
import java.awt.Color;
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

    public Lista<Particula> nube;

    public Grafo() {
    }

    public void Grafo(Lista<Particula> swarm, int t) {
        nube = swarm;

        //crear la grafica
        XYPlot plot = new XYPlot();

        //crear la nube
        XYDataset setNube = createNube();
        //caracteristicas de la nube
        XYItemRenderer rendererN = new XYLineAndShapeRenderer(false, true);
        rendererN.setSeriesShape(0, new Rectangle2D.Double(-3.0, 0.0, 6.0, 6.0));
        rendererN.setSeriesPaint(0, Color.MAGENTA);
        //añadir la nube a la grafica
        plot.setDataset(nube.size(), setNube);
        plot.setRenderer(nube.size(), rendererN);

        //crear y añadir los ejes
        ValueAxis domain = new NumberAxis("");
        domain.setRange(P5.MINX[t], P5.MAXX[t]);
        ValueAxis range = new NumberAxis("");
        range.setRange(P5.MINY[t], P5.MAXY[t]);
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
        for (int i = 0; i < nube.size(); i++) {
            series.add(nube.get(i).x, nube.get(i).y);
        }
        dataset.addSeries(series);
        return dataset;
    }

}
