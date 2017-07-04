package genertation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jxl.Cell;
import org.apache.poi.hssf.usermodel.HSSFCell;
import static org.apache.poi.hssf.usermodel.HSSFFooter.file;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;

import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.hssf.usermodel.HSSFSheet ;

import connect.Main_page;

public class Generate  {
static  boolean E ;
    static String r_name;
    static String path;
    static String script;
    static Statement stmt;
    static Connection c;
    static boolean exist;
    public static ArrayList<String> pos = new ArrayList<String>();
    public static ArrayList<String> col = new ArrayList<String>();
    public static ArrayList<String> values = new ArrayList<String>();
    static int x;

    public static void getdate() {

        Edit.filldata(Generate_report.report_name);
        r_name = edit_report.r_name;
        path = edit_report.path;
        script = edit_report.scr_path;

    }

    public static void create_table() {
        String drop;
        String create;
        try {
            intail_connection();
            for (int i = 1; i < 5; i++) {
                drop = "drop table IF EXISTS t_" + i + "_" + r_name;
                stmt.executeUpdate(drop);
                drop = "drop table IF EXISTS r_" + i + "_" + r_name;
                System.out.println(drop);
                stmt.executeUpdate(drop);
            }
            for (int i = 1; i < 5; i++) {
                exist = false;
                String script = getscript("t_" + i + "_" + r_name);
                if (exist) {
                    stmt.executeUpdate(script);
                }
                exist = false;
            }
            exist = false;
            for (int i = 1; i < 5; i++) {
                exist = false;
                script = getscript("r_" + i + "_" + r_name);
                if (exist) {
                    stmt.executeUpdate(script);
                    x = i;
                }
                exist = false;
            }

        } catch (Exception es) {

            es.printStackTrace();
        }

    }

    public static String getscript(String scriptname) {
        String script = "scripts";
        try {
            String line;
            File file2 = new File(script + "//" + scriptname + ".txt");
            if (file2.exists()) {
                exist = true;

                BufferedReader br = new BufferedReader(new FileReader(file2));
                script = br.readLine() + "\n";
                while ((line = br.readLine()) != null) {

                    script = script + line + "\n";
                }

            }
        } catch (Exception es) {

            System.out.println(es);
        }
        return script;

    }

    public static void intail_connection() {
        try {
            String x = connect.Main_page.dest_url;
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(x, "root", null);
            System.out.println("Connection ex DB Succesed");
            stmt = c.createStatement();
        } catch (Exception es) {
            System.out.println(es);
        }
    }

    public static void fill_report() {
      E=false ;
        String row = null;
        String data = null;
        intail_connection();
        try {
            for (int p = 1; p <= 1; p++) {
                String query = "select * from r_" + p + "_" + r_name;
                ResultSet rset = stmt.executeQuery(query);
                while (rset.next()) {
                    E=true ;
                    ResultSetMetaData rsmd = rset.getMetaData();
                    String colume = rset.getString(1);
                    for (int i = 2; i <= rsmd.getColumnCount(); i++) {
                        row = rsmd.getColumnName(i);
                        data = rset.getString(row);
                        col.add(row);
                        pos.add(colume);
                        values.add(data);
                    }

                }
                System.out.println(pos.toString());
            }
            String extionion = path.split("\\.")[2];
        
            if (extionion.contains("xlsx")) {
                write_xlsx(path);

            } else if (extionion.matches("xls")) {
                writexls(path);
                // write_xls(path);
            }

        } catch (Exception es) {
            System.out.println(es);

        }

    }

