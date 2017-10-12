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
    private Date date;
    private Categories categorie;
    private taxClass taxclass;

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
        this.date = new Date();
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

    public Date getDate() {
        return date;
    }

    public Categories getCategorie() {
        return categorie;
    }

    public taxClass getTaxclass() {
        return taxclass;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCategorie(Categories categorie) {
        this.categorie = categorie;
    }

    public void setTaxclass(taxClass taxclass) {
        this.taxclass = taxclass;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (this.pnr ^ (this.pnr >>> 32));
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.description);
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 31 * hash + Objects.hashCode(this.date);
        hash = 31 * hash + Objects.hashCode(this.categorie);
        hash = 31 * hash + Objects.hashCode(this.taxclass);
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
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.categorie != other.categorie) {
            return false;
        }
        if (this.taxclass != other.taxclass) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produkt{" + "pnr=" + pnr + ", name=" + name + ", description=" + description + ", price=" + price + ", date=" + date + ", categorie=" + categorie + ", taxclass=" + taxclass + '}';
    }
}
