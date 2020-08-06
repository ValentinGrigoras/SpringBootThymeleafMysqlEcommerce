package com.valentin.negozio.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.valentin.negozio.model.Admin;
import com.valentin.negozio.model.Articolo;
import com.valentin.negozio.service.ArticoloService;

@Controller
public class ArticoloController {

	@Autowired
	ArticoloService articoloService;

	/* Gestione articoli */
	@RequestMapping(value = "/admin/add_articolo", method = RequestMethod.POST)
	public ModelAndView acquisti(Articolo a, BindingResult br, HttpSession session) {
		// controllo che l'utente sia logato
		if (session.getAttribute("admin_log") == null)
			return new ModelAndView("redirect:/");
		articoloService.saveArticolo(a);
		ModelAndView mv = new ModelAndView("redirect:/admin/gestionearticoli");

		mv.addObject("admin_log", (Admin) session.getAttribute("admin_log"));
		return mv;
	}

	@RequestMapping(value = "/admin/gestionearticoli", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		if (session.getAttribute("admin_log") == null)
			return new ModelAndView("redirect:/");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/gestionearticoli");

		if (((Admin) session.getAttribute("admin_log")) != null) {
			mv.addObject("admin_log", (Admin) session.getAttribute("admin_log"));
		}

		List<Articolo> listaArticoli = articoloService.getAll();
		System.out.println(listaArticoli.size());
		mv.addObject("listaArticoli", listaArticoli);
		mv.addObject("articolo", new Articolo());
		return mv;
	}

	@RequestMapping(value = "/admin/remove_articoli/{id}", method = RequestMethod.GET)
	public ModelAndView remove(HttpSession session, @PathVariable String id) {

		if (((Admin) session.getAttribute("admin_log")) != null) {
			if (id != null)
				articoloService.removeArticoli(Long.valueOf(id));
		}
		return new ModelAndView("redirect:/admin/gestionearticoli");
	}


}
