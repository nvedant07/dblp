package dblp;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

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
        }
        
        public void print_authors(ArrayList<String> arr){
    		for(int j=0;j<arr.size();j++){
    			if(j==arr.size()-1){
    				System.out.println(arr.get(j));
    			}
    			else{
    				System.out.print(arr.get(j)+",");
    			}
    			
    		}
    	}
        
        public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) throws SAXException {
          String k;
          
          if (rawName.equals("mastersthesis") || rawName.equals("phdthesis") || rawName.equals("inproceedings") || rawName.equals("proceedings")
        		  || rawName.equals("book") || rawName.equals("incollection") || rawName.equals("article")) {
                insideMasterTag=true;
                masterTag=rawName;
          }
          else if(insideMasterTag){
            recordTag=rawName;
            insideTag=true;
            Value = "";
          }
          
        }

        public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        	if(rawName.equals("mastersthesis") || rawName.equals("phdthesis") || rawName.equals("inproceedings") || rawName.equals("proceedings")
          		  || rawName.equals("book") || rawName.equals("incollection") || rawName.equals("article"))
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
        	  if(!masterTag.equals("www")){
        		  if(recordTag.equals("author")||recordTag.equals("editor")){
        			  authors.add(Value);
        		  }
        	  }
        	  insideTag=false;
            }																																																																																																																																																																																																																																																																														
        }

        public void characters(char[] ch, int start, int length)
                throws SAXException {
        	if(insideTag)
            Value += new String(ch, start, length);
        }

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
   
   AuthorParser(String uri) {
      try {
    	  System.setProperty("jdk.xml.entityExpansionLimit", "0");
	     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	     SAXParser parser = parserFactory.newSAXParser();
	     ConfigHandler handler = new ConfigHandler();
         parser.getXMLReader().setFeature(
	          "http://xml.org/sax/features/validation", true);
         
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


