import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class CreateForm extends JDialog{
    private JPanel createPanel;
    private JLabel lblFirstName;
    private JTextField txtFirstName;
    private JTextField txtSecondName;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPass;
    private JTextField txtEmail;
    private JButton btnSave;
    private JButton btnBack;

    Repository repo = new Repository();
    public CreateForm(JFrame parent) {
        super(parent);
        setTitle("Create menu");
        setContentPane(createPanel);
        setMinimumSize(new Dimension(750,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new homeForm(null).setVisible(true);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
    }
    private void register() {
        String firstName = txtFirstName.getText();
        String secondName = txtSecondName.getText();
        String email = txtEmail.getText();
        String password = String.valueOf(txtPassword.getPassword());
        String confirmPassword = String.valueOf(txtConfirmPass.getPassword());
        String username = txtUsername.getText();

        if(firstName.isEmpty() || secondName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || username.isEmpty()){
            JOptionPane.showMessageDialog(this,"Enter all fields","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this,"Please confirm your password","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!repo.checkEmail(email)){
            JOptionPane.showMessageDialog(this,"Invalid email !","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        User user = new User(firstName,secondName,username,password,email);
        repo.addUser(user);
        JOptionPane.showMessageDialog(this,"User registered successful !");
        txtFirstName.setText("");
        txtSecondName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPass.setText("");
        txtEmail.setText("");
        dispose();
        new homeForm(null).setVisible(true);
    }
}
