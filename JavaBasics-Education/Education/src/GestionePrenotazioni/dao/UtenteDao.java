package GestionePrenotazioni.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import GestionePrenotazioni.model.Utente;
import GestionePrenotazioni.utility.Utilita;

public class UtenteDao implements InterfacciaDao<Utente>{

	@Override
	public List<Utente> retrive() throws FileNotFoundException, IOException {
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(Utilita.BASEPATH + "utenti.csv"));
			List<Utente> utenti = new ArrayList<>();
			String line = "";
			while((line = reader.readLine()) != null) {
				String[] row = line.split(";");
				if (!row[0].equals("ID")) {
					utenti.add(new Utente(row[0], row[1], row[2], row[3], row[4], row[5]));
				}
			}			
			List<Utente> utentiVeri = new ArrayList<>();
			for (int i = 0; i<utenti.size(); i++) {
				if (i!=0) {
					utentiVeri.add(utenti.get(i));
				}
			}
			
			return utentiVeri;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
			    reader.close();
			} catch (IOException e) {
			    e.printStackTrace();
			    throw e;
			}
		}
	}

	
	public void aggiungiUtente(String id, String nome, String cognome, String dataDiNascita, String indirizzo,
			String documentoId) throws FileNotFoundException, IOException {
		
		BufferedWriter writer = null;
		
		try {

			writer = new BufferedWriter(new FileWriter(Utilita.BASEPATH + "utenti.csv", true));

			Utente nuovoUtente = new Utente(id, nome, cognome, dataDiNascita, indirizzo, documentoId);
			
			writer.write("\n" + nuovoUtente.csv());
			writer.flush();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
			    writer.close();
			} catch (IOException e) {
			    e.printStackTrace();
			    throw e;
			}
		}
		
	}
	
}
