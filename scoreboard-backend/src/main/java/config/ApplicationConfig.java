/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Eric
 */
@javax.ws.rs.ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     * 
     * Moet er altijd in blijven staan
     * resources.add(MultiPartFeature.class);
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(api.GameApi.class);
        resources.add(api.UserAPI.class);
    }
    
}
