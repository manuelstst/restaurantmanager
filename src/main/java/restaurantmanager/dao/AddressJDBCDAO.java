/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Stummerer
 */


package restaurantmanager.dao;


import restaurantmanager.entity.Address;
import restaurantmanager.service.DATABASE;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class AddressJDBCDAO implements AddressDAO{
    ConnectionManager conman;
    private final static String tablename="customer";
    private final static Logger logger=Logger.getLogger(AddressJDBCDAO.class);
    public AddressJDBCDAO() {
    }
    @Override
    public List<Address> findAll() throws DAOSysException{
        Connection con=null;
        List<Address> addresses=new ArrayList<>();
        try {
            con=conman.getInstance().getConnection();
            ResultSet rs=con.createStatement().executeQuery("Select * from "+tablename);
            
            while(rs.next()!=false){
                addresses.add(new Address(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException ex) {
            throw new DAOSysException(ex.getMessage());
        }
        return addresses;
    }

    @Override
    public void insert(Address address)throws DAOSysException{
        Connection con = null;
        try {
            con=conman.getInstance().getConnection();
            con.createStatement().execute("insert into "+tablename+" (firstname,lastname,address) values('"+address.getFirstname()+
                    "','"+address.getLastname()+"','"+address.getAddress()+"')");
            ResultSet rs=con.createStatement().executeQuery("select max(id) from "+tablename);
            if(rs.next())
                address.setId(rs.getInt(1));
        } catch (SQLException ex) {
            throw new DAOSysException(ex.getMessage());
        }
    }

    @Override
    public void update(Address address)throws DAOSysException{
        Connection con = null;
        try {
            con=conman.getInstance().getConnection();
            String sqlStatement="UPDATE "+tablename+" SET firstname = '"+address.getFirstname()+
                    "', lastname = '"+address.getLastname()+"', address = '"+address.getAddress()+"' where id = "+address.getId();
            con.createStatement().execute(sqlStatement);
        } catch (SQLException ex) {
            throw new DAOSysException(ex.getMessage());
        }
    }

    @Override
    public void delete(int id)throws DAOSysException{
        Connection con = null;
        try {
            con=conman.getInstance().getConnection();
            con.createStatement().execute("delete from "+tablename+" where id="+id);
        } catch (SQLException ex) {
            throw new DAOSysException(ex.getMessage());
        }
    }

    @Override
    public Address findById(int id) throws DAOSysException {
        Connection con = null;
        try {
            con=conman.getInstance().getConnection();
            ResultSet rs=con.createStatement().executeQuery("select * from "+tablename+" where id="+id);
            if(rs.next()){
                String vname=rs.getString(2);
                String nname=rs.getString(3);
                String address=rs.getString(4);
                return new Address(id,vname, nname, address);
            }
                
        } catch (SQLException ex) {
            throw new DAOSysException(ex.getMessage());
        }
        return null;
}

    @Override
    public void createDatabaseAndTables(DATABASE database){
        if(database==DATABASE.DERBY){
            try {
                this.conman=ConnectionManager.getInstance("jdbc:derby:memory:address;create=true","sqluser","12init34");
                Connection con=this.conman.getInstance().getConnection();
                con.createStatement().execute("create table address.customer("
                        + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT PEOPLE_PK PRIMARY KEY, "
                        + "firstname varchar(45) not null, lastname varchar(45) not null,address varchar(45) not null)");
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        else if(database==DATABASE.HSQLDB){
            try {
                this.conman = ConnectionManager.getInstance("jdbc:hsqldb:mem:address","sqluser","12init34"); 
                         Connection con =conman.getConnection();
                         Statement stmt = con.createStatement();
                         stmt.execute("create table if not exists "+tablename+" ("
                            + "id integer identity not null, "
                            + "firstname varchar(45) not null, "
                            + "lastname varchar(45) not null, "
                            + "address varchar(45) not null, "
                            + ")");
                         stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if(database==DATABASE.MySQL){
            try {
                this.conman=ConnectionManager.getInstance("jdbc:mysql://localhost/address?user=sqluser&password=12init34");
                Connection con=this.conman.getConnection();
                con.createStatement().execute("create schema if not exists address");
                con.createStatement().execute("create table if not exists address.customer(id integer not null auto_increment primary key, "
                        + "firstname varchar(100) not null,lastname varchar(100) not null,address varchar(100) not null)");
                this.conman=ConnectionManager.getInstance("jdbc:mysql://localhost/address?user=sqluser&password=12init34");
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}