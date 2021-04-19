package com.note_collect.model;

import java.util.List;


import com.note_collect.model.Note_collectVO;

public interface Note_collectDAO_interface {
	public void insert(Note_collectVO note_collectVO);
//	public void update(Note_collectVO note_collectVO);
	public void delete(Integer note_id , Integer users_id);
	public Note_collectVO findByPrimaryKey(Integer note_id , Integer users_id);
	public List<Note_collectVO> getAll();
	
	public List<Note_collectVO> getAllByPrimaryKey(Integer users_id);
}

