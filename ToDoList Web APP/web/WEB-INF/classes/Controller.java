import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.genericdao.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kangw on 12/11/15.
 */
public class Controller extends HttpServlet {

    public void init() throws ServletException {

        try {
            Model model = new Model(getServletConfig());

            Action.addAction(new AddFavoriteAction(model));
            Action.addAction(new LoginAction(model));
            Action.addAction(new RegisterAction(model));
            Action.addAction(new ManageListAction(model));
            Action.addAction(new ClickLinkAction(model));
            Action.addAction(new ChangePwdAction(model));
            Action.addAction(new OtherUserListAction(model));
            Action.addAction(new LogoutAction(model));
            Action.addAction(new DeleteFavoriteAction(model));
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage, request, response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doGet(request,response);
    }

    /*
     * Extracts the requested action and (depending on whether the user is
     * logged in) perform it.
     *
     * @param request
     *
     * @return the next page (the view)
     */
    public String performTheAction(HttpServletRequest request){
        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();
        UserBean user = (UserBean)session.getAttribute("user");
        String action = getActionName(servletPath);

        if (user == null && (action.equals("add.do") || action.equals("change-pwd.do") ||
                action.equals("manageList.do") || action.equals("delete.do"))){
            return Action.perform("login.do",request);
        }

        return Action.perform(action,request);
    }

    /*
     * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
     * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
     * page (the view) This is the common case
     */
    public void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (nextPage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getServletPath());
            return;
        }

        if (nextPage.endsWith(".do")){
            response.sendRedirect(nextPage);
            return;
        }

        if (nextPage.endsWith(".jsp")){
            RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
            d.forward(request, response);
            return;
        }

        if (nextPage.startsWith("http://")){
            response.sendRedirect(nextPage);
            return;
        }
        throw new ServletException(Controller.class.getName()
                + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }



    private String getActionName(String s){
        int index = s.lastIndexOf("/");
        return s.substring(index+1);
    }
}
