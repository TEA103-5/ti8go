package com.card.model;

import java.util.*;

public interface cardDAO_interface {
	public void insert(cardVO cardVO);
	public void update(cardVO cardVO);
	public void delete(Integer card_id);
	public cardVO findByPK(Integer card_id);
	public List<cardVO> getAll();
}
