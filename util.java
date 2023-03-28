package com.dataprocessingAPP;

import javax.swing.*;
import javax.swing.border.Border;

public   class util {

    public static double[] readTextArea(JTextArea textArea, JPanel panel){
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
    public static void addTextArea(JTextArea textArea,String name,JPanel panel){
        Border etched = BorderFactory.createEtchedBorder();//设置边框类型
        textArea.setText("");
        textArea.setEnabled(true);
        JScrollPane scrollPane=new JScrollPane(textArea);//a设置滑动框
        Border title0=BorderFactory.createTitledBorder(etched,name);//设置边框
        scrollPane.setBorder(title0);//设置边框
        panel.add(scrollPane);

    }//添加一个文本域
    public static double[] calculate(double[] d, double value0, double value) {
        double adj=0,dsum=0,dsuma=0,ua=0,uc=0,ub1=0,ub2,ub = 0;
        if (d.length!=1) {
            for (double v : d) {
                dsum = dsum + (v-value0);
            }
            adj=dsum /d.length;
            for (double v : d) {
                dsuma = dsuma + Math.pow(((v-value0) - adj), 2);
            }
            ua=Math.sqrt(dsuma/(d.length*(d.length-1)));
        }else {
            adj=d[0];
            ua=0;
        }

        ub1 = value;//计算B类
            ub2=Math.pow(3,0.5);
            ub=ub1/ub2;

        uc= ((Math.hypot(ua,ub)));//计算C类
        double[] a={adj,uc};
        return a  ;
    }//返回一个数组的的平均值和c类不确定度


}
