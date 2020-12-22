package com.netcracker.edu.injections;

import com.netcracker.edu.injections.annotations.PackageConfig;
import com.netcracker.edu.injections.exceptions.InjectionException;
import com.netcracker.edu.injections.utils.InjectionUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Class, which implements basic DI with reflections. Supports injections
 * of a packages in a project structure (using {@link PackageConfig}) and
 * can inject into two types of fields: single object and list field.
 * Inject fields only annotated by
 * {@link com.netcracker.edu.injections.annotations.CustomInjection}.
 */
public class Injector {

  /**
   * Implements injection functionality.
   * Uses {@link InjectionUtils} for packages search
   * and fields extraction.
   *
   * @param instance instance of an injected class
   * @throws InjectionException raises when none packages
   * found or more than one found for none-list field
   */
  public static void inject(Object instance) throws InjectionException {
    Set<Field> annotatedFields = InjectionUtils.findFields(instance);

    for (Field field : annotatedFields) {
      if (field.isAnnotationPresent(PackageConfig.class)) {
        PackageConfig config = field.getAnnotation(PackageConfig.class);
        Class<?>[] packages = null;
        try {
          packages = InjectionUtils.getClasses(config.packages());
        } catch (IOException | ClassNotFoundException e) {
          throw new InjectionException(
                  "files or/and classes corresponding to them not found: " + String.join(
                          ", ", config.packages()));
        }
        if (packages.length == 0) {
          throw new InjectionException(
                  "unknown paths: " + String.join(", ", config.packages()));
        }
        try {
          field.setAccessible(true);
          if (Collection.class.isAssignableFrom(field.getType())) {
            // returns something like List<T<K>> in case of CsvRepositoryParser
            ParameterizedType wholeFieldType = (ParameterizedType) field.getGenericType();
            Class<?> targetClass = Object.class;
            // at that point we can have either T<K> when list elements are parameterized
            // or just T. we can check for both
            try {
              // in case of T<K>
              ParameterizedType targetType =
                      (ParameterizedType) wholeFieldType.getActualTypeArguments()[0];
              targetClass = (Class<?>) targetType.getRawType();
            } catch (ClassCastException e) {
              // in case of T
              targetClass = (Class<?>) wholeFieldType.getActualTypeArguments()[0];
            }
            List<Object> injectedList = new ArrayList<>();
            for (Class<?> injectedPackage : packages) {
              if (targetClass.isAssignableFrom(injectedPackage)) {
                injectedList.add(injectedPackage.getDeclaredConstructor().newInstance());
              }
            }
            field.set(instance, injectedList);
          } else if (!Collection.class.isAssignableFrom(field.getType()) & packages.length > 1) {
            throw new InjectionException(
                    "more then one package found for non-list type field");
          } else if (packages.length == 1) {
            Class<?> injectedPackage = packages[0];
            field.set(instance, injectedPackage.getDeclaredConstructor().newInstance());
          }
        } catch (IllegalAccessException | InstantiationException
                | NoSuchMethodException | InvocationTargetException e) {
          throw new InjectionException(e.toString());
        }
      }
    }
  }
}
