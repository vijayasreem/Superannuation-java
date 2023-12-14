package com.lic.epgs.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RenewalNoticesEmailRequestPayload {

	private String to;
	private String cc;
	private String subject;
	private String emailBody;
	private RenewalNoticesEmailPdfFileDto pdfFile;
//	private String transactionMessage;
//	private String transactionStatus;

}
