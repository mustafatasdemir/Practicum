package edu.cmu.database;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.cmu.dblp.model.Authors;

public class DBTest {

	public DBTest() {
		super();
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IntrospectionException
	{
		DBConnection dBConnection = new MySQLConnection();
		List<String> explicitColumnNames = new ArrayList<String>();
		//explicitColumnNames.add("authorName");
		//explicitColumnNames.add("authorId");
		explicitColumnNames = null;
		
		List<Authors> authorsList = new ArrayList<Authors>();
		Authors authorInsert1 = new Authors();
		authorInsert1.setAuthorId(4);
		authorInsert1.setAuthorName("Krutika");
		authorInsert1.setInstitution("CMU");
		authorsList.add(authorInsert1);
		Authors authorInsert2 = new Authors();
		authorInsert2.setAuthorId(5);
		authorInsert2.setAuthorName("Abhishek");
		authorInsert2.setInstitution("CMU");
		authorsList.add(authorInsert2);
		
		DBInsertQueries<Authors> dBInsertQueries = new DBInsertQueries<Authors>(Authors.class, dBConnection, explicitColumnNames);
		dBInsertQueries.insertItems(authorsList);
		
		List<Authors> authors = new DBSelectQueries<Authors>(Authors.class, dBConnection, explicitColumnNames, "WHERE authorId = 1").getResults();
		
		for(Authors author:authors)
		{
			System.out.println("ID: " + author.getAuthorId());
			System.out.println("Name: " + author.getAuthorName());
			System.out.println("Institution: " + author.getInstitution());
			System.out.println("==============");
		}
	}

}
