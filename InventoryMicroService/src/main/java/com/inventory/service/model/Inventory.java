package com.inventory.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Document(collection = "Inventory-Service")
public class Inventory {
	
	 @Id
 	private String inventoryId;
	
	@NotNull(message ="Inventory Name can not be null")
	@NotEmpty
 	private String inventoryName;
	
	@NotNull(message ="Inventory Type can not be null")
 	@NotEmpty
 	private String inventoryType;
	 
	@NotNull(message="Inventory Stock can not be null")
	@Positive
	private int inventoryStock;

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public int getInventoryStock() {
		return inventoryStock;
	}

	public void setInventoryStock(int inventoryStock) {
		this.inventoryStock = inventoryStock;
	}

	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", inventoryName=" + inventoryName + ", inventoryType="
				+ inventoryType + ", inventoryStock=" + inventoryStock + "]";
	}

	public Inventory(String inventoryId,
			@NotNull(message = "Inventory Name can not be null") @NotEmpty String inventoryName,
			@NotNull(message = "Inventory Type can not be null") @NotEmpty String inventoryType,
			@NotBlank @Positive int inventoryStock) {
		super();
		this.inventoryId = inventoryId;
		this.inventoryName = inventoryName;
		this.inventoryType = inventoryType;
		this.inventoryStock = inventoryStock;
	}

	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
