package com.dataprocessingAPP;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Bridge extends JFrame {
    public static JPanel panel1,panel2,panel3,panel3_1,panel3_2,panel3_2_1;
    public static JTextArea textArea1_1,textArea1_2,textArea1_3,textArea1_4,textArea1_5,textArea1_6,textArea1_7,textArea1_8,textArea2_1,textArea2_2,textArea2_3,textArea2_4,textArea2_5
            ,textArea2_6,textArea2_7,textArea2_8,textArea3;
    public static JTextField textField1,textField2,textField3,textField4,textField5;

    public static JButton button1,button2;
    Border etched=BorderFactory.createEtchedBorder();//设置边框类型

    Toolkit kit=Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize=kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth=screeSize.width;//获得屏幕宽
    int screenHeight=screeSize.height;//获得屏幕高
    public Bridge(){
        setSize(screenWidth /2, screenHeight * 4/ 9);//设置框架大小
        setTitle("用惠斯通电桥测电阻");
        setLocationByPlatform(true);//设置框架位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭响应
        setLayout(new GridLayout(3, 1));//设置3行1列的主面板网格布局

        panel1 = new JPanel(new GridLayout(1,8));
        panel2 = new JPanel(new GridLayout(1,8));
        panel3=new JPanel(new GridLayout(1,2));

        addPanel1();
        add(panel1);

        addPanel2();
        add(panel2);

        addPanel3();
        add(panel3);
    }

    private void addPanel3() {
        panel3_1=new JPanel(new GridLayout(5,2));
        panel3_2=new JPanel(new BorderLayout());

        Border title1 = BorderFactory.createTitledBorder(etched, "参数");//设置面板1   设置边框
        panel3_1.setBorder(title1);

        Border title2 = BorderFactory.createTitledBorder(etched, "结果");//设置面板1   设置边框
        panel3_2.setBorder(title2);


        JLabel label1=new JLabel("Δn(div)=",JLabel.CENTER);
        textField1=new JTextField("0.2",6);
        panel3_1.add(label1);panel3_1.add(textField1);

        //JLabel label2=new JLabel("n(格)=",JLabel.CENTER);
        //textField2=new JTextField("5.0",6);
        //panel3_1.add(label2);panel3_1.add(textField2);//n是由使用者输入的实验数据

        JLabel label3=new JLabel("电阻箱等级a2=",JLabel.CENTER);
        textField3=new JTextField(6);
        panel3_1.add(label3);panel3_1.add(textField3);

        JLabel label4=new JLabel("电阻箱等级a3=",JLabel.CENTER);
        textField4=new JTextField(6);
        panel3_1.add(label4);panel3_1.add(textField4);

        JLabel label5=new JLabel("电阻箱等级a4=",JLabel.CENTER);
        textField5=new JTextField(6);
        panel3_1.add(label5);panel3_1.add(textField5);

        textArea3=new JTextArea(10,5);
        panel3_2.add(textArea3,BorderLayout.CENTER);
        panel3_2_1=new JPanel(new GridLayout(1,2));
        button1=new JButton("数据计算");button2=new JButton("数据重置");
        button1.addActionListener(e-> calculatePanel1());
        button2.addActionListener(e->{
            textArea1_1.setText("");
            textArea1_2.setText("");
            textArea1_3.setText("");
            textArea1_4.setText("");
            textArea1_5.setText("");
            textArea1_6.setText("");
            textArea1_7.setText("");
            textArea1_8.setText("");
            textArea2_1.setText("");
            textArea2_2.setText("");
            textArea2_3.setText("");
            textArea2_4.setText("");
            textArea2_5.setText("");
            textArea2_6.setText("");
            textArea2_7.setText("");
            textArea2_8.setText("");
        });
        panel3_2_1.add(button1);
        panel3_2_1.add(button2);
        panel3_2.add(panel3_2_1,BorderLayout.SOUTH);

        panel3.add(panel3_1);panel3.add(panel3_2);



    }

    private void calculatePanel1() {
        textArea1_7.setText("");
        textArea1_8.setText("");
        textArea2_7.setText("");
        textArea2_8.setText("");
        calculatePanel(textArea1_1,textArea1_2,textArea1_3,textArea1_4,textArea1_5,textArea1_6,textArea1_7,textArea1_8,panel1);
        calculatePanel(textArea2_1,textArea2_2,textArea2_3,textArea2_4,textArea2_5,textArea2_6,textArea2_7,textArea2_8,panel2);

        double[] rx=read1(textArea1_7);
        double[] rx2=read1(textArea2_7);
        double[] s=read1(textArea1_8);

        double a2=readtextField(textField3,panel3_1);
        double a3=readtextField(textField4,panel3_1);
        double a4=readtextField(textField5,panel3_1);
        double dtn=readtextField(textField1,panel3_1);
        double ub1=Math.pow(a2/(100*1.732),2)+Math.pow(a3/(100*1.732),2)+Math.pow(a4/(100*1.732),2);
        double ub2=Math.pow(ub1,0.5);
        textArea3.setText("");
        for (int i = 0; i < rx.length; i++) {
            double rxx=Math.pow(rx[i]*rx2[i],0.5);
            double ub3=dtn*rxx/(s[i]*1.732);
            double uc=Math.hypot(rxx*ub2,ub3);
            textArea3.append("Rx"+i+"的合成不确定度μc(Rx)="+String.format("%.4f",uc)+"\r\n"+"测量结果为:Rx="+rxx+"±"+String.format("%.4f",uc)+"(Ω)\r\n");
        }
    }

    private double readtextField(JTextField textField,JPanel panel) {
        double a=1;
        try {
            a= Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel,"请输入等级参数","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        }
        return a;
    }

    private double[] read1(JTextArea textArea) {
        double[] a;
        String[] lines = textArea.getText().split("\\r?\\n");//读取文本数据
        a = new double[lines.length];
            for (int i = 0; i < lines.length; i++) {
                a[i] = Double.parseDouble(lines[i]);
            }
            return a;
        //读取数据并返回一个数组
    }

    private void calculatePanel(JTextArea t1,JTextArea t2,JTextArea t3,JTextArea t4,JTextArea t5,JTextArea t6,JTextArea t7,JTextArea t8,JPanel panel) {
       double[] R2= readTextArea(t1,panel,"R2/Ω");
        double[] R3= readTextArea(t2,panel,"R3/Ω");
        double[] R4= readTextArea(t3,panel,"R4/Ω");
        double[] n= readTextArea(t4,panel,"n/格");
        double[] R41= readTextArea(t5,panel,"R'(4)/Ω");
        double[] R42= readTextArea(t6,panel,"R''(4)/Ω");

        for (int i = 0; i < Math.min(R2.length,R3.length); i++) {
            double rx=(R2[i]/R3[i])*R4[i];
            double s=n[i]*R4[i]*2/(Math.abs(R41[i]-R42[i]));
            t7.append(String.format("%.4f",rx)+"\r\n");
            t8.append(String.format("%.4f",s)+"\r\n");
        }

    }

    private void addPanel1() {
        Border title1 = BorderFactory.createTitledBorder(etched, "换臂前");//设置面板1   设置边框
        panel1.setBorder(title1);
        textArea1_1=new JTextArea(4,2);
        textArea1_2=new JTextArea(4,2);
        textArea1_3=new JTextArea(4,2);
        textArea1_4=new JTextArea(4,2);
        textArea1_5=new JTextArea(4,2);
        textArea1_6=new JTextArea(4,2);
        textArea1_7=new JTextArea(4,2);
        textArea1_8=new JTextArea(4,2);


        addTextArea(textArea1_1,"R2/Ω",panel1);
        addTextArea(textArea1_2,"R3/Ω",panel1);
        addTextArea(textArea1_3,"R4/Ω",panel1);
        addTextArea(textArea1_4,"n/格",panel1);
        addTextArea(textArea1_5,"R'(4)/Ω",panel1);
        addTextArea(textArea1_6,"R''(4)/Ω",panel1);
        addTextArea(textArea1_7,"结果R'x/Ω",panel1);
        addTextArea(textArea1_8,"结果S/格" ,panel1);


    }
    private void addPanel2(){
        Border title1 = BorderFactory.createTitledBorder(etched, "换臂后");//设置面板1   设置边框
        panel2.setBorder(title1);


        textArea2_1=new JTextArea(4,2);
        textArea2_2=new JTextArea(4,2);
        textArea2_3=new JTextArea(4,2);
        textArea2_4=new JTextArea(4,2);
        textArea2_5=new JTextArea(4,2);
        textArea2_6=new JTextArea(4,2);
        textArea2_7=new JTextArea(4,2);
        textArea2_8=new JTextArea(4,2);


        addTextArea(textArea2_1,"R2/Ω",panel2);
        addTextArea(textArea2_2,"R3/Ω",panel2);
        addTextArea(textArea2_3,"R4/Ω",panel2);
        addTextArea(textArea2_4,"n/格",panel2);
        addTextArea(textArea2_5,"R'(4)/Ω",panel2);
        addTextArea(textArea2_6,"R''(4)/Ω",panel2);
        addTextArea(textArea2_7,"结果R''x/Ω",panel2);
        addTextArea(textArea2_8,"结果S/格" ,panel2);

    }

    private void addTextArea(JTextArea textArea, String name, JPanel panel) {
        textArea.setText("");
        textArea.setEnabled(true);
        JScrollPane scrollPane=new JScrollPane(textArea);//a设置滑动框
        Border title0=BorderFactory.createTitledBorder(etched,name);//设置边框
        scrollPane.setBorder(title0);//设置边框
        panel.add(scrollPane);
    }
    private double[] readTextArea(JTextArea textArea,JPanel panel,String s){
        double[] a;
        String[] lines =textArea.getText().split("\\r?\\n");//读取文本数据
        a=new double[lines.length];
        try {
            for (int i = 0; i < lines.length; i++) {
                a[i]= Double.parseDouble(lines[i]);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel,s+"数据输入有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        }
        return a;
    }//读取数据并返回一个数组
}
