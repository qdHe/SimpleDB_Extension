package simpledb.parse;

import simpledb.query.*;
import java.util.*;

/**
 * Data for the SQL <i>select</i> statement.
 * @author Edward Sciore
 */
 //=====================================
//add "order by"
//jindi 12/18
//=====================================
public class QueryData {
   private Collection<String> fields;
   private Collection<String> tables;
   private Predicate pred;
   private boolean needSort;
   private List<String> sortFields;
   private boolean isDesc;
   
   /**
    * Saves the field and table list and predicate.
    */
   public QueryData(Collection<String> fields, Collection<String> tables, Predicate pred,
   						boolean needSort,List<String> sortFields, boolean isDesc) {
      this.fields = fields;
      this.tables = tables;
      this.pred = pred;
	  this.needSort = needSort;
	  this.sortFields = sortFields;
	  this.isDesc = isDesc;
   }
						
	public QueryData(Collection<String> fields, Collection<String> tables, Predicate pred) {
	   this.fields = fields;
	   this.tables = tables;
	   this.pred = pred;
	   this.needSort = false;
	   this.sortFields = null;
	   this.isDesc = false;
	}

	 /**
		 * Returns whether order by clause exist.
		 * @return a collection of field names
		 */
	public boolean needSort() {
	   return needSort;
	}

	 /**
		 * Returns the order mentioned in the order by clause.
		 * @return a collection of field names
		 */
	public boolean  isDesc () {
	   return isDesc;
	}


	/**
		* Returns the fields mentioned in the order by clause.
		* @return a collection of field names
		*/
   public List<String> sortFields() {
	  return sortFields;
   }
	   


   /**
    * Returns the fields mentioned in the select clause.
    * @return a collection of field names
    */
   public Collection<String> fields() {
      return fields;
   }
   
   /**
    * Returns the tables mentioned in the from clause.
    * @return a collection of table names
    */
   public Collection<String> tables() {
      return tables;
   }
   
   /**
    * Returns the predicate that describes which
    * records should be in the output table.
    * @return the query predicate
    */
   public Predicate pred() {
      return pred;
   }
   
   public String toString() {
      String result = "select ";
      if(fields == null){
          result += "*";
      }
      else {
          for (String fldname : fields)
              result += fldname + ", ";
          result = result.substring(0, result.length() - 2); //remove final comma
      }
      result += " from ";
      for (String tblname : tables)
         result += tblname + ", ";
      result = result.substring(0, result.length()-2); //remove final comma
      String predstring = pred.toString();
      if (!predstring.equals(""))
         result += " where " + predstring;
	  if (!sortFields.isEmpty()){
	  	 result += " order by "; 
		 for (String fldname : fields)
         	result += fldname + ", ";
         result = result.substring(0, result.length()-2); //remove final comma
	  }
	  
      return result;
   }
}
