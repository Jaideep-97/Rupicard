import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.boot.autoconfigure.SpringBootApplication
@ComponentScan(basePackages = {"com.rupicard"})
@EntityScan(basePackages = {"com.rupicard.entity"})
public class SpringBootApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringBootApplication.class);
    }
}