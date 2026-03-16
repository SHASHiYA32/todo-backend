package com.taskflow;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class GetUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");

        response.setContentType("application/json");

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,Integer.parseInt(userId));

            ResultSet rs = ps.executeQuery();

            PrintWriter out = response.getWriter();

            if(rs.next()){

                out.print("{"
                        + "\"name\":\""+rs.getString("name")+"\","
                        + "\"email\":\""+rs.getString("email")+"\","
                        + "\"password\":\""+rs.getString("password")+"\""
                        + "}");
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
