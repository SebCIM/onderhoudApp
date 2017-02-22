package com.cimsolutions.onderhoudApp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cimsolutions.DAO.UserDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "login";
	}
	@RequestMapping(value="checkLogin", method=RequestMethod.POST)
	public String checkLogin(@RequestParam("txtName") String name, @RequestParam("txtPass") String pass,
			Model model) {
		UserDao dao = new UserDao();
		boolean result = dao.checkLogin(name, pass);
		if (result) {
			model.addAttribute("USER", name);
			return "home";
		}else return "invalid";
	}
}
