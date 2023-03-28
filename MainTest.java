package com.dataprocessingAPP;

import javax.swing.*;
import java.awt.*;
public class MainTest  {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->//由事件分派线程进行配置，初始化，更安全。
        {
            MainWindow frame=new MainWindow();//实例化主窗口
            frame.setVisible(true);//设置主窗口可见
        });
    }
}