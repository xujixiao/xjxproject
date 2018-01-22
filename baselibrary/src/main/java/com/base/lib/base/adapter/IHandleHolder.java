package com.base.lib.base.adapter;


public interface IHandleHolder<E, B> {
    void handleHolder(E entity, int position, B binding);
}
