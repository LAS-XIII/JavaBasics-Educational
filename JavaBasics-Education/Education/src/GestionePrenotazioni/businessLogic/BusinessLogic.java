package GestionePrenotazioni.businessLogic;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import GestionePrenotazioni.dao.CorsoDao;
import GestionePrenotazioni.dao.PrenotazioniDao;
import GestionePrenotazioni.dao.UtenteDao;
import GestionePrenotazioni.model.Corso;
import GestionePrenotazioni.model.Prenotazione;
import GestionePrenotazioni.model.Utente;
import GestionePrenotazioni.utility.Utilita;

public class BusinessLogic {
	
	private PrenotazioniDao pd = new PrenotazioniDao();
	private CorsoDao cd = new CorsoDao();
	private UtenteDao ud = new UtenteDao();
	
	public void visualizzaCorsi() throws FileNotFoundException, IOException {		

		try {
			List<Corso> corsi = cd.retrive();				
			for (Corso c : corsi) {
				if (!c.getId().trim().equalsIgnoreCase("ID")) {
					System.out.println(c);
				}				
			}
		} catch (Exception e) {				
			e.printStackTrace();
			throw e;				
		}
		
	}
	
	
	public void prenotaCorso(String idCorso, String idUtente) throws FileNotFoundException, IOException {
		
		BufferedWriter writer = null;
		Boolean flag = true;		
		
		try {
			
			List<Corso> corsi = cd.retrive();
			List<Utente> utenti = ud.retrive();
			
			for (Corso c : corsi) {
				for (Utente u : utenti) {
					if (c.getId().equals(idCorso) && u.getId().equals(idUtente) && c.getDisponibile().equalsIgnoreCase("SI")) {
						
						try {
							DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy").localizedBy(Locale.ITALY);
							LocalDate dataInizio = LocalDate.parse(c.getData(), df);
							Integer giorniPassati = (int) Math.ceil(Integer.parseInt(c.getDurata())/8);
							LocalDate dataFine = dataInizio.plusDays(giorniPassati);
							
							List<Prenotazione> prenotazioni = pd.retrive();
							Integer idCounter = 0;
							
							for (Prenotazione p : prenotazioni) {
								idCounter = Integer.parseInt(p.getId())>idCounter ? Integer.parseInt(p.getId()) : idCounter;
							}
						
							writer = new BufferedWriter(new FileWriter(Utilita.BASEPATH + "prenotazioni.csv"));
							
							prenotazioni.add(new Prenotazione(String.valueOf(++idCounter), idCorso, idUtente, dataInizio.format(df), dataFine.format(df)));
							
							String tableText = "ID;ID Attivita;ID Utente;Data inizio;Data fine\n";
							for (Prenotazione p : prenotazioni) {
								tableText += p.csv() + "\n";
							}
							
							cd.aggiornaDisponibilita(idCorso, "NO");
							
							writer.write(tableText, 0, tableText.length());
							writer.flush();
							flag = false;
							break;
							
						} catch (Exception e) {
							e.printStackTrace();
							throw e;
						} finally {
							
							try {
							    writer.close();
							} catch (IOException e) {
							    e.printStackTrace();
							    throw e;
							}
						}
						
					} else if (c.getId().equals(idCorso) && !c.getDisponibile().equalsIgnoreCase("SI")) {
						System.out.println("il corso non e' disponibile");
						flag = false;
						break;
					} 					
						
				}
				
			}
			if (flag) {
				System.out.println("qualcosa è andato storto");
			}
			
		} catch (Exception e) {
			
			throw e;
			
		} 
		
	}
	
	
	public void eliminaPrenotazione(String idCorso, String idUtente) throws FileNotFoundException, IOException {
		
		try {
			
			List<Prenotazione> prenotazioni = pd.retrive();
			
			for (Prenotazione p : prenotazioni) {
				
				if (p.getIdAttivita().equals(idCorso) && p.getIdUtente().equals(idUtente)) {
					pd.eliminaPrenotazione(p.getId());
					cd.aggiornaDisponibilita(idCorso, "SI");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	
	public void creaUtente(String nome, String cognome, String dataDiNascita, String indirizzo, String documentoId) throws FileNotFoundException, IOException {
		
		try {
			
			List<Utente> utenti = ud.retrive();
			Integer idUtente = 0;
			for (Utente u : utenti) {
				idUtente = idUtente>Integer.parseInt(u.getId()) ? idUtente : Integer.parseInt(u.getId());
			}
		
			ud.aggiungiUtente(String.valueOf(++idUtente), nome, cognome, dataDiNascita, indirizzo, documentoId);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	
	public void EsportaCorsi() throws FileNotFoundException, IOException {
		
		BufferedWriter writer = null;
		
		try {
			
			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd_MM_yyyy");
			String nomeFile = "prenotazioni_" + LocalDate.now().format(df);
			List<Corso> corsi = cd.retrive();
			writer = new BufferedWriter(new FileWriter(Utilita.BASEPATH + nomeFile));

			String tableText = "ID;Nome;Descrizione;Data;Durata(ore);Luogo;Disponibile\n";
			
			for (Corso c : corsi) {
				if (c.getDisponibile().equalsIgnoreCase("SI")) {
					tableText += c.csv() + "\n";
				}
			}
			
			writer.write(tableText);
			writer.flush();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				writer.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw e2;
			}
		}
		
	}
	
	
	
}
