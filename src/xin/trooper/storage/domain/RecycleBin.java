package xin.trooper.storage.domain;

import java.sql.Timestamp;

public class RecycleBin {

	private int garbage_id;
	private Timestamp delete_date;
	private String virfile_name;
	private String virfile_ext;
	private String virfile_path;
	private Long virfile_size;
	private Timestamp upload_date;
	private int file_id;
	private int user_id;
	
	@Override
	public String toString() {
		return "RecycleBin [garbage_id=" + garbage_id + ", delete_date=" + delete_date + ", virfile_name="
				+ virfile_name + ", virfile_ext=" + virfile_ext + ", virfile_path=" + virfile_path + ", virfile_size="
				+ virfile_size + ", upload_date=" + upload_date + ", file_id=" + file_id + ", user_id=" + user_id + "]";
	}
	
	public int getGarbage_id() {
		return garbage_id;
	}
	public void setGarbage_id(int garbage_id) {
		this.garbage_id = garbage_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getVirfile_name() {
		return virfile_name;
	}
	public void setVirfile_name(String virfile_name) {
		this.virfile_name = virfile_name;
	}
	
	public String getVirfile_ext() {
		return virfile_ext;
	}
	public void setVirfile_ext(String virfile_ext) {
		this.virfile_ext = virfile_ext;
	}
	public String getVirfile_path() {
		return virfile_path;
	}
	public void setVirfile_path(String virfile_path) {
		this.virfile_path = virfile_path;
	}
	public Long getVirfile_size() {
		return virfile_size;
	}
	public void setVirfile_size(Long virfile_size) {
		this.virfile_size = virfile_size;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public Timestamp getDelete_date() {
		return delete_date;
	}
	public void setDelete_date(Timestamp delete_date) {
		this.delete_date = delete_date;
	}
	public Timestamp getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Timestamp upload_date) {
		this.upload_date = upload_date;
	}

}
