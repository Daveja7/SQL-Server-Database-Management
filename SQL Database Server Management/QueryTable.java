package project3;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

public class QueryTable extends AbstractTableModel
{
   // Track data connection 
   private static final long serialVersionUID = 1L;
   private Connection connection;
   private Statement statement;
   private ResultSet resultSet;
   private ResultSetMetaData metaData;
   private int numberOfRows;
   private boolean connectedToDatabase = false;

   // Finds the number of rows in data to be retrieved..

   public QueryTable( String driver, String url,
                      String username, String password, String query)
           throws SQLException, ClassNotFoundException
   {
      // Database driver class
      Class.forName( driver );

      // Connect to the database
      connection = DriverManager.getConnection( url, username, password );

      // Query Statement creation
      statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
              ResultSet.CONCUR_READ_ONLY );

      // Update connection status.
      connectedToDatabase = true;
   }

   public int getRowCount() throws IllegalStateException
   {
      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      return numberOfRows;
   }

   public int getColumnCount() throws IllegalStateException
   {
      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      try
      {
         return metaData.getColumnCount();
      }

      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      }

      return 0;
   }

   public String getColumnName( int column ) throws IllegalStateException
   {
      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      try
      {
         return metaData.getColumnName( column + 1 );
      }

      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      }

      return "";
   }

   public Class<?> getColumnClass( int column ) throws IllegalStateException
   {
      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      try
      {
         String className = metaData.getColumnClassName( column + 1 );

         return Class.forName( className );
      }

      catch ( Exception exception )
      {
         exception.printStackTrace();
      }

      return Object.class;
   }

   public Object getValueAt( int row, int column ) throws IllegalStateException
   {

      if ( !connectedToDatabase )

         throw new IllegalStateException( "Not Connected to Database" );

      try
      {
         resultSet.next();
         resultSet.absolute( row + 1 );
         return resultSet.getObject( column + 1 );
      }
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      }

      return "";
   }

   public void setQuery( String query ) throws SQLException {

      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      if (query.contains("delete") || query.contains("insert"))

         statement.executeUpdate(query);

      else resultSet = statement.executeQuery(query);

      metaData = resultSet.getMetaData();

      resultSet.last();
      numberOfRows = resultSet.getRow();

      fireTableStructureChanged();
   }



   public void setUpdate( String query ) throws SQLException {

      if ( !connectedToDatabase )

         throw new IllegalStateException( "Not Connected to Database" );

      statement.executeUpdate( query );

      fireTableStructureChanged();
   }
} 


