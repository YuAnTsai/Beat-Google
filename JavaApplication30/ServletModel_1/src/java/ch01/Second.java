package ch01;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 
import java.io.IOException;
import java.io.UnsupportedEncodingException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class Second extends HttpServlet {
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        try {
            // 說明瀏覽器送來之使用者所輸入資料的編碼
            request.setCharacterEncoding("UTF-8");
            // 讀取使用者在 <input name='visitor' ...>標籤內所輸入的資料，放入變數 name內
            String name = request.getParameter("visitor");
               // 將name資料放入request物件內，成為它屬性物件，屬性物件可以讓別的程式共用。
              request.setAttribute("visitName", name);
            RequestDispatcher rd = request.getRequestDispatcher("second.jsp");
              rd.forward(request, response);
        } catch (UnsupportedEncodingException e) {
            throw new ServletException(e);
        }
    }
}