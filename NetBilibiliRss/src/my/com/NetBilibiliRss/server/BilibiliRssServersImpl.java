package my.com.NetBilibiliRss.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import my.com.NetBilibiliRss.client.interFace.BilibiliRssServers;
import my.com.NetBilibiliRss.shared.model.NewSeasonType;
import my.com.NetBilibiliRss.shared.model.TempBilibiliRss;

import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class BilibiliRssServersImpl extends RemoteServiceServlet implements
		BilibiliRssServers {

	/**
	 * 获取所有设定好的番组名字
	 */
	@SuppressWarnings("unchecked")
	public List<NewSeasonType> getSeasonTypeList() throws IllegalArgumentException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(NewSeasonType.class);
		query.setOrdering("createDate desc");

		List<NewSeasonType> ret = new ArrayList<NewSeasonType>();
		try {
			List<NewSeasonType> getList = (List<NewSeasonType>) query.execute();

			int count = 0;
			for (NewSeasonType nt : getList) {
//				Object[] obj = new Object[] { nt.getSeasonName(), nt.getKey() };
				ret.add(nt);
				count++;
			}

			if (count == 0) {
				System.out.println("还没有一个数据~");
			}
		} catch (Exception e) {
			System.out.println("还没有一个数据~");
		}
		return ret;
	}
	
	/**
	 * 获取临时库中抓到的番组
	 */
	@SuppressWarnings("unchecked")
	public List<TempBilibiliRss> getTempBilibiliRss(){
		GetNetBilibiliRss gnbbTemp=new GetNetBilibiliRss();
		try {
			gnbbTemp.getRssUrlXml(GetNetBilibiliRss.bilibiliUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query searching=pm.newQuery(TempBilibiliRss.class);
		searching.setOrdering("createDate desc");
		List<TempBilibiliRss> result=(List<TempBilibiliRss>)searching.execute();
		
		return result;
	}
	
	/**
	 * 获取所有已保存的季组信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<NewSeasonType> getNewSeasoTypeList(User user){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query searching=pm.newQuery(NewSeasonType.class);
		searching.setFilter("user == getUser");
		searching.setOrdering("pubDate desc");
		searching.declareParameters("User getUser");
		
		List<NewSeasonType> result=(List<NewSeasonType>)searching.execute(user);
		
		return result;
	}
}
