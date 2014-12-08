package com.epam.darafeyeu.domain;

import javax.persistence.*;

/**
 * Created by Alex on 07.12.14.
 */
@Entity
public class EmployeePersonalInfo {
    @Id @GeneratedValue
    @Column(name = "PINFO_ID")
    private int personalInfoId;
    private EmployeeStatus employeeStatus = EmployeeStatus.JUNIOR;

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
