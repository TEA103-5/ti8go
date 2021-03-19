package com.card.model;

import java.util.*;

public interface CardDAO_interface {
			public void insert(CardVO cardVO);
			public void update(CardVO cardVO);
			public void delete(Integer card_id);
			public CardVO findByPK(Integer card_id);
			public List<CardVO> getAll();
}
