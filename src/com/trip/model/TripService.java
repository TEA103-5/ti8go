package com.trip.model;

import java.sql.Date;

import com.team.model.TeamVO;
import com.trip_detail.model.Trip_detailVO;
import java.util.List;
import java.util.Set;

public class TripService {
	private TripDAO_interface dao;

	public TripService() {
		dao = new TripDAO();
	}

	public TripVO addEmp(Integer users_id, Integer last_editor, Integer trip_state, Integer read_authority, Integer edit_authority,
			String trip_area, Date trip_start, Date trip_end, String trip_name, String trip_description,
			String trip_type, Integer trip_tot_cost, String place_weather) {

		TripVO tripVO = new TripVO();

		tripVO.setUsers_id(users_id);
		 tripVO.setLast_editor(last_editor);
		 tripVO.setTrip_state(trip_state);
		 tripVO.setRead_authority(read_authority);
		 tripVO.setEdit_authority(edit_authority);
		 tripVO.setTrip_area(trip_area);
		 tripVO.setTrip_start(trip_start);
		 tripVO.setTrip_end(trip_end);
		 tripVO.setTrip_name(trip_name);
		 tripVO.setTrip_description(trip_description);
		 tripVO.setTrip_type(trip_type);
		 tripVO.setTrip_tot_cost(trip_tot_cost);
		 tripVO.setPlace_weather(place_weather);

		dao.insert(tripVO);

		return tripVO;
	}

	public TripVO updateEmp(Integer trip_id,Integer users_id, Integer last_editor, Integer trip_state, Integer read_authority, Integer edit_authority,
			String trip_area, Date trip_start, Date trip_end, String trip_name, String trip_description,
			String trip_type, Integer trip_tot_cost, String place_weather) {

		TripVO tripVO = new TripVO();
		
		tripVO.setTrip_id(trip_id);
		tripVO.setUsers_id(users_id);
		 tripVO.setLast_editor(last_editor);
		 tripVO.setTrip_state(trip_state);
		 tripVO.setRead_authority(read_authority);
		 tripVO.setEdit_authority(edit_authority);
		 tripVO.setTrip_area(trip_area);
		 tripVO.setTrip_start(trip_start);
		 tripVO.setTrip_end(trip_end);
		 tripVO.setTrip_name(trip_name);
		 tripVO.setTrip_description(trip_description);
		 tripVO.setTrip_type(trip_type);
		 tripVO.setTrip_tot_cost(trip_tot_cost);
		 tripVO.setPlace_weather(place_weather);
		
		 dao.update(tripVO);

		return tripVO;
	}

	public void deleteEmp(Integer trip_id) {
		dao.delete(trip_id);
	}

	public TripVO getOneEmp(Integer trip_id) {
		return dao.findByPrimaryKey(trip_id);
	}

	public List<TripVO> getAll() {
		return dao.getAll();
	}
	
	public Set<Trip_detailVO> getDetailByTrip(Integer trip_id) {
		return dao.getDetailByTrip(trip_id);
	}
	public Set<TeamVO> getTeamByTrip(Integer trip_id) {
		return dao.getTeamByTrip(trip_id);
	}

}
