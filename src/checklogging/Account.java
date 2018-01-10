package checklogging;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseManagement;

/**
 * Servlet implementation class Account
 */
public class Account extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // check account
        DatabaseManagement account = new DatabaseManagement();
        try {
            if (account.isAccount(userName, password)) {
                
                //isAccount go to success page
                String yourName = account.getYourName(userName);
                
                //create a session for user
                HttpSession session = request.getSession();
                session.setAttribute("yourName", yourName);
                response.sendRedirect("success");
            } else {
                //if incorrect account, return page login.
                response.sendRedirect("login");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
