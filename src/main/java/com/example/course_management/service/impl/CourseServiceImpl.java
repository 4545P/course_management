package com.example.course_management.service.impl;


import com.example.course_management.entity.Course;
import com.example.course_management.entity.CourseSchedule;
import com.example.course_management.entity.CourseSelection;
import com.example.course_management.entity.ScheduleManagement;
import com.example.course_management.repository.CourseDao;
import com.example.course_management.repository.CourseScheduleDao;
import com.example.course_management.repository.CourseSelectionDao;
import com.example.course_management.repository.ScheduleManagementDao;
import com.example.course_management.service.ifs.CourseService;
import com.example.course_management.vo.request.CourseRequest;
import com.example.course_management.vo.request.CourseScheduleRequest;
import com.example.course_management.vo.request.ScheduleManagementRequest;
import com.example.course_management.vo.response.CourseResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * JI.
 * 課程服務的實現類，同時啟用了計劃任務（Scheduling）。
 */
@EnableScheduling
@Service
public class CourseServiceImpl implements CourseService {

  private CourseDao courseDao;

  private CourseSelectionDao courseSelectionDao;

  private CourseScheduleDao courseScheduleDao;

  private ScheduleManagementDao scheduleManagementDao;

  @Autowired
  public void setCourseDao(CourseDao courseDao) {
    this.courseDao = courseDao;
  }

  @Autowired
  public void setCourseSelectionDao(CourseSelectionDao courseSelectionDao) {
    this.courseSelectionDao = courseSelectionDao;
  }

  @Autowired
  public void setCourseScheduleDao(CourseScheduleDao courseScheduleDao) {
    this.courseScheduleDao = courseScheduleDao;
  }

  @Autowired
  public void setScheduleManagementDao(ScheduleManagementDao scheduleManagementDao) {
    this.scheduleManagementDao = scheduleManagementDao;
  }

  @Override
  public Map<String, Object> getCourse(@RequestParam(required = false) String courseTitle) {
    List<Course> courseList;
    long totalElements;
    if (courseTitle != null && !courseTitle.isEmpty()) {
      courseList = courseDao.findAllByCourseTitleContainingIgnoreCase(courseTitle);
    } else {
      // 如果沒有參數則回傳全部
      courseList = courseDao.findAll();
    }
    totalElements = courseList.size(); // 計算總數
    List<Map<String, Object>> resultList = getMapCourse(courseList);
    Map<String, Object> result = new HashMap<>();
    result.put("list", resultList);
    result.put("total", totalElements);
    return result;
  }

  private static List<Map<String, Object>> getMapCourse(List<Course> courseList) {
    List<Map<String, Object>> responses = new ArrayList<>();
    for (Course course : courseList) {
      Map<String, Object> response = new HashMap<>();
      response.put("courseCode", course.getCourseCode());
      response.put("courseTitle", course.getCourseTitle());
      response.put("courseInstructor", course.getCourseInstructor());
      response.put("courseDescription", course.getCourseDescription());
      response.put("courseWeek", course.getCourseWeekArray());
      response.put("courseDate", course.getCourseDate());
      response.put("courseEndDate", course.getCourseEndDate());
      response.put("classTime", course.getClassTime());
      response.put("classEndTime", course.getClassEndTime());
      response.put("classCity", course.getClassCity());
      response.put("enable", course.isClassEnable());
      responses.add(response);
    }
    return responses;
  }

  @Override
  public Map<String, Object> getSchedule(@RequestParam(required = false) String studentId) {
    List<ScheduleManagement> scheduleList;
    long totalElements;
    if (studentId != null) {
      scheduleList = scheduleManagementDao.findAllByStudentIdContainingIgnoreCase(studentId);
    } else {
      scheduleList = scheduleManagementDao.findAll();
    }
    // 計算總數
    totalElements = scheduleList.size();
    List<Map<String, Object>> resultList = getMapSchedule(scheduleList);
    Map<String, Object> result = new HashMap<>();
    result.put("list", resultList);
    result.put("total", totalElements);
    return result;
  }

