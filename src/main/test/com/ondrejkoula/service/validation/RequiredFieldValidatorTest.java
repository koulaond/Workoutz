package com.ondrejkoula.service.validation;

import com.ondrejkoula.exception.Errors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.ondrejkoula.exception.Errors.VALIDATION_MISSING_FIELD_CONTENT;
import static org.junit.jupiter.api.Assertions.*;

class RequiredFieldValidatorTest {

    @Test
    void whenFieldValueIsNull_thenPutErrorToMessages() {
        RequiredFieldValidator validator = new RequiredFieldValidator("field", null);
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        Assertions.assertThat(validationMessages).contains(Map.entry("field", VALIDATION_MISSING_FIELD_CONTENT));
    }


    @Test
    void whenFieldValueIsBlank_thenPutErrorToMessages() {
        RequiredFieldValidator validator = new RequiredFieldValidator("field", "");
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        Assertions.assertThat(validationMessages).contains(Map.entry("field", VALIDATION_MISSING_FIELD_CONTENT));
    }

    @Test
    void whenFieldValueIsNotBlankNorNull_thenDoNotPutErrorToMessages() {
        RequiredFieldValidator validator = new RequiredFieldValidator("field", "nonEmptyValue");
        HashMap<String, String> validationMessages = new HashMap<>();

        validator.validateFieldValue(validationMessages);

        Assertions.assertThat(validationMessages).isEmpty();
    }
}