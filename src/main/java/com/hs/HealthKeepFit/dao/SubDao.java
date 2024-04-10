package com.hs.HealthKeepFit.dao;

import com.hs.HealthKeepFit.bean.Condition;
import com.hs.HealthKeepFit.bean.Subjects;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDao {
    public List<Subjects> selectAllSub(Condition condition);
    public long selectCountSub();

    public List<Subjects> selectAllSubByCondition(Condition condition);

    public long selectCountSubByCondition(Condition condition);

    public int delSub(String sid);

    public int addSubject(Subjects subjects);


}
