//IT Number - IT19127606
//Name - Warnasooriya P.B.
//Function - Researcher


package model;

import java.sql.*;

public class Researcher 
{
	// A common method to connect to the DB
			private Connection connect() {
				Connection con = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					// Provide the correct details: DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/researcher", "root", "");

					// For testing
					System.out.print("Successfully connected");

				} catch (Exception e) {
					e.printStackTrace();
				}

				return con;
			}
			
			public String readResearcher() {
				String output = "";

				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}

					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Researcher Name</th>"
							+ "<th>Researcher Email</th><th>Researcher Contact</th>" + "<th>Researcher Type</th>"
							+ "<th>Update</th><th>Remove</th></tr>";

					String query = "select * from researcher";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					// iterate through the rows in the result set
					while (rs.next()) {

						String ResearcherID = Integer.toString(rs.getInt("ResearcherID"));
						String ResearcherName = rs.getString("ResearcherName");
						String ResearcherEmail = rs.getString("ResearcherEmail");
						String ResearcherContact = Integer.toString(rs.getInt("ResearcherContact"));
						String ResearcherType = rs.getString("ResearcherType");

						// Add into the html table

						output += "<tr><td><input id='hidResearcherUpdate' name='hidResearcherUpdate' type='hidden' value='"
								+ ResearcherID + "'>" + ResearcherName + "</td>";
						output += "<td>" + ResearcherEmail + "</td>";
						output += "<td>" + ResearcherContact + "</td>";
						output += "<td>" + ResearcherType + "</td>";

						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-ResearcherID='"
								+ ResearcherID + "'>" + "</td></tr>";

					}

					con.close();

					// Complete the html table
					output += "</table>";
				} catch (Exception e) {
					output = "Error while reading the Researcher Details.";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
			// Insert Researcher
			public String insertResearcher(String ResearcherName, String ResearcherEmail, String ResearcherContact,
					String ResearcherType) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database";
					}

					// create a prepared statement
					String query = " insert into researcher ('ResearcherID','ResearcherName','ResearcherEmail','ResearcherContact','ResearcherType')"
							+ " values (?, ?, ?, ?, ?)";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, ResearcherName);
					preparedStmt.setString(3, ResearcherEmail);
					preparedStmt.setString(4, ResearcherContact);
					preparedStmt.setString(5, ResearcherType);

					// execute the statement
					preparedStmt.execute();
					con.close();

					// Create JSON Object to show successful msg.
					String newResearcher = readResearcher();
					output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";
				} catch (Exception e) {
					// Create JSON Object to show Error msg.
					output = "{\"status\":\"error\", \"data\": \"Error while Inserting Researcher.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
			// Update Researcher
			public String updateResearcher(String ResearcherID, String ResearcherName, String ResearcherEmail,
					String ResearcherContact, String ResearcherType) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for updating.";
					}

					// create a prepared statement
					String query = "UPDATE researcher SET ResearcherName=?,ResearcherEmail=?,ResearcherContact=?,ResearcherType=? WHERE ResearcherID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, ResearcherName);
					preparedStmt.setString(2, ResearcherEmail);
					preparedStmt.setInt(3, Integer.parseInt(ResearcherContact));
					preparedStmt.setString(4, ResearcherType);
					preparedStmt.setInt(5, Integer.parseInt(ResearcherID));

					// execute the statement
					preparedStmt.execute();
					con.close();

					// create JSON object to show successful msg
					String newResearcher = readResearcher();
					output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while Updating Researcher Details.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			public String deleteResearcher(String ResearcherID) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "DELETE FROM researcher WHERE ResearcherID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setInt(1, Integer.parseInt(ResearcherID));
					// execute the statement
					preparedStmt.execute();
					con.close();

					// create JSON Object
					String newResearcher = readResearcher();
					output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";
				} catch (Exception e) {
					// Create JSON object
					output = "{\"status\":\"error\", \"data\": \"Error while Deleting Researcher.\"}";
					System.err.println(e.getMessage());

				}

				return output;
			}

}
