package source.client;

import java.util.ArrayList;
import java.util.Vector;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import source.controller.Controller;
import source.model.student.Student;
import source.model.subject.Subject;
import source.model.teacher.Teacher;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ArrayList<String> mainMenu;
    private Controller controller;

    public static String breakConnection = "0";
    public static String showStudents = "1";
    public static String showTeachers = "2";
    public static String showSubjects = "3";
    public static String addStudent = "4";
    public static String addTeacher = "5";
    public static String addSubject = "6";

    private ArrayList<String> getMainMenuList() 
    {
        ArrayList<String> mainMenu = new ArrayList<>();

        mainMenu.add("1 - Show the students.");
        mainMenu.add("2 - Show the teachers.");
        mainMenu.add("3 - Show the subjects.");
        mainMenu.add("4 - Add a student.");
        mainMenu.add("5 - Add a teacher.");
        mainMenu.add("6 - Add a subject.");
        mainMenu.add("0 - Close the application.");

        return mainMenu;
    }

    public ClientHandler(Socket socket, Controller controller) 
    {
        this.socket = socket;
        this.mainMenu = this.getMainMenuList();
        this.controller = controller;

        try 
        {
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } 
        catch (IOException e) 
        {
            this.closeConnection();
            e.printStackTrace(System.out);
        }
    }

    private void closeConnection() 
    {
        try 
        {
            if (this.socket != null)
                this.socket.close();

            if (this.bufferedReader != null)
                this.bufferedReader.close();

            if (this.bufferedWriter != null)
                this.bufferedWriter.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        System.out.println("A client has disconnected.");
    }

    private void sendStringToClient(String string) throws IOException
    {
        this.bufferedWriter.write(String.valueOf(string));
        this.bufferedWriter.newLine();
        this.bufferedWriter.flush();
    }

    private void sendVectorToClient(Vector<?> vector) throws IOException
    {
        this.sendStringToClient(String.valueOf(vector.size()));
        for (Object object : vector)
            this.sendStringToClient(object.toString());
    }

    private Student getStudentDataFromUser() throws IOException
    {
        String studentName = this.bufferedReader.readLine();
        Integer studentID = Integer.parseInt(this.bufferedReader.readLine());
        Integer groupID = Integer.parseInt(this.bufferedReader.readLine());

        return new Student(studentName, studentID, groupID);
    }

    private Object getNameAndIDFromUser(Object object) throws IOException
    {
        String name = this.bufferedReader.readLine();
        Integer id = Integer.parseInt(this.bufferedReader.readLine());

        if (object instanceof Teacher)
            return new Teacher(name, id);

        return new Subject(name, id);
    }

    @Override
    public void run() 
    {
        try 
        {
            Integer menuSize = this.mainMenu.size();
            this.sendStringToClient(String.valueOf(menuSize));

            for (String menuEntry : this.mainMenu) 
                this.sendStringToClient(menuEntry);
            

            while (this.socket.isClosed() == false) 
            {
                String clientChoice = this.bufferedReader.readLine();

                if (clientChoice.equals(breakConnection)) 
                {
                    this.closeConnection();
                    break;
                }
                else if (clientChoice.equals(showStudents))
                {
                    Vector<Student> students = this.controller.getStudents();
                    this.sendVectorToClient(students);
                }
                else if (clientChoice.equals(showTeachers))
                {
                    Vector<Teacher> teachers = this.controller.getTeachers();
                    this.sendVectorToClient(teachers);
                }
                else if (clientChoice.equals(showSubjects))
                {
                    Vector<Subject> subjects = this.controller.getSubjects();
                    this.sendVectorToClient(subjects);
                }
                else if (clientChoice.equals(addStudent))
                {
                    Student newStudent = this.getStudentDataFromUser();
                    this.controller.addStudent(newStudent);
                }
                else if (clientChoice.equals(addTeacher))
                {
                    Teacher newTeacher = (Teacher)this.getNameAndIDFromUser(new Teacher());
                    this.controller.addTeacher(newTeacher);
                }
                else if (clientChoice.equals(addSubject))
                {
                    Subject newSubject = (Subject)this.getNameAndIDFromUser(new Subject());
                    this.controller.addSubject(newSubject);
                }
            }
        } 
        catch (IOException e) 
        {
            this.closeConnection();
            e.printStackTrace();
        }
    }
}
