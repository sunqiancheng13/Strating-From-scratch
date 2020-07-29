import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Test {
    static StudentService studentService = new StudentService();
    public static void main(String[] args) {

        System.out.println("-----获取指定学生信息------");
        int sno = 101;
        Student s1 = studentService.getStudentById(sno);
        String data = JSONObject.toJSONString(s1);
        System.out.println(data);



        System.out.println("---------修改学生-------");
        Student s2 = studentService.getStudentById(101);
        System.out.println(JSONObject.toJSONString(s2));
        System.out.println("---------修改之后-------");
        Student s3 = new Student();
        s3.setSno(101);
        s3.setClassNo(9999);
        s3.setSname("我弟");
        s3.setSsex("女");
        studentService.updateStudent(s3);
        s3 = studentService.getStudentById(101);
        System.out.println(JSONObject.toJSONString(s3));

        System.out.println("---------添加之前-------");
        list();
        System.out.println("---------修改之后-------");
        Student s5 = new Student();
        s5.setSno(107);
        s5.setClassNo(9998);
        s5.setSname("弟弟");
        s5.setSsex("男");
        studentService.addStudent(s5);
        list();


        System.out.println("---------删除--------");
        int sno1 = 101;
        studentService.deletStudent(sno);
       list();
    }

    public static void list(){
        System.out.println("------获取列表--------");
        List<Student> studentList = studentService.getStudentList();
        String s = JSONObject.toJSONString(studentList);
        System.out.println(s);
    }
}
