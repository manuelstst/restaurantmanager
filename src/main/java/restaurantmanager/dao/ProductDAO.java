/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

@author Stummerer
 */

package restaurantmanager.dao;

import restaurantmanager.service.DATABASE;
import java.util.List;
import restaurantmanager.entity.Produkt;

public interface ProductDAO {
    public List<Produkt> findAll() throws DAOSysException;
    public Produkt findById(int id)throws DAOSysException;
    public void insert(Produkt product)throws DAOSysException;
    public void update(Produkt product)throws DAOSysException;
    public void delete(int id)throws DAOSysException;
    public void createDatabaseAndTables(DATABASE database);
}
