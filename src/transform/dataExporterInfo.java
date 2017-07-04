package transform;

import connect.Main_page;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dataExporterInfo
{

  private Connection srcConn;
  private Connection dstConn;

  
 
    

    public exporterInfo[] getInfo() {
        srcConn=Main_page.srcConn;
        dstConn=Main_page.dstConn ;
        exporterInfo[] arrayOfexporterInfo = null;
        try {
            int i = 0;
            PreparedStatement localPreparedStatement = this.dstConn.prepareStatement("SELECT count(*) FROM import_list where last_update is null ");//or ((last_update=?)and(import_freq=\"DAILY\")) or ((last_update=?)and(import_freq=\"WEEKLY\")) or ((last_update=?)and(import_freq=\"MONTHLY\")) or ((last_update=?)and(import_freq=\"YEARLY\")) or ((run_import=1)and(import_freq=\"ONREQUEST\")))");
            /*
            Calendar localCalendar1 = Calendar.getInstance();
            localCalendar1.add(5, -1);
            Calendar localCalendar2 = Calendar.getInstance();
            localCalendar2.add(3, -1);
            Calendar localCalendar3 = Calendar.getInstance();
            localCalendar3.add(2, -1);
            Calendar localCalendar4 = Calendar.getInstance();
            localCalendar4.add(1, -1);
            localPreparedStatement.setString(1, String.format("%04d", new Object[]{Integer.valueOf(localCalendar1.get(1))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar1.get(2))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar1.get(5))}));
            localPreparedStatement.setString(2, String.format("%04d", new Object[]{Integer.valueOf(localCalendar2.get(1))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar2.get(2))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar2.get(5))}));
            localPreparedStatement.setString(3, String.format("%04d", new Object[]{Integer.valueOf(localCalendar3.get(1))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar3.get(2))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar3.get(5))}));
            localPreparedStatement.setString(4, String.format("%04d", new Object[]{Integer.valueOf(localCalendar4.get(1))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar4.get(2))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar4.get(5))}));
*/
            ResultSet localResultSet = localPreparedStatement.executeQuery();
            if (localResultSet.next()) {
                i = localResultSet.getInt(1);
            }
            localPreparedStatement = this.dstConn.prepareStatement("SELECT app_name,last_update,import_freq FROM import_list where last_update is null");// or ((last_update=?)and(import_freq=\"DAILY\")) or ((last_update=?)and(import_freq=\"WEEKLY\")) or ((last_update=?)and(import_freq=\"MONTHLY\")) or ((last_update=?)and(import_freq=\"YEARLY\")) or ((run_import=1)and(import_freq=\"ONREQUEST\")))");
            /*
            localPreparedStatement.setString(1, String.format("%04d", new Object[]{Integer.valueOf(localCalendar1.get(1))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar1.get(2))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar1.get(5))}));
            localPreparedStatement.setString(2, String.format("%04d", new Object[]{Integer.valueOf(localCalendar2.get(1))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar2.get(2))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar2.get(5))}));
            localPreparedStatement.setString(3, String.format("%04d", new Object[]{Integer.valueOf(localCalendar3.get(1))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar3.get(2))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar3.get(5))}));
            localPreparedStatement.setString(4, String.format("%04d", new Object[]{Integer.valueOf(localCalendar4.get(1))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar4.get(2))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar4.get(5))}));
                    */
            localResultSet = localPreparedStatement.executeQuery();
            int j = 0;
            arrayOfexporterInfo = new exporterInfo[i];
            while (localResultSet.next()) {
                arrayOfexporterInfo[j] = new exporterInfo();
                arrayOfexporterInfo[j].lastUpdate = localResultSet.getString("last_update");
                if (arrayOfexporterInfo[j].lastUpdate == null) {
                    arrayOfexporterInfo[j].lastUpdate = "";
                }
                arrayOfexporterInfo[j].updFreq = localResultSet.getString("import_freq");
                arrayOfexporterInfo[j].appName = localResultSet.getString("app_name");
                j++;
            }
            localResultSet.close();
            localPreparedStatement.close();
        } catch (SQLException localSQLException) {
            localSQLException.printStackTrace();

        }
        return arrayOfexporterInfo;
    }
}
