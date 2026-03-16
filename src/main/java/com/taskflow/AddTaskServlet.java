package com.taskflow;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AddTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String title = request.getParameter("title");

        response.setContentType("application/json");

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO tasks(user_id,title) VALUES(?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,Integer.parseInt(userId));
            ps.setString(2,title);

            int result = ps.executeUpdate();

            PrintWriter out = response.getWriter();

            if(result > 0)
                out.print("{\"status\":\"success\"}");
            else
                out.print("{\"status\":\"failed\"}");

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req,res);
    }
}
