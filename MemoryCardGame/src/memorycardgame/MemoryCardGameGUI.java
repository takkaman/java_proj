package memorycardgame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MemoryCardGameGUI implements ActionListener {
    JFrame frame = new JFrame("MemoryCardGame");
    JPanel myPanel = new JPanel();
    JButton retry = new JButton("Re-start");
    JPanel lowerPanel = new JPanel();// 创建底部面板
    Container container = new Container();

    // 数据结构
    final int row = 4;// 设置行数
    final int col = 4;// 设置列数
    static int usedTime; // 已用时间
    static JLabel timeLabel = new JLabel();// 创建时间标签
    static Timer timer;// 创建计时
    JButton[][] buttons = new JButton[row][col];// 定义翻转按钮
    int[][] counts = new int[row][col];// 定义按钮数字

    public void actionPerformed(ActionEvent e) {
        System.out.println("AAA");
    }

    // action listener
    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("AA");
        }
    }

    public MemoryCardGameGUI() {
        frame.setSize(1000, 800);// 界面大小
        frame.setResizable(false);// 不能被改变大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭
        frame.setLayout(new BorderLayout());// 边框布局

        // 设置底部面板
        timeLabel.setText("Used：" + usedTime); // 显示剩余时间
        lowerPanel.add(timeLabel);// 时间标签加入底部面板
        timer = new Timer(1000, new TimerListener());// 计时器注册监听者
        frame.add(lowerPanel, BorderLayout.SOUTH);
        System.out.println("GUI started.");
        addResetButton();
        addButtons();
        frame.setVisible(true);
    }

    void addResetButton() {
        // 重新开始按钮
        retry.setBackground(Color.white);// 按钮背景色
        retry.setOpaque(true);// 按钮透明度
        retry.addActionListener(this);
        frame.add(retry, BorderLayout.NORTH);// 将按钮添加在布局North区域
    }

    void addButtons() {
        // 翻转按钮
        frame.add(container, BorderLayout.CENTER);// 将按钮添加在布局Center区域
        container.setLayout(new GridLayout(row, col));// 按行列自动分配容器
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                JButton button = new JButton();
                button.setBackground(Color.white);
                button.setOpaque(true);
                button.addActionListener(this);// 添加按钮动作监听器
                buttons[i][j] = button;// 定义按钮数组
                container.add(button);// 添加按钮进入容器
            }
        }
    }

}
