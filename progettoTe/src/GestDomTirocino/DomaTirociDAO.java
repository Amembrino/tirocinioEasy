
package GestDomTirocino;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import Db.Connector;
import bean.DomandaTirocinio;
import bean.ListDomandeTiro;
import gestRegTir.createRegistroDAO;
import GestDomTirocino.DriverManagerConnectionPool;
 

public class DomaTirociDAO {
	String ip = "localhost";
	String port = "3306";
	String db = "tirocinioeasy";
	String username = "root";
	String password = "root";
	
	
	public DomaTirociDAO()   {
	 }
	
	public void compilaDoma(DomandaTirocinio Data) throws SQLException    {
  System.out.println("tutor aziendaleemail  "+ Data.getTutoAzrEmanil().toString()+" email tirocinante   "+ Data.getTirocinanteEmail().toString()+" email tutr uni   "+Data.getTutoUnirEmanil());
		 
		Random ran=new Random();
	int x=	ran.nextInt(200);
		
		  java.util.Date Dat  = new java.util.Date();
		new java.sql.Date(Dat.getTime());
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		   String da=dt1.format(Dat );
		
		 
		 
		  Connection connection = null;
			PreparedStatement st = null;
     
		
       String sql2=" INSERT INTO domanda_di_tirocinio  (Id_Documento, Data, Firma_tutor_universitario, Firma_tutor_aziendale, "
       		+ "Firma_dirigente_az, Firma_dir_dip, Tutor_UniversitarioEmail, TirocinanteEmail, dir_dipartimentoEmail, Tutor_Aziendale_Email) "
    		+ "VALUES (?,?,?,?,?,?,?,?,?,?) ";
   	try { 
       connection = DriverManagerConnectionPool.getConnection();
		st = connection.prepareStatement(sql2);
		
	st.setInt(1,Integer.parseInt(null));
	st.setString(2,da );
	st.setInt(3,0 );
	st.setInt(4,0 );
	st.setInt(5, 0);
	st.setInt(6, 0);
	st.setString(7, Data.getTutoUnirEmanil());
	st.setString(8, Data.getTirocinanteEmail() );
	st.setString(8, "Fulgenzio@unisa.it");
	 st.setString(2,Data.getAzienda());
	 
	
 
    st.executeUpdate();

	connection.commit();
} finally {
	try {
		if (st != null)
			st.close();
	} finally {
		DriverManagerConnectionPool.releaseConnection(connection);
	}
   }
  }
	
	
	
	public void fillDomadeTiroTA_DB(ListDomandeTiro listaDomande, String taz) throws SQLException, ClassNotFoundException{
    	Connection newConnection = null;
//		PreparedStatement preparedStatement = null;
		
		
	    
		String nome=taz;
		String sql="select * from domandatirocinio where TutorAziendaleEmail= '"+nome+"' and FirmaTutorAziendale='0'";
     
    
		    //STEP 2: Register JDBC driver
		    Class.forName("com.mysql.jdbc.Driver");
   
		   newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db, username, password);
		
