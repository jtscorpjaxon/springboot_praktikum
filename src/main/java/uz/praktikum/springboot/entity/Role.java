package uz.praktikum.springboot.entity;

import uz.praktikum.springboot.entity.enumration.EmployeePosition;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
public class Role implements Serializable {

    public Role(String name, EmployeePosition employeePosition, Boolean canCreate, Boolean canRead, Boolean canUpdate, Boolean canDelete, Boolean hasStatisticsAccess) {
        this.name = name;
        this.employeePosition = employeePosition;
        this.canCreate = canCreate;
        this.canRead = canRead;
        this.canUpdate = canUpdate;
        this.canDelete = canDelete;
        this.hasStatisticsAccess = hasStatisticsAccess;
    }

    public Role() {
    }

    @Id
    @NotNull
    private String name;

    private EmployeePosition employeePosition;

    @Column(nullable = false)
    private Boolean canCreate;

    @Column(nullable = false)
    private Boolean canRead;

    @Column(nullable = false)
    private Boolean canUpdate;

    @Column(nullable = false)
    private Boolean canDelete;

    @Column(nullable = false)
    private Boolean hasStatisticsAccess;

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public Boolean isCanCreate() {
        return canCreate;
    }

    public void setCanCreate(boolean canCreate) {
        this.canCreate = canCreate;
    }

    public Boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public Boolean isCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public Boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public Boolean isHasStatisticsAccess() {
        return hasStatisticsAccess;
    }

    public void setHasStatisticsAccess(boolean hasStatisticsAccess) {
        this.hasStatisticsAccess = hasStatisticsAccess;
    }


}
