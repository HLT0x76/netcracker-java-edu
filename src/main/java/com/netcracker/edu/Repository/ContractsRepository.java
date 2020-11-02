package com.netcracker.edu.Repository;

import java.util.Arrays;
import com.netcracker.edu.Contracts.Contract;
import lombok.AccessLevel;
import lombok.Getter;

public class ContractsRepository {

    private static final int INIT_SIZE = 3;
    @Getter(AccessLevel.PROTECTED) Contract[] contracts = new Contract[INIT_SIZE];
    private int currentIndex = INIT_SIZE - 1;

    /**
     * add method for ContractArray
     * which is scanning {@code contracts} for null values
     * to replace them with {@code Contract con} object and
     * managing array extension.
     *
     * O(n) - worst case due to null value replacing.
     *
     * @param con contract object
     */
    public void add(Contract con) {
        int currentSize = this.contracts.length;
        for (int i = 0; i < currentSize; i++) {
            if (this.contracts[i] == null) {
                this.contracts[i] = con;
                return;
            }
        }
        if (this.currentIndex == this.contracts.length - 1) {
            int newSize = this.contracts.length + 1;
            this.contracts = Arrays.copyOf(this.contracts, newSize);
        }
        this.currentIndex += 1;
        this.contracts[this.currentIndex] = con;
    }

    /**
     * delete method for ContractArrays
     * which changes Contract con with con.getId() == id to null.
     *
     * O(n) - worst case (if con with con.getId() == id at -1 index).
     *
     * @param id contract ID
     */
    public void delete(int id) {
        int currentLength = this.contracts.length;
        for (int i = 0; i < currentLength; i++) {
            if (contracts[i].getId() == id) {
                this.contracts[i] = null;
                break;
            }
        }
    }

    /**
     * get method for ContractArrays.
     *
     * O(n) - worst case (if con with con.getId() == id at -1 index).
     *
     * @param id ID of a contract object
     * @return contract object if found, else null
     */
    public Contract get(int id) {
        for (Contract con : this.contracts) {
            if ((con != null) && (con.getId() == id)) {
                return con;
            }
        }
        return null;
    }

}
