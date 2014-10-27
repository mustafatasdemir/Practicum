package edu.cmu.database;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;
import java.io.*;

import edu.cmu.dblp.model.*;

public class DBInserts {
	/*
	 * This method inserts the data into database tables on the basis of the Tag.
	 */


	public static DBConnection dBConnection = new MySQLConnection();
	public static List < String > explicitColumnNames = new ArrayList < String > ();
	//	public static explicitColumnNames = null;




	public static void DBInserts(String tag) throws Exception {



		Publication publicationInsert = new Publication();
		List < Publication > publicationList = new ArrayList < Publication > ();
		/*--------------Insert into master table publication starts here ----------------------*/
		publicationInsert.setPublicationChannel("channel");
		publicationInsert.setCitationCount(10);
		List < String > authors = new ArrayList(); //Temp List to hold the author names
		authors.add("E. F. Codd");
		authors.add("C. J. Date");
		publicationInsert.setAuthorNames(authors);
		publicationInsert.setPublicationTitle("Interactive Support for Non-Programmers: The Relational and Network Approaches.");
		publicationInsert.setYear("2002");
		publicationInsert.setUrl("");
		publicationInsert.setPublisher("");
		publicationInsert.setNote("");
		publicationInsert.setKeywords("persons/CoddD74");
		
		//publication.add(obj)//Jisha:the input obj
		publicationList.add(publicationInsert);

		DBInsertQueries < Publication > dBInsertQueries = new DBInsertQueries < Publication > (Publication.class, dBConnection, explicitColumnNames);
		publicationList = dBInsertQueries.insertItems(publicationList);

		int publicationId = publicationList.get(0).getPublicationId();

		System.out.println(publicationList.get(0).getPublicationId());
		/*-------------Insert into master table publication ends here ----------------------------*/



		for (int i = 0; i < authors.size(); i++) {
			List < Author > author = new DBSelectQueries < Author > (Author.class, dBConnection, explicitColumnNames, "authorName=" + authors.get(i)).getResults();
			int authorId = 0;
			if (author.isEmpty()) {
				/*-------Insert into author table starts here -----------*/
				Author authorInsert = new Author();
				List < Author > authorList = new ArrayList < Author > ();

				authorInsert.setAuthorName(authors.get(i));
				authorInsert.setInstitution("institution");

				authorList.add(authorInsert);


				DBInsertQueries < Author > authorInsertQueries = new DBInsertQueries < Author > (Author.class, dBConnection, explicitColumnNames);
				authorList = authorInsertQueries.insertItems(authorList);

				authorId = authorList.get(0).getAuthorId();
				/*-------Insert into author table ends here-----------*/
			} else {
				authorId = author.get(0).getAuthorId();
			}

			/*-----Insert into map table starts here--------*/



			AuthorPublicationMap map = new AuthorPublicationMap();
			List < AuthorPublicationMap > mapList = new ArrayList < AuthorPublicationMap > ();
			map.setAuthorId(authorId);
			map.setPublicationId(publicationId);




			mapList.add(map);

			DBInsertQueries < AuthorPublicationMap > mapInsertQueries = new DBInsertQueries < AuthorPublicationMap > (AuthorPublicationMap.class, dBConnection, explicitColumnNames);
			mapList = mapInsertQueries.insertItems(mapList);

			/*-----Insert into map table ends here--------*/

		}




		if (tag.equals("Book")) {


			List < String > editors = new ArrayList < String > ();
			//Jisha:If your publication type is Book, call the below method with the parameters that you get from the XML
			insertBook(publicationId, editors, pages, month, url, publisher, isbn, series);

		} else if (tag.equals("BookChapter")) {
			insertBookChapter(bookChapterName, relevance, setPages, publicationId);

		} else if (tag.equals("ConferencePaper")) {
			insertConferencePaper(conferenceName, ConferenceLocation, relevance, publicationId, pages, url);


		} else if (tag.equals("JournalArticle")) {
			insertJournalArticle(journalName, relevance, publicationId, pages, voulme, columns, url);


		} else if (tag.equals("PhDThesis")) {
			insertPhDThesis(schoolName, location, relevance, publicationId, department, advisorName);


		} else if (tag.equals("WebPage")) {


			insertWebpage(publicationId, url, accessDate);

		}
	}

	public static void main(String args[]) throws Exception {
		DBInserts("Journal");
	}


