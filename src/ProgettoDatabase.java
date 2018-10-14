import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgettoDatabase {
	public static Connection con = null;

	public static void main(String[] arg) {
		JButton connection;
		JButton add;
		JButton delete;
		JButton modify;
		JButton exec_query;
		JComboBox choice_entities;
		JComboBox choice_query;
		JComboBox choice_entities2;
		JComboBox choice_entities3;
		JTextArea list_query;
		JFrame frame;

		list_query = new JTextArea(
				"1)Elenco tratta\n" + "2)Elenco sanzioni\n" + "3)Elenco tratte in partenza alle 13.00\n"
						+ "4)Numero sanzioni effettuate nella tratta della linea 2\n"
						+ "5)Codice controllore che ha emesso più sanzioni\n"
						+ "6)Codice controllore che non ha mai emesso sanzioni\n"
						+ "7)Numero dell'autobus che è stato guidato da più di 3 autisti\n"
						+ "8)Orari Partenza di una determinata tratta\n"
						+ "9)Orari Arrivo di una determinata tratta\n"
						+ "10)Codice fiscale di tutti gli autisti di cui si conosce l'iniziale del cognome\n"
						+ "11)Autisti che hanno guidato il 2 in data 2017-12-12 ma non il 4\n");
		JPanel east = new JPanel();
		east.add(list_query);

		String[] list_entities = { "Autista", "Autobus", "Tratta", "Sanzione", "Controllore", "PasseggeroMultato",
				"Biglietto", "Turno","OrarioPartenza", "OrarioArrivo" };

		connection = new JButton("Connettiti");
		add = new JButton("Aggiungi istanza");
		delete = new JButton("Elimina istanza");
		modify = new JButton("Modifica istanza");
		choice_entities = new JComboBox(list_entities);
		choice_entities2 = new JComboBox(list_entities);
		choice_entities3 = new JComboBox(list_entities);
		JPanel west = new JPanel(new GridLayout(4, 2));
		west.add(connection);
		west.add(new JPanel());
		west.add(add);
		west.add(choice_entities);
		west.add(delete);
		west.add(choice_entities2);
		west.add(modify);
		west.add(choice_entities3);

		String[] query = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11" };
		choice_query = new JComboBox(query);
		exec_query = new JButton("Esegui query");
		JPanel south = new JPanel();
		south.add(choice_query);
		south.add(exec_query);

		frame = new JFrame();
		frame.setLayout(new GridLayout(3, 0));
		frame.setSize(500, 600);
		frame.add(west);
		frame.add(east);
		frame.add(south);
		frame.setVisible(true);

		connection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/progettodatabase";
					String username = "root";
					String pwd = "";
					con = DriverManager.getConnection(url, username, pwd);
					JOptionPane.showMessageDialog(null,"Connesso");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Connessione fallita!");
				}
			}

		});

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				try {

					Statement test = con.createStatement();
					if (choice_entities2.getSelectedItem().equals("Autista")) {
						String CodiceFiscaleDaEliminare = JOptionPane
								.showInputDialog("Inserire codice fiscale da eliminare");
						String DaEliminare = "DELETE FROM autista WHERE CodiceFiscale='" + CodiceFiscaleDaEliminare
								+ "'";
						if(test.executeUpdate(DaEliminare)!=0)
						JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!");
						else
						JOptionPane.showMessageDialog(null,"Cancellazione fallita,dati non corretti!");
						
					}

					if (choice_entities2.getSelectedItem().equals("Biglietto")) {
						String Fascia = JOptionPane.showInputDialog("Inserire biglietto da eliminare");
						String DaEliminare = "DELETE FROM biglietto WHERE Fascia='" + Fascia + "'";

						if(test.executeUpdate(DaEliminare)!=0)
							JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!");
							else
							JOptionPane.showMessageDialog(null,"Cancellazione fallita,dati non corretti!");

					}
					if (choice_entities2.getSelectedItem().equals("Autobus")) {
						String Numero = JOptionPane.showInputDialog("Inserire autobus da eliminare");
						String DaEliminare = "DELETE FROM autobus WHERE NumeroAutobus=" + Numero;

						if(test.executeUpdate(DaEliminare)!=0)
							JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!");
							else
							JOptionPane.showMessageDialog(null,"Cancellazione fallita,dati non corretti!");
					}
					if (choice_entities2.getSelectedItem().equals("Controllore")) {
						String Numero = JOptionPane.showInputDialog("Inserire codice controllore da eliminare");
						String DaEliminare = "DELETE FROM controllore WHERE Codice=" + Numero;
						if(test.executeUpdate(DaEliminare)!=0)
							JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!");
							else
							JOptionPane.showMessageDialog(null,"Cancellazione fallita,dati non corretti!");
					}
					if (choice_entities2.getSelectedItem().equals("Turno")) {
						String Data = JOptionPane.showInputDialog("Inserire data del turno da eliminare");
						String Ora = JOptionPane.showInputDialog("Inserire ora del turno da eliminare");
						String Autista = JOptionPane.showInputDialog("Inserire CodiceFiscale dell'autista del turno da eliminare");
						

						String DaEliminare = "DELETE FROM turno WHERE Data='" + Data + "'"+"AND Ora='"+Ora+"'"+"AND Autista='"+Autista+"'";

						if(test.executeUpdate(DaEliminare)!=0)
							JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!");
							else
							JOptionPane.showMessageDialog(null,"Cancellazione fallita,dati non corretti!");
					}
					if (choice_entities2.getSelectedItem().equals("PasseggeroMultato")) {
						String CodiceFiscale = JOptionPane
								.showInputDialog("Inserire codice fiscale passeggero multato da eliminare");
						String DaEliminare = "DELETE FROM passeggeromultato WHERE CodiceFiscale='" + CodiceFiscale + "'";
						if(test.executeUpdate(DaEliminare)!=0)
							JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!");
							else
							JOptionPane.showMessageDialog(null,"Cancellazione fallita,dati non corretti!");
					}

					if (choice_entities2.getSelectedItem().equals("Sanzione")) {
						String Codice = JOptionPane.showInputDialog("Inserire codice sanzione da eliminare");
						String DaEliminare = "DELETE FROM sanzioni WHERE Codice=" + Codice;
						if(test.executeUpdate(DaEliminare)!=0)
							JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!");
							else
							JOptionPane.showMessageDialog(null,"Cancellazione fallita,dati non corretti!");
					}
					if (choice_entities2.getSelectedItem().equals("Tratta")) {
						String NomeTratta = JOptionPane.showInputDialog("Inserire nome tratta da eliminare");
						String DaEliminare = "DELETE FROM tratta WHERE NomeTratta='" + NomeTratta + "'";
						if(test.executeUpdate(DaEliminare)!=0)
							JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!");
							else
							JOptionPane.showMessageDialog(null,"Cancellazione fallita,dati non corretti!");
					}
					if(choice_entities2.getSelectedItem().equals("OrarioPartenza")) {
						String Orario=JOptionPane.showInputDialog("Inserire orario da eliminare");
						String Tratta=JOptionPane.showInputDialog("Inserire tratta autobus");
						String DaEliminare="DELETE FROM oraripartenza WHERE OrarioP='"+Orario+"'"+"AND Tratta='"+Tratta+"'";
						if(test.executeUpdate(DaEliminare)!=0)
							JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!");
							else
							JOptionPane.showMessageDialog(null,"Cancellazione fallita,dati non corretti!");
					}
					if(choice_entities2.getSelectedItem().equals("OrarioArrivo")) {
						String Orario=JOptionPane.showInputDialog("Inserire orario da eliminare");
						String Tratta=JOptionPane.showInputDialog("Inserire tratta autobus");
						String DaEliminare="DELETE FROM orariarrivo WHERE OrarioA='"+Orario+"'"+"AND Tratta='"+Tratta+"'";
						if(test.executeUpdate(DaEliminare)!=0)
							JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!");
							else
							JOptionPane.showMessageDialog(null,"Cancellazione fallita,dati non corretti!");

					}}catch (Exception e) {

						JOptionPane.showMessageDialog(null,"Connessione al database assente!");	

				}
		

			}
		});

		
		modify.addActionListener(new ActionListener()
		{
	public void actionPerformed(ActionEvent event){		
		
		
try{
			
		Statement test = con.createStatement();
		int i=0;
		
		if(choice_entities3.getSelectedItem().equals("Autista"))
		{
			String[] options=new String[30];
			String[] options2={"Cognome","Nome"};
			
			ResultSet result=test.executeQuery("SELECT CodiceFiscale FROM autista");
			
			while (result.next()){
				String CodiceFiscale =
						result.getString("CodiceFiscale");
						options[i]=CodiceFiscale;
						i++;
						}
			
			
			
			String x = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona Codice Fiscale autista da modificare",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			
			String y = (String) JOptionPane.showInputDialog(frame,
						"Seleziona attributo da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[0]);
			
			
			String z=(String) JOptionPane.showInputDialog("Inserisci valore", JOptionPane.QUESTION_MESSAGE);
			
			
			test.executeUpdate("UPDATE autista SET "+y+"='"+z+"' WHERE CodiceFiscale = '"+x+"';");
			
		
		}
	
		if(choice_entities3.getSelectedItem().equals("Turno"))
		{
		
			String z;
			String[] options=new String[30];
			String[] options2={"Data","Ora","Autista","Autobus"};
			i=0;

			ResultSet result=test.executeQuery("SELECT Data,Ora,Autista FROM turno");
			
			while (result.next()){
				String Data =
						result.getString("Data");
				String Ora =
						result.getString("Ora");
				String Autista = 
						result.getString("Autista");
						options[i]=Data+","+Ora+","+Autista;
						i++;
						}
			
			
			
			String x = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona Parametri turno da modificare",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			String y = (String) JOptionPane.showInputDialog(frame,
						"Seleziona attributo da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[0]);
			if(y.equals("Autista"))
			{
				i=0;
				String[] options3=new String[30];
				
				result=test.executeQuery("SELECT CodiceFiscale FROM autista");
				
				while (result.next()){
					String Cod =
							result.getString("CodiceFiscale");
							options3[i]=Cod;
							i++;
							}
				
				
				
				z = (String) JOptionPane.showInputDialog(frame, 
					        "Seleziona nuovo Codice Fiscale",
					        null,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        options3, 
					        options3[0]);
				
				
			}
			else{
			if(y.equals("Autobus"))
			{
				i=0;
				String[] options3=new String[30];
				
				result=test.executeQuery("SELECT NumeroAutobus FROM autobus");
				
				while (result.next()){
					String Num =
							result.getString("NumeroAutobus");
							options3[i]=Num;
							i++;
							}
				
				
				
				z = (String) JOptionPane.showInputDialog(frame, 
					        "Seleziona nuovo numero autobus",
					        null,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        options3, 
					        options3[0]);
			}
			else
			{
				z=(String) JOptionPane.showInputDialog("Inserisci valore", JOptionPane.QUESTION_MESSAGE);

			}
			}
			
			
			
			String[] parts = x.split(",");
			String part1 = parts[0]; 
			String part2 = parts[1]; 
			String part3 = parts[2];
			
			test.executeUpdate("UPDATE turno SET "+y+"='"+z+"' WHERE Data = '"+part1+"' AND Ora='"+part2+"' AND Autista='"+part3+"';");
			
		
		}
	
		if(choice_entities3.getSelectedItem().equals("Autobus"))
		{
		
			String[] options=new String[30];
			String[] options2={"Modello","NumeroPosti"};
			i=0;

			ResultSet result=test.executeQuery("SELECT NumeroAutobus FROM autobus");
			
			while (result.next()){
				String Numero =
						result.getString("NumeroAutobus");
						options[i]=Numero;
						i++;
						}
			
			
			
			String x = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona numero autobus da modificare",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			String y = (String) JOptionPane.showInputDialog(frame,
						"Seleziona attributo da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[0]);
			
			
			String z=(String) JOptionPane.showInputDialog("Inserisci valore", JOptionPane.QUESTION_MESSAGE);
			
			test.executeUpdate("UPDATE autobus SET "+y+"='"+z+"' WHERE NumeroAutobus = '"+x+"';");

		
		}
	
	
		if(choice_entities3.getSelectedItem().equals("Tratta"))
		{
			String z = null;
			String[] options=new String[30];
			String[] options2={"Biglietto","NumeroAutobus"};
			i=0;

			ResultSet result=test.executeQuery("SELECT NomeTratta FROM tratta");
			
			while (result.next()){
				String Nome =
						result.getString("NomeTratta");
						options[i]=Nome;
						i++;
						}
			
			
			
			String x = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona numero tratta da modificare",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			String y = (String) JOptionPane.showInputDialog(frame,
						"Seleziona attributo da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[0]);
			
			if(y.equals("Biglietto"))
			{
				i=0;
				String[] options3=new String[30];
				
				result=test.executeQuery("SELECT Fascia FROM biglietto");
				
				while (result.next()){
					String Fasc =
							result.getString("Fascia");
							options3[i]=Fasc;
							i++;
							}
				
				
				
				z = (String) JOptionPane.showInputDialog(frame, 
					        "Seleziona nuova fascia biglietto",
					        null,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        options3, 
					        options3[0]);
				
				
			}
			if(y.equals("NumeroAutobus"))
			{
				i=0;
				String[] options3=new String[30];
				
				result=test.executeQuery("SELECT NumeroAutobus FROM autobus");
				
				while (result.next()){
					String Num =
							result.getString("NumeroAutobus");
							options3[i]=Num;
							i++;
							}
				
				
				
				z = (String) JOptionPane.showInputDialog(frame, 
					        "Seleziona nuova fascia biglietto",
					        null,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        options3, 
					        options3[0]);
			}
			
			
			
			test.executeUpdate("UPDATE tratta SET "+y+"='"+z+"' WHERE NomeTratta = '"+x+"';");
		
		
		}
		
		
	
		if(choice_entities3.getSelectedItem().equals("Sanzione"))
		{
			String z=null;
			String[] options=new String[30];
			String[] options2={"Controllore","Passeggero","Tratta","Causale","DataEmissione","Importo","Scadenza"};
			i=0;

			ResultSet result=test.executeQuery("SELECT Codice FROM sanzioni");
			
			while (result.next()){
				String Cod =
						result.getString("Codice");
						options[i]=Cod;
						i++;
						}
			
			
			
			String x = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona codice sanzione da modificare",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			String y = (String) JOptionPane.showInputDialog(frame,
						"Seleziona attributo da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[0]);
			
			if(y.equals("Controllore"))
			{
				i=0;
				String[] options3=new String[30];
				
				result=test.executeQuery("SELECT Codice FROM controllore");
				
				while (result.next()){
					String Cod =
							result.getString("Codice");
							options3[i]=Cod;
							i++;
							}
				
				
				
				z = (String) JOptionPane.showInputDialog(frame, 
					        "Seleziona nuovo codice controllore",
					        null,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        options3, 
					        options3[0]);
				
				
			}
			else{
				if(y.equals("Passeggero"))
				{
					
					i=0;
					String[] options3=new String[30];
					
					result=test.executeQuery("SELECT CodiceFiscale FROM passeggeromultato");
					
					while (result.next()){
						String Cod =
								result.getString("CodiceFiscale");
								options3[i]=Cod;
								i++;
								}
					
					
					
					z = (String) JOptionPane.showInputDialog(frame, 
						        "Seleziona nuovo codice fiscale passeggero multato",
						        null,
						        JOptionPane.QUESTION_MESSAGE, 
						        null, 
						        options3, 
						        options3[0]);
					
				}
				else
				{
					if(y.equals("Tratta"))
					{
						i=0;
						String[] options3=new String[30];
						
						result=test.executeQuery("SELECT NomeTratta FROM tratta");
						
						while (result.next()){
							String Nome =
									result.getString("NomeTratta");
									options3[i]=Nome;
									i++;
									}
						
						
						
						z = (String) JOptionPane.showInputDialog(frame, 
							        "Seleziona nuova tratta",
							        null,
							        JOptionPane.QUESTION_MESSAGE, 
							        null, 
							        options3, 
							        options3[0]);
						
						
					}
					else
					{
						z=(String) JOptionPane.showInputDialog("Inserisci valore", JOptionPane.QUESTION_MESSAGE);
					}
				}
			}
			
			
			
			test.executeUpdate("UPDATE sanzioni SET "+y+"='"+z+"' WHERE Codice = '"+x+"';");
		
		
		}
		
		if(choice_entities3.getSelectedItem().equals("PasseggeroMultato"))
		{
			String[] options=new String[30];
			String[] options2={"Cognome","Indirizzo","Nome","Recapito telefonico"};
			i=0;

			ResultSet result=test.executeQuery("SELECT CodiceFiscale FROM passeggeromultato");
			
			while (result.next()){
				String Cod =
						result.getString("CodiceFiscale");
						options[i]=Cod;
						i++;
						}
			
			
			
			String x = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona codice fiscale passeggero da modificare",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			String y = (String) JOptionPane.showInputDialog(frame,
						"Seleziona attributo da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[0]);
			
			
			String z=(String) JOptionPane.showInputDialog("Inserisci valore", JOptionPane.QUESTION_MESSAGE);
			
			test.executeUpdate("UPDATE passeggeromultato SET "+y+"='"+z+"' WHERE CodiceFiscale = '"+x+"';");
		
		
		}
	
	
		if(choice_entities3.getSelectedItem().equals("Controllore"))
		{
			String[] options=new String[30];
			String[] options2={"Cognome","Nome"};
			i=0;

			ResultSet result=test.executeQuery("SELECT Codice FROM controllore");
			
			while (result.next()){
				String Cod =
						result.getString("Codice");
						options[i]=Cod;
						i++;
						}
			
			
			
			String x = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona codice controllore da modificare",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			String y = (String) JOptionPane.showInputDialog(frame,
						"Seleziona attributo da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[0]);
			
			
			String z=(String) JOptionPane.showInputDialog("Inserisci valore", JOptionPane.QUESTION_MESSAGE);
			
			test.executeUpdate("UPDATE controllore SET "+y+"='"+z+"' WHERE Codice = '"+x+"';");
			
		}
		
		
		if(choice_entities3.getSelectedItem().equals("Biglietto"))
		{
			String[] options=new String[30];
			String[] options2={"Prezzo"};
			i=0;

			ResultSet result=test.executeQuery("SELECT Fascia FROM biglietto");
			
			while (result.next()){
				String Fasc =
						result.getString("Fascia");
						options[i]=Fasc;
						i++;
						}
			
			
			
			String x = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona fascia biglietto da modificare",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			String y = (String) JOptionPane.showInputDialog(frame,
						"Seleziona attributo da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[0]);
			
			
			String z=(String) JOptionPane.showInputDialog("Inserisci valore", JOptionPane.QUESTION_MESSAGE);
			
			test.executeUpdate("UPDATE biglietto SET "+y+"='"+z+"' WHERE Fascia = '"+x+"';");
			
		}
		
		
		if(choice_entities3.getSelectedItem().equals("OrarioPartenza"))
		{
			
			String z=null;
			String[] options=new String[30];
			String[] options2={"Tratta"};
			String[] options3=new String[30];
			i=0;

			ResultSet result=test.executeQuery("SELECT OrarioP FROM oraripartenza");
			
			while (result.next()){
				String Ora =
						result.getString("OrarioP");
						options[i]=Ora;
						i++;
						}
			
			i=0;
			result=test.executeQuery("SELECT Tratta FROM oraripartenza");
			while(result.next()){
				String Trat=
						result.getString("Tratta");
						options3[i]=Trat;
						i++;
						}
			
			String k = (String) JOptionPane.showInputDialog(frame,
						"Seleziona tratta da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options3,
						options3[0]);
			
			
			
			String x = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona orario da modificare",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			String y = (String) JOptionPane.showInputDialog(frame,
						"Seleziona attributo da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[0]);
			
			if(y.equals("Tratta"))
			{
				i=0;
				String[] options4=new String[30];
				
				result=test.executeQuery("SELECT NomeTratta FROM tratta");
				
				while (result.next()){
					String Nome =
							result.getString("NomeTratta");
							options4[i]=Nome;
							i++;
							}
				
				
				
				z = (String) JOptionPane.showInputDialog(frame, 
					        "Seleziona nuovo codice controllore",
					        null,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        options3, 
					        options3[0]);
				
				
			}

			
			test.executeUpdate("UPDATE oraripartenza SET "+y+"='"+z+"' WHERE OrarioP = '"+x+"' AND Tratta='"+k+"';");
			
			
		}
			
		if(choice_entities3.getSelectedItem().equals("OrarioArrivo"))
		{
			String z=null;
			String[] options=new String[30];
			String[] options2={"Tratta"};
			String[] options3=new String[30];
			i=0;

			ResultSet result=test.executeQuery("SELECT OrarioA FROM orariarrivo");
			
			while (result.next()){
				String Ora =
						result.getString("OrarioA");
						options[i]=Ora;
						i++;
						}
			
			i=0;
			result=test.executeQuery("SELECT Tratta FROM orariarrivo");
			while(result.next()){
				String Trat=
						result.getString("Tratta");
						options3[i]=Trat;
						i++;
						}
			
			String k = (String) JOptionPane.showInputDialog(frame,
						"Seleziona tratta da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options3,
						options3[0]);
			
			
			
			String x = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona orario da modificare",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			String y = (String) JOptionPane.showInputDialog(frame,
						"Seleziona attributo da modificare",
						null,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options2,
						options2[0]);
			
			if(y.equals("Tratta"))
			{
				i=0;
				String[] options4=new String[30];
				
				result=test.executeQuery("SELECT NomeTratta FROM tratta");
				
				while (result.next()){
					String Nome =
							result.getString("NomeTratta");
							options4[i]=Nome;
							i++;
							}
				
				
				
				z = (String) JOptionPane.showInputDialog(frame, 
					        "Seleziona nome tratta",
					        null,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        options3, 
					        options3[0]);
				
				
			}

			
			test.executeUpdate("UPDATE orariarrivo SET "+y+"='"+z+"' WHERE OrarioA = '"+x+"' AND Tratta='"+k+"';");
			
			
		}
		 
	
	
}catch (SQLException e) {

		JOptionPane.showMessageDialog(null,"Dati inseriti non corretti!");	
		

}
catch(NullPointerException e)
{
	if(con==null)
	JOptionPane.showMessageDialog(null,"Connessione al database assente!");
	else
	JOptionPane.showMessageDialog(null, "Mancato inserimento dati!");
}

}
});
		
		
		
		
		
		add.addActionListener(new ActionListener()
		{
	public void actionPerformed(ActionEvent event){

		
		
		
try{

			String Insert;
			
			
			Statement test = con.createStatement();
			int i=0;
			
			if(choice_entities.getSelectedItem().equals("Autista"))
			{	
			String x= JOptionPane.showInputDialog("Inserisci codice fiscale", JOptionPane.QUESTION_MESSAGE);
			String y=JOptionPane.showInputDialog("Inserisci cognome", JOptionPane.QUESTION_MESSAGE);
			String z=JOptionPane.showInputDialog("Inserisci nome", JOptionPane.QUESTION_MESSAGE);
			Insert="INSERT INTO autista(CodiceFiscale,Cognome,Nome) values ('"+x+"','"+y+"','"+z+"')";
			if(test.executeUpdate(Insert)!=0)
				JOptionPane.showMessageDialog(null,"Inserimento eseguito con successo!");
				else
				JOptionPane.showMessageDialog(null,"Inserimento fallito,dati non corretti!");
			}
			
			if(choice_entities.getSelectedItem().equals("Turno"))
			{
				String[] options=new String[30];
				String[] options2=new String[30];
				
			String x= JOptionPane.showInputDialog("Inserisci data", JOptionPane.QUESTION_MESSAGE);
			String w= JOptionPane.showInputDialog("Inserisci orario", JOptionPane.QUESTION_MESSAGE);
			
			ResultSet result=test.executeQuery("SELECT CodiceFiscale FROM autista");
			
			while (result.next()){
				String CodiceFiscale =
						result.getString("CodiceFiscale");
						options[i]=CodiceFiscale;
						i++;
						}
			
			
			
			String y = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona Codice Fiscale autista",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			i=0;
			result=test.executeQuery("SELECT NumeroAutobus FROM autobus");
			while (result.next()){
				String Numero =
						result.getString("NumeroAutobus");
						options2[i]=Numero;
						i++;
						}
			
			String z=(String) JOptionPane.showInputDialog(frame,
					"Seleziona numero autobus",
					null,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options2,
					options2[0]);
			
			Insert="INSERT INTO turno(Data,Ora,Autista,Autobus) values ('"+x+"','"+w+"','"+y+"','"+z+"')";
			if(test.executeUpdate(Insert)!=0)
				JOptionPane.showMessageDialog(null,"Inserimento eseguito con successo!");
				else
				JOptionPane.showMessageDialog(null,"Inserimento fallito,dati non corretti!");
			}
			
			
			if(choice_entities.getSelectedItem().equals("Autobus"))
			{
			String x= JOptionPane.showInputDialog("Inserisci modello", JOptionPane.QUESTION_MESSAGE);
			String y=JOptionPane.showInputDialog("Inserisci numero autobus", JOptionPane.QUESTION_MESSAGE);
			String z=JOptionPane.showInputDialog("Inserisci numero posti", JOptionPane.QUESTION_MESSAGE);
			Insert="INSERT INTO autobus(Modello,NumeroAutobus,NumeroPosti) values ('"+x+"','"+y+"','"+z+"')";
			if(test.executeUpdate(Insert)!=0)
				JOptionPane.showMessageDialog(null,"Inserimento eseguito con successo!");
				else
				JOptionPane.showMessageDialog(null,"Inserimento fallito,dati non corretti!");
			}
			
			if(choice_entities.getSelectedItem().equals("Tratta"))
			{
				String[] options=new String[30];
				String[] options2=new String[30];
				
				i=0;
				ResultSet result=test.executeQuery("SELECT Fascia FROM biglietto");
				
				while (result.next()){
					String Fascia =
							result.getString("Fascia");
							options[i]=Fascia;
							i++;
							}
				
				
				
				String x = (String) JOptionPane.showInputDialog(frame, 
					        "Seleziona Fascia biglietto",
					        null,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        options, 
					        options[0]);
				
				
				
			String y=JOptionPane.showInputDialog("Inserisci Nome tratta", JOptionPane.QUESTION_MESSAGE);
			
			
			i=0;
			result=test.executeQuery("SELECT NumeroAutobus FROM autobus");
			
			while (result.next()){
				String Num =
						result.getString("NumeroAutobus");
						options2[i]=Num;
						i++;
						}
			
			
			
			String z = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona numero autobus",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options2, 
				        options2[0]);
			
			
			Insert="INSERT INTO tratta(Biglietto,NomeTratta,NumeroAutobus) values ('"+x+"','"+y+"','"+z+"')";
			if(test.executeUpdate(Insert)!=0)
				JOptionPane.showMessageDialog(null,"Inserimento eseguito con successo!");
				else
				JOptionPane.showMessageDialog(null,"Inserimento fallito,dati non corretti!");
			}
			
		 if(choice_entities.getSelectedItem().equals("Sanzione"))
			{
				i=0;
				String[] options=new String[30];
				String[] options2=new String[30];
				String[] options3=new String[30];
				
			String x= JOptionPane.showInputDialog("Inserisci codice sanzione", JOptionPane.QUESTION_MESSAGE);
			String y=JOptionPane.showInputDialog("Inserisci data emissione", JOptionPane.QUESTION_MESSAGE);
			String z=JOptionPane.showInputDialog("Inserisci importo", JOptionPane.QUESTION_MESSAGE);
			String w=JOptionPane.showInputDialog("Inserisci scadenza", JOptionPane.QUESTION_MESSAGE);
			String k=JOptionPane.showInputDialog("Inserisci causale", JOptionPane.QUESTION_MESSAGE);
			
			ResultSet result=test.executeQuery("SELECT Codice FROM Controllore");
			
			while (result.next()){
				String Codice =
						result.getString("Codice");
						options[i]=Codice;
						i++;
						}
			
			
			
			String m = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona codice controllore",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			
			result=test.executeQuery("SELECT CodiceFiscale FROM passeggeromultato");
			i=0;
			
			while (result.next()){
				String CF =
						result.getString("CodiceFiscale");
						options2[i]=CF;
						i++;
						}
			
			
			
			String n = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona codice fiscale passeggero",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options2, 
				        options2[0]);
			
			result=test.executeQuery("SELECT NomeTratta FROM Tratta");
			i=0;
			while (result.next()){
				String Nome =
						result.getString("NomeTratta");
						options3[i]=Nome;
						i++;
						}
			
			
			
			String o = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona nome tratta",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options3, 
				        options3[0]);
			
			Insert="INSERT INTO sanzioni(Codice,Controllore,DataEmissione,Importo,Passeggero,Scadenza,Tratta,Causale) values ('"+x+"','"+m+"','"+y+"','"+z+"','"+n+"','"+w+"','"+o+"','"+k+"')";
			if(test.executeUpdate(Insert)!=0)
				JOptionPane.showMessageDialog(null,"Inserimento eseguito con successo!");
				else
				JOptionPane.showMessageDialog(null,"Inserimento fallito,dati non corretti!");
			}
			
			if(choice_entities.getSelectedItem().equals("PasseggeroMultato"))
			{
			String x= JOptionPane.showInputDialog("Inserisci codice fiscale", JOptionPane.QUESTION_MESSAGE);
			String y=JOptionPane.showInputDialog("Inserisci cognome", JOptionPane.QUESTION_MESSAGE);
			String z=JOptionPane.showInputDialog("Inserisci indirizzo", JOptionPane.QUESTION_MESSAGE);
			String w=JOptionPane.showInputDialog("Inserisci nome", JOptionPane.QUESTION_MESSAGE);
			String k=JOptionPane.showInputDialog("Inserisci recapito telefonico", JOptionPane.QUESTION_MESSAGE);
			Insert="INSERT INTO passeggeromultato(CodiceFiscale,Cognome,Indirizzo,Nome,RecapitoTelefonico) values ('"+x+"','"+y+"','"+z+"','"+w+"','"+k+"')";
			if(test.executeUpdate(Insert)!=0)
				JOptionPane.showMessageDialog(null,"Inserimento eseguito con successo!");
				else
				JOptionPane.showMessageDialog(null,"Inserimento fallito,dati non corretti!");
			}
			
			if(choice_entities.getSelectedItem().equals("Controllore"))
			{
			String x= JOptionPane.showInputDialog("Inserisci codice", JOptionPane.QUESTION_MESSAGE);
			String y=JOptionPane.showInputDialog("Inserisci cognome", JOptionPane.QUESTION_MESSAGE);
			String z=JOptionPane.showInputDialog("Inserisci nome", JOptionPane.QUESTION_MESSAGE);
			Insert="INSERT INTO controllore(Codice,Cognome,Nome) values ('"+x+"','"+y+"','"+z+"')";
			if(test.executeUpdate(Insert)!=0)
				JOptionPane.showMessageDialog(null,"Inserimento eseguito con successo!");
				else
				JOptionPane.showMessageDialog(null,"Inserimento fallito,dati non corretti!");
			}
			
			if(choice_entities.getSelectedItem().equals("Biglietto"))
			{
			String x= JOptionPane.showInputDialog("Inserisci  fascia", JOptionPane.QUESTION_MESSAGE);
			String y=JOptionPane.showInputDialog("Inserisci prezzo", JOptionPane.QUESTION_MESSAGE);
			Insert="INSERT INTO biglietto(Fascia,Prezzo) values ('"+x+"','"+y+"')";
			if(test.executeUpdate(Insert)!=0)
				JOptionPane.showMessageDialog(null,"Inserimento eseguito con successo!");
				else
				JOptionPane.showMessageDialog(null,"Inserimento fallito,dati non corretti!");
			}
			
			if(choice_entities.getSelectedItem().equals("OrarioPartenza"))
			{
			i=0;
			String[] options=new String[100];
				
			String x= JOptionPane.showInputDialog("Inserisci orario partenza", JOptionPane.QUESTION_MESSAGE);
			
			
			ResultSet result=test.executeQuery("SELECT NomeTratta FROM Tratta");
			while (result.next()){
				String Nome =
						result.getString("NomeTratta");
						options[i]=Nome;
						i++;
						}
			
			
			
			String y = (String) JOptionPane.showInputDialog(frame, 
				        "Seleziona nome tratta",
				        null,
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        options, 
				        options[0]);
			
			Insert="INSERT INTO oraripartenza(OrarioP,Tratta) values ('"+x+"','"+y+"')";
			if(test.executeUpdate(Insert)!=0)
				JOptionPane.showMessageDialog(null,"Inserimento eseguito con successo!");
				else
				JOptionPane.showMessageDialog(null,"Inserimento fallito,dati non corretti!");
			}
			
			if(choice_entities.getSelectedItem().equals("OrarioArrivo"))
			{
				i=0;
				String[] options=new String[100];
				
				String x= JOptionPane.showInputDialog("Inserisci orario arrivo", JOptionPane.QUESTION_MESSAGE);
				
				
				ResultSet result=test.executeQuery("SELECT NomeTratta FROM Tratta");
				while (result.next()){
					String Nome =
							result.getString("NomeTratta");
							options[i]=Nome;
							i++;
							}
				
				
				
				String y = (String) JOptionPane.showInputDialog(frame, 
					        "Seleziona nome tratta",
					        null,
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        options, 
					        options[0]);
				
				Insert="INSERT INTO orariarrivo(OrarioA,Tratta) values ('"+x+"','"+y+"')";
				if(test.executeUpdate(Insert)!=0)
					JOptionPane.showMessageDialog(null,"Inserimento eseguito con successo!");
					else
					JOptionPane.showMessageDialog(null,"Inserimento fallito,dati non corretti!");
			}
			
		}		
