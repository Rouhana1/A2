package A2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class addnewperson extends TransactionExecuter {
	
	//Initialize Input_Trans parameters
	int transactionId;
	int newReportNumber;
	String newPersonID;
	String newSexDescription;
	

	//Only one constructor needed since all values must be used
	public addnewperson(Connection conn, int transactionId, int newReportNumber, 
			String newPersonID, String newSexDescription) {
		this.conn = conn;
		this.transactionId = transactionId;
		this.newReportNumber = newReportNumber;
		this.newPersonID = newPersonID;
		this.newSexDescription = newSexDescription;
		
	}
	
	public void outputFailure(int transactionID, String message) throws SQLException {
			
		//IMPORTANT: The next line should be commented to answer a question.
		conn.rollback();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO Trans_Output (TransactionID, Message) values (?, ?)");
		ps.setInt(1, transactionID);
		ps.setString(2, message);
		ps.executeUpdate();	
		//IMPORTANT: The next line should be commented to answer a question.
		conn.commit();
 

}
	
	public void outputSuccess(int transactionID, String message) throws SQLException {
		
		//IMPORTANT: The next line should be commented to answer a question.
		conn.commit();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO Trans_Output (TransactionID, Message) values (?, ?)");
		ps.setInt(1, transactionID);
		ps.setString(2, message);
		ps.executeUpdate();		
		//IMPORTANT: The next line should be commented to answer a question.
		conn.commit();
 
}
	
	//Execute the transactions for each tuple in APAJSC_Trans
	public void execute() throws SQLException {	
		
	
				   
		
		//Insert a tuple in Person_E
		insertNewProject(newReportNumber, newPersonID, newSexDescription);
		
		
		
		
		// All the changes have been made in the database and we are committing them
		
		String outMsg = "Transaction succeeded.";
		outputSuccess(transactionId, outMsg);
		System.out.println(outMsg); 
		conn.commit();
	}

	
	
	
	//Method to insert a new project in Person_E
	private void insertNewProject(int newReportNumber, String newPersonID, String newSexDescription) throws SQLException {
		try {
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO Person_E (ReportNumber, PersonID, SexDescription) values (?, ?, ?);";	//Set query to execute
			PreparedStatement pstmt = conn.prepareStatement(query);			
			
			pstmt.setInt(1, newReportNumber);
			pstmt.setString(2, newPersonID);
			pstmt.setString(3, newSexDescription);
			pstmt.execute();				//Execute the query
			stmt.close();
			
		} catch (SQLException e) {
			if (e.getMessage().contentEquals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (UNIQUE constraint failed: Person_E.PersonNumber)")) {
				String outMsg = "PK violation. Unable to insert person " + newReportNumber + " into Person_E since the person number already exists in the table.";
				outputFailure(transactionId, outMsg);
				System.out.println(outMsg);
			    throw e;
			} else {
				String outMsg = "Unknown Exception.";
				outputFailure(transactionId, outMsg);
				throw e;
			}
		}
	}
	
	
	
	
	
	
	
		
				
	
}
