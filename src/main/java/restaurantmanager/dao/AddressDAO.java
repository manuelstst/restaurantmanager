/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

@author Stummerer
 */

package restaurantmanager.dao;

import restaurantmanager.entity.Address;
import restaurantmanager.service.DATABASE;
import java.util.List;

public interface AddressDAO {
    public List<Address> findAll() throws DAOSysException;
    public Address findById(int id)throws DAOSysException;
    public void insert(Address address)throws DAOSysException;
    public void update(Address address)throws DAOSysException;
    public void delete(int id)throws DAOSysException;
    public void createDatabaseAndTables(DATABASE database);
}
