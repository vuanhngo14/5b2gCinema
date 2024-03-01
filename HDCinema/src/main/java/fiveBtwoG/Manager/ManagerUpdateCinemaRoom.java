package fiveBtwoG.Manager;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import fiveBtwoG.entity.CinemaRoom;

@WebServlet("/updateCinemaRoom")
public class ManagerUpdateCinemaRoom extends HttpServlet{

	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String cinemaRoomNum = request.getParameter("cinemaRoomNum");
		String cinemaRoomType = request.getParameter("cinemaRoomType");
		
		boolean updatecr = CinemaRoom.updateCinemaRoom(cinemaRoomNum, cinemaRoomType);
		response.getWriter().println(updatecr);
		
	}
}
