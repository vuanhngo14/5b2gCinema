package fiveBtwoG.Manager;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.Gson;
import fiveBtwoG.entity.CinemaRoom;

@WebServlet("/viewCinemaRoom")
public class ManagerViewCinemaRoom extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest Req, HttpServletResponse Res)throws ServletException, IOException{
		String cinemaRoomNum = Req.getParameter("cinemaRoomNum");
		CinemaRoom item = CinemaRoom.searchCinemaRoom(cinemaRoomNum);

		final Gson gson = new Gson();
		
		String json = gson.toJson(item);
		Res.setContentType("application/json");//set content type to 
		Res.setCharacterEncoding("UTF-8");
		Res.getWriter().write(json);
	}
}
