package source.model.student;

public class Student 
{
    private String name;
    private Integer ID;
    private Integer groupID;    

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

    public String getName()
    {
        return this.name;
    }

    public Integer getID()
    {
        return this.ID;
    }

    public Integer groupID()
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
