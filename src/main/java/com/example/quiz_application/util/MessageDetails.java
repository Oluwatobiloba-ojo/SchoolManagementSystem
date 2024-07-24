package com.example.quiz_application.util;


import com.example.quiz_application.dtos.request.StudentInvitationRequest;
import com.example.quiz_application.dtos.request.TeacherInvitationRequestMessage;

import static com.example.quiz_application.util.AppUtils.TEACHER_REGISTRATION_URL;


public class MessageDetails {

    public static String TEACHER_INVITATION_EMAIL(TeacherInvitationRequestMessage request){
        String teacherUrl = TEACHER_REGISTRATION_URL+"/token="+request.getToken();
           return  String.format("""
                   <!DOCTYPE>
                   <head>
                   <title>Quiz Application</title>
                   </head>
                   <body>
                   <div>
                   <h1>Invitation to Join The Quiz Application Platform For %s As A Teacher</h1>
                   <p>Hello %s</p>
                   <p>We are excited to invite you to join our quiz application as a teacher! As an educator, your expertise and guidance are invaluable in creating engaging and educational quizzes for students.</p>
                                 
                   <p>With our platform you can: </p>
                   <ul>
                              <li>Create custom quizzes tailored to your curriculum</li>
                              <li>Assign quizzes to your students for assessment</li>
                              <li>Track student progress and performance</li>
                              <li>Access a library of pre-made quizzes on various subjects</li>
                   </ul>
                   <p>To get started, simply click on the link below to create your teacher account:</p>
                   <a href="%s" target="_blank"><button>Click OOOOO</button></a>
                   <p>If you have any questions or need assistance, feel free to reach out to our support team at [Your Support Email]. We look forward to having you on board!</p>           
                   <p>%s<p>
                   </div>
                   </body>
                   """,request.getInstituteName(), request.getTeacherEmail(), teacherUrl, request.getInstituteAddress());
    }
    public static String STUDENT_INVITATION_EMAIL(StudentInvitationRequest request){
        String studentUrl = AppUtils.STUDENT_REGISTRATION_LINK+"token="+request.getToken();
           return  String.format("""
                   <!DOCTYPE>
                   <head>
                   <title>Quiz Application</title>
                   </head>
                   <body>
                   <div>
                   <h1>Invitation to Join The Quiz Application Platform For %s As A Student On Their Platform</h1>
                   <p>Hello %s</p>
                   <p>We are thrilled to invite you to join our quiz application! With our platform, you can access a wide range of quizzes on various subjects, test your knowledge, and track your progress.</p>
                                 
                   <p>As a Student You can: </p>
                   <ul style="text-align: left;">
                                            <li>Take quizzes assigned by your teachers</li>
                                            <li>Review quiz results and feedback</li>
                                            <li>Explore quizzes from the library</li>
                                        </ul>
                   <p>To get started, simply click on the link below to create your student account:</p>
                   <a href="%s" target="_blank"><button>Click OOOOO</button></a>
                   <p>If you have any questions or need assistance, feel free to reach out to our support team at [Your Support Email]. We look forward to having you on board!</p>           
                   <p>%s<p>
                   </div>
                   </body>
                   """,request.getInstituteName(), request.getStudentEmail(), studentUrl, request.getInstituteAddress());
    }
}
