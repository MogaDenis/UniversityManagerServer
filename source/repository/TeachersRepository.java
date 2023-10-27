package source.repository;

import java.util.Vector;

import source.model.teacher.Teacher;

public class TeachersRepository implements Repository<Teacher>
{
    private Vector<Teacher> teachers;

    public Boolean addElement(Teacher teacher)
    {
        return this.teachers.add(teacher);
    }

    public Boolean removeElement(Teacher teacher)
    {
        return this.teachers.remove(teacher);
    }

    public Integer size()
    {
        return this.teachers.size();
    }

    public Boolean isEmpty()
    {
        return this.teachers.isEmpty();
    }

    public Vector<Teacher> getAll()
    {
        return new Vector<Teacher>(this.teachers);
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
