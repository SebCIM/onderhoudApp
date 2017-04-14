package com.cimsolutions.onderhoudApp;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
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

@Controller
public class FilterController {

	UserDaoImpl dao = new UserDaoImpl();
	UserReparatieDAOImpl daoUr = new UserReparatieDAOImpl();
	ReparatieDAOImpl daoR = new ReparatieDAOImpl();
	DistrictDAOImpl daoD = new DistrictDAOImpl();
	BaanDAOImpl daoB = new BaanDAOImpl();
	WegenDAOImpl daoW = new WegenDAOImpl();
	StrookDAOImpl daoS = new StrookDAOImpl();

	@RequestMapping(value = "printen", method = RequestMethod.GET)
	public String filterRepairs(Model model, @RequestParam(value = "aannemer", required = false) Integer uId,
			@RequestParam(value = "district", required = false) Integer dId,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "eind", required = false) String eind, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
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
					start = start + " 00:00:00";
					eind = eind + " 24:00:00";
					try {
						startDatum = simpleDateFormat.parse(start);
						eindDatum = simpleDateFormat.parse(eind);

						for (int i = 0; i < reparatieList.size(); i++) {
							Date constatering = simpleDateFormat
									.parse(reparatieList.get(i).getReparatie().getConstatering());
							if (betweenDates(constatering, startDatum, eindDatum)) {
								System.out.println(
										"Datum ligt ertussen" + reparatieList.get(i).getReparatie().getConstatering());
								if (uId != null && uId != 0) {
									if (uId == reparatieList.get(i).getApuser().getId()) {
										reparatieFilterList.add(reparatieList.get(i));
									}
								}
							}
						}
					} catch (ParseException ex) {
						System.out.println("Exception " + ex);
					}
				} else {
					if (start != "" && start != null) {
						System.out.println("start" + start);
						start = start + " 00:00:00";

						try {
							startDatum = simpleDateFormat.parse(start);

							for (int i = 0; i < reparatieList.size(); i++) {
								Date constatering = simpleDateFormat
										.parse(reparatieList.get(i).getReparatie().getConstatering());
								if (afterDates(constatering, startDatum)) {
									System.out.println(
											"Datum komt erna" + reparatieList.get(i).getReparatie().getConstatering());
									reparatieFilterList.add(reparatieList.get(i));
								}
							}
						} catch (ParseException ex) {
							System.out.println("Exception " + ex);
						}
					} else {
						if (eind != "" && eind != null) {
							System.out.println("eind" + eind);
							eind = eind + " 24:00:00";

							try {
								eindDatum = simpleDateFormat.parse(eind);

								for (int i = 0; i < reparatieList.size(); i++) {
									Date constatering = simpleDateFormat
											.parse(reparatieList.get(i).getReparatie().getConstatering());
									if (beforeDates(constatering, eindDatum)) {
										System.out.println("Datum komt ervoor"
												+ reparatieList.get(i).getReparatie().getConstatering());
										reparatieFilterList.add(reparatieList.get(i));
									}
								}
							} catch (ParseException ex) {
								System.out.println("Exception " + ex);
							}
						} else {
							if (uId != null && uId != 0 || dId != null && dId != 0) {
								for (int i = 0; i < reparatieList.size(); i++) {
									if (uId != null && uId != 0) {
										if (uId == reparatieList.get(i).getApuser().getId()) {
											if (dId != null && dId != 0) {
												if (dId == reparatieList.get(i).getReparatie().getDistrict().getId()) {
													reparatieFilterList.add(reparatieList.get(i));
												}
											} else {
												reparatieFilterList.add(reparatieList.get(i));
											}
										}
									} else {
										if (dId != null && dId != 0) {
											System.out.println("district gevonden");
											System.out.println(
													reparatieList.get(i).getReparatie().getDistrict().getId() + "test");
											if (dId == reparatieList.get(i).getReparatie().getDistrict().getId()) {
												reparatieFilterList.add(reparatieList.get(i));
											}
										}
									}
								}
							} else {
								reparatieFilterList = daoUr.listReparaties();
							}

						}
					}
				}

				List<Apuser> aannemerList = dao.listUsers();
				List<District> districtList = daoD.listDistrict();

				int totalResults = reparatieFilterList.size();
				model.addAttribute("listReparaties", reparatieFilterList);
				model.addAttribute("totalResults", totalResults);
				model.addAttribute("user", user);
				model.addAttribute("title", "Gebruikers");
				model.addAttribute("filterAannemer", filterAannemer);
				model.addAttribute("filterDistrict", filterDistrict);
				model.addAttribute("filterStartDatum", start);
				model.addAttribute("filterEindDatum", eind);
				model.addAttribute("listAannemers", aannemerList);
				model.addAttribute("listDistricten", districtList);
				System.out.println("usersessie gevonden");
				return "print";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
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
