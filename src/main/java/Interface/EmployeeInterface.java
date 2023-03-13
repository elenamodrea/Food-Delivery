package Interface;

import javax.swing.*;

public class EmployeeInterface extends JFrame{
    private JPanel employee;
    public EmployeeInterface(){
        setContentPane(employee);
        setTitle("Employee");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
