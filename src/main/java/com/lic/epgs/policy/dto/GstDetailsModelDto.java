package com.lic.epgs.policy.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GstDetailsModelDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Character transactionType;
	private Character transactionSubType;
	private Date transactionDate;
	private String gstRefNo;
	private String gstRefTransactionNo;
	private String gstTransactionType;
	private Double amountWithTax;
	private Double amountWithoutTax;
	private Double cessAmount;
	private Double totalGstAmount;
	private Double gstRate;
	private Double cgstAmount;
	private Double cgstRate;
	private Double igstAmount;
	private Double igstRate;
	private Double sgstAmount;
	private Double sgstRate;
	private Double utgstAmount;
	private Double utgstRate;
	private String gstApplicableType;
	private String gstType;
	private String fromStateCode;
	private String toStateCode;
	private Timestamp createdDate;
	private String createdBy;
	private Timestamp modifiedDate;
	private Long modifiedBy;
	private Timestamp effectiveStartDate;
	private Timestamp effectiveEndDate;
	private String toGstin;
	private String gstInvoiceNo;
	private String fromGstn;
	private String hsnCode;
	private String fromPan;
	private String toPan;
	private String natureOfTransaction;
	 private String mphName;
	 private String mphAddress;
	 private String entryType;
	 private String remarks;
	 private String oldInvoiceNo;
	 private Timestamp oldInvoiceDate;
	private String year;
	private String month;

}
