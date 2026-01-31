public class Greeter {
    private Personality personality;

    private static class DefaultPersonality implements Personality {
        @Override
        public String greet() {
            return "Hello.";
        }
    }

    @Deprecated
    public Greeter() {
        this(new DefaultPersonality());
    }

    public Greeter(Personality personality) {
        this.personality = personality;
    }

    /**
     * to be eliminated when the client code is refactored
     * @param formality level of formality
     */
    @Deprecated
    public void setFormality(String formality) {
        personality = switch (formality) {
            case "formal" ->  new FormalPersonality();
            case "casual" -> new CasualPersonality();
            case "intimate" -> new IntimatePersonality();
            default -> new DefaultPersonality();
        };
    }

    public String greet() {
        return this.personality.greet();
    }
}
