package com.springmvc.exception;

@SuppressWarnings("serial")
public class CartException extends RuntimeException {
	
	private String cartId;
	
	public CartException(String cartId) {
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}
	
}
