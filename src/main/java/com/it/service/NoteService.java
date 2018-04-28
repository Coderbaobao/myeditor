package com.it.service;

import com.it.bean.Note;

/**
 * 业务接口：站在“使用者”的角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（returm 类型/异常）
 * @author UYABOOK
 *
 */
public interface NoteService {

    int addNote(Note note);

    int deleteNote(String id);
    
    int deleteByGroupId(String id);

    int updateNote(Note note);
    
    int MoveNote(Note note);

    Note findById(String id);
        	
}
