package dblp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


public class WwwParser {
	private class ConfigHandler extends DefaultHandler {

        private Locator locator;
        private String Value;
        private String key;
        private String recordTag;
        private String masterTag;
        private boolean foundAuthor;
        private boolean insideTag;
        private boolean insidewww;
        private ArrayList<String> same_authors=new ArrayList<String>();
        
        public void setDocumentLocator(Locator locator) {
            this.locator = locator;
        }
        
        public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) throws SAXException {
          String k;
          
          if(rawName.equals("www")){
        	  if(atts.getLength()>0 && (k=atts.getValue("key"))!=null){
                  key=k;
                  if(key.matches("homepages/(.*)")){
                    insidewww=true;
                    masterTag=rawName;
                  }
                }
          }
          else if(insidewww){
        	recordTag=rawName;
            insideTag=true;
            Value = "";
          }
//          else if(!insidewww && rawName.equals("author")){
//        	  foundAuthor=true;
//        	  Value="";
//          }
        }

        public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        	if(rawName.equals("www") && insidewww){
        		insidewww=false;
        		
        		if(same_authors.size()>0){
        			ArrayList<String> temp=new ArrayList<String>();
        			for(String s: same_authors){
        				temp.add(s);
        			}
        			DBLP.same_names.add(temp);
//        			if(!DBLP.same_names.get(DBLP.same_names.size()-1).toString().equals(same_authors.toString())){
//        				System.out.println("problem");
//        			}
        		}
        		same_authors.clear();
        	}
        	else if(insideTag && insidewww){
        		if(recordTag.equals("author")||recordTag.equals("editor")){
        			same_authors.add(Value);
        		}
        		insideTag=false;
        	}
//        	else if(foundAuthor && !insidewww){
//        		if(DBLP.author_count.containsKey(Value))DBLP.author_count.put(Value, DBLP.author_count.get(Value)+1);
//        		else DBLP.author_count.put(Value, 1);
//        		foundAuthor=false;
//        	}
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
   
   WwwParser(String uri) {
      try {
    	  System.setProperty("jdk.xml.entityExpansionLimit", "0");
	     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	     SAXParser parser = parserFactory.newSAXParser();
	     ConfigHandler handler = new ConfigHandler();
         parser.getXMLReader().setFeature(
	          "http://xml.org/sax/features/validation", true);
//         DBLP.author_to_search=author;
//         DBLP.result_publications.clear();
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
