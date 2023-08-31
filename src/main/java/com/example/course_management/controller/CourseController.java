package com.example.course_management.controller;

import com.example.course_management.service.ifs.CourseService;
import com.example.course_management.vo.request.*;
import com.example.course_management.vo.response.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    // 獲取課程
    @PostMapping("/api/getCourse")
    public Map<String, Object> getCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.getCourse(courseRequest.getCourseTitle());
    }

    //獲取日程
    @PostMapping("/api/getSchedule")
    public Map<String, Object> getSchedule(@RequestBody ScheduleManagementRequest scheduleManagementRequest) {
        return courseService.getSchedule(scheduleManagementRequest.getStudentId());
    }



    //新增課程
    @PostMapping("/course/add")
    public CourseResponse addCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.addCourse(courseRequest);
    }

    //更新課程
    @PostMapping("/course/update")
    public CourseResponse updateCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(courseRequest);
    }

    //選課
    @PostMapping("/course/selection")
    public CourseResponse courseSelection(@RequestBody CourseSelectionRequest courseSelectionRequest) {
        return courseService.courseSelection(courseSelectionRequest.getStudentId(), courseSelectionRequest.getCourseCode());
    }

    //新增課程大綱
    @PostMapping("/course/schedule")
    public CourseResponse courseSchedule(@RequestBody CourseScheduleRequest courseScheduleRequest) {
        return courseService.courseSchedule(courseScheduleRequest);
    }

    //更新學員進度
    @PostMapping("/management/update")
    public CourseResponse updateManagement(@RequestBody ScheduleManagementRequest scheduleManagementRequest) {
        return courseService.updateManagement(scheduleManagementRequest);
    }
}