  private static List<Map<String, Object>> getMapSchedule(List<ScheduleManagement> scheduleList) {
    List<Map<String, Object>> responses = new ArrayList<>();
    for (ScheduleManagement schedule : scheduleList) {
      Map<String, Object> response = new HashMap<>();
      response.put("studentId", schedule.getStudentId());
      response.put("courseCode", schedule.getCourseCode());
      response.put("courseOutline", schedule.getCourseOutline());
      response.put("courseProject", schedule.getCourseProject());
      response.put("understand", schedule.isUnderstand());
      response.put("question", schedule.getQuestion());
      response.put("solve", schedule.isSolve());
      responses.add(response);
    }
    return responses;
  }


  @Override
  public CourseResponse addCourse(CourseRequest courseRequest) {
    List<Course> courseList = courseRequest.getCourseList();
    List<Course> errorList = new ArrayList<>();
    LocalDate nowDate = LocalDate.now();
    LocalDateTime nowTime = LocalDateTime.now();
    for (Course course : courseList) {
      LocalDate courseStartDate = course.getCourseDate();
      LocalDate courseEndDate = course.getCourseEndDate();
      Optional<Course> existingCourseOptional = courseDao.findById(course.getCourseCode());
      if (existingCourseOptional.isPresent()
          || course.getCourseCode().isBlank()
          || course.getCourseTitle().isBlank()
          || course.getCourseInstructor().isBlank()
          || course.getCourseDescription().isBlank()
          || course.getCourseDate() == null
          || course.getCourseEndDate() == null
          || course.getClassTime() == null
          || course.getClassEndTime() == null
          || course.getClassCity().isBlank()
          || course.getPersonnel().isBlank()) {
        errorList.add(course);
        // 可以在此處避免重複進行後續操作
        continue;
      }
      for (String day : course.getCourseWeekArray()) {
        if (day == null || day.isBlank()) {
          errorList.add(course);
          // 在找到無效 day 後立即退出迴圈
          break;
        }
      }
      if (!errorList.contains(course)) {
        course.setClassEnable(nowDate.isAfter(courseStartDate) && nowDate.isBefore(courseEndDate));
        course.setClassAdd(nowTime);
      }
    }
    if (!errorList.isEmpty()) {
      return new CourseResponse(errorList, "新增課程失敗");
    }
    courseDao.saveAll(courseList);
    return new CourseResponse(courseList, "新增課程成功");
  }

  @Override
  public CourseResponse updateCourse(CourseRequest courseRequest) {
    List<Course> courseList = courseRequest.getCourseList();
    List<Course> errorList = new ArrayList<>();
    List<Course> updateCourseList = new ArrayList<>();
    for (Course item : courseList) {
      String courseCode = item.getCourseCode();
      Optional<Course> existingCourseOptional = courseDao.findById(courseCode);
      if (existingCourseOptional.isEmpty()) {
        errorList.add(item);
      } else {
        Course existingCourse = existingCourseOptional.get();
        if (StringUtils.hasText(item.getCourseCode())) {
          existingCourse.setCourseCode(item.getCourseCode());
        }
        if (StringUtils.hasText(item.getCourseTitle())) {
          existingCourse.setCourseTitle(item.getCourseTitle());
        }
        if (StringUtils.hasText(item.getCourseInstructor())) {
          existingCourse.setCourseInstructor(item.getCourseInstructor());
        }
        if (StringUtils.hasText(item.getCourseDescription())) {
          existingCourse.setCourseDescription(item.getCourseDescription());
        }
        if (item.getCourseWeekArray() != null && item.getCourseWeekArray().length > 0) {
          existingCourse.setCourseWeek(item.getCourseWeekArray());
        }
        if (item.getCourseDate() != null) {
          existingCourse.setCourseDate(item.getCourseDate());
        }
        if (item.getCourseEndDate() != null) {
          existingCourse.setCourseEndDate(item.getCourseEndDate());
        }
        if (item.getClassTime() != null) {
          existingCourse.setClassTime(item.getClassTime());
        }
        if (item.getClassEndTime() != null) {
          existingCourse.setClassEndTime(item.getClassEndTime());
        }
        if (StringUtils.hasText(item.getClassCity())) {
          existingCourse.setClassCity(item.getClassCity());
        }
        updateCourseList.add(existingCourse);
      }
    }
    if (!errorList.isEmpty()) {
      return new CourseResponse(errorList, "更新課程失敗");
    }
    courseDao.saveAll(updateCourseList);
    return new CourseResponse(updateCourseList, "更新課程成功");
  }


