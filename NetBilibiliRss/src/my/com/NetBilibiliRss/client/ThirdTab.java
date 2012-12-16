package my.com.NetBilibiliRss.client;

import java.util.List;

import my.com.NetBilibiliRss.client.interFace.BilibiliRssServers;
import my.com.NetBilibiliRss.client.interFace.BilibiliRssServersAsync;
import my.com.NetBilibiliRss.shared.model.NewSeasonType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable;

public class ThirdTab {
	
	private final BilibiliRssServersAsync bilibiliRssServers = GWT
			.create(BilibiliRssServers.class);
	
	//这里设定第三tab面的内容
	public Widget getTab(){
		HorizontalPanel basePanel=new HorizontalPanel();
		
		//获取相应的季组
		final ListBox seasonListBox=new ListBox();
		bilibiliRssServers.getSeasonTypeList(new AsyncCallback<List<NewSeasonType>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<NewSeasonType> result) {
				// TODO Auto-generated method stub
				for(NewSeasonType obj:result){
					seasonListBox.addItem(obj.getSeasonName(),Long.toString(obj.getKey().getId()));
				}
			}
			
		});
		
		basePanel.add(seasonListBox);
		
		//获取季组之下的相应番组设置
		
//		HTMLTable banTable=new HTMLTable();
		
		return basePanel;
	}
}
