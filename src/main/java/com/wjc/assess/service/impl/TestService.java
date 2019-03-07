package com.wjc.assess.service.impl;

import com.wjc.assess.dao.CustomerMapper;
import com.wjc.assess.dao.TotaltimeMapper;
import com.wjc.assess.entity.Customer;
import com.wjc.assess.entity.Totaltime;
import com.wjc.assess.service.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService implements TestInterface {

    @Autowired
    private CustomerMapper dao;

    @Autowired
    private TotaltimeMapper dao2;

    @Override
    public void add(Customer customer) {
        dao.insert(customer);
    }

    @Override
    public void add(Totaltime totaltime){
        dao2.insert(totaltime);
    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public Customer get(int id) {
        Customer customer = dao.selectByPrimaryKey(0);
        return customer;
    }

    public Totaltime get2(int id){
        Totaltime totaltime = dao2.selectByPrimaryKey(id);
        return totaltime;
    }

    @Override
    public List<Customer> getList(int from, int size) {

        return null;
    }
}
