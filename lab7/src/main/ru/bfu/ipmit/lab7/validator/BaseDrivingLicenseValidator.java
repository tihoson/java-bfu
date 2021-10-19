package ru.bfu.ipmit.lab7.validator;

import ru.bfu.ipmit.lab7.model.DrivingLicense;
import ru.bfu.ipmit.lab7.model.ValidationResult;
import ru.bfu.ipmit.lab7.validation.crossfield.ValidAge;
import ru.bfu.ipmit.lab7.validation.crossfield.ValidDatesRelation;
import ru.bfu.ipmit.lab7.validation.field.NotBlank;
import ru.bfu.ipmit.lab7.validation.field.ValidCategories;
import ru.bfu.ipmit.lab7.validation.field.ValidDate;
import ru.bfu.ipmit.lab7.validation.field.ValidLicenseNumber;

import javax.swing.text.DateFormatter;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class BaseDrivingLicenseValidator implements DrivingLicenseValidator {

    @Override
    public ValidationResult validate(DrivingLicense drivingLicense) {
        ValidationResult result = new ValidationResult();

        Class<?> licenseClass = drivingLicense.getClass();
        for (Field field: licenseClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ValidDate.class)) {
                result.concatResult(IsValidDate(field, drivingLicense));
            }
            if (field.isAnnotationPresent(NotBlank.class)) {
                result.concatResult(IsNotBlank(field, drivingLicense));
            }
            if (field.isAnnotationPresent(ValidCategories.class)) {
                result.concatResult(IsValidCategories(field, drivingLicense));
            }
            if (field.isAnnotationPresent(ValidLicenseNumber.class)) {
                result.concatResult(IsValidLicenseNumber(field, drivingLicense));
            }
        }
        if (licenseClass.isAnnotationPresent(ValidAge.class)) {
            result.concatResult(this.IsValidAge(drivingLicense));
        }
        if (licenseClass.isAnnotationPresent(ValidDatesRelation.class)) {
            result.concatResult(this.IsValidDatesRelation(drivingLicense));
        }

        return result;
    }

    private ValidationResult IsValidAge(DrivingLicense drivingLicense) {
        List<String> categories = drivingLicense.getCategories();

        ValidationResult result = new ValidationResult();
        long years;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            LocalDate validDate = LocalDate.parse(drivingLicense.getValidFrom(), formatter);
            LocalDate birthDate = LocalDate.parse(drivingLicense.getDateOfBirth(), formatter);
            years = ChronoUnit.YEARS.between(birthDate, validDate);
        } catch (Exception e) {
            result.addValidationError(e.getLocalizedMessage());
            return result;
        }

        long validAge = categories.contains("C") || categories.contains("D") ? 21 : 18;

        result.setValid(years >= validAge);
        if (!result.isValid()) {
            result.addValidationError("Incorrect valid date");
        }

        return result;
    }

    private ValidationResult IsValidDatesRelation(DrivingLicense drivingLicense) {
        ValidationResult result = new ValidationResult();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            LocalDate validFromDate = LocalDate.parse(drivingLicense.getValidFrom(), formatter);
            LocalDate validToDate = LocalDate.parse(drivingLicense.getValidTo(), formatter);
            LocalDate birthDate = LocalDate.parse(drivingLicense.getDateOfBirth(), formatter);

            result.setValid(validFromDate.isAfter(birthDate));
            if (!result.isValid()) {
                result.addValidationError("validFrom date must be after birth date");
            }
            result.setValid(validFromDate.isBefore(validToDate));
            if (!result.isValid()) {
                result.addValidationError("validTo date must be after validFrom date");
            }
        }  catch (Exception e) {
            result.addValidationError(e.getLocalizedMessage());
        }

        return result;
    }

    private ValidationResult IsNotBlank(Field field, Object object) {
        ValidationResult result = new ValidationResult();
        try {
            Object fieldFromClass = field.get(object);
            if (fieldFromClass instanceof String) {
                result.setValid(!fieldFromClass.toString().isBlank());
                if (!result.isValid()) {
                    result.addValidationError(object.getClass() + "." + field.getName() + " is blank");
                }
            } else {
                result.addValidationError(
                        object.getClass() + "." + field.getName() + " is not string, but " + field.getType()
                );
            }
        } catch (Exception e) {
            result.addValidationError(e.getLocalizedMessage());
        }
        return result;
    }

    private ValidationResult IsValidCategories(Field field, Object object) {
        ValidationResult result = new ValidationResult();
        try {
            Object fieldFromClass = field.get(object);
            if (fieldFromClass instanceof List) {
                List<?> categories = (List<?>)fieldFromClass;
                result.setValid(categories.size() > 0 &&
                        (categories.stream().anyMatch(category -> category.equals("C") || category.equals("D")))
                );
                if (!result.isValid()) {
                    result.addValidationError("Categories does not contains \"C\" or \"D\"");
                }
            } else {
                result.addValidationError(
                        object.getClass() + "." + field.getName() + " is not List, but " + field.getType()
                );
            }
        } catch (Exception e) {
            result.addValidationError(e.getLocalizedMessage());
        }
        return result;
    }

    private ValidationResult IsValidDate(Field field, Object object) {
        ValidationResult result = new ValidationResult();
        try {
            Object fieldFromClass = field.get(object);
            if (fieldFromClass.getClass().isAssignableFrom(String.class)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate.parse(fieldFromClass.toString(), formatter);
            } else {
                result.addValidationError(
                        object.getClass() + "." + field.getName() + " is not string, but " + field.getType()
                );
            }
        } catch (Exception e) {
            result.addValidationError(object.getClass() + "." + field.getName() + ": " + e.getLocalizedMessage());
        }
        return result;
    }

    private ValidationResult IsValidLicenseNumber(Field field, Object object) {
        ValidationResult result = new ValidationResult();
        try {
            Object fieldFromClass = field.get(object);
            if (fieldFromClass.getClass().isAssignableFrom(String.class)) {
                String licenseNumber = fieldFromClass.toString();
                if (Pattern.matches("\\d{2} \\d{2} \\d{6}", licenseNumber))
                    result.setValid(true);
                else {
                    result.addValidationError(
                            "Incorrect license number format for " + object.getClass() + "." + field.getName()
                    );
                }
            } else {
                result.addValidationError(
                        object.getClass() + "." + field.getName() + " is not string, but " + field.getType()
                );
            }
        } catch (Exception e) {
            result.addValidationError(e.getLocalizedMessage());
        }
        return result;
    }
}
