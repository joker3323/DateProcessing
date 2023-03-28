package com.dataprocessingAPP;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;



public class LineOP extends JFrame {

    public LineOP() throws HeadlessException {

    }

    public static void Draw(float[] x,float[] y) {
        //数据
        float[] pmv_x = x;
        float[] pmv_y = y;


        ArrayList<Float> x_pmv = new ArrayList<Float>();
        ArrayList<Float> y_pmv = new ArrayList<Float>();


        for (int i = 0; i < pmv_y.length; i++) {

            if (pmv_y[i] != 0) {
                x_pmv.add(pmv_x[i]);
                y_pmv.add(pmv_y[i]);
            }
        }

        int pmv_point = x_pmv.size();

        float SumX = 0;
        float SumX2 = 0;
        for (int i = 0; i < pmv_point; i++) {
            SumX += x_pmv.get(i);
            SumX2 += (x_pmv.get(i) * x_pmv.get(i));
        }

        float SumY = 0;
        for (int i = 0; i < pmv_point; i++) {
            SumY += y_pmv.get(i);
        }

        float SumXY = 0;
        for (int i = 0; i < pmv_point; i++) {
            SumXY += (x_pmv.get(i) * y_pmv.get(i));
        }
        float b_pmv = ((SumX2 * SumY - SumX * SumXY) / (pmv_point * SumX2 - SumX
                * SumX));
        float k_pmv = ((pmv_point * SumXY - SumX * SumY) / (pmv_point * SumX2 - SumX
                * SumX));

        // 算直线斜率、截距



        // 构造图
        double[][] ScatterDatas_pmv = new double[2][pmv_point];
        double[][] LineDatas_pmv = new double[2][2];


        DefaultXYDataset LineDataset_pmv = new DefaultXYDataset();
        DefaultXYDataset ScatterDataset_pmv = new DefaultXYDataset();


        for (int i = 0; i < pmv_point; i++) {
            ScatterDatas_pmv[0][i] = x_pmv.get(i);
            ScatterDatas_pmv[1][i] = y_pmv.get(i);
        }
        float LineXMax_pmv = x_pmv.get(0);
        float LineXMin_pmv = x_pmv.get(0);

        for (int i = 0; i < pmv_point; i++) {
            if (LineXMax_pmv < x_pmv.get(i))
                LineXMax_pmv = x_pmv.get(i);
            if (LineXMin_pmv > x_pmv.get(i))
                LineXMin_pmv = x_pmv.get(i);
        }
        float LineYMax_pmv = LineXMax_pmv * k_pmv + b_pmv;


        // 最大值点
        // 算最小值
        float LineYMin_pmv = LineXMin_pmv * k_pmv + b_pmv;

        LineDatas_pmv[0][0] = LineXMin_pmv;
        LineDatas_pmv[1][0] = LineYMin_pmv;
        LineDatas_pmv[0][1] = LineXMax_pmv;
        LineDatas_pmv[1][1] = LineYMax_pmv;



        // 加载数据集
        String seriesKey="线性（Y="+k_pmv+"X"+"+"+b_pmv;
        ScatterDataset_pmv.addSeries("数据点", ScatterDatas_pmv);

        LineDataset_pmv.addSeries(seriesKey, LineDatas_pmv);


        // pmv
        JFreeChart chart = ChartFactory.createScatterPlot("最小二乘法拟合直线",
                "X", "Y", ScatterDataset_pmv, PlotOrientation.VERTICAL,
                true, false, false);
        XYPlot xyplot = chart.getXYPlot();// 图本身
        xyplot.setDataset(1, ScatterDataset_pmv);
        xyplot.setDataset(2, LineDataset_pmv);// 放折线图数据
        // plot.getRenderer().setSeriesPaint(0, Color.black) ;
        XYDotRenderer xydotrenderer1 = new XYDotRenderer();
        xydotrenderer1
                .setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        xyplot.setRenderer(1, xydotrenderer1);
        XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer();
        xylineandshaperenderer1
                .setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        xylineandshaperenderer1.setSeriesPaint(0, Color.BLUE);
        //xyplot.getRenderer(0).setSeriesPaint(0, Color.BLUE);//设置点颜色
        xyplot.setRenderer(2, xylineandshaperenderer1);
        xyplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        ChartFrame chartFrame = new ChartFrame("趋势线", chart);
        // chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。
        //该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        chartFrame.pack(); // 以合适的大小展现图形
        //chartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chartFrame.setVisible(true);// 图形是否可见


    }
}

