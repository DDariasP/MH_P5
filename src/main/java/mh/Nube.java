package mh;

import mh.tipos.*;
import java.awt.Color;
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
public class Nube extends JFrame {
    
    public void Nube(Lista<Particula> swarm, int t) {

        //crear la grafica
        XYPlot plot = new XYPlot();
        
        for (int i = 0; i < swarm.size(); i++) {
            //leer la particula
            Particula p = swarm.get(i);
            XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries series = new XYSeries("");
            series.add(p.x, p.y);
            dataset.addSeries(series);
            XYDataset datosP = dataset;
            //caracteristicas de la particula
            XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);
            renderer.setSeriesShape(0, p.forma);
            renderer.setSeriesPaint(0, p.color);
            //añadir la particula a la grafica
            plot.setDataset(i, datosP);
            plot.setRenderer(i, renderer);
        }

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
    
}
