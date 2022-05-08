package com.ondrejkoula.service.validation;

import java.util.Map;

public interface FieldValidator {

    void validateFieldValue(Map<String, String> validationMessages);
}
