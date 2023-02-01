import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class registerForm extends JDialog{
    private JLabel lblFirstName;
    private JTextField txtFirstName;
    private JLabel lblSecondName;
    private JTextField txtSecondName;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JLabel lblPassword;
    private JPasswordField txtPass;
    private JLabel lblConfirmPass;
    private JPasswordField txtConfirmPass;
    private JButton btnRegister;
    private JPanel registerPanel;
    private JButton btnLogIn;
    private JCheckBox captcha;

    Repository repo = new Repository();
    public registerForm(JFrame parent){
        super(parent);
        setTitle("Register menu");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(750,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(captcha.isSelected()){
                    if(captcha()){
                        register();
                    }else{
                        printWrongCaptcha();
                    }
                }else{
                    printErrorMsg();
                }
            }
        });

        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
                new LogInForm(null).setVisible(true);
            }
        });
        setVisible(true);
    }
    public void printWrongCaptcha(){
        JOptionPane.showMessageDialog(this,"Wrong captcha !","Try again",JOptionPane.ERROR_MESSAGE);
    }
    private void printErrorMsg(){
        JOptionPane.showMessageDialog(this,"Select captcha","Try again",JOptionPane.ERROR_MESSAGE);
    }
    private boolean captcha(){
        String str = JOptionPane.showInputDialog(this,"Enter code: xxx");
        if(str.equals("xxx")){
            return true;
        }
        return false;
    }
    private void register() {
        String firstName = txtFirstName.getText();
        String secondName = txtSecondName.getText();
        String email = txtEmail.getText();
        String password = String.valueOf(txtPass.getPassword());
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
            JOptionPane.showMessageDialog(this,"Invalid emaill address","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        User user = new User(firstName,secondName,username,password,email);
        repo.addUser(user);
        JOptionPane.showMessageDialog(this,"User registered successful !");
        txtFirstName.setText("");
        txtSecondName.setText("");
        txtUsername.setText("");
        txtPass.setText("");
        txtConfirmPass.setText("");
        txtEmail.setText("");
        dispose();
        new LogInForm(null).setVisible(true);
    }

    public static void main(String[] args) {
        registerForm registerForm = new registerForm(null);
    }
}
