package models;
import daos.Dao;
import daos.DbConnection;
import daos.Dto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO implements Dao {
    private int nextId;
    private Connection conn = DbConnection.getConnection();

    @Override
    public Object findById(int id){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Car WHERE id=" + id+";");

            if(rs.next())
            {
                return extractUserFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private CarDto extractUserFromResultSet(ResultSet rs) {
        try
        {
            CarDto myCar = new CarDto();

            myCar.setId(rs.getInt("id"));
            myCar.setMake(rs.getNString("Make"));
            myCar.setModel(rs.getNString("Model"));
            myCar.setYear(rs.getInt("Year"));
            myCar.setColor(rs.getNString("Color"));
            myCar.setVin(rs.getNString("Vin"));

            return myCar;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List findAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Car ;");
            ArrayList<CarDto> car = new ArrayList<>();

            while (rs.next())
            {
                CarDto c = extractUserFromResultSet(rs);
                car.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(Dto dto) {
        try {
            Statement stmt = conn.createStatement();
            int i = stmt.executeUpdate("Update Car Set Make =?, Model = ?, Year = ?,Color = ?,Vin = ? where id ="+dto.getId());
            if(i == 1){
                return true;

            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(Dto dto) {
        try{
        Statement stmt = conn.createStatement();
        int i = stmt.executeUpdate("Insert into Car( Make, Model, Year,Color,Vin) Values(?,?,?,?,?)");
            if(i ==1){
            return true;
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            Statement stmt = conn.createStatement();
            int i = stmt.executeUpdate("Delete from Car Where id = "+id);
            if(i ==1){
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
