package source.repository;

import java.util.Vector;

import source.model.teacher.Teacher;

public class TeachersRepository implements Repository<Teacher>
{
    private Vector<Teacher> teachers;

    public TeachersRepository()
    {
        this.teachers = new Vector<>();
    }

    public Boolean addElement(Teacher teacher)
    {
        if (this.teachers.contains(teacher))
            return false;

        return this.teachers.add(teacher);
    }

    public Boolean removeElement(Teacher teacher)
    {
        return this.teachers.remove(teacher);
    }

    public Boolean removeElementByID(Integer ID)
    {
        return this.teachers.remove(new Teacher(ID));
    }

    public Integer size()
    {
        return this.teachers.size();
    }

    public Boolean isEmpty()
    {
        return this.teachers.isEmpty();
    }

    @Override
    public Vector<Teacher> getAll()
    {
        Vector<Teacher> teachersCopy = new Vector<>();

        for (Teacher teacher : this.teachers)
            teachersCopy.add(teacher.deepCopy());

        return teachersCopy;
    }

    @Override
    public String toString()
    {
        String string = "";
        for (Teacher teacher : this.teachers)
            string += teacher.toString() + '\n';

        return string;
    }
}
