/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dastex.dasangebotword.managedbeans;

import com.dastex.dasangebotword.dao.DocDaoInterface;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author aladhari
 */
@Named(value = "globalManagedBean")
@Dependent
public class GlobalManagedBean {

    @EJB
    DocDaoInterface daoInterface;
    /**
     * Creates a new instance of GlobalManagedBean
     */
    public GlobalManagedBean() {
    }
    
}
