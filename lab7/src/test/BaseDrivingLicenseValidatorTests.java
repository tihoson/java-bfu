import org.junit.jupiter.api.Test;
import ru.bfu.ipmit.lab7.model.DrivingLicense;
import ru.bfu.ipmit.lab7.model.ValidationResult;
import ru.bfu.ipmit.lab7.validator.BaseDrivingLicenseValidator;
import ru.bfu.ipmit.lab7.validator.DrivingLicenseValidator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

public class BaseDrivingLicenseValidatorTests {
    @Test
    public void shouldBeOk() {
        DrivingLicense license = new DrivingLicense(
                "Петров",
                "Сидоров",
                "01.01.2000",
                "qwe",
                "01.12.2027",
                "01.01.2028",
                "qwe",
                "11 22 333333",
                "qwe",
                Collections.singletonList("C"));
        DrivingLicenseValidator validator = new BaseDrivingLicenseValidator();
        ValidationResult result = validator.validate(license);
        assertEquals(new ArrayList<>(), result.getValidationErrors());
    }

    @Test
    public void shouldBeErrorOnCategories() {
        DrivingLicense license = new DrivingLicense(
                "Петров",
                "Сидоров",
                "01.01.2000",
                "qwe",
                "01.12.2027",
                "01.01.2028",
                "qwe",
                "11 22 333333",
                "qwe",
                Collections.singletonList("A"));
        DrivingLicenseValidator validator = new BaseDrivingLicenseValidator();
        ValidationResult result = validator.validate(license);
        assertEquals(
                Collections.singletonList("Categories does not contains \"C\" or \"D\""),
                result.getValidationErrors()
        );
    }

    @Test
    public void shouldBeErrorOnBlank() {
        DrivingLicense license = new DrivingLicense(
                "Петров",
                "Сидоров",
                "01.01.2000",
                "qwe",
                "01.12.2027",
                "01.01.2028",
                "",
                "11 22 333333",
                "qwe",
                Collections.singletonList("C"));
        DrivingLicenseValidator validator = new BaseDrivingLicenseValidator();
        ValidationResult result = validator.validate(license);
        assertEquals(
                Collections.singletonList("class ru.bfu.ipmit.lab7.model.DrivingLicense.issuingAuthority is blank"),
                result.getValidationErrors()
        );
    }
}
