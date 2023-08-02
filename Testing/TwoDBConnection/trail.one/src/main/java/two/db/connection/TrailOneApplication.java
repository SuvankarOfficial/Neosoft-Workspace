package two.db.connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {"two.db.connection"})
@EnableJpaRepositories(basePackages = {"two.db.connection.sql.repository.user","two.db.connection.postsql.repository.product"})
//@EntityScan(bas)
public class TrailOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailOneApplication.class, args);
	}



}
