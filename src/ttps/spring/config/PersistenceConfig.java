package ttps.spring.config;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

	private static final String MODEL_PACKAGE = "ttps.spring.model";
	private static final String DAO_PACKAGE = "ttps.spring.dao";
	private static final String IMP_PACKAGE = "ttps.spring.implementation";
	private static final String SERVICE_PACKAGE = "ttps.spring.rest.services";
	private static final String CONTROLLER_PACKAGE = "ttps.spring.rest.controllers";
	private static final String SECURITY_PACKAGE = "ttps.spring.rest.security";
	
	@Bean 
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan(new String[] {
				MODEL_PACKAGE,
				DAO_PACKAGE,
				IMP_PACKAGE,
				SERVICE_PACKAGE,
				CONTROLLER_PACKAGE,
				SECURITY_PACKAGE});
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setJpaProperties(additionalProperties());
		return emf;		
	}
		
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUsername("mtribino");
		driverManagerDataSource.setPassword("head18");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/veterinaria");
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return driverManagerDataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;
	}
	
	
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.setProperty("spring.jpa.properties.javax.persistence.validation.mode", "none");

		return properties;
	}
		
}
