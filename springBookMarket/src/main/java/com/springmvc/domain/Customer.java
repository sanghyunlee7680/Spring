package com.springmvc.domain;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class Customer implements Serializable {
	
	private String customerId;			// 고객 ID
	private String name;				// 고객 이름
	private Address address;			// 고객 주소 객체
	private String phone;				// 고객 전화번호
	
	// 생성자
	public Customer() {
		this.address = new Address();
	}
	public Customer(String customerId, String name) {
		this();
		this.customerId = customerId;
		this.name = name;
	}
	
	
	// Getter & Setter
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		
		if( customerId == null ) {
			if( other.customerId != null )
				return false;
		}
		else if( !customerId.equals(other.customerId) )
			return false;
		return true;
	}
	
	
	
}
