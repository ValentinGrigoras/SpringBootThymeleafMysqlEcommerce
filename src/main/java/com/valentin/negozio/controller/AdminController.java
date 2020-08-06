package com.valentin.negozio.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.valentin.negozio.model.Admin;
import com.valentin.negozio.model.Ordine;
import com.valentin.negozio.model.Utente;
import com.valentin.negozio.service.AdminService;
import com.valentin.negozio.service.OrdineService;
import com.valentin.negozio.service.UtenteService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;

	@Autowired
	UtenteService utenteService;

	@Autowired
	OrdineService ordineService;

	@RequestMapping(value = "/admin/login_admin", method = RequestMethod.POST)
	public ModelAndView controlloLogin(@RequestParam("username") String user, @RequestParam("password") String pass,
			HttpSession session) {

		if (!adminService.controlloLogin(user, pass).isEmpty()) {
			session.setAttribute("admin_log", adminService.controlloLogin(user, pass).get());
			return new ModelAndView("redirect:/admin/home_admin");
		} else {
			return new ModelAndView("redirect:/");
		}
	}

	// ---------- Gestione utenti -----------

	@RequestMapping(value = "/admin/gestioneutenti", method = RequestMethod.GET)
	public ModelAndView gestioneUtenti(HttpSession session) {
		if (session.getAttribute("admin_log") == null)
			return new ModelAndView("redirect:/");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/gestioneutenti");
		List<Utente> listaUtenti = utenteService.getAll();
		mv.addObject("listaUtenti", listaUtenti);
		mv.addObject("admin_log", (Admin) session.getAttribute("admin_log"));
		mv.addObject("_keyword", session.getAttribute("_keyword"));
		return mv;
	}

	@RequestMapping(value = "/admin/gestioneutenti", method = RequestMethod.POST)
	public ModelAndView trovaUtenti(HttpSession session, @RequestParam("keyword") String keyword) {
		if (session.getAttribute("admin_log") == null)
			return new ModelAndView("redirect:/");
		if (keyword != null && !keyword.equalsIgnoreCase("")) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("admin/gestioneutenti");
			List<Utente> listaUtenti = utenteService.searchUtente(keyword);
			mv.addObject("listaUtenti", listaUtenti);
			mv.addObject("admin_log", (Admin) session.getAttribute("admin_log"));
			session.setAttribute("_keyword", keyword);
			mv.addObject("_keyword", keyword);
			return mv;
		}
		session.setAttribute("_keyword", "");
		return new ModelAndView("redirect:/admin/gestioneutenti");
	}

	@RequestMapping(value = "/admin/admdel_utente/{username}", method = RequestMethod.GET)
	public ModelAndView eliminaUtente(HttpSession session, @PathVariable String username) {
		if (session.getAttribute("admin_log") == null)
			return new ModelAndView("redirect:/");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/gestioneutenti");
		utenteService.deleteUtenteByUsername(username);
		return mv;
	}

	// ---------- Gestione ordini -----------

	@RequestMapping(value = "/admin/gestioneordini", method = RequestMethod.GET)
	public ModelAndView gestioneOrdini(HttpSession session) {
		if (session.getAttribute("admin_log") == null)
			return new ModelAndView("redirect:/");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/gestioneordini");
		List<Ordine> listaOrdini = ordineService.getAll();
		mv.addObject("listaOrdini", listaOrdini);
		mv.addObject("admin_log", (Admin) session.getAttribute("admin_log"));
		return mv;
	}

	@RequestMapping(value = "/admin/admdel_ordine/{id}", method = RequestMethod.GET)
	public ModelAndView eliminaUtente(HttpSession session, @PathVariable Long id) {
		if (session.getAttribute("admin_log") == null)
			return new ModelAndView("redirect:/");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/gestioneordini");
		ordineService.deleteOrdineById(id);
		return mv;
	}

	@RequestMapping(value = "/admin/gestioneordini", method = RequestMethod.POST)
	public ModelAndView trovaOrdini(HttpSession session, @RequestParam("keyword") String keyword) {
		if (session.getAttribute("admin_log") == null)
			return new ModelAndView("redirect:/");
		if (keyword != null && !keyword.equalsIgnoreCase("")) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("admin/gestioneordini");
			List<Ordine> listaOrdini = ordineService.searchUtente(keyword);
			mv.addObject("listaOrdini", listaOrdini);
			mv.addObject("admin_log", (Admin) session.getAttribute("admin_log"));
			session.setAttribute("_keyword", keyword);
			mv.addObject("_keyword", keyword);
			return mv;
		}
		session.setAttribute("_keyword", "");
		return new ModelAndView("redirect:/admin/gestioneordini");
	}

}
