package com.davidalzate.inventory_service.model.dtos;

public record BaseResponse(String[] erroMessages) {
	
	public boolean hasError() {
		return erroMessages != null && erroMessages.length > 0;
	}

}
