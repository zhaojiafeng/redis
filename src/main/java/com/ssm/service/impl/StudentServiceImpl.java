package com.ssm.service.impl;

import com.ssm.bean.Student;
import com.ssm.mapper.StudentMapper;
import com.ssm.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/12/28.
 */
@Service
public class StudentServiceImpl implements StudentService {


    @Resource
    private StudentMapper studentMapper;


    @Override
    public List<Student> findAllStudent() {
        return studentMapper.findAllStudent();
    }


}
