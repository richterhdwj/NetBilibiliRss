package my.com.NetBilibiliRss.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * 用于储存季度、季度名、并设定想要获取的相关季度动画的动画名的model
 * @author 俊伟
 *
 */
@SuppressWarnings("unused")
public class NewSeasonType {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private User user;
	
	@Persistent
	private String name;
	
	@Persistent
	private String season;
	
	@Persistent
	private Date createDate;
	
	public NewSeasonType(){
		
	}

	public NewSeasonType(User user, String name, String season, Date createDate) {
		super();
		this.user = user;
		this.name = name;
		this.season = season;
		this.createDate = createDate;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
