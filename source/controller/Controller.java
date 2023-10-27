package source.controller;

import source.repository.Repository;

import java.util.Vector;

import source.model.student.Student;
import source.model.subject.Subject;
import source.model.teacher.Teacher;

public class Controller 
{
    private Repository<Student> studentsRepository;    
    private Repository<Teacher> teachersRepository;
    private Repository<Subject> subjectsRepository;

    public Controller(Repository<Student> students, Repository<Teacher> teachers, Repository<Subject> subjects)
    {
        this.studentsRepository = students;
        this.teachersRepository = teachers;
        this.subjectsRepository = subjects;
    }

    public Vector<Student> getStudents()
    {
        return this.studentsRepository.getAll();
    }

    public Vector<Teacher> getTeachers()
    {
        return this.teachersRepository.getAll();
    }

    public Vector<Subject> getSubjects()
    {
        return this.subjectsRepository.getAll();
    }

    public Boolean addStudent(Student student)
    {
        return this.studentsRepository.addElement(student);
    }

    public Boolean addTeacher(Teacher teacher)
    {
        return this.teachersRepository.addElement(teacher);
    }

    public Boolean addSubject(Subject subject)
    {
        return this.subjectsRepository.addElement(subject);
    }

    public String getStudentsString()
    {
        return this.studentsRepository.toString();
    }

    public String getTeachersString()
    {
        return this.teachersRepository.toString();
    }

    public String getSubjectsString()
    {
        return this.subjectsRepository.toString();
    }
}
