package registration;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseManagement;

/**
 * Servlet implementation class NewAccount
 */
public class NewAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        //get information from register.jsp page
        String yourName = request.getParameter("yourName");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        //account is management and get data from database 
        DatabaseManagement account = new DatabaseManagement();

        try {
            if (account.isExistAccount(userName)) {
                //go to login page if account exist
                response.sendRedirect("login");
            } else {
                //register for new account
                account.createNewAccount(yourName, userName, password);
                HttpSession session = request.getSession();
                session.setAttribute("yourName", yourName);
                response.sendRedirect("success");
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
