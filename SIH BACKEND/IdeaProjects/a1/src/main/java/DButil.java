/**
 * Created by munde on 10/03/2018.
 */
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import static spark.route.HttpMethod.get;


public class DButil extends HttpServlet {

    public void  doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String f = request.getParameter("fname");
        String l = request.getParameter("lname");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rakshak", "root", "");

            PreparedStatement ps = con.prepareStatement(
                    "insert into name values(?,?)");

            ps.setString(1, f);
            ps.setString(2, l);

            int i = ps.executeUpdate();
            if (i > 0)
                out.print("You are successfully registered...");


        } catch (Exception e2) {
            System.out.println(e2);
        }

        out.close();
    }

}
