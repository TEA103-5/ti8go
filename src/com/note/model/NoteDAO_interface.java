package com.note.model;

import java.util.*;

public interface NoteDAO_interface {
        public void insert(NoteVO noteVO);
        public void update(NoteVO noteVO);
        public void delete(Integer note_id);
        public NoteVO findByPrimaryKey(Integer note_id);
        public List<NoteVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//      public List<EmpVO> getAll(Map<String, String[]> map); 
}


