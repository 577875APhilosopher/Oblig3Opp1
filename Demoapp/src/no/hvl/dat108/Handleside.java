package no.hvl.dat108;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Handleside
 */
@WebServlet("/Handleside")
public class Handleside extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Handlevogn handlevogn;
	
	public void init() {
		handlevogn = new Handlevogn();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (!Overgang.Erduinnlogg(request)) {
			response.sendRedirect("Innlogg?error=2");
		} else {
			String password = (String) session.getAttribute("password");
			response.setContentType("text/html; charset=ISO-8859-1");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Handleliste</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Handlevogn</h1>");
			out.println("<form action=\"" + "Handleside" + "\" method=\"post\">");
			out.println("<p><input type=\"submit\" value=\"Legg til\" name=\"leggtil\"> "
					+ "<input type=\"text\" name=\"vare\"></p></form>");
			out.println("<br>");
			for (Vare vare : handlevogn.getVarer()) {
				out.println("<form action =\"" + "Handleside" + "\" method =\"post\">");
				out.print("<p><input type=\"hidden\" name =\"slett\" value=\"" + handlevogn.index(vare) + "\">");
				out.print("<p><input type=\"submit\" value = \"Slett\" > ");
				out.println(vare.GetVarenavn() + "</p></form>");
			}
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession(false);
		if(!Overgang.Erduinnlogg(request)) {
			response.sendRedirect("Innlogg?error=3");
		} else {
			String vareinput = request.getParameter("vare");
			String slettVare = request.getParameter("slett");
			if (slettVare!=null) {
				int doit = Integer.parseInt(Overgang.escapeHtml(slettVare));
				synchronized (handlevogn) {
					handlevogn.fjernVare(doit);
					response.sendRedirect("Handleside");
				}
			} else {
				if ((vareinput != null) && (!vareinput.isEmpty())) {
					String vareinput2 = Overgang.escapeHtml(vareinput);
					if (vareinput2.matches("^\\S(.*)$")) {
						synchronized (handlevogn) {
							handlevogn.LeggTilVare(vareinput2);
						}
					}
				}
				response.sendRedirect("Handleside");
			}
			
		}
	}

}
