package massimomauro.S7L2BCryptAuthorizationCORS.controllers;


import massimomauro.S7L2BCryptAuthorizationCORS.entities.Device;
import massimomauro.S7L2BCryptAuthorizationCORS.payloads.NewDevicePayload;
import massimomauro.S7L2BCryptAuthorizationCORS.services.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DevicesController {
    @Autowired
    DevicesService devicesService;

    // 1. - POST http://localhost:3001/devices (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Device saveDevice() {
        return devicesService.save();
    }

    // 2. - GET http://localhost:3001/devices
    @GetMapping("")
    public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return devicesService.getDevices(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/devices/{id}
    @GetMapping("/{deviceId}")
    public Device findById(@PathVariable int deviceId) {
        return devicesService.findById(deviceId);
    }

    // 4. - PUT http://localhost:3001/devices/{id} (+ req.body)
    @PutMapping("/{deviceId}")
    public Device findAndUpdate(@PathVariable int deviceId, @RequestBody NewDevicePayload body) {
        return devicesService.findByIdAndSetStatus(deviceId, body);
    }

    // 5. - DELETE http://localhost:3001/devices/{id
    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int deviceId) {
        devicesService.findByIdAndDelete(deviceId);
    }


}
