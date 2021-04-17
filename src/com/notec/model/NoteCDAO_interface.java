package com.notec.model;

import java.util.List;



public interface NoteCDAO_interface {
	
        public void insert(NoteCVO noteCVO);
        public void update(NoteCVO noteCVO);
        public void delete(Integer note_C_id);
        public NoteCVO findByPrimaryKey(Integer note_C_id);
        public List<NoteCVO> getAll();
        

        public NoteCVO togetoneNote(Integer note_id);
        public List<NoteCVO> getAllNoteC(Integer note_id);
        

}

