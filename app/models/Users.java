package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by loretta on 15/08/14.
 */
@Entity
public class Users extends Model {
    @Id
    public Long id;

    @Constraints.Required
    @Constraints.Email
    public String email;

    @Constraints.Required
    public String password;
}