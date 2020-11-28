package com.netcracker.edu.parsers;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.contracts.ContractInternet;
import com.netcracker.edu.contracts.ContractMobile;
import com.netcracker.edu.contracts.ContractTelevision;
import com.netcracker.edu.converters.StringToContractConverter;
import com.netcracker.edu.converters.StringToGenderConverter;
import com.netcracker.edu.converters.StringToLocalDateConverter;
import com.netcracker.edu.customers.Customer;
import com.netcracker.edu.customers.Gender;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import java.time.LocalDate;
import lombok.Getter;

/**
 * Glue class (bean) for {@link com.opencsv.bean.CsvToBean}
 * which methods ({@code getCustomer}, {@code getContract})
 * will return {@link Customer} and {@link Contract} object populated by parsed data.
 */
@Getter
public class RepositoryCsv {

  @CsvCustomBindByName(converter = StringToLocalDateConverter.class, required = true)
  private LocalDate creationDate;

  @CsvCustomBindByName(converter = StringToLocalDateConverter.class, required = true)
  private LocalDate expirationDate;

  @CsvBindByName(required = true)
  private String fullName;

  @CsvCustomBindByName(converter = StringToGenderConverter.class, required = true)
  private Gender gender;

  @CsvCustomBindByName(converter = StringToLocalDateConverter.class, required = true)
  private LocalDate dateOfBirth;

  @CsvBindByName(required = true)
  private String passport;

  @CsvCustomBindByName(
          converter = StringToContractConverter.class,
          required = true,
          column = "contractType")
  private Contract contract;

  @CsvBindByName(required = true)
  private String contractAdditionalInfo;

  /**
   * Creates and returns new {@link Customer} object
   * with fields populated by {@link com.opencsv.bean.CsvToBean}.
   *
   * @return new customer object
   */
  public Customer getCustomer() {
    return new Customer(dateOfBirth, gender, fullName, passport);
  }

  /**
   * Parses csv {@code contractAdditionalInfo} field and
   * returns populated {@link Contract} object.
   *
   * @return object, which extends {@link Contract} chosen
    according to provided csv field (see {@link StringToContractConverter})
   * @throws NumberFormatException in case of mismatch between
    csv {@code contractAdditionalInfo} field content and expected types
   */
  public Contract getContract() throws NumberFormatException {
    contract.setExpirationDate(expirationDate);
    contract.setCreationDate(creationDate);
    switch (contract.getClass().getSimpleName()) {
      case "ContractTelevision":
        ((ContractTelevision) contract).setChannelsPacket(contractAdditionalInfo);
        break;
      case "ContractMobile": {
        String[] cad = contractAdditionalInfo.split(";");
        long smsAmount = Long.parseLong(cad[0]);
        long minutesAmount = Long.parseLong(cad[1]);
        long internetAmount = Long.parseLong(cad[2]);
        ((ContractMobile) contract).setSmsAmount(smsAmount);
        ((ContractMobile) contract).setMinutesAmount(minutesAmount);
        ((ContractMobile) contract).setInternetAmount(internetAmount);
        break;
      }
      case "ContractInternet":
        int internetSpeed = Integer.parseInt(contractAdditionalInfo);
        ((ContractInternet) contract).setInternetSpeed(internetSpeed);
        break;
      default:
        return contract;
    }
    return contract;
  }
}
