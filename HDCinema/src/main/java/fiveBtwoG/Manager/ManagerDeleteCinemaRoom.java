package fiveBtwoG.Manager;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import fiveBtwoG.entity.CinemaRoom;

@WebServlet("/deleteCinemaRoom")
public class ManagerDeleteCinemaRoom extends HttpServlet {
	@Override 
	public void doPost(HttpServletRequest Req, HttpServletResponse Res)throws ServletException, IOException{
		String cinemaRoomNum = Req.getParameter("cinemaRoomNum");
		
		boolean result = CinemaRoom.deleteCinemaRoom(cinemaRoomNum);
			Res.getWriter().println(result);
		
	}
}
