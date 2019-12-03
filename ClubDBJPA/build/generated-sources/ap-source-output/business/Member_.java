package business;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-27T00:11:03")
@StaticMetamodel(Member.class)
public class Member_ { 

    public static volatile SingularAttribute<Member, String> firstname;
    public static volatile SingularAttribute<Member, Long> password;
    public static volatile SingularAttribute<Member, String> middlename;
    public static volatile SingularAttribute<Member, Date> memdt;
    public static volatile SingularAttribute<Member, String> memid;
    public static volatile SingularAttribute<Member, String> lastname;
    public static volatile SingularAttribute<Member, String> status;

}