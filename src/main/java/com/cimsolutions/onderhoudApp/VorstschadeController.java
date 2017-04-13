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
	public String Invoer(Model model) {
		Apuser currentUser = HomeController.getCurrentUser();
		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			ArrayList<String> soortList = new ArrayList<String>();
			List<District> listDistrict = daoD.listDistrict();
			List<Baan> listBaan = daoB.listBaan();
			List<Wegenlijst> listWegen = daoW.listFilteredWegen();
			List<Strook> listStrook = daoS.listStrook();
			soortList.add("Rafeling");
			soortList.add("Gaten");
			soortList.add("Open naden");

			model.addAttribute("user", currentUser);
			model.addAttribute("ListFilterBaan", daoDl.listFilteredBanen());
			model.addAttribute("ListDistrict", listDistrict);
			model.addAttribute("ListFilterStrook", daoDl.listFilteredStroken());
			model.addAttribute("ListWegen", listWegen);
			model.addAttribute("ListBaan", listBaan);
			model.addAttribute("soortList", soortList);
			return "invoer";
		}
	}

	@RequestMapping(value = "vorstschade/overzicht", method = RequestMethod.GET)
	public String showRepairs(Model model) {
		Apuser currentUser = HomeController.getCurrentUser();
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
		Apuser currentUser = HomeController.getCurrentUser();
		if (currentUser == null) {
			System.out.println("geen sessie gevonden");
			return "login";
		} else {
			if (currentUser.getIsAdmin() == false) {
				System.out.println("Geen admin rechten");
				return "home";
			} else {
				Userreparatie viewReparatie = new Userreparatie();
				viewReparatie = daoUr.getReparatieById(id);

				model.addAttribute("user", currentUser);
				model.addAttribute("reparatie", viewReparatie);
				model.addAttribute("listDistricten", daoD.listDistrict());
				model.addAttribute("listMethoden", daoM.listMethode());
				model.addAttribute("ListWegen", daoW.listFilteredWegen());
				model.addAttribute("ListStroken", daoS.listStrook());
				model.addAttribute("listUsers", dao.listUsers());
				model.addAttribute("title", "Gebruiker -" + currentUser.getUsername());
				return "reparatie";
			}
		}
	}

	@RequestMapping(value = "vorstschade/toevoegen", method = RequestMethod.POST)
	public String addRepair(@ModelAttribute("Reparatie") Reparatie r, @RequestParam("WegenlijstId") int wId, @RequestParam("districtId") int diId) {
		
		Apuser currentUser = HomeController.getCurrentUser();

		Wegenlijst wegenLijst = daoW.getWegById(wId);
		District districtLijst = daoD.getDistrictById(diId);
		r.setWegenlijst(wegenLijst);
		r.setDistrict(districtLijst);
		r.setStatus("In onderhoudsplanning");
		daoUr.addReparatie(r, currentUser.getId());

		return "redirect:/vorstschade/overzicht";
	}

	@RequestMapping(value = "/vorstschade/verwijderen/{id}", method = RequestMethod.GET)
	public String removeRepair(@PathVariable("id") int id, Model model) {
		
		Apuser currentUser = HomeController.getCurrentUser();
		
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
	
	@RequestMapping(value = "vorstschade/aanpassen", method = RequestMethod.POST)
	public String editRepair(@ModelAttribute("Reparatie") Reparatie r, @RequestParam("districtId") int diId, @RequestParam("Id") int id, @RequestParam("rijksweg") int wId, @RequestParam("reparatiemethode") int rmId) {
		Reparatie currentRepair = daoR.getReparatieById(id);
		r.setDatumtijd(currentRepair.getDatumtijd());
		r.setReparatiemethoden(daoM.getMethodeById(rmId));
		r.setBaan(currentRepair.getBaan());
		r.setStrook(currentRepair.getStrook());
		r.setSoort(currentRepair.getSoort());
		r.setDatumtijd(currentRepair.getDatumtijd());
		r.setConstatering(currentRepair.getConstatering());
		r.setId(id);
		r.setDistrict(daoD.getDistrictById(diId));
		r.setWegenlijst(daoW.getWegById(wId));
		
		daoR.updateRepair(r);

		return "redirect:/vorstschade/overzicht";
	}
}
