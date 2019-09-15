package org.therolf.ExternalInput;

import javax.swing.*;
import java.awt.*;

public class App {
    private JPanel mainPanel;
    private JButton xButton;

    public App() {
        JFrame frame = new JFrame("coucou");
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setMinimumSize(new Dimension(800, 600));
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBackground(new Color(-16216064));
        panel1.setOpaque(true);
        mainPanel.add(panel1, BorderLayout.NORTH);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), null));
        final JLabel label1 = new JLabel();
        label1.setText("Label");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 80.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        xButton = new JButton();
        xButton.setBackground(new Color(-1));
        xButton.setBorderPainted(false);
        xButton.setContentAreaFilled(false);
        xButton.setFocusPainted(false);
        xButton.setForeground(new Color(-1));
        xButton.setHorizontalTextPosition(0);
        xButton.setIconTextGap(0);
        xButton.setMaximumSize(new Dimension(30, 30));
        xButton.setMinimumSize(new Dimension(30, 30));
        xButton.setOpaque(false);
        xButton.setPreferredSize(new Dimension(30, 30));
        xButton.setText("X");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel1.add(xButton, gbc);
        final JButton button1 = new JButton();
        button1.setBackground(new Color(-1));
        button1.setBorderPainted(false);
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        button1.setForeground(new Color(-1));
        button1.setHorizontalTextPosition(0);
        button1.setIconTextGap(0);
        button1.setMargin(new Insets(0, 0, 0, 0));
        button1.setMaximumSize(new Dimension(30, 30));
        button1.setMinimumSize(new Dimension(30, 30));
        button1.setOpaque(false);
        button1.setPreferredSize(new Dimension(30, 30));
        button1.setText("_");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel1.add(button1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1));
        panel2.setOpaque(true);
        mainPanel.add(panel2, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}