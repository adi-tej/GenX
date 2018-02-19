package com.genx.evelyn;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoClientURI;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

/**
 * @author adi SpringBoot Main class.
 */
@SpringBootApplication
public class GenXApplication {

	private static final String MAIL_SMTP_STARTTLS_REQUIRED = "mail.smtp.starttls.required";
	private static final String MAIL_DEBUG = "mail.debug";
	private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

	@Value("${spring.data.mongodb.database}")
	private String database;
	@Value("${mongodb.maxconnection}")
	private Integer maxConnection;
	@Value("${mongodb.minconnection}")
	private Integer minConnection;
	@Value("${mongodb.timeout}")
	private Integer connectionTimeOut;
	@Value("${spring.data.mongodb.uri}")
	private String connectionURI;
	// SMTP configuration
	@Value("${smtp.host}")
	private String smtpHost;
	@Value("${smtp.port}")
	private Integer smtpPort;
	@Value("${smtp.username}")
	private String smtpUserName;
	@Value("${smtp.password}")
	private String smtpPassword;
	@Value("${mail.transport.protocol}")
	private String transportProtocol;
	@Value("${smtp.auth}")
	private String smtpAuth;
	@Value("${smtp.starttls.enable}")
	private String smtpStarttlsEnable;
	@Value("${mail.debug}")
	private String mailDebug;
	@Value("${smtp.starttls.required}")
	private String starttlsRequired;
	
	@Autowired
	private Configuration fmConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(GenXApplication.class, args);
	}

	@Bean(name = "mongoclient")
	public MongoClient mongoclient() throws Exception {

		Builder builder = new MongoClientOptions.Builder();
		builder.connectionsPerHost(maxConnection);
		builder.connectTimeout(connectionTimeOut);
		builder.minConnectionsPerHost(minConnection);
		MongoClientURI clientURI = new MongoClientURI(connectionURI, builder);
		return new MongoClient(clientURI);
	}

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(smtpHost);
		mailSender.setPort(smtpPort);
		mailSender.setUsername(smtpUserName);
		mailSender.setPassword(smtpPassword);

		Properties javaMailProperties = new Properties();
		javaMailProperties.put(MAIL_SMTP_STARTTLS_ENABLE, smtpStarttlsEnable);
		javaMailProperties.put(MAIL_SMTP_AUTH, smtpAuth);
		javaMailProperties.put(MAIL_TRANSPORT_PROTOCOL, transportProtocol);
		javaMailProperties.put(MAIL_DEBUG, mailDebug);
		javaMailProperties.put(MAIL_SMTP_STARTTLS_REQUIRED, starttlsRequired);
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	@Bean("fmconfig")
	public Configuration getConfiguration() {
		ClassTemplateLoader templateLoader = new ClassTemplateLoader(GenXApplication.class,"/templates/");
		fmConfiguration.setTemplateLoader(templateLoader);
		return fmConfiguration;
	}

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongoclient(), database);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory()), new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), converter);
        return mongoTemplate;
    }

}
