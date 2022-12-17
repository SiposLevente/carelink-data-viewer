package com.levente.carelink.viewer;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.levente.carelink.viewer.objects.CareLinkDataManager;
import com.levente.carelink.viewer.objects.CareLinkSettingsReader;
import com.levente.carelink.viewer.objects.Settings;

@SpringBootApplication
public class SpringBootJspApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootJspApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootJspApplication.class, args);

        System.out.print("Getting CareLink instance...");
        if (CareLinkSettingsReader.SettingsFileExists()) {

            CareLinkDataManager.setUnit(CareLinkSettingsReader.GetConfigProperty(Settings.unit));
            CareLinkDataManager.setLoginData(CareLinkSettingsReader.GetConfigProperty(Settings.username),
                    CareLinkSettingsReader.GetConfigProperty(Settings.password),
                    CareLinkSettingsReader.GetConfigProperty(Settings.country_code));
            CareLinkDataManager.GetInstance();
            System.out.println("done! You can now open http://localhost:8080/ to view the current glucose data!");
        } else {
            throw new FileNotFoundException();
        }
    }

}
