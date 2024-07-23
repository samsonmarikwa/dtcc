package com.dtcc.retailer;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SelectPackages("com.dtcc.retailer")
@Suite
@SuiteDisplayName("Retailer Application Test Suite")
class RetailerApplicationTests {

	@Test
	void contextLoads() {
	}

}
