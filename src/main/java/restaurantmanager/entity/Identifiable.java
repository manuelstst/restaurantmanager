package restaurantmanager.entity;

import java.io.Serializable;

/**
 * Interface implemented by all POJOs to produce consistent ways of getting and setting the id of a POJO.
 * 
 * @author MM
 */
public interface Identifiable extends Serializable{
    
    /**
     * Sets the ID of the POJO.
     * 
     * @param id The ID of the POJO
     */
    void setId(long id);
    
    /**
     * Returns the ID of the POJO.
     * 
     * @return The ID of the POJO
     */
    long getId();
}
