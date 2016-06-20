import org.mybeans.form.FormBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangw on 11/28/15.
 */
public class RegisterForm  extends FormBean{
    private String email;
    private String password;
    private String confirm;
    private String firstName;
    private String lastName;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getValidationErrors(){
        List<String> errors = new ArrayList<String>();

        if (email==null || email.length()==0){errors.add("Email is required");}
        if (password==null || password.length()==0) {errors.add("Password is required");}
        if(firstName==null || firstName.length()==0) {errors.add("FirstName is required");}
        if (lastName == null || lastName.length()==0){errors.add("LastName is required");}
        if(errors.size()>0) return errors;

        if (email.matches(".*[<>\"].*")){
            errors.add("Email may not contain angle brackets or quotes");
        }

        if (firstName.matches(".*[<>\"].*")){
            errors.add("First name may not contain angle brackets or quotes");
        }

        if (lastName.matches(".*[<>\"].*")){
            errors.add("Last name may not contain angle brackets or quotes");
        }

        if (!password.equals(confirm)){
            errors.add("password does not match");
        }
        return errors;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
