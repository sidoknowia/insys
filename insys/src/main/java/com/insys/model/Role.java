package com.insys.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	
	@Getter
	@ManyToMany(mappedBy = "roles")
	private Collection<User> users;
	
	@Getter
	@Setter
	@ManyToMany
	@JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id") )
	private Collection<Privilege> privileges;
	
	@Getter @Setter
	private String name;

	public Role() {
		super();
	}

	public Role(final String name) {
		super();
		this.name = name;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role role = (Role) obj;
        if (!getName().equals(role.getName())) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Role [name=").append(name).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }
}
