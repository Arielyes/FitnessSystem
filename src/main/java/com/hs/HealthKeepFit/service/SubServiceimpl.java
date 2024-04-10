package com.hs.HealthKeepFit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.HealthKeepFit.bean.Condition;
import com.hs.HealthKeepFit.bean.Subjects;
import com.hs.HealthKeepFit.dao.SubDao;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class SubServiceimpl implements SubService{

    @Autowired
    private SubDao subDao;

    @Override
    public List<Subjects> selectAllSub(String currentPageStr, String pageSizeStr) {
        int currentPage=Integer.valueOf(currentPageStr);
        int pageSize=Integer.valueOf(pageSizeStr);
        currentPage=(currentPage-1)*pageSize;

        Condition condition=new Condition();
        condition.setCurrentPage(currentPage);
        condition.setPageSize(pageSize);

        List<Subjects> list=subDao.selectAllSub(condition);
        if(list.size()==0||list==null){
            throw new RuntimeException("暂无课程，请耐心等待！");
        }

        return list;
    }

    @Override
    public long selectCountSub() {
        long total=subDao.selectCountSub();
        if(total==0){
            throw new RuntimeException("暂无课程，请耐心等待！");
        }
        return total;
    }

    @Override
    public List<Subjects> selectAllSubByCondition(String ConditionJson) throws JsonProcessingException {
        //利用jackson.jar包将json格式数据转换为对象类型
        //当json中包含的属性，对象中不包含，就会发生异常
        Condition condition= new ObjectMapper().readValue(ConditionJson,Condition.class);
        int currentPage=condition.getCurrentPage();
        int pageSize=condition.getPageSize();
        currentPage=(currentPage-1)*pageSize;
        condition.setCurrentPage(currentPage);

        List<Subjects> list=subDao.selectAllSubByCondition(condition);
        if(list.size()==0||list==null){
            throw new RuntimeException("暂无课程，请耐心等待！");
        }

        return list;
    }

    @Override
    public long selectCountSubByCondition(String ConditionJson) throws JsonProcessingException {
        //利用jackson.jar包将json格式数据转换为对象类型
        //当json中包含的属性，对象中不包含，就会发生异常
        Condition condition= new ObjectMapper().readValue(ConditionJson,Condition.class);


        long total=subDao.selectCountSubByCondition(condition);
        if(total==0){
            throw new RuntimeException("暂无课程，请耐心等待！");
        }
        return total;
    }

    @Override
    public int delSub(String sid) {
        int num=subDao.delSub(sid);
        if(num==0){
            throw new RuntimeException("删除失败");
        }

        return num;
    }

    @Override
    public void addSubject(String subjectJson, MultipartFile imgFile) throws JsonProcessingException {
        //获得文件原名称
        String imgName=imgFile.getOriginalFilename();
        //获得文件后缀名
        String newImgName=UUID.randomUUID().toString()+imgName.substring(imgName.lastIndexOf("."));
        //数据库存储地址 数据库+文件存储地址
        String targetFile="D:\\Program Files\\apache-tomcat-8180\\webapps\\images\\"+newImgName;
        //数据库存储地址
        String dataSourceFile="http://localhost:8180/images/"+newImgName;

        Subjects subjects = new ObjectMapper().readValue(subjectJson,Subjects.class);
        String sid = UUID.randomUUID().toString();
        subjects.setSid(sid);
        subjects.setTimg(dataSourceFile);
        int num = subDao.addSubject(subjects);
        if(num == 0){
            throw new RuntimeException("创建课程失败，请检查！");
        }
        //创建目标文件
        File file=new File(targetFile);
        try {
            imgFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
