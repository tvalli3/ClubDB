package business;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

/**
 * @author Tom Valli
 */
@Entity
@Table(name="tblPurchases")
public class Purchase {
    @Id
    @Column(name="ID")
    private long pid;
    
    @Column(name="MemId")
    private String memid;
    
    @Column(name="PurchaseDt")
    @Temporal(TemporalType.DATE)
    private Date purchasedt;
    
    @Column(name="TransType")
    private String transtype;
    
    @Column(name="TransCd")
    private String transcd;
    
    @Column(name="Amount")
    private double amount;
    
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="TransCd", insertable=false, updatable=false)
    private PCodes pcodes;
        
    public Purchase() {
        this.memid = "";
        this.purchasedt = null;
        this.transtype = "";
        this.transcd = "";
        this.amount = 0;
        this.pid = 0;
    }

    public long getPid() {
        return this.pid;
    }
    
    public void setPid(long pid) {
        this.pid = pid;
    }
    
    public String getMemid() {
        return memid;
    }

    public void setMemid(String memid) {
        this.memid = memid;
    }

    public Date getPurchasedt() {
        return purchasedt;
    }

    public void setPurchasedt(Date purchasedt) {
        this.purchasedt = purchasedt;
    }
    
    public String getPurchasedtS() {
        return new SimpleDateFormat("MM-dd-yyyy").format(this.purchasedt);
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }

    public String getTranscd() {
        return transcd;
    }

    public void setTranscd(String transcd) {
        this.transcd = transcd;
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getAmountS() {
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        return curr.format(this.amount);
    }
    
    public String getTransDesc() {
        return pcodes.getTransdesc();
    }
    
}
