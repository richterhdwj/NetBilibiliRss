package my.com.NetBilibiliRss.server;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import my.com.NetBilibiliRss.shared.model.TempBilibiliRss;

/**
 * 这里是自动获取Rss页面后，进行分析后并进行相应储存的处理机构。
 * 
 * @author 俊伟
 * 
 */
public class GetNetBilibiliRss {

	public final static String bilibiliUrl = "http://www.bilibili.tv/rss-13.xml";

	@SuppressWarnings("unchecked")
	public void getRssUrlXml(String url) throws Exception {
		//获取相关数据
		URL urlpost = new URL(url);

		//对xml进行解析
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		MyContentHandler mycontent = new MyContentHandler();

		saxParser.parse(urlpost.openStream(), mycontent);
		
		//获得解析后的数据集合
		ArrayList<TempBilibiliRss> list = mycontent.getRet();


		try {
//			将获得的rss进行筛选后进行保存
			PersistenceManager pm = PMF.get().getPersistenceManager();
			ArrayList<TempBilibiliRss> saveList=new ArrayList<TempBilibiliRss>();
			for(TempBilibiliRss tempbilibili:list){
				Query searching=pm.newQuery(TempBilibiliRss.class);
				searching.setFilter("link == urlpath");
				searching.declareParameters("String urlpath");
				try{
					List<TempBilibiliRss> result=(List<TempBilibiliRss>)searching.execute(tempbilibili.getLink());
					if(result.size()<=0){
						saveList.add(tempbilibili);
					}else{
						TempBilibiliRss temp=result.get(0);
						temp.setAuthor(tempbilibili.getAuthor());
						temp.setComments(tempbilibili.getComments());
						temp.setDescription(tempbilibili.getDescription());
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			pm.makePersistentAll(saveList);
			pm.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存出错了");
		}
	}
}