  @Override
  public CourseResponse deleteCourse(String courseId) {
    return null;
  }

  @Override
  public CourseResponse courseSelection(Integer studentId, String courseCode) {
    if (studentId == null || courseCode == null) {
      return new CourseResponse("資料錯誤");
    }
    // 查詢學生的選課記錄
    Optional<CourseSelection> courseSelectionOptional = courseSelectionDao.findById(studentId);
    if (courseSelectionOptional.isEmpty()) {
      return new CourseResponse("找不到學生");
    }
    CourseSelection selection = courseSelectionOptional.get();
    // 檢查該學生是否已經選擇了一堂課
    if (selection.getCourseCode() != null) {
      return new CourseResponse("學生已經選擇了課程");
    }
    // 查詢課程是否存在並可選擇
    Optional<Course> courseOptional = courseDao.findById(courseCode);
    if (courseOptional.isEmpty()) {
      return new CourseResponse(courseCode, "找不到該課程");
    }
    Course course = courseOptional.get();
    if (!course.isClassEnable()) {
      return new CourseResponse(courseCode, "本門課程無法選擇");
    }
    // 創建 CourseSelection 實體並設定屬性
    CourseSelection courseSelection = new CourseSelection();
    courseSelection.setStudentId(studentId);
    courseSelection.setName(selection.getName());
    courseSelection.setCourseCode(courseCode);
    courseSelectionDao.save(courseSelection);
    return new CourseResponse(courseCode, "選課成功");
  }

  @Override
  public CourseResponse courseSchedule(CourseScheduleRequest courseScheduleRequest) {
    List<CourseSchedule> scheduleList = courseScheduleRequest.getScheduleList();
    List<CourseSchedule> errorList = new ArrayList<>();
    for (CourseSchedule schedule : scheduleList) {
      if (schedule.getCourseDate() == null
          || schedule.getCourseOutline().isBlank()
          || schedule.getCourseProject().isBlank()
          || schedule.getCourseContent().isBlank()) {
        errorList.add(schedule);
      }
    }
    if (!errorList.isEmpty()) {
      return new CourseResponse("新增課程日程失敗", errorList);
    }
    courseScheduleDao.saveAll(scheduleList);
    return new CourseResponse("新增課程日程成功", scheduleList);
  }


  @Override
  public CourseResponse updateManagement(ScheduleManagementRequest scheduleManagementRequest) {
    List<ScheduleManagement> managementList = scheduleManagementRequest.getManagementList();
    List<ScheduleManagement> errorList = new ArrayList<>();
    List<ScheduleManagement> updateManagementList = new ArrayList<>();
    String studentId = scheduleManagementRequest.getManagementList().get(0).getStudentId();
    String courseCode = scheduleManagementRequest.getManagementList().get(0).getCourseCode();
    String courseOutline = scheduleManagementRequest.getManagementList().get(0).getCourseOutline();
    Optional<ScheduleManagement> existingScheduleManagementOptional =
            scheduleManagementDao.findByStudentIdAndCourseCodeAndCourseOutline(studentId, courseCode, courseOutline);
    for (ScheduleManagement item : managementList) {

      if (existingScheduleManagementOptional.isPresent()) {
        ScheduleManagement existingScheduleManagement = existingScheduleManagementOptional.get();
        // 更新屬性
        existingScheduleManagement.setCourseOutline(item.getCourseOutline());
        existingScheduleManagement.setCourseProject(item.getCourseProject());
        existingScheduleManagement.setUnderstand(item.isUnderstand());
        existingScheduleManagement.setQuestion(item.getQuestion());
        existingScheduleManagement.setSolve(item.isSolve());
        // 儲存回資料庫
        scheduleManagementDao.save(existingScheduleManagement);
        // 將更新後的對象添加到列表中，如果需要
        updateManagementList.add(existingScheduleManagement);
      } else {
        // 如果找不到對應的資料，將其添加到錯誤列表中
        errorList.add(item);
      }
    }
    if (!errorList.isEmpty()) {
      return new CourseResponse("更新學員進度失敗");
    }
    scheduleManagementDao.saveAll(updateManagementList);
    return new CourseResponse("更新學員進度成功");
  }


