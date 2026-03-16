package com.taskflow;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class DeleteTaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        response.setContentType("application/json");

        try{

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM tasks WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,Integer.parseInt(id));

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
