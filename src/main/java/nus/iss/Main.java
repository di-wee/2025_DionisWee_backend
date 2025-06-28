package nus.iss;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import nus.iss.apis.CoinChangeResource;
import nus.iss.apis.PingResource;

public class Main  extends Application<AppConfiguration> {
    public static void main(String[] args) throws Exception {

        new Main().run(args);
    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new PingResource());
        environment.jersey().register(new CoinChangeResource());

    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }
}