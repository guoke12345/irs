package pojo;

public class Profile {
	private int id;
	private String profileName;
	private String profileContent;
	
	
	
	public Profile() {
	}
	public Profile(int id, String profileName, String profileContent) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.profileContent = profileContent;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProfileContent() {
		return profileContent;
	}
	public void setProfileContent(String profileContent) {
		this.profileContent = profileContent;
	}
	
	
}
