package models;
import daos.Dao;
import daos.DbConnection;
import daos.Dto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO implements Dao<Dto> {
    private int nextId;
    private Connection conn = DbConnection.getConnection();

    @Override
    public CarDto findById(int id){
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
        ArrayList<CarDto> car = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Car ;");
            //car = new ArrayList<>();

            while (rs.next()) {
                CarDto c = extractUserFromResultSet(rs);
                car.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return car;
    }

    @Override
    public boolean update(Dto dto) {
        try {
            PreparedStatement stmt = conn.prepareStatement("Update Car Set Make =?, Model = ?, Year = ?,Color = ?,Vin = ? where id = ?");

            // Set values for placeholders

            stmt.setString(1,dto.getMake());
            stmt.setString(2, dto.getModel());
            stmt.setInt(3,dto.getYear());
            stmt.setString(4,dto.getColor());
            stmt.setString(5,dto.getVin());
            stmt.setInt(6,dto.getId());
            int i = stmt.executeUpdate();

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
    public Dto create(Dto dto) {
        try {
            // Use PreparedStatement to avoid SQL injection and handle placeholders
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Car (Make, Model, Year, Color, Vin) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            // Set values for placeholders
            //stmt.setInt(1,dto.getId());
            stmt.setString(1,dto.getMake());
            stmt.setString(2, dto.getModel());
            stmt.setInt(3,dto.getYear());
            stmt.setString(4,dto.getColor());
            stmt.setString(5,dto.getVin());

            // Execute the update
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                // Retrieve the generated keys
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    // Assuming findById returns the same type as Dto
//                    return findById(rs.getInt(1));
                    dto.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }


    @Override
    public boolean delete(int id) {
        try {
            Statement stmt = conn.createStatement();
            int i = stmt.executeUpdate("Delete from Car Where id = "+id+";");
            if(i ==1){
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    public int findCount() {
        int count =0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM Car ;");
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return count;
    }
}
