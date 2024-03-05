package Testing;

import Devices.DeviceClasses;
import WarehouseManagement.Cart;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartTest {
    @Test
    public void addDevice_validDevice() {
        Cart cart = new Cart();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);

        assertTrue(cart.addDevice(device));
        assertEquals(1, cart.getDevices().size());
    }

    @Test
    public void addDevice_invalidDevice() {
        Cart cart = new Cart();

        assertFalse(cart.addDevice(null));
        assertEquals(0, cart.getDevices().size());
    }

    @Test
    public void addDevice_existingId() {
        Cart cart = new Cart();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);

        assertTrue(cart.addDevice(device));
        assertFalse(cart.addDevice(device));
        assertEquals(1, cart.getDevices().size());
    }

    @Test
    public void removeDeviceById_existingId() {
        Cart cart = new Cart();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);

        cart.addDevice(device);
//        assertTrue(cart.removeDeviceById(device.getId()));
        assertEquals(0, cart.getDevices().size());
    }

    @Test
    public void removeDeviceById_notExistingId() {
        Cart cart = new Cart();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);

        cart.addDevice(device);

//        assertFalse(cart.removeDeviceById(34242L));
        assertEquals(1, cart.getDevices().size());
    }

    @Test
    public void removeDeviceById_null() {
        Cart cart = new Cart();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);

        cart.addDevice(device);

//        assertFalse(cart.removeDeviceById(null));
        assertEquals(1, cart.getDevices().size());
    }

    @Test
    public void getDeviceById_existingId() {
        Cart cart = new Cart();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        cart.addDevice(device);

        DeviceClasses result = cart.getDeviceById(device.getId());

        assertEquals(device, result);
    }

    @Test
    public void getDeviceById_notExistingId() {
        Cart cart = new Cart();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        cart.addDevice(device);

        DeviceClasses result = cart.getDeviceById(412421L);

        assertNull(result);
    }

    @Test
    public void getDeviceById_null() {
        Cart cart = new Cart();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        cart.addDevice(device);

        DeviceClasses result = cart.getDeviceById(null);

        assertNull(result);
    }

    @Test
    public void getFinalPrice_notEmptyCart() {
        Cart cart = new Cart();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        cart.addDevice(device);

        double result = cart.getFinalPrice();

        assertEquals(device.getSale(), result, 0.1);
    }

    @Test
    public void getFinalPrice_emptyCart() {
        Cart cart = new Cart();

        double result = cart.getFinalPrice();

        assertEquals(0, result, 0.1);
    }
}
