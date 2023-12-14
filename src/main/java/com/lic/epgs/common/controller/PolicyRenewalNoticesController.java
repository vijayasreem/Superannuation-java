package com.lic.epgs.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lic.epgs.common.dto.RenewalNoticesEmailPdfFileDto;
import com.lic.epgs.common.dto.RenewalNoticesEmailRequestPayload;
import com.lic.epgs.common.service.PolicyRenewalNoticesService;
import com.lic.epgs.common.service.impl.PolicyRenewalNoticeServiceImpl;
import com.lic.epgs.integration.dto.ResponseDto;
import com.lic.epgs.integration.serviceImpl.AccountingIntegrationServiceImpl;
import com.lic.epgs.policy.entity.PolicyMasterEntity;
import com.lic.epgs.policy.repository.MphMasterRepository;
import com.lic.epgs.policy.repository.PolicyMasterRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/renewalNotice/pdf/")
public class PolicyRenewalNoticesController {

	@Autowired
	private PolicyRenewalNoticesService policyRenewalNoticesService;
	
	@Autowired
	PolicyMasterRepository policyMasterRepository;
	
	@Autowired
	MphMasterRepository masterRepository;
	
	@Autowired
	PolicyRenewalNoticeServiceImpl policyRenewalNoticeServiceImpl;
	
	@Autowired
	AccountingIntegrationServiceImpl accountingIntegrationServiceImpl;
	
