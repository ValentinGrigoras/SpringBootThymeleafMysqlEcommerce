package com.valentin.negozio.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.valentin.negozio.businesscomponent.Carrello;
import com.valentin.negozio.model.Admin;
import com.valentin.negozio.model.Utente;
import com.valentin.negozio.service.OrdineArticoloService;
import com.valentin.negozio.service.UtenteService;

@Controller
public class HomeController {
	@Autowired
	UtenteService utenteService;

	@Autowired
	OrdineArticoloService ordineArticoloService;

	@RequestMapping(value = { "/home", "/index" }, method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		
		if(((Utente)session.getAttribute("utente_log")) != null) {
		mv.addObject("utente_log", (Utente) session.getAttribute("utente_log"));
		mv.addObject("_carrello", (Carrello) session.getAttribute("carrello"));
		}
	
		List<String[]> miglioriArticoli = null;
		miglioriArticoli = ordineArticoloService.getMiglioriArticoli();
		mv.addObject("miglioriArticoli", miglioriArticoli);
		return mv;
	}
	
	@RequestMapping(value = "/admin/home_admin", method = RequestMethod.GET)
	public ModelAndView homeAdmin(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/home_admin");
		
		if(((Admin)session.getAttribute("admin_log")) != null)
		mv.addObject("admin_log", (Admin) session.getAttribute("admin_log"));
		
		List<String[]> miglioriArticoli = null;
		miglioriArticoli = ordineArticoloService.getMiglioriArticoli();
		mv.addObject("miglioriArticoli", miglioriArticoli);
		return mv;
	}
}