catch (SQLException e) {

	JOptionPane.showMessageDialog(null,"Dati inseriti non corretti!");	


}
catch(NullPointerException e)
{
if(con==null)
JOptionPane.showMessageDialog(null,"Connessione al database assente!");
else
JOptionPane.showMessageDialog(null, "Mancato inserimento dati!");
}
		
	}
		});
		
		
		exec_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				try {
					
					Statement test = con.createStatement();
					if (choice_query.getSelectedItem().equals("1")) {
						ResultSet Result;
						ArrayList<String> Options=new ArrayList<String>();
						StringBuilder builder=new StringBuilder("<html>");
						Result=test.executeQuery("Select * from tratta");
						while(Result.next())
						{
							String Tratta = Result.getString("NomeTratta");
							Options.add(Tratta);
							
						}for(int i=0;i<Options.size();i++) {
						builder.append(Options.get(i));
						builder.append("<br>");}
						builder.append("</html>");
						JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
					
					}
					if (choice_query.getSelectedItem().equals("2")) {
						ResultSet Result;
						ArrayList<String> Options=new ArrayList<String>();
						StringBuilder builder=new StringBuilder("<html>");
						Result=test.executeQuery("Select * from sanzioni");
						while(Result.next())
						{
							String Sanzione = Result.getString("Codice");
							Options.add(Sanzione);
						}for(int i=0;i<Options.size();i++) {
							builder.append(Options.get(i));
							builder.append("<br>");}
							builder.append("</html>");
							JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
					}
					if (choice_query.getSelectedItem().equals("3")) {
						ResultSet Result;
						ArrayList<String> Options=new ArrayList<String>();
						StringBuilder builder=new StringBuilder("<html>");
						Result=test.executeQuery("Select * from tratta join oraripartenza where OrarioP='13:00:00' group by NomeTratta");
						while(Result.next())
						{
							String Tratta = Result.getString("NomeTratta");
							Options.add(Tratta);
						}for(int i=0;i<Options.size();i++) {
							builder.append(Options.get(i));
							builder.append("<br>");}
							builder.append("</html>");
							JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
					}
					if (choice_query.getSelectedItem().equals("4")) {
						ResultSet Result;
						ArrayList<Integer> Options=new ArrayList<Integer>();
						StringBuilder builder=new StringBuilder("<html>");
						Result=test.executeQuery("Select Count( *) from sanzioni join tratta on sanzioni.tratta=tratta.NomeTratta join autobus on tratta.NumeroAutobus=autobus.NumeroAutobus where autobus.NumeroAutobus=2");
						while(Result.next())
						{
							int Numero = Result.getInt(1);
							Options.add(Numero);
						}for(int i=0;i<Options.size();i++) {
							builder.append(Options.get(i));
							builder.append("<br>");}
							builder.append("</html>");
							JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
					}
					if (choice_query.getSelectedItem().equals("5")) {
						ResultSet Result;
						ArrayList<Integer> Options=new ArrayList<Integer>();
						StringBuilder builder=new StringBuilder("<html>");
						
						test.executeUpdate("CREATE VIEW MaxSanzioni(codice,numsanzioni) AS SELECT controllore.Codice,COUNT(*) as numsanzioni FROM controllore join sanzioni ON controllore.Codice=sanzioni.Controllore GROUP BY controllore.Codice");
						Result=test.executeQuery("Select MaxSanzioni.codice,MaxSanzioni.numsanzioni from MaxSanzioni where numsanzioni= (Select MAX(MaxSanzioni.numsanzioni) from MaxSanzioni)");
						while(Result.next())
						{
							int Codice = Result.getInt(1);
							Options.add(Codice);
						}for(int i=0;i<Options.size();i++) {
							builder.append(Options.get(i));
							builder.append("<br>");}
							builder.append("</html>");
							JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
						test.executeUpdate("DROP VIEW MaxSanzioni");
					}
					if (choice_query.getSelectedItem().equals("6")) {
						ResultSet Result;
						ArrayList<Integer> Options=new ArrayList<Integer>();
						StringBuilder builder=new StringBuilder("<html>");
						Result=test.executeQuery("SELECT controllore.Codice from controllore where NOT EXISTS (Select sanzioni.Codice FROM sanzioni WHERE sanzioni.Controllore=controllore.Codice)");
						while(Result.next())
						{
							int Codice = Result.getInt(1);
							Options.add(Codice);
						}for(int i=0;i<Options.size();i++) {
							builder.append(Options.get(i));
							builder.append("<br>");}
							builder.append("</html>");
							JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
					}
					if (choice_query.getSelectedItem().equals("7")) {
						ResultSet Result;
						ArrayList<Integer> Options=new ArrayList<Integer>();
						StringBuilder builder=new StringBuilder("<html>");
						Result=test.executeQuery("Select autobus.NumeroAutobus from autobus join turno on autobus.NumeroAutobus=turno.Autobus having Count(*)>3");
						while(Result.next())
						{
							int Codice = Result.getInt(1);
							Options.add(Codice);
						}for(int i=0;i<Options.size();i++) {
							builder.append(Options.get(i));
							builder.append("<br>");}
							builder.append("</html>");
							JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
					}if (choice_query.getSelectedItem().equals("8")) {
						ResultSet Result;
						ArrayList<String> Options=new ArrayList<String>();
						StringBuilder builder=new StringBuilder("<html>");
						String Tratta=JOptionPane.showInputDialog("Di che tratta si desiderano sapere gli orari di partenza?");
						String Query="SELECT OrarioP FROM `oraripartenza` WHERE Tratta='"+Tratta+"'";
						Result=test.executeQuery(Query);
						while(Result.next())
						{
							String Orari = Result.getString("OrarioP");
							Options.add(Orari);
						}for(int i=0;i<Options.size();i++) {
							builder.append(Options.get(i));
							builder.append("<br>");}
							builder.append("</html>");
							JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
					}if (choice_query.getSelectedItem().equals("9")) {
						ResultSet Result;
						ArrayList<String> Options=new ArrayList<String>();
						StringBuilder builder=new StringBuilder("<html>");
						String Tratta=JOptionPane.showInputDialog("Di che tratta si desiderano sapere gli orari di arrivo?");
						String Query="SELECT OrarioA FROM `orariarrivo` WHERE Tratta='"+Tratta+"'";
						Result=test.executeQuery(Query);
						while(Result.next())
						{
							String Orari = Result.getString("OrarioA");
							Options.add(Orari);
						}for(int i=0;i<Options.size();i++) {
							builder.append(Options.get(i));
							builder.append("<br>");}
							builder.append("</html>");
							JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
					}if (choice_query.getSelectedItem().equals("10")) {
						ResultSet Result;
						ArrayList<String> Options=new ArrayList<String>();
						StringBuilder builder=new StringBuilder("<html>");
						String Iniziale=JOptionPane.showInputDialog("Inserire lettera iniziale del cognome degli autisti da cercare");
						String Query="SELECT CodiceFiscale FROM `autista` WHERE Cognome like'"+Iniziale+"%'";
						Result=test.executeQuery(Query);
						while(Result.next())
						{
							String CodiceFiscale = Result.getString("CodiceFiscale");
							Options.add(CodiceFiscale);
						}for(int i=0;i<Options.size();i++) {
							builder.append(Options.get(i));
							builder.append("<br>");}
							builder.append("</html>");
							JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
					}if (choice_query.getSelectedItem().equals("11")) {
						ResultSet Result;
						ArrayList<String> Options=new ArrayList<String>();
						StringBuilder builder=new StringBuilder("<html>");
						
						String Query="Select turno.Autista FROM turno WHERE turno.Autobus=2 AND turno.Data='2017-12-12'AND turno.Autista NOT IN (SELECT turno.Autista FROM turno WHERE turno.Autobus=4 AND turno.Data='2017-12-12')";
						Result=test.executeQuery(Query);
						while(Result.next())
						{
							String Autista = Result.getString("Autista");
							Options.add(Autista);
						}for(int i=0;i<Options.size();i++) {
							builder.append(Options.get(i));
							builder.append("<br>");}
							builder.append("</html>");
							JOptionPane.showMessageDialog(null, builder.toString(),"Risultati",JOptionPane.INFORMATION_MESSAGE);
					}


				}catch (SQLException e) {

					JOptionPane.showMessageDialog(null,"Dati inseriti non corretti!");	
				

			}
			catch(NullPointerException e)
			{
				if(con==null)
				JOptionPane.showMessageDialog(null,"Connessione al database assente!");
				else
				JOptionPane.showMessageDialog(null, "Mancato inserimento dati!");
			}

			}
		});
	}}