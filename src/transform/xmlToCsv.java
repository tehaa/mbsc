package transform;

import java.util.Vector;
import oracle.xdb.XMLType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xmlToCsv
{
  private fieldDefinition[] defs;
  private int subCount;
  private Vector<Vector<String[]>> subData;
  private int subColIndex;
  private char sFieldDelimiter;
  private char sMultiDelimiter;
  private char sSubDelimiter;
  private int curr_no;
  private int curr_no_loc;
  private String rowId;

  public xmlToCsv(char paramChar1, char paramChar2, char paramChar3)
  {
    this.sFieldDelimiter = ',';
    this.sMultiDelimiter = '|';
    this.sSubDelimiter = '$';
  }
  
  public String getSublines()
  {
   
    String str = "";
    for (int i = 0; i < this.subData.size(); i++)
      for (int j = 0; j < ((Vector)this.subData.get(i)).size(); j++)
      {
        String[] arrayOfString = (String[])((Vector)this.subData.get(i)).get(j);
        str = str + (i + 1) + "," + (j + 1) + "," + this.rowId;
        for (int k = 0; k < arrayOfString.length; k++)
          str = str + "," + arrayOfString[k];                             
        str = str + "\n";
      }
 
    return str;
  }
   
  public void setCurr_no_loc(int paramInt)
  {
    this.curr_no_loc = paramInt;
  }

  public void setFieldDelimiters(char paramChar1, char paramChar2, char paramChar3)
  {
    this.sFieldDelimiter = paramChar1;
    this.sMultiDelimiter = paramChar2;
    this.sSubDelimiter = paramChar3;
  }

  public char getFieldDelimiter()
  {
    return this.sFieldDelimiter;
  }

  public char getMultiDelimiter()
  {
    return this.sMultiDelimiter;
  }
   
  
  public char getSubDelimiter()
  {
    return this.sSubDelimiter;
  }

  public void setDefs(fieldDefinition[] paramArrayOffieldDefinition)
  {
    this.defs = paramArrayOffieldDefinition;
  }

  public void setSubCount(int paramInt)
  {
    this.subCount = paramInt;
  }

  public boolean requiresSync()
  {
    return this.curr_no > 1;
  }

  private boolean isGetRowId(fieldDefinition paramfieldDefinition)
  {
    return (paramfieldDefinition.isIs_id()) || (paramfieldDefinition.getField_location() == 0);
  }

  private boolean isGetFieldValue(fieldDefinition paramfieldDefinition)
  {
    return (!paramfieldDefinition.isIs_multi()) && (!paramfieldDefinition.isIs_sub()) && (paramfieldDefinition.getField_location() != 0) && (paramfieldDefinition.getField_multi_location() == 0) && (paramfieldDefinition.getField_sub_location() == 0);
  }

  private boolean isGetSubValue(fieldDefinition paramfieldDefinition)
  {
    return (!paramfieldDefinition.isIs_multi()) && (!paramfieldDefinition.isIs_sub()) && (paramfieldDefinition.getField_location() != 0) && (paramfieldDefinition.getField_multi_location() != 0) && (paramfieldDefinition.getField_sub_location() != 0);
  }

  private boolean isGetMultiValue(fieldDefinition paramfieldDefinition)
  {
    return (!paramfieldDefinition.isIs_multi()) && (!paramfieldDefinition.isIs_sub()) && (paramfieldDefinition.getField_location() != 0) && (paramfieldDefinition.getField_multi_location() != 0) && (paramfieldDefinition.getField_sub_location() == 0);
  }

  private boolean isGetMultiValues(fieldDefinition paramfieldDefinition)
  {
    return (paramfieldDefinition.isIs_multi()) && (!paramfieldDefinition.isIs_sub()) && (paramfieldDefinition.getField_location() != 0) && (paramfieldDefinition.getField_multi_location() == 0) && (paramfieldDefinition.getField_sub_location() == 0);
  }

  private boolean isGetSubValues(fieldDefinition paramfieldDefinition)
  {
    return (paramfieldDefinition.isIs_multi()) && (paramfieldDefinition.isIs_sub()) && (paramfieldDefinition.getField_location() != 0) && (paramfieldDefinition.getField_multi_location() == 0) && (paramfieldDefinition.getField_sub_location() == 0);
  }

  private String getSubFieldValues(NodeList paramNodeList)
  {
    String str = "";
    try
    {
      int j = 0;
      int m = 0;
      for (int n = 0; n < paramNodeList.getLength(); n++)
      {
        Element localElement = (Element)paramNodeList.item(n);
        Node localNode;
        int i;
        try
            
        {
          localNode = localElement.getAttributes().getNamedItem("m");
          i = Integer.parseInt(localNode.getNodeValue());
        }
        catch (NullPointerException localNullPointerException1)
        {
          i = 1;
        }
        int k;
        try
        {
          localNode = localElement.getAttributes().getNamedItem("s");
          k = Integer.parseInt(localNode.getNodeValue());
        }
        catch (NullPointerException localNullPointerException2)
        {
          k = 1;
        }
        String[] arrayOfString;
        if (n == 0)
        {
          while (j < i - 1)
          {
            str = str + this.sMultiDelimiter;
            j++;
          }
          while (m < k - 1)
          {
            str = str + this.sSubDelimiter;
            m++;
          }
          str = str + localElement.getTextContent();
          while (this.subData.size() < i)
            this.subData.add(new Vector());
          while (((Vector)this.subData.get(i - 1)).size() < k)
            ((Vector)this.subData.get(i - 1)).add(new String[this.subCount]);
          arrayOfString = (String[])((Vector)this.subData.get(i - 1)).get(k - 1);
          arrayOfString[(this.subColIndex - 1)] = localElement.getTextContent();
          ((Vector)this.subData.get(i - 1)).set(k - 1, arrayOfString);
          j++;
          m++;
        }
        else if (i != j)
        {
          while (j < i - 1)
          {
            str = str + this.sMultiDelimiter;
            j++;
          }
          str = str + this.sMultiDelimiter + localElement.getTextContent();
          while (this.subData.size() < i)
            this.subData.add(new Vector());
          while (((Vector)this.subData.get(i - 1)).size() < k)
            ((Vector)this.subData.get(i - 1)).add(new String[this.subCount]);
          arrayOfString = (String[])((Vector)this.subData.get(i - 1)).get(k - 1);
          arrayOfString[(this.subColIndex - 1)] = localElement.getTextContent();
          ((Vector)this.subData.get(i - 1)).set(k - 1, arrayOfString);
          j++;
        }
        else
        {
          while (m < k - 1)
          {
            str = str + this.sSubDelimiter;
            m++;
          }
          str = str + this.sSubDelimiter + localElement.getTextContent();
          while (this.subData.size() < i)
            this.subData.add(new Vector());
          while (((Vector)this.subData.get(i - 1)).size() < k)
            ((Vector)this.subData.get(i - 1)).add(new String[this.subCount]);
          arrayOfString = (String[])((Vector)this.subData.get(i - 1)).get(k - 1);
          arrayOfString[(this.subColIndex - 1)] = localElement.getTextContent();
          ((Vector)this.subData.get(i - 1)).set(k - 1, arrayOfString);
          m++;
        }
      }
    }
    catch (Exception localException)
    {
      return null;
    }
    
    return str;
  }

  private String getSubFieldValue(NodeList paramNodeList, int paramInt1, int paramInt2)
  {
    String str = "";
    for (int k = 0; k < paramNodeList.getLength(); k++)
    {
      Element localElement = (Element)paramNodeList.item(k);
      Node localNode;
      int i;
      try
      {
        localNode = localElement.getAttributes().getNamedItem("m");
        i = Integer.parseInt(localNode.getNodeValue());
      }
      catch (NullPointerException localNullPointerException1)
      {
        i = 1;
      }
      int j;
      try
      {
        localNode = localElement.getAttributes().getNamedItem("s");
        j = Integer.parseInt(localNode.getNodeValue());
      }
      catch (NullPointerException localNullPointerException2)
      {
        j = 1;
      }
      if ((i == paramInt1) && (j == paramInt2))
      {
        str = localElement.getTextContent();
        break;
      }
    }
    return str;
  }

  private String getMultiFieldValues(NodeList paramNodeList)
  {
    String str = "";
    int i = 0;
    for (int k = 0; k < paramNodeList.getLength(); k++)
    {
      Element localElement = (Element)paramNodeList.item(k);
      int j;
      try
      {
        Node localNode = localElement.getAttributes().getNamedItem("m");
        j = Integer.parseInt(localNode.getNodeValue());
      }
      catch (NullPointerException localNullPointerException)
      {
        j = 1;
      }
      String[] arrayOfString;
      if (k == 0)
      {
        while (i < j - 1)
        {
          str = str + this.sMultiDelimiter;
          i++;
        }
        str = str + localElement.getTextContent();
        while (this.subData.size() < j)
          this.subData.add(new Vector());
        if (((Vector)this.subData.get(j - 1)).size() < 1)
          ((Vector)this.subData.get(j - 1)).add(new String[this.subCount]);
        arrayOfString = (String[])((Vector)this.subData.get(j - 1)).get(0);
        arrayOfString[(this.subColIndex - 1)] = localElement.getTextContent();
        ((Vector)this.subData.get(j - 1)).set(0, arrayOfString);
        i++;
      }
      else
      {
        while (i < j - 1)
        {
          str = str + this.sMultiDelimiter;
          i++;
        }
        str = str + this.sMultiDelimiter + localElement.getTextContent();
        while (this.subData.size() < j)
          this.subData.add(new Vector());
        if (((Vector)this.subData.get(j - 1)).size() < 1)
          ((Vector)this.subData.get(j - 1)).add(new String[this.subCount]);
        arrayOfString = (String[])((Vector)this.subData.get(j - 1)).get(0);
        arrayOfString[(this.subColIndex - 1)] = localElement.getTextContent();
        ((Vector)this.subData.get(j - 1)).set(0, arrayOfString);
        i++;
      }
    }
    return str;
  }

  private String getMultiFieldValue(NodeList paramNodeList, int paramInt)
  {
    String str = "";
    for (int j = 0; j < paramNodeList.getLength(); j++)
    {
      Element localElement = (Element)paramNodeList.item(j);
      int i;
      try
      {
        Node localNode = localElement.getAttributes().getNamedItem("m");
        i = Integer.parseInt(localNode.getNodeValue());
      }
      catch (NullPointerException localNullPointerException)
      {
        i = 1;
      }
      if (i == paramInt)
      {
        str = localElement.getTextContent();
        break;
      }
    }
    return str;
  }

  public String processXmlContent(XMLType paramXMLType)
  {
    String str = "";
    if (this.subData == null)
      this.subData = new Vector();
    else
      this.subData.clear();
    try
    {
      this.subColIndex = 1;
      Document localDocument = paramXMLType.getDOM();
      this.rowId = localDocument.getDocumentElement().getAttributes().getNamedItem("id").getNodeValue();
      for (int i = 0; i < this.defs.length; i++)
      {
        str = str + this.sFieldDelimiter;
        if (isGetRowId(this.defs[i]))
        {
          str = str + this.rowId;
        }
        else
        {
          NodeList localNodeList;
          if (isGetFieldValue(this.defs[i]))
          {
            localNodeList = localDocument.getElementsByTagName("c" + this.defs[i].getField_location());
            if (this.curr_no_loc == this.defs[i].getField_location())
              if (localNodeList.getLength() > 0)
                this.curr_no = Integer.parseInt(localNodeList.item(0).getTextContent().replaceAll(",", ""));
              else
                this.curr_no = 1;
            if (localNodeList.getLength() > 0)
              str = str + localNodeList.item(0).getTextContent().replaceAll(",", "");
          }
          else if (isGetSubValue(this.defs[i]))
          {
            localNodeList = localDocument.getElementsByTagName("c" + this.defs[i].getField_location());
            str = str + getSubFieldValue(localNodeList, this.defs[i].getField_multi_location(), this.defs[i].getField_sub_location()).replaceAll(",", "");
          }
          else if (isGetMultiValue(this.defs[i]))
          {
            localNodeList = localDocument.getElementsByTagName("c" + this.defs[i].getField_location());
            str = str + getMultiFieldValue(localNodeList, this.defs[i].getField_multi_location()).replaceAll(",", "");
          }
          else if (isGetSubValues(this.defs[i]))
          {
            localNodeList = localDocument.getElementsByTagName("c" + this.defs[i].getField_location());
            str = str + getSubFieldValues(localNodeList).replaceAll(",", "");
            this.subColIndex += 1;
          }
          else if (isGetMultiValues(this.defs[i]))
          {
            localNodeList = localDocument.getElementsByTagName("c" + this.defs[i].getField_location());
            str = str + getMultiFieldValues(localNodeList).replaceAll(",", "");
            this.subColIndex += 1;
          }
        }
      }
     
      return str.substring(1);
    }
    catch (Exception localException)
    {
    }
    return null;
  }
}

