package com.netcracker.edu.Repository;

import java.util.Arrays;
import java.util.Objects;

import com.netcracker.edu.Contracts.Contract;
import lombok.AccessLevel;
import lombok.Getter;

public class ContractsRepository {

    private static final int INIT_SIZE = 1;
    private int currentIndex = 0;
    @Getter(AccessLevel.PROTECTED) private Contract[] contracts = null;

    /**
     * add method for ContractArray
     * that's adding {@code con} to a {@code contracts}
     * and manages array extension.
     *
     * @param con contract object
     */
    public void add(Contract con) {
        if (Objects.isNull(contracts)) {
            contracts = new Contract[INIT_SIZE];
        }
        int currentSize = contracts.length;
        if (currentIndex == currentSize) {
            int newSize = currentSize + 1;
            contracts = Arrays.copyOf(contracts, newSize);
        }
        contracts[currentIndex] = con;
        currentIndex += 1;
    }

    /**
     * delete method for ContractArrays
     * which changes Contract by shifting array left
     *
     * @param id contract ID
     */
    public void delete(int id) {
        int currentLength = contracts.length;
        for (int i = 0; i < currentLength; i++) {
            if (contracts[i].getId() == id) {
                System.arraycopy(contracts,
                        i + 1,
                        contracts,
                        i,
                        currentLength - 1 - i);
                contracts = Arrays.copyOf(contracts, currentLength - 1);
                currentIndex -= 1;
                break;
            }
        }
    }

    /**
     * get method for ContractArrays.
     *
     * @param id ID of a contract object
     * @return contract object if found, else null
     */
    public Contract get(int id) {
        for (Contract con : contracts) {
            if (con.getId() == id) {
                return con;
            }
        }
        return null;
    }

}
