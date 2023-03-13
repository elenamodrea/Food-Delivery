package Interface;

import BusinessLayer.*;
import Serialization.Serialization;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorInterface extends JFrame {
    private JPanel administrator;
    private JTable table1;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton addToComposedProductButton;
    private JButton createComposedProductButton;
    private JButton backButton;
    private JButton exitButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton showBaseProductsButton;
    private JButton reportsButton;
    private JTextField textField8;
    List<BaseProduct> listComposed;

    public AdministratorInterface()  {
        setContentPane(administrator);
        setTitle("Administrator" );
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        DeliveryService f=new DeliveryService();
        ReadFromFile r=new ReadFromFile();
        Serialization s=new Serialization();
        listComposed=new ArrayList<BaseProduct>();
        /*try {
            s.listSerialization(r.readFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();;
                new LogInInterface();

            }
        });
        showBaseProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    table1.setModel(f.tableModel());
                } catch (IllegalAccessException | IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // check for selected row first
                if(table1.getSelectedRow() != -1) {
                    // remove selected row from the model
                    try {
                      f.delete(table1.getSelectedRow());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
            }
        });
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String title=textField1.getText();
              String ratingS=textField2.getText();
              String caloriesS=textField3.getText();
              String proteinS=textField4.getText();
              String fatS=textField5.getText();
              String sodiumS=textField6.getText();
              String priceS=textField7.getText();
                if(table1.getSelectedRow() != -1) {
                    try {
                        f.modify(table1.getSelectedRow(), title,ratingS,caloriesS,proteinS,fatS,sodiumS,priceS);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }


                }}
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title=textField1.getText();
                String ratingS=textField2.getText();
                String caloriesS=textField3.getText();
                String proteinS=textField4.getText();
                String fatS=textField5.getText();
                String sodiumS=textField6.getText();
                String priceS=textField7.getText();
                try {
                    f.add(title,ratingS,caloriesS,proteinS,fatS,sodiumS,priceS);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        addToComposedProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() != -1) {
                    try {
                        listComposed=f.addToComposedList(table1.getSelectedRow(),listComposed);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        createComposedProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=textField8.getText();
                List<BaseProduct> list=(List<BaseProduct>)listComposed;
                CompositeProduct product=new CompositeProduct(name,list);
                try {
                    f.add(name,Double.toString(product.getRating()),Integer.toString(product.getCalories()),Integer.toString(product.getProtein()),Integer.toString(product.getFat()),Integer.toString(product.getSodium()),Integer.toString(product.getPrice()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                listComposed=new ArrayList<>();

            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
               new Reports();

            }
        });
    }
}
