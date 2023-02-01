import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class homeForm extends JDialog{
    private JLabel lblWelcome;
    private JButton btnLogOut;
    private JPanel homePanel;
    private JButton btnDelete;
    private JButton btnSelect;
    private JButton btnUpdate;
    private JTextArea txtPanel;
    private JTextField txtUserID;
    private JLabel lbltxt;
    private JButton btnCreate;


    Repository repo = new Repository();

    public homeForm(JFrame parent){
        super(parent);
        setTitle("Home menu");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(750,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LogInForm(null).setVisible(true);
            }
        });

        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String,ArrayList<String>> map = repo.getUsers();
                txtPanel.setText("");
                txtUserID.setText("");
                for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
                    txtPanel.append("User id: " + entry.getKey() + " User information " + entry.getValue() + "\n");
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtUserID.getText());
                    repo.deleteUser(id);
                    txtPanel.setText("");
                    txtUserID.setText("");
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CreateForm(null).setVisible(true);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openForm();
            }
        });
    }
    private void openForm(){
            dispose();
            new UpdateForm(null).setVisible(true);

    }
}
