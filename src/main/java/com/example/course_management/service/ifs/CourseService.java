package com.example.course_management.service.ifs;

import com.example.course_management.entity.ScheduleManagement;
import com.example.course_management.vo.request.CourseRequest;
import com.example.course_management.vo.request.CourseScheduleRequest;
import com.example.course_management.vo.request.PersonnelRequest;
import com.example.course_management.vo.request.ScheduleManagementRequest;
import com.example.course_management.vo.response.CourseResponse;
import com.example.course_management.vo.response.PersonnelResponse;

import java.util.List;
import java.util.Map;

public interface CourseService {

    public Map<String, Object> getCourse(String courseTitle);

    public Map<String, Object> getSchedule(String studentId);

    public CourseResponse addCourse(CourseRequest courseRequest);

    public CourseResponse updateCourse(CourseRequest courseRequest);

    public CourseResponse deleteCourse(String courseId);

    public CourseResponse courseSelection(Integer studentId, String courseId);

    public CourseResponse courseSchedule(CourseScheduleRequest courseScheduleRequest);

    public CourseResponse updateManagement(ScheduleManagementRequest scheduleManagementRequest);

}
