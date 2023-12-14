package com.lic.epgs.policy.dto;

import java.io.Serializable;

import com.lic.epgs.policy.old.dto.PolicyFundStmtRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FmcResponseDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object response;
	private FmcRequestDto request;

}
