package com.wjc.assess.service;

import com.wjc.assess.entity.Customer;
import com.wjc.assess.entity.Totaltime;

import java.util.List;

public interface TestInterface {
    void add(Customer customer);
    void add(Totaltime totaltime);
    void delete(Customer customer);
    Customer get(int id);
    Totaltime get2(int id);
    List<Customer> getList(int from,int size);
}
