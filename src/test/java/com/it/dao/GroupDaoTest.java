package com.it.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.MarkdownApplication;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = MarkdownApplication.class)
public class GroupDaoTest {

	@Autowired()
	private GroupDao groupDao;
	
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	

}
