package br.com.servletlib;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
 
@WebServlet("/DoubleMeServlet")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public MainServlet() {
        super();
 
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        response.getOutputStream().println("Hurray !! This Servlet Works");
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        try {
        	JSONObject jsonRequest = new JSONObject(request.getParameter("request"));
        	String action = jsonRequest.getString("action");
        	
        	if (action.equals("1")) { //acao de login
        		JSONObject jsonRequestLogin = jsonRequest.getJSONObject("param");
        		
        		String userName = jsonRequestLogin.getString("UserName");
        		String password = jsonRequestLogin.getString("Password");
        		
        		System.out.println("Credenciais - Usuario: " + userName + " - Senha: " + password);
        	}
        	
            //response.setStatus(HttpServletResponse.SC_OK);
        	response.setContentType("application/json");
            OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", 2);
            //Integer doubledValue = Integer.parseInt(recievedString) * 2;
 
            writer.write(jsonResponse.toString());
            writer.flush();
            writer.close();
 
 
 
        } catch (IOException  e) {
 
 
            try{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(e.getMessage());
                response.getWriter().close();
            } catch (IOException ioe) {
            }
        } catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
        }
 
}