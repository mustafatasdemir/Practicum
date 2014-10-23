package edu.cmu.database;

import java.sql.*;
import java.sql.SQLException;
import java.util.*;
import org.json.simple.*;

public class TempDBQueries {
	
	public TempDBQueries()
	{}
	
	public JSONObject findPublicationsOfAuthor(String authorName) throws ClassNotFoundException, SQLException
	{
		JSONObject returnJsonObject = new JSONObject();
		
		int authorId = -1;
		DbOperations databaseAccess = new DbOperations();
		String sqlQuery = "SELECT authorId FROM authors WHERE authorName = " + authorName;
		
		ResultSet result = databaseAccess.callDatabaseQuery(sqlQuery);
		while(result.next())
		{
			authorId = Integer.parseInt(result.getString("authorId"));
		}
		
		if(authorId != -1)
		{
			sqlQuery = "SELECT publicationId, publicationTitle FROM publications"
					+ " WHERE publicationId IN (SELECT publicationId FROM authorpublicationmap"
					+ " WHERE authorId = " + authorId + ")";
			result = databaseAccess.callDatabaseQuery(sqlQuery);
			JSONArray publicationsArray = new JSONArray();
			JSONArray edgesArray = new JSONArray();
			int counter = 0;
			
			while(result.next())
			{
				JSONObject publicationObject = new JSONObject();
				publicationObject.put("name", String.valueOf(++counter));
				publicationObject.put("artist", result.getString("publicationTitle"));
				publicationObject.put("id", result.getString("publicationId"));
				publicationObject.put("playcount", 1);
				publicationsArray.add(publicationObject);
				
				JSONObject edgeObject = new JSONObject();
				edgeObject.put("source", authorId);
				edgeObject.put("target", result.getString("publicationId"));
				edgesArray.add(edgeObject);
			}
			returnJsonObject.put("", publicationsArray);
			returnJsonObject.put("", edgesArray);
		
		}
		return returnJsonObject;
	}

}
