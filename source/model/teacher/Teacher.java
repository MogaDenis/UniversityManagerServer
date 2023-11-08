package source.model.teacher;

import java.util.HashSet;

import source.model.copyable.Copyable;

public class Teacher implements Copyable<Teacher>
{
    private Integer ID;
    private String name;
    private HashSet<Integer> subjectsIDs;  

    public Teacher()
    {
        this.name = null;
        this.ID = null;
        this.subjectsIDs = new HashSet<>();
    }

    public Teacher(String name, Integer ID)
    {
        this.name = name;
        this.ID = ID;
        this.subjectsIDs = new HashSet<>();
    }

    public Teacher(String name, Integer ID, HashSet<Integer> subjectsIDs)
    {
        this.name = name;
        this.ID = ID;
        this.subjectsIDs = new HashSet<>(subjectsIDs);
    }

    public Teacher(Integer ID)
    {
        this.name = null;
        this.subjectsIDs = new HashSet<>();
        this.ID = ID;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
            return true;

        if (!(object instanceof Teacher))
            return false;

        Teacher teacher = (Teacher)object;

        return this.ID == teacher.ID;
    }

    @Override
    public Teacher deepCopy()
    {
        return new Teacher(this.name, this.ID, this.subjectsIDs);
    }

    public Integer getID()
    {
        return this.ID;
    }

    public String getName()
    {
        return this.name;
    }

    public HashSet<Integer> getSubjectsIDs()
    {
        return this.subjectsIDs;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public Boolean addSubjectID(Integer subjectID)
    {
        return this.subjectsIDs.add(subjectID);
    }

    @Override
    public String toString()
    {
        return "Teacher ID: " + String.valueOf(this.ID) + " - Name: " + this.name;
    }
}
