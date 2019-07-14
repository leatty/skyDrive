package xin.trooper.storage.domain;

public class RealFile {

	private int file_id;
	private String file_name;
	private String file_path;
	private String preview_path;
	private String file_hash;
	private int file_amount;


	@Override
	public String toString() {
		return "RealFile [file_id=" + file_id + ", file_name=" + file_name + ", file_path=" + file_path + ", file_hash="
				+ file_hash + ", file_amount=" + file_amount + "]";
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	public String getPreview_path() {
		return preview_path;
	}
	public void setPreview_path(String preview_path) {
		this.preview_path = preview_path;
	}
	public String getFile_hash() {
		return file_hash;
	}
	public void setFile_hash(String file_hash) {
		this.file_hash = file_hash;
	}
	public int getFile_amount() {
		return file_amount;
	}
	public void setFile_amount(int file_amount) {
		this.file_amount = file_amount;
	}

	
}
