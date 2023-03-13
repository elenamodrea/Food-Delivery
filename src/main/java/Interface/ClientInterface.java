package Interface;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.User;
import Serialization.Serialization;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class ClientInterface extends JFrame{
    private JPanel client;
    private JTable table1;
    private JTextField textField1;
    private JButton searchByKeywordButton;
    private JButton searchByPriceButton;
    private JButton orderButton;
    private JButton backButton;
    private JButton exitButton;
    private JButton addToOrderButton;
    private JButton showMenuButton;
    private JButton searchByRatingButton;
    private JButton searchByCaloriesButton;
    private JButton searchByProteinsButton;
    private JButton searchbyFatsButton;
    private JButton searchBysodiumButton;
    private List<MenuItem> listComposed;
    private Map<Order, List<MenuItem>> orderMap;

    ClientInterface(User user){
        DeliveryService f=new DeliveryService();
        listComposed=new ArrayList<>();
        orderMap=new HashMap<Order, List<MenuItem>>();
        Serialization s=new Serialization();
        /*try {
            s.MapSerialization(orderMap);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        setContentPane(client);
        setTitle("Client");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
        showMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    table1.setModel(f.tableModel());
                } catch (IllegalAccessException | IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new LogInInterface();

            }
        });
        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(table1.getSelectedRow() != -1) {
                    try {
                        listComposed=f.addToOrderList(table1.getSelectedRow(),listComposed);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int clientId=user.getUserId();
                try {
                    orderMap=s.MapDeserialization();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                int orderId=orderMap.size()+1;
               Date date=new Date();
               Order order=new Order(orderId,clientId,date);
                try {
                    orderMap=f.createOrder(order,listComposed);
                    f.createBill(order,listComposed);
                    listComposed=new ArrayList<>();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        searchByKeywordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String keyword=textField1.getText();
                try {
                    table1.setModel(f.searchByKeyword(keyword));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        searchByPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword=textField1.getText();
                int price=Integer.parseInt(keyword);
                try {
                    table1.setModel(f.searchByPrice(price));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        searchByCaloriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyword=textField1.getText();
                int calories=Integer.parseInt(keyword);
                try {
                    table1.setModel(f.searchByCalories(calories));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });
        searchbyFatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyword=textField1.getText();
                int fat=Integer.parseInt(keyword);
                try {
                    table1.setModel(f.searchByFats(fat));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });
        searchByProteinsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyword=textField1.getText();
                int protein=Integer.parseInt(keyword);
                try {
                    table1.setModel(f.searchByProteins(protein));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });
        searchByRatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyword=textField1.getText();
                double rating=Double.parseDouble(keyword);
                try {
                    table1.setModel(f.searchByRating(rating));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });

        searchBysodiumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyword=textField1.getText();
                int sodium=Integer.parseInt(keyword);
                try {
                    table1.setModel(f.searchBySodium(sodium));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });

    }
}
