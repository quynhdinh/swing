package com.quynhdv.swing_java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width * 2 / 3;
        int height = screenSize.height * 2 / 3;

        JFrame frame = new JFrame("Two third Screen Swing Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);

        JButton button = new JButton("Click Me!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Hello, World!");
            }
        });

        // Add the button to the frame
        frame.getContentPane().add(button);
        frame.setLayout(null);
        button.setBounds(width / 4, height / 2 - 15, width / 2, 30); // Center the button

        // Make the frame visible
        frame.setVisible(true);
    }
}
