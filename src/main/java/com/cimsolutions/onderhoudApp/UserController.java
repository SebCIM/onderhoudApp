package com.cimsolutions.onderhoudApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cimsolutions.DAO.UserDaoImpl;
import com.cimsolutions.DAO.WegenDAOImpl;
import com.cimsolutions.DAO.BaanDAOImpl;
import com.cimsolutions.DAO.DistrictDAOImpl;
import com.cimsolutions.DAO.ReparatieDAOImpl;
import com.cimsolutions.DAO.UserReparatieDAOImpl;
import com.cimsolutions.DAO.StrookDAOImpl;
import com.cimsolutions.entities.Apuser;
import com.cimsolutions.entities.District;
import com.cimsolutions.service.EmailService;

import java.util.List;

import javax.mail.MessagingException;

@Controller
public class UserController {
	UserDaoImpl dao = new UserDaoImpl();
	UserReparatieDAOImpl daoUr = new UserReparatieDAOImpl();
	ReparatieDAOImpl daoR = new ReparatieDAOImpl();
	DistrictDAOImpl daoD = new DistrictDAOImpl();
	BaanDAOImpl daoB = new BaanDAOImpl();
	WegenDAOImpl daoW = new WegenDAOImpl();
	StrookDAOImpl daoS = new StrookDAOImpl();

	@RequestMapping(value = "gebruikers", method = RequestMethod.GET)
	public String showUsers(Model model) {
		
		Apuser currentUser = HomeController.getCurrentUser();
		if (currentUser == null) {
			return "login";
		} else {
			if (currentUser.getIsAdmin() == false) {
				return "home";
			} else {
				model.addAttribute("listUsers", dao.listUsers());
				model.addAttribute("listDistricten", daoD.listDistrict());
				model.addAttribute("user", currentUser);
				model.addAttribute("title", "Gebruikers");
				return "users";
			}
		}
	}

	@RequestMapping(value = "gebruiker/aanpassen", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("Apuser") Apuser u) {
		dao.updateUser(u);

		return "redirect:/gebruikers";
	}

	@RequestMapping(value = "gebruiker/aanpassen/{id}", method = RequestMethod.GET)

	public String editUser(@PathVariable("id") int id, Model model) {
		Apuser currentUser = HomeController.getCurrentUser();
		if (currentUser == null) {
			return "login";
		} else {
			if (currentUser.getIsAdmin() == false) {
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

	@RequestMapping(value = "/gebruiker/toevoegen", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("Apuser") Apuser u) throws MessagingException {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");

		// mail addresses
		String toAannemer = u.getEmail();

		// subjects
		String subjectAannemer = "Token Vorstschade Aangifte";

		// body
		String emailBody = "Hierbij ontvangt u de inlog token voor het doorgeven van uw Vorstschade. Dit kan via het volgende adres: http://vorstschadeapp.com. Uw token is:"
				+ u.getToken();

		EmailService mm = (EmailService) context.getBean("EmailService");
		mm.sendMail("vorstschade@gmail.com", toAannemer, subjectAannemer, emailBody);

		dao.addUser(u);

		return "redirect:/gebruikers";

	}

	@RequestMapping(value = "/gebruiker/verwijderen/{id}", method = RequestMethod.GET)
	public String removeUser(@PathVariable("id") int id, Model model) {
		Apuser currentUser = HomeController.getCurrentUser();
		if (currentUser == null) {
			return "login";
		} else {
			dao.removeUser(id);
			model.addAttribute("user", currentUser);
			model.addAttribute("title", "Remove");
			System.out.println("usersessie gevonden");
			return "redirect:/gebruikers";
		}
	}

}
