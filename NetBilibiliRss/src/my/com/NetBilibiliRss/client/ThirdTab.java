package my.com.NetBilibiliRss.client;

import java.util.ArrayList;
import java.util.List;

import my.com.NetBilibiliRss.client.interFace.BaseSetting;
import my.com.NetBilibiliRss.client.interFace.BilibiliRssServers;
import my.com.NetBilibiliRss.client.interFace.BilibiliRssServersAsync;
import my.com.NetBilibiliRss.shared.model.NewSeasonType;
import my.com.NetBilibiliRss.shared.model.SeasonBan;
import my.com.NetBilibiliRss.shared.model.TempBilibiliRss;

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

	List<NewSeasonType> seasonList;// 季组内容

	ListBox seasonListBox; // 季组列表

	HTMLTable banTable; // 番组表格

	HorizontalPanel basePanel; // 列表放置区域

	HorizontalPanel buttonPanel; // 按键放置区域

	Button newSaveButton; // 新增按钮

	Button modityButton; // 修改按钮

	DialogBox dialogBox;// 点击新增/修改后的弹出界面

	// TODO::
	public void load() {

	}

	// TODO::
	public void clean() {

	}

	// TODO::
	public void cache() {

	}

	// TODO:此Tab上的所有控件disable
	public void disable() {

	}

	// TODO:此Tab上的所有控件enable
	public void enable() {

	}

	// 从这里开始设定第三tab面的内容
	public Widget getList() {
		basePanel = new HorizontalPanel();

		// 获取相应的季组
		setSeasonList();

		basePanel.add(seasonListBox);

		// 获取季组之下的相应番组设置

		seasonListBox.setSelectedIndex(0);

		this.setBanlist(0);

		basePanel.add(banTable);

		// 设定成员间距

		basePanel.setSpacing(10);

		return basePanel;
	}

	// 获取相应的季组
	private void setSeasonList() {
		seasonListBox = new ListBox(false);
		seasonListBox.setWidth("30%");

		seasonList = new ArrayList<NewSeasonType>();

		bilibiliRssServers
				.getSeasonTypeList(new AsyncCallback<List<NewSeasonType>>() {
					@Override
					public void onFailure(Throwable caught) {
						System.out.println("似乎是季组获取出错了的样子.");
					}

					@Override
					public void onSuccess(List<NewSeasonType> result) {
						for (NewSeasonType obj : result) {
							seasonListBox.addItem(obj.getSeasonName(),
									Long.toString(obj.getKey().getId()));
							seasonList.add(obj);
						}
					}
				});

		// 这里是列表点击事件
		seasonListBox.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				if (event.getNativeButton() == com.google.gwt.dom.client.NativeEvent.BUTTON_LEFT)
					setBanlist(seasonListBox.getSelectedIndex());
			}
		});
	}

	// 获取季组之下的番组
	private void setBanlist(int index) {

		banTable = new FlexTable();

		banTable.setWidth("65%");

		banTable.setText(0, 0, "选择");
		banTable.setText(0, 1, "关键字");
		banTable.setText(0, 2, "剔除字");
		banTable.setText(0, 3, "活跃");
		banTable.setText(0, 4, "修改");

		if (seasonList.size() > 0) {
			NewSeasonType st = seasonList.get(index);
			for (final SeasonBan seasonban : st.getBanlist()) {
				int row = banTable.getRowCount();
				// 选择框列
				CheckBox checkbox = new CheckBox();
				checkbox.setWidth("5%");
				checkbox.setValue(false);
				banTable.setWidget(row, 0, checkbox);

				// 关键字列
				String useName = null;
				for (String useNames : seasonban.getName()) {
					if (useName == null) {
						useName = useNames;
					} else {
						useName = useName + "," + useNames;
					}
				}
				TextBox useNameBox = new TextBox();
				useNameBox.setText(useName);
				useNameBox.setWidth("40%");
				useNameBox.setEnabled(false);
				banTable.setWidget(row, 1, useNameBox);

				// 剔除字列
				String unpassUseName = null;

				for (String unpassUseNames : seasonban.getUnpassName()) {
					if (useName == null) {
						unpassUseName = unpassUseNames;
					} else {
						unpassUseName = unpassUseName + "," + unpassUseNames;
					}
				}
				TextBox unpassUseNameBox = new TextBox();
				unpassUseNameBox.setText(useName);
				unpassUseNameBox.setWidth("40%");
				unpassUseNameBox.setEnabled(false);
				banTable.setWidget(row, 2, unpassUseNameBox);

				// 是否活跃列
				Button activeButton = new Button();
				if (seasonban.getHasActive().equals("1")) {
					activeButton.setText("活跃");
				} else {
					activeButton.setText("沉寂");
				}
				activeButton.setWidth("7%");
				banTable.setWidget(row, 3, activeButton);

				// 修改列
				Button modifyButton = new Button();
				modifyButton.setText("修改");
				modifyButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				});
				banTable.setWidget(row, 4, modifyButton);

			}
		}
	}

	
	private void openNewSeasonBanSettingBox(final SeasonBan seasonban) {
		// Create the popup dialog box
		dialogBox = new DialogBox();
		if (seasonban == null)
			dialogBox.setTitle("新增条目");
		else
			dialogBox.setTitle("修改条目");
		dialogBox.setAnimationEnabled(true);
		
		//第一排的按钮横列
		HorizontalPanel buttonPanel=new HorizontalPanel();
		buttonPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		buttonPanel.setSpacing(15);
		buttonPanel.setWidth("100%");
		buttonPanel.setHeight("25px");
		
		Button saveButton=new Button("保存");
		
		Button closeButton = new Button("关闭");
		closeButton.setFocus(true);
		
		//第二排的现有番组下拉选择横列
		HorizontalPanel listPanel=new HorizontalPanel();
		final ListBox banlistbox=new ListBox(false);
		listPanel.setWidth("100%");
		listPanel.setHeight("30px");
		listPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		bilibiliRssServers.getTempBilibiliRss(new AsyncCallback<List<TempBilibiliRss>>() {
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("番组获取失败");
			}

			@Override
			public void onSuccess(List<TempBilibiliRss> result) {
				for(TempBilibiliRss tbr:result){
					banlistbox.addItem(tbr.getTitle());
				}
			}
		});
		listPanel.add(banlistbox);
		

		//第三排的季组选择和名字确定横列
		HorizontalPanel acceptPanel=new HorizontalPanel();
		acceptPanel.setSpacing(20);
		acceptPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		acceptPanel.setHeight("50px");
		
		final ListBox seasonList=new ListBox(false);
		
		seasonList.setWidth("45%");
		
		bilibiliRssServers.getSeasonTypeList(new AsyncCallback<List<NewSeasonType>>(){
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("季组获取失败");
			}

			@Override
			public void onSuccess(List<NewSeasonType> result) {
				int i=0;
					for(NewSeasonType nst:result){
						seasonList.addItem(nst.getSeasonName(),Long.toString(nst.getKey().getId()));
						if(seasonban!=null){
							if(seasonban.getNewSeasonType().equals(nst)){
								i=result.indexOf(nst);
							}
						}
					}
					
					if(seasonban!=null){
						seasonList.setSelectedIndex(i);
					}
			}
		});
		
		//设定番组的展示名
		final TextBox useName= new TextBox();
		useName.setWidth("45%");
		banlistbox.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				String name=banlistbox.getItemText(banlistbox.getSelectedIndex());
				useName.setText(name);
			}
		});
		
		acceptPanel.add(seasonList);
		acceptPanel.add(useName);
		
		
		// 第四列的输入条目区域
		HorizontalPanel listsPanel=new HorizontalPanel();
		acceptPanel.setSpacing(20);
		acceptPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		
		final VerticalPanel newBanNamePanel = new VerticalPanel();
		if (seasonban == null) {
			for (int i = 0; i < 5; i++) {
				TextBox newLabel = new TextBox();
				newBanNamePanel.add(newLabel);
			}
		} else {
			int i = 0;
			for (String name : seasonban.getName()) {
				TextBox newLabel = new TextBox();
				newLabel.setText(name);
				newBanNamePanel.add(newLabel);
				i++;
			}
			for (; i < 5; i++) {
				TextBox newLabel = new TextBox();
				newBanNamePanel.add(newLabel);
			}
		}

		final VerticalPanel newUnpassBanNamePanel = new VerticalPanel();
		if (seasonban == null) {
			for (int i = 0; i < 5; i++) {
				TextBox newLabel = new TextBox();
				if(i==0){
					newLabel.setText("生肉");
				}
				newUnpassBanNamePanel.add(newLabel);
			}
		} else {
			int i = 0;
			for (String name : seasonban.getName()) {
				TextBox newLabel = new TextBox();
				newLabel.setText(name);
				newUnpassBanNamePanel.add(newLabel);
				i++;
			}
			for (; i < 5; i++) {
				TextBox newLabel = new TextBox();
				newUnpassBanNamePanel.add(newLabel);
			}
		}
		
		listsPanel.add(newBanNamePanel);
		listsPanel.add(newUnpassBanNamePanel);
		
		//整体面板
		VerticalPanel dialogPanel = new VerticalPanel();
		dialogPanel.add(buttonPanel);
		dialogPanel.add(listPanel);
		dialogPanel.add(acceptPanel);
		
		dialogBox.setWidget(dialogPanel);

		// 设定关闭按钮的作用
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				dialogBox.clear();
				dialogBox = null;
			}
		});
		
		//设定保存按钮的作用
		saveButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
