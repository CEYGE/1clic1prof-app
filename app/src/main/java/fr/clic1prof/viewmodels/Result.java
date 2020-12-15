package fr.clic1prof.viewmodels;

public class Result<T> {

    private final T data;
    private final ResultType type;

    private Result(T data, ResultType type) {
        this.data = data;
        this.type = type;
    }

    public T getData() {
        return this.data;
    }

    public ResultType getType() {
        return this.type;
    }

    public static <T> Result<T> loading() {
        return new Result<>(null, ResultType.LOADING);
    }

    public static <T> Result<T> error() {
        return new Result<>(null, ResultType.ERROR);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, ResultType.SUCCESS);
    }
}
