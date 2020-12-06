package fr.clic1prof.network;

public interface NetworkProvider {

    <T> T getService(Class<T> clazz);
}
