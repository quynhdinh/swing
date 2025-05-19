package com.quynhdv.swing_java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

final class Pair<T, U> {

    public Pair(T first, U second) {
        this.second = second;
        this.first = first;
    }

    public final T first;
    public final U second;

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
public class Main extends JPanel {
    private int numDots;
    private List<Pair<Integer, Integer>> locations;
    public Main(int numDots) {
        this.numDots = numDots;
        this.locations = new ArrayList<>();
    }
    private int getRandom(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Random rand = new Random();
        for (int i = 0; i < numDots; i++) {
            int x = getRandom(getWidth() / 10, getWidth() - getWidth() / 10);
            int y = getRandom(getHeight() / 10, getHeight() - getHeight() / 10);
            locations.add(new Pair<>(x, y));
            g.fillOval(x, y, 5, 5);
        }
    }

    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width * 2 / 3;
        int height = screenSize.height * 2 / 3;

        // Generate random number of dots between 100 and 200
        Random rand = new Random();
        int numDots = 4 + rand.nextInt(5); // 100 to 200
        System.out.println("Generated " + numDots + " points");
        // Create the frame
        JFrame frame = new JFrame("Random Dots Swing Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Add the panel with random dots
        Main dotsPanel = new Main(numDots);
        frame.add(dotsPanel);
        List<Pair<Integer, Integer>> locations1 = dotsPanel.locations;
        List<Integer> xCoords = new ArrayList<>(), yCoords = new ArrayList<>();
        for (Pair<Integer, Integer> integerIntegerPair : locations1) {
            xCoords.add(integerIntegerPair.first);
            yCoords.add(integerIntegerPair.second);
        }
        // Make the frame visible
        frame.setVisible(true);
        Solution sol = new Solution();
        List<Pair<Integer, Integer>> pairs = sol.maxRectangleArea(xCoords, yCoords);
        if (pairs.isEmpty()){
            System.out.println("no sol");
        } else {
            System.out.println(pairs);
        }
    }
}