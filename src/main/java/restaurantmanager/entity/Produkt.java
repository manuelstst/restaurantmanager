/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantmanager.entity;

import enums.Categories;
import enums.taxClass;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Manuel
 */
public class Produkt implements Identifiable{
    private long pnr;
    private String name;
    private String description;
    private double price;
    private Date man_date;
    private Date mod_date;
    private Categories categorie;
    private taxClass taxclass;
    private boolean has_picture;

    @Override
    public void setId(long id) {
        this.pnr = id;
    }

    @Override
    public long getId() {
        return pnr;
    }
    
    public Produkt(){
        
    }
    
    public Produkt(long pnr, String name, String description, double price,Categories categorie, taxClass taxclass) {
        this.pnr = pnr;
        this.name = name;
        this.description = description;
        this.price = price;
        this.man_date = new Date();
        this.categorie = categorie;
        this.taxclass = taxclass;
    }

    

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
    
    public Categories getCategorie() {
        return categorie;
    }

    public taxClass getTaxclass() {
        return taxclass;
    }

    public Date getMan_date() {
        return man_date;
    }

    public Date getMod_date() {
        return mod_date;
    }

    public boolean isHas_picture() {
        return has_picture;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategorie(Categories categorie) {
        this.categorie = categorie;
    }

    public void setTaxclass(taxClass taxclass) {
        this.taxclass = taxclass;
    }

    public void setMan_date(Date man_date) {
        this.man_date = man_date;
    }

    public void setMod_date(Date mod_date) {
        this.mod_date = mod_date;
    }

    public void setHas_picture(boolean has_picture) {
        this.has_picture = has_picture;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.pnr ^ (this.pnr >>> 32));
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.man_date);
        hash = 67 * hash + Objects.hashCode(this.mod_date);
        hash = 67 * hash + Objects.hashCode(this.categorie);
        hash = 67 * hash + Objects.hashCode(this.taxclass);
        hash = 67 * hash + (this.has_picture ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produkt other = (Produkt) obj;
        if (this.pnr != other.pnr) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.man_date, other.man_date)) {
            return false;
        }
        if (!Objects.equals(this.mod_date, other.mod_date)) {
            return false;
        }
        if (this.categorie != other.categorie) {
            return false;
        }
        if (this.taxclass != other.taxclass) {
            return false;
        }
        if (this.has_picture != other.has_picture) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produkt{" + "pnr=" + pnr + ", name=" + name + ", description=" + description + ", price=" + price + ", man_date=" + man_date + ", mod_date=" + mod_date + ", categorie=" + categorie + ", taxclass=" + taxclass + ", has_picture=" + has_picture + '}';
    }
}
