package com.staff.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Document(collection = "Staff-Service")
public class Staff {
	
	
	@Id
	private String staffId;

	@NotNull(message = "Staff Name cannnot be Null")
	@Size(min = 3, message = "Staff Name should be minimum of 3 digits")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Staff Name should only contain letters and spaces")
	private String staffName;

	@NotNull(message = "Staff Address cannnot be Null")
	@Size(min = 5, message = "Staff Address should be minimum of 5 digits")
	private String staffAddress;
	
	@NotNull(message = "Staff Role cannnot be Null")
	private String staffRole;

	@NotNull(message = "Staff Salary Can not be Null")
	private double staffSalary;

	@Positive
	private int staffAge;

	@NotNull(message = "Staff MailId cannnot be Null")
	@Size(min = 7, message = "Staff MailId should be minimum of 12 digits")
	private String staffEmail;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffAddress() {
		return staffAddress;
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	public String getStaffRole() {
		return staffRole;
	}

	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}

	public double getStaffSalary() {
		return staffSalary;
	}

	public void setStaffSalary(double staffSalary) {
		this.staffSalary = staffSalary;
	}

	public int getStaffAge() {
		return staffAge;
	}

	public void setStaffAge(int staffAge) {
		this.staffAge = staffAge;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffName=" + staffName + ", staffAddress=" + staffAddress
				+ ", staffRole=" + staffRole + ", staffSalary=" + staffSalary + ", staffAge=" + staffAge
				+ ", staffEmail=" + staffEmail + "]";
	}

	public Staff(String staffId,
			@NotNull(message = "Staff Name cannnot be Null") @Size(min = 3, message = "Staff Name should be minimum of 3 digits") @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Staff Name should only contain letters and spaces") String staffName,
			@NotNull(message = "Staff Address cannnot be Null") @Size(min = 5, message = "Staff Address should be minimum of 5 digits") String staffAddress,
			@NotNull(message = "Staff Role cannnot be Null") String staffRole,
			@NotNull(message = "Staff Salary Can not be Null") double staffSalary, @Positive int staffAge,
			@NotNull(message = "Staff MailId cannnot be Null") @Size(min = 12, message = "Staff MailId should be minimum of 12 digits") String staffEmail) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffAddress = staffAddress;
		this.staffRole = staffRole;
		this.staffSalary = staffSalary;
		this.staffAge = staffAge;
		this.staffEmail = staffEmail;
	}

	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
