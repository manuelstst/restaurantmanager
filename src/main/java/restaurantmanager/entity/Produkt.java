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
public class Produkt{
    
    private int id;
    private int pnr;
    private String name;
    private String description;
    private double price;
    private Date man_date;
    private Date mod_date;
    private Categories categorie;
    private taxClass taxclass;
    private boolean has_picture;
    private boolean storno;

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Produkt(){
        
    }

    public Produkt(int id, int pnr, String name, String description, double price, Date man_date, Date mod_date, Categories categorie, taxClass taxclass, boolean has_picture, boolean storno) {
        this.id = id;
        this.pnr = pnr;
        this.name = name;
        this.description = description;
        this.price = price;
        this.man_date = man_date;
        this.mod_date = mod_date;
        this.categorie = categorie;
        this.taxclass = taxclass;
        this.has_picture = has_picture;
        this.storno = storno;
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

    public boolean isStorno() {
        return storno;
    }

    public int getPnr() {
        return pnr;
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

    public void setStorno(boolean storno) {
        this.storno = storno;
    }

    public void setPnr(int pnr) {
        this.pnr = pnr;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (int) (this.pnr ^ (this.pnr >>> 32));
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.description);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 11 * hash + Objects.hashCode(this.man_date);
        hash = 11 * hash + Objects.hashCode(this.mod_date);
        hash = 11 * hash + Objects.hashCode(this.categorie);
        hash = 11 * hash + Objects.hashCode(this.taxclass);
        hash = 11 * hash + (this.has_picture ? 1 : 0);
        hash = 11 * hash + (this.storno ? 1 : 0);
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
        if (this.storno != other.storno) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produkt{" + "pnr=" + pnr + ", name=" + name + ", description=" + description + ", price=" + price + ", man_date=" + man_date + ", mod_date=" + mod_date + ", categorie=" + categorie + ", taxclass=" + taxclass + ", has_picture=" + has_picture + ", storno=" + storno + '}';
    }
    
}
