package com.dataprocessingAPP;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UsedTable extends JFrame {
    public static JPanel panel1, panel1_1, panel1_2, panel1_3, panel2, panel2_1, panel2_2, panel2_3, panel3, panel3_1, panel3_2,panel3_3;
    public static JTextArea textArea1_1, textArea1_2, textArea2_1, textArea2_2, textArea3_1, textArea3_2;
    public static JTextField textField1, textField2, textField3, textField4, textField5, textField6,
            textField7, textField8;
    public static JButton button1, button2, button3, button4, button5, button6;
    Border etched = BorderFactory.createEtchedBorder();//设置边框类型

    Toolkit kit = Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize = kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth = screeSize.width;//获得屏幕宽
    int screenHeight = screeSize.height;//获得屏幕高

    public UsedTable() {
        //框架相关选择
        setSize(screenWidth * 3 / 5, screenHeight * 3 / 5);//设置框架大小
        setTitle("学习使用万用表");
        setLocationByPlatform(true);//设置框架位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭响应
        setLayout(new GridLayout(1, 3));//设置1行3列的网格布局

        panel1 = new JPanel(new GridLayout(3, 1));
        panel2 = new JPanel(new GridLayout(3, 1));
        panel3 = new JPanel(new GridLayout(3, 1));

        //初始化窗口
        addPanel1();
        add(panel1);

        addPanel2();
        add(panel2);

        addPanel3();
        add(panel3);

    }

    private void addPanel3() {
        panel3_1 = new JPanel(new GridLayout());
        textArea3_1 = new JTextArea(8, 5);
        textArea3_2 = new JTextArea(8, 5);

        addTextArea(textArea3_1, "R/Ω", panel3_1);

        Border title = BorderFactory.createTitledBorder(etched, "测量电阻");//设置面板1——1  设置边框
        panel3_1.setBorder(title);
        

        //初始化面板1，2位置
        Border title0 = BorderFactory.createTitledBorder(etched, "参数输入");//设置面板1——2  设置边框
        panel3_2 = new JPanel(new GridLayout(4, 2));//设置为网格布局，到时候看效果在调整
        panel3_2.setBorder(title0);


        JLabel label1 = new JLabel("倍率:", SwingConstants.CENTER);//设置面板1——2
        textField7 = new JTextField(6);
        textField7.setText("10");
        panel3_2.add(label1);
        panel3_2.add(textField7);
        

        JLabel label3 = new JLabel("准确等级读数a:", SwingConstants.CENTER);
        textField8 = new JTextField(6);
        textField8.setText("2.5");
        panel3_2.add(label3);
        panel3_2.add(textField8);

        button5 = new JButton("数据计算");
        button6 = new JButton("数值重置");
        panel3_2.add(button5);
        panel3_2.add(button6);
        button5.addActionListener((event) -> calculatePanel3());
        button6.addActionListener((event) -> {
            textArea3_1.setText("");
            textArea3_2.setText("");
        });
        Border title1 = BorderFactory.createTitledBorder(etched, "测量结果");//设置面板1——3   设置边框
        panel3_3 = new JPanel(new GridLayout());
        panel3_3.setBorder(title1);
        panel3_3.add(textArea3_2);


        panel3.add(panel3_1);
        panel3.add(panel3_2);
        panel3.add(panel3_3);

    }

    private void calculatePanel3() {
        double[] v=readTextArea(panel3_2,textArea3_1,textField7,textField8);
        double ur=0;
        try {
            ur= Double.parseDouble(textField7.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel3_2,"请输入倍率！","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

        }
        double ar = 0;
        try {
            ar= Double.parseDouble(textField8.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel3_2,"请输入准确度等级！","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

        }
        textArea3_2.append("μ(R)="+String.format("%.5f",(ur*10*(ar/100))/1.732)+"Ω"+"\r\n");

        textArea3_2.append("测量结果：R±μ="+v[0]*ur+"±"+String.format("%.5f",(ur*10*(ar/100))/1.732)+"Ω"+"\r\n");

    }

    private void addPanel2() {
        panel2_1 = new JPanel(new GridLayout());
        textArea2_1 = new JTextArea(8, 5);
        textArea2_2 = new JTextArea(8, 5);

        addTextArea(textArea2_1, "U/V", panel2_1);

        Border title = BorderFactory.createTitledBorder(etched, "测量直流电压");//设置面板1——1  设置边框
        panel2_1.setBorder(title);

        //初始化面板1，2位置
        Border title0 = BorderFactory.createTitledBorder(etched, "参数输入");//设置面板1——2  设置边框
        panel2_2 = new JPanel(new GridLayout(4, 2));//设置为网格布局，到时候看效果在调整
        panel2_2.setBorder(title0);


        JLabel label1 = new JLabel("量程/V:", SwingConstants.CENTER);//设置面板1——2
        textField4 = new JTextField(6);
        textField4.setText("10");
        panel2_2.add(label1);
        panel2_2.add(textField4);

        JLabel label12 = new JLabel("分度值/(V/div):", SwingConstants.CENTER);
        textField5 = new JTextField(6);
        textField5.setText("0.2");
        panel2_2.add(label12);
        panel2_2.add(textField5);

        JLabel label3 = new JLabel("准确等级读数a:", SwingConstants.CENTER);
        textField6 = new JTextField(6);
        textField6.setText("2.5");
        panel2_2.add(label3);
        panel2_2.add(textField6);

        button3 = new JButton("数据计算");
        button4 = new JButton("数值重置");
        panel2_2.add(button3);
        panel2_2.add(button4);
        button3.addActionListener((event) -> calculatePanel2());
        button4.addActionListener((event) -> {
            textArea2_1.setText("");
            textArea2_2.setText("");
        });
        Border title1 = BorderFactory.createTitledBorder(etched, "测量结果");//设置面板1——3   设置边框
        panel2_3 = new JPanel(new GridLayout());
        panel2_3.setBorder(title1);
        panel2_3.add(textArea2_2);

        panel2.add(panel2_1);panel2.add(panel2_2);panel2.add(panel2_3);



    }

    private void calculatePanel2() {
        double[] v=readTextArea(panel2_2,textArea2_1,textField4,textField6);
        textArea2_2.append("μ(U)="+String.format("%.5f",(v[1]*(v[2]/100))/1.732)+"V"+"\t\n");
        textArea2_2.append("测量结果：U±μ="+v[0]+"±"+String.format("%.5f",(v[1]*(v[2]/100))/1.732)+"V"+"\t\n");


    }

    private void addPanel1() {
        panel1_1 = new JPanel(new GridLayout());
        textArea1_1 = new JTextArea(8, 5);
        textArea1_2 = new JTextArea(8, 5);

        addTextArea(textArea1_1, "I/mA", panel1_1);

        Border title = BorderFactory.createTitledBorder(etched, "测量直流电流");//设置面板1——1  设置边框
        panel1_1.setBorder(title);

        //初始化面板1，2位置
        Border title0 = BorderFactory.createTitledBorder(etched, "参数输入");//设置面板1——2  设置边框
        panel1_2 = new JPanel(new GridLayout(4, 2));//设置为网格布局，到时候看效果在调整
        panel1_2.setBorder(title0);


        JLabel label1 = new JLabel("量程/mA:", SwingConstants.CENTER);//设置面板1——2
        textField1 = new JTextField(6);
        textField1.setText("500");
        panel1_2.add(label1);
        panel1_2.add(textField1);

        JLabel label2 = new JLabel("分度值/(mA/div):", SwingConstants.CENTER);
        textField2 = new JTextField(6);
        textField2.setText("10");
        panel1_2.add(label2);
        panel1_2.add(textField2);

        JLabel label3 = new JLabel("准确等级读数a:", SwingConstants.CENTER);
        textField3 = new JTextField(6);
        textField3.setText("2.5");
        panel1_2.add(label3);
        panel1_2.add(textField3);

        button1 = new JButton("数据计算");
        button2 = new JButton("数值重置");
        panel1_2.add(button1);
        panel1_2.add(button2);
        button1.addActionListener((event) -> calculatePanel1());
        button2.addActionListener((event) -> {
            textArea1_1.setText("");
            textArea1_2.setText("");
        });
        Border title1 = BorderFactory.createTitledBorder(etched, "测量结果");//设置面板1——3   设置边框
        panel1_3 = new JPanel(new GridLayout());
        panel1_3.setBorder(title1);
        panel1_3.add(textArea1_2);

        panel1.add(panel1_1);panel1.add(panel1_2);panel1.add(panel1_3);



    }

    private void calculatePanel1(){

        double[] v=readTextArea(panel1_2,textArea1_1,textField1,textField3);
        textArea1_2.append("μ(I)="+String.format("%.5f",(v[1]*(v[2]/100))/1.732)+"mA"+"\r\n");
        textArea1_2.append("测量结果：I±μ="+v[0]+"±"+String.format("%.5f",(v[1]*(v[2]/100))/1.732)+"mA"+"\r\n");

    }
    private void addTextArea (JTextArea textArea,String name,JPanel panel){
        textArea.setText("");
        textArea.setEnabled(true);
        JScrollPane scrollPane=new JScrollPane(textArea);//a设置滑动框
        Border title0=BorderFactory.createTitledBorder(etched,name);//设置边框
        scrollPane.setBorder(title0);//设置边框
        panel.add(scrollPane);
    }
    private double [] readTextArea(JPanel panel,JTextArea textArea,JTextField textField,JTextField textFields){
        double[] a;
        String[] lines =textArea.getText().split("\\r?\\n");//读取文本数据
        a=new double[lines.length];
        double L= 15;
        try {
            L = Double.parseDouble(textFields.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel,"请输入量程！","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        }
        double S=2.5;
        try {
            S=Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel,"请输入准度等级！","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        }
        try {
            for (int i = 0; i < lines.length; i++) {
                a[i]= Double.parseDouble(lines[i]);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel,"输入数据有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        }
        double sum=0;
        for (double v : a) {
            sum=v+sum;
        }
         double value=sum/a.length;
        double[] b=new double[3];
        b[0]=value;b[1]=L;b[2]=S;
        return b;
    }//读取数据并返回一个数组，


}