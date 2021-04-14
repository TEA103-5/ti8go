package com.notec.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class NoteCService {
	
	private NoteCDAO_interface dao;
	
	public NoteCService() {
		dao = new NoteCDAO();
	}
	
	public NoteCVO addNoteC(String note_c_title, String note_c_content, byte[] note_c_img, 
			Integer note_id) {
		
		NoteCVO noteCVO = new NoteCVO();
		
		noteCVO.setNote_c_title(note_c_title);
		noteCVO.setNote_c_content(note_c_content);
		noteCVO.setNote_c_img(note_c_img);
		noteCVO.setNote_id(note_id);
	
		dao.insert(noteCVO);
		
		return noteCVO;
		
	}
	
	public NoteCVO updateNoteC(Integer note_c_id,String note_c_title, String note_c_content, 
			byte[] note_c_img, Integer note_id) {
		
		NoteCVO noteCVO = new NoteCVO();
		
		noteCVO.setNote_c_id(note_c_id);
		noteCVO.setNote_c_title(note_c_title);
		noteCVO.setNote_c_content(note_c_content);
		noteCVO.setNote_c_img(note_c_img);
		noteCVO.setNote_id(note_id);
		dao.update(noteCVO);
		
		return noteCVO;
		
	}
	
	public void deleteNoteC(Integer note_c_id) {
		dao.delete(note_c_id);
	}

	public NoteCVO getOneNoteC(Integer note_c_id) {
		return dao.findByPrimaryKey(note_c_id);
	}
	
	public NoteCVO togetoneNote(Integer note_id) {
		return dao.togetoneNote(note_id);
	}

	public List<NoteCVO> getAll() {
		return dao.getAll();
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];  // available源頭的資料量(類似length長度)
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("Output/2.png");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
	 public List<NoteCVO> getAllNoteC(Integer note_id){
		 return dao.getAllNoteC(note_id);
	 };

}
