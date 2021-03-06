package com.note_collect.model;

import java.util.List;

public interface Note_collectDAO_interface {
	public void insert(Note_collectVO note_collectVO);
	public void update(Note_collectVO note_collectVO);
	public void delete(Integer note_collect_id);
	public Note_collectVO findByPrimaryKey(Integer note_collect_id);
	public List<Note_collectVO> getAll(); 
}
