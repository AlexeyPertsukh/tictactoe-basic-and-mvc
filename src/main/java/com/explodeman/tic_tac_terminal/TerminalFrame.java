package com.explodeman.tic_tac_terminal;

import com.explodeman.file_rw.BasicFileReader;
import com.explodeman.file_rw.BasicFileWriter;
import com.explodeman.file_rw.MyFileReader;
import com.explodeman.file_rw.MyFileWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class TerminalFrame extends JFrame {
    private static final int LED_DIAMETER = 18;
    private static final Color COLOR_OFF = Color.GRAY;
    private static final Color COLOR_ON_X = Color.RED;
    private static final Color COLOR_ON_0 = Color.GREEN;
    private static final Color COLOR_DRAW = Color.YELLOW;

    private final ImagePanel imagePanel = new ImagePanel();

    private final JButton[] buttons = new JButton[9];

    private final Led<Boolean> ledPlayerX = new Led1(70, 140, LED_DIAMETER, COLOR_ON_X);
    private final Led<Boolean> ledPlayer0 = new Led1(510, 140, LED_DIAMETER, COLOR_ON_0);
    private final Led<Integer> ledWin = new Led2(140, 290, LED_DIAMETER, COLOR_ON_X, COLOR_ON_0);
    private final Led<Boolean> ledDraw = new Led1(140, 320, LED_DIAMETER, COLOR_DRAW);
    private final Led<Integer>[] ledsMove = new Led2[]{
            new Led2(225, 110, LED_DIAMETER, COLOR_ON_X, COLOR_ON_0),
            new Led2(290, 110, LED_DIAMETER, COLOR_ON_X, COLOR_ON_0),
            new Led2(350, 110, LED_DIAMETER, COLOR_ON_X, COLOR_ON_0),

            new Led2(225, 170, LED_DIAMETER, COLOR_ON_X, COLOR_ON_0),
            new Led2(290, 170, LED_DIAMETER, COLOR_ON_X, COLOR_ON_0),
            new Led2(350, 170, LED_DIAMETER, COLOR_ON_X, COLOR_ON_0),

            new Led2(225, 230, LED_DIAMETER, COLOR_ON_X, COLOR_ON_0),
            new Led2(290, 230, LED_DIAMETER, COLOR_ON_X, COLOR_ON_0),
            new Led2(350, 230, LED_DIAMETER, COLOR_ON_X, COLOR_ON_0),
    };

    private final MyFileReader fileReader;
    private final MyFileWriter fileWriter;

    public TerminalFrame() throws HeadlessException {
        super();

        fileReader = new BasicFileReader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\explodeman\\tic_tac_terminal\\to_terminal.txt");
        fileWriter = new BasicFileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\com\\explodeman\\tic_tac_terminal\\from_terminal.txt");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Rectangle rectangle = new Rectangle(0, 0, 621, 760);
        this.setBounds(rectangle);
        this.setLocationRelativeTo(null);

        initButtons();

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));


        JPanel panel = new JPanel();
        for (JButton button : buttons) {
            panel.add(button);
        }

        container.add(imagePanel);
        container.add(panel);

        add(container);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                List<String> strings = fileReader.read();
                if (!strings.isEmpty()) {
                    long code = Long.parseLong(strings.get(strings.size() - 1));
                    ledsUpdate(code);
                    imagePanel.updateUI();
                }

            }
        }, 1000, 1000);


    }

    private void initButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(String.valueOf(i));
            buttons[i].addActionListener(this::actionPerformed);
        }
    }


    private void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int number = Integer.parseInt(button.getText());
        System.out.println(number);
        fileWriter.write("\n" + number);

    }

    private void ledsUpdate(long code) {
        for (int i = 0; i < 9; i++) {

            if (ProtocolToTerminal.isMoveX(i, code)) {
                ledsMove[i].set(1);
            } else if (ProtocolToTerminal.isMove0(i, code)) {
                ledsMove[i].set(2);
            } else {
                ledsMove[i].set(0);
            }
        }

        if(ProtocolToTerminal.isWinX(code)) {
            ledWin.set(1);
        } else if(ProtocolToTerminal.isWin0(code)) {
            ledWin.set(2);
        } else {
            ledWin.set(0);
        }

        ledPlayerX.set(ProtocolToTerminal.isNeedMoveX(code));
        ledPlayer0.set(ProtocolToTerminal.isNeedMove0(code));

        ledDraw.set(ProtocolToTerminal.isDraw(code));
    }

    public class ImagePanel extends JPanel {

        private BufferedImage image;

        public ImagePanel() {
            try {
                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\explodeman\\tic_tac_terminal\\XoTerminal.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);

            List<Led> list = new ArrayList<>();
            list.add(ledPlayerX);
            list.add(ledPlayer0);
            list.add(ledWin);
            list.add(ledDraw);
            Collections.addAll(list, ledsMove);

            for (Led led : list) {
                g.setColor(led.getColor());
                g.fillOval(led.x, led.y, led.diameter, led.diameter);
            }
        }
    }

    private static abstract class Led<T> {
        T value;
        public int x;
        public int y;
        public int diameter;

        public Led(int x, int y, int diameter) {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
        }

        void set(T state) {
            this.value = state;
        }

        abstract Color getColor();
    }

    private static class Led1 extends Led<Boolean> {

        private final Color colorOn;

        public Led1(int x, int y, int diameter, Color colorOn) {
            super(x, y, diameter);
            this.colorOn = colorOn;
            value = false;
        }

        @Override
        Color getColor() {
            return value ? colorOn : COLOR_OFF;
        }
    }

    private static class Led2 extends Led<Integer> {
        private final Color color1;
        private final Color color2;

        public Led2(int x, int y, int diameter, Color color1, Color color2) {
            super(x, y, diameter);
            this.color1 = color1;
            this.color2 = color2;
            value = 0;
        }

        @Override
        Color getColor() {
            if (value == 0) {
                return COLOR_OFF;
            }
            return value == 1 ? color1 : color2;
        }
    }
}
