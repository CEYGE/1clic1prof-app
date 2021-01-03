package fr.clic1prof.util;

import javax.annotation.Nullable;

public interface DataListener<T> {

    void onSuccess(@Nullable T value);

    void onFailure(Throwable throwable, String message);
}
