package com.dataprocessingAPP;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ElasticModulus extends JFrame {
    public static JPanel panel1,panel1_1,panel1_2,panel1_3,panel2,panel2_1,panel2_2;
    public static JTextField textField1,textField2,textField3,textField4,textField5,textField6;
    public static JTextArea textArea0,textArea1,textArea2,textArea3,textArea4,textArea5,textArea6;
    public static JButton button1,button2;

    Border etched = BorderFactory.createEtchedBorder();//设置边框类型
    Toolkit kit=Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize=kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth=screeSize.width;//获得屏幕宽
    int screenHeight=screeSize.height;//获得屏幕高
    public ElasticModulus(){
        setSize(screenWidth*3/5,screenHeight*2/3);//设置框架大小
        setTitle("用拉伸法测定弹性模量");
        setLocationByPlatform(true);//设置框架位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭响应
        setLayout(new GridLayout(2,1));//设置2行1列的网格布局
        panel1=new JPanel(new GridLayout(1,3));
        panel2=new JPanel(new GridLayout(1,2));

        //初始化面板1
        addPanel1();
        add(panel1);

        //初始化面板2
        addPanel2();
        add(panel2);
    }
private void addPanel1(){
        panel1_1=new JPanel(new GridLayout(1,2));
        panel1_2=new JPanel(new GridLayout(1,2));
        panel1_3=new JPanel(new GridLayout(1,2));

        textArea0=new JTextArea(10,5);
        textArea1=new JTextArea(10,5);
        textArea2=new JTextArea(10,5);
        textArea3=new JTextArea(10,5);
        textArea4=new JTextArea(10,5);
        textArea5=new JTextArea(10,5);


        addTextArea(textArea0,"金属丝直径d/mm",panel1_1);
        addTextArea(textArea1,"i\t\t\t\t\t\t\t砝码m/kg",panel1_1);
        addTextArea(textArea2,"读数S（i）/cm",panel1_2);
        addTextArea(textArea3,"读数S'（i）/cm",panel1_2);
        addTextArea(textArea4,"平均值S（i）/cm",panel1_3);
        addTextArea(textArea5,"逐差值y（i）/cm",panel1_3);
        panel1.add(panel1_1);panel1.add(panel1_2);panel1.add(panel1_3);
    for (int j = 0; j < 15; j++) {
        textArea1.append((j+1)+"\t"+(j)+"\r\n");
    }
    textArea1.setEnabled(false);
}
    private void addPanel2() {
        panel2_1=new JPanel(new GridLayout(7,2));
        textArea6=new JTextArea(10,5);
        textArea6.setText("数据报道：\r\n");
        textArea6.setEnabled(false);
        panel2_2=new JPanel(new GridLayout(1,1));
        panel2_2.add(textArea6);
        JLabel label1=new JLabel("千分尺零点读数（mm）:",JLabel.RIGHT);
        textField1=new JTextField(6);
        textField1.setText("0.00");
        panel2_1.add(label1);panel2_1.add(textField1);

        JLabel label2=new JLabel("千分尺分度值（mm）:",JLabel.RIGHT);
        textField2=new JTextField(6);
        textField2.setText("0.02");
        panel2_1.add(label2);panel2_1.add(textField2);

        JLabel label3=new JLabel("米尺分度值（cm）:",JLabel.RIGHT);
        textField3=new JTextField(6);
        textField3.setText("0.1");
        panel2_1.add(label3);panel2_1.add(textField3);

        JLabel label4=new JLabel("后尖足到两前尖足距离D1(cm):",JLabel.RIGHT);
        textField4=new JTextField(6);
        panel2_1.add(label4);panel2_1.add(textField4);

        JLabel label5=new JLabel("光杠杠镜面到标尺面距离D2(cm):",JLabel.RIGHT);
        textField5=new JTextField(6);
        panel2_1.add(label5);panel2_1.add(textField5);

        JLabel label6=new JLabel("弹性金属丝长度l(cm):",JLabel.RIGHT);
        textField6=new JTextField(6);
        panel2_1.add(label6);panel2_1.add(textField6);

        button1=new JButton("数据计算");button2=new JButton("数据重置");
        button1.addActionListener((event)->{
            textArea4.setText("");
            textArea5.setText("");
            textArea6.setText("");
            calculate2();});
        button2.addActionListener((e)->{
            textArea2.setText("");
            textArea0.setText("");
            textArea3.setText("");
            textArea4.setText("");
            textArea5.setText("");
        });
        panel2_1.add(button1);panel2_1.add(button2);
        Border title=BorderFactory.createTitledBorder(etched,"参数及结果(采用逐差法)");//设置边框
        panel2.setBorder(title);
        panel2.add(panel2_1);panel2.add(panel2_2);
    }
    private void calculate2(){
        double gs=1.732,l=1,d1=1,d2=1,E;
        double[] si=readTextArea(textArea2);
        double[] s1i=readTextArea(textArea3);
        for (int i = 0; i < si.length; i++) {
            double pj=(si[i]+s1i[i])/2;
            textArea4.append(String.format("%.6f",pj)+"\r\n");
        }
        double[] y=zhucha(textArea4,textArea5);
        //textArea6.append("y="+String.format("%.5f",y[0])+"±"+String.format("%.5f",y[1])+"(cm)\r\n");
        double ucl=Math.hypot(0.08/gs,0.3/gs);
        double ucd1=Math.hypot(0.05/gs,0.08/gs);
        double ucd2=Math.hypot(0.12/gs,0.5/gs);
        try {
            l= Double.parseDouble(textField6.getText());
        } catch (NumberFormatException e) {
            if (textField6.getText()==null){
                JOptionPane.showConfirmDialog(panel2_1,"请输入金属丝长度L。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

            }else {
                JOptionPane.showConfirmDialog(panel2_1,"金属丝长度L输入有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

            }

        }
        try {
            d1= Double.parseDouble(textField4.getText());
        } catch (NumberFormatException e) {
            if (textField4.getText()==null){
                JOptionPane.showConfirmDialog(panel2_1,"请输入后尖足到两前尖足距离D1。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

            }else {
                JOptionPane.showConfirmDialog(panel2_1,"后尖足到两前尖足距离D1输入有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

            }
        }
        try {
            d2= Double.parseDouble(textField5.getText());
        } catch (NumberFormatException e) {
            if (textField5.getText()==null){
                JOptionPane.showConfirmDialog(panel2_1,"请输入光杠杠镜面到标尺面距离D2(cm)。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

            }else {
                JOptionPane.showConfirmDialog(panel2_1,"光杠杠镜面到标尺面距离D2输入有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

            }
        }
        double[] dd=calculate(textArea0,panel1,textField1,textField2);
        E=8*l*(si.length/2)*9.78443*d2/(d1*y[0]*3.14159*Math.pow(dd[0]/1000,2));
        double uce=E*Math.sqrt(Math.pow(ucl/l,2)+Math.pow(ucd1/d1,2)+Math.pow(ucd2/d2,2)+Math.pow(2*dd[1]/dd[0],2)+Math.pow(y[1]/y[0],2));
        textArea6.append("E="+String.format("%.5f",E)+"±"+String.format("%.5f",uce)+"N/m^2\r\n"+"E的相对不确定度为："+String.format("%.3f",(uce/E)*100)+"%");
    }
    public double[] zhucha(JTextArea textArea,JTextArea textArea1q){
        int n;
        String[] lines =textArea.getText().split("\\r?\\n");//读取文本数据
        n = lines.length / 2;
         double sum=0;
         double[] a=readTextArea(textArea);
         double[] new1=new double[n];
        for (int j = 0; j < n; j++) {
            new1[j]=a[j+n]-a[j];
           sum=sum+(a[j+n]-a[j]);
        }
        double[] b=new double[2];
        b[0]=sum/n;
        double suma=0;
        for (double v : new1) {
            textArea1q.append(String.format("%.6f",v)+"\r\n");//如果在其他地方使用要注释
            suma=suma+Math.pow(v-b[0],2);

        }
        double v = suma / (n * (n - 1));
        double ub=0.01/1.732;
        b[1]=Math.hypot(ub,Math.sqrt(v));
        return b;
    }
    public void addTextArea(JTextArea textArea,String name,JPanel panel){
        textArea.setText("");
        textArea.setEnabled(true);
        JScrollPane scrollPane=new JScrollPane(textArea);//a设置滑动框
        Border title0=BorderFactory.createTitledBorder(etched,name);//设置边框
        scrollPane.setBorder(title0);//设置边框
        panel.add(scrollPane);
    }
    public double[] readTextArea(JTextArea textArea){
        double[] a;
        String[] lines =textArea.getText().split("\\r?\\n");//读取文本数据
        a=new double[lines.length];


        try {
            for (int i = 0; i < lines.length; i++) {
                a[i]= Double.parseDouble(lines[i]);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(panel1,"输入数据有误！请检查数据。","数据有误",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
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

}
