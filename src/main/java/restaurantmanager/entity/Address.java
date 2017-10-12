/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restaurantmanager.entity;


public class Address {
    private int id;
    private String firstname;
    private String lastname;
    private String address;

    
    public Address(int id, String lastname, String firstname, String address) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
    }
    
    public Address(String lastname, String firstname, String address) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Address) {
            Address objAdr = (Address) obj;
            if (objAdr.id != this.id) {
                return false;
            } else if (!objAdr.firstname.equals(this.firstname)) {
                return false;
            } else if (!objAdr.lastname.equals(this.lastname)) {
                return false;
            } else if (!objAdr.address.equals(this.address)){
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() +1 ; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", address=" + address;
    }

  
}
