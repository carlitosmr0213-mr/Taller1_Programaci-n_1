package co.edu.uptc.taller1.model;

import java.util.Date;

import co.edu.uptc.taller1.enums.IdentificationTypeEnum;

import java.util.ArrayList;

public class Employee extends Person {
	 
    private double             salary;
    private String             workArea;
    private Date               hireDate;
    private String             charge;
    private Company            company;
    private ArrayList<Project> projects;
    private Contract           contract;
 
 
    public Employee(String fullName, int identificationNumber, int phone, String email,
                    IdentificationTypeEnum identificationType, String id, double salary,
                    String workArea, Date hireDate, String charge, Company company) {
        super(fullName, identificationNumber, phone, email, identificationType);
        this.id       = id;
        this.salary   = salary;
        this.workArea = workArea;
        this.hireDate = hireDate;
        this.charge   = charge;
        this.company  = company;
        this.projects = new ArrayList<>();
        this.contract = null;
    }
    
    public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getWorkArea() {
		return workArea;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public ArrayList<Project> getProjects() {
		return projects;
	}
	
	public void addProject(Project project){ 
		projects.add(project); 
		}
	
    public void removeProject(Project project) {
    	projects.remove(project); 
    }

	@Override
    public String toString() {
        return "Employee [ID=" + id + ", Nombre=" + fullName +
               ", Cargo=" + charge + ", Empresa=" +
               (company != null ? company.getCompanyName() : "N/A") + "]";
    }
}
