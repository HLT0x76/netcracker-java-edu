package com.netcracker.edu.Repository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import com.netcracker.edu.Contracts.Contract;
import com.netcracker.edu.Sorters.ISorter;
import lombok.AccessLevel;
import lombok.Getter;

public class ContractsRepository implements IRepository<Contract> {

    private static final int INIT_SIZE = 1;
    private int currentIndex = 0;
    @Getter(AccessLevel.PROTECTED) private Contract[] content = null;

    /**
     * add method for ContractArray
     * that's adding {@code con} to a {@code contracts}
     * and manages array extension.
     *
     * @param contract contract object
     */
    @Override
    public void add(Contract contract) {
        if (Objects.isNull(content)) {
            content = new Contract[INIT_SIZE];
        }
        int currentSize = content.length;
        if (currentIndex == currentSize) {
            int newSize = currentSize + 1;
            content = Arrays.copyOf(content, newSize);
        }
        content[currentIndex] = contract;
        currentIndex += 1;
    }

    /**
     * delete method for ContractArrays
     * which modifies the array - "shifts" it by one if needed
     *
     * @param id contract ID
     */
    @Override
    public void delete(int id) {
        int currentLength = content.length;
        for (int i = 0; i < currentLength; i++) {
            if (content[i].getId() == id) {
                System.arraycopy(content,
                        i + 1,
                        content,
                        i,
                        currentLength - 1 - i);
                content = Arrays.copyOf(content, currentLength - 1);
                currentIndex -= 1;
                break;
            }
        }
    }

    /**
     * get method for ContractArrays.
     *
     * @param index ID of a contract object
     * @return contract object if found, else null
     */
    @Override
    public Optional<Contract> get(int index) {
        for (Contract con : content) {
            if (con.getId() == index) {
                return Optional.of(con);
            }
        }
        return Optional.empty();
    }

    @Override
    public void sortBy(ISorter<Contract> sorter, Comparator<Contract> comparator) {
        sorter.sort(this.content, comparator);
    }

    @Override
    public IRepository<Contract> searchBy(Predicate condition) {
        return null;
    }

}
