package ua.pomoc.helpoffers.model;

import ua.pomoc.helpoffers.exception.BusinessException;

public enum BooleanType {
    TRUE("tak"),
    FALSE("nie");

    private final String value;

    BooleanType(String value) {
        this.value = value;
    }

    public static Boolean getBoolean(String value) {
        for (BooleanType type: BooleanType.values()) {
            if (type.value.equals(value.toLowerCase())) {
                return type == TRUE;
            }
        }
        throw new BusinessException("No BooleanType constant for: " +  value);
    }
}
