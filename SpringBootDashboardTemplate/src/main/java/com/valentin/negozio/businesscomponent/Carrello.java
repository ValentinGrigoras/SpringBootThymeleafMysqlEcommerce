package com.valentin.negozio.businesscomponent;

import java.util.Enumeration;
import java.util.Hashtable;

public class Carrello {
	private Hashtable<String, String[]> articoli = new Hashtable<String, String[]>();
	
	private int numeroArticoli;

	public Carrello() {
		numeroArticoli = 0;
	}

	public int totaleArticoli() {
		return numeroArticoli;
	}

	public void aggiungiArticolo(String id, String marca, String modello, double prezzo) {
		String[] record = { marca, modello, Double.toString(prezzo), "1", id };
		numeroArticoli++;
		if (articoli.containsKey(id)) {
			String dati[] = articoli.get(id);
			int quant = Integer.parseInt(dati[3]);
			quant++;
			dati[3] = Integer.toString(quant);
			articoli.put(id, dati);
		} else {
			articoli.put(id, record);
		}
	}

	public void rimuoviArticolo(String id) {
		if (articoli.containsKey(id)) {
			numeroArticoli--;
			String dati[] = articoli.get(id);
			if (Integer.parseInt(dati[3]) == 1) {
				articoli.remove(id);
			} else {
				int quant = Integer.parseInt(dati[3]);
				quant--;
				dati[3] = Integer.toString(quant);
				articoli.put(id, dati);
			}
		}
	}

	public double totaleParziale(String id) {
		String dati[] = articoli.get(id);
		return Double.parseDouble(dati[2]) * Integer.parseInt(dati[3]);
	}

	public double totaleComplessivo() {
		double totale = 0.00;
		Enumeration<String[]> elementi = getProdotti();
		String[] dati;
		while (elementi.hasMoreElements()) {
			dati = elementi.nextElement();
			totale += Double.parseDouble(dati[2]) * Integer.parseInt(dati[3]);
		}
		return totale;
	}

	public Enumeration<String[]> getProdotti() {
		return articoli.elements();
	}
}