	public static void insertBook(int publicationId, List < String > editors, String pages, String month, String url, String publisher, String isbn, String series) throws Exception {
		Book bookInsert = new Book();
		List < Book > bookList = new ArrayList < Book > ();
		bookInsert.setPublicationId(publicationId); //Publication Id that just got created


		bookInsert.setEditors(editors);
		bookInsert.setPages(pages);
		bookInsert.setMonth(month);
		bookInsert.setUrl(url);
		bookInsert.setPublisher(publisher);
		bookInsert.setIsbn(isbn);
		bookInsert.setSeries(series);
		bookList.add(bookInsert);

		DBInsertQueries < Book > bookInsertQueries = new DBInsertQueries < Book > (Book.class, dBConnection, explicitColumnNames);
		bookList = bookInsertQueries.insertItems(bookList);
	}


	public static void insertBookChapter(String BookChapterName, int relevance, String Pages, int publicationId) throws Exception {
		List < BookChapterData > bookChapterdata = new DBSelectQueries < BookChapterData > (BookChapterData.class, dBConnection, explicitColumnNames, "bookChapterName=" + BookChapterName).getResults();
		int bookChapterDataId = 0;

		if (bookChapterdata.isEmpty()) { //Checking if bookChapterName already exists in the table bookChapterName

			/*-----------Insert into BookChapterData starts here ------*/
			BookChapterData bookChapterData = new BookChapterData();
			List < BookChapterData > bookChapterDataList = new ArrayList < BookChapterData > ();

			bookChapterData.setBookChapterName(BookChapterName);
			bookChapterData.setRelevance(relevance);

			bookChapterDataList.add(bookChapterData);

			DBInsertQueries < BookChapterData > bcdInsertQueries = new DBInsertQueries < BookChapterData > (BookChapterData.class, dBConnection, explicitColumnNames);
			bookChapterDataList = bcdInsertQueries.insertItems(bookChapterDataList);

			bookChapterDataId = bookChapterDataList.get(0).getBookChapterData();
			/*-----------Insert into BookChapterData ends here ------*/
		} else {
			bookChapterDataId = bookChapterdata.get(0).getBookChapterData();
		}


		/*-----------Insert into BookChapter starts here ------*/
		BookChapter bookChapter = new BookChapter();
		List < BookChapter > bookChapterList = new ArrayList < BookChapter > ();
		bookChapter.setBookChapterDataId(bookChapterDataId);
		bookChapter.setPublicationId(publicationId);
		bookChapter.setPages(Pages);
		bookChapterList.add(bookChapter);

		DBInsertQueries < BookChapter > bcInsertQueries = new DBInsertQueries < BookChapter > (BookChapter.class, dBConnection, explicitColumnNames);
		bookChapterList = bcInsertQueries.insertItems(bookChapterList);
		/*-----------Insert into BookChapter ends here ------*/
	}



	public static void insertBookChapter(String conferenceName, String conferenceLocation, int relevance, int publicationId, String pages, String url) throws Exception {
		List < Conference > conferenceListInfo = new DBSelectQueries < Conference > (Conference.class, dBConnection, explicitColumnNames, "ConferenceName=" + conferenceName).getResults();
		int conferenceId = 0;

		if (conferenceListInfo.isEmpty()) { //Checking if conferenceName already exists in the table Conference

			/*-----------Insert into Conference starts here ------*/
			Conference conference = new Conference();
			List < Conference > conferenceList = new ArrayList < Conference > ();

			conference.setConferenceLocation(conferenceLocation);
			conference.setConferenceName(conferenceName);
			conference.setRelevance(relevance); //Setting the relevance score as 1 temporarily 

			conferenceList.add(conference);

			DBInsertQueries < Conference > conferenceInsertQueries = new DBInsertQueries < Conference > (Conference.class, dBConnection, explicitColumnNames);
			conferenceList = conferenceInsertQueries.insertItems(conferenceList);

			conferenceId = conferenceList.get(0).getConferenceId();
			/*-----------Insert into Conference ends here ------*/
		} else {
			conferenceId = conferenceListInfo.get(0).getConferenceId();
		}
		/*-----------Insert into ConferencePaper starts here ------*/
		ConferencePaper conferencePaper = new ConferencePaper();
		List < ConferencePaper > conferencePaperList = new ArrayList < ConferencePaper > ();

		conferencePaper.setPublicationId(publicationId);
		conferencePaper.setConferenceId(conferenceId);
		conferencePaper.setPages(pages);
		conferencePaper.setUrl(url);


		conferencePaperList.add(conferencePaper);

		DBInsertQueries < ConferencePaper > cpInsertQueries = new DBInsertQueries < ConferencePaper > (ConferencePaper.class, dBConnection, explicitColumnNames);
		conferencePaperList = cpInsertQueries.insertItems(conferencePaperList);
		/*-----------Insert into ConferencePaper ends here ------*/
	}

