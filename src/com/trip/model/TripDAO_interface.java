package com.trip.model;

import java.util.List;
import java.util.Set;

import com.group_activities.model.Group_activitiesVO;
import com.team.model.TeamVO;
import com.trip_detail.model.Trip_detailVO;

public interface TripDAO_interface {
	public TripVO insert2(TripVO tripVO);
    public void insert(TripVO tripVO);
    public void update(TripVO tripVO);
    public void updateLook(Integer trip_id,Integer look);
    public void updateDay(String day,Integer trip_id,Integer read_authority);
    public void delT(Integer trip_id);
    public void delete(Integer trip_id);
    public TripVO findByPrimaryKey(Integer trip_id);
    public List<TripVO> getAll();
  //查詢某部門的員工(一對多)(回傳 Set)
    public Set<Trip_detailVO> getDetailByTrip(Integer trip_id);
    public Set<TeamVO> getTeamByTrip(Integer trip_id);
    public Set<Group_activitiesVO> getActivitiesByTrip(Integer trip_id);

    public void insertWithTrip_detail(TripVO tripVO , List<Trip_detailVO> list);
    public void insertWithTeam(TripVO tripVO , List<TeamVO> list);
    public void insertWithTrip_detail_and_Team(TripVO tripVO , List<Trip_detailVO> list, List<TeamVO> list1);

}
