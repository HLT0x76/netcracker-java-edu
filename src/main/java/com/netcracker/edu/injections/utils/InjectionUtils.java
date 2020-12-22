package com.netcracker.edu.injections.utils;

import com.netcracker.edu.injections.Injector;
import com.netcracker.edu.injections.annotations.CustomInjection;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * {@link Injector}-helper for a various functionality
 * (get annotated fields, finds packets and classes by reference, etc).
 */
public class InjectionUtils {

  /**
   * Finds all annotated by {@link CustomInjection} fields
   * in class of a provided {@code instance} object.
   *
   * @param instance instance of a class
   * @return set of fields annotated by {@link CustomInjection}
   */
  public static Set<Field> findFields(Object instance) {
    Class<?> clazz = instance.getClass();
    Set<Field> set = new HashSet<>();
    for (Field field : clazz.getDeclaredFields()) {
      if (field.isAnnotationPresent(CustomInjection.class)) {
        set.add(field);
      }
    }
    return set;
  }


  /**
   * Obtains relative paths for each package string in {@code packagesNames}
   * and scans each package for class files.
   *
   * @param packagesNames packages in dot notation which will be scanned
   * @return list of {@code Class<?>} objects, which represent founded classes
   * @throws ClassNotFoundException in case founded className somehow brakes defined schema
   * @throws IOException            if I/O errors occur
   */
  public static Class<?>[] getClasses(String[] packagesNames)
          throws ClassNotFoundException, IOException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    List<Class<?>> classes = new ArrayList<>();
    for (String packageName : packagesNames) {
      String path = packageName.replace('.', '/');
      Enumeration<URL> resources = classLoader.getResources(path);
      List<File> dirs = new ArrayList<>();
      while (resources.hasMoreElements()) {
        URL resource = resources.nextElement();
        dirs.add(new File(resource.getFile()));
      }
      for (File directory : dirs) {
        classes.addAll(findClasses(directory, packageName));
      }
    }
    return classes.toArray(new Class[0]);
  }


  /**
   * Scans provided {@code directory} under {@code packageName} for more packages recursively or
   * uses it as a provider of a new element for returned list object
   * (if {@code directory} turns out to be a .class file).
   *
   * @param directory   absolute path for a directory in which function will search for class files
   * @param packageName package name for a {@code Class.forName()}
   * @return list of all founded classes or empty list if provided directory not found
   * @throws ClassNotFoundException in case founded className somehow brakes defined schema
   */
  public static List<Class<?>> findClasses(
          File directory, String packageName)
          throws ClassNotFoundException {
    List<Class<?>> classes = new ArrayList<>();
    if (!directory.exists()) {
      return classes;
    }
    File[] files = directory.listFiles();
    for (File file : files) {
      if (file.isDirectory()) {
        classes.addAll(findClasses(file, packageName + "." + file.getName()));
      } else if (file.getName().endsWith(".class")) {
        // excluding ".class" postfix from filename which is exactly 6 chars long
        String fileNameWithoutPostfix = file.getName().substring(0, file.getName().length() - 6);
        String className = packageName + '.' + fileNameWithoutPostfix;
        classes.add(Class.forName(className));
      }
    }
    return classes;
  }
}
