package com.it.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.MarkdownApplication;
import com.it.bean.Note;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = MarkdownApplication.class)
public class NoteDaoTest {

	@Autowired()
	private NoteDao noteDao;
	
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		Note findById = noteDao.findById("1fda7640-383e-4fac-961a-3a85dd1317ef");
		System.out.println(findById);
	}

}
