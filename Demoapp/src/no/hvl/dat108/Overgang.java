package no.hvl.dat108;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Overgang {

	
	public static boolean valid(String password, String RPassword) {
		return password.equals(RPassword);
	}

	public static boolean Erduinnlogg(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session != null)
				&& (session.getAttribute("innlogget") != null);
	}
	public static void innlogg(HttpServletRequest request, int tid) {
		loggUt(request);
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(tid);
		session.setAttribute("password", "password");
    	session.setAttribute("handlevogn", new Handlevogn());
    	session.setAttribute("innlogget", "ja");
}
	
	public static void loggUt(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
}
	
	public static String escapeHtml (String s) {
		String resultat = s;
		resultat = resultat.replaceAll("<", "&lt;");
		resultat = resultat.replaceAll(">", "&gt;");
		resultat = resultat.replaceAll("\"", "&quot;");
		resultat = resultat.replaceAll("&", "&amp;");
		resultat = resultat.replaceAll(" ", "");
		return resultat;
	}
}
