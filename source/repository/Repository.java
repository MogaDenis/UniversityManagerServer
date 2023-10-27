package source.repository;

import java.util.Vector;

public interface Repository<T>
{
    public Boolean addElement(T element);
    public Boolean removeElement(T element);
    public Integer size();
    public Boolean isEmpty();
    public Vector<T> getAll();
    public String toString();
}