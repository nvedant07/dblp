/**
 * Authors:
 * Vedant Nanda 2015114
 * Arpan Mondal 2015132
 */
package dblp;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
/**
 * This is the custom implementation of the SAXParser, which wraps the XMLReader,
 * to read the provodied DBLP XML file, with the required tasks, to find authora and number of papers published 
 */
public class AuthorParser {
   
   private class ConfigHandler extends DefaultHandler {

        private Locator locator;
        private String Value;
        private String masterTag;
        private boolean insideMasterTag;
        private String key;
        private String recordTag;
        private boolean insideTag;
        private ArrayList<String> authors=new ArrayList<String>();
        private String url;
        private String pages;
        private int year;
        private String title;
        private String journal;
        private String booktitle;
        private String volume;
        
        public void setDocumentLocator(Locator locator) {
            this.locator = locator;
        }//!< Handler with all the required fields
        
        public void print_authors(ArrayList<String> arr){
    		for(int j=0;j<arr.size();j++){
    			if(j==arr.size()-1){
    				System.out.println(arr.get(j));
    			}
    			else{
    				System.out.print(arr.get(j)+",");
    			}
    			
    		}
    	}//!< Displays the required members according to the query.
        
        public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) throws SAXException {
          String k;
          
          if (rawName.equals("mastersthesis") || rawName.equals("phdthesis") || rawName.equals("inproceedings") || rawName.equals("proceedings")
        		  || rawName.equals("book") || rawName.equals("incollection") || rawName.equals("article") || rawName.equals("www")) {
                insideMasterTag=true;
                masterTag=rawName;
                if(rawName.equals("www")){
                	  if(atts.getLength()>0 && (k=atts.getValue("key"))!=null){
                          key=k;
                          if(key.matches("homepages/(.*)")){
                            insideMasterTag=false;
                          }
                      }
                  }
          }
          else if(insideMasterTag){
            recordTag=rawName;
            insideTag=true;
            Value = "";
          }
          
        }//!< Identefies required fields from the XML Document

        public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        	if((rawName.equals("mastersthesis") || rawName.equals("phdthesis") || rawName.equals("inproceedings") || rawName.equals("proceedings")
          		  || rawName.equals("book") || rawName.equals("incollection") || rawName.equals("article") || rawName.equals("www"))&&insideMasterTag)
            {
        		for(int i=0 ; i<authors.size() ; i++){
        			if(DBLP.author_count.containsKey(authors.get(i))){
        				int update=DBLP.author_count.get(authors.get(i))+1;
        				DBLP.author_count.replace(authors.get(i), update);
        			}
        			else{
        				DBLP.author_count.put(authors.get(i), 1);
        			}
        		}
              insideMasterTag=false;
              authors.clear();
            }
            else if(insideTag){
//        	  if(!masterTag.equals("www")){
        		  if(recordTag.equals("author")||recordTag.equals("editor")){
        			  authors.add(Value);
        		  }
//        	  }
        	  insideTag=false;
            }																																																																																																																																																																																																																																																																														
        }//!< Idententifies the end of the tag, thus complete recording the field

        public void characters(char[] ch, int start, int length)
                throws SAXException {
        	if(insideTag)
            Value += new String(ch, start, length);
        }//!< Recording the required field

        private void Message(String mode, SAXParseException exception) {
            System.out.println(mode + " Line: " + exception.getLineNumber()
                    + " URI: " + exception.getSystemId() + "\n" + " Message: "
                    + exception.getMessage());
        }

        public void warning(SAXParseException exception) throws SAXException {

            Message("**Parsing Warning**\n", exception);
            throw new SAXException("Warning encountered");
        }

        public void error(SAXParseException exception) throws SAXException {

            Message("**Parsing Error**\n", exception);
            throw new SAXException("Error encountered");
        }

        public void fatalError(SAXParseException exception) throws SAXException {

            Message("**Parsing Fatal Error**\n", exception);
            throw new SAXException("Fatal Error encountered");
        }
    }
   /** The function which is called to parse the XML document.
    * It is called whenever a new query is chosen
    */
   AuthorParser(String uri) {
      try {
    	  System.setProperty("jdk.xml.entityExpansionLimit", "0");
	     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	     SAXParser parser = parserFactory.newSAXParser();
	     ConfigHandler handler = new ConfigHandler();
         parser.getXMLReader().setFeature(
	          "http://xml.org/sax/features/validation", true);
         DBLP.author_count.clear();
         DBLP.result_publications.clear();
         parser.parse(new File(uri), handler);
      } catch (IOException e) {
         System.out.println("Error reading URI: " + e.getMessage());
      } catch (SAXException e) {
         System.out.println("Error in parsing: " + e.getMessage());
      } catch (ParserConfigurationException e) {
         System.out.println("Error in XML parser configuration: " +
			    e.getMessage());
      }
      
   }

}
