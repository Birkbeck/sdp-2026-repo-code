package helloworld;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

public class HelloWorldSpring {
    // Requires the org.springframework:spring-beans:7.0.3 library
    // (e.g., spring-beans, spring-core and spring-jcl from the maven central repo)
    // Remember to mark the resources directory (with the beans file) as Resources Root
    public static void main(String... args) throws IOException, BeansException {
        // get the bean factory
        BeanFactory factory = getBeanFactory();

        MessageRenderer mr = (MessageRenderer) factory.getBean("renderer");
        MessageProvider mp = (MessageProvider) factory.getBean("provider");
        mr.setMessageProvider(mp);
        mr.render();
    }

    private static BeanFactory getBeanFactory() throws IOException {
        // Get the bean factory - understanding DefaultListableBeanFactory() not really important.
        // It is just a Factory class example from Spring.
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // create a definition reader
        PropertiesBeanDefinitionReader rdr = new PropertiesBeanDefinitionReader(factory);

        // load the configuration options
        Properties props = new Properties();
        try (var fis = HelloWorldSpring.class.getResourceAsStream("/beans")) {
            props.load(fis);
        }
        rdr.registerBeanDefinitions(props);

        return factory;
    }
}