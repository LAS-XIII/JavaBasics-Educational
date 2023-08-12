package GestionePrenotazioni.model;

public class Corso {

	private String id;
	private String nome;
	private String descrizione;
	private String data;
	private String durata;
	private String luogo;
	private String disponibile;
	
	
	public Corso() {
		super();
	}
	
	public Corso(String id, String nome, String descrizione, String data, String durata, String luogo, String disponibile) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.data = data;
		this.durata = durata;
		this.luogo = luogo;
		this.disponibile = disponibile;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getDisponibile() {
		return disponibile;
	}

	public void setDisponibile(String disponibile) {
		this.disponibile = disponibile;
	}

	@Override
	public String toString() {
		return "\nCorso [\nid=" + id + ", \nnome=" + nome + ", \ndescrizione=" + descrizione + ", \ndata=" + data + ", \ndurata="
				+ durata + ", \nluogo=" + luogo + ", \ndisponibile=" + disponibile + "]";
	}
	
	public String csv() {
		return id+";" +
				nome+";" +
				descrizione+";" +
				data+";" +
				durata+";" +
				luogo+";" +
				disponibile+";";
	}
	
	
}
