package com.dataprocessingAPP;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.DecimalFormat;

public class Measurement extends JFrame {
    public static JPanel panel1,panel1_1,panel1_2,panel2,panel2_1,panel2_2,panel2_2_1,panel2_2_2,panel3,panel3_1,panel3_2,panel4,panel4_1,panel4_2;
    public static JTextArea textArea1_1,textArea1_2,textArea1_3,textArea2_1,textArea2_2,textArea2_3,textArea3_1,textArea3_2,textArea3_3,textArea4_1,textArea4_2,textArea4_3,textArea4_4;
    public static JTextField textField1,textField2,textField3,textField4,textField5,textField6,
            textField7,textField8,textField9,textField10,textField11,textField12,textField13,textField14,textField15,textField16,textField17,textField18,textField19,textField20,textField21,textField22;

    public static JButton button1,button2,button3,button4,button5,button6,button7,button8;
    Border etched=BorderFactory.createEtchedBorder();//设置边框类型

    Toolkit kit=Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize=kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth=screeSize.width;//获得屏幕宽
    int screenHeight=screeSize.height;//获得屏幕高
    public Measurement(){
        //框架相关选择
        setSize(screenWidth*5/6,screenHeight*2/3);//设置框架大小
        setTitle("长度与体积的测量");
        setLocationByPlatform(true);//设置框架位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭响应
        setLayout(new GridLayout(1,4));//设置1行4列的网格布局

        //初始化面板1
        addPanel1();
        add(panel1);

        //初始化面板2
        addPanel2();
        add(panel2);

        //初始化面板3
        addPanel3();
        add(panel3);
        //初始化面板4
        addPanel4();
        add(panel4);

    }
    private void addPanel4() {
        panel4_1=new JPanel(new GridLayout(1,4));
        textArea4_1 = new JTextArea(10, 3);
        textArea4_2 = new JTextArea(10, 3);
        textArea4_3 = new JTextArea(10, 3);
        textArea4_4 = new JTextArea(10, 3);

        addTextArea(textArea4_1,"y1/mm",panel4_1);
        addTextArea(textArea4_2,"y2/mm",panel4_1);
        addTextArea(textArea4_3,"x1/mm",panel4_1);
        addTextArea(textArea4_4,"x2/mm",panel4_1);

        Border title3=BorderFactory.createTitledBorder(etched,"测量毛细管的内外直径d和D");//设置边框
        panel4_1.setBorder(title3);//设置边框
        panel4.add(panel4_1);

        Border title11=BorderFactory.createTitledBorder(etched,"参数输入");//设置边框
        panel4_2=new JPanel();
        panel4_2.setBorder(title11);
        panel4_2.setLayout(new GridLayout(7,2));
        JLabel label1=new JLabel("零点度数(mm):");
        textField17=new JTextField(6);
        textField17.setText("无");

        JLabel label2=new JLabel("分度值(mm):");
        textField18=new JTextField(6);

        JLabel label3=new JLabel("内直径d(mm):");
        textField19=new JTextField(6);
        JLabel label=new JLabel("d的不确定度");
        textField20=new JTextField(6);

        JLabel label4=new JLabel("外直径D(mm):");
        textField21=new JTextField(6);
        JLabel j=new JLabel("D的不确定度:");
        textField22=new JTextField(6);


        button7=new JButton("数据计算");
        button7.addActionListener((event)->{calculatePanel4();});//数据计算按钮响应事件

        button8=new JButton("数据重置");
        button8.addActionListener((event)->{
            textArea4_1.setText("");
            textArea4_2.setText("");
            textArea4_3.setText("");
            textArea4_4.setText("");
        });//数据重置按钮

        panel4_2.add(label1);panel4_2.add(textField17);

        panel4_2.add(label2);panel4_2.add(textField18);

        panel4_2.add(label3);panel4_2.add(textField19);

        panel4_2.add(label);panel4_2.add(textField20);

        panel4_2.add(label4);panel4_2.add(textField21);

        panel4_2.add(j);panel4_2.add(textField22);
        panel4_2.add(button7);panel4_2.add(button8);

        panel4.add(panel4_2);


    }
    private void calculatePanel4(){
        double[] d ;double[] D;
        String[] y1s =textArea4_1.getText().split("\\r?\\n");//读取文本数据
        String[] y2s =textArea4_2.getText().split("\\r?\\n");//读取文本数据
        String[] x1s =textArea4_3.getText().split("\\r?\\n");//读取文本数据
        String[] x2s =textArea4_4.getText().split("\\r?\\n");//读取文本数据

        int len=Math.min(y1s.length,y2s.length);int l=Math.min(x1s.length,x2s.length);
        d=new double[len];
        D=new double[l];
        for (int i = 0; i <len ; i++) {
            try {
                d[i]=Math.abs(Double.parseDouble(y1s[i])-Double.parseDouble(y2s[i]));
                D[i]=Math.abs(Double.parseDouble(x1s[i])-Double.parseDouble(x2s[i]));
            } catch (NumberFormatException e) {
                JOptionPane.showConfirmDialog(panel4_2,"输入数据有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                //可以在这做一个对话框
            }
        }
        double[] dd=jisuan(d,textField18);
        textField19.setText(String.format("%.8fmm",dd[0]));
        textField20.setText(String.format("%.8fmm",dd[1]));
        double[] ddd=jisuan(D,textField18);
        textField21.setText(String.format("%.8fmm",ddd[0]));
        textField22.setText(String.format("%.8fmm",ddd[1]));
    }

    private double[] jisuan(double[] d,JTextField textField) {
       double adj,dsum=0,dsuma=0,ua,uc,ub1=0,ub2,ub = 0;
        for (double v : d) {
            dsum = dsum + v;
        }
        adj=dsum/d.length;
        for (double v : d) {
            dsuma = dsuma + Math.pow((v - adj), 2);
        }
        ua=Math.sqrt(dsuma/(d.length*(d.length-1)));
        try {
            ub1 = Double.parseDouble(textField.getText());//计算B类
            ub2=1.732;
            ub=ub1/ub2;
        }
        catch (NumberFormatException e) {
            int selection=JOptionPane.showConfirmDialog(panel4_2,"请输入分度值(单击ok将默认0.01)","参数不足",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            if (selection==JOptionPane.OK_OPTION){
                textField.setText("0.01");}//应该弹出一个警告框
        }uc= ((Math.hypot(ua,ub)));//计算C类
        double[] a={adj,uc};
        return a  ;
    }

    private void addPanel3() {
        panel3_1=new JPanel(new GridLayout(1,3));
        textArea3_1 = new JTextArea(10, 5);
        textArea3_2 = new JTextArea(10, 5);
        textArea3_3 = new JTextArea(10, 5);
        addTextArea(textArea3_1,"l(长)/cm",panel3_1);
        addTextArea(textArea3_2,"a(宽)/cm",panel3_1);
        addTextArea(textArea3_3,"h(高)/cm",panel3_1);

        Border title31=BorderFactory.createTitledBorder(etched,"测量长方体的体积");//设置边框
        panel3_1.setBorder(title31);//设置边框
        panel3.add(panel3_1);

        //初始化第3个大面板的（2，1）位置
        Border title4=BorderFactory.createTitledBorder(etched,"参数输入");//设置边框
        panel3_2=new JPanel();
        panel3_2.setBorder(title4);
        panel3_2.setLayout(new GridLayout(7,2));
        JLabel label1=new JLabel("零点度数(cm):");
        textField13=new JTextField(6);

        JLabel label2=new JLabel("分度值(cm):");
        textField14=new JTextField(6);

        JLabel label3=new JLabel("长方体体积V(cm^3):");
        textField15=new JTextField(6);

        JLabel label4=new JLabel("不确定度(cm^3):");
        textField16=new JTextField(6);

        button5=new JButton("数据计算");
        button5.addActionListener((event)->{calculatePanel3();});//数据计算按钮响应事件

        button6=new JButton("数据重置");
        button6.addActionListener((event)->{
            textArea3_1.setText("");
            textArea3_2.setText("");
            textArea3_3.setText("");
        });//数据重置按钮

        panel3_2.add(label1);panel3_2.add(textField13);

        panel3_2.add(label2);panel3_2.add(textField14);

        panel3_2.add(label3);panel3_2.add(textField15);

        panel3_2.add(label4);panel3_2.add(textField16);

        panel3_2.add(button5);panel3_2.add(button6);

        panel3.add(panel3_2);
    }
    private void calculatePanel3(){
        double V,uc;
        double[] l=calculate(textArea3_1,panel3_2,textField13,textField14);
        double[] a=calculate(textArea3_2,panel3_2,textField13,textField14);
        double[] h=calculate(textArea3_3,panel3_2,textField13,textField14);
        V=h[0]*a[0]*l[0];
        uc=Math.sqrt(Math.pow(l[0]*a[0]*h[1],2)+Math.pow(l[0]*a[1]*h[0],2)+Math.pow(l[1]*a[0]*h[0],2));
        textField15.setText(String.format("%.8f",V));
        textField16.setText(String.format("%.8f",uc));
    }
    private void addPanel2() {
        //初始化面板2的(1,1)位置
        panel2_1=new JPanel(new GridLayout(1,3));
        textArea2_1 = new JTextArea(10, 5);
        textArea2_2 = new JTextArea(10, 5);
        textArea2_3 = new JTextArea(10, 5);

        addTextArea(textArea2_1,"D(直径)/mm",panel2_1);
        addTextArea(textArea2_2,"h(高)/cm",panel2_1);
        addTextArea(textArea2_3,"d(直径)/cm",panel2_1);
        Border title3=BorderFactory.createTitledBorder(etched,"测量小钢球/圆柱体的体积");//设置边框
        panel2_1.setBorder(title3);//设置边框
        panel2.add(panel2_1);
        //初始化面板2的(2,1)位置
        panel2_2=new JPanel(new GridLayout(1,2));
        Border title=BorderFactory.createTitledBorder(etched,"参数输入");//设置边框
        panel2_2.setBorder(title);
        panel2_2_1=new JPanel(new GridLayout(9,1));
        panel2_2_2=new JPanel(new GridLayout(9,1));


        JLabel label2_1=new JLabel("零点度数(mm):");
        textField5=new JTextField(5);
        panel2_2_1.add(label2_1);panel2_2_1.add(textField5);

        textField6=new JTextField(5);
        JLabel label2_2=new JLabel("分度值(mm):");
        panel2_2_1.add(label2_2);panel2_2_1.add(textField6);

        JLabel label5=new JLabel("钢球体积V(cm^3):");
        textField7=new JTextField(5);
        panel2_2_1.add(label5);panel2_2_1.add(textField7);

        JLabel label6=new JLabel("不确定度(cm^3):");
        textField8=new JTextField(5);
        panel2_2_1.add(label6);panel2_2_1.add(textField8);

        button3=new JButton("数据计算");
        button3.addActionListener((event)->{calculatePanel2_1();});//数值计算按钮
        Border title41=BorderFactory.createTitledBorder(etched,"小钢球");//设置边框
        panel2_2_1.setBorder(title41);
        panel2_2_1.add(button3);

        textField9=new JTextField(5);
        JLabel label2_3=new JLabel("零点度数(cm):");
        panel2_2_2.add(label2_3);panel2_2_2.add(textField9);

        textField10=new JTextField(5);
        JLabel label2_4=new JLabel("分度值(cm):");
        panel2_2_2.add(label2_4);panel2_2_2.add(textField10);

        textField11=new JTextField(5);
        JLabel labelyz=new JLabel("圆柱体积(cm^3):");
        panel2_2_2.add(labelyz);panel2_2_2.add(textField11);

        textField12=new JTextField(5);
        JLabel labelc=new JLabel("不确定度(cm^3):");
        panel2_2_2.add(labelc);panel2_2_2.add(textField12);

        button4=new JButton("数据计算");
        button4.addActionListener((event)->{calculatePanel2_2();});//数据计算按钮
        panel2_2_2.add(button4);
        Border title411=BorderFactory.createTitledBorder(etched,"圆柱体");//设置边框
        panel2_2_2.setBorder(title411);
        panel2_2.add(panel2_2_1);panel2_2.add(panel2_2_2);
        panel2.add(panel2_2);



    }
    private void calculatePanel2_1(){
        double V,uc,pi=3.1415926;
        double[] a=calculate(textArea2_1,panel2_2_1,textField5,textField6);
        V=pi*Math.pow(a[0],3)/6;
        uc=3*a[1]*V/a[0];
        textField7.setText(String.format("%.6f",V*0.001));
        textField8.setText(String.format("%.6f",uc*0.001));
    }
    private void calculatePanel2_2(){
        double V,uc,pi=3.1415926;
        double[] h=calculate(textArea2_2,panel2_2_2,textField9,textField10);
        double[] d=calculate(textArea2_3,panel2_2_2,textField9,textField10);
        V=(pi/4)*h[0]*Math.pow(d[0],2);
        uc=Math.hypot(pi*Math.pow(d[0],2)*h[1]/4,pi*d[0]*h[0]*d[1]/2);
        textField11.setText(String.format("%.6f",V));
        textField12.setText(String.format("%.6f",uc));
    }
    private void addPanel1() {
        panel1=new JPanel(new GridLayout(2,1));panel2=new JPanel(new GridLayout(2,1));panel3=new JPanel(new GridLayout(2,1));
        panel4=new JPanel(new GridLayout(2,1));//四个大面板
        //初始化第一个大面板的(1，1）位置
        panel1_1=new JPanel(new GridLayout(1,3));
        textArea1_1 = new JTextArea(10, 5);
        textArea1_2 = new JTextArea(10, 5);
        textArea1_3 = new JTextArea(10, 5);

        addTextArea(textArea1_1,"H(高)/cm",panel1_1);

        addTextArea(textArea1_2,"D1(内径)/cm",panel1_1);

        addTextArea(textArea1_3,"D2(外径)/cm",panel1_1);


        Border title3=BorderFactory.createTitledBorder(etched,"测量圆管的体积");//设置边框
        panel1_1.setBorder(title3);//设置边框
        panel1.add(panel1_1);

        //初始化第二个大面板的（2，1）位置
        Border title4=BorderFactory.createTitledBorder(etched,"参数输入");//设置边框
        panel1_2=new JPanel();
        panel1_2.setBorder(title4);
        panel1_2.setLayout(new GridLayout(7,2));
        JLabel label1=new JLabel("零点度数(cm):");
        textField1=new JTextField(6);

        JLabel label2=new JLabel("分度值(cm):");
        textField2=new JTextField(6);

        JLabel label3=new JLabel("圆管体积V(cm^3):");
        textField3=new JTextField(6);

        JLabel label4=new JLabel("V的不确定度(cm^3):");
        textField4=new JTextField(6);

        button1=new JButton("数据计算");
        button1.addActionListener((event)->{calculatePanel1();});//数据计算按钮响应事件

        button2=new JButton("数据重置");
        button2.addActionListener((event)->{
            textArea1_1.setText("");
            textArea1_2.setText("");
            textArea1_3.setText("");
        });//数据重置按钮

        panel1_2.add(label1);panel1_2.add(textField1);

        panel1_2.add(label2);panel1_2.add(textField2);

        panel1_2.add(label3);panel1_2.add(textField3);

        panel1_2.add(label4);panel1_2.add(textField4);

        panel1_2.add(button1);panel1_2.add(button2);

        panel1.add(panel1_2);

    }
    private void calculatePanel1(){
        double V,uc,pi=3.1415926;
        double[] a=calculate(textArea1_1,panel1_2,textField1,textField2);
        double[] b=calculate(textArea1_2,panel1_2,textField1,textField2);
        double[] c=calculate(textArea1_3,panel1_2,textField1,textField2);
        V=a[0]*(pi/4)*Math.abs(Math.pow(c[0],2)-Math.pow(b[0],2));
        uc=Math.sqrt(Math.pow((pi/2)*a[0]*b[0]*b[1],2)+Math.pow((pi/2)*a[0]*c[0]*c[1],2)+a[1]*V/a[0]);
        textField3.setText(String.format("%.8f",V));
        textField4.setText(String.format("%.8f",uc));


    }
    private void addTextArea(JTextArea textArea,String name,JPanel panel){
        textArea.setText("");
        textArea.setEnabled(true);
        JScrollPane scrollPane=new JScrollPane(textArea);//a设置滑动框
        Border title0=BorderFactory.createTitledBorder(etched,name);//设置边框
        scrollPane.setBorder(title0);//设置边框
        panel.add(scrollPane);

    }
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
            int selection=JOptionPane.showConfirmDialog(panel,"请输入分度值(单击ok将默认0.02)","参数不足",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            if (selection==JOptionPane.OK_OPTION){
                textField2.setText("0.02");}//应该弹出一个警告框
        }
        double[] u={adj,uc};
        return u;
    }


}
