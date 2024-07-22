package com.ytu.todolist.aop.annotations;
import com.ytu.todolist.aop.crossCuttingConcerns.CategoryNameMustBeUniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CategoryNameMustBeUniqueValidator.class})
public @interface CategoryNameMustBeUnique {
    String message() default "Kategori Adı Benzersiz olmalıdır.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
