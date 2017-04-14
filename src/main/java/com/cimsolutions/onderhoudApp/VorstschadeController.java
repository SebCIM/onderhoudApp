package com.cimsolutions.onderhoudApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.cimsolutions.DAO.DeklagenDAOImpl;
import com.cimsolutions.DAO.DistrictDAOImpl;
import com.cimsolutions.DAO.MethodeDAOImpl;
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

@Controller
public class VorstschadeController {
	
	UserDaoImpl dao = new UserDaoImpl();
	UserReparatieDAOImpl daoUr = new UserReparatieDAOImpl();
	ReparatieDAOImpl daoR = new ReparatieDAOImpl();
	DistrictDAOImpl daoD = new DistrictDAOImpl();
	DeklagenDAOImpl daoDl = new DeklagenDAOImpl();
	BaanDAOImpl daoB = new BaanDAOImpl();
	WegenDAOImpl daoW = new WegenDAOImpl();
	StrookDAOImpl daoS = new StrookDAOImpl();
	MethodeDAOImpl daoM = new MethodeDAOImpl();
	
	@RequestMapping(value = "vorstschade/invoeren", method = RequestMethod.GET)
	public String Invoer(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				ArrayList<String> soortList = new ArrayList<String>();
				List<District> listDistrict = daoD.listDistrict();
				List<Baan> listBaan = daoB.listBaan();
				List<Wegenlijst> listWegen = daoW.listFilteredWegen();
				List<Strook> listStrook = daoS.listStrook();
				soortList.add("Rafeling");
				soortList.add("Gaten");
				soortList.add("Open naden");

				model.addAttribute("user", user);
				model.addAttribute("ListFilterBaan", daoDl.listFilteredBanen());
				model.addAttribute("ListDistrict", listDistrict);
				model.addAttribute("ListFilterStrook", daoDl.listFilteredStroken());
				model.addAttribute("ListWegen", listWegen);
				model.addAttribute("ListBaan", listBaan);
				model.addAttribute("soortList", soortList);
				return "invoer";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}

	@RequestMapping(value = "vorstschade/overzicht", method = RequestMethod.GET)
	public String showRepairs(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				Apuser currentUser = HomeController.getCurrentUser();
				List<Userreparatie> reparatieList = daoUr.listReparaties();
				
				model.addAttribute("listReparaties", reparatieList);
				model.addAttribute("user", user);
				model.addAttribute("title", "Gebruikers");
				System.out.println("usersessie gevonden");
				return "reparaties";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}

	@RequestMapping(value = "vorstschade/bekijken/{id}", method = RequestMethod.GET)
	public String viewRepair(@PathVariable("id") int id, Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				Userreparatie viewReparatie = new Userreparatie();
				viewReparatie = daoUr.getReparatieById(id);

				model.addAttribute("user", user);
				model.addAttribute("reparatie", viewReparatie);
				model.addAttribute("listDistricten", daoD.listDistrict());
				model.addAttribute("listMethoden", daoM.listMethode());
				model.addAttribute("ListWegen", daoW.listFilteredWegen());
				model.addAttribute("ListStroken", daoS.listStrook());
				model.addAttribute("listUsers", dao.listUsers());
				model.addAttribute("title", "Gebruiker -" + user.getUsername());
				return "reparatie";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}

	@RequestMapping(value = "vorstschade/toevoegen", method = RequestMethod.POST)
	public String addRepair(@ModelAttribute("Reparatie") Reparatie r, @RequestParam("WegenlijstId") int wId, @RequestParam("districtId") int diId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				Wegenlijst wegenLijst = daoW.getWegById(wId);
				District districtLijst = daoD.getDistrictById(diId);
				r.setWegenlijst(wegenLijst);
				r.setDistrict(districtLijst);
				r.setStatus("In onderhoudsplanning");
				daoUr.addReparatie(r, user.getId());

				return "redirect:/vorstschade/overzicht";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}

	@RequestMapping(value = "/vorstschade/verwijderen/{id}", method = RequestMethod.GET)
	public String removeRepair(@PathVariable("id") int id, Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				Userreparatie ur = daoUr.removeUserRepair(id);
				daoR.removeRepair(ur.getReparatie().getId());

				model.addAttribute("user", user);
				model.addAttribute("title", "Remove");
				System.out.println("usersessie gevonden");
				return "redirect:/vorstschade/overzicht";
			} else {
				System.out.println("geen user gevonden");
				return "login";
			}
		} else {
			System.out.println("geen user gevonden");
			return "login";
		}
	}
	
	@RequestMapping(value = "vorstschade/aanpassen", method = RequestMethod.POST)
	public String editRepair(@ModelAttribute("Reparatie") Reparatie r, @RequestParam("districtId") int diId, @RequestParam("Id") int id, @RequestParam("rijksweg") int wId, @RequestParam("reparatiemethode") int rmId, @RequestParam("sjonnie") String rdId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Apuser user = (Apuser) session.getAttribute("user");
			if (user != null) {
				Reparatie currentRepair = daoR.getReparatieById(id);
				if(rmId != 0){
					r.setReparatiemethoden(daoM.getMethodeById(rmId));
				} else {
					r.setReparatiemethoden(null);
				}
				if(rdId != ""){
					r.setReparatiemethoden(daoM.getMethodeById(rmId));
				} else {
					r.setReparatiemethoden(null);
				}
				r.setDatumtijd(currentRepair.getDatumtijd());
				r.setBaan(currentRepair.getBaan());
				r.setStrook(currentRepair.getStrook());
				r.setSoort(currentRepair.getSoort());
				r.setConstatering(currentRepair.getConstatering());
				r.setId(id);
				r.setDistrict(daoD.getDistrictById(diId));
				r.setWegenlijst(daoW.getWegById(wId));
				
				daoR.updateRepair(r);

				return "redirect:/vorstschade/overzicht";
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
