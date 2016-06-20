import org.mybeans.form.FormBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangw on 12/12/15.
 */
public class ChangePwdForm extends FormBean {
    private String password1;
    private String password2;

    public String sanitize(String s){
        return s.replace("&","$amp;").replace("<","&lt;")
                .replace(">","&gt;").replace("\"","&quot;");
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public List<String> getValidationErrors(){
        List<String> errors = new ArrayList<String>();

        if (password1==null || password1.length()==0){errors.add("Password1 is required");}
        if (password2==null || password2.length()==0) {errors.add("Password2 is required");}
        if(errors.size()>0) return errors;

        if (password1.matches(".*[<>\"].*")){
            errors.add("password1 may not contain angle brackets or quotes");
        }

        if (password2.matches(".*[<>\"].*")){
            errors.add("password2 name may not contain angle brackets or quotes");
        }

        if (!password1.equals(password2)){
            errors.add("Password does not match");
        }
        return errors;
    }


}
