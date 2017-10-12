/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

@author Stummerer
 */
package restaurantmanager.service;

import restaurantmanager.dao.AddressDAO;
import restaurantmanager.dao.AddressJDBCDAO;
import restaurantmanager.dao.DAOSysException;
import restaurantmanager.entity.Address;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class AddressJDBCService implements AddressService{
    private AddressDAO dao;
    private final static Logger logger=Logger.getLogger(AddressJDBCService.class);
    private DATABASE db;
    private List<Address> addressList=new ArrayList<>();
    
    public AddressJDBCService(DATABASE database) {
        this.dao=new AddressJDBCDAO();
        this.db=database;
        this.dao.createDatabaseAndTables(database);
    }
    
    /*
    @Override
    public Address findById(int id) throws ServiceException{
        try {
            return this.dao.findById(id);
        } catch (DAOSysException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public List<Address> findAll() throws ServiceException{
        try {
            this.addressList=this.dao.findAll();
        } catch (DAOSysException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return this.addressList;
    }
    
    */
    
    @Override
    public void delete(int id) throws ServiceException{
        try {
            this.dao.delete(id);
            this.addressList=this.dao.findAll();
        } catch (DAOSysException ex) {
            throw new ServiceException(ex.getMessage());
        }
        
    }
    
    @Override
    public Address findById(int id) throws ServiceException{
        try {
            return this.dao.findById(id);
        } catch (DAOSysException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public List<Address> findAll() throws ServiceException{
        try {
            this.addressList=this.dao.findAll();
        } catch (DAOSysException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return this.addressList;
    }
    

    @Override
    public List<Address> getAddressList() {
        return this.addressList;
    }
    
    @Override
    public void insert(Address adr) throws ServiceException{
        try {
            this.dao.insert(adr);
        } catch (DAOSysException ex) {
            throw new ServiceException(ex.getMessage());
        }
        this.addressList.add(adr);
    }
    
    @Override
    public void update(Address adr) throws ServiceException{
        try {
            this.dao.update(adr);
            this.addressList=this.dao.findAll();
        } catch (DAOSysException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
