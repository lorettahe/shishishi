package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by loretta on 15/08/14.
 */
@Entity
public class Users extends Model {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="users_id_seq")
    public Long id;

    @Constraints.Required
    @Constraints.Email
    public String email;

    @Constraints.Required
    public String password;
}