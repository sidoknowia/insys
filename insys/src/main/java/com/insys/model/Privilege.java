package com.insys.model;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Privilege {
	
	@Getter @Setter
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Getter @Setter
    private String name;
	
	@Getter @Setter
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege() {
        super();
    }

    public Privilege(final String name) {
        super();
        this.name = name;
    }
}
