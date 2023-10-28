package source.model.subject;

public class Subject 
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
