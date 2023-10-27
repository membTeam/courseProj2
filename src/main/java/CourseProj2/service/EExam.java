package CourseProj2.service;

public enum EExam { JAVA("java"), MATH("math");

    private String strExam;
    EExam(String arg){
        strExam = arg;
    }

    public String getStr(){
        return strExam;
    }
}
