package GestionePrenotazioni.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import GestionePrenotazioni.businessLogic.BusinessLogic;

public class TestCaricamento {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		try {
			
			BusinessLogic bl = new BusinessLogic();
			Boolean flag = true;
			
			do {				
				System.out.println("\nSeleziona una di queste opzioni scrivendo il numero corrispondente:\n"
						+ "1 \t Visualizzare tutti i corsi all'interno del sistema \n"
						+ "2 \t Prenotare un corso esistente \n"
						+ "3 \t Disdire la prenotazione di un corso \n"
						+ "4 \t Aggiungere un nuovo utente \n"
						+ "5 \t Esportare un file con i corsi pubblici \n"
						+ "0 \t Uscire dal programma");
				System.out.println();
				Integer answer = chiediNumero(sc, 0, 5, "puoi inserire solo un numero intero contenuto tra 0 e 5. Riprova");
				
				switch(answer) {				
					case 0: {
						flag = false;
						break;
					}
					case 1: {
						bl.visualizzaCorsi();
						break;
					}
					case 2: {
						prenotazione(sc);
						break;
					}
					case 3: {
						cancellazione(sc);
						break;
					}
					case 4: {
						nuovoUtente(sc);
						break;
					}
					case 5: {
						bl.EsportaCorsi();
						break;
					}
				}
				
			} while (flag);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		
	}
	
	
	public static Integer chiediNumero(Scanner sc, Integer min, Integer max, String mess) {
		while (true) {
			try {
				String s = sc.nextLine();
				if (s.trim().isEmpty() || s == null) {
					throw new InputMismatchException();
				}	
				if (Integer.parseInt(s)<=min && Integer.parseInt(s)>=max) {
					throw new InputMismatchException();
				}
				return Integer.parseInt(s);
			} catch (InputMismatchException | NumberFormatException e) {
				System.out.println(mess);
			}
		}
	}
	
	
	public static void prenotazione(Scanner sc) throws FileNotFoundException, IOException {
		
		BusinessLogic bl = new BusinessLogic();
		String idCorso = "";
		String idUtente = "";
		
		do {
			try {
				System.out.println("perfavore inserisci l'id del corso");
				idCorso = sc.nextLine();
				System.out.println("perfavore inserisci l'id dell'utente");
				idUtente = sc.nextLine();
				
				Integer.parseInt(idCorso);
				Integer.parseInt(idUtente);
				break;
				
			} catch (InputMismatchException | NumberFormatException e) {
				System.out.println("puoi inserire solo numeri interi");
			}
			
		} while (true);

		bl.prenotaCorso(idCorso, idUtente);	
		
	}	
	

	public static void cancellazione(Scanner sc) throws FileNotFoundException, IOException {
		
		BusinessLogic bl = new BusinessLogic();
		String idCorso = "";
		String idUtente = "";
		
		do {
			try {
				System.out.println("perfavore inserisci l'id del corso");
				idCorso = sc.nextLine();
				System.out.println("perfavore inserisci l'id dell'utente");
				idUtente = sc.nextLine();
				
				Integer.parseInt(idCorso);
				Integer.parseInt(idUtente);
				break;
				
			} catch (InputMismatchException | NumberFormatException e) {
				System.out.println("puoi inserire solo numeri interi");
			}
			
		} while (true);
		
		bl.eliminaPrenotazione(idCorso, idUtente);
		
	}	
	
	
	public static void nuovoUtente(Scanner sc) throws FileNotFoundException, IOException {
		
		BusinessLogic bl = new BusinessLogic();

		System.out.println("perfavore inserisci il nome dell'utente");
		String nome = sc.nextLine();
		System.out.println("perfavore inserisci il cognome dell'utente");
		String cognome = sc.nextLine();
		System.out.println("perfavore inserisci la data di nascita");
		String dataDiNascita = sc.nextLine();
		System.out.println("perfavore inserisci l'indirizzo");
		String indirizzo = sc.nextLine();
		System.out.println("perfavore inserisci l'id del documento");
		String documentoId = sc.nextLine();
		
		bl.creaUtente(nome, cognome, dataDiNascita, indirizzo, documentoId);	
		
	}
	
	
}







