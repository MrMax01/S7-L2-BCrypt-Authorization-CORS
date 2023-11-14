package massimomauro.S7L2BCryptAuthorizationCORS.payloads;

import lombok.Getter;
import massimomauro.S7L1SpringSecurityJWT.enums.DeviceStatus;


@Getter
public class NewDevicePayload {
    private int userId;
    private DeviceStatus deviceStatus;
}
