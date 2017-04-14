package com.cimsolutions.onderhoudApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import com.cimsolutions.service.EmailService;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public String showUsers(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				model.addAttribute("listUsers", dao.listUsers());
				model.addAttribute("listDistricten", daoD.listDistrict());
				model.addAttribute("user", user);
				model.addAttribute("title", "Gebruikers");
				return "users";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}

	@RequestMapping(value = "gebruiker/aanpassen", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("Apuser") Apuser u, @RequestParam("districtId") int diId,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				u.setDistrict(daoD.getDistrictById(diId));
				dao.updateUser(u);

				return "redirect:/gebruikers";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}

	@RequestMapping(value = "gebruiker/aanpassen/{id}", method = RequestMethod.GET)

	public String editUser(@PathVariable("id") int id, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				if (user.getIsAdmin() == false) {
					return "home";
				} else {
					Apuser editUser = new Apuser();
					editUser = dao.getUserById(id);

					model.addAttribute("user", user);
					model.addAttribute("edituser", editUser);
					model.addAttribute("listUsers", dao.listUsers());
					model.addAttribute("listDistricten", daoD.listDistrict());
					model.addAttribute("title", "Gebruiker -" + editUser.getUsername());
					return "user";
				}
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}

	@RequestMapping(value = "gebruiker/toevoegen", method = RequestMethod.POST)
	public String addRepair(@ModelAttribute("Apuser") Apuser u, @RequestParam("districtId") int diId,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				District districtLijst = daoD.getDistrictById(diId);
				u.setDistrict(districtLijst);

				ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");

				// mail addresses
				String toAannemer = u.getEmail();

				// subjects
				String subjectAannemer = "Identificatie Code Vorstschadedatabase";

				// body
				String emailBody = "Geachte vorstschadecontactpersoon,<br /><br /> Hierbij ontvangt u de identificatie code voor het doorgeven van uw Vorstschade. Dit kan via het volgende adres: http://vorstschadeapp.com.<br /><br />Uw identificatie code:<br /><br />" + u.getToken() + "<br /><br />Met vriendelijke groeten,<br />Steunpunt Wegen en Geotechniek<br />steunpunt-wegenbouw@rws.nl<br />T: 088 79 82 500";

				EmailService mm = (EmailService) context.getBean("EmailService");
				mm.sendMail("vorstschade@gmail.com", toAannemer, subjectAannemer, emailBody);

				dao.addUser(u);

				System.out.println("test");

				return "redirect:/gebruikers";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}

	@RequestMapping(value = "/gebruiker/verwijderen/{id}", method = RequestMethod.GET)
	public String removeUser(@PathVariable("id") int id, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				if (user.getIsAdmin() == false) {
					return "home";
				} else {
					dao.removeUser(id);
					model.addAttribute("user", user);
					model.addAttribute("title", "Remove");
					System.out.println("usersessie gevonden");
					return "redirect:/gebruikers";
				}
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}
}
