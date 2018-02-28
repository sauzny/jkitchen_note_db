package com.sauzny.dbutils.entity.randomdata;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.sauzny.dbutils.entity.Orders;
import com.sauzny.dbutils.entity.Person;
import com.sauzny.dbutils.entity.Products;

public class BuildDemo {

    public static Person getOnePerson(){

        LocalDateTime start = LocalDateTime.parse("1980-01-01T00:00:00.000");
        LocalDateTime end = LocalDateTime.parse("2005-01-01T00:00:00.000");
        
        int gender = RandomPersonUtils.getGender_int();
        LocalDateTime localDateTime = RandomDateTimeUtils.getTime(start, end);
        
        Person person = new Person();
        person.setAge(LocalDateTime.now().getYear()-localDateTime.getYear());
        person.setGender(RandomPersonUtils.getGender_zh(gender));
        person.setHometown(RandomPersonUtils.getRoad());
        person.setName(RandomPersonUtils.getChineseName(gender));
        person.setPhone(RandomPersonUtils.getTel());
        person.setEmail(RandomPersonUtils.getEmail(6, 10));
        person.setBalance(RandomUtils.nextDouble(2.5, 8999.99));
        person.setBirthday(localDateTime.toLocalDate());
        return person;
    }
    
    public static Products getOneProduct(){
        
        int oldPrice = RandomUtils.nextInt(100, 10000);
        double newPrice = oldPrice*RandomUtils.nextInt(6, 10)/10;
        
        Products products = new Products();
        products.setName(RandomStringUtils.randomAlphabetic(1).toUpperCase() + "-" + RandomStringUtils.randomAlphabetic(6).toLowerCase());
        products.setStatus(RandomUtils.nextInt(1, 3));
        products.setOldPrice(oldPrice/10);
        products.setNowPrice(newPrice/10);
        return products;
    }
    
    public static Orders getOneOrder(Person person, Products product){

        LocalDateTime start = LocalDateTime.parse("2012-01-01T00:00:00.000");
        LocalDateTime end = LocalDateTime.now();
        
        Orders orders = new Orders();
        orders.setCreateTime(RandomDateTimeUtils.getTime(start, end));
        orders.setCostPrice(product.getNowPrice());
        orders.setPersonId(person.getId());
        orders.setProductId(product.getId());
        orders.setStatus(RandomUtils.nextInt(1, 6));
        orders.setType(RandomUtils.nextInt(1, 3));
        return orders;
    }
    
    public static Person getOnePerson(int id){
        Person person = getOnePerson();
        person.setId(id);
        return person;
    }
    
    public static Products getOneProduct(int id){
        
        Products products = getOneProduct();
        products.setId(id);
        return products;
    }
    
    public static Orders getOneOrder(int id, Person person, Products product){
        Orders orders = getOneOrder(person, product);
        orders.setId(id);
        return orders;
    }
    
    @Test
    public void foo01() throws IOException{
        
        List<Products> productsList = Lists.newArrayList();
        List<Person> personList = Lists.newArrayList();
        List<Orders> ordersList = Lists.newArrayList();

        int productsNum = 200;
        int personsNum = 3000;
        
        for(int i=1;i<=productsNum;i++){
            Products products = BuildDemo.getOneProduct(i);
            productsList.add(products);
        }
        
        int temp=0;
        for(int i=1;i<=personsNum;i++){
            
            int orderNum = RandomUtils.nextInt(8, 50);
            Person person = BuildDemo.getOnePerson(i);
            personList.add(person);
            for(int j=1;j<=orderNum;j++){
                Orders orders = BuildDemo.getOneOrder(j+temp, person, productsList.get(RandomUtils.nextInt(1, productsNum)));
                ordersList.add(orders);
            }
            temp += orderNum;
        }
        
        String productTitle = "id\tname\tstatus\tnowPrice\toldPrice\r\n";
        String personTitle = "id\tname\tage\tgender\thometown\tphone\temail\tbirthday\tbalance\r\n";
        String ordersTitle = "id\tstatus\ttype\tproductId\tpersonId\tcreateTime\tcostPrice\tcostScore\r\n";

        Files.write(Paths.get("/data/demo/v2/productsList.txt"), productTitle.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        Files.write(Paths.get("/data/demo/v2/personList.txt"), personTitle.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        Files.write(Paths.get("/data/demo/v2/ordersList.txt"), ordersTitle.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        
        Files.write(Paths.get("/data/demo/v2/productsList.txt"), productsList, StandardOpenOption.APPEND);
        Files.write(Paths.get("/data/demo/v2/personList.txt"), personList, StandardOpenOption.APPEND);
        Files.write(Paths.get("/data/demo/v2/ordersList.txt"), ordersList, StandardOpenOption.APPEND);
    }
}
