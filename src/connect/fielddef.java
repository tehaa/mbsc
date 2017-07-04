

package connect;

import static connect.Main_page.srcConn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class fielddef {
    public static Connection srcConn;
    public static Connection dstConn;
    private String appName;
    private String t24Name;
    private String orclName;
    private String dictName;
    private String updateFreq;
    private String exportCondition;
    private String currField;

    private int localref_loc;
    private int datetime_loc;
    private int curr_no_loc;
 
  public String getUpdateFreq()
  {
    return this.updateFreq;
  }

  public void setUpdateFreq(String paramString)
  {
    this.updateFreq = paramString;
  }    
  public   void setAppName(String paramString)
  {
    this.appName = paramString;

 
  }
     private boolean appExists() {
        boolean bool = false;
        try {
            dstConn = Main_page.dstConn;
            Statement localStatement = this.dstConn.createStatement();
            ResultSet localResultSet = localStatement.executeQuery("select orcl_name,dict_name,t24_name,localref_loc,datetime_loc,curr_no_loc from import_list  where app_name='" + this.appName + "'");
            if (localResultSet.next()) {
                this.orclName = localResultSet.getString("orcl_name");
                this.dictName = localResultSet.getString("dict_name");
                this.t24Name = localResultSet.getString("t24_name");
                this.localref_loc = localResultSet.getInt("localref_loc");
                this.datetime_loc = localResultSet.getInt("datetime_loc");
                this.curr_no_loc = localResultSet.getInt("curr_no_loc");
                bool = true;

            } else {
                bool = false;
            }
        } catch (SQLException localSQLException) {
            System.out.println(localSQLException);
        }
        return bool;
    }
       
  public void processApplicationFields()
  {
    if (!appExists())
    {   
      getAppNames();
      fillDefForTable();
     
    }
  }
    private void getAppNames() {
        try {

            srcConn = Main_page.srcConn;
            Statement localStatement = this.srcConn.createStatement();
            String x = "select v1.classification as cType from  " + Main_page.schema + "F_FILE_CONTROL ss, xmltable('row/c6' passing SS.XMLRECORD columns classification varchar(3) path '.' )v1 where recid='" + this.appName + "' ";

            ResultSet localResultSet = localStatement.executeQuery(x);
            String str;

            if (localResultSet.next()) {
                str = localResultSet.getString(1);
                if ("INT".equals(str)) {
                    this.t24Name = ("F." + this.appName);
                } else {
                    this.t24Name = ("FBNK." + this.appName);
                }
                localResultSet.close();
            }
            localResultSet = localStatement.executeQuery("select * from " + Main_page.schema + "TAFJ_VOC where RECID = '" + this.t24Name + "' ");
            if (localResultSet.next()) {
                this.orclName = localResultSet.getString("ORCLFILENAME");
                this.dictName = localResultSet.getString("ORCLDICTNAME");
                localResultSet.close();
            }
            localResultSet = localStatement.executeQuery("select * from " + Main_page.schema + this.dictName + " where RECID in ('LOCAL.REF','DATE.TIME','CURR.NO')");
            while (localResultSet.next()) {
                str = localResultSet.getString("RECID");
                if ("LOCAL.REF".equals(str)) {
                    this.localref_loc = Integer.parseInt(localResultSet.getString("XMLRECORD").split("")[1]);
                } else if ("DATE.TIME".equals(str)) {
                    this.datetime_loc = Integer.parseInt(localResultSet.getString("XMLRECORD").split("")[1]);
                } else if ("CURR.NO".equals(str)) {
                    this.curr_no_loc = Integer.parseInt(localResultSet.getString("XMLRECORD").split("")[1]);
                }
            }
            localResultSet.close();
            localStatement.close();

            PreparedStatement localPreparedStatement = this.dstConn.prepareStatement("insert into import_list(t24_name,orcl_name,dict_name,app_name,import_freq,localref_loc,datetime_loc,curr_no_loc,export_condition) values (?,?,?,?,?,?,?,?,?)");
            localPreparedStatement.setString(1, this.t24Name);
            localPreparedStatement.setString(2, this.orclName);
            localPreparedStatement.setString(3, this.dictName);
            localPreparedStatement.setString(4, this.appName);
            localPreparedStatement.setString(5, this.updateFreq);
            localPreparedStatement.setInt(6, this.localref_loc);
            localPreparedStatement.setInt(7, this.datetime_loc);
            localPreparedStatement.setInt(8, this.curr_no_loc);
            localPreparedStatement.setString(9, this.exportCondition);
            localPreparedStatement.executeUpdate();
        } catch (SQLException localSQLException) {
            System.out.println(localSQLException);
        }
    }
    private void fillDefForTable() {
        try {

            Statement localStatement = this.srcConn.createStatement();
            ResultSet localResultSet = localStatement.executeQuery("select * from " + Main_page.schema + this.dictName + " Where xmlrecord not like 'PH%'");

            int i = 1;
            PreparedStatement localPreparedStatement = this.dstConn.prepareStatement("insert into import_fields_list2 (t24_name,col_order,t24_field_name,report_field_name,field_type,field_location,field_multi_location,field_sub_location,field_size,field_group,is_primary,is_id,is_multi,is_sub)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            while (localResultSet.next()) {
                String[] arrayOfString = localResultSet.getString("XMLRECORD").split("");
                this.currField = localResultSet.getString("RECID");
                if ("D".equals(arrayOfString[0])) {
                    localPreparedStatement.setString(1, this.appName);
                    localPreparedStatement.setInt(2, i);
                    localPreparedStatement.setString(3, localResultSet.getString("RECID"));
                    localPreparedStatement.setString(4, localResultSet.getString("RECID").toLowerCase().replaceAll("[.]", "_").replaceAll("[@]", ""));
                    localPreparedStatement.setString(5, "D");
                    localPreparedStatement.setInt(6, Integer.parseInt(arrayOfString[1].replace("CATEGORY.CODE", "0000")));
                    localPreparedStatement.setInt(9, Integer.parseInt(arrayOfString[4].replaceAll("R", "").replaceAll("L", "")));
                    if ("@ID".equals(localResultSet.getString("RECID"))) {
                        localPreparedStatement.setBoolean(11, true);
                    } else {
                        localPreparedStatement.setBoolean(11, false);
                    }
                    localPreparedStatement.setBoolean(12, false);
                    if ("S".equals(arrayOfString[5])) {
                        localPreparedStatement.setNull(10, 12);
                        localPreparedStatement.setNull(7, 4);
                        localPreparedStatement.setNull(8, 4);
                        localPreparedStatement.setBoolean(13, false);
                        localPreparedStatement.setBoolean(14, false);
                    } else if (("M".equals(arrayOfString[5])) && (arrayOfString.length < 35)) {
                        localPreparedStatement.setString(10, "");
                        localPreparedStatement.setNull(7, 4);
                        localPreparedStatement.setNull(8, 4);
                        localPreparedStatement.setBoolean(13, true);
                        localPreparedStatement.setBoolean(14, false);
                    } else if (("M".equals(arrayOfString[5])) && (arrayOfString[35].isEmpty())) {
                        localPreparedStatement.setString(10, arrayOfString[34]);
                        localPreparedStatement.setNull(7, 4);
                        localPreparedStatement.setNull(8, 4);
                        localPreparedStatement.setBoolean(13, true);
                        localPreparedStatement.setBoolean(14, false);
                    } else {
                        localPreparedStatement.setString(10, arrayOfString[34]);
                        localPreparedStatement.setNull(7, 4);
                        localPreparedStatement.setNull(8, 4);
                        localPreparedStatement.setBoolean(13, true);
                        localPreparedStatement.setBoolean(14, true);
                    }
                    localPreparedStatement.addBatch();
                    i++;
                } else if ("I".equals(arrayOfString[0])) {
                    if (arrayOfString[1].startsWith("LOCAL.REF<1,")) {
                        localPreparedStatement.setString(1, this.appName);
                        localPreparedStatement.setInt(2, i);
                        localPreparedStatement.setString(3, localResultSet.getString("RECID"));
                        localPreparedStatement.setString(4, localResultSet.getString("RECID").toLowerCase().replaceAll("[.]", "_"));
                        localPreparedStatement.setString(5, "I");
                        localPreparedStatement.setInt(6, this.localref_loc);
                        localPreparedStatement.setInt(7, Integer.parseInt(arrayOfString[1].replaceAll("LOCAL.REF<1,", "").replaceAll(">", "")));
                        localPreparedStatement.setNull(8, 4);
                        localPreparedStatement.setInt(9, Integer.parseInt(arrayOfString[4].replaceAll("R", "").replaceAll("L", "").replace(" ", "")));
                        localPreparedStatement.setNull(10, 12);
                        if ("@ID".equals(localResultSet.getString("RECID"))) {
                            localPreparedStatement.setBoolean(11, true);
                        } else {
                            localPreparedStatement.setBoolean(11, false);
                        }
                        localPreparedStatement.setBoolean(12, false);
                        localPreparedStatement.setBoolean(13, false);
                        localPreparedStatement.setBoolean(14, false);
                        localPreparedStatement.addBatch();
                        i++;
                    } else if ("@ID".equals(arrayOfString[1])) {
                        localPreparedStatement.setString(1, this.appName);
                        localPreparedStatement.setInt(2, i);
                        localPreparedStatement.setString(3, localResultSet.getString("RECID"));
                        localPreparedStatement.setString(4, localResultSet.getString("RECID").toLowerCase().replaceAll("[.]", "_"));
                        localPreparedStatement.setString(5, "I");
                        localPreparedStatement.setNull(6, 4);
                        localPreparedStatement.setNull(7, 4);
                        localPreparedStatement.setNull(8, 4);
                        localPreparedStatement.setInt(9, Integer.parseInt(arrayOfString[4].replaceAll("R", "").replaceAll("L", "")));
                        localPreparedStatement.setNull(10, 12);
                        localPreparedStatement.setBoolean(11, false);
                        localPreparedStatement.setBoolean(12, true);
                        localPreparedStatement.setBoolean(13, false);
                        localPreparedStatement.setBoolean(14, false);
                        localPreparedStatement.addBatch();
                        i++;
                    }
                }
            }

            localPreparedStatement.executeBatch();
        } catch (SQLException localSQLException) {
            System.out.println(localSQLException);
        } catch (Exception localException) {
            System.out.println(localException);
        }
  }
}
