package filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebFilter("/*")
public class AuthFilter implements Filter {

    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String reqURL = ((HttpServletRequest)request).getRequestURI();
		System.out.println("req url: " + ((HttpServletRequest)request).getRequestURI()); 
		String login = "/";
		String login2 = "/index.html";
		String login3 = "/LoginController";
		String login4 = "/css/style.css";
		ArrayList<String> arr = new ArrayList();
		arr.add(login);
		arr.add(login2);
		arr.add(login3);
		arr.add(login4);
		User u = (User)((HttpServletRequest)request).getSession().getAttribute("user");
		
		if(reqURL != null && arr.contains(reqURL)) {
			System.out.println("logging in");
			chain.doFilter(request, response);
		} else if(u != null) {
			System.out.println("fname: " + u.getFirstName() + " is logged in");
			chain.doFilter(request, response);
		} else {
			System.out.println("not logged in");
			request.getRequestDispatcher("unauthorized.jsp").forward(request, response);
		}
//		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
