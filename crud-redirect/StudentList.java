import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.File;
import java.io.*;

public class StudentList{
    private Scanner scanner = new Scanner(System.in);
    public List<Student> students = new ArrayList<Student>();

    public StudentList(){
        createInitialList();
    }

    public List<Student> getStudentList(){
        return students;
    }

    public void updateList(List<Student> students){
        this.students = students;
        importList(students);
    }

    public void addStudent(Student student){
        students.add(student);
    }


    public void importList(List<Student> students){
        BufferedWriter writer = null;

        try {
            File studentsSource = new File("students.txt");
            writer = new BufferedWriter(new FileWriter(studentsSource));
    
                for (Student student : students) {
                    String studentData = student.getName() + "|" + student.getSurname() + "|" + student.getAge()+ "\n";
                    writer.write(studentData);
                }
                writer.close();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createInitialList(){
        
        BufferedReader reader = null;

    try {
        File studentsSource = new File("students.txt");
        reader = new BufferedReader(new FileReader(studentsSource));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] rawData = line.split("\\|");
                String name = rawData[0];
                String surname = rawData[1];
                String ageString = rawData[2]; 
                int age = Integer.parseInt(ageString);
                Student student = new Student(name, surname, age);
                students.add(student);
            }

    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    }


    public int checkLength(){
        return students.size();
    }

}