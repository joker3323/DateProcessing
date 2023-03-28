
package com.dataprocessingAPP;
/*
  可以使用方法来响应事件，处理数据，只要传入参数。
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class MainWindow extends JFrame {
    public static JPanel panel, p1, p2, p3;
    public static JMenuBar jmenuBar;
    public static JMenu jMenu_file, jMenu_method, jMenu_project, jMenu_help,jMenu_method_1;
    public static JMenuItem jMenu_file_1, jMenu_file_2, jMenu_file_3, jMenu_method_1_1,jMenu_method_1_2,jMenu_method_2,
            jMenu_method_3, jMenu_project_1, jMenu_project_2, jMenu_project_3, jMenu_project_4,
            jMenu_project_5, jMenu_project_6, jMenu_project_7, jMenu_project_8, jMenu_help_1;

    public static JButton button1, button2, button3;
    public static JLabel label, label1, value, label3, label4, label5, label6,
            label7, label8, label9;
    public static JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7;
    public static JTextArea textArea1, textArea2;
    Border etched = BorderFactory.createEtchedBorder();
    Border title = BorderFactory.createTitledBorder(etched, "原始数据");//设置边框
    Border title1 = BorderFactory.createTitledBorder(etched, "检验后数据");//设置边框

    Border titled = BorderFactory.createTitledBorder(etched, "数据区");//设置边框
    Border titled1 = BorderFactory.createTitledBorder(etched, "参数输入");//设置边框
    Border titled2 = BorderFactory.createTitledBorder(etched, "平均值及不确定度计算结果");
    Border titled3 = BorderFactory.createTitledBorder(etched);

    Toolkit kit = Toolkit.getDefaultToolkit();//调用工具类toolkit获得对象
    Dimension screeSize = kit.getScreenSize();//调用方法返回屏幕尺寸对象
    int screenWidth = screeSize.width;//获得屏幕宽
    int screenHeight = screeSize.height;//获得屏幕高

    public MainWindow() {
        //框架相关选择
        setSize(screenWidth * 3 / 5, screenHeight / 2);//设置框架大小
        Image image = new ImageIcon("icon.gif").getImage();//不知道为什么，不可以，估计是像素问题
        setIconImage(image);
        setTitle("大学物理实验数据处理");
        setLocation(screenHeight / 4, screenWidth / 12);//设置框架位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭响应
        setLayout(new GridLayout(1, 3));//设置布局

        //及菜单初始化
        addMenuBar();
        setJMenuBar(jmenuBar);

        //第一部分面板初始化，
        addPanel1();
        add(p1);

        //第二部分
        addPanel2();
        add(p2);

        //第三部分
        addPanel3();
        add(p3);

    }

    public void addPanel1() {
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p1.setLayout(new GridLayout(1, 2));
        textArea1 = new JTextArea(20, 8);//有问题，
        textArea1.setEnabled(true);
        JScrollPane scrollPane = new JScrollPane(textArea1);//a设置滑动框
        scrollPane.setBorder(title);//设置边框
        p1.add(scrollPane);
        textArea2 = new JTextArea(20, 8);//有问题，
        textArea2.setText("输入数据说明：\\t\n" +
                "                输入数据请按一\\t\n" +
                "                行一个数据手动\\t\n" +
                "                输入中间不要有\\t\n" +
                "                空行!本程序只\\t\n" +
                "                有数值计算功能\\t\n" +
                "                请使用者自行注\\t\n" +
                "                意单位换算数据\\t\n" +
                "                输入例如\\t\n" +
                "                13.4\\t\n" +
                "                12.5\\t\n" +
                "                25.78\\t\n" +
                "                35.59\\t\n" +
                "                请在键盘为英文\\t\n" +
                "                状态下输入数据");
        textArea2.setEnabled(false);
        JScrollPane scrollPane1 = new JScrollPane(textArea2);//设置滑动框
        scrollPane1.setBorder(title1);//设置边框
        p1.add(scrollPane1);
        p1.setBorder(titled);//设置边框
    }

    public void addPanel2() {
        p2.setLayout(new FlowLayout());
        button1 = new JButton("数据计算");
        button2 = new JButton("数据重置");
        button3 = new JButton("肖维涅准则检验坏值");
        label1 = new JLabel("置信概率:");
        value = new JLabel("0.683");
        label3 = new JLabel("置信系数:");
        textField1 = new JTextField(6);
        textField1.setText("1.732");
        label4 = new JLabel("仪器误差:");
        textField2 = new JTextField(6);
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(label1);
        panel.add(value);
        panel.add(label3);
        panel.add(textField1);
        panel.add(label4);
        panel.add(textField2);
        panel.setBorder(titled1);
        button1.addActionListener((event) -> calculate());//数值计算代码，可以写一个方法
        button2.addActionListener((event) -> shujchonzhi());//数据重置代码
        button3.addActionListener((event) -> xiaoweinie());//肖维涅准则检验坏值代码
        p2.add(button1);
        p2.add(button2);
        p2.add(button3);
        p2.add(panel);
    }

    public void addPanel3() {
        p3 = new JPanel();
        p3.setLayout(new GridLayout(10, 2));
        label = new JLabel(" ");
        label5 = new JLabel("平均值:", JLabel.RIGHT);
        label6 = new JLabel("A类不确定度:", JLabel.RIGHT);
        label7 = new JLabel("B类不确定度:", JLabel.RIGHT);
        label8 = new JLabel("C类不确定度:", JLabel.RIGHT);
        label9 = new JLabel("相对不确定度:", JLabel.RIGHT);

        textField3 = new JTextField(8);
        textField4 = new JTextField(8);
        textField5 = new JTextField(8);
        textField6 = new JTextField(8);
        textField7 = new JTextField(8);

        p3.add(label5);
        p3.add(textField3);
        p3.add(label);
        p3.add(label);
        p3.add(label6);
        p3.add(textField4);
        p3.add(label);
        p3.add(label);
        p3.add(label7);
        p3.add(textField5);
        p3.add(label);
        p3.add(label);
        p3.add(label8);
        p3.add(textField6);
        p3.add(label);
        p3.add(label);
        p3.add(label9);
        p3.add(textField7);
        p3.add(label);
        p3.add(label);
        p3.setBorder(titled2);
    }

    public void addMenuBar() {
        jmenuBar = new JMenuBar();
        jMenu_file = new JMenu("文件");
        jMenu_method = new JMenu("处理方法");
        jMenu_project = new JMenu("实验项目");
        jMenu_help = new JMenu("帮助");
        jMenu_file_1 = new JMenuItem("打开");
        jMenu_file_2 = new JMenuItem("退出");
        jMenu_file_3 = new JMenuItem("保存");
        jMenu_file_3.setAccelerator(KeyStroke.getKeyStroke("ctrl o"));//设置加速器组合键。将直接激活关联事件
        jMenu_method_1 = new JMenu("作图法");
        jMenu_method_1_1=new JMenuItem("折线图");
        jMenu_method_1_2=new JMenuItem("最小二乘法拟合直线图");
        jMenu_method_1_2.addActionListener((e -> {
            LineOPTest lineOPTest = new LineOPTest();
            lineOPTest.setVisible(true);
        }));
        jMenu_method_1.add(jMenu_method_1_1);
        jMenu_method_1_1.addActionListener((event)->{
            /**
             * 这个位置要写一个界面用来输入参数
             */
            LineChart lineChart = new LineChart();
            lineChart.setVisible(true);
        });
        jMenu_method_1.add(jMenu_method_1_2);
        jMenu_method_2 = new JMenuItem("逐差法");
        jMenu_method_3 = new JMenuItem("最小二乘法");
        jMenu_project_1 = new JMenuItem("长度与体积的测量");
        jMenu_project_2 = new JMenuItem("质量与密度的测量");
        jMenu_project_3 = new JMenuItem("单摆的研究");
        jMenu_project_4 = new JMenuItem("用拉伸法测定弹性模量");
        jMenu_project_5 = new JMenuItem("学习使用万用表");
        jMenu_project_6 = new JMenuItem("示波器的使用");
        jMenu_project_7 = new JMenuItem("电表的改装");
        jMenu_project_8 = new JMenuItem("用惠斯通电桥测电组");
        jMenu_help = new JMenu("帮助");
        //文件
        jMenu_file.add(jMenu_file_1);
        jMenu_file.addSeparator();
        jMenu_file.add(jMenu_file_3);
        jMenu_file.addSeparator();
        jMenu_file.add(jMenu_file_2);
        jmenuBar.add(jMenu_file);
        jMenu_file_2.addActionListener((event) -> System.exit(0));
        //实验处理方法
        jMenu_method.add(jMenu_method_1);
        jMenu_method.addSeparator();
        jMenu_method.add(jMenu_method_2);
        jMenu_method.addSeparator();
        jMenu_method.add(jMenu_method_3);
        jmenuBar.add(jMenu_method);

        //实验项目
        jMenu_project.add(jMenu_project_1);
        jMenu_project.addSeparator();
        jMenu_project.add(jMenu_project_2);
        jMenu_project.addSeparator();
        jMenu_project.add(jMenu_project_3);
        jMenu_project.addSeparator();
        jMenu_project.add(jMenu_project_4);
        jMenu_project.addSeparator();
        jMenu_project.add(jMenu_project_5);
        jMenu_project.addSeparator();
        jMenu_project.add(jMenu_project_6);
        jMenu_project.addSeparator();
        jMenu_project.add(jMenu_project_7);
        jMenu_project.addSeparator();
        jMenu_project.add(jMenu_project_8);
        jmenuBar.add(jMenu_project);
        //方法
        jMenu_project_1.addActionListener((event) -> {
            Measurement measurement = new Measurement();
            measurement.setVisible(true);

        });
        jMenu_project_2.addActionListener((event) -> {
            Density density = new Density();
            density.setVisible(true);
        });
        jMenu_project_3.addActionListener((event) -> {
            SimplePendulum simplePendulum = new SimplePendulum();
            simplePendulum.setVisible(true);
        });
        jMenu_project_4.addActionListener((event) -> {
            ElasticModulus elasticModulus = new ElasticModulus();
            elasticModulus.setVisible(true);
        });
        jMenu_project_5.addActionListener((event) -> {
            UsedTable usedTable = new UsedTable();
            usedTable.setVisible(true);
        });
        jMenu_project_6.addActionListener((event) -> {
            Oscilloscope oscilloscope = new Oscilloscope();
            oscilloscope.setVisible(true);
        });
        jMenu_project_7.addActionListener((event) -> {
            Microammeter m = new Microammeter();
            m.setVisible(true);
        });
        jMenu_project_8.addActionListener((event) -> {
            Bridge bridge = new Bridge();
            bridge.setVisible(true);
        });
        //帮助
        jmenuBar.add(jMenu_help);
        jmenuBar.setBorder(titled3);
    }

    public void calculate() {
        String[] lines = textArea1.getText().split("\\r?\\n");//读取文本数据
        double sum = 0, suma = 0, adj;
        for (String line : lines) {
            try {
                sum = sum + Double.parseDouble(line);
            } catch (NumberFormatException ex) {
                JOptionPane.showConfirmDialog(p2, "输入数据有误！请检查数据。\n请不要输入空格或英文字母", "数据有误", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                //提示错误信息
            }
        }
        DecimalFormat df1 = new DecimalFormat("###.00000000");//设置保留8位小数位数
        adj = Double.parseDouble((df1.format(sum / lines.length)));
        textField3.setText(String.valueOf(adj));//输出数据

        for (String s : lines) {
            suma +=  Math.pow((Double.parseDouble(s) - adj), 2);//计算A类的方差s
        }
        double ua1 = Double.parseDouble(df1.format(suma / (lines.length * (lines.length - 1))));
        double ua = Double.parseDouble(df1.format(Math.sqrt(ua1)));//计算A类不确定度。
        textField4.setText(String.valueOf(ua));//输出数据

        try {//计算B类
            double ub1 = Double.parseDouble(textField2.getText());//读取仪器误差。
            double ub2 = Double.parseDouble(textField1.getText());//1.732
            double ub = Double.parseDouble((df1.format(ub1 / ub2)));
            textField5.setText(String.valueOf(ub));


            double uc = Double.parseDouble(df1.format(Math.hypot(ua, ub)));//计算C类不确定度
            textField6.setText(String.valueOf(uc));
            DecimalFormat df2 = new DecimalFormat("###.0000");//设置保留4位小数位数
            double u = Double.parseDouble(df2.format((uc / adj) * 100));//计算相对不确定度，Ur=u/y。u是标准不确定度，y可以是测量值，或测量结果的算数平均值，或公认标准值，或理论值。

            textField7.setText(u + "%");
        } catch (NumberFormatException e) {
            int selection = JOptionPane.showConfirmDialog(p2, "请输入仪器误差(单击ok将默认0.01)", "参数不足", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (selection == JOptionPane.OK_OPTION) {
                textField2.setText("0.01");
            }//应该弹出一个警告框
        }

    }

    public void xiaoweinie() {
        String[] lines = textArea1.getText().split("\\r?\\n");//读取文本数据
        double sum = 0, suma = 0, adj, sa, k, max, min;
        if (lines.length <= 4) {
            JOptionPane.showConfirmDialog(p2, "测量数据要多于5个", "数据不足", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);//警告框
        } else {
            for (String line : lines) {
                try {
                    sum = sum + Double.parseDouble(line);
                } catch (NumberFormatException ex) {
                    JOptionPane.showConfirmDialog(p2, "输入数据有误！请检查数据。", "数据有误", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);//警告框
                }
            }
            adj = sum / lines.length;
            for (String s : lines) {

                try {

                    suma += Math.pow((Double.parseDouble(s) - adj), 2);//计算A类
                } catch (NumberFormatException ex) {
                    JOptionPane.showConfirmDialog(p2, "输入数据有误！请检查数据。", "数据有误", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    //应该弹出一个警告框
                }
            }
            sa = Math.sqrt(suma / lines.length);//计算标准差s
            if (lines.length <= 70) {
                k = 0.359 * Math.log1p(lines.length - 2.5) + 1.193;//拟合肖维涅系数
            } else if (lines.length <= 2000) {
                k = 0.2688 * Math.log1p(lines.length - 21) + 1.63;//拟合肖维涅系数
            } else {
                k = 3.76;
            }//取N=3000时
            max = adj + (k * sa);
            min = adj - k * sa;
            textArea2.setText("");
            textArea2.setEnabled(true);
            for (String line : lines) {//写入数据
                double n = Double.parseDouble(line);
                if (n > min && n < max) {
                    textArea2.append(line + "  合格\r\n");
                } else {
                    textArea2.append(line + "  不合格\r\n");
                }

            }
        }

    }

    public void shujchonzhi() {
        textArea1.setText("");
        textArea2.setText("  输入数据说明：\n" +
                "输入数据请按一\n" +
                "行一个数据手动\n" +
                "输入中间不要有\n" +
                "空行!本程序只\n" +
                " 有数值计算功能\n" +
                "\n" +
                "                请使用者自行注\n" +
                "                \n" +
                "意单位换算数据\n" +
                "                \n" +
                "输入例如\n" +
                "                \n" +
                "13.4\n" +
                "                \n" +
                "12.5\n" +
                "                \n" +
                "25.78\n" +
                "                \n" +
                "35.59\n" +
                "                \n" +
                "请在键盘为英文\n" +
                "                \n" +
                "状态下输入数据");
        textArea2.setEnabled(false);

    }

}

