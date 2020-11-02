package Contracts;

import java.util.Date;

public class ContractTelevision extends Contract {

    private String channelPacket;

    public ContractTelevision(long id, String name, Date creationDate, Date expirationDate, String channelPacket) {
        super(id, name, creationDate, expirationDate);
        this.channelPacket = channelPacket;
    }

    public String getChannelPacket() {
        return channelPacket;
    }

    public void setChannelPacket(String channelPacket) {
        this.channelPacket = channelPacket;
    }
}
