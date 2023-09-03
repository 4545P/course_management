package com.example.course_management.service.impl;

import com.example.course_management.entity.*;
import com.example.course_management.repository.*;
import com.example.course_management.service.ifs.CourseService;
import com.example.course_management.vo.request.CourseRequest;
import com.example.course_management.vo.request.CourseScheduleRequest;
import com.example.course_management.vo.request.ScheduleManagementRequest;
import com.example.course_management.vo.response.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@EnableScheduling
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseSelectionDao courseSelectionDao;

    @Autowired
    private CourseScheduleDao courseScheduleDao;

    @Autowired
    private ScheduleManagementDao scheduleManagementDao;

    @Override
    public Map<String, Object> getCourse(@RequestParam(required = false) String courseTitle) {
        List<Course> courseList;
        long totalElements;

        if (courseTitle != null && !courseTitle.isEmpty()) {
            courseList = courseDao.findAllByCourseTitleContainingIgnoreCase(courseTitle);
            totalElements = courseList.size(); // 計算總數
        } else {
            // 如果沒有參數則回傳全部
            courseList = courseDao.findAll();
            totalElements = courseList.size();
        }

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
            totalElements = scheduleList.size(); // 計算總數
        } else {
            scheduleList = scheduleManagementDao.findAll();
            totalElements = scheduleList.size();
        }

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
            if (existingCourseOptional.isPresent()) {
                errorList.add(course);
            }

            for (String day : course.getCourseWeekArray()) {
                if (day == null || day.isBlank()) {
                    errorList.add(course);
                }
            }
            if (course.getCourseCode().isBlank() || course.getCourseTitle().isBlank() || course.getCourseInstructor().isBlank() || course.getCourseDescription().isBlank() || course.getCourseDate() == null || course.getCourseEndDate() == null || course.getClassTime() == null || course.getClassEndTime() == null || course.getClassCity().isBlank() || course.getPersonnel().isBlank()) {
                errorList.add(course);
            } else if(nowDate.isAfter(courseStartDate) && nowDate.isBefore(courseEndDate)) {
                course.setClassEnable(true); // 開課中
                } else {
                course.setClassEnable(false); // 未開課
            }
            course.setClassAdd(nowTime);
        }
//        String[] daysOfWeek = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
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

            if (!existingCourseOptional.isPresent()) {
                errorList.add(item);
                continue;
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
    public CourseResponse courseSelection(Integer studentId, String courseCode){

        if (studentId == null || courseCode == null) {
        return new CourseResponse("資料錯誤");
    }

        Optional<CourseSelection> CourseSelectionOptional = courseSelectionDao.findById(studentId);
        if (CourseSelectionOptional.isEmpty()) {
            return new CourseResponse("找不到學生");
        }

        CourseSelection selection = CourseSelectionOptional.get();
        // 檢查該學生是否已經選擇了一堂課
        if (selection.getCourseCode() != null) {
            return new CourseResponse("學生已經選擇了課程");
        }

        Course course = courseDao.findById(courseCode).get();
        if (course.isClassEnable() == false) {
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
            if (schedule.getCourseDate() == null || schedule.getCourseOutline().isBlank() || schedule.getCourseProject().isBlank() || schedule.getCourseContent().isBlank()) {
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
                errorList.add(item); // 如果找不到對應的資料，將其添加到錯誤列表中
            }
        }
        if (!errorList.isEmpty()) {
            return new CourseResponse("更新學員進度失敗");
        }
        scheduleManagementDao.saveAll(updateManagementList);
        return new CourseResponse("更新學員進度成功");
    }


    //每日上午八點確認課表
//    @Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 8 * * ?")
    public void courseTimeChecker() {
        System.out.println("定時任務：開始確認");

        List<Course> courseList = courseDao.findAll();

        for(Course item : courseList) {
            LocalDate courseDate = item.getCourseDate();
            LocalDate courseEndDate = item.getCourseEndDate();

            // 獲取當前日期時間
            LocalDateTime nowDateTime = LocalDateTime.now();
            LocalDateTime courseStartDateTime = courseDate.atStartOfDay();
            LocalDateTime courseEndDateTime = courseEndDate.atStartOfDay();

            // 檢查課程是否結束並處理選課表
            if (nowDateTime.isAfter(courseEndDateTime)) {
                clearCourseSelection(item.getCourseCode());
            }

            if (nowDateTime.isAfter(courseStartDateTime) && nowDateTime.isBefore(courseEndDateTime)) {
                item.setClassEnable(true); // 開課中
                item.setClassRevise(nowDateTime);
                item.setPersonnel("admin");
            } else {
                item.setClassEnable(false); // 未開課
            }
        }
        courseDao.saveAll(courseList);
    }

    private void clearCourseSelection(String courseCode) {
        List<CourseSelection> selections = courseSelectionDao.findByCourseCode(courseCode);
        for (CourseSelection selection : selections) {
            selection.setCourseCode(null); // 清空選課的課程代碼
        }
        courseSelectionDao.saveAll(selections);
    }

    //每日上午八點確認選課學生
    @Scheduled(cron = "0 0 8 * * ?")
    public void updateCourseSchedules() {
        // 查詢已選課的學生
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
