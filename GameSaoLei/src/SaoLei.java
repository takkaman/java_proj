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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

public class SaoLei implements ActionListener {
    public class rcd {
        public String name;
        public int usedTime;

        rcd(String name, int time) {
            this.name = name;
            this.usedTime = time;
        }
    }

    JFrame frame = new JFrame("扫雷");
    JPanel myPanel = new JPanel();
    JButton retry = new JButton("重新开始");
    JPanel lowerPanel = new JPanel();// 创建底部面板
    Container container = new Container();

    // 数据结构
    // Leo
    final int rcdNum = 5;// 设置排名数
    // end Leo
    final int row = 20;// 设置行数
    final int col = 20;// 设置列数
    final int lei = 1;// 设置地雷数
    final int leinum = 10; // 设置地雷序号
    static int usedTime; // 已用时间
    static JLabel timeLabel = new JLabel();// 创建时间标签
    static Timer timer;// 创建计时
    JButton[][] buttons = new JButton[row][col];// 定义翻转按钮
    int[][] counts = new int[row][col];// 定义按钮数字

    // 构造函数
    public SaoLei() {
        // 界面
        frame.setSize(1000, 800);// 界面大小
        frame.setResizable(false);// 不能被改变大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭
        frame.setLayout(new BorderLayout());// 边框布局

        // 设置底部面板
        timeLabel.setText("用时：" + usedTime); // 显示剩余时间
        lowerPanel.add(timeLabel);// 时间标签加入底部面板
        timer = new Timer(1000, new TimerListener());// 计时器注册监听者
        frame.add(lowerPanel, BorderLayout.SOUTH);

        // 添加重新开始按钮
        addResetButton();

        // 添加翻转按钮
        addButtons();

        // 埋雷
        addLei();

        // 翻开附近的按钮
        dealLei();

        // 启动计时器
        timer.start();

        // 显示
        frame.setVisible(true);
    }

    void addResetButton() {
        // 重新开始按钮
        retry.setBackground(Color.white);// 按钮背景色
        retry.setOpaque(true);// 按钮透明度
        retry.addActionListener(this);
        frame.add(retry, BorderLayout.NORTH);// 将按钮添加在布局North区域
    }

