package co.edu.uptc.taller1.model;

public class Company extends BaseClass {
	 
    private String companyName;
    private String address;
    private String phone;
    private String nit;
 
    public Company(String id, String companyName, String address,
                   String phone, String nit) {
        this.id          = id;
        this.companyName = companyName;
        this.address     = address;
        this.phone       = phone;
        this.nit         = nit;
    }

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	@Override
    public String toString() {
        return "Company [ID=" + id + ", Nombre=" + companyName +
               ", NIT=" + nit + ", Tel=" + phone + "]";
    }
}