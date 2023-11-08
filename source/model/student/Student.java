package source.model.student;

import source.model.copyable.Copyable;

public class Student implements Copyable<Student>
{
    private String name;
    private Integer ID;
    private Integer groupID;   
    
    public Student()
    {
        this.name = null;
        this.ID = null;
        this.groupID = null;
    }

    public Student(String name, Integer ID, Integer groupID)
    {
        this.name = name;
        this.ID = ID;
        this.groupID = groupID;
    }

    public Student(String name, Integer ID)
    {
        this.name = name;
        this.ID = ID;
        this.groupID = null;
    }

    public Student(Integer ID)
    {
        this.name = null;
        this.groupID = null;
        this.ID = ID;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
            return true;

        if (!(object instanceof Student))
            return false;

        Student student = (Student)object;

        return this.ID == student.ID;
    }

    @Override
    public Student deepCopy()
    {
        return new Student(this.name, this.ID, this.groupID);
    }

    public String getName()
    {
        return this.name;
    }

    public Integer getID()
    {
        return this.ID;
    }

    public Integer getGroupID()
    {
        return this.groupID;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public void setGroupID(Integer newGroupID)
    {
        this.groupID = newGroupID;
    }

    @Override
    public String toString()
    {
        return "Student ID: " + String.valueOf(this.ID) + " - Name: " + this.name + " - Group: " + String.valueOf(this.groupID); 
    }
}
