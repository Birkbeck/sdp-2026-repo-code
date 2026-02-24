package template;

import java.util.function.Consumer;

public class TemplateLambda {
    public static void withResource(Consumer<Resource> consumer) {
        try (Resource resource = new Resource()) {
            consumer.accept(resource);
        }
    }

    public static void main(String... args) {
        withResource(Resource::useResource);
        withResource(Resource::employResource);
    }
}
