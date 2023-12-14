package com.lic.epgs.common.service;

import java.io.IOException;

public interface PolicyRenewalNoticesService {

	String renewalNoticepdf(Long policyId)throws IOException;


}
