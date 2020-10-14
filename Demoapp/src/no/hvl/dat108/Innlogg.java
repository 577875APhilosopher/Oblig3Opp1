package no.hvl.dat108;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Innlogg
 */
@WebServlet(name ="Innlogg", urlPatterns= "/Innlogg")
public class Innlogg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String password;
	int tid;
	
	public void init() {
		password =getServletConfig().getInitParameter("password");
		tid = Integer.parseInt(getServletConfig().getInitParameter("Tid"));
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String passordPrompt="Skriv passord: ";
		
		String error = request.getParameter("error");
		
		if (error != null) {
			if (error.equals("1")) {
				passordPrompt="Feil passord";
			} else if (error.equals("2")) {
				passordPrompt ="Logg inn forst";
			} else if (error.equals("3")) {
				passordPrompt="Du har logget ut";
			}
		}
		
		response.setContentType("text/html; charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"" + "Innlogg" + "\" method=\"post\">");
		out.println("<fieldset>");
		out.println("<p>" + passordPrompt + "<br><input type=\"password\" name=\"passord\" /></p>");
		out.println("<p><input type=\"submit\" value=\"Innlogg\" /></p>");
		out.println("</fieldset>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String passord = request.getParameter("passord");

		if (!Overgang.valid(password, passord)) {
			response.sendRedirect("logginn?feilkode=1");

		} else {
			Overgang.innlogg(request, tid);
			response.sendRedirect("Handleside");
	}

}
}
