package edu.cmu.lab;
//
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import edu.cmu.DBLPProcessor.Coauthorship;
import edu.cmu.DBLPProcessor.DBLPUser;
import edu.cmu.DBLPProcessor.Publication;
import edu.cmu.dataset.DBLPDataSource;
import edu.cmu.dataset.DatasetInterface;

public class WriteXML {

//	/**
//	 * @author NASA-Trust-Team
//	 * @throws JAXBException 
//	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, JAXBException, SAXException, ParserConfigurationException {
		DatasetInterface dblpDataset = new DBLPDataSource();
		HashMap<String,DBLPUser> dblp = dblpDataset.getDataset("dblp_example.xml");
		Iterator<String> it = dblp.keySet().iterator();
		while (it.hasNext())
		{
			String key = it.next();
			DBLPUser author = dblp.get(key);
			for(Coauthorship c: author.getCoAuthors()) {
				System.out.print("\nUser ID :" + c.getUserid() + "	CoauthorID: " + c.getCoauthorid());
			}
			
			//System.out.println(author);
		}
				
	}

	
	/*
	 * Commented out by the previous team (2013)
	 * Modified according to current schema and printed the results
	 * 
	 * */
	public static void writeAuthor(DBLPUser author) throws IOException, JAXBException {
		
		FileWriter fileWriter = new FileWriter("DBLP_XML/user"+author.getId()+".xml");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println("<user>");
		printWriter.println("<id>"+author.getId()+"</id>");
		printWriter.println("<name>"+author.getName()+"</name>");
		printWriter.println("<publicationlist>");
		for(Publication publication : author.getPublication()){
			// publication type
			printWriter.println("<"+publication.getType()+"></" + publication.getType() + ">");
			// publication id
			printWriter.println("<id>" + publication.getId() + "</id>");
		}
		printWriter.println("</publicationlist>");
		printWriter.println("</user>");
		printWriter.flush();
		printWriter.close();
		fileWriter.close();
	}
}
