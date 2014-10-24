/**
 * 
 */
package edu.cmu.DBLPProcessor.refined;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author NASA-Trust-Team
 *
 */
@XmlRootElement(name="publication")
public class Publication 
{   
	private int publicationId;
	private String publicationChannel;
	private int citationCount;
	//private List<Integer> author = new ArrayList<Integer>();
	private List<String> authorNames = new ArrayList<String>();
	//private List<Integer> editor = new ArrayList<Integer>();
	//private List<String> editornames = new ArrayList<String>();
	private String publicationTitle;
	//private String booktitle;
	//private String pages;
	private String year;
	//private String address;
	//private String journal;
	//private String volume;
	//private String number;
	//private String month;
	private String url;
	//private String ee;
	//private String cdrom;
	//private List<String> cite = new ArrayList<String>();
	private String publisher;
	private String note;
	//private List<String> crossref = new ArrayList<String>();
	//private String isbn;
	//private String series;
	//private String school;
	//private String chapter;
	//private String mdate;
	private String keywords;
	//private String reviewid;
	//private String rating;
	//private List<String> field = new ArrayList<String>();
	private List<String> tags;
	
	public Publication() {
		super();
	}
	
}