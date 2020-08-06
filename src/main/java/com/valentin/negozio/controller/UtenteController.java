package com.valentin.negozio.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.valentin.negozio.businesscomponent.Carrello;
import com.valentin.negozio.model.Utente;
import com.valentin.negozio.service.UtenteService;

@Controller
public class UtenteController {
	@Autowired
	UtenteService utenteService;

	@RequestMapping(value = "/utente", method = RequestMethod.GET)
	public ModelAndView visualizzaUtente(HttpSession session) {
		Utente utente = (Utente) session.getAttribute("utente_log");
		if (utente != null) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("utente");
			mv.addObject("_carrello", (Carrello) session.getAttribute("carrello"));
			mv.addObject("utente_log", utenteService.controlloLogin(utente.getUsername(), utente.getPassword()).get());
			return mv;
		}
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/utente", method = RequestMethod.POST)
	public ModelAndView updateUtente(Utente u, BindingResult br, HttpSession session) {
		int n = utenteService.updateUtente(u);
		ModelAndView mv = new ModelAndView("redirect:/utente");
		if (n > 0) {
			session.setAttribute("utente_log", utenteService.controlloLogin(u.getUsername(), u.getPassword()).get());
		}
		return mv;
	}
	
	@RequestMapping(value = "/registrazione", method = RequestMethod.POST) // operazione salva utente
	public ModelAndView saveUtente(Utente u, BindingResult r) { // pagina di login rappresenta la pagina home
		utenteService.saveUtente(u);
		return new ModelAndView("redirect:/login");
	}

}
