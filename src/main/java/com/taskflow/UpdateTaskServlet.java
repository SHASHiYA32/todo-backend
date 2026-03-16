package com.taskflow;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class UpdateTaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String status = request.getParameter("status");

        response.setContentType("application/json");

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "UPDATE tasks SET status=? WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1,status);
            ps.setInt(2,Integer.parseInt(id));

            int result = ps.executeUpdate();

            PrintWriter out = response.getWriter();

            if(result>0)
                out.print("{\"status\":\"success\"}");
            else
                out.print("{\"status\":\"failed\"}");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
