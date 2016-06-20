import org.genericdao.PrimaryKey;

/**
 * Created by kangw on 11/27/15.
 */
@PrimaryKey("id")
public class UserBean implements Comparable<UserBean>{
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public String getPassword()        { return password; }
    public String getEmail()        { return email; }
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public int getId() {return id;}

    public void setPassword(String s)  { password = s;    }
    public void setEmail(String s)  { email = s;    }
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int compareTo(UserBean other) {
        // Order first by lastName and then by firstName
        int c = lastName.compareTo(other.lastName);
        if (c != 0) return c;
        c = firstName.compareTo(other.firstName);
        if (c != 0) return c;
        return email.compareTo(other.email);
    }
}
