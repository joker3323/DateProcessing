package com.dataprocessingAPP;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LineOPTest extends JFrame {

    public static JPanel panel1, panel1_1, panel1_2, panel1_3, panel2, panel2_1, panel2_2, panel2_3, panel3, panel3_1, panel3_2,panel3_3;
    public static JTextArea textArea1_0,textArea1_1, textArea1_2, textArea1_3, textArea2_0,textArea2_1, textArea2_2, textArea2_3;

    public static JButton button1, button2, button3, button4;
    public static JTextField textField1,textField2,textField3,textField4,textField5,textField6;
    Border etched = BorderFactory.createEtchedBorder();//设置边框类型

    Toolkit kit = Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize = kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth = screeSize.width;//获得屏幕宽
    int screenHeight = screeSize.height;//获得屏幕高

    public LineOPTest() throws HeadlessException {
        setSize(screenWidth * 2 / 8, screenHeight * 1/2);//设置框架大小
        setTitle("最小二乘法拟合趋势图");
        setLocationByPlatform(true);//设置框架位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭响应
        setLayout(new BorderLayout());//设置主面版布局

        addPanel1();
    }

    private void addPanel1() {
        panel1_1=new JPanel(new GridLayout(1,2));
        panel1_2=new JPanel(new GridLayout(1,2));

        textArea1_0=new JTextArea(10,4);
        textArea1_1=new JTextArea(10,4);




        addTextArea(textArea1_0,"X",panel1_1);
        addTextArea(textArea1_1,"Y",panel1_1);

        button1=new JButton("数据重置");
        button2=new JButton("作图");

        button1.addActionListener((e -> {
            textArea1_0.setText("");
            textArea1_1.setText("");
        }));
        button2.addActionListener(e -> {

            LineOP.Draw(readTextArea(textArea1_0,panel1_1),readTextArea(textArea1_1,panel1_1));
        });

        panel1_2.add(button1);panel1_2.add(button2);
        add(panel1_1,BorderLayout.CENTER);
        add(panel1_2,BorderLayout.SOUTH);

    }

    private float[] readTextArea(JTextArea textArea,JPanel panel){
        String[] lines =textArea.getText().split("\\r?\\n");//读取文本数据
        float[] a=new float [lines.length];


        try {
            for (int i = 0; i < lines.length; i++) {
                a[i]= Float.parseFloat(lines[i]);
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
