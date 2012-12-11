package my.com.NetBilibiliRss.server;

import java.util.ArrayList;

import my.com.NetBilibiliRss.server.model.TempBilibiliRss;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler extends DefaultHandler {

    private StringBuffer buf;
    
    private boolean hasRecord=false;
    
    private ArrayList<TempBilibiliRss> ret=new ArrayList<TempBilibiliRss>();
    
    private TempBilibiliRss tempbilibilirss;
    
    private final static String item="item";
    
    private final static String link = "link";
    
    private final static String title = "title";
    
    private final static String author = "author";
    
    private final static String description = "description";
    
    private final static String comments = "comments";
    
    private final static String pubDate = "pubDate";

    public void setDocumentLocator( Locator locator ) {
    }

    public void startDocument() throws SAXException {
        buf=new StringBuffer();
        System.out.println("*******开始解析文档*******");
    }

    public void endDocument() throws SAXException {
        System.out.println("*******解析文档结束*******");
    }

    public void processingInstruction( String target, String instruction )
        throws SAXException {
    }

    public void startPrefixMapping( String prefix, String uri ) {
          System.out.println("\n前缀映射: " + prefix +" 开始!"+ "  它的URI是:" + uri);
    }

    public void endPrefixMapping( String prefix ) {
          System.out.println("\n前缀映射: "+prefix+" 结束!");
    }

    public void startElement( String namespaceURI, String localName,
                                  String fullName, Attributes attributes )
                          throws SAXException {
    	if(fullName.equals(item)){
    		hasRecord=true;
    		tempbilibilirss=new TempBilibiliRss();
    	}
    	
    	
        System.out.println("\n 元素: " + "["+fullName+"]" +" 开始解析!");
        // 打印出属性信息
        for ( int i = 0; i < attributes.getLength(); i++ ) {
            System.out.println("\t属性名称:" + attributes.getLocalName(i)
                + " 属性值:" + attributes.getValue(i));
        }
    }

    public void endElement( String namespaceURI, String localName,
                                                      String fullName )
                          throws SAXException {
        //打印出非空的元素内容并将StringBuffer清空                 
      String nullStr="";
        if (!buf.toString().trim().equals(nullStr)){
        	if(hasRecord){
        		if(tempbilibilirss==null){
            		tempbilibilirss=new TempBilibiliRss();
        		}
        		if(fullName.equals(title)){
        			tempbilibilirss.setTitle(buf.toString());
        		}else if(fullName.equals(link)){
        			tempbilibilirss.setLink(buf.toString());
        		}else if(fullName.equals(author)){
        			tempbilibilirss.setAuthor(buf.toString());
        		}else if(fullName.equals(description)){
        			tempbilibilirss.setDescription(buf.toString());
        		}else if(fullName.equals(comments)){
        			tempbilibilirss.setComments(buf.toString());
        		}else if(fullName.equals(pubDate)){
        			tempbilibilirss.setPubDate(buf.toString());
        		}
        	}
        }
        if(fullName.equals(item)){
			ret.add(tempbilibilirss);
			hasRecord=false;
		}
        buf.setLength(0);
        //打印元素解析结束信息         
    }

    public void characters( char[] chars, int start, int length )
                                throws SAXException {
          //将元素内容累加到StringBuffer中               
          buf.append(chars,start,length);
    }

    public void ignorableWhitespace( char[] chars, int start, int length )
                                  throws SAXException {
    }

    public void skippedEntity( String name ) throws SAXException {
    }

	public ArrayList<TempBilibiliRss> getRet() {
		return ret;
	}

	public void setRet(ArrayList<TempBilibiliRss> ret) {
		this.ret = ret;
	}
}