    void addLei() {
        // 埋雷
        Random rand = new Random();// 产生随机数
        int randRow, randCol;// 定义随机行，随机列
        for (int i = 0; i < lei; i++) {
            // 生产随机行，随机列
            randRow = rand.nextInt(row);
            randCol = rand.nextInt(col);
            if (counts[randRow][randCol] == leinum) {
                i--;// 如果随机数相同不计入埋雷数，不进行埋雷
            } else {
                counts[randRow][randCol] = leinum;// 进行埋雷
                // buttons[randRow][randCol].setText("x");
            }
        }
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

    void dealLei() {
        int count;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                count = 0;
                if (counts[i][j] == leinum)
                    continue;
                // 显示附近按钮数字
                if (i > 0 && j > 0 && counts[i - 1][j - 1] == leinum)
                    count++;
                if (i > 0 && counts[i - 1][j] == leinum)
                    count++;
                if (i > 0 && j < 19 && counts[i - 1][j + 1] == leinum)
                    count++;
                if (j > 0 && counts[i][j - 1] == leinum)
                    count++;
                if (j < 19 && counts[i][j + 1] == leinum)
                    count++;
                if (i < 19 && j > 0 && counts[i + 1][j - 1] == leinum)
                    count++;
                if (i < 19 && counts[i + 1][j] == leinum)
                    count++;
                if (i < 19 && j < 19 && counts[i + 1][j + 1] == leinum)
                    count++;

                counts[i][j] = count;
                // buttons[i][j].setText(counts[i][j]+"");
            }

        }

    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton button = (JButton) e.getSource();// 获取按钮
        if (button.equals(retry)) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setBackground(Color.white);
                    counts[i][j] = 0;
                    timer.stop();
                    timer = new Timer(1000, new TimerListener());// 计时器注册监听者
                    usedTime = 0;// 计时归零
                    timeLabel.setText("用时：" + usedTime);
                    lowerPanel.add(timeLabel);// 时间标签加入底部面板
                    frame.add(lowerPanel, BorderLayout.SOUTH);
                    timer.start();
                }
            }
            addLei();
            dealLei();
        } else {
            int count = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (button.equals(buttons[i][j])) {
                        count = counts[i][j];
                        if (count == leinum) {
                            dead();// 触雷
                            JOptionPane.showMessageDialog(frame,
                                    "You are dead!");
                            timer.stop();

                        } else {
                            // 未触雷
                            openOthers(i, j);
                            win();
                        }
                        return;
                    }
                }

            }
        }
    }

    private String PatternOut(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    // 赢
    void win() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (buttons[i][j].isEnabled() == true && counts[i][j] != leinum)
                    return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Win!");
        // Leo: pop out input if user enter top 10
//		System.out.println(usedTime);
        List<rcd> rank = new ArrayList<>();
        String fileName="./rank.txt";
        String line;
        String finalRank = String.format("%8s %12s %8s\n","rank","name","time");
        int maxTime = 99999999;
        int count = 0;
        try
        {
            BufferedReader in=new BufferedReader(new FileReader(fileName));
            line=in.readLine();
            while (line!=null) {
//				System.out.println(line);
                String name = line.split(" ")[1];
                int time = Integer.valueOf(line.split(" ")[2]);
                maxTime = time;
                rank.add(new rcd(name, time));
//				System.out.println(rcd[0] + " " + rcd[1]);
                count++;
                line=in.readLine();
            }
            in.close();
            if (count == rcdNum) {
                if (usedTime < maxTime) {
                    rank.remove(rcdNum-1);
                    String usrName = JOptionPane.showInputDialog("You ranked top "+ rcdNum + ". please input your name!");
                    rank.add(new rcd(usrName, usedTime));
                }
            } else {
                String usrName = JOptionPane.showInputDialog("You ranked top "+ rcdNum + ". please input your name!");
                rank.add(new rcd(usrName, usedTime));
            }
            Collections.sort(rank, new Comparator<rcd>() {
                @Override
                public int compare(rcd o1, rcd o2) {
                    if (o1.usedTime > o2.usedTime) return 1;
                    return -1;
                }
            });
            FileWriter writer=new FileWriter(fileName);
            int i = 1;
            for (rcd r: rank) {
//				System.out.println(r.name + " " + r.usedTime);
                String str = String.format("%11d %12s %8s \n", i, r.name, r.usedTime);
                finalRank += str;
                writer.write(i+ " " + r.name + " " + r.usedTime + "\n");
                i++;
            }
            JOptionPane.showMessageDialog(frame, finalRank);
            writer.close();
        } catch (Exception e) {
//			e.printStackTrace();
            String usrName = JOptionPane.showInputDialog("You ranked top "+ rcdNum + ". please input your name!");
            try {
                FileWriter writer=new FileWriter(fileName);
                String str = String.format("%11d %12s %8s\n", 1, usrName, usedTime);
                finalRank += str;
                writer.write("1"+" "+usrName+" "+usedTime+"\n");
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(frame, finalRank);
        }
        // end Leo
    }

    // 未触雷
    void openOthers(int i, int j) {
        if (buttons[i][j].isEnabled() == false)
            return;// 如果按钮已被翻转，直接返回

        buttons[i][j].setEnabled(false);// 按钮不能被重复点击
        if (counts[i][j] == 0) {
            // 翻转附近不是雷的按钮
            if (i > 0 && j > 0 && counts[i - 1][j - 1] != leinum)
                openOthers(i - 1, j - 1);
            if (i > 0 && counts[i - 1][j] != leinum)
                openOthers(i - 1, j);
            if (i > 0 && j < 19 && counts[i - 1][j + 1] != leinum)
                openOthers(i - 1, j + 1);
            if (j > 0 && counts[i][j - 1] != leinum)
                openOthers(i, j - 1);
            if (j < 19 && counts[i][j + 1] != leinum)
                openOthers(i, j + 1);
            if (i < 19 && j > 0 && counts[i + 1][j - 1] != leinum)
                openOthers(i + 1, j - 1);
            if (i < 19 && counts[i + 1][j] != leinum)
                openOthers(i + 1, j);
            if (i < 19 && j < 19 && counts[i + 1][j + 1] != leinum)
                openOthers(i + 1, j + 1);
        } else {
            buttons[i][j].setText(counts[i][j] + "");// 显示按钮数字
        }
    }

    // 触雷
    void dead() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int count = counts[i][j];
                if (count == leinum) {
                    buttons[i][j].setText("X");// 按钮点击后显示文本
                    buttons[i][j].setBackground(Color.red);// 按钮背景颜色
                    buttons[i][j].setEnabled(false);// 按钮不能被重复点击
                    timer.stop();
                } else {
                    buttons[i][j].setText(count + "");// 按钮点击后显示文本
                    buttons[i][j].setEnabled(false);// 按钮不能被重复点击
                }
            }
        }
    }

    // 内部类，时间监听
    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            usedTime++;
            timeLabel.setText("用时：" + usedTime);

        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SaoLei game = new SaoLei();

    }

}
