package tirocinio.registro.gestore;

import bean.Attivitą;
import java.util.ArrayList;
import java.util.List;
 
public class ListaAttivitą {

  private List<Attivitą> attivity;
	
	
  public ListaAttivitą() {
    this.attivity = new ArrayList<Attivitą>();
  }
	
  public List<Attivitą> getAttivitą() {
    return attivity;
  }
	
  public void aggiungi(Attivitą reg) {
    attivity.add(reg);
  }
}