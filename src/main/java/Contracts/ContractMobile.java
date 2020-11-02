package Contracts;

import java.util.Date;

public class ContractMobile extends Contract {

    private long smsAmount;
    private long minutesAmount;
    private long internetInMb;

    public ContractMobile(long id,
                          String name,
                          Date creationDate,
                          Date expirationDate,
                          long smsAmount,
                          long minutesAmount,
                          long internetInMb)
    {
        super(id, name, creationDate, expirationDate);
        this.smsAmount = smsAmount;

    }

}
