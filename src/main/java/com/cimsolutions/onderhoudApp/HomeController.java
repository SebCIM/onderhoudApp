package com.cimsolutions.onderhoudApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.cimsolutions.entities.Baan;
import com.cimsolutions.entities.District;
import com.cimsolutions.entities.Reparatie;
import com.cimsolutions.entities.Strook;
import com.cimsolutions.entities.Userreparatie;
import com.cimsolutions.entities.Wegenlijst;

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
	Apuser currentUser = new Apuser();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
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
			model.addAttribute("error", token + " is not valid!");
			model.addAttribute("title", "Invalid Token");
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
			model.addAttribute("title", "Gebruikerspaneel");
			return "home";
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model) {
		currentUser = null;
		model.addAttribute("title", "Logout");
		return "login";
	}

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String showUsers(Model model) {
		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			if (this.currentUser.getIsAdmin() == false) {
				return "home";
			} else {
				model.addAttribute("listUsers", dao.listUsers());
				model.addAttribute("user", currentUser);
				model.addAttribute("title", "Gebruikers");
				System.out.println("usersessie gevonden");
				return "users";
			}
		}
	}

	@RequestMapping(value = "user/edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("Apuser") Apuser u) {

		dao.updateUser(u);
		System.out.println("controller opgevangen");

		return "redirect:/users";
	}

	@RequestMapping(value = "user/edit/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") int id, Model model) {
		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			if (this.currentUser.getIsAdmin() == false) {
				return "home";
			} else {
				Apuser editUser = new Apuser();
				editUser = dao.getUserById(id);

				model.addAttribute("user", currentUser);
				model.addAttribute("edituser", editUser);
				model.addAttribute("listUsers", dao.listUsers());
				model.addAttribute("title", "Gebruiker -" + editUser.getUsername());
				return "user";
			}
		}
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
			model.addAttribute("title", "Remove");
			System.out.println("usersessie gevonden");
			return "redirect:/users";
		}
	}

	@RequestMapping(value = "repair", method = RequestMethod.GET)
	public String Invoer(Model model) {
		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			ArrayList<String> soortList = new ArrayList<String>();
			List<String> list = new ArrayList<String>();
			List<District> listDistrict = daoD.listDistrict();
			List<Baan> listBaan = daoB.listBaan();
			List<Wegenlijst> listWegen = daoW.listWegen();
			List<Strook> listStrook = daoS.listStrook();
			list.add("List A");
			list.add("List B");
			list.add("List C");
			list.add("List D");
			list.add("List 1");
			list.add("List 2");
			list.add("List 3");
			soortList.add("Rafeling");
			soortList.add("Gaten");
			soortList.add("Open naden");

			model.addAttribute("user", currentUser);
			model.addAttribute("list", list);
			model.addAttribute("ListDistrict", listDistrict);
			model.addAttribute("ListStrook", listStrook);
			model.addAttribute("ListWegen", listWegen);
			model.addAttribute("ListBaan", listBaan);
			model.addAttribute("soortList", soortList);
			return "invoer";
		}
	}

	@RequestMapping(value = "overzicht", method = RequestMethod.GET)
	public String showRepairs(Model model) {
		List<Userreparatie> reparatieList = daoUr.listReparaties();

		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			model.addAttribute("listReparaties", reparatieList);
			model.addAttribute("user", currentUser);
			model.addAttribute("title", "Gebruikers");
			System.out.println("usersessie gevonden");
			return "reparaties";
		}
	}

	@RequestMapping(value = "repair/view/{id}", method = RequestMethod.GET)
	public String viewRepair(@PathVariable("id") int id, Model model) {
		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			if (this.currentUser.getIsAdmin() == false) {
				System.out.println("Geen admin rechten");
				return "home";
			} else {
				Userreparatie viewReparatie = new Userreparatie();
				viewReparatie = daoUr.getReparatieById(id);
				
				System.out.println(viewReparatie.getApuser().getUsername());

				model.addAttribute("user", currentUser);
				model.addAttribute("reparatie", viewReparatie);
				model.addAttribute("listUsers", dao.listUsers());
				model.addAttribute("title", "Gebruiker -" + currentUser.getUsername());
				return "reparatie";
			}
		}
	}
	
	@RequestMapping(value = "repair/add", method = RequestMethod.POST)
	public String addRepair(@ModelAttribute("Reparatie") Reparatie r) {
		daoUr.addReparatie(r, currentUser.getId());

		return "redirect:/overzicht";
	}
	
	@RequestMapping(value = "/repair/remove/{id}", method = RequestMethod.GET)
	public String removeRepair(@PathVariable("id") int id, Model model) {
		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			
			Userreparatie ur = daoUr.removeUserRepair(id);
			daoR.removeRepair(ur.getReparatie().getId());
			
			model.addAttribute("user", currentUser);
			model.addAttribute("title", "Remove");
			System.out.println("usersessie gevonden");
			return "redirect:/overzicht";
		}
	}
}
