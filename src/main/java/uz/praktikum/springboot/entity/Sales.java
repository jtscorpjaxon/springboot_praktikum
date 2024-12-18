package uz.praktikum.springboot.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uz.praktikum.springboot.entity.enumration.AdsType;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table
public class Sales implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(0)
    private Float amount;

    @NotNull
    private Instant duration;

    @NotNull
    @Enumerated(EnumType.STRING) // Enum qiymatini saqlash
    private AdsType adsType;

    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public @NotNull @Min(0) Float getAmount() {
        return amount;
    }

    public void setAmount(@NotNull @Min(0) Float amount) {
        this.amount = amount;
    }

    public @NotNull Instant getDuration() {
        return duration;
    }

    public void setDuration(@NotNull Instant duration) {
        this.duration = duration;
    }

    public @NotNull AdsType getAdsType() {
        return adsType;
    }

    public void setAdsType(@NotNull AdsType adsType) {
        this.adsType = adsType;
    }

    public @NotNull @FutureOrPresent() Instant getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(@NotNull @FutureOrPresent() Instant beginDate) {
        this.beginDate = beginDate;
    }

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true, nullable = false)
    private Employee employee;

    @NotNull
    @FutureOrPresent()
    private Instant beginDate;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;



}
