package com.note.model;

import java.sql.Date;
import java.util.List;

public class NoteService {
	
	private NoteDAO_interface dao;
	
	public NoteService() {
		dao = new NoteDAO();
	}
	
	public NoteVO addNote(Integer note_classid, Date travel_start, 
			String note_title, String note_description, Integer users_id, 
			Integer trip_id, Integer note_like) {
		
		NoteVO noteVO = new NoteVO();
		
		noteVO.setNote_classid(note_classid);
		noteVO.setTravel_start(travel_start);
		noteVO.setNote_title(note_title);
		noteVO.setNote_description(note_description);
		noteVO.setUsers_id(users_id);
		noteVO.setTrip_id(trip_id);
		noteVO.setNote_like(note_like);
		dao.insert(noteVO);
		
		return noteVO;
		
	}
	
	public NoteVO updateNote(Integer note_id, Integer note_classid, Date travel_start, 
			String note_title, String note_description, Integer users_id, 
			Integer trip_id, Integer note_like) {
		
		NoteVO noteVO = new NoteVO();
		
		noteVO.setNote_id(note_id);
		noteVO.setNote_classid(note_classid);
		noteVO.setTravel_start(travel_start);
		noteVO.setNote_title(note_title);
		noteVO.setNote_description(note_description);
		noteVO.setUsers_id(users_id);
		noteVO.setTrip_id(trip_id);
		noteVO.setNote_like(note_like);
		dao.update(noteVO);
		
		return noteVO;
		
	}
	
	public void deleteNote(Integer note_id) {
		dao.delete(note_id);
	}

	public NoteVO getOneNote(Integer note_id) {
		return dao.findByPrimaryKey(note_id);
	}

	public List<NoteVO> getAll() {
		return dao.getAll();
	}

}
