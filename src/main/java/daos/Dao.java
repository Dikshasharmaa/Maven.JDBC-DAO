package daos;

import java.util.List;

public interface Dao <T>{
    public T findById(int id);
    public List findAll();
    public boolean update(Dto dto);
    public boolean create(Dto dto);
    public boolean delete(int id);
}
