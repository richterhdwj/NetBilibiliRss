package my.com.NetBilibiliRss.server;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 这里是自动获取Rss页面后，进行分析后并进行相应储存的处理机构。
 * @author 俊伟
 *
 */
public class GetNetBilibiliRss extends DefaultHandler{
	
	public final static String bilibiliUrl="http://www.bilibili.tv/rss-13.xml";
	
	public void getRssUrlXml(String url) throws Exception{
		URL urlpost=new URL(url);
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser=factory.newSAXParser();
		
		saxParser.getXMLReader().setContentHandler(new MyContentHandler());
		saxParser.parse(urlpost.openStream(),this);
		
		XMLReader xml=saxParser.getXMLReader();
	}
}
