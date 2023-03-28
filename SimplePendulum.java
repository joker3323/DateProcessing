package com.dataprocessingAPP;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class SimplePendulum extends JFrame {
    public static JPanel panel1,panel2,panel2_1,panel2_2;
    public static JTextField textField1,textField2,textField3,textField4,textField5,textField6,
            textField7;
    public static JTextArea textArea1,textArea2,textArea3,textArea4,textArea5;
    public static JButton button1,button2;

    Border etched = BorderFactory.createEtchedBorder();//设置边框类型
    Toolkit kit=Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize=kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth=screeSize.width;//获得屏幕宽
    int screenHeight=screeSize.height;//获得屏幕高

    public SimplePendulum(){
        setSize(screenWidth*3/5,screenHeight*2/3);//设置框架大小
        setTitle("单摆的研究");
        setLocationByPlatform(true);//设置框架位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭响应
        setLayout(new GridLayout(2,1));//设置2行1列的网格布局
        panel1=new JPanel(new GridLayout(1,4));
        panel2=new JPanel(new GridLayout(1,2));

        //初始化面板1
        addPanel1();
        add(panel1);

        //初始化面板2
        addPanel2();
        add(panel2);


    }

    private void addPanel1() {


        textArea1=new JTextArea(10,5);
        textArea2=new JTextArea(10,5);
        textArea3=new JTextArea(10,5);
        textArea4=new JTextArea(10,5);

        addTextArea(textArea1,"摆线长l/cm",panel1);
        addTextArea(textArea2,"摆动30个周期的时间t/s",panel1);
        addTextArea(textArea3,"单摆周期T/s",panel1);
        addTextArea(textArea4,"单摆周期的平方T^2/s^2",panel1);
    }

    private void addPanel2() {
        panel2_1=new JPanel(new GridLayout(7,2));
        panel2_2=new JPanel(new GridLayout(1,1));


        JLabel label1=new JLabel("米尺零点读数(cm):",JLabel.RIGHT);
        textField1=new JTextField(6);
        textField1.setText("0.00");
        panel2_1.add(label1);panel2_1.add(textField1);

        JLabel label2=new JLabel("米尺分度值(cm):",JLabel.RIGHT);
        textField2=new JTextField(6);
        textField2.setText("0.1");
        panel2_1.add(label2);panel2_1.add(textField2);


        JLabel label3=new JLabel("停表零点读数(s):",JLabel.RIGHT);
        textField3=new JTextField(6);
        textField3.setText("0.00");
        panel2_1.add(label3);panel2_1.add(textField3);

        JLabel label4=new JLabel("停表分度值(s):",JLabel.RIGHT);
        textField4=new JTextField(6);
        textField4.setText("0.01");
        panel2_1.add(label4);panel2_1.add(textField4);

       // JLabel label5=new JLabel("摆球的直径d=",JLabel.RIGHT);
        //textField5=new JTextField(6);
      // panel2_1.add(label5);panel2_1.add(textField5);

        JLabel label6=new JLabel("测量摆球的零点读数(mm):",JLabel.RIGHT);
        textField6=new JTextField(6);
        textField6.setText("0.00");
        panel2_1.add(label6);panel2_1.add(textField6);

        JLabel label7=new JLabel("测量摆球的分度值(mm):",JLabel.RIGHT);
        textField7=new JTextField(6);
        textField7.setText("0.02");
        panel2_1.add(label7);panel2_1.add(textField7);
//2_2初始化
        textArea5=new JTextArea(10,6);
        textArea5.setText("利用最小二乘法拟合，g(蒙自)=9.78443(m/s^2) \ny=k*x+b 其中k为斜率。\r\n拟合结果为:");
        button1=new JButton("数据计算");button2=new JButton("数据重置");

        panel2_2.add(textArea5);panel2_1.add(button1);panel2_1.add(button2);
button1.addActionListener((event)->{
    textArea3.setText("");
    textArea4.setText("");
        calculate2();});
button2.addActionListener((e)->{
    textArea2.setText("");
    textArea1.setText("");
    textArea3.setText("");
    textArea4.setText("");
});



        Border title=BorderFactory.createTitledBorder(etched,"参数及结果(采用最小二乘法拟合(l-T^2))");//设置边框
        panel2.setBorder(title);
        panel2.add(panel2_1);panel2.add(panel2_2);



    }
    private void calculate2(){
        double pi=Math.PI;
        double[] l=readTextArea(textArea1,textField1);
        double[] t=readTextArea(textArea2,textField3);
        for (double v : t) {
            textArea3.append(String.format("%.5f",v/30)+"\r\n");
            textArea4.append(String.format("%.5f",Math.pow(v/30,2))+"\r\n");
        }
        for (int i = 0; i < l.length; i++) {
            l[i]=l[i]/100;

        }
        double[] T=calculate(textArea3,panel2_2,textField3,textField4);

        double[] ucl=calculate(textArea1,panel2_2,textField1,textField2);
        double[] T2=readTextArea(textArea4,textField3);
        int n=Math.min(l.length,T2.length);
        //Num num=new Num(n,l,T2);
        //double a=num.a();double b=num.b();


        double[] X=readTextArea(textArea4,textField3);
        double[] Y=readTextArea(textArea1,textField1);
        float[] floatsX = new float[X.length];
        float[] floatsY = new float[Y.length];
        for (int i = 0; i < X.length; i++) {
            floatsX[i]=(float) X[i];
        }
        for (int i = 0; i < Y.length; i++) {
            floatsY[i]= (float) Y[i];
        }
        Draws(floatsX,floatsY);

    }

    public void addTextArea(JTextArea textArea,String name,JPanel panel){
        textArea.setText("");
        textArea.setEnabled(true);
        JScrollPane scrollPane=new JScrollPane(textArea);//a设置滑动框
        Border title0=BorderFactory.createTitledBorder(etched,name);//设置边框
        scrollPane.setBorder(title0);//设置边框
        panel.add(scrollPane);

    }
    private double[] readTextArea(JTextArea textArea,JTextField textField){
        double[] a;
        String[] lines =textArea.getText().split("\\r?\\n");//读取文本数据
        a=new double[lines.length];
        double zeroPoint= 0;
        try {
            zeroPoint = Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel2_2,"零点度数有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        }
        try {
            for (int i = 0; i < lines.length; i++) {
                a[i]= Double.parseDouble(lines[i])-zeroPoint;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel2_2,"输入数据有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        }
        return a;
    }//读取数据并返回一个数组，
    public double[] calculate(JTextArea textArea,JPanel panel,JTextField textField1,JTextField textField2){
        double sum=0,suma=0,adj=0,uc = 0,ua1,ua,a=0;

        String[] lines =textArea.getText().split("\\r?\\n");//读取文本数据

        try {
            a=Double.parseDouble(textField1.getText());
        }catch (NumberFormatException ex){
            int selection=JOptionPane.showConfirmDialog(panel,"请输入零点度数(单击ok将默认0.00)","参数不足",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            if (selection==JOptionPane.OK_OPTION){
                textField1.setText("0.00");}
        }

        for (String line : lines) {
            try {
                sum=sum+(Double.parseDouble(line)-a);
            } catch (NumberFormatException ex) {
                JOptionPane.showConfirmDialog(panel,"输入数据有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                //应该弹出一个警告框

            }
        }
        for (String s : lines) {

                adj= (sum/lines.length);
                suma=suma+Math.pow(((Double.parseDouble(s)-a)-adj),2);//计算A类


        }
        ua1= suma/(lines.length*(lines.length-1));
        ua= Math.sqrt(ua1);
        try {
            double ub1 = Double.parseDouble(textField2.getText());//计算B类
            double ub2=1.732;
            double ub=(ub1/ub2);
            uc= Math.hypot(ua,ub);//计算C类

        } catch (NumberFormatException e) {
            int selection=JOptionPane.showConfirmDialog(panel,"请输入分度值(单击ok将默认0.002)","参数不足",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            if (selection==JOptionPane.OK_OPTION){
                textField2.setText("0.002");}//应该弹出一个警告框
        }
        double[] u={adj,uc};
        return u;
    }//读取数据并返回平均值和uc

    private void Draws(float[] x,float[] y) {
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
        String seriesKey="线性（l="+k_pmv+"(T^2)"+"+("+b_pmv+")";
        ScatterDataset_pmv.addSeries("数据点", ScatterDatas_pmv);

        LineDataset_pmv.addSeries(seriesKey, LineDatas_pmv);


        // pmv
        JFreeChart chart = ChartFactory.createScatterPlot("最小二乘法拟合直线",
                "T^2", "摆线长l", ScatterDataset_pmv, PlotOrientation.VERTICAL,
                true, false, false);
        XYPlot xyplot = chart.getXYPlot();// 图本身
        xyplot.setDataset(1, ScatterDataset_pmv);
        xyplot.setDataset(2, LineDataset_pmv);// 放折线图数据
        // plot.getRenderer().setSeriesPaint(0, Color.black) ;
        XYDotRenderer xydotrenderer1 = new XYDotRenderer();
        xydotrenderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
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
        double g=4*Math.pow(Math.PI,2)*k_pmv;

        double uck=k_pmv * Math.hypot(2*(0.05/1.732)/((Math.pow(LineXMax_pmv,2)-Math.pow(LineXMin_pmv,2))) ,((0.01/1.732))/((Math.pow(LineYMax_pmv,2)-Math.pow(LineYMin_pmv,2))));
        double ucg=g*uck*k_pmv;
        //计算不确定度横坐标的分度值为0.05,纵坐标的分度值为0.1
        if (b_pmv<0){
            textArea5.append("l="+String.format("%.4f",k_pmv)+"T^2"+String.format("%,4f",b_pmv)+"\r\n");//单位问题
        }else {
            textArea5.append("l="+String.format("%.4f",k_pmv)+"T^2+"+String.format("%,4f",b_pmv)+"\r\n");//单位问题
        }

        textArea5.append("g="+String.format("%.4f",g/100)+"±"+String.format("%.4f",ucg/10000)+"(m/s^2)\r\n");
        //不确定度的计算有些问题，最小二乘法的不确定度计算？
        chartFrame.setVisible(true);// 图形是否可见

    }
}
