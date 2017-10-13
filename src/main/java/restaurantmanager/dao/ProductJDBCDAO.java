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


import enums.Categories;
import enums.taxClass;
import restaurantmanager.entity.Address;
import restaurantmanager.service.DATABASE;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import restaurantmanager.entity.Produkt;

public class ProductJDBCDAO implements ProductDAO{
    ConnectionManager conman;
    private final static String tablename="products";
    private final static Logger logger=Logger.getLogger(ProductJDBCDAO.class);
    
    public ProductJDBCDAO() {
    }
    
    @Override
    public List<Produkt> findAll() throws DAOSysException{
        Connection con=null;
        List<Produkt> products=new ArrayList<>();
        try {
            con=conman.getInstance().getConnection();
            ResultSet rs=con.createStatement().executeQuery("Select * from "+tablename);
            
            while(rs.next()!=false){
                products.add(new Produkt(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDate(6),
                rs.getDate(7),Categories.valueOf(rs.getString(8)),(rs.getInt(9) == 10?taxClass.TEN:taxClass.TWENTY),(rs.getInt(9)==1?true:false),(rs.getInt(10)==1?true:false)));
            }
        } catch (SQLException ex) {
            throw new DAOSysException(ex.getMessage());
        }
        return products;
    }

    @Override
    public void insert(Produkt product)throws DAOSysException{
        Connection con = null;
        try {
            con=conman.getInstance().getConnection();
            /*
            INSERT INTO `restaurant`.`products` (`pnr`, `name`, `description`, `price`, `man_date`, `mod_date`, `categorie`, `taxclass`, `haspicture`) 
            VALUES ('102', 'Birne', 'Ist sehr gut', '2.0', '20.01.1997', '20.01.1997', 'dessert', '10', '0');
            */
            con.createStatement().execute("insert into "+tablename+" (pnr,name,description,price,man_date,mod_date,categorie,taxclass,haspicture) "
                    + "values('"+product.getPnr()+
                    "','"+product.getName()+"','" +product.getName()+"','" +product.getDescription()+"','" 
                    +product.getPrice()+"','" +product.getMan_date()+"','" +product.getMod_date()+"','"
                    +product.getCategorie().toString()+"','" +(product.isHas_picture()==true?1:0)+"','" +(product.isStorno()==true?1:0)+"')");
            ResultSet rs=con.createStatement().executeQuery("select max(id) from "+tablename);
            if(rs.next())
                product.setId(rs.getInt(1));
        } catch (SQLException ex) {
            throw new DAOSysException(ex.getMessage());
        }
    }

    @Override
    public void update(Produkt product)throws DAOSysException{
        /*
        Connection con = null;
        try {
            con=conman.getInstance().getConnection();
            String sqlStatement="UPDATE "+tablename+" SET firstname = '"+address.getFirstname()+
                    "', lastname = '"+address.getLastname()+"', address = '"+address.getAddress()+"' where id = "+address.getId();
            con.createStatement().execute(sqlStatement);
        } catch (SQLException ex) {
            throw new DAOSysException(ex.getMessage());
        } */
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
    public Produkt findById(int id) throws DAOSysException {
        Connection con = null;
        try {
            con=conman.getInstance().getConnection();
            ResultSet rs=con.createStatement().executeQuery("select * from "+tablename+" where id="+id);
            if(rs.next()){
                int idd = rs.getInt(1);
                int pnr = rs.getInt(2);
                String name=rs.getString(2);
                String description=rs.getString(3);
                Double price = rs.getDouble(4);
                Date man_date=rs.getDate(5);
                Date mod_date=rs.getDate(6);
                Categories categorie = Categories.valueOf(rs.getString(7));
                taxClass taxclass = rs.getInt(8) == 10?taxClass.TEN:taxClass.TWENTY;
                boolean haspicture = rs.getInt(9) == 1?true:false;
                boolean isstorno = rs.getInt(9) == 1?true:false;
                return new Produkt(idd,pnr,name,description,price,man_date,mod_date,categorie,taxclass,haspicture,isstorno);
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