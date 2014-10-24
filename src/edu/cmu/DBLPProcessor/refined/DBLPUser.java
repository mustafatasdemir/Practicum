package edu.cmu.DBLPProcessor.refined;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

// Refer to not refined package for deleted methods

@XmlRootElement(name="dblpuser")
public class DBLPUser {

	public void setId(int id) {
		this.id = id;
	}

	private static int count;
	private int id;
	private String name;
	private String email;
	private String title;
	private String affiliation;
	private String phoneNumber;
	private String faxNumber;
	private String mailingAddress;
	private List<String> researchInterests;
	private List<String> tags;
	private String hightestDegree;
	//private List<Publication> publication;
	//private List<Long> coauthorship;
	//private List<Coauthorship> coAuthors = new ArrayList<Coauthorship>();

	public DBLPUser() {
		super();
		id = ++count;
		name = null;
	}

	@XmlElement(name="id")
	public int getId() {
		return id;
	}

	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private void writeXMLCoauthorship(Coauthorship coauthorship, String filename) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(Coauthorship.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		File file = new File(filename);
		m.marshal(coauthorship, file);
	}
}
