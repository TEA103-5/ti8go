package com.trip.model;

import java.util.List;
import java.util.Set;

import com.group_activities.model.Group_activitiesVO;
import com.team.model.TeamVO;
import com.trip_detail.model.Trip_detailVO;

public interface TripDAO_interface {
    public void insert(TripVO tripVO);
    public void update(TripVO tripVO);
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
