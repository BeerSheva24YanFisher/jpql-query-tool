package telran.queries.config;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

public class BullsCowsPersistenceUnitInfo implements PersistenceUnitInfo{

    @Override
    public String getPersistenceUnitName() {
       return "bulls-cows-unit";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public String getScopeAnnotationName() {
       return null;
    }

    @Override
    public List<String> getQualifierAnnotationNames() {
        return null;
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return null;
    }

    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    @Override
    public DataSource getNonJtaDataSource() {
         HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(String.format("jdbc:postgresql://%s:5432/postgres",
                "16.171.22.97"));
        ds.setPassword("12345.com");
        ds.setUsername("postgres");
        ds.setDriverClassName("org.postgresql.Driver");
        return ds;
    }

    @Override
    public List<String> getMappingFileNames() {
        return null;
    }

    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return List.of("telran.queries.entities.Game","telran.queries.entities.GameGamer",
         "telran.queries.entities.Gamer","telran.queries.entities.Move");
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
       return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        // Properties properties = new Properties();
        // properties.put("jakarta.persistence.jdbc.url", "jdbc:postgresql://16.171.22.97:5432/postgres");
        // properties.put("jakarta.persistence.jdbc.user", "postgres");
        // properties.put("jakarta.persistence.jdbc.password", "12345.com");
        // properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        //     properties.put("hibernate.hbm2ddl.auto", "update");
        //     properties.put("hibernate.show_sql", "true");
        //     properties.put("hibernate.format_sql", "true");
        return null; //properties;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer transformer) {
       
    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }

}