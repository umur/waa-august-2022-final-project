package com.example.demo.UtilityClasses;

import com.example.demo.entity.*;
import com.example.demo.model.*;

public final class Mapper {

    public static Profile ConvertModelToProfile(ProfileModel profileModel){
        Profile profile = new Profile();
        profile.setFirstName(profileModel.getFirstName());
        profile.setLastName(profileModel.getLastName());
        profile.setProfileKClockId(profileModel.getProfileKClockId());
//        profile.setPassword(profileModel.getPassword());
//        profile.setEmail(profileModel.getEmail());
        profile.setProfileType(profileModel.getProfileType());
        profile.setDeleted(profileModel.isDeleted());

        return profile;
    }

    public static ProfileModel ConvertProfileToModel(Profile profile){
        ProfileModel profileModel = new ProfileModel();
        profileModel.setFirstName(profile.getFirstName());
        profileModel.setLastName(profile.getLastName());
        profileModel.setProfileKClockId(profile.getProfileKClockId());
//        profileModel.setPassword(profile.getPassword());
//        profileModel.setEmail(profile.getEmail());
        profileModel.setProfileType(profile.getProfileType());
        profileModel.setDeleted(profile.isDeleted());
        return profileModel;
    }

    public static Faculty ConvertModelToFaculty(FacultyModel facultyModel){
        Faculty faculty = new Faculty();
        faculty.setFacultyKClockId(facultyModel.getFacultyKClockId());
        faculty.setProfile(facultyModel.getProfile());
//        faculty.setDepartment(facultyModel.getDepartment());

        return faculty;
    }

    public static FacultyModel ConvertFacultyToModel(Faculty faculty){
        FacultyModel facultyModel = new FacultyModel();
        facultyModel.setFacultyKClockId(faculty.getFacultyKClockId());
        facultyModel.setProfile(faculty.getProfile());
//        facultyModel.setDepartment(faculty.getDepartment());
        return facultyModel;
    }

    public static Student ConvertModelToStudent(StudentModel studentModel){
        Student student = new Student();
        student.setGpa(studentModel.getGpa());
        student.setStudentKClockId(studentModel.getStudentKClockId());
        student.setProfile(studentModel.getProfile());
        student.setDepartment(studentModel.getDepartment());
//        student.setJobHistoryList(studentModel.getJobHistoryList());
        return student;
    }

    public static StudentModel ConvertStudentToModel(Student student){
        StudentModel studentModel = new StudentModel();
        studentModel.setGpa(student.getGpa());
        studentModel.setStudentKClockId(student.getStudentKClockId());
        studentModel.setProfile(student.getProfile());
        studentModel.setDepartment(student.getDepartment());
//        studentModel.setJobHistoryList(student.getJobHistoryList());

        return studentModel;
    }

    public static Department ConvertModelToDepartment(DepartmentModel departmentModel){
        Department department = new Department();
        department.setName(departmentModel.getName());

        return department;
    }

    public static DepartmentModel ConvertDepartmentToModel(Department department){
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setName(department.getName());

        return departmentModel;
    }

    public static JobHistory ConvertModelToJobHistory(JobHistoryModel jobHistoryModel){
        JobHistory jobHistory = new JobHistory();
        jobHistory.setComments(jobHistoryModel.getComments());
        jobHistory.setCompanyName(jobHistoryModel.getCompanyName());
        jobHistory.setEndDate(jobHistoryModel.getEndDate());
        jobHistory.setReasonToLeave(jobHistoryModel.getReasonToLeave());
        jobHistory.setStartDate(jobHistoryModel.getStartDate());



        return jobHistory;
    }

    public static JobHistoryModel ConvertJobHistoryToModel(JobHistory jobHistory){
        JobHistoryModel jobHistoryModel = new JobHistoryModel();

        jobHistoryModel.setComments(jobHistory.getComments());
        jobHistoryModel.setCompanyName(jobHistory.getCompanyName());
        jobHistoryModel.setEndDate(jobHistory.getEndDate());
        jobHistoryModel.setReasonToLeave(jobHistory.getReasonToLeave());
        jobHistoryModel.setStartDate(jobHistory.getStartDate());

        return jobHistoryModel;
    }

    public static JobAdvertisement ConvertModelToJobAdvertisement(JobAdvertisementModel jobAdvertisementModel){
        JobAdvertisement jobAdvertisement = new JobAdvertisement();

        jobAdvertisement.setBenefits(jobAdvertisementModel.getBenefits());
        jobAdvertisement.setDescription(jobAdvertisementModel.getDescription());

        return jobAdvertisement;
    }

    public static JobAdvertisementModel ConvertJobAdvertisementToModel(JobAdvertisement jobAdvertisement){
        JobAdvertisementModel jobAdvertisementModel = new JobAdvertisementModel();

        jobAdvertisementModel.setBenefits(jobAdvertisement.getBenefits());
        jobAdvertisementModel.setDescription(jobAdvertisement.getDescription());

        return jobAdvertisementModel;
    }

    public static Comment ConvertModelToComment(CommentModel commentModel){
        Comment comment = new Comment();

        comment.setCommentDetails(commentModel.getCommentDetails());
        comment.setFaculty(commentModel.getFaculty());
        comment.setStudent(commentModel.getStudent());

        return comment;
    }

    public static CommentModel ConvertCommentToModel(Comment comment){
        CommentModel commentModel = new CommentModel();

        commentModel.setCommentDetails(comment.getCommentDetails());
        commentModel.setFaculty(comment.getFaculty());
        commentModel.setStudent(comment.getStudent());

        return commentModel;
    }
}
