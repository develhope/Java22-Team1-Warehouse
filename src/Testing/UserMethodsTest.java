package Testing;

import Devices.DeviceClasses;
import MenusMethods.UserMethods;
import WarehouseManagement.Cart;
import WarehouseManagement.Warehouse;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class UserMethodsTest {
    @Test
    public void fromWarehouseToCart_CorrectId(){
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        userMethods.fromWarehouseToCart(device.getId());
        assertEquals(1,cart.getDevices().size());
        assertEquals(0,warehouse.getDevices().size());
    }
    @Test
    public void fromWarehouseToCart_UncorrectId(){
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        warehouse.addDevice(device);
        userMethods.fromWarehouseToCart(-2L);
        assertEquals(0,cart.getDevices().size());
        assertEquals(1,warehouse.getDevices().size());
    }
    @Test
    public void fromCartToWarehouse_CorrectId(){
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        cart.addDevice(device);
        userMethods.fromCartToWarehouse(device.getId());
        assertEquals(0,cart.getDevices().size());
        assertEquals(1,warehouse.getDevices().size());
    }
    @Test
    public void fromCartToWarehouse_UnCorrectId(){
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        cart.addDevice(device);
        userMethods.fromCartToWarehouse(-2L);
        assertEquals(1,cart.getDevices().size());
        assertEquals(0,warehouse.getDevices().size());
    }
    @Test
    public void finalizeSale() {
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        cart.addDevice(device);
        Double result = userMethods.finalizeSale();
        assertEquals(device.getSale(), result);
        assertEquals(0,cart.getDevices().size());
    }
    @Test
    public void finalizeSale_EmptyCart() {
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        double result =userMethods.finalizeSale();
        assertEquals(0.0, result,0.0);
    }
    @Test
    public void finalizeSaleMenu() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        DeviceClasses device = new DeviceClasses(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        cart.addDevice(device);
        UserMethods userMethods = new UserMethods(warehouse,cart,false,new Scanner(System.in));
        String result = userMethods.finalizeSaleMenu();
        String expected = "Questo è il tuo prezzo finale:\"" + device.getSale() + "\n Grazie per l'acquisto, speriamo di rivederti presto.";
        assertEquals(expected,result);
    }
}
