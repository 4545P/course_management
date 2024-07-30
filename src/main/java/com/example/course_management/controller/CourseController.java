package com.example.course_management.controller;

import com.example.course_management.service.ifs.CourseService;
import com.example.course_management.vo.request.CourseRequest;
import com.example.course_management.vo.request.CourseScheduleRequest;
import com.example.course_management.vo.request.CourseSelectionRequest;
import com.example.course_management.vo.request.ScheduleManagementRequest;
import com.example.course_management.vo.response.CourseResponse;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JI.
 * 控制課程相關操作的 REST API 控制器。
 * 允許跨域請求（CORS）。
 */
@CrossOrigin
@RestController
public class CourseController {

  private CourseService courseService;

  @Autowired
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }

  /**
   * 提交POST請求以獲取符合條件的課程資訊.
   *
   * @param courseRequest 包含課程標題的請求物件
   * @return 包含課程資訊的鍵值對
   */
  @PostMapping("/api/getCourse")
  public Map<String, Object> getCourse(@RequestBody CourseRequest courseRequest) {
    return courseService.getCourse(courseRequest.getCourseTitle());
  }

  /**
   * 提交POST請求以獲取指定學員的課程日程.
   *
   * @param scheduleManagementRequest 包含學員ID的請求物件
   * @return 包含課程日程資訊的鍵值對
   */
  @PostMapping("/api/getSchedule")
  public Map<String, Object> getSchedule(@RequestBody ScheduleManagementRequest scheduleManagementRequest) {
    return courseService.getSchedule(scheduleManagementRequest.getStudentId());
  }

  /**
   * 提交POST請求以新增課程.
   *
   * @param courseRequest 包含新增課程資訊的請求物件
   * @return 新增課程的回應物件
   */
  @PostMapping("/course/add")
  public CourseResponse addCourse(@RequestBody CourseRequest courseRequest) {
    return courseService.addCourse(courseRequest);
  }

  /**
   * 提交POST請求以更新課程.
   *
   * @param courseRequest 包含更新課程資訊的請求物件
   * @return 更新課程的回應物件
   */
  @PostMapping("/course/update")
  public CourseResponse updateCourse(@RequestBody CourseRequest courseRequest) {
    return courseService.updateCourse(courseRequest);
  }

  /**
   * 提交POST請求以進行課程選課.
   *
   * @param courseSelectionRequest 包含學員ID和課程代碼的請求物件
   * @return 課程選課的回應物件
   */
  @PostMapping("/course/selection")
  public CourseResponse courseSelection(@RequestBody CourseSelectionRequest courseSelectionRequest) {
    return courseService.courseSelection(courseSelectionRequest.getStudentId(), courseSelectionRequest.getCourseCode());
  }

  /**
   * 提交POST請求以新增課程大綱.
   *
   * @param courseScheduleRequest 包含新增課程大綱資訊的請求物件
   * @return 新增課程大綱的回應物件
   */
  @PostMapping("/course/schedule")
  public CourseResponse courseSchedule(@RequestBody CourseScheduleRequest courseScheduleRequest) {
    return courseService.courseSchedule(courseScheduleRequest);
  }

  /**
   * 提交POST請求以更新學員進度.
   *
   * @param scheduleManagementRequest 包含更新學員進度資訊的請求物件
   * @return 更新學員進度的回應物件
   */
  @PostMapping("/management/update")
  public CourseResponse updateManagement(@RequestBody ScheduleManagementRequest scheduleManagementRequest) {
    return courseService.updateManagement(scheduleManagementRequest);
  }
}
