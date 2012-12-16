package my.com.NetBilibiliRss.client.interFace;

import java.util.List;

import my.com.NetBilibiliRss.shared.model.NewSeasonType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("bilibilirss")
public interface BilibiliRssServers extends RemoteService {
	List<NewSeasonType> getSeasonTypeList() throws IllegalArgumentException;
}
