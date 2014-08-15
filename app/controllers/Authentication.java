package controllers;

import com.avaje.ebean.Ebean;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Result;

import javax.persistence.Entity;
import javax.persistence.Id;

import static play.data.Form.form;
import static play.mvc.Controller.session;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

import views.html.*;

/**
 * Created by loretta on 15/08/14.
 */
public class Authentication {

    public static Result login() {
        return ok(login.render(form(User.class)));
    }

    public static Result authenticate() {
        Form<User> loginForm = form(User.class).bindFromRequest();
        User user = Ebean.find(User.class)
                .where().eq("email", loginForm.get().email)
                .eq("password", loginForm.get().password)
                .findUnique();
        if (user == null) {
            return badRequest(login.render(form(User.class)));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(routes.Application.index());
        }
    }

    public static Result register() {
        return ok(register.render(form(User.class)));
    }

    public static Result addUser() {
        Form<User> registerForm = form(User.class).bindFromRequest();
        User existingUser = Ebean.find(User.class)
                .where().eq("email", registerForm.get().email).findUnique();
        if (existingUser != null) {
            return badRequest(register.render(form(User.class)));
        } else {
            Ebean.save(registerForm.get());
            return redirect(login.render(form(User.class)));
        }
    }

    @Entity
    public static class User {
        @Id
        public Long id;

        @Constraints.Required
        @Constraints.Email
        public String email;

        @Constraints.Required
        public String password;
    }
}