	public static void insertJournalArticle(String journalName, int relevance, int publicationId, String pages, String volume, String columns, String url) throws Exception {

		List < Journal > journalInfo = new DBSelectQueries < Journal > (Journal.class, dBConnection, explicitColumnNames, "journalName=" + journalName).getResults();
		int journalId = 0;

		if (journalInfo.isEmpty()) { //Checking if journal already exists in the table Conference

			/*-----------Insert into JOurnal starts here ------*/
			Journal journal = new Journal();
			List < Journal > journalList = new ArrayList < Journal > ();


			journal.setJournalName(journalName);
			journal.setRelevance(relevance); //Setting the relevance score as 1 temporarily 

			journalList.add(journal);

			DBInsertQueries < Journal > jInsertQueries = new DBInsertQueries < Journal > (Journal.class, dBConnection, explicitColumnNames);
			journalList = jInsertQueries.insertItems(journalList);

			journalId = journalList.get(0).getJournalId();
			/*-----------Insert into Conference ends here ------*/
		} else {
			journalId = journalInfo.get(0).getJournalId();
		}

		/*-----------Insert into JOurnalArticle starts here ------*/
		JournalArticle journalArticle = new JournalArticle();
		List < JournalArticle > journalArticleList = new ArrayList < JournalArticle > ();


		journalArticle.setJournalId(journalId);
		journalArticle.setPublicationId(publicationId);
		journalArticle.setPages(pages);
		journalArticle.setVolume(volume);
		journalArticle.setColumns(columns);
		journalArticle.setUrl(url);



		journalArticleList.add(journalArticle);

		DBInsertQueries < JournalArticle > jaInsertQueries = new DBInsertQueries < JournalArticle > (JournalArticle.class, dBConnection, explicitColumnNames);
		journalArticleList = jaInsertQueries.insertItems(journalArticleList);
		/*-----------Insert into JOurnalArticle ends here ------*/

	}


	public static void insertPhDThesis(String schoolName, String location, double relevance, int publicationId, String department, String advisorName) throws Exception {



		List < School > schoolInfo = new DBSelectQueries < School > (School.class, dBConnection, explicitColumnNames, "schoolName=" + schoolName).getResults();
		int schoolId = 0;

		if (schoolInfo.isEmpty()) { //Checking if school already exists in the table School

			/*-----------Insert into school starts here ------*/
			School school = new School();
			List < School > schoolList = new ArrayList < School > ();

			school.setSchoolName(schoolName);
			school.setSchoolLocation(location);
			school.setRelevance(relevance);

			schoolList.add(school);

			DBInsertQueries < School > InsertQueries = new DBInsertQueries < School > (School.class, dBConnection, explicitColumnNames);
			schoolList = InsertQueries.insertItems(schoolList);

			schoolId = schoolList.get(0).getSchoolId();
			/*-----------Insert into school ends here ------*/
		} else {
			schoolId = schoolInfo.get(0).getSchoolId();
		}

		/*-----------Insert into phDThesis starts here ------*/
		PhdThesis phdthesis = new PhdThesis();
		List < PhdThesis > phdthesisList = new ArrayList < PhdThesis > ();


		phdthesis.setSchoolId(schoolId);
		phdthesis.setPublicationId(publicationId);
		phdthesis.setDepartment(department);
		phdthesis.setAdvisorId(advisorName);




		phdthesisList.add(phdthesis);

		DBInsertQueries < PhdThesis > InsertQueries = new DBInsertQueries < PhdThesis > (PhdThesis.class, dBConnection, explicitColumnNames);
		phdthesisList = InsertQueries.insertItems(phdthesisList);
		/*-----------Insert into phDThesis ends here ------*/

	}

	public static void insertWebpage(int publicationId, String url, String accessDate) throws Exception {


		WebPage webpage = new WebPage();
		List < WebPage > webpagelist = new ArrayList < WebPage > ();


		webpage.setPublicationId(publicationId);
		webpage.setUrl(url);
		webpage.setAccessDate(accessDate);


		webpagelist.add(webpage);

		DBInsertQueries < WebPage > bookInsertQueries = new DBInsertQueries < WebPage > (WebPage.class, dBConnection, explicitColumnNames);
		webpagelist = bookInsertQueries.insertItems(webpagelist);

	}
}