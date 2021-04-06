package com.note_collect.model;

import java.util.List;


public class Note_collectService {

	private Note_collectDAO_interface dao;

	public Note_collectService() {
		dao = new Note_collectDAO();
	}

	public Note_collectVO addNote_collect(Integer note_id, Integer users_id) {

		Note_collectVO note_collectVO = new Note_collectVO();

		note_collectVO.setNote_id(note_id);
		note_collectVO.setUsers_id(users_id);
		dao.insert(note_collectVO);

		return note_collectVO;
	}

	public void deleteNote_collect(Integer note_id, Integer users_id) {
		dao.delete(note_id, users_id);
	}

	public Note_collectVO getOneNote_collect(Integer note_id, Integer users_id) {
		return dao.findByPrimaryKey(note_id, users_id);
	}

	public List<Note_collectVO> getAll() {
		return dao.getAll();
	}

	public List<Note_collectVO> getAllByPrimaryKey(Integer users_id) {
		return dao.getAllByPrimaryKey(users_id);
	}

}
