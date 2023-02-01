import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogInForm extends JDialog{
    private JButton btnLogIn;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPanel logInPanel;
    private JButton btnResetPass;
    private JButton btnRegister;

    Repository repo = new Repository();

    public LogInForm(JFrame parent){
        super(parent);
        setTitle("Log in menu");
        setContentPane(logInPanel);
        setMinimumSize(new Dimension(750,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                registerForm rg = new registerForm(null);
                rg.setVisible(true);
            }
        });
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = String.valueOf(txtPassword.getPassword());
                if(repo.checkExisting(username,password)){
                    dispose();
                    homeForm hf = new homeForm(null);
                    hf.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Wrong username or password !","Try again",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnResetPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(repo.checkUsername(txtUsername.getText())){
                    dispose();
                    new resetPass(null).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Username doesnt exist !","Try again",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
