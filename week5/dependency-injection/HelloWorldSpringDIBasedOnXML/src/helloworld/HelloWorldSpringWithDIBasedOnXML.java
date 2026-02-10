package helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldSpringWithDIBasedOnXML {

    public static void main(String... args) {
        // requires in addition the Spring Context library
        // (e.g., org.springframework:spring-context:7.0.3 from the Maven central repo)
        // Remember to mark the resources directory (with beans.xml) as Resources Root

        // get the bean factory
        BeanFactory factory = new ClassPathXmlApplicationContext("/beans.xml");
        MessageRenderer mr = (MessageRenderer) factory.getBean("renderer");
        mr.render();
    }
}