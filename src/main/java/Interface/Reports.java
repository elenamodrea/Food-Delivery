package Interface;

import BusinessLayer.ReportsMethods;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Reports extends JFrame{
    private JPanel reports;
    private JTextField textField1;
    private JTextField textField2;
    private JButton generateReport1Button;
    private JTextField textField3;
    private JButton generateReport2Button;
    private JTextField textField4;
    private JTextField textField5;
    private JButton generateReport3Button;
    private JTextField textField6;
    private JButton generateReport4Button;
    private JButton exitButton;
    private JButton backButton;

    public Reports(){
        setContentPane(reports);
        setTitle("Reports");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        ReportsMethods r=new ReportsMethods();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new AdministratorInterface();

            }
        });
        generateReport1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sh=textField1.getText();
                String eh=textField2.getText();
                int startHour=Integer.parseInt(sh);
                int endHour=Integer.parseInt(eh);
                try {
                    r.generateRaport1(startHour,endHour);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        generateReport2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String n=textField3.getText();
                int number=Integer.parseInt(n);
                try {
                    r.generateReport2(number);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        generateReport3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String n=textField4.getText();
                int number=Integer.parseInt(n);
                String m=textField5.getText();
                int amount=Integer.parseInt(m);
                try {
                    r.generateReport3(number,amount);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        generateReport4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String n=textField6.getText();
                try {
                    r.generateReport4(n);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
