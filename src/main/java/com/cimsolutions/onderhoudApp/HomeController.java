package com.cimsolutions.onderhoudApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class HomeController {
	UserDaoImpl dao = new UserDaoImpl();
	UserReparatieDAOImpl daoUr = new UserReparatieDAOImpl();
	ReparatieDAOImpl daoR = new ReparatieDAOImpl();
	DistrictDAOImpl daoD = new DistrictDAOImpl();
	BaanDAOImpl daoB = new BaanDAOImpl();
	WegenDAOImpl daoW = new WegenDAOImpl();
	StrookDAOImpl daoS = new StrookDAOImpl();
	static Apuser currentUser = new Apuser();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		currentUser = dao.getUserByToken("admin");
		if (currentUser == null) {
			model.addAttribute("title", "Login");
			return "login";
		} else {
			model.addAttribute("user", currentUser);
			model.addAttribute("title", "Login Paneel");
			return "home";
		}
	}

	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	public String checkLogin(@RequestParam("txtToken") String token, Model model) {
		boolean result = dao.checkLogin(token);
		// boolean getId = dao.checkLogin(token);
		if (result) {
			currentUser = new Apuser();
			currentUser.setToken(token);
			currentUser = dao.getUserByToken(token);
			model.addAttribute("user", currentUser);
			model.addAttribute("title", "Gebruikerspaneel");
			return "home";
		} else {
			model.addAttribute("error", "Probeer opnieuw");
			model.addAttribute("title", "Invalid Token");
			return "invalid";
		}
	}

	@RequestMapping(value = "paneel", method = RequestMethod.GET)
	public String panel(Model model) {
		if (currentUser == null) {
			System.out.println("geen user gevonden");
			return "login";
		} else {
			model.addAttribute("user", currentUser);
			System.out.println("user gevonden");
			model.addAttribute("title", "Gebruikerspaneel");
			return "home";
		}
	}

	@RequestMapping(value = "loguit", method = RequestMethod.GET)
	public String logout(Model model) {
		currentUser = null;
		model.addAttribute("title", "Logout");
		return "login";
	}
	
	public static Apuser getCurrentUser() {
		return currentUser;
	}
}
