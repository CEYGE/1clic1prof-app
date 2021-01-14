package fr.clic1prof.util;

import javax.annotation.Nullable;

public interface DataListener<T> {

    void onSuccess(@Nullable T value);

    void onError(String message);

}
