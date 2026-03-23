package co.edu.uptc.taller1.model;

import co.edu.uptc.taller1.enums.IdentificationTypeEnum;

public class Person extends BaseClass {
	 
    protected String                 fullName;
    protected int                    identificationNumber;
    protected int                    phone;
    protected String                 email;
    protected IdentificationTypeEnum identificationType;
 
    public Person(String fullName, int identificationNumber, int phone,
                  String email, IdentificationTypeEnum identificationType) {
        this.fullName             = fullName;
        this.identificationNumber = identificationNumber;
        this.phone                = phone;
        this.email                = email;
        this.identificationType   = identificationType;
    }
    
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(int identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public IdentificationTypeEnum getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(IdentificationTypeEnum identificationType) {
		this.identificationType = identificationType;
	}
    
}
