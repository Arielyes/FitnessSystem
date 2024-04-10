package com.hs.HealthKeepFit.controller;

import com.hs.HealthKeepFit.bean.Result;
import com.hs.HealthKeepFit.bean.Subjects;
import com.hs.HealthKeepFit.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class SubController {

    @Autowired
    private SubService subService;

    @Autowired
    private Result result;

    @RequestMapping("selectAllSub")
    @ResponseBody
    public Result selectAllSub(String currentPage,String pageSize){
        try {
            List<Subjects> list=subService.selectAllSub(currentPage,pageSize);
            long total=subService.selectCountSub();
            result.setStatue(true);
            result.setList(list);
            result.setTotal(total);
        }catch (Exception e){
            result.setStatue(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping("selectAllSubByCondition")
    @ResponseBody
    public Result selectAllSubByCondition(String conditionJson){
        try {
            List<Subjects> list=subService.selectAllSubByCondition(conditionJson);
            long total=subService.selectCountSubByCondition(conditionJson);
            result.setStatue(true);
            result.setList(list);
            result.setTotal(total);
        }catch (Exception e){
            result.setStatue(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping("delSub")
    @ResponseBody
    public Result delSub(String sid){
        try {
            subService.delSub(sid);
            result.setStatue(true);
        }catch (Exception e){
            result.setStatue(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping("/addSubject")
    @ResponseBody
    public Result addSubject(String subjectJson, MultipartFile imgFile){
        try{
            subService.addSubject(subjectJson,imgFile);
            result.setStatue(true);
        }catch (Exception e){
            result.setStatue(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
