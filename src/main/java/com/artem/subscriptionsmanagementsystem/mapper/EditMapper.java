package com.artem.subscriptionsmanagementsystem.mapper;

public interface EditMapper<F, T> {

    T map(F fromObject, T toObject);

}
