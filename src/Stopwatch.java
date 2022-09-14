import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch extends main implements ActionListener {

    JFrame frame = new JFrame("Stopwatch");
    JButton sButton = new JButton("Start");
    JButton rButton = new JButton("Reset");
    JButton lButton = new JButton("Lap");
    JLabel tLabel = new JLabel();
    JLabel lLabel1 = new JLabel();
    JLabel lLabel2 = new JLabel();
    JLabel lLabel3 = new JLabel();
    int eTime = 0;
    int sec = 0;
    int min = 0;
    int hour = 0;
    boolean lapped1 = false;
    boolean lapped2 = false;
    boolean lapped3 = false;
    boolean running = false;
    String sString = String.format("%02d", sec);
    String mString = String.format("%02d", min);
    String hString = String.format("%02d", hour);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            eTime = eTime + 1000;
            hour = (eTime / 3600000);
            min = (eTime / 60000) % 60;
            sec = (eTime / 1000) % 60;

            String sString = String.format("%02d", sec);
            String mString = String.format("%02d", min);
            String hString = String.format("%02d", hour);
            tLabel.setText(hString + ":" + mString + ":" + sString);
            System.out.println(hString + ":" + mString + ":" + sString);

        }
    });

    Stopwatch() {
        tLabel.setText(hString + ":" + mString + ":" + sString);
        tLabel.setFont(new Font("Arial", Font.BOLD, 36));
        tLabel.setBounds(70, 100, 280, 100);
        tLabel.setBorder(BorderFactory.createBevelBorder(1));
        tLabel.setOpaque(true);
        tLabel.setHorizontalAlignment(JTextField.CENTER);

        lLabel1.setBounds(70, 200, 80, 30);
        lLabel1.setText("1st:" + hString + ":" + mString + ":" + sString);

        lLabel2.setBounds(170, 200, 80, 30);
        lLabel2.setText("2nd:" + hString + ":" + mString + ":" + sString);

        lLabel3.setBounds(280, 200, 80, 30);
        lLabel3.setText("3rd:" + hString + ":" + mString + ":" + sString);

        sButton.setBounds(70, 250, 80, 30);
        sButton.setFocusable(false);
        sButton.addActionListener(this);

        rButton.setBounds(170, 250, 80, 30);
        rButton.setFocusable(false);
        rButton.addActionListener(this);

        lButton.setBounds(270, 250, 80, 30);
        lButton.setFocusable(false);
        lButton.addActionListener(this);

        frame.add(lLabel1);
        frame.add(lLabel2);
        frame.add(lLabel3);
        frame.add(lButton);
        frame.add(sButton);
        frame.add(rButton);
        frame.add(tLabel);
        frame.setSize(420, 420);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sButton) {
            start();
            if (!running) {
                running = true;
                sButton.setText("Stop");
                start();
                System.out.println("Started");
            } else {
                running = false;
                sButton.setText("Start");
                stop();
                System.out.println("Stopped");
            }
        }
        if (e.getSource() == rButton) {
            running = false;
            sButton.setText("Start");
            reset();
            System.out.println("Resetted");
        }
        if (e.getSource() == lButton && !lapped1) {
            lap();
            lapped1 = true;
        } else if (e.getSource() == lButton && !lapped2) {
            lap();
            lapped2 = true;
        } else if (e.getSource() == lButton && !lapped3) {
            lap();
            lapped3 = true;
        }

    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        eTime = 0;
        sec = 0;
        min = 0;
        hour = 0;

        String sString = String.format("%02d", sec);
        String mString = String.format("%02d", min);
        String hString = String.format("%02d", hour);
        tLabel.setText(hString + ":" + mString + ":" + sString);
        lapped1 = false;
        lapped2 = false;
        lapped3 = false;
        lLabel1.setText("1st:" + hString + ":" + mString + ":" + sString);
        lLabel2.setText("2nd:" + hString + ":" + mString + ":" + sString);
        lLabel3.setText("3rd:" + hString + ":" + mString + ":" + sString);
        System.out.println(hString + ":" + mString + ":" + sString);
    }

    void lap() {
        String sString = String.format("%02d", sec);
        String mString = String.format("%02d", min);
        String hString = String.format("%02d", hour);

        if (!lapped1) {
            lLabel1.setText("1st:" + hString + ":" + mString + ":" + sString);
        } else if (!lapped2) {
            lLabel2.setText("2nd:" + hString + ":" + mString + ":" + sString);
        } else if (!lapped3) {
            lLabel3.setText("3rd:" + hString + ":" + mString + ":" + sString);
        }
    }
}
