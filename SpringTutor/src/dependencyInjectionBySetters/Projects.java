package dependencyInjectionBySetters;

public class Projects 
{
	private int projectID;
	private String projectName;
		
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String toString() 
	{
		return projectID + " " + projectName;
	}	
}
