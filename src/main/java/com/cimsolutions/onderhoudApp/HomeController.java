package com.cimsolutions.onderhoudApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cimsolutions.DAO.UserDaoImpl;
import com.cimsolutions.entities.Apuser;
import com.cimsolutions.service.UserService;
import com.cimsolutions.service.UserServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	UserDaoImpl dao = new UserDaoImpl();
	private UserDaoImpl userDaoImpl;
	Apuser currentUser = new Apuser();

	private UserService userService;

	private UserServiceImpl userServiceImpl;

	@Qualifier(value = "userService")
	public void setUserService(UserService us) {
		this.userService = us;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		if (currentUser == null) {
			return "login";
		} else {
			model.addAttribute("username", currentUser.getUsername());
			return "panel";
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
			return "home";
		} else {
			model.addAttribute("error", token + " is not valid!");
			return "invalid";
		}
	}

	@RequestMapping(value = "panel", method = RequestMethod.GET)
	public String panel(Model model) {
		if (currentUser == null) {
			System.out.println("geen user gevonden");
			return "login";
		} else {
			model.addAttribute("user", currentUser);
			System.out.println("user gevonden");
			return "panel";
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		currentUser = null;
		return "login";
	}
	
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String showUsers(Model model) {
		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			model.addAttribute("listUsers", dao.listUsers());
			model.addAttribute("user", currentUser);
			System.out.println("usersessie gevonden");
			return "users";
		}
	}
	
	@RequestMapping(value = "user/edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("Apuser") Apuser u) {
		
		dao.updateUser(u);
		
		return "redirect:/users";
	}

	@RequestMapping(value = "user/edit/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") int id, Model model) {
		currentUser = dao.getUserById(id);

		model.addAttribute("user", currentUser);
		model.addAttribute("listUsers", dao.listUsers());
		return "user";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("Apuser") Apuser u) {

		dao.addUser(u);

		return "redirect:/users";

	}
	
	@RequestMapping(value = "/user/remove/{id}", method = RequestMethod.GET)
	public String removeUser(@PathVariable("id") int id, Model model) {
		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			dao.removeUser(id);
			model.addAttribute("user", currentUser);
			System.out.println("usersessie gevonden");
			return "redirect:/users";
		}
	}
}
