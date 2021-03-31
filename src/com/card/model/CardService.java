package com.card.model;

import java.util.List;

public class CardService {
	
	private CardDAO_interface dao;
	
	public CardService() {
		dao = new CardDAO();
	}
	
	public CardVO addCard(Integer users_id, String card_number, String card_date, String card_last, Integer card_default) {
		
	CardVO cardVO = new CardVO();
	
//	cardVO.setCard_id(card_id);
	cardVO.setUsers_id(users_id);
	cardVO.setCard_number(card_number);
	cardVO.setCard_date(card_date);
	cardVO.setCard_last(card_last);
	cardVO.setCard_default(card_default);
	dao.insert(cardVO);
	
	return cardVO;
	}
	
	public CardVO updateCard( Integer users_id, Integer card_id, String card_number, String card_date, String card_last, Integer card_default) {
		// Integer card_id,
		CardVO cardVO = new CardVO();
		
		cardVO.setCard_id(card_id);
		cardVO.setUsers_id(users_id);
		cardVO.setCard_number(card_number);
		cardVO.setCard_date(card_date);
		cardVO.setCard_last(card_last);
		cardVO.setCard_default(card_default);
		dao.update(cardVO);
		
		return cardVO;	
	}	
	
	public void deleteCard(Integer card_id) {
		dao.delete(card_id);
	}
	
	public CardVO getOneCard(Integer users_id) {
		return dao.findByPK(users_id);
	}
	
	public List<CardVO> getAll() {
		return dao.getAll();
	}

}
