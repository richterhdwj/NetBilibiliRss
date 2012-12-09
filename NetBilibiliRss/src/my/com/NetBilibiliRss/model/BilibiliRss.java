package my.com.NetBilibiliRss.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class BilibiliRss {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private User user;
	
	@Persistent
	private String url;
	
	@Persistent
	private String name;
	
	@Persistent
	private String titleType;
	
	@Persistent
	private String season;
	
	@Persistent
	private Date createDate;
	
	@Persistent
	private boolean isWatch;
	
	//构造方法
	public BilibiliRss(){
		
	}
	
	public BilibiliRss(User user, String url, String name,
			String titleType, String season, Date createDate) {
		super();
		this.key = key;
		this.user = user;
		this.url = url;
		this.name = name;
		this.titleType = titleType;
		this.season = season;
		this.createDate = createDate;
		isWatch=false;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
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

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
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
