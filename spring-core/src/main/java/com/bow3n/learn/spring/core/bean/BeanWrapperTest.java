package com.bow3n.learn.spring.core.bean;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

/**
 * @author bowen
 */
public class BeanWrapperTest {
    public static void main(String[] args) {
        BeanWrapper company = new BeanWrapperImpl(new Company());
        company.setPropertyValue("name", "Some Company Inc.");
        PropertyValue value = new PropertyValue("name", "Some Company Inc.");
        company.setPropertyValue(value);
        // ok, let's create the director and tie it to the company:
        BeanWrapper jim = new BeanWrapperImpl(new Employee());
        jim.setPropertyValue("name", "Jim Stravinsky");
        company.setPropertyValue("managingDirector", jim.getWrappedInstance());

        Float salary = (Float) company.getPropertyValue("managingDirector.salary");
        System.out.println(salary);

        Company c = (Company) company.getWrappedInstance();
        System.out.println(c.getName());
    }
}
