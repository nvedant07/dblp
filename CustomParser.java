package dblp;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class CustomParser {
   
	Person p=new Person(DBLP.author_to_search);
	private final List<String> t=p.give_bucket();
	
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
          
        }

        public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        	if((rawName.equals("mastersthesis") || rawName.equals("phdthesis") || rawName.equals("inproceedings") || rawName.equals("proceedings")
          		  || rawName.equals("book") || rawName.equals("incollection") || rawName.equals("article") || rawName.equals("www"))&&insideMasterTag)
            {//        		if(count==1){
//    			System.out.println(same_authors.toString());
//        		count++;
//        		System.out.println(Person.same_names.size());
//        		System.out.println(Person.same_names.get(0).toString());
//    		}
//        		System.out.println(t.toString());
        		for(int i=0 ; i<authors.size() ; i++){
        			for(String s:t){
        				if(s.toLowerCase().equals(authors.get(i).toLowerCase())){
            				Publication temp=new Publication(authors);
            				if(title!=null){
            					temp.setTitle(title);
            				}
            				if(pages!=null){
            					temp.setPages(pages);
            				}
            				if(year!=0){
            					temp.setYear(year);
            				}
            				if(volume!=null){
            					temp.setVolume(volume);
            				}
            				if(journal!=null){
            					temp.setJournal(journal);
            				}
            				if(booktitle!=null){
            					temp.setBooktitle(booktitle);
            				}
            				if(url!=null){
            					temp.setUrl(url);
            				}
            				DBLP.result_publications.add(temp);
            				break;
            			}
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
        		  else if(recordTag.equals("url")){
        			  url=Value;
        		  }
        		  else if(recordTag.equals("year")){
        			  year=Integer.parseInt(Value);
        		  }
        		  else if(recordTag.equals("title")){
        			  title=Value;
        		  }
        		  else if(recordTag.equals("pages")){
        			  pages=Value;
        		  }
        		  else if(recordTag.equals("journal")){
        			  journal=Value;
        		  }
        		  else if(recordTag.equals("booktitle")){
        			  booktitle=Value;
        		  }
        		  else if(recordTag.equals("volume")){
        			  volume=Value;
        		  }
//        	  }
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
   
   CustomParser(String uri,String author) {
      try {
    	  System.setProperty("jdk.xml.entityExpansionLimit", "0");
	     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	     SAXParser parser = parserFactory.newSAXParser();
	     ConfigHandler handler = new ConfigHandler();
         parser.getXMLReader().setFeature(
	          "http://xml.org/sax/features/validation", true);
         DBLP.author_to_search=author;
         DBLP.result_publications.clear();
         DBLP.author_count.clear();
//         Person.same_names.clear();
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


