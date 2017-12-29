package com.ssm.controller;

import com.ssm.service.StudentService;
import com.ssm.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/28.
 */
@Controller
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @ResponseBody
    @RequestMapping(value = "/findAllStudents")
    public AjaxResult findAllStudents() {
        return new AjaxResult(studentService.findAllStudent());
    }


}