	@Autowired
	private Environment environment;
	
//	@Scheduled(cron = "0 1 * * * ?")
	@GetMapping("renewlNoticeremainder")
	public String scheduleTask() throws ParseException, IOException {
		
		//email for renewal notices generation - 60 days 
		
		int day = 60;
		
		List<PolicyMasterEntity> policyMasterEntitiesList = policyMasterRepository.findPolicyRenewalDate(day);
		
		if(!policyMasterEntitiesList.isEmpty()) {
			RenewalNoticesEmailRequestPayload emailRequest = new RenewalNoticesEmailRequestPayload();
			RestTemplate restTemplate = new RestTemplate();

			for(PolicyMasterEntity policyMasterEntity: policyMasterEntitiesList) {
				
				try {
					
					String emailId=masterRepository.getEmailIdByMphId(policyMasterEntity.getMphId());
										
					String renewalnotice= policyRenewalNoticesService.renewalNoticepdf(policyMasterEntity.getPolicyId());

					ResponseDto responseUnitCodes = accountingIntegrationServiceImpl.commonmasterserviceUnitByCode(policyMasterEntity.getUnitId());
					
					String result = null;
					
					File file = new File(renewalnotice);
					  
				    byte[] data = Files.readAllBytes(file.toPath());
				    String s= Base64.getEncoder().encodeToString(data);

					RenewalNoticesEmailPdfFileDto emailPdfFileDto = new RenewalNoticesEmailPdfFileDto();
					emailPdfFileDto.setFileData(s);
					emailPdfFileDto.setFileName("Renewal Notice");
					emailPdfFileDto.setFileType("pdf");
					
//					RenewalNoticesEmailRequestPayload emailRequest = new RenewalNoticesEmailRequestPayload();
					
					if(emailId!=null) {
						emailRequest.setTo(emailId);
						}else {
							emailRequest.setTo(responseUnitCodes.getEmailId());	
						}
					emailRequest.setSubject("Renewal Notice");
					emailRequest.setEmailBody("<html>\r\n" + "<head>\r\n" + "<title>Renewal Notice</title>\r\n"
							+ "</head>\r\n" + "<body>\r\n" + "<p>Dear Policy Holder,</p>\r\n" + "\r\n" + "<p>\r\n"
							+ "Here is a reminder about the policy renewal notice,Please find the attached Document.\r\n"
							+ "</p>\r\n" + "\r\n" + "<p>Thank you,</p>\r\n" + "</body>\r\n" + "</html>");
					emailRequest.setPdfFile(emailPdfFileDto);
					
//					RestTemplate restTemplate = new RestTemplate();
//					String url = "https://email-api-dev.apps.nposepgs.licindia.com/api/email";
					String url = environment.getProperty("RENEWAL_NOTICES_EMAIL");

					try {
						new ObjectMapper().writer().writeValueAsString(emailRequest);
						result = restTemplate.postForObject(url, emailRequest, String.class);
					} catch (Exception e) {
						result = "Exception occured while sending email " + e.getMessage();
					}
				return result;

				} catch (Exception e) {
					// TODO: handle exception
				}
				String url = environment.getProperty("RENEWAL_NOTICES_REMAINDER_EMAIL_SAVE");
				new ObjectMapper().writer().writeValueAsString(emailRequest);
				if (StringUtils.isNotBlank(url)) {
					restTemplate.postForObject(url, emailRequest, String.class);

				}
			}
//			policyRenewalNoticeServiceImpl.saveEmailRequestAndResponse(emailRequest);
			
			
		}
		
		//email for renewal notices  remainder - 30 days 
		
		int days = 30;
		
		List<PolicyMasterEntity> policyMasterEntitiesLists = policyMasterRepository.findPolicyRenewalThirtyDate(days);
		
		if(!policyMasterEntitiesList.isEmpty()) {
			RenewalNoticesEmailRequestPayload emailRequest = new RenewalNoticesEmailRequestPayload();
			RestTemplate restTemplate = new RestTemplate();
			
			for(PolicyMasterEntity policyMasterEntity: policyMasterEntitiesLists) {
				
				try {
					
					String emailId=masterRepository.getEmailIdByMphId(policyMasterEntity.getMphId());
					
					
					String renewalnotice= policyRenewalNoticesService.renewalNoticepdf(policyMasterEntity.getPolicyId());

					ResponseDto responseUnitCodes = accountingIntegrationServiceImpl.commonmasterserviceUnitByCode(policyMasterEntity.getUnitId());

					
					String result = null;
					
					File file = new File(renewalnotice);
					  
				    byte[] data = Files.readAllBytes(file.toPath());
				    String s= Base64.getEncoder().encodeToString(data);

					RenewalNoticesEmailPdfFileDto emailPdfFileDto = new RenewalNoticesEmailPdfFileDto();
					emailPdfFileDto.setFileData(s);
					emailPdfFileDto.setFileName("Renewal Notice - Reminder");
					emailPdfFileDto.setFileType("pdf");
					
//					RenewalNoticesEmailRequestPayload emailRequest = new RenewalNoticesEmailRequestPayload();
					if(emailId!=null) {
					emailRequest.setTo(emailId);
					}else {
						emailRequest.setTo(responseUnitCodes.getEmailId());	
					}
					emailRequest.setSubject("Renewal Notice Reminder");
					emailRequest.setEmailBody("<html>\r\n" + "<head>\r\n" + "<title>Renewal Notice - Reminder</title>\r\n"
							+ "</head>\r\n" + "<body>\r\n" + "<p>Dear Policy Holder,</p>\r\n" + "\r\n" + "<p>\r\n"
							+ "Here is a reminder about the policy renewal notice,Please find the attached Document.\r\n"
							+ "</p>\r\n" + "\r\n" + "<p>Thank you,</p>\r\n" + "</body>\r\n" + "</html>");
					emailRequest.setPdfFile(emailPdfFileDto);
					
//					RestTemplate restTemplate = new RestTemplate();
//					String url = "https://email-api-dev.apps.nposepgs.licindia.com/api/email";
					String url = environment.getProperty("RENEWAL_NOTICES_EMAIL");
					
					try {
						new ObjectMapper().writer().writeValueAsString(emailRequest);
						result = restTemplate.postForObject(url, emailRequest, String.class);
					} catch (Exception e) {
						result = "Exception occured while sending email " + e.getMessage();
					}
				return result;

				} catch (Exception e) {
					// TODO: handle exception
				}
				
				String url = environment.getProperty("RENEWAL_NOTICES_REMAINDER_EMAIL_SAVE");
				new ObjectMapper().writer().writeValueAsString(emailRequest);
				if (StringUtils.isNotBlank(url)) {
					restTemplate.postForObject(url, emailRequest, String.class);

				}
			}
//			policyRenewalNoticeServiceImpl.saveEmailRequestAndResponse(emailRequest);
			
			

		}

		
		
		return null;
	}
	
	}
