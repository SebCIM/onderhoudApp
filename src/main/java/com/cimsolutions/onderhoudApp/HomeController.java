package com.cimsolutions.onderhoudApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.cimsolutions.entities.Strook;
import com.cimsolutions.entities.Userreparatie;
import com.cimsolutions.entities.Wegenlijst;
import com.cimsolutions.service.EmailService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping(value = "gebruikers", method = RequestMethod.GET)
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

	@RequestMapping(value = "gebruiker/aanpassen", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("Apuser") Apuser u) {

		dao.updateUser(u);
		System.out.println("controller opgevangen");

		return "redirect:/gebruikers";
	}

	@RequestMapping(value = "gebruiker/aanpassen/{id}", method = RequestMethod.GET)
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

	@RequestMapping(value = "/gebruiker/toevoegen", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("Apuser") Apuser u)  throws MessagingException {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
		
		// mail addresses
		String toAannemer = u.getEmail();

		// subjects
		String subjectAannemer = "Token Vorstschade Aangifte";
		
		// body
		String emailBody = "Hierbij ontvangt u de inlog token voor het doorgeven van uw Vorstschade. Dit kan via het volgende adres: http://vorstschadeapp.com. Uw token is:" + u.getToken();

		EmailService mm = (EmailService) context.getBean("EmailService");
	        mm.sendMail("vorstschade@gmail.com", toAannemer, subjectAannemer, emailBody);


		dao.addUser(u);

		return "redirect:/gebruikers";

	}

	@RequestMapping(value = "/gebruiker/verwijderen/{id}", method = RequestMethod.GET)
	public String removeUser(@PathVariable("id") int id, Model model) {
		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			dao.removeUser(id);
			model.addAttribute("user", currentUser);
			model.addAttribute("title", "Remove");
			System.out.println("usersessie gevonden");
			return "redirect:/gebruikers";
		}
	}

	@RequestMapping(value = "vorstschade/invoeren", method = RequestMethod.GET)
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

	@RequestMapping(value = "vorstschade/overzicht", method = RequestMethod.GET)
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

	@RequestMapping(value = "vorstschade/bekijken/{id}", method = RequestMethod.GET)
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

				model.addAttribute("user", currentUser);
				model.addAttribute("reparatie", viewReparatie);
				model.addAttribute("listUsers", dao.listUsers());
				model.addAttribute("title", "Gebruiker -" + currentUser.getUsername());
				return "reparatie";
			}
		}
	}

	@RequestMapping(value = "vorstschade/toevoegen", method = RequestMethod.POST)
	public String addRepair(@ModelAttribute("Reparatie") Reparatie r, @RequestParam("WegenlijstId") int wId,
			@RequestParam("strookId") int sId, @RequestParam("districtId") int diId, @RequestParam("baanId") int bId) {

		Wegenlijst wegenLijst = daoW.getWegById(wId);
		Strook strookLijst = daoS.getStrookById(sId);
		District districtLijst = daoD.getDistrictById(diId);
		Baan baanLijst = daoB.getBaanById(bId);
		r.setWegenlijst(wegenLijst);
		r.setDistrict(districtLijst);
		r.setStrook(strookLijst);
		r.setBaan(baanLijst);
		daoUr.addReparatie(r, currentUser.getId());

		return "redirect:/vorstschade/overzicht";
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
			return "redirect:/vorstschade/overzicht";
		}
	}

	@RequestMapping(value = "printen", method = RequestMethod.GET)
	public String filterRepairs(Model model, @RequestParam(value = "aannemer", required = false) Integer uId,
			@RequestParam(value = "district", required = false) Integer dId,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "eind", required = false) String eind) {
		List<Userreparatie> reparatieList = daoUr.listReparaties();
		List<Userreparatie> reparatieFilterList = new ArrayList<Userreparatie>();
		int filterAannemer = 0;
		int filterDistrict = 0;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date startDatum = new Date();
		Date eindDatum = new Date();

		if (uId != null && uId != 0) {
			reparatieList = daoUr.listReparatiesById(uId);
			filterAannemer = uId;
		}

		if (dId != null) {
			filterDistrict = dId;
		}

		if (start != "" && eind != "" && start != null && eind != null) {
			try {
				startDatum = simpleDateFormat.parse(start);
				eindDatum = simpleDateFormat.parse(eind);

				for (int i = 0; i < reparatieList.size(); i++) {
					Date constatering = simpleDateFormat.parse(reparatieList.get(i).getReparatie().getConstatering());
					if (betweenDates(constatering, startDatum, eindDatum)) {
						System.out
								.println("Datum ligt ertussen" + reparatieList.get(i).getReparatie().getConstatering());
						reparatieFilterList.add(reparatieList.get(i));
					}
				}
			} catch (ParseException ex) {
				System.out.println("Exception " + ex);
			}
		} else {
			if (start != "" && start != null) {
				System.out.println("start" + start);

				try {
					startDatum = simpleDateFormat.parse(start);

					for (int i = 0; i < reparatieList.size(); i++) {
						Date constatering = simpleDateFormat
								.parse(reparatieList.get(i).getReparatie().getConstatering());
						if (afterDates(constatering, startDatum)) {
							System.out
									.println("Datum komt erna" + reparatieList.get(i).getReparatie().getConstatering());
							reparatieFilterList.add(reparatieList.get(i));
						}
					}
				} catch (ParseException ex) {
					System.out.println("Exception " + ex);
				}
			} else {
				if (eind != "" && eind != null) {
					System.out.println("eind" + eind);

					try {
						eindDatum = simpleDateFormat.parse(eind);

						for (int i = 0; i < reparatieList.size(); i++) {
							Date constatering = simpleDateFormat
									.parse(reparatieList.get(i).getReparatie().getConstatering());
							if (beforeDates(constatering, eindDatum)) {
								System.out.println(
										"Datum komt ervoor" + reparatieList.get(i).getReparatie().getConstatering());
								reparatieFilterList.add(reparatieList.get(i));
							}
						}
					} catch (ParseException ex) {
						System.out.println("Exception " + ex);
					}
				} else {
					reparatieFilterList = daoUr.listReparaties();
				}
			}
		}

		List<Apuser> aannemerList = dao.listUsers();
		List<District> districtList = daoD.listDistrict();

		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			model.addAttribute("listReparaties", reparatieFilterList);
			model.addAttribute("user", currentUser);
			model.addAttribute("title", "Gebruikers");
			model.addAttribute("filterAannemer", filterAannemer);
			model.addAttribute("filterDistrict", filterDistrict);
			model.addAttribute("filterStartDatum", start);
			model.addAttribute("filterEindDatum", eind);
			model.addAttribute("listAannemers", aannemerList);
			model.addAttribute("listDistricten", districtList);
			System.out.println("usersessie gevonden");
			return "print";
		}
	}

	private static boolean betweenDates(Date date, Date dateStart, Date dateEnd) {
		if (date != null && dateStart != null && dateEnd != null) {
			if (date.after(dateStart) && date.before(dateEnd)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	private static boolean afterDates(Date date, Date dateStart) {
		if (date != null && dateStart != null) {
			if (date.after(dateStart)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	private static boolean beforeDates(Date date, Date dateEnd) {
		if (date != null && dateEnd != null) {
			if (date.before(dateEnd)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
