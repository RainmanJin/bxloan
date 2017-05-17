package com.coamctech.bxloan.web.controller.customermng;

import static com.coamctech.bxloan.commons.GlobalConstants.INDIVIDUAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coamctech.bxloan.service.customermng.WarrantyCustomerService;

@Controller
@RequestMapping("/" + "warrantyCustomer")
public class WarrantyCustomerController {
  
	@Autowired
	private WarrantyCustomerService warrantyCustomerService;
}
