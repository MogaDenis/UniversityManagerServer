package source.repository;

import java.util.Vector;
import source.model.subject.Subject;

public class SubjectsRepository implements Repository<Subject>
{
    private Vector<Subject> subjects;

    public SubjectsRepository()
    {
        this.subjects = new Vector<>();
    }

    public Boolean addElement(Subject subject)
    {
        if (this.subjects.contains(subject))
            return false;

        return this.subjects.add(subject);
    }

    public Boolean removeElement(Subject subject)
    {
        return this.subjects.remove(subject);
    }

    public Boolean removeElementByID(Integer ID)
    {
        return this.subjects.remove(new Subject(ID));
    }

    public Integer size()
    {
        return this.subjects.size();
    }

    public Boolean isEmpty()
    {
        return this.subjects.isEmpty();
    }

    @Override
    public Vector<Subject> getAll()
    {
        Vector<Subject> subjectsCopy = new Vector<>();

        for (Subject subject : this.subjects)
            subjectsCopy.add(subject.deepCopy());

        return subjectsCopy;
    }

    @Override
    public String toString()
    {
        String string = "";
        for (Subject subjects : this.subjects)
            string += subjects.toString() + '\n';

        return string;
    }
}
