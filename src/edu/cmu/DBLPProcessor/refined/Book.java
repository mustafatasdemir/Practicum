/**
 * 
 */
package edu.cmu.DBLPProcessor.refined;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author NASA-Trust-Team
 *
 */
@XmlRootElement(name="book")
public class Book extends Publication
{
	//private static int count;
	//private int id;
	//private int cited;
	//private List<String> author;
	private List<String> editors;
	//private String title;
	//private String booktitle;
	private String pages;
	//private String year;
	private String publisherAddress;
	//private String journal;
//private String volume;
	//private String number;
	private String month;
	private String url;
	//private String ee;
	//private String cdrom;
	//private List<String> cite;
	private String publisher;
	//private String note;
	//private List<String> crossref;
	private String isbn;
	private String series;
	//private String school;
	//private String chapter;
	//private String mdate;
	//private String key;
	//private String reviewid;
	//private String rating;
	//private List<String> field;
	
}
