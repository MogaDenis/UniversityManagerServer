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
        return this.subjects.add(subject);
    }

    public Boolean removeElement(Subject subject)
    {
        return this.subjects.remove(subject);
    }

    public Integer size()
    {
        return this.subjects.size();
    }

    public Boolean isEmpty()
    {
        return this.subjects.isEmpty();
    }

    public Vector<Subject> getAll()
    {
        return new Vector<Subject>(this.subjects);
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
