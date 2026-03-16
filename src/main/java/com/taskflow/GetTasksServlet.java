package com.taskflow;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class GetTasksServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");

        response.setContentType("application/json");

        try{

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM tasks WHERE user_id=? ORDER BY id DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,Integer.parseInt(userId));

            ResultSet rs = ps.executeQuery();

            PrintWriter out = response.getWriter();

            StringBuilder json =
                    new StringBuilder("[");

            while(rs.next()){

                json.append("{")
                        .append("\"id\":").append(rs.getInt("id")).append(",")
                        .append("\"title\":\"").append(rs.getString("title")).append("\"")
                        .append("},");

            }

            if(json.charAt(json.length()-1)==','){
                json.deleteCharAt(json.length()-1);
            }

            json.append("]");

            out.print(json);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
