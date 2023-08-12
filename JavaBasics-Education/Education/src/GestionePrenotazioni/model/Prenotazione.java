package GestionePrenotazioni.model;

public class Prenotazione {

	private String id;
	private String idAttivita;
	private String idUtente;
	private String dataInizio;
	private String dataFine;
	
	
	public Prenotazione() {
		super();
	}

	public Prenotazione(String id, String idAttivita, String idUtente, String dataInizio, String dataFine) {
		super();
		this.id = id;
		this.idAttivita = idAttivita;
		this.idUtente = idUtente;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdAttivita() {
		return idAttivita;
	}

	public void setIdAttivita(String idAttivita) {
		this.idAttivita = idAttivita;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	
	
	@Override
	public String toString() {
		return "Prenotazione [id=" + id + ", idAttivita=" + idAttivita + ", idUtente=" + idUtente + ", dataInizio="
				+ dataInizio + ", dataFine=" + dataFine + "]";
	}

	public String csv() {
		return id+";" +
				idAttivita+";" +
				idUtente+";" +
				dataInizio+";" +
				dataFine+";";
	}
	
}
