package my.com.NetBilibiliRss.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import my.com.NetBilibiliRss.client.interFace.BilibiliRssServers;
import my.com.NetBilibiliRss.shared.model.NewSeasonType;

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
}
