package app.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockRequest {
    private String item;
    private String name;
    private Integer mass;
    private Boolean isMetal;
}
