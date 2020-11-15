package com.netcracker.edu.Repository;

import java.util.*;
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

    /**
     * transforms {@code content} array into a list of contracts
     *
     * @return list of {@code Contract}'s
     */
    @Override
    public List<Contract> toList() {
        return Arrays.asList(this.content.clone());
    }

    /**
     * allows us sort content of a {@code ContractRepository} using a <p>
     * {@code Comparator} generic and sorter, which must implement {@link com.netcracker.edu.Sorters.ISorter} interface
     *
     * @param sorter sorter, which implements {@link com.netcracker.edu.Sorters.ISorter} interface
     * @param comparator generic comparator, that will set fields which will be used by sorter
     */
    @Override
    public void sortBy(ISorter<Contract> sorter, Comparator<Contract> comparator) {
        sorter.sort(this.content, comparator);
    }

    /**
     * searches content of {@code content} field and filters it using {@code condition} predicate
     *
     * @param condition predicate generic
     * @return new {@code ContractRepository}, which contains filtered by {@code condition} repository field
     */
    @Override
    public IRepository<Contract> searchBy(Predicate<Contract> condition) {
        ContractsRepository newRepo = new ContractsRepository();
        this.toList().stream().filter(condition).forEach(newRepo::add);
        return newRepo;
    }

}
