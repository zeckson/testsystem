package ru.ifmo.qd.testing.client.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.annotation.Inherited;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 21:39:00
 * To change this template use File | Settings | File Templates.
 */
@DiscriminatorValue(value = "ADMIN")
@Table(name = "people")
@Entity
public class Admin extends Person implements Serializable {

}
