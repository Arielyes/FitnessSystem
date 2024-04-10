package com.hs.HealthKeepFit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hs.HealthKeepFit.bean.Subjects;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubService {
    public List<Subjects> selectAllSub(String currentPage, String pageSize);

    public long selectCountSub();

    List<Subjects> selectAllSubByCondition(String ConditionJson) throws JsonProcessingException;

    long selectCountSubByCondition(String ConditionJson) throws JsonProcessingException;

    public int delSub(String sid);

    public void addSubject(String subjectJson, MultipartFile imgFile) throws JsonProcessingException;
}
