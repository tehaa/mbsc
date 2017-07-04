package transform;

import connect.Main_page;
import connect.extractorGuiPanel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import oracle.xdb.XMLType;

public class dataExporter extends Thread {

    private String appName;
    private String t24Name;
    private String orclName;
    private String dictName;
    private String updFreq;
    private char sFM;
    private char sVM;
    private char sSM;

    private Connection srcConn;
    private Connection dstConn;
    private int datetime_loc;
    private int curr_no_loc;
    private int sub_count;
    private long recordCount;
    private long doneCount;
    private String sqlCondition;
    private String lastUpdate;
    private String ssubFields;
    private boolean isVisual;
    private JProgressBar pBar;
    private extractorGuiPanel panel;

    public dataExporter(String paramString, JProgressBar paramJProgressBar, extractorGuiPanel paramextractorGuiPanel) {
        this(paramString);
        this.isVisual = true;
        this.pBar = paramJProgressBar;
        this.panel = paramextractorGuiPanel;
    }

    public dataExporter(String paramString) {

        this.sFM = ',';
        this.sVM = '|';
        this.sSM = '$';
        this.appName = paramString;

        this.srcConn = Main_page.srcConn;
        this.dstConn = Main_page.dstConn;

    }

    private void setDefs(xmlToCsv paramxmlToCsv) {
        int i = 0;
        try {

            Statement localStatement = this.dstConn.createStatement();
            ResultSet localResultSet = localStatement.executeQuery("select count(0) as rcount from import_fields_list where t24_name='" + this.appName + "'");
            if (localResultSet.next()) {
                i = localResultSet.getInt("rcount");
            }
            localResultSet.close();
            fieldDefinition[] arrayOffieldDefinition = new fieldDefinition[i];
            localResultSet = localStatement.executeQuery("select report_field_name,field_location,field_multi_location,field_sub_location,is_id,is_multi,is_sub,is_primary from import_fields_list where t24_name='" + this.appName + "' order by field_location,field_multi_location,field_sub_location");
            int j = 0;
            this.ssubFields = "";
            while (localResultSet.next()) {
                arrayOffieldDefinition[j] = new fieldDefinition();
                arrayOffieldDefinition[j].setField_location(localResultSet.getInt("field_location"));
                arrayOffieldDefinition[j].setField_multi_location(localResultSet.getInt("field_multi_location"));
                arrayOffieldDefinition[j].setField_sub_location(localResultSet.getInt("field_sub_location"));
                arrayOffieldDefinition[j].setIs_id(localResultSet.getBoolean("is_id"));
                arrayOffieldDefinition[j].setIs_multi(localResultSet.getBoolean("is_multi"));
                arrayOffieldDefinition[j].setIs_sub(localResultSet.getBoolean("is_sub"));
                if ((arrayOffieldDefinition[j].isIs_multi()) || (arrayOffieldDefinition[j].isIs_sub())) {
                    this.ssubFields = (this.ssubFields + ",`" + localResultSet.getString("report_field_name") + "`");
                } else if (localResultSet.getBoolean("is_primary")) {
                    this.ssubFields = (",`" + localResultSet.getString("report_field_name") + "`" + this.ssubFields);
                }
                j++;
            }
            localResultSet.close();
            localStatement.close();
            paramxmlToCsv.setDefs(arrayOffieldDefinition);
        } catch (SQLException localSQLException) {
            localSQLException.printStackTrace();
            //   Logger.getLogger(fieldDefImport.class.getName()).log(Level.SEVERE, null, localSQLException);
        }
    }

