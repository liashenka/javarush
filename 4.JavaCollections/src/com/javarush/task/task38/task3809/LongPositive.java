package com.javarush.task.task38.task3809;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) //аннотация будет записана в class-файл и доступна во время выполнения через reflection.
public @interface LongPositive {
}
