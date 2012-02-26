package com.simpoir.elixy.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Container {

    @Basic(optional = false)
    private String name;

    @ManyToOne(optional = false)
    private Host host;

}
