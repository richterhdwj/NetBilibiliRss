package my.com.NetBilibiliRss.server;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class MyContentHandler implements ContentHandler {

    private StringBuffer buf;

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
           System.out.println("\t内容是: " + buf.toString().trim());
        }
        buf.setLength(0);
        //打印元素解析结束信息
        System.out.println("元素: "+"["+fullName+"]"+" 解析结束!");             
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
}
