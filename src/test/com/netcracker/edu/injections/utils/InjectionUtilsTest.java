package com.netcracker.edu.injections.utils;

import com.netcracker.edu.repository.ContractsRepository;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link InjectionUtils} functionality tests (class search, annotated fields lookup, etc).
 */
public class InjectionUtilsTest {

  @Test
  public void findFields() throws NoSuchFieldException {
    ContractsRepository repo = new ContractsRepository();
    Set<Field> fields = InjectionUtils.findFields(repo);
    Assert.assertTrue(fields.contains(ContractsRepository.class.getDeclaredField("sorter")));
  }

  @Test
  public void getClasses() throws IOException, ClassNotFoundException {
    Class<?>[] clazz = InjectionUtils.getClasses(new String[]{"com.netcracker.edu.sorters.bubble"});
    Assert.assertEquals(clazz.length, 1);
    String expected = "com.netcracker.edu.sorters.bubble.BubbleSorter";
    String actual = clazz[0].getCanonicalName();
    Assert.assertEquals(expected, actual);
  }
}