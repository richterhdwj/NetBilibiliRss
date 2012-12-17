package my.com.NetBilibiliRss.client.interFace;

public interface BaseSetting {
	void clean();//清除内容功能
	void load();//打开时读取功能
	void cache(); //离开时的缓存功能
}
