package com.cimsolutions.onderhoudApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cimsolutions.DAO.UserDaoImpl;
import com.cimsolutions.DAO.WegenDAOImpl;
import com.cimsolutions.DAO.BaanDAOImpl;
import com.cimsolutions.DAO.DistrictDAOImpl;
import com.cimsolutions.DAO.ReparatieDAOImpl;
import com.cimsolutions.DAO.UserReparatieDAOImpl;
import com.cimsolutions.DAO.StrookDAOImpl;
import com.cimsolutions.entities.Apuser;
import com.cimsolutions.entities.District;
import com.cimsolutions.entities.Userreparatie;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends HttpServlet {
	UserDaoImpl dao = new UserDaoImpl();
	UserReparatieDAOImpl daoUr = new UserReparatieDAOImpl();
	ReparatieDAOImpl daoR = new ReparatieDAOImpl();
	DistrictDAOImpl daoD = new DistrictDAOImpl();
	BaanDAOImpl daoB = new BaanDAOImpl();
	WegenDAOImpl daoW = new WegenDAOImpl();
	StrookDAOImpl daoS = new StrookDAOImpl();
	static Apuser currentUser = new Apuser();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				System.out.println(user.getUsername());
				model.addAttribute("user", user);
				System.out.println("user gevonden");
				model.addAttribute("title", "Gebruikerspaneel");
				return "home";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}

	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	public String checkLogin(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String token = request.getParameter("txtToken");

		boolean result = dao.checkLogin(token);

		if (result) {
			HttpSession session = request.getSession();
			Apuser newUser = new Apuser();
			newUser = dao.getUserByToken(token);
			session.setAttribute("user", newUser);
			Apuser user = (Apuser) session.getAttribute("user");
			System.out.println(user.getUsername());

			model.addAttribute("user", user);
			model.addAttribute("title", "Gebruikerspaneel");
			return "home";
		} else {
			model.addAttribute("error", "Probeer opnieuw");
			model.addAttribute("title", "Invalid Token");
			return "invalid";
		}
	}

	@RequestMapping(value = "paneel", method = RequestMethod.GET)
	public String panel(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				System.out.println(user.getUsername());
				model.addAttribute("user", user);
				System.out.println("user gevonden");
				model.addAttribute("title", "Gebruikerspaneel");
				return "home";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}

	@RequestMapping(value = "loguit", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		model.addAttribute("title", "Logout");
		return "login";
	}

	public static Apuser getCurrentUser() {
		return currentUser;
	}
}
