package xin.trooper.storage.domain;

public class Test {
	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + "]";
	}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
}
