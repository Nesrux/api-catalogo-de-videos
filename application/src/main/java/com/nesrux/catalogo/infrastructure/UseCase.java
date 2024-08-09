package com.nesrux.catalogo.infrastructure;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);

}