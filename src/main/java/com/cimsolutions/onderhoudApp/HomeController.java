package com.cimsolutions.onderhoudApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cimsolutions.DAO.UserDao;
import com.cimsolutions.entities.Apuser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	UserDao dao = new UserDao();
	Apuser currentUser = new Apuser();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		if(currentUser == null){
			return "login";
		} else {
			model.addAttribute("username", currentUser.getUsername());
			return "panel";
		}
	}
	@RequestMapping(value="checkLogin", method=RequestMethod.POST)
	public String checkLogin(@RequestParam("txtName") String name, @RequestParam("txtPass") String pass,
			Model model) {
		boolean result = dao.checkLogin(name, pass);
		currentUser = new Apuser();
		currentUser.setUsername(name);
		
		if (result) {
			model.addAttribute("username", name);
			return "home";
		}else return "invalid";
	}
	@RequestMapping(value = "panel", method = RequestMethod.GET)
	public String panel(Model model) {
		if(currentUser == null){
			return "login";
		} else {
			model.addAttribute("username", currentUser.getUsername());
			return "panel";
		}
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		currentUser = null;
		return "login";
	}
}
