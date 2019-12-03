package business;

import business.PCodes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-27T00:11:03")
@StaticMetamodel(Purchase.class)
public class Purchase_ { 

    public static volatile SingularAttribute<Purchase, Double> amount;
    public static volatile SingularAttribute<Purchase, PCodes> pcodes;
    public static volatile SingularAttribute<Purchase, String> transtype;
    public static volatile SingularAttribute<Purchase, Long> pid;
    public static volatile SingularAttribute<Purchase, Date> purchasedt;
    public static volatile SingularAttribute<Purchase, String> transcd;
    public static volatile SingularAttribute<Purchase, String> memid;

}