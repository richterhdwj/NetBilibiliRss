package my.com.NetBilibiliRss.shared.model;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

public class SeasonBan {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;//主键
	
	@Persistent
	private NewSeasonType newSeasonType; //所属季
	
	@Persistent
	private List<String> name;//采用名，可以多个
	
	@Persistent
	private List<String> unpassName;//不采用名，也可以多个
	
	@Persistent
	private Date createDate;//保存时间

	@Persistent
	private String hasActive;//是否活跃

	public SeasonBan() {
		
	}

	public SeasonBan(NewSeasonType newSeasonType, List<String> name,
			List<String> unpassName, Date createDate, String hasActive) {
		super();
		this.newSeasonType = newSeasonType;
		this.name = name;
		this.unpassName = unpassName;
		this.createDate = createDate;
		this.hasActive = hasActive;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public NewSeasonType getNewSeasonType() {
		return newSeasonType;
	}

	public void setNewSeasonType(NewSeasonType newSeasonType) {
		this.newSeasonType = newSeasonType;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getUnpassName() {
		return unpassName;
	}

	public void setUnpassName(List<String> unpassName) {
		this.unpassName = unpassName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getHasActive() {
		return hasActive;
	}

	public void setHasActive(String hasActive) {
		this.hasActive = hasActive;
	}
}
