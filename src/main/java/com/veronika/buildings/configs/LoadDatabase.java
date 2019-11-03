package com.veronika.buildings.configs;

import com.veronika.buildings.model.AddressEntity;
import com.veronika.buildings.model.BuildingEntity;
import com.veronika.buildings.model.PropertyType;
import com.veronika.buildings.repositories.BuildingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private Logger LOG = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BuildingRepository repository) {
        return args -> {

            LOG.info("Preloading " + repository.save(new BuildingEntity("aw1", new AddressEntity("aaa", "aaa", 12),
                    120000.01, PropertyType.APARTMENT, 130)));

            LOG.info("Preloading " + repository.save(new BuildingEntity("aw2", new AddressEntity("aaa", "aaa", 12),
                    120.01, PropertyType.APARTMENT, 140)));

            LOG.info("Preloading " + repository.save(new BuildingEntity("aw3", new AddressEntity("aaa", "aaa", 12),
                    12.01, PropertyType.APARTMENT, 120)));

            LOG.info("Preloading " + repository.save(new BuildingEntity("aw4", new AddressEntity("aaa", "aaa", 12),
                    1200000.01, PropertyType.APARTMENT, 120)));

            LOG.info("Preloading " + repository.save(new BuildingEntity("aw5", new AddressEntity("aaa", "bbb", 12),
                    12.01, PropertyType.APARTMENT, 130)));

            LOG.info("Preloading " + repository.save(new BuildingEntity("aw6", new AddressEntity("bbb", "aaa", 12),
                    12.01, PropertyType.APARTMENT, 130)));

            LOG.info("Preloading " + repository.save(new BuildingEntity("aw7", new AddressEntity("aaa", "aaa", 12),
                    12.01, PropertyType.APARTMENT, 180)));

            LOG.info("Preloading " + repository.save(new BuildingEntity("aw1", new AddressEntity("aaa", "aaa", 12),
                    12.01, PropertyType.HOUSE, 130)));

            LOG.info("Preloading " + repository.save(new BuildingEntity("aw1", new AddressEntity("aaa", "aaa", 12),
                    12.01, PropertyType.INDUSTRIAL, 130)));
        };
    }
}
