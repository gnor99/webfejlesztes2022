package app.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    private String item;
    private String name;
    private Integer mass;
    private Boolean isMetal;

    public Stock(String item, String name, Integer mass, Boolean isMetal) {
        this.item = item;
        this.name = name;
        this.mass = mass;
        this.isMetal = isMetal;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", name='" + name + '\'' +
                ", mass=" + mass +
                ", isMetal=" + isMetal +
                '}';
    }
}
