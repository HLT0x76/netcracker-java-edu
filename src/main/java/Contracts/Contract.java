package Contracts;

import java.util.Date;

public abstract class Contract {

    private final long id;
    private Date creationDate;
    private Date expirationDate;
    private String name;

    public Contract(long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Contract(long id, String name, Date creationDate, Date expirationDate)
    {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if(name.isEmpty()) {
            System.out.println("loh");
        }
        if(!name.equals(this.name) && !name.trim().isEmpty())
        {
            this.name = name;
        }
    }

    public Date getExpirationDate()
    {
        return this.expirationDate;
    }

    public void setExpirationDate(Date date)
    {
        this.expirationDate = date;
    }

    public Date getCreationDate()
    {
        return this.creationDate;
    }

    public void setCreationDate(Date date)
    {
        this.creationDate = date;
    }
}