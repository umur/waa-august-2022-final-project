## Alumni Management Portal
 
Alumni Tracking System is an online-based application that helps to tracking of college graduates. The project aims to improve current tracking procedure of college graduate and providing alumni data to college faculties. It aims to developing a web portal which will be useful for the college to monitor the alumni and for the alumni to update their current status about the job activities.

Create dashboard pages for students and faculty:
- Display the last 10 job advertisements. 
- Display 10 most recently applied job advertisements.
- Display charts.
- Add more features as you see fit.

####  Functional  Requirements
--- 
* Faculty/Students can register to the system.
* Faculty/Students can only edit their own profile information.
* Students can add job advertisements.
	* Can upload pictures/files of the job.
	* Optional: Use cloud services like Amazon S3 or Google Cloud Storage.
* Students can only edit their own job advertisements.
* Students can apply to the jobs.
	* Student's CV will be also visible to owners of the job advertisements that the student has applied.
* Students and Faculty can filter job advertisements:
	* by tags.
	* by state.
	* by city
	* by companyName.
* Faculty can filter students:
	* by state.
	* by city.
	* by major.
	* by name.
	* by student id.
* Auto-complete tags while typing.
* Faculty can write comments on students.
	* Only faculty members can see the comments.
* Students can add their professional job experiences.
*  Admin can Activate/Deactivate students and faculty.
* Admin can reset passwords.
* Students and faculty can reset their password.
	* Users should follow a password reset link.
* Use [ECharts](https://echarts.apache.org/en/index.html) to create live charts for dashboards:
	* Number of job advertisements per location. 
	* Number of students per state.
	* Number of students per city. (User should select the state.)
	* Tags.
	* Tags with location. 
	* Average time spent to find a job per gpa range.
	* Add at least 4 more charts as you see fit.
*   All delete operations should be a soft deletion.
* Log all user activities on the system.
	* Optional: Log data before/after for update operations.
* Limit login attempts. If a user try to login with an invalid password more than 5 times, the system will lock the user and make it unable to login for 15 minutes.


#### OTP
---
The system will send randomly generated code to the user's email address.

#### Notifications
---
* AppliedToTheJob: To the owner of the job advertisement when a student has applied to the job.
* NewJobPoster: To all students who are interested in job poster's tags get a notification about the job.

#### Extra Features
---
You can pass with a Honor Grade by completing all extra features.

To get extra points:

 - All features of the project must be completed.
 - Prepare a deployment-ready docker image including dummy data and all configurations needed. Your project should run with `docker-compose up` command.
 - Prepare API documentation with Swagger.
 - Make sure you use the proper fetch strategies and explain them with comments.

| Feature | Points |
|---------|--------|
|<font size="3"> Implement multi-tenant database. Shared database, seperate schema </font> | 1 point |
|<font size="3">Implement Method Level Security. </font> |1 point|
|<font size="3">Implement Attribute Based Access Control. Refer to ABAC. </font> |1 point|



#### ABAC
---
Attribute-based control models allow access decisions based not only on the identity, action, and target object but also on contextual information related to a request such as location, ip address, time etc...


#### Sample Domain Models
---
Followings are the sample domain models. You may need to modify them and create more domain models.

JobHistory:
```
tags: List<Tag>
companyName: String
startDate: LocalDateTime
endDate: LocalDateTime
reasonToLeave: String
comments: String
```

JobAdvertisement:
```
tags: List<Tag>
description: String
benefits: String
files: List<File>
```

Faculty:
```
email: String
firstName: String
lastname: String
password: String
department: Department
LastLoggedInAt: Datetime
active: Boolean
```

Student:
```
email: String
firstName: String
lastname: String
password: String
major: Department
gpa: Float
LastLoggedInAt: Datetime
active: Boolean
```

#### Technical Details
---
* Use Java and Spring Boot framework for the backend project.
* Use React framework for the frontend project.
* Use n-tier software architecture model.
* PostgreSQL is recommended as a Relational Database system.
* Populate your database with dummy data using `data.sql`.
	* Prepare your dummy data for testing and presentation.
* If necessary, change Fetch mode.


#### Submission
---
* Deadline: 08/13/2022 23:55
* Submit your project to the Sakai.
* Provide a detailed project plan for your daily performance (day/task/time).
* Fork this repository and push your changes.
* Project will be evaluated based on your code quality. It is possible that I will need to schedule meetings with some students about their source-code.

#### Important Notes
---
* You are not allowed to share codes with your classmates. If detected, you will get NC.
 
* Remember to respect the code honor submission policy. All written code must be original. Presenting something as one’s own work when it came from another source is plagiarism and is forbidden.
    
* Plagiarism is a very serious thing in all American academic institutions and is guarded against vigilantly by every professor.
 
