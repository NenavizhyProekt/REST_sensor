package ru.danil.rest_practice.rest_proj.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    @NotNull
    @DecimalMax(value = "100.0")
    @DecimalMin(value = "-100.0")
    private float value;

    @Column(name = "is_raining")
    @NotNull
    private boolean isRaining;

    @Column(name = "recording_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordingTime;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Sensor sensor;

    public Measurement() {
    }

    public Measurement(float value, boolean isRaining, Date recordingTime, Sensor sensor) {
        this.value = value;
        this.isRaining = isRaining;
        this.recordingTime = recordingTime;
        this.sensor = sensor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Date getRecordingTime() {
        return recordingTime;
    }

    public void setRecordingTime(Date recordingTime) {
        this.recordingTime = recordingTime;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", isRaining=" + isRaining +
                ", sensor=" + sensor.getName() +
                '}';
    }
}