    public static void write_xlsx(String path) {
        try {
            File source = new File(path);
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String X = dateFormat.format(d);
            System.out.println(X);
String destination = "excel reports\\" + r_name + "_" + X + "." + "xlsx";
File dest = new File(destination);
            add_details_report.copyFileUsingChannel(source, dest);
File fil = new File(destination);
            FileInputStream fIP = new FileInputStream(fil);
            System.out.println(destination);
         
            XSSFWorkbook workbook = new XSSFWorkbook(fIP);
            System.out.println(destination);
            if (fil.isFile() && fil.exists()) {
                System.out.println(
                        "openworkbook.xlsx file open successfully.");
            } else {
                System.out.println(
                        "Error to open openworkbook.xlsx file.");
            }
            XSSFSheet worksheet = workbook.getSheetAt(0);

            XSSFCell cell = null;
            XSSFRow row1 = null;
            int rows = worksheet.getLastRowNum();
            System.out.println(rows);
for (int i = 0; i < pos.size(); i++) {
                String row_num = pos.get(i).toLowerCase();
                String colum = col.get(i).toLowerCase();
            String v = values.get(i);
                char p = colum.charAt(0);
//String rr = row_num.substring(1);
                int poi = p - 'a' + 1;
int x = poi - 1;
int col_num = Integer.parseInt(row_num) - 1;
               if (colum.length() > 1) {
                    System.out.println(colum.length());
                    char c = colum.charAt(1);
                    int poi2 = c - 'a' + 1 + 25;
                    x = x + poi2;
       }
                if (v == null) {
                    v = "0";
                }
                try {
 worksheet.getRow(col_num).getCell(x).setCellValue(Double.parseDouble(v));
 XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
                } catch (Exception es) {
                      worksheet.getRow(col_num).getCell(x).setCellValue(v);
                    es.printStackTrace();
                    System.out.println(es);
                }
  }
     worksheet.getCTWorksheet().getSheetViews().getSheetViewArray(0).setRightToLeft(true);
      fIP.close();
            FileOutputStream output_file = new FileOutputStream(destination);
            workbook.write(output_file);
             if (E) {
                  JOptionPane.showMessageDialog(null, "Done", "Success", 2, new ImageIcon("Ok.png"));
            }
  else {
     JOptionPane.showMessageDialog(null, "Error", "Fail", 2, new ImageIcon("Ok.png"));
  }
 output_file.close();
} catch (Exception es) {
            es.printStackTrace();
        }

    }

    public static void write_xls(String path) {
        try {
           File source = new File(path);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String X = dateFormat.format(d);
            System.out.println(X);

            String destination = "excel reports\\" + r_name + "_" + X + "." + "xls";

            File dest = new File(destination);
            add_details_report.copyFileUsingChannel(source, dest);

            File fil = new File(destination);
            FileInputStream fIP = new FileInputStream(fil);
            System.out.println(destination);
            //Get the workbook instance for XLSX file 
            HSSFWorkbook workbook = new HSSFWorkbook(fIP);
            System.out.println(destination);
            if (fil.isFile() && fil.exists()) {
                System.out.println(
                        "openworkbook.xlsx file open successfully.");
            } else {
                System.out.println(
                        "Error to open openworkbook.xlsx file.");
            }
            HSSFSheet worksheet = workbook.getSheetAt(0);

            HSSFCell cell = null;
            HSSFRow row1 = null;
            int rows = worksheet.getLastRowNum();
            System.out.println(rows);
//            worksheet.shiftRows(rows - 2, rows, 10);
//0 Create my new 3th row

            for (int i = 0; i < pos.size(); i++) {
                String row_num = pos.get(i).toLowerCase();
                String colum = col.get(i).toLowerCase();
                System.out.println(colum.length());
                String v = values.get(i);
                char p = colum.charAt(0);

                String rr = row_num.substring(1);
                int poi = p - 'a' + 1;

                int x = poi - 1;

                int col_num = Integer.parseInt(row_num) - 1;
                //    int number = Integer.parseInt(v);
                if (colum.length() > 1) {
                    System.out.println(colum.length());
                    char c = colum.charAt(1);
                    int poi2 = c - 'a' + 1 + 25;
                    x = x + poi2;
                    System.out.println(x);

                }
                if (v == null) {
                    v = "0";
                }
                try {

                        worksheet.getRow(col_num).getCell((short) x).setCellValue(Double.parseDouble(v));
                   // worksheet.getRow(col_num).getCell(x).setCellValue(v);
                //   HSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
                } catch (Exception es) {
                      worksheet.getRow(col_num).getCell((short) x).setCellValue(v);
                    es.printStackTrace();
                    System.out.println(es);
                }
           //     worksheet.getCTWorksheet().getSheetViews().getSheetViewArray(0).setRightToLeft(true);

            }

         //   int rows = worksheet.getLastRowNum();
            //     System.out.println(rows);
            // worksheet.shiftRows(40, rows, 1);
// Create my new 3th row
            //  Row row = worksheet.createRow(1);
            //   System.out.println(rows);
            /* 
             worksheet.shiftRows(120, rows, 1);
             worksheet.createRow(120);
             //    worksheet.getRow(120).getCell(3).setCellValue(5);             */
            //   worksheet.getRow(40).getCell(3).setCellValue(7);
            //   System.out.println(rows + "");
            fIP.close();
            FileOutputStream output_file = new FileOutputStream(destination);
            workbook.write(output_file);

            System.out.println(workbook.getNumberOfSheets());
            

            output_file.close();

        } catch (Exception es) {
            es.printStackTrace();
        }


    }