    private void loadImportInformation() {
        try {
            Statement localStatement = this.dstConn.createStatement();
            ResultSet localResultSet = localStatement.executeQuery("select orcl_name,dict_name,t24_name,datetime_loc,curr_no_loc,export_condition,last_update,import_freq,sub_count from import_list  where app_name='" + this.appName + "'");
            if (localResultSet.next()) {
                this.orclName = localResultSet.getString("orcl_name");
                this.dictName = localResultSet.getString("dict_name");
                this.t24Name = localResultSet.getString("t24_name");
                this.datetime_loc = localResultSet.getInt("datetime_loc");
                this.curr_no_loc = localResultSet.getInt("curr_no_loc");
                this.sqlCondition = localResultSet.getString("export_condition");
                this.lastUpdate = localResultSet.getString("last_update");
                this.updFreq = localResultSet.getString("import_freq");
                this.sub_count = localResultSet.getInt("sub_count");
            }
            localResultSet.close();
            localStatement.close();
        } catch (SQLException localSQLException) {
            localSQLException.printStackTrace();
            // Logger.getLogger(fieldDefImport.class.getName()).log(Level.SEVERE, null, localSQLException);
        }
    }

    private void checkTable() {
        try {
            String str1 = this.appName.replaceAll("[.]", "_").toLowerCase();
            Statement localStatement = this.dstConn.createStatement();
            ResultSet localResultSet = localStatement.executeQuery("show tables like '" + str1 + "'");
            int i = 62;
            int j = 0;
            String str2 = "";
            String str3 = "";
            String str4 = "";
            if (localResultSet.next()) {
                return;
            }
            localResultSet.close();
            localResultSet = localStatement.executeQuery("select report_field_name,field_data_type,field_size,decimal_size,is_primary,is_multi,is_sub,is_index from import_fields_list where t24_name='" + this.appName + "' order by field_location,field_multi_location,field_sub_location");
            while (localResultSet.next()) {
                if (localResultSet.getBoolean("is_primary")) {
                    str2 = str2 + " `" + localResultSet.getString("report_field_name") + "` ";
                    if ("decimal".equals(localResultSet.getString("field_data_type"))) {
                        str2 = str2 + localResultSet.getString("field_data_type") + "(" + localResultSet.getString("field_size") + "," + localResultSet.getString("decimal_size") + ") NOT NULL, ";
                        str3 = str3 + " `" + localResultSet.getString("report_field_name") + "` " + localResultSet.getString("field_data_type") + "(" + localResultSet.getString("field_size") + "," + localResultSet.getString("decimal_size") + ") DEFAULT NULL, ";
                    } else {
                        str2 = str2 + localResultSet.getString("field_data_type") + "(" + localResultSet.getString("field_size") + ") NOT NULL, ";
                        str3 = str3 + " `" + localResultSet.getString("report_field_name") + "` " + localResultSet.getString("field_data_type") + "(" + localResultSet.getString("field_size") + ") DEFAULT NULL, ";
                    }
                    str4 = str4 + " PRIMARY KEY (`" + localResultSet.getString("report_field_name") + "`), UNIQUE KEY `" + localResultSet.getString("report_field_name") + "_UNIQUE` (`" + localResultSet.getString("report_field_name") + "`), ";
                } else if ((localResultSet.getBoolean("is_multi")) || (localResultSet.getBoolean("is_sub"))) {
                    str2 = str2 + " `" + localResultSet.getString("report_field_name") + "` TEXT DEFAULT NULL, ";
                    str3 = str3 + " `" + localResultSet.getString("report_field_name") + "` ";
                    j++;
                    if ("decimal".equals(localResultSet.getString("field_data_type"))) {
                        str3 = str3 + localResultSet.getString("field_data_type") + "(" + localResultSet.getString("field_size") + "," + localResultSet.getString("decimal_size") + ") DEFAULT NULL, ";
                    } else {
                        str3 = str3 + localResultSet.getString("field_data_type") + "(" + localResultSet.getString("field_size") + ") DEFAULT NULL, ";
                    }
                } else {
                    str2 = str2 + " `" + localResultSet.getString("report_field_name") + "` " + localResultSet.getString("field_data_type") + "(" + localResultSet.getString("field_size") + ") DEFAULT NULL, ";
                    if ((localResultSet.getBoolean("is_index")) && (i > 0)) {
                        i--;
                        str4 = str4 + " KEY `" + localResultSet.getString("report_field_name") + "_idx` (`" + localResultSet.getString("report_field_name") + "`), ";
                    }
                }
            }

        

            localStatement.executeUpdate("UPDATE `import_list` set sub_count=" + j + " where app_name='" + this.appName + "'");
            System.out.println("CREATE TABLE `" + str1 + "`(" + str2 + str4.substring(0, str4.length() - 2) + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED   ;");
            localStatement.executeUpdate("CREATE TABLE `" + str1 + "`(" + str2 + str4.substring(0, str4.length() - 2) + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED ;");
            //System.out.println("CREATE TABLE `" + str1 + "`(" + str2 + str4.substring(0, str4.length() - 2) + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;");
            localStatement.executeUpdate("CREATE TABLE `" + str1 + "_subs` (`multiId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,   `multi` int(11) DEFAULT NULL,  `sub` int(11) DEFAULT NULL," + str3 + " PRIMARY KEY (`multiId`), UNIQUE KEY `id_UNIQUE` (`multiId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;");
            localStatement.executeUpdate("CREATE TABLE `" + str1 + "_sync`(`key` varchar(50) NOT NULL, PRIMARY KEY (`key`), UNIQUE KEY `key_UNIQUE` (`key`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;");
            localResultSet.close();
            localStatement.close();
        } catch (SQLException localSQLException) {
            localSQLException.printStackTrace();
            //  Logger.getLogger(fieldDefImport.class.getName()).log(Level.SEVERE, null, localSQLException);
        }
    }

    public void run() {
        int size = 0;
        System.out.println(this.sFM);
        xmlToCsv localxmlToCsv = new xmlToCsv(this.sFM, this.sVM, this.sSM);
        if (this.isVisual) {
            this.panel.setStatus("Loading Application Definitions...");
        }
        setDefs(localxmlToCsv);
        checkTable();
        loadImportInformation();
        localxmlToCsv.setSubCount(this.sub_count);
        localxmlToCsv.setCurr_no_loc(this.curr_no_loc);

        String str1 = "";
        String str2 = "";

        str2 = "Select count(0) from " + connect.Main_page.schema + this.orclName + str1;
        str1 = "Select * from " + connect.Main_page.schema + this.orclName + str1;
        try {
            Statement localStatement = this.srcConn.createStatement();
            long x = 0;
            if (this.isVisual) {

                ResultSet localResultSet = localStatement.executeQuery(str2);

                if (localResultSet.next()) {
                    x = localResultSet.getLong(1);
                    this.recordCount = localResultSet.getLong(1);
                    this.panel.setGeneralLabels(this.appName, this.updFreq, this.lastUpdate);
                    this.panel.setRecordsCount(Long.toString(this.recordCount));
                    this.doneCount = 0L;
                    this.panel.setRecordsDone(Long.toString(this.doneCount));
                }
                localResultSet.close();
            }
       
            size = (int) ((x / 50000) + 1);
            System.out.println(size);
            BufferedWriter[] localBufferedWriter1 = new BufferedWriter[size];
            BufferedWriter[] localBufferedWriter2 = new BufferedWriter[size];
            BufferedWriter[] localBufferedWriter3 = new BufferedWriter[size];
            if (this.isVisual) {
                this.panel.setStatus("Exporting data...");
            }
            for (int i = 0; i < localBufferedWriter1.length; i++) {
                localBufferedWriter1[i] = new BufferedWriter(new FileWriter("C:\\Users\\Ahmed.ahmed\\Desktop\\cbe-reports\\cbe-reports\\exports" + "\\" + this.appName + i + "-export.csv"));
                localBufferedWriter2[i] = new BufferedWriter(new FileWriter("C:\\Users\\Ahmed.ahmed\\Desktop\\cbe-reports\\cbe-reports\\exports" + "\\" + this.appName + i + "-sync.csv"));
                localBufferedWriter3[i] = new BufferedWriter(new FileWriter("C:\\Users\\Ahmed.ahmed\\Desktop\\cbe-reports\\cbe-reports\\exports" + "\\" + this.appName + i + "-subs.csv"));
            }

            ResultSet localResultSet = localStatement.executeQuery(str1);
            int file = 0;
            int file_capacity = 50000;

            while (localResultSet.next()) {
                int file_number = localResultSet.getRow() / file_capacity;

                XMLType localXMLType = (XMLType) localResultSet.getObject("XMLRECORD");
                String str3 = localxmlToCsv.processXmlContent(localXMLType);
                String str4 = localxmlToCsv.getSublines();

                localBufferedWriter1[file_number].write(str3 + "\n");
                localBufferedWriter3[file_number].write(str4);
                if ((!"".equals(this.lastUpdate)) && (localxmlToCsv.requiresSync())) {
                    localBufferedWriter2[file_number].write(localResultSet.getString("RECID") + "\n");
                }
                if (this.isVisual) {
                    this.doneCount += 1L;
                    this.pBar.setValue(Math.round((float) (this.doneCount * 100L / this.recordCount)));
                    this.panel.setRecordsDone(Long.toString(this.doneCount));
                }
            }

            for (int i = 0; i < localBufferedWriter1.length; i++) {
                localResultSet.close();
                localStatement.close();
                localBufferedWriter1[i].close();
                localBufferedWriter2[i].close();
                localBufferedWriter3[i].close();
            }

            for (int i = 0; i < size; i++) {

                String str5 = this.appName.replaceAll("[.]", "_").toLowerCase();
                if (this.isVisual) {
                    this.panel.setStatus("Uploading extracted data...");
                }
                localStatement = this.dstConn.createStatement();
                String sync = "LOAD DATA LOCAL INFILE '" + "C:/Users/Ahmed.ahmed/Desktop/cbe-reports/cbe-reports/exports" + "/" + this.appName + i + "-sync.csv'" + "  INTO TABLE `" + str5 + "_sync` COLUMNS TERMINATED BY ',' LINES TERMINATED BY '\\n'";
                System.out.println(sync);
                localStatement.executeUpdate(sync);
                localStatement.close();
                localStatement = this.dstConn.createStatement();
                localStatement.executeUpdate("delete from `" + str5 + "` where id in (select `key` from " + str5 + "_sync)");
                localStatement.close();
                localStatement = this.dstConn.createStatement();
                localStatement.executeUpdate("delete from `" + str5 + "_subs` where id in (select `key` from " + str5 + "_sync)");
                localStatement.close();
                localStatement = this.dstConn.createStatement();
                String export = "LOAD DATA LOCAL INFILE '" + "C:/Users/Ahmed.ahmed/Desktop/cbe-reports/cbe-reports/exports" + "/" + this.appName + i + "-export.csv'" + "  INTO TABLE `" + str5 + "` COLUMNS  terminated by ',' LINES TERMINATED by '\\n'";
                System.out.println(export);

                localStatement.executeUpdate(export);
                localStatement.close();
                localStatement = this.dstConn.createStatement();
                String subs = "LOAD DATA LOCAL INFILE '" + "C:/Users/Ahmed.ahmed/Desktop/cbe-reports/cbe-reports/exports" + "/" + this.appName + i + "-subs.csv'" + "  INTO TABLE `" + str5 + "_subs` COLUMNS  terminated by ','  LINES TERMINATED by '\\n'";
                System.out.println(subs);
                localStatement.executeUpdate(subs);
                localStatement.close();

                localStatement = this.dstConn.createStatement();
                localStatement.executeUpdate("delete from `" + str5 + "_sync`");
                localStatement.close();
            }
            localStatement = this.dstConn.createStatement();
            Calendar localCalendar = Calendar.getInstance();
            localStatement.executeUpdate("update import_list set last_update='" + String.format("%04d", new Object[]{Integer.valueOf(localCalendar.get(1))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar.get(2))}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(localCalendar.get(5))}) + "',run_import=0 where app_name='" + this.appName + "'");
            localStatement.close();

            if (this.isVisual) {
                this.panel.setStatus("Done...");
            }

        } catch (SQLException localSQLException) {
            localSQLException.printStackTrace();

        } catch (Exception localException) {
            localException.printStackTrace();

        }

    }
}
