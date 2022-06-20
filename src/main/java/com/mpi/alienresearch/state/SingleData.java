package com.mpi.alienresearch.state;

public class SingleData<T> {
    T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SingleData() {
    }
}