    public static void writexls(String path) {
        try {
              
            File source = new File(path);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String X = dateFormat.format(d);
            System.out.println(X);

            String destination = "excel reports\\" + r_name + "_" + X + "." + "xls";
            System.out.println(destination);
            File dest = new File(destination);
            add_details_report.copyFileUsingChannel(source, dest);
            File file = new File(destination);
            //.out.println(path_file);

            Workbook wb = Workbook.getWorkbook(file);
            boolean b = wb.isProtected();
            System.out.println(b);
            WritableWorkbook copy = Workbook.createWorkbook(file, wb);
            WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
            cellFont.setColour(Colour.BLACK);
            WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
            
            //  cellFormat.setBackground(Colour.ORANGE);
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            WritableSheet copysheet = copy.getSheet(0);
     
            for (int i = 0; i < pos.size(); i++) {
                String row_num = pos.get(i).toLowerCase();
                String colum = col.get(i).toLowerCase();

                String v = values.get(i);
                char p = colum.charAt(0);

                String rr = row_num.substring(1);
                int poi = p - 'a' + 1;

                int x = poi - 1;

                int col_num = Integer.parseInt(row_num) - 1;
                if (colum.length() > 1) {
                    System.out.println(colum.length());
                    char c = colum.charAt(1);
                    int poi2 = c - 'a' + 1 + 25;
                    x = x + poi2;

                }
                //int number = Integer.parseInt(v);
                if (v == null) {
                    v = "0";
                }
                else if (v=="") {
                    v="0";
                }
               
                try {

                    copysheet.addCell(new jxl.write.Number(x, col_num, Double.parseDouble(v), cellFormat));
                    //copysheet.addCell(new jxl.write.Label(x, col_num, v, cellFormat));
                } catch (Exception e) {
                    System.out.println(v);
                    copysheet.addCell(new jxl.write.Label(x, col_num, v, cellFormat));

                }

            }
            System.out.println(copysheet.getRows());
          
            // copysheet.setPageSetup(PageOrientation.PORTRAIT);
            copy.write();
            System.out.println(b);
  if (E) {
                  JOptionPane.showMessageDialog(null, "Done", "Success", 2, new ImageIcon("Ok.png"));
            }
  else {
     JOptionPane.showMessageDialog(null, "Error", "Fail", 2, new ImageIcon("Ok.png"));
  }
            copy.close();

        } catch (Exception es) {
            es.printStackTrace();
            System.out.println(es);

        }
    }
}
