package dependencyInjectionByConstructor;

public class Projects 
{
	private int projectID;
	private String projectName;
		
	public Projects(int projectID, String projectName) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
	}
	
	public String toString() 
	{
		return projectID + " " + projectName;
	}
	
	
}
