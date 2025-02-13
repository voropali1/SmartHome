package org.example.entity;

import org.example.entity.residents.Resident;

public interface Object {
    String getName();
    void use(Resident resident);

    void afterUse(Resident resident);

    double getUsageTime();
    boolean isUsing();

}