package com.valentin.negozio.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.valentin.negozio.businesscomponent.Carrello;
import com.valentin.negozio.model.Articolo;
import com.valentin.negozio.model.Ordine;
import com.valentin.negozio.model.OrdineArticolo;
import com.valentin.negozio.model.Utente;
import com.valentin.negozio.service.ArticoloService;
import com.valentin.negozio.service.OrdineArticoloService;
import com.valentin.negozio.service.OrdineService;

@Controller
public class AcquistiController {

	@Autowired
	ArticoloService articoloService;

	@Autowired
	OrdineService ordineService;

	@Autowired
	OrdineArticoloService ordineArticoloService;

	/* Gestione acquisti */
	@RequestMapping(value = "/acquisti", method = RequestMethod.GET)
	public ModelAndView acquisti(@RequestParam(value = "ok", required = false, defaultValue = "false") String esito,
			HttpSession session) {
		// controllo che l'utente sia logato
		if (session.getAttribute("utente_log") == null)
			return new ModelAndView("redirect:/");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("acquisti");
		List<Articolo> listaArticoli = articoloService.getAll();
		mv.addObject("listaArticoli", listaArticoli);
		mv.addObject("_carrello", (Carrello) session.getAttribute("carrello"));
		mv.addObject("utente_log", (Utente) session.getAttribute("utente_log"));
		mv.addObject("ok", esito);
		int numArt = 0;
		if (session.getAttribute("carrello") != null)
			numArt = ((Carrello) session.getAttribute("carrello")).totaleArticoli();
		mv.addObject("empty", numArt > 0 ? "false" : "true");
		return mv;
	}

	@RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
	public ModelAndView add(HttpSession session, @PathVariable Long id) {
		// controllo che l'utente sia logato
		if (session.getAttribute("utente_log") == null)
			return new ModelAndView("redirect:/");
		Articolo a = articoloService.findById(id).get();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		if (a.getIdArticolo() != 0) {
			carrello.aggiungiArticolo(Long.toString(id), a.getMarca(), a.getModello(), a.getPrezzo());
		}
		return new ModelAndView("redirect:/acquisti?ok=false");
	}

	@RequestMapping(value = "/addfromhome/{id}", method = RequestMethod.GET)
	public ModelAndView addfromhome(HttpSession session, @PathVariable Long id) {
		// controllo che l'utente sia logato
		if (session.getAttribute("utente_log") == null)
			return new ModelAndView("redirect:/");
		Articolo a = articoloService.findById(id).get();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		if (a.getIdArticolo() != 0) {
			carrello.aggiungiArticolo(Long.toString(id), a.getMarca(), a.getModello(), a.getPrezzo());
		}
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/carrello", method = RequestMethod.GET)
	public ModelAndView carrello(HttpSession session) {
		try {
			// controllo che l'utente sia logato
			if (session.getAttribute("utente_log") == null)
				return new ModelAndView("redirect:/");
			List<String[]> articoli = converti(session);
			ModelAndView mv = new ModelAndView();
			Carrello carrello = (Carrello) session.getAttribute("carrello");
			if (carrello.totaleArticoli() == 0)
				return new ModelAndView("redirect:/acquisti?ok=false");
			mv.setViewName("carrello");
			mv.addObject("_carrello", (Carrello) session.getAttribute("carrello"));
			mv.addObject("utente_log", (Utente) session.getAttribute("utente_log"));
			mv.addObject("listaArticoli", articoli);
			return mv;
		} catch (NullPointerException exc) {
			return new ModelAndView("redirect:/login");
		}

	}

	private List<String[]> converti(HttpSession session) {
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		Vector<String[]> articoli = new Vector<String[]>();
		Enumeration<String[]> prodotti = carrello.getProdotti();
		while (prodotti.hasMoreElements()) {
			String[] prodotto = prodotti.nextElement();
			articoli.add(prodotto);
		}
		return articoli;
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public ModelAndView remove(HttpSession session, @PathVariable String id) {

		Carrello carrello = (Carrello) session.getAttribute("carrello");
		carrello.rimuoviArticolo(id);
		return new ModelAndView("redirect:/carrello");
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView salvaOrdine(HttpSession session) {
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		Utente utente = (Utente) session.getAttribute("utente_log");
		Ordine ord = new Ordine();
		ord.setTotale(carrello.totaleComplessivo());
		ord.setData(new Date());
		ord.setUtente(utente);
		ordineService.saveOrdine(ord);
		Enumeration<String[]> prodotti = carrello.getProdotti();
		while (prodotti.hasMoreElements()) {
			String prodotto[] = prodotti.nextElement();
			OrdineArticolo oa = new OrdineArticolo();
			oa.setArticolo(articoloService.findById(Long.parseLong(prodotto[4])).get());
			oa.setOrdine(ord);
			oa.setQta(Integer.parseInt(prodotto[3]));
			ordineArticoloService.saveOrdineArticolo(oa);
		}
		Carrello nCarrello = new Carrello();
		session.setAttribute("carrello", nCarrello);
		return new ModelAndView("redirect:/acquisti?ok=true");
	}
	
	@RequestMapping(value="/ordini", method = RequestMethod.GET)
	public ModelAndView visualizzaOrdini(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (((Utente)session.getAttribute("utente_log")) == null)
			return new ModelAndView("redirect:/");
		
		List<Ordine> listaOrdini = ordineService.findByUsername(((Utente)session.getAttribute("utente_log")).getUsername());
		mv.addObject("listaOrdini", listaOrdini);
		mv.addObject("_carrello", (Carrello) session.getAttribute("carrello"));
		mv.addObject("utente_log", (Utente) session.getAttribute("utente_log"));
		mv.setViewName("ordini");
		return mv;
	}
}
