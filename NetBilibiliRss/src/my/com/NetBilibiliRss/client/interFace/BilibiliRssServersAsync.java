package my.com.NetBilibiliRss.client.interFace;

import java.util.List;

import my.com.NetBilibiliRss.shared.model.NewSeasonType;
import my.com.NetBilibiliRss.shared.model.TempBilibiliRss;

import com.google.appengine.api.users.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BilibiliRssServersAsync {
	void getSeasonTypeList(AsyncCallback<List<NewSeasonType>> callback) throws IllegalArgumentException;
	void getTempBilibiliRss(AsyncCallback<List<TempBilibiliRss>> callback);
	void getNewSeasoTypeList(User user,AsyncCallback<List<NewSeasonType>> callback);
}
