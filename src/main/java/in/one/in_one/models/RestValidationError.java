package in.one.in_one.models;

public record RestValidationError(
        Integer code,
        String field,
        String message) {
}