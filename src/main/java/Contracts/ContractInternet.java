package Contracts;

import java.util.Date;

public class ContractInternet extends Contract {

    private int internetSpeed;

    public ContractInternet(long id, String name, Date creationDate, Date expirationDate, int internetSpeed) {
        super(id, name, creationDate, expirationDate);
        this.internetSpeed = internetSpeed;
    }

    public int getInternetSpeed() {
        return internetSpeed;
    }

    public void setInternetSpeed(int internetSpeed) {
        this.internetSpeed = internetSpeed;
    }
}
