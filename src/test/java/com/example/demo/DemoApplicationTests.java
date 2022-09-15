package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;


@SpringBootTest
class DemoApplicationTests {

	@Autowired
	PersonRepository personRepository;

	@Test
	public void testCountExpectSuccess(){
		assertThat(personRepository.count(), equalTo(4L));
	}

	/**
	 * This test reproduces the behaviour of https://github.com/spring-projects/spring-data-relational/issues/1323.
	 *
	 * spring-data-jdbc in version 2.4.0 is configured by default and the test runs successfully.
	 * Please change the version spring-data-jdbc to 2.4.2 in the pom.xml and run the test again. The test will fail.
	 */
	@Test
	public void testFindByComplexBindParameterExpectSuccess(){
		List<String[]> combinations = List.of(new String[]{"einstein", "albert"}, new String[]{"twain", "mark"});
		assertThat( personRepository.findLastnameByComplexBindParameter(combinations), hasItems("einstein", "twain"));
	}

}
