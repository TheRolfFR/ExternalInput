package org.therolf.ExternalInput;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.nio.ByteBuffer;
import java.nio.file.FileSystems;

public class Main {

    private JFrame frame;
    private JPasswordField inputField;
    private JComboBox<SerialPort> portsComboBox;
    private SerialPort chosenPort;

    private static final int WIDTH  = 400;
    private static final int HEIGHT = 320;

    public Main() {

        frame = new JFrame ("ExternalInput");
        String path = FileSystems.getDefault().getPath("res/keyboard-variant.png").toAbsolutePath().toString();
        ImageIcon icon = new ImageIcon(path);
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JLabel label = new JLabel("<html><div style=\"text-align: justify;\" >Type any key below :</div></html>");
        label.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        label.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        inputField = new JPasswordField();
        inputField.setHorizontalAlignment(SwingConstants.CENTER);
        inputField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputField.setText("");
                System.out.println(e.getKeyCode());
                System.out.println(e.getKeyLocation());
                System.out.println(e.getKeyChar());
                if(chosenPort != null && chosenPort.isOpen()) {
                    ByteBuffer bb = ByteBuffer.allocate(4);
                    bb.putInt(e.getKeyCode());
                    chosenPort.writeBytes(bb.array(), 1);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputField.setText("");
            }
        });


        JLabel comText = new JLabel("<html><div style=\"text-align: justify;\" >Choose the COM port corresponding to the device</div></html>");
        JButton refreshButton = new JButton ("REFRESH");
        refreshButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshPortComboBox();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        portsComboBox = new JComboBox<>();
        refreshPortComboBox();

        JPanel bottomButtons = new JPanel(new BorderLayout(10, 10));
        bottomButtons.add(BorderLayout.NORTH, comText);
        bottomButtons.add(BorderLayout.CENTER, portsComboBox);
        bottomButtons.add (BorderLayout.EAST, refreshButton);


        JPanel content = new JPanel (new BorderLayout(10, 10));
        content.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        content.setBorder(new EmptyBorder(10, 10, 10, 10));
        content.add (BorderLayout.NORTH, label);
        content.add (BorderLayout.CENTER, inputField);
        content.add(BorderLayout.SOUTH, bottomButtons);
        frame.add (content, BorderLayout.CENTER);

        frame.pack ();
        frame.setSize(new Dimension (WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setVisible (true);
    }

    public static void main (String[] args) {
        try {
            UIManager.setLookAndFeel (new MaterialLookAndFeel ());
            if (UIManager.getLookAndFeel() instanceof MaterialLookAndFeel){
                MaterialLookAndFeel.changeTheme(new MaterialDarkTheme());
            }
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace ();
        }

        new Main();
    }

    private void refreshPortComboBox() {
        ActionListener listener = e -> {
            if (chosenPort != null) {
                chosenPort.closePort();
                chosenPort = null; // force the garbage collector to destroy it
            }
            chosenPort = (SerialPort) portsComboBox.getSelectedItem();
            if(chosenPort != null) {
                // System.out.println(chosenPort.getDescriptivePortName());
                System.out.println(chosenPort.getSystemPortName());
                chosenPort.openPort();
                chosenPort.addDataListener(new SerialPortDataListener() {
                    @Override
                    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
                    @Override
                    public void serialEvent(SerialPortEvent event)
                    {
                        if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                            return;
                        if(chosenPort.bytesAvailable() > 0) {
                            byte[] newData = new byte[chosenPort.bytesAvailable()];
                            int numRead = chosenPort.readBytes(newData, newData.length);
                            for (byte b : newData) {
                                char c = (char) b;
                                if(c != '\n') {
                                    System.out.print(c);
                                }
                            }
                            System.out.println();
                            System.out.println("Read " + numRead + " bytes.");
                        }
                    }
                });
            }
        };
        SerialPort[] ports = SerialPort.getCommPorts();
        if(ports.length != 0) {
            portsComboBox.removeAllItems();
            for(SerialPort p : ports) {
                portsComboBox.addItem(p);
            }
            portsComboBox.addActionListener(listener);
            portsComboBox.setEnabled(true);
        } else {
            portsComboBox.removeActionListener(listener);
            portsComboBox.setEnabled(false);
        }
    }
}