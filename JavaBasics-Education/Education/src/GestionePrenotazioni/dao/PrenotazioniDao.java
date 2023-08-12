package GestionePrenotazioni.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import GestionePrenotazioni.model.Prenotazione;
import GestionePrenotazioni.utility.Utilita;

public class PrenotazioniDao implements InterfacciaDao<Prenotazione>{

	
	@Override
	public List<Prenotazione> retrive() throws FileNotFoundException, IOException {

		BufferedReader reader = null;
		List<Prenotazione> prenotazioni = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(Utilita.BASEPATH + "prenotazioni.csv"));
			String line = "";
						
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(";");
				if (!(row[0].toLowerCase().contains("id"))) {
					Prenotazione prenotazione = new Prenotazione(row[0], row[1], row[2], row[3], row[4]);
					prenotazioni.add(prenotazione);
				}						
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
			
		} finally {
			
			try {
				reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
				
		return prenotazioni;
	}

	
	public void eliminaPrenotazione(String idPrenotazione) throws FileNotFoundException, IOException {
		
		BufferedWriter writer = null;
		
		try {

			List<Prenotazione> prenotazioni = retrive();
			
			writer = new BufferedWriter(new FileWriter(Utilita.BASEPATH + "prenotazioni.csv"));
			String tableText = "ID;ID Attivita;ID Utente;Data inizio;Data fine\n";
			
			for (Prenotazione p : prenotazioni) {
				if (!p.getId().equals(idPrenotazione)) {
					tableText += p.csv() + "\n";
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
			}
			
		}
	
	}
	
	
}
