package source.model.subject;

import source.model.copyable.Copyable;

public class Subject implements Copyable<Subject>
{
    private Integer ID;
    private String name;

    public Subject()
    {
        this.name = null;
        this.ID = null;
    }
    
    public Subject(String name, Integer ID)
    {
        this.name = name;
        this.ID = ID;
    }

    public Subject(Integer ID)
    {
        this.name = null;
        this.ID = ID;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
            return true;

        if (!(object instanceof Subject))
            return false;

        Subject subject = (Subject)object;

        return this.ID == subject.ID;
    }

    @Override
    public Subject deepCopy()
    {
        return new Subject(this.name, this.ID);
    }

    public Integer getID()
    {
        return this.ID;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    @Override
    public String toString()
    {
        return "Subject ID: " + String.valueOf(this.ID) + " - Name: " + this.name;
    }
}
