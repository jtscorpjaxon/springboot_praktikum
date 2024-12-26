package uz.praktikum.springboot.entity;

import uz.praktikum.springboot.entity.enumration.EmployeePosition;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
public class Role implements Serializable {

    public Role(String apiName, Boolean canCreate, Boolean canRead, Boolean canUpdate, Boolean canDelete, Boolean hasStatisticsAccess) {
        this.apiName = apiName;
        this.canCreate = canCreate;
        this.canRead = canRead;
        this.canUpdate = canUpdate;
        this.canDelete = canDelete;
        this.hasStatisticsAccess = hasStatisticsAccess;
    }

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String apiName;

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

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
