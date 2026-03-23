package co.edu.uptc.taller1.model;

import java.util.Date;

import co.edu.uptc.taller1.logic.BaseClass;

public class Project extends BaseClass {
	 
    private String projectName;
    private Date   startDate;
    private Date   endDate;
    private double budget;
 
    public Project(String id, String projectName,
                   Date startDate, Date endDate, double budget) {
        this.id          = id;
        this.projectName = projectName;
        this.startDate   = startDate;
        this.endDate     = endDate;
        this.budget      = budget;
    }

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	@Override
    public String toString() {
        return "Project [ID=" + id + ", Nombre=" + projectName +
               ", Presupuesto=" + budget + "]";
    }
}
