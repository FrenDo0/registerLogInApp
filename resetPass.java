import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class resetPass extends JDialog{
    private JPasswordField txtPass;
    private JPasswordField txtConfirmPass;
    private JButton btnChangePass;
    private JButton btnBack;
    private JPanel resetPanel;
    private JTextField txtUsername;
    private JTextField txtEmail;

    Repository repo = new Repository();
    public resetPass(JFrame parent){
        super(parent);
        setTitle("Create menu");
        setContentPane(resetPanel);
        setMinimumSize(new Dimension(750,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LogInForm(null).setVisible(true);
            }
        });
        btnChangePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(repo.checkUserAndEmail(txtUsername.getText(),txtEmail.getText())){
                    if(String.valueOf(txtPass.getPassword()).equals(String.valueOf(txtConfirmPass.getPassword()))){
                        repo.updatePassword(txtUsername.getText(),String.valueOf(txtPass.getPassword()));
                    }else{
                        JOptionPane.showMessageDialog(null,"Password does not match !","Try again",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Wrong username or email","Try again",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
