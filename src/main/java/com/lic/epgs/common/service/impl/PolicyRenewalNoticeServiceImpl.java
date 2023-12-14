package com.lic.epgs.common.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fasterxml.jackson.databind.JsonNode;
import com.lic.epgs.common.dto.RenewalNoticesEmailRequestPayload;
import com.lic.epgs.common.integration.service.IntegrationService;
import com.lic.epgs.common.service.PolicyRenewalNoticesService;
import com.lic.epgs.integration.dto.ResponseDto;
import com.lic.epgs.integration.serviceImpl.AccountingIntegrationServiceImpl;
import com.lic.epgs.policy.entity.MphAddressEntity;
import com.lic.epgs.policy.entity.MphBankEntity;
import com.lic.epgs.policy.entity.MphMasterEntity;
import com.lic.epgs.policy.repository.MphAddressRepository;
import com.lic.epgs.policy.repository.MphBankRepository;
import com.lic.epgs.policy.repository.MphMasterRepository;
import com.lic.epgs.policy.repository.PolicyMasterRepository;
import com.lic.epgs.utils.DateUtils;
import com.lic.epgs.utils.NumericUtils;
import com.lowagie.text.DocumentException;


@Service
public class PolicyRenewalNoticeServiceImpl implements PolicyRenewalNoticesService{

	@Autowired
	PolicyMasterRepository policyMasterRepository;
	
	@Autowired
	MphBankRepository mphBankRepository;
	
	@Autowired
	IntegrationService integrationService;
	
	@Autowired
	MphAddressRepository mphAddressRepository;
	
	@Autowired
	MphMasterRepository masterRepository;
	
	@Autowired
	AccountingIntegrationServiceImpl accountingIntegrationServiceImpl;
	
