package day6.dawnoneline.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Posting extends TimeStamp {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String content;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;

    public void setContent(String content) {
        this.content = content;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCoordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
