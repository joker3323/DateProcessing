package com.dataprocessingAPP;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Density extends JFrame {
    public static JPanel panel1, panel1_1, panel1_2, panel2, panel2_1, panel2_2, panel3, panel3_1, panel3_2;
    public static JTextArea textArea1_1, textArea1_2, textArea2_1, textArea2_2, textArea2_3, textArea3_1, textArea3_2;
    public static JTextField textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8,textField9,textField10,textField11,textField12,textField13,textField14,textField15;
    public static JButton button1, button2, button3, button4, button5, button6;
    Border etched = BorderFactory.createEtchedBorder();//设置边框类型
    Toolkit kit=Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize=kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth=screeSize.width;//获得屏幕宽
    int screenHeight=screeSize.height;//获得屏幕高
    public Density(){
        setSize(screenWidth*3/5,screenHeight*3/5);//设置框架大小
        setTitle("质量与密度的测量");
        setLocationByPlatform(true);//设置框架位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭响应
        setLayout(new GridLayout(1,3));//设置1行3列的网格布局
        panel1=new JPanel(new GridLayout(2,1));
        panel2=new JPanel(new GridLayout(2,1));
        panel3=new JPanel(new GridLayout(2,1));


        //初始化面板1
        addPanel1();
        add(panel1);

        //初始化面板2
        addPanel2();
        add(panel2);

        //初始化面板3
        addPanel3();
        add(panel3);

    }

    private void addPanel1() {
        //初始化面板1，1位置
        panel1_1=new JPanel(new GridLayout(1,2));
        textArea1_1=new JTextArea(10,5);
        textArea1_2=new JTextArea(10,5);

        util.addTextArea(textArea1_1,"d(边长)/cm",panel1_1);
        util.addTextArea(textArea1_2,"M(质量)/g",panel1_1);

        Border title=BorderFactory.createTitledBorder(etched,"测量立方体的密度");//设置边框
        panel1_1.setBorder(title);
        panel1.add(panel1_1);

        //初始化面板1，2位置,测量立方体密度参数
        Border title0=BorderFactory.createTitledBorder(etched,"参数输入");//设置边框
        panel1_2=new JPanel(new GridLayout(8,2));//设置为网格布局，到时候看效果在调整
        panel1_2.setBorder(title0);

        JLabel label2=new JLabel("零点度数(cm):");
        textField2=new JTextField(6);
        textField2.setText("0.00");
        panel1_2.add(label2);panel1_2.add(textField2);

        JLabel label3=new JLabel("测量仪器分度值(cm):");
        textField3=new JTextField(6);
        textField3.setText("0.002");
        panel1_2.add(label3);panel1_2.add(textField3);

        JLabel label4=new JLabel("物理天平分度值(g):");
        textField4=new JTextField(6);
        textField4.setText("0.02");
        panel1_2.add(label4);panel1_2.add(textField4);

        JLabel label5=new JLabel("密度(g/cm^3):");
        textField5=new JTextField(6);
        panel1_2.add(label5);panel1_2.add(textField5);

        JLabel label6=new JLabel("不确定度(g/cm^3):");
        textField6=new JTextField(6);
        panel1_2.add(label6);panel1_2.add(textField6);

        button1=new JButton("数据计算");button2=new JButton("数值重置");
        panel1_2.add(button1);panel1_2.add(button2);
        button1.addActionListener((event)->{calculatePanel1();});
        button2.addActionListener((event)->{
            textArea1_1.setText("");
            textArea1_2.setText("");
        });
        panel1.add(panel1_2);
    }
    public void calculatePanel1(){
        double p,uc;
        double value0=0.00;
        double value=0.002;
        double tpVilue=0.02;
        try {
            value0= Double.parseDouble(textField2.getText());
            value= Double.parseDouble(textField3.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel1_2,"请输入立方体边长测量仪器零点读数和分度值，零点读数默认为0.00，分度值默认为0.002cm","参数有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

        }
        try {
            tpVilue= Double.parseDouble(textField4.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel1_2,"请输入物理天平分度值，默认为0.02g","参数有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

        }
        double[] d=util.calculate(util.readTextArea(textArea1_1,panel1_2),value0,value);
        double[] m=util.calculate(util.readTextArea(textArea1_2,panel1_2),value0,tpVilue);
        p=m[0]/Math.pow(d[0],3);
        uc=p*Math.hypot(m[1]/m[0],3*d[1]/d[0]);
        textField5.setText(String.format("%.8f",p*1000));
        textField6.setText(String.format("%.8f",uc*1000));

    }
    private void addPanel2() {
        //初始化第一个面板1，1
        panel2_1=new JPanel(new GridLayout(1,3));
        textArea2_1 = new JTextArea(10, 5);
        textArea2_2 = new JTextArea(10, 5);
        textArea2_3 = new JTextArea(10, 5);

        util.addTextArea(textArea2_1,"D(直径)/cm",panel2_1);

        util.addTextArea(textArea2_2,"h(高)/cm",panel2_1);

        util.addTextArea(textArea2_3,"M(质量)/g",panel2_1);//

        Border title3=BorderFactory.createTitledBorder(etched,"测量圆柱体的密度");//设置边框
        panel2_1.setBorder(title3);//设置边框
        panel2.add(panel2_1);

        //初始化面板2，2位置
        Border title0=BorderFactory.createTitledBorder(etched,"参数输入");//设置边框
        panel2_2=new JPanel(new GridLayout(8,2));//设置为网格布局，到时候看效果在调整
        panel2_2.setBorder(title0);

        JLabel label2=new JLabel("零点度数(cm):");
        textField7=new JTextField(6);
        textField7.setText("0.00");
        panel2_2.add(label2);panel2_2.add(textField7);

        JLabel label3=new JLabel("游标卡尺分度值(cm):");
        textField8=new JTextField(6);
        textField8.setText("0.002");
        panel2_2.add(label3);panel2_2.add(textField8);

        JLabel label4=new JLabel("物理天平分度值(g):");
        textField9=new JTextField(6);
        textField9.setText("0.02");
        panel2_2.add(label4);panel2_2.add(textField9);

        JLabel label5=new JLabel("密度(g/cm^3):");
        textField10=new JTextField(6);
        panel2_2.add(label5);panel2_2.add(textField10);

        JLabel label6=new JLabel("不确定度(g/cm^3):");
        textField11=new JTextField(6);
        panel2_2.add(label6);panel2_2.add(textField11);

        button3=new JButton("数据计算");button4=new JButton("数值重置");
        panel2_2.add(button3);panel2_2.add(button4);
        button3.addActionListener((event)->{calculatePanel2();});
        button4.addActionListener((event)->{
            textArea2_1.setText("");
            textArea2_2.setText("");
            textArea2_3.setText("");
        });
        panel2.add(panel2_2);
    }
    public void calculatePanel2(){
        double p,uc,pi=Math.PI;
        double value0=0.00;
        double value=0.002;
        double tpVilue=0.02;
        try {
            value0= Double.parseDouble(textField7.getText());
            value= Double.parseDouble(textField8.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel1_2,"请输入测量仪器零点读数和分度值，零点读数默认为0.00，分度值默认为0.002cm","参数有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

        }
        try {
            tpVilue= Double.parseDouble(textField9.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel1_2,"请输入物理天平分度值，分度值默认为0.02g","参数有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

        }
        double[] d=util.calculate(util.readTextArea(textArea2_1,panel2_2),value0,value);
        double[] h=util.calculate(util.readTextArea(textArea2_2,panel2_2),value0,value);
        double[] m=util.calculate(util.readTextArea(textArea2_3,panel2_2),value0,tpVilue);
       p=4*m[0]/(pi*Math.pow(d[0],2)*h[0]);
       uc=p*Math.sqrt(Math.pow(m[1]/m[0],2)+Math.pow(2*d[1]/d[0],2)+Math.pow(h[1]/h[0],2));
        textField10.setText(String.format("%.8f",p*1000));//克每立方厘米化为千克没立方米
        textField11.setText(String.format("%.8f",uc*1000));

    }
    private void addPanel3() {
        panel3_1=new JPanel(new GridLayout(1,2));
        textArea3_1=new JTextArea(10,5);
        textArea3_2=new JTextArea(10,5);

        util.addTextArea(textArea3_1,"m(固体质量)/g",panel3_1);
        util.addTextArea(textArea3_2,"m1(悬浮质量)/g",panel3_1);

        Border title=BorderFactory.createTitledBorder(etched,"流体静力称衡法测定金属块密度");//设置边框
        panel3_1.setBorder(title);
        panel3.add(panel3_1);

        //初始化面板1，2位置
        panel3_2=new JPanel(new GridLayout(8,2));//设置为网格布局，到时候看效果在调整
        //
        Border title0=BorderFactory.createTitledBorder(etched,"参数输入");//设置
        panel3_2.setBorder(title0);

        JLabel label200=new JLabel("零点度数(cm):");
        textField14=new JTextField(6);
        textField14.setText("0.00");
        panel3_2.add(label200);panel3_2.add(textField14);

             JLabel label1=new JLabel("水的密度(kg/m^3):");
             textField1=new JTextField(6);
             textField1.setText("1000");
        panel3_2.add(label1);panel3_2.add(textField1);

        JLabel label2=new JLabel("物理天平分度值(g):");
        textField12=new JTextField(6);
        textField12.setText("0.02");
        panel3_2.add(label2);panel3_2.add(textField12);


        JLabel label3=new JLabel("密度(g/cm^3):");
        textField13=new JTextField(6);
        panel3_2.add(label3);panel3_2.add(textField13);

        JLabel label4=new JLabel("不确定度(g/cm^3):");
        textField15=new JTextField(6);
        panel3_2.add(label4);panel3_2.add(textField15);


        button5=new JButton("数据计算");button6=new JButton("数值重置");
        panel3_2.add(button5);panel3_2.add(button6);
        button5.addActionListener((event)->{calculatePanel3();});
        button6.addActionListener((event)->{
            textArea3_1.setText("");
            textArea3_2.setText("");
        });
        panel3.add(panel3_2);
    }
    public void calculatePanel3(){
        double p,uc;
        double tpVilue=0.02;
        try {
            tpVilue= Double.parseDouble(textField9.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel1_2,"请输入物理天平分度值，分度值默认为0.02g","参数有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

        }
        double[] m=util.calculate(util.readTextArea(textArea3_1,panel3_2),0.00,tpVilue);
        double[] m1=util.calculate(util.readTextArea(textArea3_2,panel3_2),0.00,tpVilue);
        p=(m[0]*Double.parseDouble(textField1.getText())/(m[0]-m1[0]))/1000;
        uc=Math.sqrt(Math.pow(m[1]/m[0],2)+(Math.pow(m[1],2)+Math.pow(m1[1],2))/(m[0]-m1[0]));
        textField13.setText(String.format("%.8f",p));
        textField15.setText(String.format("%.8f",uc));

    }
}
