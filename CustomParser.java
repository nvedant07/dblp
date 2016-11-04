package dblp;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class CustomParser {
	
	private int maxAuthorsPerPaper=200;
	
	private class ConfigHandler extends DefaultHandler{
		private Locator locator;

        private String Value;
        private String key;
        private String recordTag;
        private Person[] persons= new Person[maxAuthorsPerPaper];
        private int numberOfPersons = 0;

        private boolean insidePerson;

        public void setDocumentLocator(Locator locator) {
            this.locator = locator;
        }

        public void startElement(String namespaceURI, String localName,
                String rawName, Attributes atts) throws SAXException {
            String k;
            
            if (insidePerson = (rawName.equals("author") || rawName
                    .equals("editor"))) {
                Value = "";
                return;
            }
            if ((atts.getLength()>0) && ((k = atts.getValue("key"))!=null)) {
                key = k;
                recordTag = rawName;   
            }
        }

        public void endElement(String namespaceURI, String localName,
                String rawName) throws SAXException {
            if (rawName.equals("author") || rawName.equals("editor")) {

                Person p;
                if ((p = Person.searchPerson(Value)) == null) {
                    p = new Person(Value);
                }
                p.increment();
                if (numberOfPersons<maxAuthorsPerPaper)
                    persons[numberOfPersons++] = p;
                return;
            }
            if (rawName.equals(recordTag)) {
                if (numberOfPersons == 0)
                    return;
                Person pa[] = new Person[numberOfPersons];
                for (int i=0; i<numberOfPersons; i++) {
                    pa[i] = persons[i];
                    persons[i] = null;
                }
                Publication p = new Publication(key,pa);
                numberOfPersons = 0;
            }
        }

        public void characters(char[] ch, int start, int length)
                throws SAXException {
            if (insidePerson)
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
	public CustomParser(String filename){
		try {
		     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		     SAXParser parser = parserFactory.newSAXParser();
		     ConfigHandler handler = new ConfigHandler();
	         parser.getXMLReader().setFeature(
		          "http://xml.org/sax/features/validation", true);
	         parser.parse(new File(filename), handler);
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
