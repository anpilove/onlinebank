package com.anpilov.onlinebank.user;

import jakarta.persistence.*;

/**
 * The Role class represents a user role in the online bank system.
 *
 * @author Anpilov Kirill
 * @version 1.0
 */
@Entity
@Table(name = "role")
public class Role {

	/**
	 * The unique identifier for this role.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The name of this role.
	 */
	private String name;

	/**
	 * Creates a new Role instance with the given name.
	 *
	 * @param name the name of the role.
	 */
	public Role(String name) {
		this.name = name;
	}

	/**
	 * Creates a new Role instance.
	 */
	public Role() {
	}

	/**
	 * Returns the unique identifier for this role.
	 *
	 * @return the unique identifier for this role.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique identifier for this role.
	 *
	 * @param id the unique identifier for this role.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the name of this role.
	 *
	 * @return the name of this role.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this role.
	 *
	 * @param name the name of this role.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
