package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fiveBtwoG.entity.Account;

@WebServlet("/getAccountList")
public class GetAccountList extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//System.out.println("Ajax called");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ArrayList<Account> returnList = new ArrayList<Account>();

        try {

            returnList = Account.getAccountList();
            //System.out.println(new Gson().toJson(returnList));
            out.println(new Gson().toJson(returnList));

        } finally {
            out.close();
        }

    }
}
