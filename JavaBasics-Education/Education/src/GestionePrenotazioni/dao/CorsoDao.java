package GestionePrenotazioni.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import GestionePrenotazioni.model.Corso;
import GestionePrenotazioni.utility.Utilita;

public class CorsoDao implements InterfacciaDao<Corso>{

	
	@Override
	public List<Corso> retrive() throws FileNotFoundException, IOException {
		
		BufferedReader reader = null;
		
		try {
			System.out.println(Utilita.BASEPATH);
			reader = new BufferedReader(new FileReader(Utilita.BASEPATH + "corsi.csv"));
			List<Corso> corsi = new ArrayList<>();
			String line = "";
			while((line = reader.readLine()) != null) {
				String[] row = line.split(";");
				corsi.add(new Corso(row[0], row[1], row[2], row[3], row[4], row[5], row[6]));
			}			
			return corsi;
			
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
	
	
	public void aggiornaDisponibilita(String idCorso, String disponibilita) throws FileNotFoundException, IOException {
		
		BufferedWriter writer = null;
		
		try {
			List<Corso> corsi = retrive();

			writer = new BufferedWriter(new FileWriter(Utilita.BASEPATH + "corsi.csv"));
			String tableText = "";
			for (Corso c : corsi) {
				if (c.getId().equals(idCorso)) {
					c.setDisponibile(disponibilita);
				}
				tableText += c.csv()+"\n";
			}
			writer.write(tableText);
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
