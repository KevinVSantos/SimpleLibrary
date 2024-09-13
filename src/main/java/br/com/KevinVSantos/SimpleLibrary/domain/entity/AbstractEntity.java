package br.com.KevinVSantos.SimpleLibrary.domain.entity;

import java.io.Serializable;

public abstract class AbstractEntity<T> implements Serializable {

    public abstract T getGenericId();

}
