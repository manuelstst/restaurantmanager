
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

@author Stummerer
 */
package restaurantmanager.service;


import restaurantmanager.entity.Address;
import java.util.List;

public interface AddressService {
    public void insert(Address address) throws ServiceException;
    public void update(Address address) throws ServiceException;
    public void delete(int id) throws ServiceException;
    public Address findById(int id) throws ServiceException;
    public List<Address> findAll() throws ServiceException;
    public List<Address> getAddressList();
}
