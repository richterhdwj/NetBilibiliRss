package my.com.NetBilibiliRss.shared.model;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

/**
 * 用于储存季度、季度名、并设定想要获取的相关季度动画的动画名和不能通过的含有名的model
 * @author 俊伟
 *
 */
public class NewSeasonType {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;//主键
	
	@Persistent
	private User user;//用户
	
    @Persistent(mappedBy = "newSeasonType")
    @Order(extensions = @Extension(vendorName="datanucleus", key="list-ordering", value="createDate desc"))
    private List<SeasonBan> banlist; //该季所属的番组筛选
	
	@Persistent
	private String seasonName;//季度名
	
	@Persistent
	private Date createDate;//保存时间
	
	@Persistent
	private String hasActive;//活跃设定（0：不活跃，1：活跃，2：半活跃）
	
	public NewSeasonType(){
		
	}

	public NewSeasonType(User user, String seasonName,Date createDate) {
		super();
		this.user = user;
		this.seasonName = seasonName;
		this.createDate = createDate;
		this.hasActive = "1";
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

	public List<SeasonBan> getBanlist() {
		return banlist;
	}

	public void setBanlist(List<SeasonBan> banlist) {
		this.banlist = banlist;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
