package br.com.fiap.abctechservice.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "operator_id", nullable = false)
    private Long operatorId;

    @ManyToMany
    private List<Assistance> services;

    @OneToOne
    @JoinColumn(name = "start_order_location_id")
    private OrderLocation startOrderLocation;

    @OneToOne
    @JoinColumn(name = "end_order_location_id")
    private OrderLocation endOrderLocation;
    
    public boolean hasMinAssists() {
        return services.size() > 0;
    }
    
    public boolean exceedsMaxAssists() {
        return services.size() > 15;
    }
}
