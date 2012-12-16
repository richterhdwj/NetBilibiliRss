package my.com.NetBilibiliRss.client.interFace;

import java.util.List;

import my.com.NetBilibiliRss.shared.model.NewSeasonType;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BilibiliRssServersAsync {
	void getSeasonTypeList(AsyncCallback<List<NewSeasonType>> callback) throws IllegalArgumentException;
}
