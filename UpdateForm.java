import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateForm extends JDialog{
    private JPanel updatePanel;
    private JButton btnUpdate;
    private JButton btnBack;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JTextField txtUsername;
    private JTextField txtSecondName;
    private JTextField txtFirstName;
    private JTextField txtEmail;
    private JTextField txtUserID;

    Repository repo = new Repository();
    public UpdateForm(JFrame parent){

        super(parent);
        setTitle("Update menu");
        setContentPane(updatePanel);
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
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxes();
                if(repo.checkID(Integer.parseInt(txtUserID.getText())) == true){
                    repo.updateUser(Integer.parseInt(txtUserID.getText()), txtFirstName.getText(),
                            txtSecondName.getText(),txtUsername.getText(),
                            String.valueOf(txtPassword.getPassword()),txtEmail.getText());
                    txtUserID.setText("");
                    txtFirstName.setText("");
                    txtSecondName.setText("");
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtConfirmPassword.setText("");
                    txtEmail.setText("");
                }else{
                   System.out.println("Invalid user id !");
                    txtUserID.setText("");
                    txtFirstName.setText("");
                    txtSecondName.setText("");
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtConfirmPassword.setText("");
                    txtEmail.setText("");
                }
            }
        });
    }
    private void checkBoxes(){
        String firstName = txtFirstName.getText();
        String secondName = txtSecondName.getText();
        String email = txtEmail.getText();
        String password = String.valueOf(txtPassword.getPassword());
        String confirmPassword = String.valueOf(txtConfirmPassword.getPassword());
        String username = txtUsername.getText();
        String id = txtUserID.getText();
        if(firstName.isEmpty() || secondName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || username.isEmpty() || id.isEmpty()){
            JOptionPane.showMessageDialog(this,"Enter all fields","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this,"Please confirm your password","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
