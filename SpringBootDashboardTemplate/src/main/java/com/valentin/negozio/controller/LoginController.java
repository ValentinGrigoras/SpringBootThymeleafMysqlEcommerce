package com.valentin.negozio.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.valentin.negozio.businesscomponent.Carrello;
import com.valentin.negozio.model.Utente;
import com.valentin.negozio.service.UtenteService;

@Controller
public class LoginController {

	@Autowired
	UtenteService utenteService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView loginPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("utente", new Utente());
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(value = "/login_user", method = RequestMethod.POST)
	public ModelAndView controlloLogin(@RequestParam("username") String user, @RequestParam("password") String pass,
			HttpSession session) {

		if (!utenteService.controlloLogin(user, pass).isEmpty()) {

			session.setAttribute("carrello", new Carrello());
			session.setAttribute("utente_log", utenteService.controlloLogin(user, pass).get());
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/");
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutUtente(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/login");
	}
}
