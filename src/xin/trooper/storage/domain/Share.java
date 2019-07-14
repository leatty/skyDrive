package xin.trooper.storage.domain;

import java.sql.Timestamp;

public class Share {
	
	private int share_id;
	private int user_id;
	private int virfile_id;
	private String share_name;
	private Timestamp share_date;
	private String share_password;
	
	
	@Override
	public String toString() {
		return "Share [share_id=" + share_id + ", user_id=" + user_id + ", virfile_id=" + virfile_id + ", share_name="
				+ share_name + ", share_date=" + share_date + ", share_password=" + share_password + "]";
	}
	public int getShare_id() {
		return share_id;
	}
	public void setShare_id(int share_id) {
		this.share_id = share_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getVirfile_id() {
		return virfile_id;
	}
	public void setVirfile_id(int virfile_id) {
		this.virfile_id = virfile_id;
	}
	public Timestamp getShare_date() {
		return share_date;
	}
	public void setShare_date(Timestamp share_date) {
		this.share_date = share_date;
	}
	public String getShare_password() {
		return share_password;
	}
	public void setShare_password(String share_password) {
		this.share_password = share_password;
	}
	public String getShare_name() {
		return share_name;
	}
	public void setShare_name(String share_name) {
		this.share_name = share_name;
	}
	

	
}
