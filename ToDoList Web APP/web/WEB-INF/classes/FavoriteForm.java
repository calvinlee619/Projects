import org.mybeans.form.FormBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangw on 11/28/15.
 */
public class FavoriteForm extends FormBean{
    private String url;
    private String comment;


    public String getUrl() {
        return url;
    }

    public String getComment() {
        return comment;
    }

    public String sanitize(String s){
        return s.replace("&","$amp;").replace("<","&lt;")
                .replace(">","&gt;").replace("\"","&quot;");
    }

    public List<String> getValidationErrors(){
        List<String> errors = new ArrayList<String>();
        if (url==null || url.length()==0) errors.add("URL is required");
        if (comment == null || comment.length()==0) errors.add("Comment is required");
        if (errors.size()>0) return errors;

        url = sanitize(url);
        if (!url.startsWith("http://")){
            url = "http://" + url;
        }
        comment = sanitize(comment);
        return errors;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
