package com.ondrejkoula.dto.error;

import com.ondrejkoula.exception.*;
import com.ondrejkoula.exception.converters.MessageCodes;
import com.ondrejkoula.exception.validation.ValidationException;

public class ErrorDetailDtoFactory {
    
    public static DefaultErrorDetailDto fromGeneralError(Exception ex) {
        return DefaultErrorDetailDto.builder().errorMessage(ex.getMessage()).messageCode(MessageCodes.GENERAL_ERROR).build();
    }
    
    public static ValidationErrorDetailDto fromValidationError(ValidationException ex) {
        return ValidationErrorDetailDto.builder()
                .errorMessage("Validation constraints violated.")
                .messageCode(MessageCodes.VALIDATION_ERROR)
                .constraintViolations(ex.getConstraintViolations())
                .build();
    }

    public static CascadeDependenciesErrorDetailDto fromCascadeDependenciesError(CascadeDependenciesException ex) {
        return CascadeDependenciesErrorDetailDto.builder()
                .errorMessage("Unable to finish an operation die to existing dependencies.")
                .parentEntityId(ex.getParentEntityId())
                .messageCode(MessageCodes.CASCADE_DEPENDENCIES)
                .dependenciesOccurrences(ex.getTypesAndOccurrences())
                .build();
    }

    public static DefaultErrorDetailDto fromDataNotFoundError(DataNotFoundException ex) {
        return DefaultErrorDetailDto.builder()
                .errorMessage("Data not found")
                .messageCode(MessageCodes.NOT_FOUND)
                .build();
    }

    public static InconsistentDataUpdateErrorDetailDto fromInconsistentDataUpdateError(InconsistentDataUpdateException ex) {
        return InconsistentDataUpdateErrorDetailDto.builder()
                .errorMessage("Cannot finish update doe to inconsistent data types received.")
                .messageCode(MessageCodes.INCONSISTENT_DATA)
                .entityId(ex.getEntityId())
                .fieldName(ex.getFieldName())
                .expectedDataType(ex.getFieldType().getSimpleName())
                .receivedValue(ex.getValue())
                .build();
    }

    public static DefaultErrorDetailDto fromInconsistentPositionsError(InconsistentPositionsException ex) {
        return DefaultErrorDetailDto.builder().errorMessage(ex.getMessage()).messageCode(MessageCodes.INCONSISTENT_POSITIONS).build();
    }

    public static IncorrectParentErrorDetailDto fromIncorrectParentError(IncorrectParentException ex) {
        return IncorrectParentErrorDetailDto.builder()
                .errorMessage("Parent ID is incorrect and is not bound with this child item.")
                .messageCode(MessageCodes.INCORRECT_PARENT)
                .invalidParentId(ex.getInvalidParentId())
                .childId(ex.getChildId())
                .build();
    }

    public static MissingDataForFieldUpdateErrorDetailDto fromMissingDataForFieldUpdateError(MissingDataForFieldUpdateException ex) {
        return MissingDataForFieldUpdateErrorDetailDto.builder()
                .errorMessage("Missing data for field update.")
                .messageCode(MessageCodes.MISSING_DATA_FOR_FIELD_UPDATE)
                .fieldName(ex.getFieldName())
                .entityId(ex.getEntityId())
                .build();
    }


    public static MissingDataOnSaveErrorDetailDto fromMissingDataOnSaveError(MissingDataOnSaveException ex) {
        return MissingDataOnSaveErrorDetailDto.builder()
                .errorMessage("Required data is missing on save.")
                .messageCode(MessageCodes.MISSING_DATA_ON_SAVE)
                .errorMessages(ex.getErrorMessages())
                .build();
    }

    public static OutOfTimeWindowErrorDetailDto fromOutOfTimeWindowError(OutOfTimeWindowException ex) {
        return OutOfTimeWindowErrorDetailDto.builder()
                .errorMessage("Inserted time does not satisfy time window restrictions.")
                .messageCode(MessageCodes.OUT_OF_TIME_WINDOW)
                .time(ex.getTime())
                .when(ex.getWhen())
                .windowTime(ex.getWindowTime())
                .build();
    }

    public static ParentNotFoundErrorDetailDto fromParentNotFoundError(ParentNotFoundException ex) {
        return ParentNotFoundErrorDetailDto.builder()
                .errorMessage("Parent not found.")
                .messageCode(MessageCodes.PARENT_NOT_FOUND)
                .id(ex.getId())
                .type(ex.getType())
                .build();
    }

    public static PositionOutOfRangeErrorDetailDto fromPositionOutOfRangeError(PositionOutOfRangeException ex) {
        return PositionOutOfRangeErrorDetailDto.builder()
                .errorMessage("Position is out of range.")
                .messageCode(MessageCodes.POSITION_OUT_OF_RANGE)
                .position(ex.getPosition())
                .childType(ex.getChildType())
                .parentType(ex.getParentType())
                .size(ex.getSize())
                .build();
    }

    public static UnsupportedChangeTypeErrorDetailDto fromUnsupportedChangeTypeError(UnsupportedChangeTypeException ex) {
        return UnsupportedChangeTypeErrorDetailDto.builder()
                .errorMessage("Unsupported data change operation type.")
                .messageCode(MessageCodes.UNSUPPORTED_CHANGE_TYPE)
                .operation(ex.getOperation())
                .entityId(ex.getEntityId())
                .fieldName(ex.getFieldName())
                .build();
    }

    public static UnsupportedCompositeChangeTypeErrorDetailDto fromUnsupportedCompositeChangeTypeError(UnsupportedCompositeChangeTypeException ex) {
        return UnsupportedCompositeChangeTypeErrorDetailDto.builder()
                .errorMessage("Unsupported data change operation type in parent-child relationship.")
                .messageCode(MessageCodes.UNSUPPORTED_COMPOSITE_CHANGE_TYPE)
                .unsupportedChangeType(ex.getUnsupportedChangeType())
                .parentEntityId(ex.getParentEntityId())
                .build();
    }
}
