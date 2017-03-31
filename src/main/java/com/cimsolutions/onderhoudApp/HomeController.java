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
	
	public static Apuser getCurrentUser() {
		return currentUser;
	}
}