		 	try{
		 		 java.sql.Statement st  = newConnection.createStatement();
		 		  ResultSet rs = st.executeQuery(sql);
     	
		 		  while(rs.next()){
			
		    //Retrieve by column name
		    int id  = rs.getInt("IdDoccumento");
		    String azienda = rs.getString("Azienda");
		    Date data = rs.getDate("data") ;
		    int FirmatutorUniversitario = rs.getInt("FirmatutorUniversitario");
		    int firma_tutor_aziendale = rs.getInt("FirmaTutorAziendale");
		    int firma_dir_az=rs.getInt("Firma_dirigente_az");
		    int firma_dir_dip=rs.getInt("Firma_dir_dip");
		    String TutorUniversitarioEmail=rs.getString("TutorUniversitarioEmail");
		    String TirocinanteEmail=rs.getString("TirocinanteEmail");
		    String TutorAziendaleEmail=rs.getString("TutorAziendaleEmail");
         
		  DomandaTirocinio doma =new DomandaTirocinio(id, azienda, data, FirmatutorUniversitario, firma_tutor_aziendale, firma_dir_az, firma_dir_dip, TutorUniversitarioEmail, TirocinanteEmail, TutorAziendaleEmail);
		  listaDomande.aggiungi(doma);
	}

			 		 st.close();
			 		newConnection.close();
		
  }catch (SQLException  e) {
	e.printStackTrace();
 }
	
	}
	
	  public void fillListaDomandeTAZ (ListDomandeTiro domande,  String taz) {
	      
		  try {
			fillDomadeTiroTA_DB(domande, taz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }	
	  
	  
	  
		public void fillDomadeTiroTu_DB(ListDomandeTiro listaDomande, String TUNI) throws SQLException, ClassNotFoundException{
	    	Connection newConnection = null;
//			PreparedStatement preparedStatement = null;
			
			
		    
			String nome=TUNI;
			String sql="select * from domandatirocinio where TutorUniversitarioEmail='"+nome+"' and FirmatutorUniversitario='0'";
	     
	    
			    //STEP 2: Register JDBC driver
			    Class.forName("com.mysql.jdbc.Driver");

			
	        
			newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db, username, password);
			
			 	try{
			 		 java.sql.Statement st  = newConnection.createStatement();
			 		  ResultSet rs = st.executeQuery(sql);
	     	
			 		  while(rs.next()){
				
			    //Retrieve by column name
			    int id  = rs.getInt("IdDoccumento");
			    String azienda = rs.getString("Azienda");
			    Date data = rs.getDate("data") ;
			    int FirmatutorUniversitario = rs.getInt("FirmatutorUniversitario");
			    int firma_tutor_aziendale = rs.getInt("FirmaTutorAziendale");
			    int firma_dir_az=rs.getInt("Firma_dirigente_az");
			    int firma_dir_dip=rs.getInt("Firma_dir_dip");
			    String TutorUniversitarioEmail=rs.getString("TutorUniversitarioEmail");
			    String TirocinanteEmail=rs.getString("TirocinanteEmail");
			    String TutorAziendaleEmail=rs.getString("TutorAziendaleEmail");
	         
			    DomandaTirocinio doma =new DomandaTirocinio(id, azienda, data, FirmatutorUniversitario, firma_tutor_aziendale, firma_dir_az, firma_dir_dip, TutorUniversitarioEmail, TirocinanteEmail, TutorAziendaleEmail);
				  listaDomande.aggiungi(doma);
		}
		
			 		 st.close();
			 		newConnection.close();
	  }catch (SQLException  e) {
		e.printStackTrace();
	 }
		
		}
		
		  public void fillListaDomandeTUNI (ListDomandeTiro domande,  String TUNI) {
		      
			  try {
				  fillDomadeTiroTu_DB(domande, TUNI);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
	
		  
		  public void  fillListaDomandeDirDip(ListDomandeTiro listaDomande, String dirdip){
			
			  Connection newConnection=Connector.getConnection();
			try{
		 		 java.sql.Statement st  = newConnection.createStatement();
		 		  String sql ="select * from domandatirocinio where dir_dipartimentoEmail='"+dirdip+"' and FirmatutorUniversitario='1' "
		 		  		      + "and Firma_tutor_aziendale= '1' and  Firma_dir_dip='0'";
				ResultSet rs = st.executeQuery(sql );
    	
		 		  while(rs.next()){
			
		    //Retrieve by column name
		    int id  = rs.getInt("IdDoccumento");
		    String azienda = rs.getString("Azienda");
		    Date data = rs.getDate("data") ;
		    int FirmatutorUniversitario = rs.getInt("FirmatutorUniversitario");
		    int firma_tutor_aziendale = rs.getInt("FirmaTutorAziendale");
		    int firma_dir_az=rs.getInt("Firma_dirigente_az");
		    int firma_dir_dip=rs.getInt("Firma_dir_dip");
		    String TutorUniversitarioEmail=rs.getString("TutorUniversitarioEmail");
		    String TirocinanteEmail=rs.getString("TirocinanteEmail");
		    String TutorAziendaleEmail=rs.getString("TutorAziendaleEmail");
        
		    DomandaTirocinio doma =new DomandaTirocinio(id, azienda, data, FirmatutorUniversitario, firma_tutor_aziendale, firma_dir_az, firma_dir_dip, TutorUniversitarioEmail, TirocinanteEmail, TutorAziendaleEmail);
			  listaDomande.aggiungi(doma);
	}
	
		
 }catch (SQLException  e) {
	e.printStackTrace();
}
			
		}
	  
	  
	  
public void firmaTAz(int id) {
   Connection conn=null;
 
	 try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 try {
		conn = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db, username, password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
 try {
	int  firma=1;
	 java.sql.Statement  stmt = conn.createStatement();
	 String sql="UPDATE domandatirocinio  SET FirmaTutorAziendale = "+firma+" WHERE IdDoccumento= '"+id+"' ";
    System.out.println(sql);
	 stmt.executeUpdate(sql);
       

	 stmt.close();
     conn.close();

 } catch (SQLException ex) {
    ex.printStackTrace();
 }
 

}


public void firmaTuni(int id) {
	   Connection conn=null;
	 
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			conn = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	 try {
		int  firma=1;
		 java.sql.Statement  stmt = conn.createStatement();
		 String sql="UPDATE domandatirocinio  SET FirmatutorUniversitario= "+firma+" WHERE IdDoccumento= '"+id+"' ";
	    System.out.println(sql);
		 stmt.executeUpdate(sql);
	       

		 stmt.close();
	     conn.close();

	 } catch (SQLException ex) {
	    ex.printStackTrace();
	 }
	 

	}
/** Metodo per la firma del dirigente aziendale*/
public void firmaDirAz(int id) {
	   Connection conn=null;
	 
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			conn = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	 try {
		int  firma=1;
		 java.sql.Statement  stmt = conn.createStatement();
		 String sql="UPDATE domandatirocinio  SET Firma_dirigente_az= "+firma+" WHERE IdDoccumento= '"+id+"' ";
	    System.out.println(sql);
		 stmt.executeUpdate(sql);
	       

		 stmt.close();
	     conn.close();

	 } catch (SQLException ex) {
	    ex.printStackTrace();
	 }
	 

	}

/** Metodo per la firma del direttore di dipartimento*/
public void firmaDirDip(int id) {
	   Connection conn=null;
	 
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			conn = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	 try {
		int  firma=1;
		 java.sql.Statement  stmt = conn.createStatement();
		 String sql="UPDATE domandatirocinio  SET Firma_dir_dip= "+firma+" WHERE IdDoccumento= '"+id+"' ";
	    System.out.println(sql);
	    
	    /** Creazione registro in seguito all'accettazione della domanda*/
	    createRegistroDAO cr=new createRegistroDAO();
	    /**vengono prelevati i dati del nuovo registro direttamente dalla domanda*/
	    cr.getDatiDomanda(id);
	    /**viene creato il registro nel db*/
	    cr.CreaRegistro(id, 0);
	    
	
		 stmt.executeUpdate(sql);
	       

		 stmt.close();
	     conn.close();

	 } catch (SQLException ex) {
	    ex.printStackTrace();
	 }
	 

	}

}