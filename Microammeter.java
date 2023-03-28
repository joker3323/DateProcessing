package com.dataprocessingAPP;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.logging.Level;

public class Microammeter extends JFrame {
    public static JPanel panel1, panel1_1, panel1_2, panel2, panel2_1, panel2_2;
    public static JTextArea textArea1_1, textArea1_2, textArea1_3, textArea2_1, textArea2_2, textArea2_3;
    public static JTextField textField1, textField2;

    public static JButton button1, button2, button3, button4;
    Border etched = BorderFactory.createEtchedBorder();//设置边框类型
    Toolkit kit = Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize = kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth = screeSize.width;//获得屏幕宽
    int screenHeight = screeSize.height;//获得屏幕高

    public Microammeter() {
        setSize(screenWidth / 2, screenHeight * 2 / 5);//设置框架大小
        setTitle("电表的改装");
        setLocationByPlatform(true);//设置框架位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭响应
        setLayout(new GridLayout(1, 2));//设置1行2列的主面板网格布局

        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel(new BorderLayout());

        addPanel1();
        add(panel1);

        addPanel2();
        add(panel2);
    }

    private void addPanel2() {
        Border title2 = BorderFactory.createTitledBorder(etched, "校准电压表");//设置面板1   设置边框
        panel2.setBorder(title2);
        panel2_1 = new JPanel(new GridLayout(1, 3));
        panel2_2 = new JPanel(new GridLayout(1, 4));

        textArea2_1 = new JTextArea(10, 4);
        textArea2_2 = new JTextArea(10, 4);
        textArea2_3 = new JTextArea(10, 4);

        addTextArea(textArea2_1, "U(V)", panel2_1);
        textArea2_1.setText("0\r\n0.2\r\n0.4\r\n0.6\r\n0.8\r\n1.0\r\n1.2\r\n1.4\r\n1.6\r\n1.8\r\n2.0\r\n");
        addTextArea(textArea2_2, "准确值U(p)", panel2_1);
        addTextArea(textArea2_3, "差值|U-U(p)|", panel2_1);

        button3 = new JButton("数据计算");
        button4 = new JButton("数据重置");
        JLabel label2 = new JLabel("级别a=", JLabel.CENTER);
        textField2 = new JTextField(8);

        button3.addActionListener((e -> calculatePanel2()));
        button4.addActionListener(e -> {
            textArea2_1.setText("0\r\n0.2\r\n0.4\r\n0.6\r\n0.8\r\n1.0\r\n1.2\r\n1.4\r\n1.6\r\n1.8\r\n2.0\r\n");
            textArea2_2.setText("");
            textArea2_3.setText("");
        });
        panel2_2.add(button3);
        panel2_2.add(button4);
        panel2_2.add(label2);
        panel2_2.add(textField2);
        panel2.add(panel2_1, BorderLayout.CENTER);
        panel2.add(panel2_2, BorderLayout.SOUTH);

    }

    private void calculatePanel2() {
        textArea2_3.setText("");
        double[] I = readTextArea(textArea2_1, panel2_1);
        double[] Ip = readTextArea(textArea2_2, panel2_1);
        for (int i = 0; i < Math.min(I.length, Ip.length); i++) {
            double a = I[i] - Ip[i];
            textArea2_3.append(String.format("%.4f",Math.abs(a)) + "\r\n");

        }
        double[] IIp = readTextArea(textArea2_3, panel2_1);
        double max = MAX(IIp);
        level(max, textArea2_1, panel2_1, textField2);
        double[] i_ip=readTextArea(textArea2_3,panel2_1) ;
        JFreeChartTest ex = new JFreeChartTest("误差校准曲线图","U(p)——U(p)-U"
                ,"电压表校准曲线图","U/V ","U(p)-U/V",I,i_ip);
        ex.setVisible(true);
    }


