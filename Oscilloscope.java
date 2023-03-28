package com.dataprocessingAPP;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Oscilloscope extends JFrame {
    public static JPanel panel1, panel1_1, panel1_2, panel1_3, panel2, panel2_1, panel2_2, panel2_3, panel3, panel3_1, panel3_2,panel3_3;
    public static JTextArea textArea1_0,textArea1_1, textArea1_2, textArea1_3, textArea2_0,textArea2_1, textArea2_2, textArea2_3;

    public static JButton button1, button2, button3, button4;
    Border etched = BorderFactory.createEtchedBorder();//设置边框类型

    Toolkit kit = Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize = kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth = screeSize.width;//获得屏幕宽
    int screenHeight = screeSize.height;//获得屏幕高

    public Oscilloscope(){
        setSize(screenWidth *7/8, screenHeight * 2 / 5);//设置框架大小
        setTitle("示波器的使用");
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

    private void addPanel1() {
        Border title1 = BorderFactory.createTitledBorder(etched, "电压");//设置面板1   设置边框
        panel1.setBorder(title1);
        panel1_1=new JPanel(new GridLayout(1,4));
        panel1_2=new JPanel(new GridLayout(1,3));

        textArea1_0=new JTextArea(10,4);
        textArea1_1=new JTextArea(10,4);
        textArea1_2=new JTextArea(10,4);
        textArea1_3=new JTextArea(10,4);

        addTextArea(textArea1_0,"信号发生器电压U/V",panel1_1);
        addTextArea(textArea1_1,"偏转灵敏度K(y)",panel1_1);
        addTextArea(textArea1_2,"波高 B/div",panel1_1);
        addTextArea(textArea1_3,"峰—峰值U(p-p)和U",panel1_1);

        button1=new JButton("数据计算");
        button2=new JButton("数据重置");
        button1.addActionListener((e -> calculatePanel1()));
        button2.addActionListener(e -> {
            textArea1_0.setText("");
            textArea1_1.setText("");
            textArea1_2.setText("");
            textArea1_3.setText("");
        });
        panel1_2.add(button1);panel1_2.add(button2);panel1_2.add(new JLabel(""));
        panel1.add(panel1_1,BorderLayout.CENTER);
        panel1.add(panel1_2,BorderLayout.SOUTH);

        
    }

    private void calculatePanel1() {
        double[] as = readTextArea(textArea1_1,panel1);
        double[] bs=readTextArea(textArea1_2,panel1);
        for (int i = 0; i < as.length; i++) {
           double c= as[i]*bs[i];
           double u=c/(2*1.414213);
           textArea1_3.append("U(p-p):"+String.format("%.4fv",c)+"U:"+String.format("%.4fV",u)+"\r\n");
        }

    }

    private void addPanel2(){

        Border title2 = BorderFactory.createTitledBorder(etched, "周期与频率");//设置面板1   设置边框
        panel2.setBorder(title2);
        panel2_1=new JPanel(new GridLayout(1,4));
        panel2_2=new JPanel(new GridLayout(1,3));

        textArea2_0=new JTextArea(10,4);
        textArea2_1=new JTextArea(10,4);
        textArea2_2=new JTextArea(10,4);
        textArea2_3=new JTextArea(10,4);

        addTextArea(textArea2_0,"信号发生器频率f/Hz",panel2_1);
        addTextArea(textArea2_1,"扫描速率K(x)/(μs/div)",panel2_1);
        addTextArea(textArea2_2,"1个周期的距离 A/div",panel2_1);
        addTextArea(textArea2_3,"周期T/s与频率f/Hz",panel2_1);

        button3=new JButton("数据计算");
        button4=new JButton("数据重置");
        button3.addActionListener((e -> calculatePanel2()));
        button4.addActionListener(e -> {
            textArea2_0.setText("");
            textArea2_1.setText("");
            textArea2_2.setText("");
            textArea2_3.setText("");
        });
        panel2_2.add(button3);panel2_2.add(button4);panel2_2.add(new JLabel(""));
        panel2.add(panel2_1,BorderLayout.CENTER);
        panel2.add(panel2_2,BorderLayout.SOUTH);

    }

    private void calculatePanel2() {
        double[] as = readTextArea(textArea2_1,panel2);
        double[] bs=readTextArea(textArea2_2,panel2);
        for (int i = 0; i < as.length; i++) {
            double t= (as[i]*bs[i])/1000000;
            double f=1/t;
            textArea2_3.append("T:"+String.format("%.4fs",t)+"f:"+String.format("%.4fHz",f)+"\r\n");
        }
    }

    private void addTextArea (JTextArea textArea,String name,JPanel panel){
        textArea.setText("");
        textArea.setEnabled(true);
        JScrollPane scrollPane=new JScrollPane(textArea);//a设置滑动框
        Border title0=BorderFactory.createTitledBorder(etched,name);//设置边框
        scrollPane.setBorder(title0);//设置边框
        panel.add(scrollPane);
    }
    private double[] readTextArea(JTextArea textArea,JPanel panel){
        String[] lines =textArea.getText().split("\\r?\\n");//读取文本数据
        double[] a=new double[lines.length];

        try {
            for (int i = 0; i < lines.length; i++) {
                a[i]= Double.parseDouble(lines[i]);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel,"输入数据有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        }
        return a;
    }//读取数据并返回一个数组，


}

