package my.com.NetBilibiliRss.shared.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * 用于处理获取到的页面数据的model
 * @author 俊伟
 *
 */
@PersistenceCapable
public class BilibiliRss {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private NewSeasonType newseason; //所属季
	
	@Persistent
	private User user;//用户
	
	@Persistent
	private String url;//链接
	
	@Persistent
	private String name;//名称
	
	@Persistent
	private String description;//注释
	
	@Persistent
	private String titleType;//所属动画名
	
	@Persistent
	private Date createDate;//创建日期
	
	@Persistent
	private boolean isWatch;//是否观看
	
	//构造方法
	public BilibiliRss(){
		
	}
	

	public BilibiliRss(NewSeasonType newseason, User user, String url,
			String name, String description, String titleType, String season,
			Date createDate) {
		super();
		this.newseason = newseason;
		this.user = user;
		this.url = url;
		this.name = name;
		this.description = description;
		this.titleType = titleType;
		this.createDate = createDate;
		this.isWatch = false;
	}


	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public NewSeasonType getNewseason() {
		return newseason;
	}

	public void setNewseason(NewSeasonType newseason) {
		this.newseason = newseason;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isWatch() {
		return isWatch;
	}

	public void setWatch(boolean isWatch) {
		this.isWatch = isWatch;
	}
}