  /**
   * 定時任務：每日上午八點確認課表.
   * <p>
   * 此方法使用定時任務機制，每日定時執行，用於確認並更新課程安排。
   * 檢查課程是否已結束，並根據當前日期時間更新課程狀態及相關選課表。
   * 如果課程已結束，清空該課程的所有選課表。
   * 如果當前時間在課程開始和結束時間之間，設置課程為開啟狀態，並更新課程的修訂時間和負責人。
   * 如果當前時間在課程開始時間之前或課程結束時間之後，設置課程為關閉狀態。
   * </p>
   */
  @Scheduled(cron = "0 0 8 * * ?")
  public void courseTimeChecker() {
    System.out.println("定時任務：開始確認");
    List<Course> courseList = courseDao.findAll();
    for (Course item : courseList) {
      LocalDate courseDate = item.getCourseDate();
      LocalDate courseEndDate = item.getCourseEndDate();
      LocalDateTime nowDateTime = LocalDateTime.now();
      LocalDateTime courseStartDateTime = courseDate.atStartOfDay();
      LocalDateTime courseEndDateTime = courseEndDate.atStartOfDay();
      if (nowDateTime.isAfter(courseEndDateTime)) {
        clearCourseSelection(item.getCourseCode());
      }
      if (nowDateTime.isAfter(courseStartDateTime) && nowDateTime.isBefore(courseEndDateTime)) {
        // 開課中
        item.setClassEnable(true);
        item.setClassRevise(nowDateTime);
        item.setPersonnel("admin");
      } else {
        // 未開課
        item.setClassEnable(false);
      }
    }
    courseDao.saveAll(courseList);
  }

  /**
   * 清空指定課程的所有選課表.
   * <p>
   * 根據課程代碼查找所有選課表項目，將它們的課程代碼設置為空並保存到數據庫。
   * </p>
   *
   * @param courseCode 課程代碼
   */
  private void clearCourseSelection(String courseCode) {
    List<CourseSelection> selections = courseSelectionDao.findByCourseCode(courseCode);
    for (CourseSelection selection : selections) {
      // 清空選課的課程代碼
      selection.setCourseCode(null);
    }
    courseSelectionDao.saveAll(selections);
  }

  /**
   * 每日上午八點執行的定時任務，用於更新選課學生的課程進度管理.
   * <p>
   * 查詢所有已選課的學生，檢查他們是否有相應的課程進度管理紀錄，如無則創建預設進度管理紀錄。
   * </p>
   */
  @Scheduled(cron = "0 0 8 * * ?")
  public void updateCourseSchedules() {
    List<CourseSelection> selectedCourses = courseSelectionDao.findStudentsWithSelectedCourses();
    for (CourseSelection studentCourse : selectedCourses) {
      Integer studentId = studentCourse.getStudentId();
      String courseCode = studentCourse.getCourseCode();
      CourseSchedule course = courseScheduleDao.findByCourseCode(courseCode);
      String courseProject = course.getCourseProject();
      //檢查選課的學生是否有進度管理紀錄
      ScheduleManagement existingDefaultSchedule = scheduleManagementDao.findByStudentIdAndCourseCodeAndCourseProject(
              studentId.toString(), courseCode, courseProject
      );
      //如果沒有紀錄則創建預設進度管理紀錄
      if (existingDefaultSchedule == null) {
        ScheduleManagement defaultSchedule = createDefaultSchedule(studentId, courseCode, courseProject);
        scheduleManagementDao.save(defaultSchedule);
      }
    }
  }

  private ScheduleManagement createDefaultSchedule(Integer studentId, String courseCode, String courseProject) {
    CourseSchedule course = courseScheduleDao.findByCourseCodeAndCourseProject(courseCode, courseProject);
    String courseOutline = course.getCourseOutline();
    ScheduleManagement defaultSchedule = new ScheduleManagement();
    defaultSchedule.setStudentId(studentId.toString());
    defaultSchedule.setCourseCode(courseCode);
    defaultSchedule.setCourseOutline(courseOutline);
    defaultSchedule.setCourseProject(courseProject);
    defaultSchedule.setUnderstand(false);
    defaultSchedule.setQuestion(null);
    defaultSchedule.setSolve(false);
    return defaultSchedule;
  }
}
