package source.repository;

import java.util.Vector;
import source.model.student.Student;

public class StudentsRepository implements Repository<Student>
{
    private Vector<Student> students;

    public StudentsRepository()
    {
        this.students = new Vector<>();
    }

    public Boolean addElement(Student student)
    {
        if (this.students.contains(student))
            return false;

        return this.students.add(student);
    }

    public Boolean removeElement(Student student)
    {
        return this.students.remove(student);
    }

    public Integer size()
    {
        return this.students.size();
    }

    public Boolean isEmpty()
    {
        return this.students.isEmpty();
    }

    public Vector<Student> getAll()
    {
        return new Vector<Student>(this.students);
    }

    @Override
    public String toString()
    {
        String string = "";
        for (Student student : this.students)
            string += student.toString() + '\n';

        return string;
    }
}
