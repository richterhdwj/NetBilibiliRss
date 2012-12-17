package my.com.NetBilibiliRss.client;

import java.util.ArrayList;
import java.util.List;

import my.com.NetBilibiliRss.client.interFace.BaseSetting;
import my.com.NetBilibiliRss.client.interFace.BilibiliRssServers;
import my.com.NetBilibiliRss.client.interFace.BilibiliRssServersAsync;
import my.com.NetBilibiliRss.shared.model.NewSeasonType;
import my.com.NetBilibiliRss.shared.model.SeasonBan;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ThirdTab implements BaseSetting {
	/**
	 * 
	 */
	private final BilibiliRssServersAsync bilibiliRssServers = GWT
			.create(BilibiliRssServers.class);
	
	List<NewSeasonType> seasonList;//季组内容
	
	ListBox seasonListBox; //季组列表
	
	HTMLTable banTable; //番组表格
	
	HorizontalPanel basePanel; //列表放置区域
	
	HorizontalPanel buttonPanel; //按键放置区域
	
	Button newSaveButton; //新增按钮

	Button modityButton; //修改按钮
	
	DialogBox dialogBox;//点击新增/修改后的弹出界面
	
	

	//TODO::
	public void load(){
		
	}
	
	//TODO::
	public void clean(){
		
	}
	
	//TODO::
	public void cache(){
		
	}
	
	//TODO:此Tab上的所有控件disable
	public void disable(){
		
	}
	
	//TODO:此Tab上的所有控件enable
	public void enable(){
		
	}
	
	//从这里开始设定第三tab面的内容
	public Widget getList(){
		basePanel=new HorizontalPanel();
		
		//获取相应的季组
		setSeasonList();
		
		basePanel.add(seasonListBox);
		
		//获取季组之下的相应番组设置
		
		seasonListBox.setSelectedIndex(0);
		
		this.setBanlist(0);

		basePanel.add(banTable);
		
		//设定成员间距
		
		basePanel.setSpacing(10);
		
		return basePanel;
	}
	
	//获取相应的季组
	private void setSeasonList(){
		seasonListBox=new ListBox();
		seasonListBox.setWidth("30%");
		
		seasonList = new ArrayList<NewSeasonType>();
		
		bilibiliRssServers.getSeasonTypeList(new AsyncCallback<List<NewSeasonType>>() {
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("似乎是季组获取出错了的样子.");
			}

			@Override
			public void onSuccess(List<NewSeasonType> result) {
				for(NewSeasonType obj:result){
					seasonListBox.addItem(obj.getSeasonName(),Long.toString(obj.getKey().getId()));
					seasonList.add(obj);
				}
			}
		});
		
		//这里是列表点击事件
		seasonListBox.addMouseDownHandler(new MouseDownHandler(){
			@Override
			public void onMouseDown(MouseDownEvent event) {
				if(event.getNativeButton()==com.google.gwt.dom.client.NativeEvent.BUTTON_LEFT)
					setBanlist(seasonListBox.getSelectedIndex());
			}
		});
	}
	
	//获取季组之下的番组
	private void setBanlist(int index){
		
		banTable = new FlexTable();
		
		banTable.setWidth("65%");

		banTable.setText(0, 0, "选择");
		banTable.setText(0, 1, "关键字");
		banTable.setText(0, 2, "剔除字");
		banTable.setText(0, 3, "活跃");
		banTable.setText(0, 4, "修改");
		
		if(seasonList.size()>0){
			NewSeasonType st=seasonList.get(index);
			for(final SeasonBan seasonban:st.getBanlist()){
				int row=banTable.getRowCount();
				//选择框列
				CheckBox checkbox=new CheckBox();
				checkbox.setWidth("5%");
				checkbox.setValue(false);
				banTable.setWidget(row, 0, checkbox);
				
				//关键字列
				String useName=null;
				for(String useNames:seasonban.getName()){
					if(useName==null){
						useName=useNames;
					}else{
						useName=useName+","+useNames;
					}
				}
				TextBox useNameBox=new TextBox();
				useNameBox.setText(useName);
				useNameBox.setWidth("40%");
				useNameBox.setEnabled(false);
				banTable.setWidget(row, 1, useNameBox);
				
				//剔除字列
				String unpassUseName=null;

				for(String unpassUseNames:seasonban.getUnpassName()){
					if(useName==null){
						unpassUseName=unpassUseNames;
					}else{
						unpassUseName=unpassUseName+","+unpassUseNames;
					}
				}
				TextBox unpassUseNameBox=new TextBox();
				unpassUseNameBox.setText(useName);
				unpassUseNameBox.setWidth("40%");
				unpassUseNameBox.setEnabled(false);
				banTable.setWidget(row, 2, unpassUseNameBox);
				
				//是否活跃列
				Button activeButton=new Button();
				if(seasonban.getHasActive().equals("1")){
					activeButton.setText("活跃");
				}else{
					activeButton.setText("沉寂");
				}
				activeButton.setWidth("7%");
				banTable.setWidget(row, 3, activeButton);
				
				//修改列
				Button modifyButton=new Button();
				modifyButton.setText("修改");
				modifyButton.addClickHandler(new ClickHandler(){
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						
					}
				});
				banTable.setWidget(row, 4, modifyButton);
				
			}
		}
	}
	
	private void openDialogBox(SeasonBan seasonban){

		// Create the popup dialog box
		dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
	}
}
