package com.dataprocessingAPP;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.YInterval;

public class JFreeChartTest extends JFrame {
    private final String head;//大标题某某折直线图
    private final String name;//折线名称
    private final String title;//图表标题
    private final String Xtitle;//横坐标名称
    private final String Ytitle;//纵坐标名称
    private double[] X;
    private double[] Y;

    public JFreeChartTest(String head, String name, String title, String xtitle, String ytitle, double[]X, double[]Y) throws HeadlessException {
        this.head = head;
        this.name = name;
        this.title = title;
        this.Xtitle = xtitle;
        this.Ytitle = ytitle;
        this.X=X;
        this.Y=Y;
        initUI();
    }

    private void initUI(){
    XYDataset dataset = createDataset();
    JFreeChart chart = createChart(dataset);
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    chartPanel.setBackground(Color.white);
    add(chartPanel);

    pack();
    setTitle(head);
    setLocationRelativeTo(null);
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
    private XYDataset createDataset() {

        int min = Math.min(X.length, Y.length);
        XYSeries series = new XYSeries(name);
        for (int i = 0; i < X.length; i++) {
            series.add(X[i],Y[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                Xtitle,
                Ytitle,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(title,
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;

    }



}