	@Value("${temp.pdf.location}")
	private String tempPdfLocation;
	
	
	@Override
	public String renewalNoticepdf(Long policyId) throws IOException {

		boolean showLogo = true;
		String reportBody = "";
		String reportStyles = "";
		String htmlContent2 = "</div></body></html>"; // DO NOT DISTRUB THIS LINE	
		reportBody=renewalnoticepdf(policyId) + htmlContent2;;
		
		String completehtmlContent = "<!DOCTYPE  html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
				+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/><title>LIC ePGS</title><meta name=\"author\" content=\"LIC PNGS\"/><meta name=\"keywords\" content=\"Customer Communication\"/><meta name=\"description\" content=\"Report/Letter/Contract\"/><style type=\"text/css\"> body{border-width:0px;\r\n"
				+ "border-style:solid;\r\n" + "border-color:#586ec5;} * {margin:0; padding:0; text-indent:0; }"
				+ " .s1 { color: #2E5396; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 16pt; }"
				+ " .s2 { color: #2E3599; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 12pt; }"
				+ " p { color: black; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 10pt; margin:0pt; }"
				+ " .a { color: black; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 12pt; }"
				+ " .s3 { color: black; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 12pt; }"
				+ " .s5 { color: #2E3599; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: bold; text-decoration: none; font-size: 12pt; }"
				+ " .s6 { color: #120000; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: bold; text-decoration: none; font-size: 12pt; }"
				+ " .s7 { color: black; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 12pt; }"
				+ " .s8 { color: black; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 12pt; vertical-align: -2pt; }"
				+ " .s9 { color: black; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 12pt; }"
				+ " h1 { color: #120000; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: bold; text-decoration: none; font-size: 12pt; }"
				+ " .s10 { color: #2D3499; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: bold; text-decoration: none; font-size: 12pt; }"
				+ " .pt10 { padding-top: 10pt; }" + " .pb10 { padding-bottom: 10pt; }"
				+ " .pb50 { padding-bottom: 50pt; }"
				+ " table, tbody, td {vertical-align: top; overflow: visible; color: black; font-family:\"Times New Roman\", serif; font-style: normal; font-weight: normal; text-decoration: none; font-size: 10pt; margin:0pt; } "
				// Uncomment below line to add watermark to the pdf
				// + " .watermark {\r\n background-image: url(\"data:image/png;base64," +
				// licWatermark + "\");} "
				+ " " + reportStyles + " " + " </style></head><body><div id=\"bg\" class=\"watermark\">"
				+ "<p style=\"padding-left: 5pt;text-indent: 0pt;text-align: left;\"/>";

//		if (showLogo) {
//			completehtmlContent = completehtmlContent
//					+ "<p style=\"text-indent: 0pt;text-align: center;\"><span><table style=\"width:100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr style=\"display:flex;justify-content:space-between;align-items:center\"><td style=\"padding-left:24pt\"><img width=\"100\" height=\"63\" src=\"data:image/jpg;base64,"
//					+ licLogo + "\"/></td>" + "</tr></table></span></p>";
//		}
		completehtmlContent = completehtmlContent + "<p style=\"text-indent: 0pt;text-align: left;\"><br/></p>"
				+ reportBody + "";

		String htmlFileLoc = tempPdfLocation + "/" + policyId + ".html";
		FileWriter fw = new FileWriter(htmlFileLoc);
		fw.write(completehtmlContent);
		fw.close();

		FileOutputStream fileOutputStream = new FileOutputStream(tempPdfLocation + "/" + policyId + ".pdf");
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(new File(htmlFileLoc));
		renderer.layout();
		try {
			renderer.createPDF(fileOutputStream, false);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		renderer.finishPDF();

		if (new File(htmlFileLoc).exists()) {
			new File(htmlFileLoc).delete();
		}

		return tempPdfLocation + "/" + policyId + ".pdf";

	}


	private String renewalnoticepdf(Long policyId) throws IOException {
		
		Object result = policyMasterRepository.findByPolicyIdForRenewal(policyId);
		
		Object[] obj = (Object[]) result;
			
		String mphName= masterRepository.getNameByMphId(NumericUtils.convertStringToLong(String.valueOf(obj[2])));
		
		MphBankEntity mphBankEntity = mphBankRepository.findByMphId(NumericUtils.convertStringToLong(String.valueOf(obj[2])));
		
		String address = mphAddressRepository.getAddressByMphId(NumericUtils.convertStringToLong(String.valueOf(obj[2])));
		
		Date ardDate = DateUtils.stringToDateYYYYMMDDHHMMSSHyphen(String.valueOf(obj[4]));
		
		Date currentDate = new Date();
		
		JsonNode response = integrationService.getProductDetailsByProductId(NumericUtils.convertStringToLong(String.valueOf(obj[3])));
		String product = null;
		if (response != null) {
			JsonNode proposeDetails = response.path("responseData");
			 product = proposeDetails.path("productCode").textValue();
			
		}
		ResponseDto responseUnitCodes = accountingIntegrationServiceImpl.commonmasterserviceUnitByCode(String.valueOf(obj[6]));

		
		InputStream islicLogo = new ClassPathResource("liclogo.png").getInputStream();
		// Uncomment below line to add watermark to the pdf
		// InputStream islicWatermark = new
		// ClassPathResource("watermark.png").getInputStream();
		byte[] bytesLogo = IOUtils.toByteArray(islicLogo);
		// Uncomment below line to add watermark to the pdf
		// byte[] bytesWatermark = IOUtils.toByteArray(islicWatermark);

		String licLogo = Base64.getEncoder().encodeToString(bytesLogo);
		// Uncomment below line to add watermark to the pdf
		// String licWatermark = Base64.getEncoder().encodeToString(bytesWatermark);
		
		
		
		String reportBody =
				"<div style=\" margin: 10px; padding: 10px 25px; border: 1px solid black;\">"
				+ "  <div>"
				+ "    <span style=\"width: 30%;\">"
				+"<img width=\"186px\" height=\"95px\" padding-left=\"28px\" padding-top=\"12px\"  src=\"data:image/jpg;base64,"+ licLogo + "\"/>"
//				+ "      <img src=\"../assets/img/logo.png\" alt=\"lic-logo\""
//				+ "        style=\"height: 95px; width: 186px; padding-left: 28px; padding-top: 12px;\">"
				+ "    </span>"
				+ "    <span style=\"  width:70%; position:fixed; padding-left: 40%; padding-top:5px;\">"
				+ "      <p><b>Life Insurance Corporation of India</b></p>"
				+ "      <p><b>Pensions &amp; Group Schemes Unit</b></p>"
				+ "      <p>"+responseUnitCodes.getAddress1()+"</p>"
				+ "      <p>Phone nos:"+responseUnitCodes.getTelephoneNo()+"</p>"
				+ "      <p>Email id:"+responseUnitCodes.getEmailId()+"</p>"
				+ "    </span>"
				+ "  </div>"
				+ "  <div style=\"border-top: 1px solid black;\"></div>"
				+ "  <div style=\" padding-top: 10px;\">"
				+ "    <div style=\"width: 50%;\">"
				+ "      Ref: P&amp;GS/"+product+"/ "+String.valueOf( obj[1])+ " "
				+ "    </div>"
				+ "    <div style=\"width: 50%; text-align: end;position:fixed;padding-left: 80%;\">"
				+ "      Date:"+  DateUtils.dateToStringFormatDDMMYYYYSlash(currentDate)
				+ "    </div>"
				+ "  </div>"
				+"<p> &nbsp; </p>"
				+ "  <div>"
				+ "    <p>TO</p>"
				+ "    <p style=\"padding-left: 40px;\">"
				+ "      <b>Trustees,"+mphName+"</b>"
				+ "    </p>"
				+ "    <p style=\"padding-left: 40px;\">"
				+ "      <b>Address1"+address+"</b>"
				+ "    </p>"
				+ "  </div>"
				+"<p> &nbsp; </p>"
				+ "  <div>"
				+ "    <p>Dear Sir,</p>"
				+ "    <div style=\"  padding-left: 110px;\">"
				+ "      <p>"
				+ "        <b>"
				+ "          Re:&nbsp;&nbsp;&nbsp;&nbsp; Renewal of Master Policy No. "+product+"/ "+String.valueOf( obj[1])
				+ "        </b>"
				+ "      </p>"
				+"<p> &nbsp; </p>"
				+ "      <p style=\"padding-left: 44px;\">"
				+ "        <b>"
				+ "          Annual Renewal Date :"+ DateUtils.dateToStringFormatDDMMYYYYSlash(ardDate)
				+ "        </b>"
				+ "      </p>"
				+ "    </div>"
				+"<p> &nbsp; </p>"
				+ "    <p>"
				+ "      Greetings from Life Insurance Corporation of India! We are happy to have you as our valuable customer. We find"
				+ "      that the above scheme is due for renewal as on ARD. We would like to reiterate the fact that no new entrants are"
				+ "      allowed to join this scheme."
				+ "    </p>"
				+"<p> &nbsp; </p>"
				+ "    <p>"
				+ "      To enable us to renew the scheme we request you to send the following requirements:"
				+ "    </p>"
				+"<p> &nbsp; </p>"
				+ "    <div style=\"padding-left: 28px;\">"
				+ "      <p>1. &nbsp;&nbsp; Data of the of existing members as a hard/soft copy in an excel file in the following format:"
				+ "      </p>"
				+"<p> &nbsp; </p>"
				+ "      <table style=\"margin-left: 15px; border-collapse: collapse; width: 100%; border: 1px solid black;\">"
				+ "        <thead>"
				+ "          <tr>"
				+ "            <td style=\"border: 1px solid black;\">Policy No</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;LIC ID</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;Emp No</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;Emp Name</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;Gender</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;Category</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;DOB</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;Date of Appointment</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;Eligible Salary</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;Employer Contribution</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;Employee Contribution</td>"
				+ ""
				+ "          </tr>"
				+ "        </thead>"
				+ "        <tbody>"
				+ "          <tr>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "          </tr>"
				+ "        </tbody>"
				+ "      </table>"
				+ "    </div>"
				+ ""
				+"<p> &nbsp; </p>"
				+ "    <div style=\"padding-left: 28px;\">"
				+ "      <p>2. &nbsp;&nbsp; The remittance of total contributions may be made by NEFT/RTGS to our bank account as detailed"
				+ "        below: </p>"
				+"<p> &nbsp; </p>"
				+ "      <table style=\"margin-left: 27px; border-collapse: collapse; width: 40%; border: 1px solid black;\">"
				+ "        <tr>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp; Name of the Bank</td>"
				+ "          <td style=\"border: 1px solid black;\">"+mphBankEntity.getBankName() +"</td>"
				+ "        </tr>"
				+ "        <tr>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp; Bank Branch</td>"
				+ "          <td style=\"border: 1px solid black;\">"+mphBankEntity.getBankBranch() +"</td>"
				+ "        </tr>"
				+ "        <tr>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp; Account Number</td>"
				+ "          <td style=\"border: 1px solid black;\">"+mphBankEntity.getAccountNumber() +"</td>"
				+ "        </tr>"
				+ "        <tr>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp; IFS Code</td>"
				+ "          <td style=\"border: 1px solid black;\">"+mphBankEntity.getIfscCode() +"</td>"
				+ "        </tr>"
				+ "      </table>"
				+ "    </div>"
				+"<p> &nbsp; </p>"
				+ ""
				+ "    <div style=\"padding-left: 28px;\">"
				+ "      <p>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; Confirmation of remittance has to be sent to us to the email id mentioned above"
				+ "        giving the following information : </p>"
				+"<p> &nbsp; </p>"
				+ "      <table style=\"margin-left: 27px; border-collapse: collapse; width: 70%; border: 1px solid black;\">"
				+ "        <tr>"
				+ "          <td style=\"border: 1px solid black;\"> UTR Number</td>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp; Date of remittance</td>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp; Policy number</td>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp; Type of scheme</td>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp; Amount</td>"
				+ "        </tr>"
				+ "        <tr>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp; </td>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "          <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "        </tr>"
				+ "      </table>"
				+ "    </div>"
				+"<p> &nbsp; </p>"
				+ ""
				+ "    <div style=\"padding-left: 28px;\">"
				+ "      <p>3. &nbsp;&nbsp; Particulars of members ceasing to be in service by way of Resignation/ Retirement/Death during"
				+ "        the last policy year, and particulars of those eligible members for whom claims are not lodged with us during"
				+ "        the last policy year"
				+ "        in the following format."
				+ ""
				+ "      </p>"
				+"<p> &nbsp; </p>"
				+ "      <div>"
				+ "        <table style=\"margin-left: 27px; border-collapse: collapse; width: 60%; border: 1px solid black;\">"
				+ "          <tr>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp; Policy no</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp; LIC id</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp; Emp no</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp; Name</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp; Date of exit</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp; Cause of exit</td>"
				+ "          </tr>"
				+ "          <tr>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp; </td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "            <td style=\"border: 1px solid black;\">&nbsp;</td>"
				+ "          </tr>"
				+ "        </table>"
				+ "      </div>"
				+"<p> &nbsp; </p>"
				+ ""
				+ "    </div>"
				+ "    <p>"
				+ "      In case you have sent any of the above particulars to us, kindly let us know the date and mode of sending the"
				+ "      same."
				+ "    </p>"
				+"<p> &nbsp; </p>"
				+ "    <p>"
				+ "      Thanking you and assuring you of our best services always."
				+ "    </p>"
				+"<p> &nbsp; </p>"
				+ "    <p>"
				+ "      <b>"
				+ "        Yours Faithfully,"
				+ "      </b>"
				+ "    </p>"
				+"<p> &nbsp; </p>"
				+ "    <p>"
				+ ""
				+ "    </p>"
				+ "    <p>"
				+ "      <b>"
				+ "        Manager (P &amp; GS)"
				+ "      </b>"
				+ "    </p>"
				+ ""
				+ "  </div>"
				+ "</div>";
		return reportBody;

	}


//	public void saveEmailRequestAndResponse(RenewalNoticesEmailRequestPayload emailRequest) {
//		// TODO Auto-generated method stub
//		
//	}

	
		
}
	