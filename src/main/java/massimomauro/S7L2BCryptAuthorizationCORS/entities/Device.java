package massimomauro.S7L2BCryptAuthorizationCORS.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import massimomauro.S7L2BCryptAuthorizationCORS.enums.DeviceStatus;

@Getter
@Setter
@ToString
@Entity
@Table(name="devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private DeviceStatus deviceStatus;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
}
