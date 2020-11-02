import java.time.Period;
import java.time.Year;
import java.util.Calendar;

public class Customer {

    private long id;
    private Calendar dob;
    private String fio;
    private String passport;

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;

    }

    public int getAge() {
        return 0;
//        Calendar.getInstance();
//        Period.between(Calendar.getInstance(), dob).getYears();
//        return Calendar.getInstance().before(dob);
    }
}
