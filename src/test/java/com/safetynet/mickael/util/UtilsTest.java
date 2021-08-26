package com.safetynet.mickael.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.mickael.utils.PersonUtils;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UtilsTest {
	@Autowired
	MockMvc mockMvc;

	@Test
	void Calculate() throws Exception {
		int age;
		String ageParam = "24/08/2003";
		age = PersonUtils.getAge(ageParam);
		assertEquals(18, age);
		System.out.println(age);
	}

	@Test
    void Mineur() throws Exception{
    	boolean age;
    	String ageParam = "24/08/2011";
    	age = PersonUtils.isMineur(ageParam);
    			assertEquals(true, age);
    	}

}
