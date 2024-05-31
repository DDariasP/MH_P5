package mh;

import mh.tipos.*;
import mh.algoritmos.*;
import java.awt.*;
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

public class Grafica extends JFrame {

    public double minY, maxY;

    public Grafica(BusquedaLocal[] bl, int t) {
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;

        //crear la grafica
        XYPlot plot = new XYPlot();

        for (int i = 0; i < bl.length; i++) {
            String nombre = P5.P[t] + "-S" + P5.SEED[i];
            //crear la funcion
            XYDataset funcion = createDataset(bl[i].convergencia, nombre);
            //caracteristicas de la funcion
            XYItemRenderer renderer = new XYLineAndShapeRenderer(true, true);
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));
            //añadir la funcion a la grafica
            plot.setDataset(i, funcion);
            plot.setRenderer(i, renderer);
        }

        //crear y añadir los ejes
        ValueAxis domain = new NumberAxis("Iteración (1 : " + P5.RATIO[t] + ")");
        ValueAxis range = new NumberAxis("Coste");
        double diffY = Math.abs((maxY - minY) / 10.0);
        range.setRange(minY - diffY, maxY + diffY);
        plot.setDomainAxis(0, domain);
        plot.setRangeAxis(0, range);

        //crear el area de trazado
        JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        plot.setBackgroundPaint(Color.DARK_GRAY);

        //crear la ventana 
        ChartPanel panel = new ChartPanel(chart);
        panel.setDomainZoomable(true);
        panel.setRangeZoomable(true);
        setContentPane(panel);
    }

    private XYDataset createDataset(Lista<Double> datos, String nombre) {
        //leer los datos
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(nombre);
        for (int i = 0; i < datos.size(); i++) {
            series.add(i, datos.get(i));
        }
        //determinar min/max
        if (minY > series.getMinY()) {
            minY = series.getMinY();
        }
        if (maxY < series.getMaxY()) {
            maxY = series.getMaxY();
        }
        dataset.addSeries(series);
        return dataset;
    }
}
