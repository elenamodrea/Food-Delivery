package Interface;

import BusinessLayer.DeliveryService;
import BusinessLayer.ReadFromFile;
import BusinessLayer.TypeUser;
import BusinessLayer.User;
import Serialization.Serialization;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogInInterface extends JFrame{
    private JPanel login;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;
    private JTextField textField2;
    private List<User> users;
    private DeliveryService d;
    private Serialization s;
    private ReadFromFile r;
    public LogInInterface(){
        d=new DeliveryService();
        s=new Serialization();
        r=new ReadFromFile();
        try {
          //  s.listUsersSerialization(r.readUserFromFile());
            users=s.listUsersDeserialization();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setContentPane(login);
        setTitle("LogIn");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    users=s.listUsersDeserialization();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                String username=textField1.getText();
                char[] pass=passwordField1.getPassword();
                String password=String.valueOf(pass);
                int ok=0;
                for (User t: users) {
                    if(username.equals(t.getUsername()))
                    {    ok=1;
                        if(password.equals(t.getPassword()))
                        {if(t.getTypeuser().equals("employee"))
                              {
                                  dispose();
                                  new EmployeeInterface();
                              }
                             else if(t.getTypeuser().equals("administrator"))
                              {
                                  dispose();
                                  new AdministratorInterface();
                              }
                             else if(t.getTypeuser().equals("client"))
                              {
                                  dispose();
                                  new ClientInterface(t);
                              }}
                        else {
                            textField2.setText("Parola este gresita. Va rugam incercati din nou");
                        }
                }}
                if(ok==0)
                    textField2.setText("Username-ul nu a fost gasit. Va rugam incercati din nou");
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=textField1.getText();
                char[] pass=passwordField1.getPassword();
                String password=String.valueOf(pass);
                try {
                    users=s.listUsersDeserialization();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                User user3=new User(users.size()+1,username,"client",password);
                try {
                    d.userInit(user3);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
