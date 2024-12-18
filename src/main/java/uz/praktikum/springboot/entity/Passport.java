package uz.praktikum.springboot.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.io.Serializable;
import java.time.Instant;

@Entity
public class Passport implements Serializable {
    public Passport(String passportSerial, String passportNumber, String JSHSHIR, String nation) {
        this.passportSerial = passportSerial;
        this.passportNumber = passportNumber;
        this.JSHSHIR = JSHSHIR;
        this.nation = nation;
    }

    public Passport() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 5)
    @Column(length = 5,  nullable = false)
    private String passportSerial;

    @NotNull
    @Size(min = 5, max = 10)
    @Column(length = 5,  nullable = false)
    private String passportNumber;

    @NotNull
    @Size(min = 14, max = 16)
    @Column(length = 20,  nullable = false)
    private String JSHSHIR;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(length = 20,  nullable = false)
    private String nation;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;


    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public @NotNull @Size(min = 1, max = 5) String getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(@NotNull @Size(min = 1, max = 5) String passportSerial) {
        this.passportSerial = passportSerial;
    }

    public @NotNull @Size(min = 5, max = 10) String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(@NotNull @Size(min = 5, max = 10) String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public @NotNull @Size(min = 14, max = 16) String getJSHSHIR() {
        return JSHSHIR;
    }

    public void setJSHSHIR(@NotNull @Size(min = 14, max = 16) String JSHSHIR) {
        this.JSHSHIR = JSHSHIR;
    }

    public @NotNull @Size(min = 2, max = 20) String getNation() {
        return nation;
    }

    public void setNation(@NotNull @Size(min = 2, max = 20) String nation) {
        this.nation = nation;
    }
}
