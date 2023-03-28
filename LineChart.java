package com.dataprocessingAPP;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LineChart extends JFrame {
    public static JPanel panel1, panel1_1, panel1_2, panel1_3, panel2, panel2_1, panel2_2, panel2_3, panel3, panel3_1, panel3_2,panel3_3;
    public static JTextArea textArea1_0,textArea1_1, textArea1_2, textArea1_3, textArea2_0,textArea2_1, textArea2_2, textArea2_3;

    public static JButton button1, button2, button3, button4;
    public static JTextField textField1,textField2,textField3,textField4,textField5,textField6;
    Border etched = BorderFactory.createEtchedBorder();//设置边框类型

    Toolkit kit = Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize = kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth = screeSize.width;//获得屏幕宽
    int screenHeight = screeSize.height;//获得屏幕高

    public LineChart() throws HeadlessException {
        setSize(screenWidth * 2 / 8, screenHeight * 2 / 5);//设置框架大小
        setTitle("折线图");
        setLocationByPlatform(true);//设置框架位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭响应
        setLayout(new BorderLayout());//设置主面版布局
        addPanel1();
    }

    private void addPanel1() {
        panel1_1=new JPanel(new GridLayout(1,3));
        panel1_2=new JPanel(new GridLayout(1,3));
        panel1_3=new JPanel(new GridLayout(8,1));

        textArea1_0=new JTextArea(10,4);
        textArea1_1=new JTextArea(10,4);

        JLabel l1=new JLabel("折线名:"); textField1=new JTextField(6);textField1.setText("");
        panel1_3.add(l1);panel1_3.add(textField1);
        JLabel l2=new JLabel("图表标题:"); textField2=new JTextField(6);textField2.setText("");
        panel1_3.add(l2);panel1_3.add(textField2);
        JLabel l3=new JLabel("X轴名称:"); textField3=new JTextField(6);textField3.setText("");
        panel1_3.add(l3);panel1_3.add(textField3);
        JLabel l4=new JLabel("Y轴名称:"); textField4=new JTextField(6);textField4.setText("");
        panel1_3.add(l4);panel1_3.add(textField4);


        addTextArea(textArea1_0,"X",panel1_1);
        addTextArea(textArea1_1,"Y",panel1_1);
        Border title0=BorderFactory.createTitledBorder(etched,"参数");//设置边框
        panel1_3.setBorder(title0);//设置边框
        panel1_1.add(panel1_3);

        button1=new JButton("数据重置");
        button2=new JButton("作图");

        button1.addActionListener((e -> {
            textArea1_0.setText("");
            textArea1_1.setText("");
        }));
        button2.addActionListener(e -> {
            JFreeChartTest ex = new JFreeChartTest(textField3.getText()+"-"+textField3.getText()+"折线图",textField1.getText()
                    ,textField2.getText(),textField3.getText(),textField3.getText(),readTextArea(textArea1_0,panel1_1),readTextArea(textArea1_1,panel1_1));
            ex.setVisible(true);
        });

        panel1_2.add(button1);panel1_2.add(button2);panel1_2.add(new JLabel(""));
        add(panel1_1,BorderLayout.CENTER);
        add(panel1_2,BorderLayout.SOUTH);

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
    private void addTextArea (JTextArea textArea,String name,JPanel panel){
        textArea.setText("");
        textArea.setEnabled(true);
        JScrollPane scrollPane=new JScrollPane(textArea);//a设置滑动框
        Border title0=BorderFactory.createTitledBorder(etched,name);//设置边框
        scrollPane.setBorder(title0);//设置边框
        panel.add(scrollPane);
    }

}