    private void addPanel1() {
        Border title1 = BorderFactory.createTitledBorder(etched, "校准电流表");//设置面板1   设置边框
        panel1.setBorder(title1);
        panel1_1 = new JPanel(new GridLayout(1, 3));
        panel1_2 = new JPanel(new GridLayout(1, 4));

        textArea1_1 = new JTextArea(10, 4);
        textArea1_2 = new JTextArea(10, 4);
        textArea1_3 = new JTextArea(10, 4);

        addTextArea(textArea1_1, "I(mA)", panel1_1);
        textArea1_1.setText("0\r\n6\r\n12\r\n18\r\n24\r\n30\r\n36\r\n42\r\n48\r\n54\r\n60");
        addTextArea(textArea1_2, "准确值I(p)", panel1_1);
        addTextArea(textArea1_3, "差值|I-I(p)|", panel1_1);

        button1 = new JButton("数据计算");
        button2 = new JButton("数据重置");
        JLabel label1 = new JLabel("级别a=", JLabel.CENTER);
        textField1 = new JTextField(8);
        button1.addActionListener((e -> calculatePanel1()));
        button2.addActionListener(e -> {
            textArea1_1.setText("0\r\n6\r\n12\r\n18\r\n24\r\n30\r\n36\r\n42\r\n48\r\n54\r\n60");
            textArea1_2.setText("");
            textArea1_3.setText("");
        });
        panel1_2.add(button1);
        panel1_2.add(button2);
        panel1_2.add(label1);
        panel1_2.add(textField1);
        panel1.add(panel1_1, BorderLayout.CENTER);
        panel1.add(panel1_2, BorderLayout.SOUTH);

    }

    private void calculatePanel1() {
        textArea1_3.setText("");
        double[] I = readTextArea(textArea1_1, panel1_1);
        double[] Ip = readTextArea(textArea1_2, panel1_1);
        for (int i = 0; i < Math.min(I.length, Ip.length); i++) {
            double a = I[i] - Ip[i];
            textArea1_3.append(String.format("%.4f",Math.abs(a) )+ "\r\n");

        }
        double[] IIp = readTextArea(textArea1_3, panel1_1);
        double max = MAX(IIp);
        level(max, textArea1_1, panel1_1, textField1);
        double[] i_ip=readTextArea(textArea1_3,panel1_1) ;
        JFreeChartTest ex = new JFreeChartTest("误差校准曲线图","I——I(p)-I"
                ,"电流表校准曲线图","I/mA ","I(p)-I/mA",I,i_ip);
        ex.setVisible(true);
    }

    private double MAX(double[] iIp) {
        Arrays.sort(iIp);
        return iIp[iIp.length - 1];
    }

    private void level(double v, JTextArea textArea, JPanel panel, JTextField textField1) {//计算电表级别
        double[] I = readTextArea(textArea, panel);

        double dt = (v / I[I.length - 1]) * 100;
        if (dt <= 0.1) {
            textField1.setText("0.1");
        } else if (dt <= 0.2) {
            textField1.setText("0.2");
        } else if (dt <= 0.5) {
            textField1.setText("0.5");
        } else if (dt <= 1.0) {
            textField1.setText("1.0");
        } else if (dt <= 1.5) {
            textField1.setText("1.5");
        } else if (dt <= 2.5) {
            textField1.setText("2.5");
        } else if (dt <= 5) {
            textField1.setText("5.0");
        } else {
            textField1.setText("大于5.0");
        }
    }

    private void addTextArea(JTextArea textArea, String name, JPanel panel) {
        textArea.setText("");
        textArea.setEnabled(true);
        JScrollPane scrollPane = new JScrollPane(textArea);//a设置滑动框
        Border title0 = BorderFactory.createTitledBorder(etched, name);//设置边框
        scrollPane.setBorder(title0);//设置边框
        panel.add(scrollPane);
    }

    private double[] readTextArea(JTextArea textArea, JPanel panel) {
        double[] a;
        String[] lines = textArea.getText().split("\\r?\\n");//读取文本数据
        a = new double[lines.length];

        try {
            for (int i = 0; i < lines.length; i++) {
                a[i] = Double.parseDouble(lines[i]);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel, "输入数据有误！请检查数据。", "数据有误", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        }
        return a;
    }//读取数据并返回一个数组，

}
