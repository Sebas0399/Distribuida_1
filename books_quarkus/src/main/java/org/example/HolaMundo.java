package org.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hola")
public class HolaMundo {
    @Inject
    @ConfigProperty(name = "app.books.msg",defaultValue = "xx")
    private String mensaje;
    @GET
    public String holaMundo() {
        Config config= ConfigProvider.getConfig();
        config.getConfigSources().forEach(x->{
                    System.out.print(x.getOrdinal());

                    System.out.println(x.getName());
                }

        );
        var msg= config.getValue("app.books.msg", String.class);
        System.out.println(msg);
        System.out.println(mensaje);
        return "Hola mundo!";
    }
}
