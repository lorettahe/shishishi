package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by loretta on 14/08/14.
 */
@Entity
public class Poem extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String title;

    @Constraints.Required
    public String author;

    @Constraints.Required
    public String content;

    public static Finder<Long, Poem> find = new Finder<Long, Poem>(
            Long.class, Poem.class
    );
}
