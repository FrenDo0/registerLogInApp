import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Repository {
    public void addUser(User user){
        String sql = "INSERT INTO users (first_name, second_name, username, password, email) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection(); Statement stmt = conn.createStatement(); PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, ArrayList<String>> getUsers(){
        Map<String,ArrayList<String>> listMap = new LinkedHashMap<>();
        String sql = "SELECT * FROM users";

        try(Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);)
        {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String key = rs.getString("id_user");
                if (!listMap.containsKey(key)){
                    listMap.put(key, new ArrayList<>());
                }
                listMap.get(key).add(rs.getString("first_name"));
                listMap.get(key).add(rs.getString("second_name"));
                listMap.get(key).add(rs.getString("username"));
                listMap.get(key).add(rs.getString("password"));
                listMap.get(key).add(rs.getString("email"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listMap;
    }
    public void deleteUser(int userId){
        String sql = "DELETE FROM users WHERE id_user=?";

        try(Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setInt(1,userId);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean checkID(int userID){
        String sql = "SELECT * FROM users WHERE id_user=?";
        boolean isExisting = false;

        try(Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,userID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                isExisting = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isExisting;
    }

    public void updateUser(int userID,String firstName,String secondName,String username,String password,String email){
        String sql = "UPDATE users SET first_name=?,second_name=?,username=?,password=?,email=? WHERE id_user=?";

        try(Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,firstName);
            ps.setString(2,secondName);
            ps.setString(3,username);
            ps.setString(4,password);
            ps.setString(5,email);
            ps.setInt(6,userID);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updatePassword(String username,String password){
        String sql = "UPDATE users SET password=? WHERE username=?";

        try(Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,password);
            ps.setString(2,username);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean checkUserAndEmail(String username,String email){
        String sql = "SELECT * FROM users WHERE username=? AND email=?";
        boolean isExisting = false;

        try(Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,username);
            ps.setString(2,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                isExisting = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isExisting;
    }
    public boolean checkUsername(String username){
        String sql = "SELECT * FROM users WHERE username=?";
        boolean isExisting = false;
        try(Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                isExisting = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isExisting;
    }
    public boolean checkExisting(String username,String password){
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        boolean isExisting = false;

        try(Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                isExisting = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isExisting;
    }

    public boolean checkEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
