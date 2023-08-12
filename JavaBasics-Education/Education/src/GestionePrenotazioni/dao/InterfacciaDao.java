package GestionePrenotazioni.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface InterfacciaDao <T>{

	public List<T> retrive() throws FileNotFoundException, IOException ;
	
}
