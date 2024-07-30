package com.example.course_management.service.ifs;

import com.example.course_management.vo.request.CourseRequest;
import com.example.course_management.vo.request.CourseScheduleRequest;
import com.example.course_management.vo.request.ScheduleManagementRequest;
import com.example.course_management.vo.response.CourseResponse;
import java.util.Map;

/**
 * JI.
 * 課程服務接口，定義了課程相關操作的方法。
 */
public interface CourseService {

  Map<String, Object> getCourse(String courseTitle);

  Map<String, Object> getSchedule(String studentId);

  CourseResponse addCourse(CourseRequest courseRequest);

  CourseResponse updateCourse(CourseRequest courseRequest);

  CourseResponse deleteCourse(String courseId);

  CourseResponse courseSelection(Integer studentId, String courseId);

  CourseResponse courseSchedule(CourseScheduleRequest courseScheduleRequest);

  CourseResponse updateManagement(ScheduleManagementRequest scheduleManagementRequest);
}
