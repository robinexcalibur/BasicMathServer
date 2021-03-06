package net.codejava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MathServlet
 */
@WebServlet("/MathServlet")
public class MathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MathServlet() {
        super();
    }

    private ArrayList<String> array = new ArrayList<String>();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		handleMessage(request);
		response.sendRedirect("/index.html");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		for(int i = 0; i < array.size(); i++){
			response.getOutputStream().println("<p>" + array.get(i) + "</p>");
		}
	}
	
	//handles placing the message in the array.
	private void handleMessage (HttpServletRequest request) {
		String equation = request.getParameter("equation");
		array.add(0, mathSolver(equation));
		
		while (array.size() > 10) {
			array.remove(array.size()-1);
		}
		
	}
	
	//Handles solving the math equation sent. It returns a notice if the string's incorrectly formated.
	private String mathSolver (String s) {
		String[] split = s.split(" ", 3);
		int a, b;
		String equation = s;
		try {
	        a = Integer.parseInt(split[0]);
	        b = Integer.parseInt(split[2]);
	    } catch (NumberFormatException nfe) {
	    	return "Equation formated incorrectly: " + s;
	    }
		
		switch(split[1].charAt(0)) {
		case 'x':
		case 'X':
		case '*':
			equation+= " = " + (a * b);
			break;
		case '%':
		case '/':
			equation+= " = " + (a / b);
			break;
		case '-':
			equation+= " = " + (a - b);
			break;
		case '+':
			equation+= " = " + (a + b);
			break;
		}
		
		return equation;
	}
	
}



