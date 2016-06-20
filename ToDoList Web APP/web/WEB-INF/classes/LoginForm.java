import org.mybeans.form.FormBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangw on 11/28/15.
 */
public class LoginForm extends FormBean{
    private String email;
    private String password;
    private String button;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

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

    public List<String> getValidationErrors(){
        List<String> errors = new ArrayList<String>();

        if (email==null || email.length()==0){errors.add("Email is required");}
        if (password==null || password.length()==0) {errors.add("Password is required");}
        if (button == null) {errors.add("action is required");}

        if (errors.size()>0) return errors;

        if (!button.equals("login")) errors.add("illegal button");
        if (email.matches(".*[<>\"].*")){
            email = sanitize(email);
            errors.add("Email may not contain angle brackets or quotes");
        }

        return errors;
    }

    public String sanitize(String s){
        return s.replace("&","$amp;").replace("<","&lt;")
                .replace(">","&gt;").replace("\"","&quot;");
    }
}
