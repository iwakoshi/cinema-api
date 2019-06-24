package br.com.iwakoshi.cinemaapi.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Fabio Iwakoshi
 *
 */
@ExtendWith(MockitoExtension.class)
public class UtilTest {

  @Test
  public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException,
      InstantiationException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    Constructor<Util> constructor = Util.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    // Act
    constructor.newInstance();

    // Then
    Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
  }
}
