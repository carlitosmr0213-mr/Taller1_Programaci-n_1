package co.edu.uptc.taller1.model;

import java.util.Date;

public class Contract extends BaseClass {
	 
    private Date   startDate;
    private Date   endDate;
    private String contractType;
    private double salary;
 
    public Contract(String id, Date startDate, Date endDate,
                    String contractType, double salary) {
        this.id           = id;
        this.startDate    = startDate;
        this.endDate      = endDate;
        this.contractType = contractType;
        this.salary       = salary;
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

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
    public String toString() {
        return "Contract [ID=" + id + ", Tipo=" + contractType +
               ", Salario=" + salary + "]";
    }
}
