package edu.cmu.dblp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import edu.cmu.dblp.constants.Publication;
import edu.cmu.dblp.model.JournalArticle;



public class Main {

	public static void main(String[] args) {
		readXML("/Users/ironstone/Desktop/dblp_example.xml");
		//		List<Item> readConfig = StaXParser.readConfig("/Users/ironstone/Desktop/dblp_example.xml");
		//	    for (Item item : readConfig) {
		//	      System.out.println(item);
		//	    }	
	}

	public static void readXML(String configFile) {
		try {
			// First, create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					// If we have an item element, we create a new item
					if (startElement.getName().getLocalPart() == (Publication.ARTICLE)) {
						JournalArticle article = new JournalArticle();
						// We read the attributes from this tag and add the date
						// attribute to our object
						Iterator<Attribute> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Publication.MDATE)) {
								continue;
							}
							if (attribute.getName().toString().equals(Publication.KEY)) {
								continue;
							}
						}
						while(eventReader.hasNext()){
							XMLEvent articleEvent = eventReader.nextEvent();
							if (articleEvent.isStartElement()) {
								StartElement articleStartElement = articleEvent.asStartElement();
								if (articleStartElement.getName().getLocalPart() == (Publication.AUTHOR)) {
									articleEvent = eventReader.nextEvent();
									article.getAuthorNames().add(articleEvent.asCharacters().getData());
									continue;
								}
							}
							if(articleEvent.isEndElement()){
								EndElement articleEndElement = articleEvent.asEndElement();
								if(articleEndElement.getName().getLocalPart().equals(Publication.ARTICLE)){
									System.out.println(article.getAuthorNames().size());
									break;
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
