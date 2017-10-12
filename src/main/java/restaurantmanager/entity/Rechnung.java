package restaurantmanager.entity;

import enums.Status;
import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;



public class Rechnung implements Identifiable {

    private long rNr;
    private int tableNr;
    private Date man_date;
    private Date acc_date;
    private List<Produkt> products = new ArrayList<>();
    private Status status;
    //private DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

    @Override
    public void setId(long id) {
        this.rNr = id;
    }

    @Override
    public long getId() {
        return this.rNr;
    }
    

    /**
     * Default empty constructor - Required for JAXB XML conversion.
     */
    public Rechnung() {
    }

    public Rechnung(long rNr, int tableNr) {
        this.rNr = rNr;
        this.tableNr = tableNr;
        this.man_date = new Date();
        this.status = Status.OPEN;
    }

    public int getTableNr() {
        return tableNr;
    }

    public Date getMan_date() {
        return man_date;
    }

    public Date getAcc_date() {
        return acc_date;
    }

    public Status getStatus() {
        return status;
    }

    public List<Produkt> getProducts() {
        return products;
    }

    public void setTableNr(int tableNr) {
        this.tableNr = tableNr;
    }

    public void setMan_date(Date man_date) {
        this.man_date = man_date;
    }

    public void setProducts(List<Produkt> products) {
        this.products = products;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAcc_date(Date acc_date) {
        this.acc_date = acc_date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.rNr ^ (this.rNr >>> 32));
        hash = 67 * hash + this.tableNr;
        hash = 67 * hash + Objects.hashCode(this.man_date);
        hash = 67 * hash + Objects.hashCode(this.acc_date);
        hash = 67 * hash + Objects.hashCode(this.products);
        hash = 67 * hash + Objects.hashCode(this.status);
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
        final Rechnung other = (Rechnung) obj;
        if (this.rNr != other.rNr) {
            return false;
        }
        if (this.tableNr != other.tableNr) {
            return false;
        }
        if (!Objects.equals(this.man_date, other.man_date)) {
            return false;
        }
        if (!Objects.equals(this.acc_date, other.acc_date)) {
            return false;
        }
        if (!Objects.equals(this.products, other.products)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rechnung{" + "rNr=" + rNr + ", tableNr=" + tableNr + ", man_date=" + man_date + ", acc_date=" + acc_date + ", products=" + products + ", status=" + status + '}';
    }
    
}
